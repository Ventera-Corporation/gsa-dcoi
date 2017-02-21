package gov.gsa.dcoi.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gov.gsa.dcoi.dto.CostCalculationDto;
import gov.gsa.dcoi.dto.FieldOfficeDto;
import gov.gsa.dcoi.dto.OtherCalculationDto;
import gov.gsa.dcoi.dto.ServerInformationDto;
import gov.gsa.dcoi.entity.CostCalculation;
import gov.gsa.dcoi.entity.DataCenterQuarter;
import gov.gsa.dcoi.entity.FieldOffice;
import gov.gsa.dcoi.refValueEntity.GenericReferenceValueObject;
import gov.gsa.dcoi.refValueEntity.ReferenceValueConstants;
import gov.gsa.dcoi.repository.CostCalculationRepository;
import gov.gsa.dcoi.repository.FieldOfficeRepository;
import gov.gsa.dcoi.security.SecurityUtils;

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

	public static final String OCIO_VALUE = "OCIO";

	@Autowired
	FieldOfficeRepository fieldOfficeRepository;

	@Autowired
	CostCalculationRepository costCalcRepository;

	@Autowired
	SecurityUtils securityUtils;

	/**
	 * Save field office value object into database
	 * 
	 * @param fieldOffice
	 * @return
	 */
	@Transactional
	public FieldOffice save(FieldOffice fieldOffice) {
		securityUtils.setUserIdForAudit();
		return fieldOfficeRepository.save(fieldOffice);
	}

	/**
	 * Copy fields from the dto to the value object
	 * 
	 * @param fieldOfficeDto
	 * @param fieldOfficeVO
	 * @return
	 */
	public FieldOffice copyDtoToVO(FieldOfficeDto fieldOfficeDto, FieldOffice fieldOfficeVO) {
		if (fieldOfficeDto.getServerInfo() != null) {
			CommonHelper.modelMapper.map(fieldOfficeDto.getServerInfo(), fieldOfficeVO);
		}
		CommonHelper.modelMapper.map(fieldOfficeDto, fieldOfficeVO);
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
		// Set name, id, etc
		FieldOfficeDto fieldOfficeDto = new FieldOfficeDto();
		CommonHelper.modelMapper.map(fieldOfficeEntity, fieldOfficeDto);

		// Set server info
		ServerInformationDto serverInformationDto = new ServerInformationDto();
		CommonHelper.modelMapper.map(fieldOfficeEntity, serverInformationDto);

		fieldOfficeDto.setServerInfo(serverInformationDto);
		fieldOfficeDto.setFieldOfficeName(CommonHelper.parseComponentId(fieldOfficeEntity.getComponentId()));

		return fieldOfficeDto;
	}

	/**
	 * Copy properties from the data center quarter into the Dto to be used for
	 * the totals tab
	 * 
	 * @param dataCenterQuarter
	 * @return
	 */
	@Transactional(readOnly = true)
	public FieldOfficeDto createTotalsTab(DataCenterQuarter dataCenterQuarter) {
		FieldOfficeDto fieldOfficeDto = new FieldOfficeDto();
		// server info totals
		ServerInformationDto serverInformationDto = new ServerInformationDto();
		CommonHelper.modelMapper.map(dataCenterQuarter, serverInformationDto);
		fieldOfficeDto.setServerInfo(serverInformationDto);

		// cost calc
		List<CostCalculation> costCalcList = costCalcRepository
				.findByDataCenterQuarterId(dataCenterQuarter.getDataCenterQuarterId());
		if (costCalcList != null && !costCalcList.isEmpty()) {
			CostCalculation costCalc = costCalcList.get(costCalcList.size() - 1);
			CostCalculationDto costCalcDto = new CostCalculationDto();
			CommonHelper.modelMapper.map(costCalc, costCalcDto);
			fieldOfficeDto.setCostCalc(costCalcDto);
		} else {
			fieldOfficeDto.setCostCalc(new CostCalculationDto());
		}

		// other calc
		OtherCalculationDto otherCalcDto = new OtherCalculationDto();
		CommonHelper.modelMapper.map(dataCenterQuarter, otherCalcDto);
		fieldOfficeDto.setOtherCalc(otherCalcDto);

		fieldOfficeDto.setOtherCalc(otherCalcDto);

		fieldOfficeDto.setFieldOfficeName("Totals");

		return fieldOfficeDto;
	}

	/**
	 * Populate the fieldOfficeDto Lists to display back for a quarter
	 * 
	 * @param dataCenterQuarterId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<FieldOfficeDto> populateFieldOfficeDtosList(Long dataCenterQuarterId) {
		return populateInformationAboutFieldOffices(
				fieldOfficeRepository.findByDataCenterQuarterId(dataCenterQuarterId));
	}

	/**
	 * Temp method to populate the information about each of the field offices
	 * 
	 * @return
	 */
	private List<FieldOfficeDto> populateInformationAboutFieldOffices(List<FieldOffice> fieldOffices) {
		List<FieldOfficeDto> fieldOfficeDtos = new ArrayList<FieldOfficeDto>();
		List<GenericReferenceValueObject> componentRefValueList = ReferenceValueListService.refValueLists
				.get("componentRefValueList");
		// if there are no field offices create a default OCIO one
		if (CollectionUtils.isNotEmpty(fieldOffices)) {
			for (FieldOffice fieldOffice : fieldOffices) {
				for (GenericReferenceValueObject valueObject : componentRefValueList) {
					if (fieldOffice.getComponentId() == valueObject.getId()) {
						FieldOfficeDto fieldOfficeDto = copyEntityToDto(fieldOffice);
						fieldOfficeDto.setComponentId(valueObject.getId());
						fieldOfficeDto.setFieldOfficeName(valueObject.getValue());
						fieldOfficeDtos.add(fieldOfficeDto);
					}
				}
			}
		} else {
			for (GenericReferenceValueObject valueObject : componentRefValueList) {
				if (OCIO_VALUE.equals(valueObject.getValue())) {
					FieldOfficeDto fieldOfficeDto = new FieldOfficeDto();
					fieldOfficeDto.setComponentId(valueObject.getId());
					fieldOfficeDto.setFieldOfficeName(valueObject.getValue());
					fieldOfficeDtos.add(fieldOfficeDto);
				}
			}
		}
		return fieldOfficeDtos;
	}

	/**
	 * Walk through the field offices for each data center and select the
	 * component name that will be put into the final OMB report
	 * 
	 * @param dataCenterQuarterId
	 * @return
	 */
	public String parseFieldOfficesForOverallComponent(Long dataCenterQuarterId) {
		List<FieldOffice> fieldOffices = fieldOfficeRepository.findByDataCenterQuarterId(dataCenterQuarterId);
		List<GenericReferenceValueObject> componentRefValueList = ReferenceValueListService.refValueLists
				.get("componentRefValueList");
		String componentName = OCIO_VALUE;
		if (CollectionUtils.isNotEmpty(fieldOffices)) {
			for (FieldOffice fieldOffice : fieldOffices) {
				for (GenericReferenceValueObject valueObject : componentRefValueList) {
					if (fieldOffice.getComponentId() == ReferenceValueConstants.OCIO_ID) {
						return OCIO_VALUE;
					} else if (fieldOffice.getComponentId() == valueObject.getId()) {
						componentName = valueObject.getValue();
					}
				}
			}
		} else {
			return OCIO_VALUE;
		}
		return componentName;
	}

}
