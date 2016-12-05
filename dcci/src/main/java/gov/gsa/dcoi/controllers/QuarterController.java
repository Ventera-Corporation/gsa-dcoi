package gov.gsa.dcoi.controllers;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.gsa.dcoi.dto.QuarterDto;
import gov.gsa.dcoi.entity.QuarterReport;
import gov.gsa.dcoi.service.FieldOfficeService;
import gov.gsa.dcoi.service.QuarterService;

@RestController
@RequestMapping("/quarter")
public class QuarterController {
	
	@Autowired 
	QuarterService quarterService;
	
	@Autowired 
	FieldOfficeService fieldOfficeService;
	
	
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Map<String, Object> initQuarter(){
		//call DB stored procedure to create new quarter
		//return back fiscalQuarterInformation like quarter and fiscal year
		//as well as return regions/data centers/categories
		Map<String, Object> returnData = new HashMap<>();
		//returnData.put("here", "infunction");
		QuarterDto quarterData = quarterService.initQuarter();
		
		returnData.put("quarterData", quarterData );
		return returnData;
		
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public Map<String, Object> createQuarter(QuarterDto quarterDto){
		
		QuarterReport fiscalQuarterReportEntity = new QuarterReport();
		BeanUtils.copyProperties(quarterDto.getFiscalQuarterReport(), fiscalQuarterReportEntity);

		quarterService.createQuarter(fiscalQuarterReportEntity);
		fieldOfficeService.saveDataCenters(quarterDto.getRegions());
		//also do notification in this function
		//pass back id for new quarter	
		return new HashMap<String,Object>();
	}
	

	

}
