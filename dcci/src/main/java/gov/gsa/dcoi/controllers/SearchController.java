package gov.gsa.dcoi.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gov.gsa.dcoi.repository.DataCenterViewRepository;

/**
 * Controller for Searches
 */
@RestController
@RequestMapping("/search")
@PreAuthorize("hasAnyRole('ADMIN', 'SERVER', 'FACILITY')")
public class SearchController {

	@Autowired
	DataCenterViewRepository viewRepository;

	/**
	 * Search
	 * 
	 * @return
	 */
	@RequestMapping(value = "/results", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> search() {
		Map<String, Object> returnData = new HashMap<>();
		returnData.put("searchResults", viewRepository.findAllDataCenterRecords());
		return returnData;
	}

}
