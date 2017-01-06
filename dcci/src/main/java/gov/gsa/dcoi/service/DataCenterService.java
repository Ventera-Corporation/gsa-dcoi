package gov.gsa.dcoi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import gov.gsa.dcoi.DcoiRestMessage;
import gov.gsa.dcoi.dto.DataCenterDto;
import gov.gsa.dcoi.dto.FacilityInformationDto;
import gov.gsa.dcoi.dto.FieldOfficeDto;
import gov.gsa.dcoi.dto.GeneralInformationDto;
import gov.gsa.dcoi.dto.StatusDto;
import gov.gsa.dcoi.entity.DataCenter;
import gov.gsa.dcoi.entity.DataCenterQuarter;
import gov.gsa.dcoi.entity.FieldOffice;
import gov.gsa.dcoi.entity.QuarterReport;
import gov.gsa.dcoi.refValueEntity.ReferenceValueConstants;
import gov.gsa.dcoi.repository.DataCenterQuarterRepository;
import gov.gsa.dcoi.repository.DataCenterRepository;
import gov.gsa.dcoi.repository.FieldOfficeRepository;
import gov.gsa.dcoi.repository.QuarterReportRepository;
import gov.gsa.dcoi.security.SecurityUtils;
import gov.gsa.dcoi.security.User;

/**
 * Service class to handle database connection and information collection for
 * data centers by quarter
 * 
 * @author sgonthier
 *
 */
@Component
public class DataCenterService {

	private static final String MESSAGE_LIST = "messageList";
	public static final String ERROR_ALERT = "error.alert";

	@Autowired
	DataCenterQuarterRepository dataCenterQuarterRepository;

	@Autowired
	DataCenterRepository dataCenterRepository;

	@Autowired
	FieldOfficeService fieldOfficeService;

	@Autowired
	FieldOfficeRepository fieldOfficeRepository;

	@Autowired
	CacheManager cacheManager;

	@Autowired
	MessageSource messageSource;

	@Autowired
	QuarterReportRepository quarterReportRepository;

	@Autowired
	SecurityUtils securityUtils;

	/**
	 * Find data center quarter information by the quarter report ID
	 * 
	 * @param curQuarterId
	 * @return
	 */
	public List<DataCenterQuarter> findByQuarterReportId(Long curQuarterId) {
		return dataCenterQuarterRepository.findByQuarterReportId(curQuarterId);
	}

	/**
	 * Search
	 * 
	 * @return
	 */
	public List<DataCenterDto> executeSearch() {
		Iterator<DataCenterQuarter> allQuarters = dataCenterQuarterRepository.findAll().iterator();
		List<DataCenterDto> returnDataCenters = new ArrayList<DataCenterDto>();
		while (allQuarters.hasNext()) {
			DataCenterDto dataCenterDto = new DataCenterDto();
			DataCenterQuarter quarter = allQuarters.next();
			DataCenter dataCenter = dataCenterRepository.findByDataCenterId(quarter.getDataCenterId());
			returnDataCenters.add(copyEntityToDto(quarter, dataCenter, dataCenterDto));
		}
		return returnDataCenters;
	}

