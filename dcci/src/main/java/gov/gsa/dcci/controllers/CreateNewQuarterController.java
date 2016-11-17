package gov.gsa.dcci.controllers;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.gsa.dcci.entity.FiscalQuarterReport;
import gov.gsa.dcci.repository.FiscalQuarterReportRepository;

@RestController
@RequestMapping("/newQuarter")
public class CreateNewQuarterController {
	
	@Autowired
	FiscalQuarterReportRepository createNewQuarterDao;
	
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public Map<String, Object> initQuarter(){

		Map<String, Object> returnData = new HashMap<>();
		returnData.put("fiscalQuarterReport", new FiscalQuarterReport());
		return returnData;
		
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Map<String, Object> createQuarter(FiscalQuarterReport fiscalQuarterReport){
		//createNewQuarterDao.save(fiscalQuarterReport);
		//call DB stored procedure to create new quarter
		return new HashMap<String,Object>();
	}
	

}
