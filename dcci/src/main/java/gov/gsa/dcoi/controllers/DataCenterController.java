package gov.gsa.dcoi.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.gsa.dcoi.dto.DataCenterDto;
import gov.gsa.dcoi.dto.FieldOfficeDto;
import gov.gsa.dcoi.repository.DataCenterRepository;
import gov.gsa.dcoi.service.DataCenterService;
import gov.gsa.dcoi.service.FieldOfficeService;
import gov.gsa.dcoi.service.QuarterService;
import gov.gsa.dcoi.service.ReferenceValueListService;
import gov.gsa.dcoi.service.ValidateDcoiData;

/**
 * Controller for managing Data Centers.
 */
@RestController
@RequestMapping("/datacenter")
public class DataCenterController {

	@Autowired
	DataCenterRepository dataCenterDao;

	@Autowired
	DataCenterService dataCenterService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	QuarterService quarterService;

	@Autowired
	FieldOfficeService fieldOfficeService;
	
	@Autowired
	ValidateDcoiData validationService;
	
	@Autowired
	SecurityController securityController;

	private static final String SUCCESS_DATA = "successData";
	private static final String MESSAGE_LIST = "messageList";
	private static final String ADD_DATA_CENTER_SUCCESS = "addDataCenterSuccess";
	private static final String SUBMIT_SUCCESS = "submitSuccess";
	private static final String VALIDATE_SUCCESS = "validateSuccess";
	private static final String REJECT_SUCCESS = "rejectSuccess";
	
	/**
	 * Initialize adding new Data Center.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Map<String, Object> initNewDataCenter() {
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("dataCenterData", new DataCenterDto());
		returnData.put("fieldOfficeData", new FieldOfficeDto());
		returnData.put("regionRefValueList", ReferenceValueListService.refValueLists.get("regionRefValueList"));
		returnData.put("stateRefValueList", ReferenceValueListService.refValueLists.get("stateRefValueList"));
		returnData.put("componentRefValueList", ReferenceValueListService.refValueLists.get("componentRefValueList"));
		return returnData;
	}

	/**
	 * Method to add new Data Center.
	 * 
	 * @param dataCenterDto
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Map<String, Object> addNewDataCenter(@Valid @RequestBody DataCenterDto dataCenterDto) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("dataCenterData", dataCenterService.addAndReturnNewDataCenter(dataCenterDto));
		addSuccessData(returnMap, ADD_DATA_CENTER_SUCCESS);
		return returnMap;
	}

	/**
	 * Submit a data center, completed by an SSO and moves the data center into
	 * the admin bucket
	 * 
	 * @param dataCenterId
	 * @return
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Map<String, Object> submit(@RequestBody Integer dataCenterId) {
		Map<String, Object> returnMap;
		returnMap = dataCenterService.setSSOCompleteFlag(dataCenterId);
		if (returnMap.get("errorMessage") != null) {
			return returnMap;
		}
		addSuccessData(returnMap, SUBMIT_SUCCESS);
		return returnMap;
	}

	/**
	 * Validate a data center from an admin's perspective Just a sanity check to
	 * set the adminComplete flag to 1
	 * 
	 * @param dataCenterDto
	 * @return
	 */
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public Map<String, Object> validate(@RequestBody DataCenterDto dataCenterDto) {
		Map<String, Object> returnMap;
		returnMap = validationService.validateDataCenters(Arrays.asList(dataCenterDto), securityController.getUserAccount());
		if (returnMap.containsKey("messageList")) {
			return returnMap;
		}
		returnMap = dataCenterService.setAdminCompleteFlag(dataCenterDto.getDataCenterId());
		if (returnMap.get("errorMessage") != null) {
			return returnMap;
		}
		addSuccessData(returnMap, VALIDATE_SUCCESS);
		return returnMap;
	}

	/**
	 * Validate a data center from an admin's perspective Just a sanity check to
	 * set the adminComplete flag to 1
	 * 
	 * @param dataCenterId
	 * @return
	 */
	@RequestMapping(value = "/reject", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public Map<String, Object> reject(@RequestBody Integer dataCenterId) {
		Map<String, Object> returnMap;
		returnMap = dataCenterService.rejectDataCenter(dataCenterId);
		if (returnMap.get("errorMessage") != null) {
			return returnMap;
		}
		addSuccessData(returnMap, REJECT_SUCCESS);
		return returnMap;
	}
	
	private void addSuccessData(Map<String, Object> returnMap, String messageName){
		Map<String, String[]> successData = new HashMap<String, String[]>();
		successData.put(MESSAGE_LIST, new String[]{messageSource.getMessage(messageName, null, null)});
		returnMap.put(SUCCESS_DATA, successData);
	}
}
