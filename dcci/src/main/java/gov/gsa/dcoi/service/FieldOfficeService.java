package gov.gsa.dcoi.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.gsa.dcoi.dto.CostCalculationDto;
import gov.gsa.dcoi.dto.FacilityInformationDto;
import gov.gsa.dcoi.dto.FieldOfficeDto;
import gov.gsa.dcoi.dto.ServerInformationDto;
import gov.gsa.dcoi.entity.CostCalculation;
import gov.gsa.dcoi.entity.DataCenterQuarter;
import gov.gsa.dcoi.entity.FieldOffice;
import gov.gsa.dcoi.repository.CostCalculationRepository;
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

	@Autowired
	CostCalculationRepository costCalcRepository;

	/**
	 * Save field office value object into database
	 * 
	 * @param fieldOffice
	 * @return
	 */
	public FieldOffice save(FieldOffice fieldOffice) {
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
		if (fieldOfficeDto.getFacilityInfo() != null) {
			BeanUtils.copyProperties(fieldOfficeDto.getFacilityInfo(), fieldOfficeVO);
		}
		if (fieldOfficeDto.getServerInfo() != null) {
			BeanUtils.copyProperties(fieldOfficeDto.getServerInfo(), fieldOfficeVO);
		}
		BeanUtils.copyProperties(fieldOfficeDto, fieldOfficeVO);
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

		BeanUtils.copyProperties(fieldOfficeEntity, fieldOfficeDto);

		fieldOfficeDto.setFacilityInfo(facilityInformationDto);
		fieldOfficeDto.setServerInfo(serverInformationDto);
		fieldOfficeDto.setFieldOfficeName(CommonHelper.parseComponentId(fieldOfficeEntity.getComponentId()));

		return fieldOfficeDto;
	}

	/**
	 * Copy properties from the data center quarter into the Dto to be used for
	 * the totals tab
	 * 
	 * @param fieldOfficeEntity
	 * @param fieldOfficeDto
	 * @return
	 */
	public FieldOfficeDto createTotalsTab(DataCenterQuarter dataCenterQuarter) {
		FieldOfficeDto fieldOfficeDto = new FieldOfficeDto();
		FacilityInformationDto facilityInformationDto = new FacilityInformationDto();
		ServerInformationDto serverInformationDto = new ServerInformationDto();

		BeanUtils.copyProperties(dataCenterQuarter, facilityInformationDto);
		BeanUtils.copyProperties(dataCenterQuarter, serverInformationDto);

		// BeanUtils.copyProperties(fieldOfficeEntity, fieldOfficeDto);

		fieldOfficeDto.setFacilityInfo(facilityInformationDto);
		fieldOfficeDto.setServerInfo(serverInformationDto);
		List<CostCalculation> costCalcList = costCalcRepository
				.findByDataCenterQuarterId(dataCenterQuarter.getDataCenterQuarterId());
		if (costCalcList != null && !costCalcList.isEmpty()) {
			CostCalculation costCalc = costCalcList.get(costCalcList.size() - 1);
			CostCalculationDto costCalcDto = new CostCalculationDto();
			BeanUtils.copyProperties(costCalc, costCalcDto);
			fieldOfficeDto.setCostCalc(costCalcDto);
		} else {
			fieldOfficeDto.setCostCalc(new CostCalculationDto());
		}
		fieldOfficeDto.setFieldOfficeName("Totals");

		return fieldOfficeDto;
	}

}
