
package gov.gsa.dcoi.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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
import gov.gsa.dcoi.service.CommonHelper;
import gov.gsa.dcoi.service.DataCenterService;
import gov.gsa.dcoi.service.DcoiCSVWriter;
import gov.gsa.dcoi.service.FieldOfficeService;
import gov.gsa.dcoi.service.QuarterService;
import gov.gsa.dcoi.service.ReferenceValueListService;

/**
 * Controller for managing Quarter Information.
 */
@Controller
@RestController
@RequestMapping("/quarter")
public class QuarterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuarterController.class);

	@Autowired
	QuarterService quarterService;

	@Autowired
	FieldOfficeService fieldOfficeService;

	@Autowired
	DataCenterService dataCenterService;

	@Autowired
	DcoiCSVWriter csvService;

	@Autowired
	SecurityController securityController;

	@Autowired
	MessageSource messageSource;

	private static final String SUCCESS_DATA = "successData";
	private static final String ERROR_DATA = "errorData";
	public static final String ERROR_ALERT = "error.alert";
	private static final String MESSAGE_LIST = "messageList";
	private static final String SAVE_SUCCESS = "saveSuccess";
	private static final String CREATE_QUARTER_SUCCESS = "createQuarterSuccess";
	private static final String COMPLETE_QUARTER_SUCCESS = "completeQuarterSuccess";
	private static final String DATE_NOT_IN_PAST = "dateNotInPast";
	private static final String QUARTER_DUE_DATE = "quarterDueDate";

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
		Map<String, Object> returnData = new HashMap<String, Object>();
		if (quarterService.findQuarterByActiveFlag()) {
			returnData.put("warningMessage", "This action will make the currently active quarter inactive");
		} else {
			QuarterDto newQuarter = new QuarterDto();
			returnData = quarterService.initQuarter();
			QuarterReport quarterReport = (QuarterReport) returnData.get("quarterReport");
			if (quarterReport != null) {
				newQuarter.setFiscalQuarterReport(populateFiscalQuarterReportDto(quarterReport));
				newQuarter.setRegions(quarterService.populateRegionDtosList(quarterReport.getQuarterId()));
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
		Map<String, Object> returnMap = new HashMap<String, Object>();
		QuarterDto quarterDto = quarterService.viewQuarter(quarterId);

		QuarterDto pastQuarterDto = quarterService
				.viewQuarter(quarterService.findPastQuarter(quarterId).getQuarterId());
		returnMap.put("quarterData", quarterDto);
		returnMap.put("referenceValueLists", ReferenceValueListService.refValueLists);
		returnMap.put("pastQuarterData", pastQuarterDto);
		return returnMap;

	}

	/**
	 * Method to create a new quarter.
	 * 
	 * @param dueDate
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public Map<String, Object> createQuarter(
			@RequestBody @Pattern(regexp = "([0-9]{2})/([0-9]{2})/([0-9]{4})") String dueDate) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date inputDate = format.parse(dueDate);
			if (null != inputDate) {
				if (inputDate.before(new Date())) {
					Map<String, DcoiRestMessage> errorData = new HashMap<>();
					errorData.put(MESSAGE_LIST,
							new DcoiRestMessage(ERROR_ALERT, messageSource.getMessage(ERROR_ALERT, null, null)));
					errorData.put("messages", new DcoiRestMessage(QUARTER_DUE_DATE,
							messageSource.getMessage(DATE_NOT_IN_PAST, null, null)));

					returnMap.put(ERROR_DATA, errorData);

					addErrorData(returnMap, DATE_NOT_IN_PAST);
					return returnMap;
				}
			}
		} catch (ParseException pe) {
			LOGGER.error(pe.getMessage());
			addErrorData(returnMap, DATE_NOT_IN_PAST);
			return returnMap;
		}
		// Should only do validation on the due date
		quarterService.createQuarter(dueDate);
		addSuccessData(returnMap, CREATE_QUARTER_SUCCESS);
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

		Map<String, Object> returnMap;
		returnMap = dataCenterService.validateDataCenters(dataCenterDtos.getList());
		if (returnMap.containsKey("messageList")) {
			return returnMap;
		}
		// Add Admin Check
		returnMap.put("dataCenterIdTotalsPairs", quarterService.costCalculation(dataCenterDtos.getList()));
		dataCenterService.saveDataCenters(dataCenterDtos.getList(), securityController.getUserAccount());
		addSuccessData(returnMap, SAVE_SUCCESS);
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
		if (returnMap.get("errorMessage") != null) {
			return returnMap;
		}
		addSuccessData(returnMap, COMPLETE_QUARTER_SUCCESS);
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
		CommonHelper.modelMapper.map(quarterReport, quarterReportDto);
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

		return csvService.exportReportResults(
				buildResultsForExport(quarterService.findViewResultsByQuarterId(quarterId), quarterId));
	}

	/**
	 * Build the map that is necessary to turn the search results into data
	 * points that are associated with a header and can be displayed in excel
	 * 
	 * @param searchResults
	 * @return
	 */
	private Map<String[], List<List<String>>> buildResultsForExport(List<DataCenterView> searchResults,
			Long quarterId) {
		List<List<String>> valuesForCSV = new LinkedList<List<String>>();
		Map<String[], List<List<String>>> searchResultsMap = new LinkedHashMap<String[], List<List<String>>>();
		for (DataCenterView searchResultVO : searchResults) {
			List<String> dataCenterViewSearchResult = new LinkedList<String>();
			dataCenterViewSearchResult.add("GSA");
			dataCenterViewSearchResult.add(
					dataCenterService.findComponentNameForOMBReport(searchResultVO.getDcoiDataCenterId(), quarterId));
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
			dataCenterViewSearchResult.add(searchResultVO.getGrossFloorArea());
			dataCenterViewSearchResult.add(searchResultVO.getTotalCustomerFloorArea());
			dataCenterViewSearchResult.add(searchResultVO.getAnnualCostPerSqFt());
			dataCenterViewSearchResult.add(searchResultVO.getOtherAgenciesServiced());
			dataCenterViewSearchResult.add(searchResultVO.getElectricityIncludedInCost());
			dataCenterViewSearchResult.add(searchResultVO.getElectricityIsMetered());
			dataCenterViewSearchResult.add(searchResultVO.getTotalPowerCapacity());
			dataCenterViewSearchResult.add(searchResultVO.getTotalITPowerCapacity());
			dataCenterViewSearchResult.add(searchResultVO.getAvgElectricityUsage());
			dataCenterViewSearchResult.add(searchResultVO.getAvgITElectricityUsage());
			dataCenterViewSearchResult.add(searchResultVO.getCostPerkWh());
			dataCenterViewSearchResult.add(searchResultVO.getAutomatedMonitoring());
			dataCenterViewSearchResult.add(searchResultVO.getServerUtilization());
			dataCenterViewSearchResult.add(searchResultVO.getFte());
			dataCenterViewSearchResult.add(searchResultVO.getFteCost());
			dataCenterViewSearchResult.add(searchResultVO.getRackCount());
			dataCenterViewSearchResult.add(searchResultVO.getTotalMainframes());
			dataCenterViewSearchResult.add(searchResultVO.getTotalWindowsServers());
			dataCenterViewSearchResult.add(searchResultVO.getTotalHPCClusterNodes());
			dataCenterViewSearchResult.add(searchResultVO.getTotalOtherServers());
			dataCenterViewSearchResult.add(searchResultVO.getTotalVirtualHosts());
			dataCenterViewSearchResult.add(searchResultVO.getTotalVirtualOS());
			dataCenterViewSearchResult.add(searchResultVO.getTotalStorage());
			dataCenterViewSearchResult.add(searchResultVO.getUsedStorage());
			dataCenterViewSearchResult.add(searchResultVO.getCoreClassificationName());
			dataCenterViewSearchResult.add(searchResultVO.getClosingStageName());
			dataCenterViewSearchResult.add(searchResultVO.getFiscalYear());
			dataCenterViewSearchResult.add(searchResultVO.getFiscalQuarter());
			dataCenterViewSearchResult.add(searchResultVO.getIssPositionName());
			dataCenterViewSearchResult.add(searchResultVO.getIssProvider());
			valuesForCSV.add(dataCenterViewSearchResult);
		}

		String[] exportColumnNames = { "AGENCY ABBREVIATION", "COMPONENT", "DATA CENTER NAME", "DATA CENTER ID",
				"STREET ADDRESS", "STREET ADDRESS 2", "CITY", "ZIPCODE", "STATE NAME", "COUNTRY NAME",
				"AGENCY DATA CENTER NAME", "PUBLISHED NAME", "RECORD STATUS NAME", "RECORD VALIDITY ID",
				"OWNERSHIP TYPE NAME", "DATA CENER TIER NAME", "GROSS FLOOR AREA", "TOTAL CUSTOMER FLOOR AREA",
				"ANNUAL COST PER SQ FT", "OTHER AGENCIES SERVICED", "ELECTRICTY INCLUDED IN COST",
				"ELECTRICTY IS METERED", "TOTAL POWER CAPACITY", "TOTAL IT POWER CAPACITY", "AVG ELECTRICITY USAGE",
				"AVG IT ELECTRICITY USAGE", "COST PER KWH", "AUTOMATED MONITORING", "SERVER UTILIZATION", "FTE",
				"FTE COST", "RACK COUNT", "TOTAL MAINFRAMES", "TOTAL WINDOWS SERVERS", "TOTAL HPC CLUSTER NODES",
				"TOTAL OTHER SERVERS", "TOTAL VIRTUAL HOSTS", "TOTAL VIRTUAL OS", "TOTAL STORAGE", "USED STORAGE",
				"CORE CLASSIFICATION NAME", "CLOSING STAGE NAME", "FISCAL YEAR", "FISCAL QUARTER", "ISS POSITION NAME",
				"ISS PROVIDER" };
		searchResultsMap.put(exportColumnNames, valuesForCSV);
		return searchResultsMap;
	}

	/**
	 * Method to add a message to the success data being sent to the front end
	 * 
	 * @param returnMap
	 * @param messageName
	 */
	private void addSuccessData(Map<String, Object> returnMap, String messageName) {
		Map<String, String[]> successData = new HashMap<>();
		successData.put(MESSAGE_LIST, new String[] { messageSource.getMessage(messageName, null, null) });
		returnMap.put(SUCCESS_DATA, successData);
	}

	/**
	 * Method to add a message to the error data to be sent to the front end
	 * 
	 * @param returnMap
	 * @param messageName
	 * @return
	 * 
	 */
	private void addErrorData(Map<String, Object> returnMap, String messageName) {
		Map<String, String[]> errorData = new HashMap<>();
		errorData.put(MESSAGE_LIST, new String[] { messageSource.getMessage(messageName, null, null) });
		returnMap.put(ERROR_DATA, errorData);
	}
}
