package gov.gsa.dcoi.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.gsa.dcoi.dto.DataCenterDto;
import gov.gsa.dcoi.entity.DataCenter;
import gov.gsa.dcoi.repository.DataCenterRepository;
import gov.gsa.dcoi.service.ReferenceValueListService;

/**
 * Controller for managing Data Centers.
 */
@RestController
@RequestMapping("/datacenter")
public class DataCenterController {

	@Autowired
	DataCenterRepository dataCenterDao;

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

}