	/**
	 * Populate the dataCenterDto Lists to display back for a quarter
	 * 
	 * @param regionId
	 * @param quarterReportId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<DataCenterDto> populateDataCenterDtosList(Integer regionId, Long quarterReportId) {
		List<DataCenterDto> dataCenterDtos = new ArrayList<DataCenterDto>();
		List<DataCenterQuarter> dataCenterStaticInfo = dataCenterQuarterRepository
				.findByRegionIdAndQuarterReportId(regionId, quarterReportId);
		for (DataCenterQuarter dataCenterQuarter : dataCenterStaticInfo) {
			DataCenter dataCenter = dataCenterRepository.findByDataCenterId(dataCenterQuarter.getDataCenterId());
			DataCenterDto dataCenterDto = new DataCenterDto();
			dataCenterDtos.add(copyEntityToDto(dataCenterQuarter, dataCenter, dataCenterDto));
		}
		return dataCenterDtos;
	}

	/**
	 * Will save the edited information for data centers that are passed back
	 * after a "save changes" call from the application
	 * 
	 * @param dataCenterDtos
	 * @param user
	 * @return
	 */
	@Transactional
	public void saveDataCenters(List<DataCenterDto> dataCenterDtos, User user) {
		for (DataCenterDto dataCenterDto : dataCenterDtos) {
			DataCenter dataCenterEntity = new DataCenter();
			CommonHelper.modelMapper.map(dataCenterDto, dataCenterEntity);
			if (user.getRoleIds().contains(ReferenceValueConstants.ADMIN_ROLE)) {
				CommonHelper.modelMapper.map(dataCenterDto.getGeneralInfo(), dataCenterEntity);
				CommonHelper.modelMapper.map(dataCenterDto.getStatus(), dataCenterEntity);
				CommonHelper.modelMapper.map(dataCenterDto.getFacilityInfo(), dataCenterEntity);
			}
			if (user.getRoleIds().contains(ReferenceValueConstants.FACILITY_ROLE)) {
				CommonHelper.modelMapper.map(dataCenterDto.getFacilityInfo(), dataCenterEntity);
			}
			securityUtils.setUserIdForAudit();
			dataCenterRepository.save(dataCenterEntity);

			for (FieldOfficeDto fieldOfficeDto : dataCenterDto.getFieldOffices()) {
				securityUtils.setUserIdForAudit();
				dataCenterQuarterRepository.save(otherCalculations(copyDtoToEntity(dataCenterDto, fieldOfficeDto)));
				if (user.getRoleIds().contains(ReferenceValueConstants.ADMIN_ROLE)
						|| (user.getFieldOfficeIds().contains(fieldOfficeDto.getComponentId())
								&& user.getRoleIds().contains(ReferenceValueConstants.SERVER_ROLE))) {
					securityUtils.setUserIdForAudit();
					fieldOfficeRepository.save(fieldOfficeService.copyDtoToVO(fieldOfficeDto, new FieldOffice()));
				}
			}
		}
	}

	/**
	 * Helper method to compute the other OMB calculations
	 * 
	 * @param dataCenterQuarter
	 * @return
	 */
	private DataCenterQuarter otherCalculations(DataCenterQuarter dataCenterQuarter) {
		otherCalcFromStatusInfo(dataCenterQuarter);
		otherCalcFromFacilityInfo(dataCenterQuarter);
		otherCalcFromServerInfo(dataCenterQuarter);
		return dataCenterQuarter;
	}

	/**
	 * Helper method to compute the additional calculated fields corresponding
	 * to the statusDTO
	 * 
	 * @param dataCenterQuarter
	 */
	private void otherCalcFromStatusInfo(DataCenterQuarter dataCenterQuarter) {
		Integer dataCenterTierId = dataCenterQuarter.getDataCenterTierId();

		if ((dataCenterQuarter.getOwnershipTypeId() != null
				&& dataCenterQuarter.getOwnershipTypeId().equals(ReferenceValueConstants.CLOUD_PROVIDER_OWNERSHIP_TYPE))
				|| (dataCenterQuarter.getRecordValidityId() != null
						&& dataCenterQuarter.getRecordValidityId().equals(ReferenceValueConstants.INVALID_FACILITY))) {
			dataCenterQuarter.setTierClassification("Using Cloud Provider");
		} else if (dataCenterTierId != null && (dataCenterTierId.equals(ReferenceValueConstants.TIER_1)
				|| dataCenterTierId.equals(ReferenceValueConstants.TIER_2)
				|| dataCenterTierId.equals(ReferenceValueConstants.TIER_3)
				|| dataCenterTierId.equals(ReferenceValueConstants.TIER_4))) {
			dataCenterQuarter.setTierClassification("Tiered");
		} else {
			dataCenterQuarter.setTierClassification("Non-Tiered");
		}
		if (dataCenterQuarter.getClosingFiscalQuarterId() != null
				&& dataCenterQuarter.getClosingFiscalYearId() != null) {
			dataCenterQuarter.setClosingTargetDate(
					CommonHelper.parseFiscalQuarterId(dataCenterQuarter.getClosingFiscalQuarterId()) + "/"
							+ CommonHelper.parseFiscalYearId(dataCenterQuarter.getClosingFiscalYearId()));
		}
	}

