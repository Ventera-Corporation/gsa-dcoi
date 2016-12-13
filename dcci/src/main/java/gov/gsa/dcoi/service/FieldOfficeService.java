package gov.gsa.dcoi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.gsa.dcoi.dto.FacilityInformationDto;
import gov.gsa.dcoi.dto.FieldOfficeDto;
import gov.gsa.dcoi.dto.ServerInformationDto;
import gov.gsa.dcoi.entity.FieldOffice;
import gov.gsa.dcoi.repository.FieldOfficeRepository;

/**
 * Contains methods that implement functionality for specific field offices
 * including copying/persisting information between the value object and the
 * Dtos as well as saving the field office info in to the database
 * 
 * @author sgonthier
 *
 */
@Component
public class FieldOfficeService {

	@Autowired
	FieldOfficeRepository fieldOfficeRepository;

	/**
	 * Copy fields from the dto to the value object
	 * 
	 * @param fieldOfficeDto
	 * @param fieldOfficeVO
	 * @return
	 */
	public FieldOffice copyDtoToVO(FieldOfficeDto fieldOfficeDto, FieldOffice fieldOfficeVO) {
		BeanUtils.copyProperties(fieldOfficeDto.getFacilityInfo(), fieldOfficeVO);
		BeanUtils.copyProperties(fieldOfficeDto.getServerInfo(), fieldOfficeVO);
		return fieldOfficeVO;
	}

	/**
	 * Copy properties from the data center quarter (ONE day will be data center
	 * inventory) into the Dto to be used on the front-end
	 * 
	 * @param fieldOfficeEntity
	 * @param fieldOfficeDto
	 * @return
	 */
	public FieldOfficeDto copyEntityToDto(FieldOffice fieldOfficeEntity) {
		FieldOfficeDto fieldOfficeDto = new FieldOfficeDto();
		FacilityInformationDto facilityInformationDto = new FacilityInformationDto();
		ServerInformationDto serverInformationDto = new ServerInformationDto();

		BeanUtils.copyProperties(fieldOfficeEntity, facilityInformationDto);
		BeanUtils.copyProperties(fieldOfficeEntity, serverInformationDto);

		fieldOfficeDto.setFacilityInfo(facilityInformationDto);
		fieldOfficeDto.setServerInfo(serverInformationDto);
		fieldOfficeDto.setFieldOfficeName(CommonHelper.parseComponentId(fieldOfficeEntity.getComponentId()));

		return fieldOfficeDto;

	}

}
