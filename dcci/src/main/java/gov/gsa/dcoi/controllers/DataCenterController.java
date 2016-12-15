package gov.gsa.dcoi.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.gsa.dcoi.DcoiRestMessage;
import gov.gsa.dcoi.dto.DataCenterDto;
import gov.gsa.dcoi.entity.DataCenter;
import gov.gsa.dcoi.repository.DataCenterRepository;
import gov.gsa.dcoi.service.DataCenterService;
import gov.gsa.dcoi.service.ReferenceValueListService;

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

	/**
	 * Initialize adding new Data Center.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Map<String, Object> initNewDataCenter() {
		Map<String, Object> returnData = new HashMap<>();
		returnData.put("newDataCenter", new DataCenterDto());
		returnData.put("regionRefValueList", ReferenceValueListService.refValueLists.get("regionRefValueList"));
		returnData.put("stateRefValueList", ReferenceValueListService.refValueLists.get("stateRefValueList"));
		return returnData;
	}

	/**
	 * Method to add new Data Center.
	 * 
	 * @param dataCenter
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Map<String, Object> addNewDataCenter(DataCenter dataCenter) {
		dataCenterDao.save(dataCenter);
		return new HashMap<>();
	}
	
	/**
	 * Submit a data center, completed by an SSO and moves the 
	 * data center into the admin bucket
	 * @param dataCenterId
	 * @return
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Map<String, Object> submit(@RequestBody Integer dataCenterId) {
		Map<String, Object> returnMap;
		returnMap = dataCenterService.setSSOCompleteFlag(dataCenterId);
		if(returnMap.get("errorMessage") != null){
			return returnMap;
		}
		returnMap.put("successsMessage", new DcoiRestMessage(messageSource.getMessage("submitSuccess", null, null)));
		return returnMap;
	}
	
	/**
	 * Validate a data center from an admin's perspective
	 * Just a sanity check to set the adminComplete flag to 1
	 * @param dataCenterId
	 * @return
	 */
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public Map<String, Object> validate(@RequestBody Integer dataCenterId) {
		Map<String, Object> returnMap;
		returnMap = dataCenterService.setAdminCompleteFlag(dataCenterId);
		if(returnMap.get("errorMessage") != null){
			return returnMap;
		}
		returnMap.put("successsMessage", new DcoiRestMessage(messageSource.getMessage("validateSuccess", null, null)));
		return returnMap;
	}
	
	/**
	 * Validate a data center from an admin's perspective
	 * Just a sanity check to set the adminComplete flag to 1
	 * @param dataCenterId
	 * @return
	 */
	@RequestMapping(value = "/reject", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN')")
	public Map<String, Object> reject(@RequestBody Integer dataCenterId) {
		Map<String, Object> returnMap;
		returnMap = dataCenterService.rejectDataCenter(dataCenterId);
		if(returnMap.get("errorMessage") != null){
			return returnMap;
		}
		returnMap.put("successsMessage", new DcoiRestMessage(messageSource.getMessage("rejectSuccess", null, null)));
		return returnMap;
	}

}