	/**
	 * Helper method to compute the additional calculated fields from the
	 * facilityDTO
	 * 
	 * @param dataCenterQuarter
	 */
	private void otherCalcFromFacilityInfo(DataCenterQuarter dataCenterQuarter) {
		Integer grossFloorArea = dataCenterQuarter.getGrossFloorArea();

		if (grossFloorArea != null) {
			// Floor Area Classification
			if (grossFloorArea < Integer.valueOf(100)) {
				dataCenterQuarter.setFloorAreaClassification("< 100 sq. ft.");
			} else if (grossFloorArea < Integer.valueOf(250)) {
				dataCenterQuarter.setFloorAreaClassification("< 250 sq. ft.");
			} else if (grossFloorArea < Integer.valueOf(500)) {
				dataCenterQuarter.setFloorAreaClassification("< 500 sq. ft.");
			} else if (grossFloorArea < Integer.valueOf(5000)) {
				dataCenterQuarter.setFloorAreaClassification("< 5000 sq. ft.");
			} else {
				dataCenterQuarter.setFloorAreaClassification(">= 5000 sq. ft.");
			}

			// Watts Per Sq Ft
			if (dataCenterQuarter.getTotalITPowerCapacity() != null) {
				dataCenterQuarter.setWattsSqFt(
						(dataCenterQuarter.getTotalITPowerCapacity() * Double.valueOf(1000)) / grossFloorArea);
			}
		}

		if (dataCenterQuarter.getAvgITElectricityUsage() != null && dataCenterQuarter.getAvgITElectricityUsage() != 0
				&& dataCenterQuarter.getAvgElectricityUsage() != null) {
			dataCenterQuarter
					.setPue(dataCenterQuarter.getAvgElectricityUsage() / dataCenterQuarter.getAvgITElectricityUsage());
		}
	}

	/**
	 * Helper method to compute the additional calculated fields corresponding
	 * to the serverInfoDto
	 * 
	 * @param dataCenterQuarter
	 */
	private void otherCalcFromServerInfo(DataCenterQuarter dataCenterQuarter) {

		dataCenterQuarter.setPhysicalServerCount(addPhysicalServerCounts(dataCenterQuarter));

		dataCenterQuarter.setOsCount(addOSServerCounts(dataCenterQuarter));

		if (dataCenterQuarter.getUsedStorage() != null && (dataCenterQuarter.getTotalStorage() != null
				&& dataCenterQuarter.getTotalStorage() == Double.valueOf(0.0))) {
			dataCenterQuarter.setStorageUtilization(
					(dataCenterQuarter.getUsedStorage() / dataCenterQuarter.getTotalStorage()) * Double.valueOf(100));
		}

	}

	/**
	 * Temp helper method to add physical server counts together
	 * 
	 * @param dataCenterQuarter
	 * @return Integer
	 */
	private Integer addPhysicalServerCounts(DataCenterQuarter dataCenterQuarter) {
		Integer serverCount = 0;
		if (dataCenterQuarter.getTotalWindowsServers() != null) {
			serverCount += dataCenterQuarter.getTotalWindowsServers();
		}
		if (dataCenterQuarter.getTotalMainframes() != null) {
			serverCount += dataCenterQuarter.getTotalMainframes();
		}
		if (dataCenterQuarter.getTotalOtherServers() != null) {
			serverCount += dataCenterQuarter.getTotalOtherServers();
		}
		return serverCount;
	}

	/**
	 * Temp helper method to add os server counts together
	 * 
	 * @param dataCenterQuarter
	 * @return Integer
	 */
	private Integer addOSServerCounts(DataCenterQuarter dataCenterQuarter) {
		Integer serverCount = 0;
		if (dataCenterQuarter.getPhysicalServerCount() != null) {
			serverCount += dataCenterQuarter.getPhysicalServerCount();
		}
		if (dataCenterQuarter.getTotalHPCClusterNodes() != null) {
			serverCount += dataCenterQuarter.getTotalHPCClusterNodes();
		}
		if (dataCenterQuarter.getTotalVirtualHosts() != null) {
			serverCount += dataCenterQuarter.getTotalVirtualHosts();
		}
		return serverCount;
	}

