package gov.gsa.dcoi.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.gsa.dcoi.dto.DataCenterDto;
import gov.gsa.dcoi.dto.FieldOfficeDto;
import gov.gsa.dcoi.dto.GeneralInformationDto;
import gov.gsa.dcoi.dto.RegionDto;
import gov.gsa.dcoi.dto.StatusDto;
import gov.gsa.dcoi.entity.DataCenter;
import gov.gsa.dcoi.entity.DataCenterQuarter;
import gov.gsa.dcoi.entity.FieldOffice;
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

				dataCenterDto = copyEntityToDto(dataCenterForQuarter, dataCenter, dataCenterDto);

				List<FieldOffice> fieldOffices = fieldOfficeRepository
						.findByDataCenterQuarterId(dataCenterForQuarter.getDataCenterQuarterId());
				for (FieldOffice fieldOffice : fieldOffices) {
					if (fieldOffice.getGrossFloorArea() == null && fieldOffice.getItPowerCapacityTotal() == null) {
						// do nothing - will pass back an empty object

					} else {
						fieldOfficesDto.add(fieldOfficeService.copyEntityToDto(fieldOffice));
					}
				}
				if (!fieldOfficesDto.isEmpty()) {
					dataCenterDto.setFieldOffices(fieldOfficesDto);
				}
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
			BeanUtils.copyProperties(dataCenterDto.getStatusInfo(), dataCenterEntity);
			dataCenterRepository.save(dataCenterEntity);
			dataCenterQuarterRepository.save(copyDtoToEntity(dataCenterDto));
			List<FieldOfficeDto> fieldOffices = dataCenterDto.getFieldOffices();
			for (FieldOfficeDto fieldOfficeDto : fieldOffices) {
				FieldOffice fieldOfficeEntity = new FieldOffice();
				fieldOfficeEntity = fieldOfficeService.copyDtoToVO(fieldOfficeDto, fieldOfficeEntity);
				fieldOfficeRepository.save(fieldOfficeEntity);
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
		for (int i = 0; i < 10; i++) {
			RegionDto regionDto = new RegionDto();
			regionDto.setName("R" + i);
			regionDto.setCode("r" + i);
			regionDto.setRegionId(i);
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

		GeneralInformationDto generalInformationDto = new GeneralInformationDto();
		StatusDto statusDto = new StatusDto();

		BeanUtils.copyProperties(dataCenterEntity, generalInformationDto);
		generalInformationDto.setPublishedName(dataCenterQuarterEntity.getPublishedName());

		BeanUtils.copyProperties(dataCenterQuarterEntity, statusDto);
		dataCenterDto.setGeneralInfo(generalInformationDto);
		dataCenterDto.setStatusInfo(statusDto);
		return dataCenterDto;
	}

	/**
	 * Copy the dto information to the dataCenterQuarterEntity
	 * 
	 * @param dataCenterDto
	 * @return
	 */
	private DataCenterQuarter copyDtoToEntity(DataCenterDto dataCenterDto) {
		DataCenterQuarter dataCenterQuarter = new DataCenterQuarter();
		BeanUtils.copyProperties(dataCenterDto.getGeneralInfo(), dataCenterQuarter);
		BeanUtils.copyProperties(dataCenterDto.getStatusInfo(), dataCenterQuarter);

		return dataCenterQuarter;
	}

}
