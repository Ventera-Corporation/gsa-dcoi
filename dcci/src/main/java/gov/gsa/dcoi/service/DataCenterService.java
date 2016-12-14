package gov.gsa.dcoi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.lang.Iterable;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import gov.gsa.dcoi.dto.DataCenterDto;
import gov.gsa.dcoi.dto.FieldOfficeDto;
import gov.gsa.dcoi.dto.GeneralInformationDto;
import gov.gsa.dcoi.dto.RegionDto;
import gov.gsa.dcoi.dto.StatusDto;
import gov.gsa.dcoi.entity.DataCenter;
import gov.gsa.dcoi.entity.DataCenterQuarter;
import gov.gsa.dcoi.entity.FieldOffice;
import gov.gsa.dcoi.refValueEntity.GenericReferenceValueObject;
import gov.gsa.dcoi.repository.DataCenterQuarterRepository;
import gov.gsa.dcoi.repository.DataCenterRepository;
import gov.gsa.dcoi.repository.FieldOfficeRepository;

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
		while(allQuarters.hasNext()){
			DataCenterDto dataCenterDto = new DataCenterDto();
			DataCenterQuarter quarter = allQuarters.next();
			List<DataCenter> dataCenters = dataCenterRepository.findByDataCenterId(quarter.getDataCenterId());
			returnDataCenters.add(copyEntityToDto(quarter, dataCenters.get(0), dataCenterDto));
		}
		return returnDataCenters;
	}

		/**
	 * Populate the regionsDto Lists to display back for a quarter
	 * 
	 * @param quarterReportId
	 * @return
	 */
	@Transactional
	public List<RegionDto> populateRegionDtosList(Long quarterReportId) {
		List<RegionDto> regionDtos = populateInformationAboutRegions();
		for (RegionDto region : regionDtos) {
			List<DataCenterDto> dataCenterDtos = new ArrayList<>();
			List<DataCenter> dataCenterStaticInfo = dataCenterRepository.findByRegionId(region.getRegionId());
			for (DataCenter dataCenter : dataCenterStaticInfo) {
				DataCenterDto dataCenterDto = new DataCenterDto();
				List<FieldOfficeDto> fieldOfficesDto = new ArrayList<>();

				BeanUtils.copyProperties(dataCenter, dataCenterDto);
				DataCenterQuarter dataCenterForQuarter = dataCenterQuarterRepository
						.findByQuarterReportIdAndDataCenterId(quarterReportId, dataCenter.getDataCenterId());
				if (dataCenterForQuarter == null) {
					// This data center has historical information, but no
					// information for the current quarter
					continue;
				}
				
				//Set the totals tab
				dataCenterDto.setTotals(fieldOfficeService.createTotalsTab(dataCenterForQuarter));

				List<FieldOffice> fieldOffices = fieldOfficeRepository
						.findByDataCenterQuarterId(dataCenterForQuarter.getDataCenterQuarterId());
				for (FieldOffice fieldOffice : fieldOffices) {
					if (fieldOffice.getGrossFloorArea() == null && fieldOffice.getTotalITPowerCapacity() == null) {
						// Pass back a sparse fieldOfficeDto
						FieldOfficeDto fieldOfficeDto = new FieldOfficeDto();
						fieldOfficeDto.setDataCenterInventoryId(fieldOffice.getDataCenterQuarterId());
						fieldOfficeDto.setComponentId(fieldOffice.getComponentId());
						fieldOfficeDto.setDataCenterQuarterId(fieldOffice.getDataCenterQuarterId());
						fieldOfficesDto.add(fieldOfficeDto);

					} else {
						fieldOfficesDto.add(fieldOfficeService.copyEntityToDto(fieldOffice));
						// dataCenterDto.getGeneralInfo().setComponentId(fieldOffice.getComponentId());
					}
				}
				dataCenterDto.setFieldOffices(fieldOfficesDto);
				dataCenterDtos.add(dataCenterDto);
			}
			region.setDataCenters(dataCenterDtos);
		}
		return regionDtos;
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
			dataCenterRepository.save(dataCenterEntity);

			List<FieldOfficeDto> fieldOffices = dataCenterDto.getFieldOffices();
			for (FieldOfficeDto fieldOfficeDto : fieldOffices) {

				// Add check for totals tab
				if (!"Total".equals(fieldOfficeDto.getFieldOfficeName())) {
					dataCenterQuarterRepository.save(copyDtoToEntity(dataCenterDto, fieldOfficeDto));
					FieldOffice fieldOfficeEntity = new FieldOffice();
					fieldOfficeEntity = fieldOfficeService.copyDtoToVO(fieldOfficeDto, fieldOfficeEntity);
					fieldOfficeRepository.save(fieldOfficeEntity);
				}
				// if (fieldOfficeDto != null) {

				// }
			}

		}

	}

	/**
	 * Temp method to populate the information about each of the regions
	 * 
	 * @return
	 */
	private List<RegionDto> populateInformationAboutRegions() {
		List<RegionDto> regionDtos = new ArrayList<>();
		for (GenericReferenceValueObject valueObject : ReferenceValueListService.refValueLists
				.get("regionRefValueList")) {
			RegionDto regionDto = new RegionDto();
			regionDto.setName(valueObject.getValue());
			regionDto.setCode(valueObject.getValue().toLowerCase().replace(" ", ""));
			regionDto.setRegionId(valueObject.getId());
			regionDtos.add(regionDto);
		}
		return regionDtos;
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
	private DataCenterDto copyEntityToDto(DataCenterQuarter dataCenterQuarterEntity, DataCenter dataCenterEntity,
			DataCenterDto dataCenterDto) {

		// General Information
		BeanUtils.copyProperties(dataCenterEntity, dataCenterDto);
		GeneralInformationDto generalInformationDto = new GeneralInformationDto();
		BeanUtils.copyProperties(dataCenterEntity, generalInformationDto);

		generalInformationDto.setPublishedName(dataCenterQuarterEntity.getPublishedName());
		dataCenterDto.setGeneralInfo(generalInformationDto);
		// Status
		StatusDto statusDto = new StatusDto();
		BeanUtils.copyProperties(statusDto, dataCenterQuarterEntity);
		dataCenterDto.setStatus(statusDto);

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

		// Set Field Office Info
		if (fieldOfficeDto != null) {
			BeanUtils.copyProperties(fieldOfficeDto, dataCenterQuarter);
			if (fieldOfficeDto.getFacilityInfo() != null) {
				BeanUtils.copyProperties(fieldOfficeDto.getFacilityInfo(), dataCenterQuarter);
			}
			if (fieldOfficeDto.getServerInfo() != null) {
				BeanUtils.copyProperties(fieldOfficeDto.getServerInfo(), dataCenterQuarter);
			}
		}

		return dataCenterQuarter;
	}

}