	/**
	 * Will save the edited information for data centers that are passed back
	 * after a "save changes" call from the application
	 * 
	 * @param dataCenterDtos
	 * @return
	 */
	@Transactional
	public DataCenterDto addAndReturnNewDataCenter(DataCenterDto dataCenterDto) {
		// Save data center object
		DataCenter dataCenter = new DataCenter();
		CommonHelper.modelMapper.map(dataCenterDto, dataCenter);
		securityUtils.setUserIdForAudit();
		dataCenter = dataCenterRepository.save(dataCenter);
		// then copy data center object back for any updates
		CommonHelper.modelMapper.map(dataCenter, dataCenterDto);

		// Save Data Center Quarter object
		DataCenterQuarter dataCenterQuarter = new DataCenterQuarter();
		CommonHelper.modelMapper.map(dataCenter, dataCenterQuarter);
		QuarterReport quarterReport = quarterReportRepository.findByQuarterInProgressFlag(1);
		dataCenterQuarter.setQuarterReportId(quarterReport.getQuarterId());
		dataCenterQuarter.setAdminCompleteFlag(0);
		dataCenterQuarter.setSsoCompleteFlag(0);
		securityUtils.setUserIdForAudit();
		dataCenterQuarter = save(dataCenterQuarter);
		// save new field offices
		for (FieldOfficeDto fieldOfficeDto : dataCenterDto.getFieldOffices()) {
			fieldOfficeDto.setDataCenterQuarterId(dataCenterQuarter.getDataCenterQuarterId());
			securityUtils.setUserIdForAudit();
			fieldOfficeService.save(fieldOfficeService.copyDtoToVO(fieldOfficeDto, new FieldOffice()));
		}
		return copyEntityToDto(dataCenterQuarter, dataCenter, dataCenterDto);
	}

	/**
	 * copy the Entity Properties to the DataCenterDto to be displayed on the
	 * front end
	 * 
	 * @param dataCenterQuarterEntity
	 * @param dataCenterEntity
	 * @param dataCenterDto
	 * @return
	 */
	public DataCenterDto copyEntityToDto(DataCenterQuarter dataCenterQuarterEntity, DataCenter dataCenterEntity,
			DataCenterDto dataCenterDto) {

		// General Information
		CommonHelper.modelMapper.map(dataCenterEntity, dataCenterDto);
		GeneralInformationDto generalInformationDto = new GeneralInformationDto();
		CommonHelper.modelMapper.map(dataCenterEntity, generalInformationDto);
		CommonHelper.modelMapper.map(dataCenterQuarterEntity, generalInformationDto);
		generalInformationDto.setPublishedName(dataCenterQuarterEntity.getPublishedName());
		if (StringUtils.isEmpty(generalInformationDto.getAgencyAbbreviation())) {
			generalInformationDto.setAgencyAbbreviation("GSA");
		}

		// set the field offices and the component field in general info
		List<FieldOfficeDto> fieldOfficeDtos = fieldOfficeService
				.populateFieldOfficeDtosList(dataCenterQuarterEntity.getDataCenterQuarterId());
		dataCenterDto.setFieldOffices(fieldOfficeDtos);
		if (StringUtils.isEmpty(generalInformationDto.getComponent())) {
			// if there is an OCIO field office set that as the component
			for (FieldOfficeDto fieldOfficeDto : fieldOfficeDtos) {
				if ("OCIO".equals(fieldOfficeDto.getFieldOfficeName())) {
					generalInformationDto.setComponent(fieldOfficeDto.getFieldOfficeName());
				}
			}
			// if there is no OCIO field office, set the first one as the
			// component
			if (StringUtils.isEmpty(generalInformationDto.getComponent())) {
				generalInformationDto.setComponent(fieldOfficeDtos.get(0).getFieldOfficeName());
			}
		}
		dataCenterDto.setGeneralInfo(generalInformationDto);

		// Status
		StatusDto statusDto = new StatusDto();
		CommonHelper.modelMapper.map(dataCenterQuarterEntity, statusDto);
		dataCenterDto.setStatus(statusDto);

		// Facility Info
		FacilityInformationDto facilityInfoDto = new FacilityInformationDto();
		CommonHelper.modelMapper.map(dataCenterQuarterEntity, facilityInfoDto);
		dataCenterDto.setFacilityInfo(facilityInfoDto);

		// Set the totals tab
		dataCenterDto.setTotals(fieldOfficeService.createTotalsTab(dataCenterQuarterEntity));
		CommonHelper.modelMapper.map(dataCenterQuarterEntity, dataCenterDto);
		return dataCenterDto;
	}

