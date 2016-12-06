package gov.gsa.dcoi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.gsa.dcoi.dto.DataCenterDto;
import gov.gsa.dcoi.dto.FacilityInformationDto;
import gov.gsa.dcoi.dto.FieldOfficeDto;
import gov.gsa.dcoi.dto.GeneralInformationDto;
import gov.gsa.dcoi.dto.RegionDto;
import gov.gsa.dcoi.dto.ServerInformationDto;
import gov.gsa.dcoi.dto.StatusDto;
import gov.gsa.dcoi.entity.DataCenterQuarter;
import gov.gsa.dcoi.entity.FieldOffice;
import gov.gsa.dcoi.repository.FieldOfficeRepository;

/**
 * Contains methods that implement functionality for specific field offices
 * including copying/persisting information between the value object and the Dtos
 * as well as saving the field office info in to the database
 * @author sgonthier
 *
 */
@Component
public class FieldOfficeService {

	@Autowired
	FieldOfficeRepository fieldOfficeDao;

	/**
	 * Will save the edited information for data centers that are passed back 
	 * after a "save changes" call from the application
	 * @param regions
	 * @return
	 */
	@Transactional
	public Map<String, Object> saveDataCenters(List<RegionDto> regions) {
		Map<String, Object> returnData = new HashMap<>();
		for (RegionDto region : regions) {
			List<DataCenterDto> dataCenters = region.getDataCenters();
			for (DataCenterDto dataCenter : dataCenters) {
				List<FieldOfficeDto> fieldOffices = dataCenter.getFieldOffices();
				for (FieldOfficeDto fieldOfficeDto : fieldOffices) {
					FieldOffice fieldOfficeVO = new FieldOffice();
					fieldOfficeVO = copyDtoToVO(fieldOfficeDto, fieldOfficeVO);
					fieldOfficeDao.save(fieldOfficeVO);
				}
			}
		}

		return returnData;

	}

	/**
	 * Copy fields from the dto to the value object
	 * @param fieldOfficeDto
	 * @param fieldOfficeVO
	 * @return
	 */
	private FieldOffice copyDtoToVO(FieldOfficeDto fieldOfficeDto, FieldOffice fieldOfficeVO) {
		BeanUtils.copyProperties(fieldOfficeDto.getFacilityInfo(), fieldOfficeVO);
		BeanUtils.copyProperties(fieldOfficeDto.getServerInfo(), fieldOfficeVO);
		return fieldOfficeVO;
	}

	/**
	 * Copy properties from the data center quarter (ONE day will be data center
	 * inventory) into the Dto to be used on the front-end
	 * 
	 * @param dataCenterQuarter
	 * @param fieldOfficeDto
	 * @return
	 */
	public FieldOfficeDto copyEntityToDto(DataCenterQuarter dataCenterQuarter) {
		FieldOfficeDto fieldOffice = new FieldOfficeDto();
		GeneralInformationDto generalInformationDto = new GeneralInformationDto();
		FacilityInformationDto facilityInformationDto = new FacilityInformationDto();
		ServerInformationDto serverInformationDto = new ServerInformationDto();
		StatusDto statusDto = new StatusDto();

		BeanUtils.copyProperties(dataCenterQuarter, generalInformationDto);
		BeanUtils.copyProperties(dataCenterQuarter, facilityInformationDto);
		BeanUtils.copyProperties(dataCenterQuarter, serverInformationDto);
		BeanUtils.copyProperties(dataCenterQuarter, statusDto);

		fieldOffice.setFacilityInfo(facilityInformationDto);
		fieldOffice.setServerInfo(serverInformationDto);
		fieldOffice.setFieldOfficeName("PBS");

		return fieldOffice;

	}

}
