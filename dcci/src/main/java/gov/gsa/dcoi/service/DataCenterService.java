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
		List<DataCenterDto> dataCenterDtos = new ArrayList<DataCenterDto>();
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
				dataCenterQuarterRepository.save(copyDtoToEntity(dataCenterDto, fieldOfficeDto));
				fieldOfficeRepository.save(fieldOfficeService.copyDtoToVO(fieldOfficeDto, new FieldOffice()));
			}
		}
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
		for(FieldOfficeDto fieldOfficeDto : dataCenterDto.getFieldOffices()){
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
		if(StringUtils.isEmpty(generalInformationDto.getAgencyAbbreviation())){
			generalInformationDto.setAgencyAbbreviation("GSA");
		}
		
		// set the field offices and the component field in general info
		List<FieldOfficeDto> fieldOfficeDtos = 
				fieldOfficeService.populateFieldOfficeDtosList(dataCenterQuarterEntity.getDataCenterQuarterId());
		dataCenterDto.setFieldOffices(fieldOfficeDtos);
		if(StringUtils.isEmpty(generalInformationDto.getComponent())){
			//if there is an OCIO field office set that as the component
			for(FieldOfficeDto fieldOfficeDto : fieldOfficeDtos){
				if("OCIO".equals(fieldOfficeDto.getFieldOfficeName())){
					generalInformationDto.setComponent(fieldOfficeDto.getFieldOfficeName());
				}
			}
			//if there is no OCIO field office, set the first one as the component
			if(StringUtils.isEmpty(generalInformationDto.getComponent())){
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

}