	/**
	 * Copy the dto information to the dataCenterQuarterEntity
	 * 
	 * @param dataCenterDto
	 * @return
	 */
	private DataCenterQuarter copyDtoToEntity(DataCenterDto dataCenterDto, FieldOfficeDto fieldOfficeDto) {
		// Set dataCenterDto
		DataCenterQuarter dataCenterQuarter = new DataCenterQuarter();
		CommonHelper.modelMapper.map(dataCenterDto, dataCenterQuarter);
		CommonHelper.modelMapper.map(dataCenterDto.getGeneralInfo(), dataCenterQuarter);
		CommonHelper.modelMapper.map(dataCenterDto.getStatus(), dataCenterQuarter);
		CommonHelper.modelMapper.map(dataCenterDto.getFacilityInfo(), dataCenterQuarter);

		// Set Field Office Info
		if (fieldOfficeDto != null) {
			CommonHelper.modelMapper.map(fieldOfficeDto, dataCenterQuarter);
			if (fieldOfficeDto.getServerInfo() != null) {
				CommonHelper.modelMapper.map(fieldOfficeDto.getServerInfo(), dataCenterQuarter);
			}
		}

		return dataCenterQuarter;
	}

	/**
	 * Set SSO complete flag to be true
	 * 
	 * @param dataCenterId
	 * @return
	 */
	@Transactional
	public Map<String, Object> setSSOCompleteFlag(Integer dataCenterId) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		QuarterReport quarterReport = quarterReportRepository.findByQuarterActiveFlag(1);
		DataCenterQuarter dataCenterQuarter = dataCenterQuarterRepository
				.findByQuarterReportIdAndDataCenterId(quarterReport.getQuarterId(), dataCenterId);

