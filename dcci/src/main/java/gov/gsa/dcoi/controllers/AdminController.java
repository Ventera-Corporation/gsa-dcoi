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
	private UserRepository userRepository;

	@Autowired
	MessageSource messageSource;

	private static final String SUCCESS_DATA = "successData";
	private static final String MESSAGE_LIST = "messageList";
	private static final String SAVE_USER_INFO_SUCCESS = "saveUserInfoSuccess";

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
	 */
	@RequestMapping(value = "/saveUserInfo", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public Map<String, Object> saveUserInfo(@RequestBody User user) {
		Map<String, Object> returnMap = new HashMap<>();
		
		//TODO - save user info - must parse userFieldOffices of names to save
		
		addSuccessData(returnMap, SAVE_USER_INFO_SUCCESS);
		return returnMap;
	}
	
	private void addSuccessData(Map<String, Object> returnMap, String messageName){
		Map<String, String[]> successData = new HashMap<>();
		successData.put(MESSAGE_LIST, new String[]{messageSource.getMessage(messageName, null, null)});
		returnMap.put(SUCCESS_DATA, successData);
	}
}
