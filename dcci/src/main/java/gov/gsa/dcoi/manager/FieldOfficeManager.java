package gov.gsa.dcoi.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.gsa.dcoi.dto.DataCenterDto;
import gov.gsa.dcoi.dto.FieldOfficeDto;
import gov.gsa.dcoi.dto.RegionDto;
import gov.gsa.dcoi.entity.FieldOffice;
import gov.gsa.dcoi.repository.FieldOfficeRepository;

@Component
public class FieldOfficeManager {
	
	@Autowired 
	FieldOfficeRepository fieldOfficeDao;
	
	public Map<String, Object> saveDataCenters(List<RegionDto> regions){
		Map<String, Object> returnData = new HashMap<>();
		for(RegionDto region : regions){
			List<DataCenterDto> dataCenters = region.getDataCenters();
			for(DataCenterDto dataCenter : dataCenters){
				List<FieldOfficeDto> fieldOffices = dataCenter.getFieldOffices();
				for(FieldOfficeDto fieldOfficeDto : fieldOffices){
					FieldOffice fieldOfficeVO = new FieldOffice();
					fieldOfficeVO = copyDtoToVO(fieldOfficeDto, fieldOfficeVO);
					fieldOfficeDao.save(fieldOfficeVO);
				}
			}
		}
		
		
		return returnData;
		
		
	}
	
	
	private FieldOffice copyDtoToVO(FieldOfficeDto fieldOfficeDto, FieldOffice fieldOfficeVO){
		BeanUtils.copyProperties(fieldOfficeDto.getFacilityInformation(), fieldOfficeVO);
		BeanUtils.copyProperties(fieldOfficeDto.getGeneralInformation(), fieldOfficeVO);
		BeanUtils.copyProperties(fieldOfficeDto.getServerInformation(), fieldOfficeVO);
		BeanUtils.copyProperties(fieldOfficeDto.getStatus(), fieldOfficeVO);
		return fieldOfficeVO;
	}

}
