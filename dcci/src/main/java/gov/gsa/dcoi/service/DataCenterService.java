package gov.gsa.dcoi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import org.springframework.beans.BeanUtils;
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

/**
 * Service class to handle database connection and information collection for
 * data centers by quarter
 * 
 * @author sgonthier
 *
 */
@Component
public class DataCenterService {

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
			List<DataCenter> dataCenters = dataCenterRepository.findByDataCenterId(quarter.getDataCenterId());
			returnDataCenters.add(copyEntityToDto(quarter, dataCenters.get(0), dataCenterDto));
		}
		return returnDataCenters;
	}

	/**
	 * Populate the dataCenterDto Lists to display back for a quarter
	 * 
	 * @param quarterReportId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<DataCenterDto> populateDataCenterDtosList(Integer regionId, Long quarterReportId) {
		List<DataCenterDto> dataCenterDtos = new ArrayList<>();
		List<DataCenter> dataCenterStaticInfo = dataCenterRepository.findByRegionId(regionId);
		for (DataCenter dataCenter : dataCenterStaticInfo) {
			DataCenterDto dataCenterDto = new DataCenterDto();
			BeanUtils.copyProperties(dataCenter, dataCenterDto);
			DataCenterQuarter dataCenterForQuarter = dataCenterQuarterRepository
					.findByQuarterReportIdAndDataCenterId(quarterReportId, dataCenter.getDataCenterId());
			if (dataCenterForQuarter == null) {
				// This data center has historical information, but no
				// information for the current quarter
				continue;
			}
			dataCenterDtos.add(copyEntityToDto(dataCenterForQuarter, dataCenter, dataCenterDto));
		}
		return dataCenterDtos;
	}

	/**
	 * Will save the edited information for data centers that are passed back
	 * after a "save changes" call from the application
	 * 
	 * @param dataCenterDtos
	 * @return
	 */
	@Transactional
	public void saveDataCenters(List<DataCenterDto> dataCenterDtos) {
		for (DataCenterDto dataCenterDto : dataCenterDtos) {
			DataCenter dataCenterEntity = new DataCenter();
			BeanUtils.copyProperties(dataCenterDto, dataCenterEntity);
			BeanUtils.copyProperties(dataCenterDto.getGeneralInfo(), dataCenterEntity);
			BeanUtils.copyProperties(dataCenterDto.getStatus(), dataCenterEntity);
			BeanUtils.copyProperties(dataCenterDto.getFacilityInfo(), dataCenterEntity);
			dataCenterRepository.save(dataCenterEntity);

			for (FieldOfficeDto fieldOfficeDto : dataCenterDto.getFieldOffices()) {

				dataCenterQuarterRepository.save(otherCalculations(copyDtoToEntity(dataCenterDto, fieldOfficeDto)));
				fieldOfficeRepository.save(fieldOfficeService.copyDtoToVO(fieldOfficeDto, new FieldOffice()));
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
				&& dataCenterQuarter.getTotalStorage().equals(Double.valueOf(0)))) {
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
		BeanUtils.copyProperties(dataCenterDto, dataCenter);
		dataCenter = dataCenterRepository.save(dataCenter);
		// then copy data center object back for any updates
		BeanUtils.copyProperties(dataCenter, dataCenterDto);

		// Save Data Center Quarter object
		DataCenterQuarter dataCenterQuarter = new DataCenterQuarter();
		BeanUtils.copyProperties(dataCenter, dataCenterQuarter);
		QuarterReport quarterReport = quarterReportRepository.findByQuarterInProgressFlag(1);
		dataCenterQuarter.setQuarterReportId(quarterReport.getQuarterId());
		dataCenterQuarter.setAdminCompleteFlag(0);
		dataCenterQuarter.setSsoCompleteFlag(0);
		dataCenterQuarter = save(dataCenterQuarter);
		// save new field offices
		for (FieldOfficeDto fieldOfficeDto : dataCenterDto.getFieldOffices()) {
			fieldOfficeDto.setDataCenterQuarterId(dataCenterQuarter.getDataCenterQuarterId());
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
		BeanUtils.copyProperties(dataCenterEntity, dataCenterDto);
		GeneralInformationDto generalInformationDto = new GeneralInformationDto();
		BeanUtils.copyProperties(dataCenterEntity, generalInformationDto);
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
		BeanUtils.copyProperties(dataCenterQuarterEntity, statusDto);
		dataCenterDto.setStatus(statusDto);

		// Facility Info
		FacilityInformationDto facilityInfoDto = new FacilityInformationDto();
		BeanUtils.copyProperties(dataCenterQuarterEntity, facilityInfoDto);
		dataCenterDto.setFacilityInfo(facilityInfoDto);

		// Set the totals tab
		dataCenterDto.setTotals(fieldOfficeService.createTotalsTab(dataCenterQuarterEntity));

		BeanUtils.copyProperties(dataCenterQuarterEntity, dataCenterDto);
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
		BeanUtils.copyProperties(dataCenterDto, dataCenterQuarter);
		BeanUtils.copyProperties(dataCenterDto.getGeneralInfo(), dataCenterQuarter);
		BeanUtils.copyProperties(dataCenterDto.getStatus(), dataCenterQuarter);
		BeanUtils.copyProperties(dataCenterDto.getFacilityInfo(), dataCenterQuarter);

		// Set Field Office Info
		if (fieldOfficeDto != null) {
			BeanUtils.copyProperties(fieldOfficeDto, dataCenterQuarter);
			if (fieldOfficeDto.getServerInfo() != null) {
				BeanUtils.copyProperties(fieldOfficeDto.getServerInfo(), dataCenterQuarter);
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
		Map<String, Object> returnMap = new HashMap<>();
		QuarterReport quarterReport = quarterReportRepository.findByQuarterActiveFlag(1);
		DataCenterQuarter dataCenterQuarter = dataCenterQuarterRepository
				.findByQuarterReportIdAndDataCenterId(quarterReport.getQuarterId(), dataCenterId);

		if (dataCenterQuarter == null) {
			returnMap.put("errorMessage", new DcoiRestMessage(messageSource.getMessage("submitError", null, null)));
			return returnMap;
		}
		dataCenterQuarter.setSsoCompleteFlag(1);
		dataCenterQuarter.setAdminCompleteFlag(0);
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
		Map<String, Object> returnMap = new HashMap<>();
		QuarterReport quarterReport = quarterReportRepository.findByQuarterActiveFlag(1);
		DataCenterQuarter dataCenterQuarter = dataCenterQuarterRepository
				.findByQuarterReportIdAndDataCenterId(quarterReport.getQuarterId(), dataCenterId);

		if (dataCenterQuarter == null) {
			returnMap.put("errorMessage", new DcoiRestMessage(messageSource.getMessage("validateError", null, null)));
			return returnMap;
		}
		dataCenterQuarter.setAdminCompleteFlag(1);
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
		Map<String, Object> returnMap = new HashMap<>();
		QuarterReport quarterReport = quarterReportRepository.findByQuarterActiveFlag(1);
		DataCenterQuarter dataCenterQuarter = dataCenterQuarterRepository
				.findByQuarterReportIdAndDataCenterId(quarterReport.getQuarterId(), dataCenterId);

		if (dataCenterQuarter == null) {
			returnMap.put("errorMessage", new DcoiRestMessage(messageSource.getMessage("rejectError", null, null)));
			return returnMap;
		}
		dataCenterQuarter.setSsoCompleteFlag(0);
		dataCenterQuarter.setAdminCompleteFlag(1);
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
		List<Integer> totalsList = new ArrayList<>();
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

}
