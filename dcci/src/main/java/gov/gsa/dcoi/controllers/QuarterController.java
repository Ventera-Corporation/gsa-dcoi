
package gov.gsa.dcoi.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.gsa.dcoi.DcoiRestMessage;
import gov.gsa.dcoi.dto.DataCenterDto;
import gov.gsa.dcoi.dto.FiscalQuarterReportDto;
import gov.gsa.dcoi.dto.QuarterDto;
import gov.gsa.dcoi.dto.ValidList;
import gov.gsa.dcoi.entity.DataCenterView;
import gov.gsa.dcoi.entity.QuarterReport;
import gov.gsa.dcoi.service.DataCenterService;
import gov.gsa.dcoi.service.ExcelWriter;
import gov.gsa.dcoi.service.FieldOfficeService;
import gov.gsa.dcoi.service.QuarterService;
import gov.gsa.dcoi.service.ReferenceValueListService;

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

	@Autowired
	ExcelWriter excelService;
	
	@Autowired
	MessageSource messageSource;

	/**
	 * Initialize adding a new Quarter
	 * 
	 * @return
	 */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
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
		returnData.put("referenceValueLists", ReferenceValueListService.refValueLists);
		return returnData;

	}

	/**
	 * Method to view read-only information about a data center
	 * 
	 * @param quarterId
	 * @return
	 */
	@RequestMapping(value = "view", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Map<String, Object> viewQuarter(Long quarterId) {
		Map<String, Object> returnMap = new HashMap<>();
		QuarterDto quarterDto = quarterService.viewQuarter(quarterId);
		returnMap.put("quarterData", quarterDto);
		returnMap.put("referenceValueLists", ReferenceValueListService.refValueLists);
		return returnMap;

	}

	/**
	 * Method to create a new quarter.
	 * 
	 * @param dueDate
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Map<String, Object> createQuarter(Date dueDate) {
		// Should only do validation on the due date
		Map<String, Object> returnMap;
		returnMap = quarterService.createQuarter(dueDate);
		return returnMap;

	}

	/**
	 * Save Quarter information
	 * 
	 * @param dataCenterDtos
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Map<String, Object> save(@Valid @RequestBody ValidList<DataCenterDto> dataCenterDtos) {

		Map<String, Object> returnMap = new HashMap<>();
		//Add Admin Check
		returnMap.put("dataCenterIdTotalsPairs", quarterService.costCalculation(dataCenterDtos.getList()));
		dataCenterService.saveDataCenters(dataCenterDtos.getList());
		return returnMap;

	}
	
	/**
	 * Save Quarter information
	 * 
	 * @param dataCenterDtoList
	 * @return
	 */
	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public Map<String, Object> complete() {
		Map<String, Object> returnMap;
		returnMap = quarterService.completeQuarter();
		if(returnMap.get("errorMessage") != null){
			return returnMap;
		}
		returnMap.put("successsMessage", new DcoiRestMessage(messageSource.getMessage("completeQuarterSuccess", null, null)));
		return returnMap;

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

	/**
	 * Perform the query for data center view results and format those results
	 * into an excel workbook for the user to download
	 * 
	 * @param quarterId
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public byte[] exportSearchResults(@RequestBody Long quarterId) {

		String[] sheetTitles = { "Quarter Report" };
		return excelService.exportReportResults(sheetTitles,
				buildResultsForExport(findViewResultsByQuarterId(quarterId)));
	}

	/**
	 * Get the data center view results that are necessary for the final quarter
	 * report
	 * 
	 * @param quarterId
	 * @return
	 */
	private List<DataCenterView> findViewResultsByQuarterId(Long quarterId) {
		return quarterService.findViewResultsByQuarterId(quarterId);
	}

	/**
	 * Build the map that is necessary to turn the search results into data
	 * points that are associated with a header and can be displayed in excel
	 * 
	 * @param searchResults
	 * @return
	 */
	private Map<String[], List<List<String>>> buildResultsForExport(List<DataCenterView> searchResults) {
		Map<String[], List<List<String>>> searchResultsMap = new LinkedHashMap<>();
		List<List<String>> dataCenterViewSearchResults = new LinkedList<>();
		for (DataCenterView searchResultVO : searchResults) {
			List<String> dataCenterViewSearchResult = new LinkedList<>();
			dataCenterViewSearchResult.add(searchResultVO.getDataCenterName());
			dataCenterViewSearchResult.add(searchResultVO.getDcoiDataCenterId());
			dataCenterViewSearchResult.add(searchResultVO.getStreetAddress());
			dataCenterViewSearchResult.add(searchResultVO.getStreetAddress2());
			dataCenterViewSearchResult.add(searchResultVO.getCity());
			dataCenterViewSearchResult.add(searchResultVO.getZipCode());
			dataCenterViewSearchResult.add(searchResultVO.getStateName());
			dataCenterViewSearchResult.add(searchResultVO.getCountryName());
			dataCenterViewSearchResult.add(searchResultVO.getAgencyDataCenterName());
			dataCenterViewSearchResult.add(searchResultVO.getPublishedName());
			dataCenterViewSearchResult.add(searchResultVO.getRecordStatusName());
			dataCenterViewSearchResult.add(searchResultVO.getRecordValidityName());
			dataCenterViewSearchResult.add(searchResultVO.getOwnershipTypeName());
			dataCenterViewSearchResult.add(searchResultVO.getDataCenterTierName());
			dataCenterViewSearchResult.add(searchResultVO.getGrossFloorArea().toString());
			dataCenterViewSearchResult.add(searchResultVO.getTotalCustomerFloorArea().toString());
			dataCenterViewSearchResult.add(searchResultVO.getAnnualCostPerSqFt().toString());
			dataCenterViewSearchResult.add(searchResultVO.getOtherAgenciesServiced());
			dataCenterViewSearchResult.add(searchResultVO.getElectricityIncludedInCost().toString());
			dataCenterViewSearchResult.add(searchResultVO.getElectricityIsMetered().toString());
			dataCenterViewSearchResult.add(searchResultVO.getTotalPowerCapacity().toString());
			dataCenterViewSearchResult.add(searchResultVO.getTotalITPowerCapacity().toString());
			dataCenterViewSearchResult.add(searchResultVO.getAvgElectricityUsage().toString());
			dataCenterViewSearchResult.add(searchResultVO.getAvgITElectricityUsage().toString());
			dataCenterViewSearchResult.add(searchResultVO.getCostPerkWh().toString());
			dataCenterViewSearchResult.add(searchResultVO.getAutomatedMonitoring().toString());
			dataCenterViewSearchResult.add(searchResultVO.getServerUtilization().toString());
			dataCenterViewSearchResult.add(searchResultVO.getFte().toString());
			dataCenterViewSearchResult.add(searchResultVO.getFteCost().toString());
			dataCenterViewSearchResult.add(searchResultVO.getRackCount().toString());
			dataCenterViewSearchResult.add(searchResultVO.getTotalMainframes().toString());
			dataCenterViewSearchResult.add(searchResultVO.getTotalWindowsServers().toString());
			dataCenterViewSearchResult.add(searchResultVO.getTotalHPCClusterNodes().toString());
			dataCenterViewSearchResult.add(searchResultVO.getTotalOtherServers().toString());
			dataCenterViewSearchResult.add(searchResultVO.getTotalVirtualHosts().toString());
			dataCenterViewSearchResult.add(searchResultVO.getTotalVirtualOS().toString());
			dataCenterViewSearchResult.add(searchResultVO.getTotalStorage().toString());
			dataCenterViewSearchResult.add(searchResultVO.getUsedStorage().toString());
			dataCenterViewSearchResult.add(searchResultVO.getCoreClassificationName());
			dataCenterViewSearchResult.add(searchResultVO.getClosingStageName());
			dataCenterViewSearchResult.add(searchResultVO.getFiscalYear().toString());
			dataCenterViewSearchResult.add(searchResultVO.getFiscalQuarter());
			dataCenterViewSearchResult.add(searchResultVO.getIssPositionName());
			dataCenterViewSearchResult.add(searchResultVO.getIssProvider());
			dataCenterViewSearchResults.add(dataCenterViewSearchResult);
		}

		String[] exportColumnNames = { "DATA CENTER NAME", "DATA CENTER ID",
				"STREET ADDRESS", "STREET ADDRESS 2", "CITY", "ZIPCODE", "STATE NAME", "COUNTRY NAME",
				"AGENCY DATA CENTER NAME", "PUBLISHED NAME", "RECORD STATUS NAME", "RECORD VALIDITY ID", "OWNERSHIP TYPE NAME",
				"DATA CENER TIER NAME", "GROSS FLOOR AREA", "TOTAL CUSTOMER FLOOR AREA", "ANNUAL COST PER SQ FT", "OTHER AGENCIES SERVICED",
				"ELECTRICTY INCLUDED IN COST", "ELECTRICTY IS METERED", "TOTAL POWER CAPACITY", "TOTAL IT POWER CAPACITY", "AVG ELECTRICITY USAGE",
				"AVG IT ELECTRICITY USAGE", "COST PER KWH", "AUTOMATED MONITORING", "SERVER UTILIZATION", "FTE", "FTE COST", "RACK COUNT",
				"TOTAL MAINFRAMES", "TOTAL WINDOWS SERVERS", "TOTAL HPC CLUSTER NODES", "TOTAL OTHER SERVERS", "TOTAL VIRTUAL HOSTS","TOTAL VIRTUAL OS",
				"TOTAL STORAGE", "USED STORAGE", "CORE CLASSIFICATION NAME", "CLOSING STAGE NAME", "FISCAL YEAR", "FISCAL QUARTER", "ISS POSITION NAME",
				"ISS PROVIDER"};
		searchResultsMap.put(exportColumnNames, dataCenterViewSearchResults);
		return searchResultsMap;
	}
}