		if (dataCenterQuarter == null) {
			returnMap.put("errorMessage", new DcoiRestMessage(messageSource.getMessage("submitError", null, null)));
			return returnMap;
		}
		dataCenterQuarter.setSsoCompleteFlag(1);
		dataCenterQuarter.setAdminCompleteFlag(0);
		securityUtils.setUserIdForAudit();
		dataCenterQuarterRepository.save(dataCenterQuarter);
		return returnMap;
	}

	/**
	 * Set Admin complete flag to be true
	 * 
	 * @param dataCenterId
	 * @return
	 */
	@Transactional
	public Map<String, Object> setAdminCompleteFlag(Integer dataCenterId) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		QuarterReport quarterReport = quarterReportRepository.findByQuarterActiveFlag(1);
		DataCenterQuarter dataCenterQuarter = dataCenterQuarterRepository
				.findByQuarterReportIdAndDataCenterId(quarterReport.getQuarterId(), dataCenterId);

		if (dataCenterQuarter == null) {
			returnMap.put("errorMessage", new DcoiRestMessage(messageSource.getMessage("validateError", null, null)));
			return returnMap;
		}
		dataCenterQuarter.setAdminCompleteFlag(1);
		securityUtils.setUserIdForAudit();
		dataCenterQuarterRepository.save(dataCenterQuarter);
		return returnMap;
	}

	/**
	 * Set SSO complete flag back to 0, the sso will now have to edit it again
	 * 
	 * @param dataCenterId
	 * @return
	 */
	@Transactional
	public Map<String, Object> rejectDataCenter(Integer dataCenterId) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		QuarterReport quarterReport = quarterReportRepository.findByQuarterActiveFlag(1);
		DataCenterQuarter dataCenterQuarter = dataCenterQuarterRepository
				.findByQuarterReportIdAndDataCenterId(quarterReport.getQuarterId(), dataCenterId);

		if (dataCenterQuarter == null) {
			returnMap.put("errorMessage", new DcoiRestMessage(messageSource.getMessage("rejectError", null, null)));
			return returnMap;
		}
		dataCenterQuarter.setSsoCompleteFlag(0);
		dataCenterQuarter.setAdminCompleteFlag(1);
		securityUtils.setUserIdForAudit();
		dataCenterQuarterRepository.save(dataCenterQuarter);
		return returnMap;
	}

	/**
	 * Save data center quarter object
	 * 
	 * @param dataCenterQuarter
	 * @return
	 */
	@Transactional
	public DataCenterQuarter save(DataCenterQuarter dataCenterQuarter) {
		securityUtils.setUserIdForAudit();
		return dataCenterQuarterRepository.save(dataCenterQuarter);
	}

	/**
	 * Find the total number of data centers, the completed data centers and the
	 * in progress data centers for a quarter
	 * 
	 * @param quarterReportId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Integer> findDCCountsForQuarter(Long quarterReportId) {
		List<Integer> totalsList = new ArrayList<Integer>();
		Iterator<DataCenterQuarter> dataCenterQuarterList = dataCenterQuarterRepository
				.findByQuarterReportId(quarterReportId).iterator();
		Integer totalCount = 0;
		Integer completedCount = 0;
		Integer inProgress = 0;
		while (dataCenterQuarterList.hasNext()) {
			totalCount += 1;
			DataCenterQuarter dataCenterQuarter = dataCenterQuarterList.next();
			if (dataCenterQuarter.getAdminCompleteFlag() == 1 && dataCenterQuarter.getSsoCompleteFlag() == 1) {
				completedCount += 1;
			} else {
				inProgress += 1;
			}
		}
		totalsList.add(0, totalCount);
		totalsList.add(1, completedCount);
		totalsList.add(2, inProgress);
		return totalsList;
	}

	/**
	 * Private helper method to add validation messages for the closing
	 * information
	 * 
	 * @param dataCenterDto
	 * @param messages
	 */
	private void validateClosingInformation(DataCenterDto dataCenterDto, Map<String, String> messages) {
		if (!(dataCenterDto.getStatus().getClosingStageId() == ReferenceValueConstants.NOT_CLOSING
				|| dataCenterDto.getStatus().getClosingStageId() == ReferenceValueConstants.CONSIDERING)) {
			fillMessagesIfFieldIsNull(dataCenterDto.getStatus().getClosingFiscalYearId(), "closingFiscalYearRequired",
					messages);
			fillMessagesIfFieldIsNull(dataCenterDto.getStatus().getClosingFiscalQuarterId(),
					"closingFiscalQuarterRequired", messages);
		}
		if (dataCenterDto.getStatus().getClosingFiscalYearId() != null) {
			fillMessagesIfFieldIsNull(dataCenterDto.getStatus().getClosingFiscalQuarterId(),
					"closingFiscalQuarterRequired2", messages);
		}
		if (dataCenterDto.getStatus().getClosingFiscalQuarterId() != null) {
			fillMessagesIfFieldIsNull(dataCenterDto.getStatus().getClosingFiscalYearId(), "closingFiscalYearRequired2",
					messages);
		}

	}

	/**
	 * Private helper method to add validation messages for the server fields
	 * 
	 * @param dataCenterDto
	 * @param messages
	 */
	private void validateServerInformation(DataCenterDto dataCenterDto, Map<String, String> messages) {
		if (dataCenterDto.getFieldOffices() != null) {
			for (FieldOfficeDto fieldOfficeDto : dataCenterDto.getFieldOffices()) {
				if (fieldOfficeDto.getServerInfo() != null) {
					fillMessagesIfFieldIsNull(fieldOfficeDto.getServerInfo().getTotalMainframes(),
							"totalMainframesRequired", messages);
					fillMessagesIfFieldIsNull(fieldOfficeDto.getServerInfo().getTotalWindowsServers(),
							"totalWindowsServersRequired", messages);
					fillMessagesIfFieldIsNull(fieldOfficeDto.getServerInfo().getTotalHPCClusterNodes(),
							"totalHPCClusterNodesRequired", messages);
					fillMessagesIfFieldIsNull(fieldOfficeDto.getServerInfo().getTotalOtherServers(),
							"totalOtherServersRequired", messages);
					fillMessagesIfFieldIsNull(fieldOfficeDto.getServerInfo().getTotalVirtualHosts(),
							"totalVirtualHostsRequired", messages);
					fillMessagesIfFieldIsNull(fieldOfficeDto.getServerInfo().getTotalVirtualOS(),
							"totalVirtualOSRequired", messages);
				}
			}
		}
	}

	/**
	 * Private helper method to add validation messages for record validity
	 * 
	 * @param dataCenterDto
	 * @param messages
	 */
	private void validateRecordValidity(DataCenterDto dataCenterDto, Map<String, String> messages) {
		List<DataCenterQuarter> pastQuarterDataCenterList = dataCenterQuarterRepository
				.findByDataCenterId(dataCenterDto.getDataCenterId());

		if (pastQuarterDataCenterList.size() > 1) {
			DataCenterQuarter pastQuarterDataCenter = pastQuarterDataCenterList
					.get(pastQuarterDataCenterList.size() - 2);
			if (pastQuarterDataCenter.getRecordValidityId() == ReferenceValueConstants.INVALID_FACILITY
					&& dataCenterDto.getStatus().getRecordValidityId() == ReferenceValueConstants.VALID_FACILITY) {
				messages.put("recordValidityCannotChange",
						messageSource.getMessage("recordValidityCannotChange", null, null));
			}
		}
		if (dataCenterDto.getStatus().getClosingStageId() == ReferenceValueConstants.CLOSED
				&& dataCenterDto.getStatus().getRecordValidityId() == ReferenceValueConstants.INVALID_FACILITY) {
			messages.put("recordValidityIncorrect", messageSource.getMessage("recordValidityIncorrect", null, null));
		}

	}

	/**
	 * Add validations for the business logic rules
	 * 
	 * @param dataCenterDtos
	 * @return
	 */
	public Map<String, Object> validateDataCenters(List<DataCenterDto> dataCenterDtos) {

		Map<String, String> messages = new HashMap<>();
		Map<String, Object> errorData = new HashMap<>();
		for (DataCenterDto dataCenterDto : dataCenterDtos) {
			Boolean isNotInvalid = dataCenterDto.getStatus()
					.getRecordValidityId() != ReferenceValueConstants.INVALID_FACILITY;
			Boolean isAgencyOwned = dataCenterDto.getStatus()
					.getOwnershipTypeId() == ReferenceValueConstants.AGENCY_OWNED;
			Boolean isElectricityMeteredAndTiered = dataCenterDto.getStatus()
					.getElectricityIsMeteredId() == ReferenceValueConstants.YES && dataCenterIsTiered(dataCenterDto);

			validateClosingInformation(dataCenterDto, messages);
			validateRecordValidity(dataCenterDto, messages);

			if (dataCenterDto.getStatus().getRecordStatusId().equals(ReferenceValueConstants.EXISTING_FACILITY)
					&& (dataCenterDto.getGeneralInfo().getDcoiDataCenterId() == null
							|| dataCenterDto.getGeneralInfo().getDcoiDataCenterId().isEmpty())) {
				messages.put("dataCenterIdRequired", messageSource.getMessage("dataCenterIdRequired", null, null));
			}
			if (dataCenterDto.getStatus().getAutomatedMonitoringId() == ReferenceValueConstants.YES
					&& dataCenterDto.getFieldOffices() != null) {
				for (FieldOfficeDto fieldOfficeDto : dataCenterDto.getFieldOffices()) {
					if (fieldOfficeDto.getServerInfo() != null
							&& (fieldOfficeDto.getServerInfo().getServerUtilization() == null
									|| fieldOfficeDto.getServerInfo().getServerUtilization().isEmpty())) {
						messages.put("serverUtilizationRequired",
								messageSource.getMessage("serverUtilizationRequired", null, null));
					}
				}

			}
			if (isNotInvalid) {
				fillMessagesIfFieldIsNull(dataCenterDto.getStatus().getOwnershipTypeId(), "ownershipTypeRequired",
						messages);
				fillMessagesIfFieldIsNull(dataCenterDto.getStatus().getDataCenterTierId(), "dataCenterTierRequired",
						messages);
			}
			if (isNotInvalid && isAgencyOwned) {
				fillMessagesIfFieldIsNull(dataCenterDto.getFacilityInfo().getGrossFloorArea(), "grossFloorAreaRequired",
						messages);
				fillMessagesIfFieldIsNull(dataCenterDto.getStatus().getElectricityIsMeteredId(),
						"electricityMeteredRequired", messages);
				fillMessagesIfFieldIsNull(dataCenterDto.getStatus().getAutomatedMonitoringId(),
						"automatedMonitoringRequired", messages);
				fillMessagesIfFieldIsNull(dataCenterDto.getFacilityInfo().getFte(), "fteRequired", messages);
				fillMessagesIfFieldIsNull(dataCenterDto.getFacilityInfo().getRackCount(), "rackCountRequired",
						messages);
				validateServerInformation(dataCenterDto, messages);

			}
			if (isNotInvalid && isAgencyOwned && isElectricityMeteredAndTiered) {
				fillMessagesIfFieldIsNull(dataCenterDto.getFacilityInfo().getAvgElectricityUsage(),
						"avgElectrictyUsageRequired", messages);
				fillMessagesIfFieldIsNull(dataCenterDto.getFacilityInfo().getAvgITElectricityUsage(),
						"avgITElectrictyUsageRequired", messages);

			}
		}
		errorData.put("messages", messages);
		if (!messages.isEmpty()) {
			DcoiRestMessage message = new DcoiRestMessage(ERROR_ALERT,
					messageSource.getMessage(ERROR_ALERT, null, null));
			List<DcoiRestMessage> messageList = new ArrayList<>();
			messageList.add(message);
			errorData.put(MESSAGE_LIST, messageList);
			errorData.put("error", Boolean.valueOf("true"));
		}
		return errorData;
	}

	private void fillMessagesIfFieldIsNull(Object fieldValue, String fieldName, Map<String, String> messages) {

		if (fieldValue == null || fieldValue.toString().isEmpty()) {
			messages.put(fieldName, messageSource.getMessage(fieldName, null, null));
		}

	}

	/**
	 * Private helper method to determine if a data center is tiered or not
	 */
	private Boolean dataCenterIsTiered(DataCenterDto dataCenterDto) {
		Integer dataCenterTierId = dataCenterDto.getStatus().getDataCenterTierId();
		if (dataCenterTierId != null && (dataCenterTierId == ReferenceValueConstants.TIER_1
				|| dataCenterTierId == ReferenceValueConstants.TIER_2
				|| dataCenterTierId == ReferenceValueConstants.TIER_3
				|| dataCenterTierId == ReferenceValueConstants.TIER_4)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Find the component name to be displayed in the OMB report - default to
	 * OCIO whenever possible, otherwise defaults to one of the other provided
	 * components
	 * 
	 * @param dcoiDataCenterId
	 * @param quarterId
	 * @return
	 */
	public String findComponentNameForOMBReport(String dcoiDataCenterId, Long quarterId) {

		DataCenter dataCenter = dataCenterRepository.findByDcoiDataCenterId(dcoiDataCenterId);
		DataCenterQuarter dataCenterQuarter = dataCenterQuarterRepository
				.findByQuarterReportIdAndDataCenterId(quarterId, dataCenter.getDataCenterId());
		return fieldOfficeService.parseFieldOfficesForOverallComponent(dataCenterQuarter.getDataCenterQuarterId());

	}

}
