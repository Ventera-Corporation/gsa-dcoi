package gov.gsa.dcoi.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gov.gsa.dcoi.dto.DataCenterDto;
import gov.gsa.dcoi.service.DataCenterService;

/**
 * Controller for Searches
 */
@RestController
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
	DataCenterService dataCenterService;
	
	/**
	 * Global Search
	 */
	@RequestMapping(value = "/searchResults", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> searchGlobal() {
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("searchResults", dataCenterService.findByDcoiDataCenterId(null));
		return returnData;
	}
	
	/**
	 * Advanced Search
	 */
	@RequestMapping(value = "/queryData", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> search() {
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("searchResults", dataCenterService.findByDcoiDataCenterId(null));
		return returnData;
	}
}
