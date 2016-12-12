package gov.gsa.dcoi.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.gsa.dcoi.dto.DataCenterDto;
import gov.gsa.dcoi.dto.FiscalQuarterReportDto;
import gov.gsa.dcoi.dto.QuarterDto;
import gov.gsa.dcoi.entity.QuarterReport;
import gov.gsa.dcoi.service.DataCenterService;
import gov.gsa.dcoi.service.FieldOfficeService;
import gov.gsa.dcoi.service.QuarterService;

/**
 * Controller for managing Quarter Information.
 */
@RestController
@RequestMapping("/quarter")
public class QuarterController {

	@Autowired
	QuarterService quarterService;

	@Autowired
	FieldOfficeService fieldOfficeService;

	@Autowired
	DataCenterService dataCenterService;

	/**
	 * Initialize adding a new Quarter
	 * 
	 * @return
	 */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public Map<String, Object> initQuarter() {
		// call DB stored procedure to create new quarter
		// return back fiscalQuarterInformation like quarter and fiscal year
		// as well as return regions/data centers/categories
		Map<String, Object> returnData = new HashMap<>();
		if (quarterService.findQuarterByActiveFlag()) {
			returnData.put("warningMessage", "This action will make the currently active quarter inactive");
		} else {
			QuarterDto newQuarter = new QuarterDto();
			returnData = quarterService.initQuarter();
			QuarterReport quarterReport = (QuarterReport) returnData.get("quarterReport");
			if (quarterReport != null) {
				newQuarter.setFiscalQuarterReport(populateFiscalQuarterReportDto(quarterReport));
				newQuarter.setRegions(dataCenterService.populateRegionDtosList(quarterReport.getQuarterId()));
			}

			returnData.put("quarterData", newQuarter);
		}
		return returnData;

	}

	/**
	 * Method to view read-only information about a data center
	 * 
	 * @param quarterId
	 * @return
	 */
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public Map<String, Object> viewQuarter(Long quarterId) {
		Map<String, Object> returnMap = new HashMap<>();
		QuarterDto quarterDto = quarterService.viewQuarter(quarterId);
		returnMap.put("quarterData", quarterDto);
		return returnMap;

	}

	/**
	 * Method to create a new quarter.
	 * 
	 * @param dueDate
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public Map<String, Object> createQuarter(Date dueDate) {
		// Should only do validation on the due date
		Map<String, Object> returnMap = new HashMap<>();
		returnMap = quarterService.createQuarter(dueDate);
		return returnMap;

	}

	/**
	 * Save Quarter information
	 * 
	 * @param dataCenterDtoList
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	// Add save method that will save the DataCenterDtos that need to be saved
	public void save(List<DataCenterDto> dataCenterDtoList) {
		dataCenterService.saveDataCenters(dataCenterDtoList);

	}

	/**
	 * Populate the fiscal quarter report dto for use on the front end from the
	 * quarter report information we received
	 * 
	 * @param quarterReport
	 * @return
	 */
	private FiscalQuarterReportDto populateFiscalQuarterReportDto(QuarterReport quarterReport) {
		FiscalQuarterReportDto quarterReportDto = new FiscalQuarterReportDto();
		BeanUtils.copyProperties(quarterReport, quarterReportDto);
		return quarterReportDto;
	}
}
