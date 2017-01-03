package gov.gsa.dcoi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gov.gsa.dcoi.entity.OMBMetrics;
import gov.gsa.dcoi.repository.OMBMetricsRepository;
import gov.gsa.dcoi.repository.UserRepository;
import gov.gsa.dcoi.security.User;
import gov.gsa.dcoi.service.AdminService;
import gov.gsa.dcoi.service.ReferenceValueListService;

/**
 * Admin controller to handle functionality for the admin module
 * 
 * @author sgonthier
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	OMBMetricsRepository metricsService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AdminService adminService;

	@Autowired
	MessageSource messageSource;

	private static final String SUCCESS_DATA = "successData";
	private static final String ERROR_DATA = "errorData";
	private static final String MESSAGE_LIST = "messageList";
	private static final String SAVE_USER_INFO_SUCCESS = "saveUserInfoSuccess";
	private static final String SAVE_USER_INFO_ERROR = "saveUserInfoError";

	/**
	 * Method to get the OMB metrics for all quarters
	 * 
	 * @return
	 */
	@RequestMapping(value = "/metrics", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ResponseBody
	public List<OMBMetrics> getMetrics() {
		return metricsService.findAllOMBMetrics();
	}

	/**
	 * Show the Admin users settings page
	 */
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public Map<String, Object> getAllUsers() {
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("allUsers", userRepository.findAllUsers());
		returnMap.put("roleRefValueList", ReferenceValueListService.refValueLists.get("roleRefValueList"));
		returnMap.put("componentRefValueList", ReferenceValueListService.refValueLists.get("componentRefValueList"));
		return returnMap;
	}

	/**
	 * Save the edited users info
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/saveUserInfo", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public Map<String, Object> saveUserInfo(@RequestBody User user) {
		Map<String, Object> returnMap = new HashMap<>();

		if (adminService.updateUserInformation(user)) {
			addSuccessData(returnMap, SAVE_USER_INFO_SUCCESS);
		} else {
			addErrorData(returnMap, SAVE_USER_INFO_ERROR);
		}

		return returnMap;
	}

	/**
	 * Method to add a message to the success data to be sent to the front end
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
