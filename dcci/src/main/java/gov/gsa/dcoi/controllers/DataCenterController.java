package gov.gsa.dcoi.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.gsa.dcoi.dto.DataCenterDto;
import gov.gsa.dcoi.entity.DataCenter;
import gov.gsa.dcoi.repository.DataCenterRepository;

@RestController
@RequestMapping("/datacenter")
public class DataCenterController {

	@Autowired
	DataCenterRepository dataCenterDao;

	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public Map<String, Object> initNewDataCenter() {
		Map<String, Object> returnData = new HashMap<>();
		returnData.put("newDataCenter", new DataCenterDto());
		return returnData;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Map<String, Object> addNewDataCenter(DataCenter dataCenter) {
		dataCenterDao.save(dataCenter);
		return new HashMap<>();
	}

}
