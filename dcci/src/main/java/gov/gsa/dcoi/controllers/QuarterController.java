package gov.gsa.dcoi.controllers;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.gsa.dcoi.dto.QuarterDto;
import gov.gsa.dcoi.entity.DataCenter;
import gov.gsa.dcoi.entity.FiscalQuarterReport;
import gov.gsa.dcoi.manager.FieldOfficeManager;
import gov.gsa.dcoi.repository.DataCenterRepository;
import gov.gsa.dcoi.repository.FieldOfficeRepository;
import gov.gsa.dcoi.repository.FiscalQuarterReportRepository;

@RestController
@RequestMapping("/quarter")
public class QuarterController {
	
	@Autowired
	FiscalQuarterReportRepository createNewQuarterDao;
	
	@Autowired 
	DataCenterRepository dataCenterDao;
	
	@Autowired 
	FieldOfficeRepository dataCenterInventoryDao;
	
	@Autowired 
	FieldOfficeManager fieldOfficeManager;
	
	
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public Map<String, Object> initQuarter(){
		//call DB stored procedure to create new quarter
		//return back fiscalQuarterInformation like quarter and fiscal year
		//as well as return regions/data centers/categories
		Map<String, Object> returnData = new HashMap<>();
		
		returnData.put("quarterData", new QuarterDto());
		return returnData;
		
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Map<String, Object> createQuarter(QuarterDto quarterDto){
		
		FiscalQuarterReport fiscalQuarterReportEntity = new FiscalQuarterReport();
		BeanUtils.copyProperties(quarterDto.getFiscalQuarterReport(), fiscalQuarterReportEntity);
		//BeanUtils.copyProperties(quarterDto.getRegions(), target);
		createNewQuarterDao.save(fiscalQuarterReportEntity);
		
		fieldOfficeManager.saveDataCenters(quarterDto.getRegions());
		//also do notification in this function
		//pass back id for new quarter	
		return new HashMap<String,Object>();
	}
	

	

}
