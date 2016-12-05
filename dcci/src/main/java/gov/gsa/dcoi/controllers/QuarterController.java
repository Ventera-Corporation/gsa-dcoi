package gov.gsa.dcoi.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.gsa.dcoi.dto.DataCenterDto;
import gov.gsa.dcoi.dto.QuarterDto;
import gov.gsa.dcoi.entity.QuarterReport;
import gov.gsa.dcoi.service.FieldOfficeService;
import gov.gsa.dcoi.service.QuarterService;

/**
 * Controller for managing Quarter Information.
 *
 */
@RestController
@RequestMapping("/quarter")
public class QuarterController {

	@Autowired
	QuarterService quarterService;

	@Autowired
	FieldOfficeService fieldOfficeService;
	
	/**
	 * Initialize adding a new Quarter
	 * @return
	 */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Map<String, Object> initQuarter() {
		// call DB stored procedure to create new quarter
		// return back fiscalQuarterInformation like quarter and fiscal year
		// as well as return regions/data centers/categories
		Map<String, Object> returnData = new HashMap<>();
		QuarterDto quarterData = quarterService.initQuarter();

		returnData.put("quarterData", quarterData);
		return returnData;

	}

	/**
	 * Method to create a new queater.
	 * @param quarterDto
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public void createQuarter(Date dueDate) {

		QuarterReport fiscalQuarterReportEntity = new QuarterReport();
		//BeanUtils.copyProperties(quarterDto.getFiscalQuarterReport(), fiscalQuarterReportEntity);

		//quarterService.createQuarter(dueDate);
		//update all the flags
		// pass back id for new quarter
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	//Add save method that will save the DataCenterDtos that need to be saved
	public Map<String, Object> save(List<DataCenterDto> dataCenterDtoList){
		//fieldOfficeService.saveDataCenters(quarterDto.getRegions());	
		return new HashMap<String, Object>();
	}

}
