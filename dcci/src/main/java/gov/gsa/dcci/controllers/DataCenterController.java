package gov.gsa.dcci.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.gsa.dcci.entity.DataCenter;
import gov.gsa.dcci.repository.DataCenterRepository;

@RestController
@RequestMapping("/dataCenter")
public class DataCenterController {
	
	@Autowired
	DataCenterRepository dataCenterDao;
	
	@RequestMapping(value = "/initNewDataCenter", method = RequestMethod.GET)
	public Map<String, Object> initNewDataCenter(){
		Map<String, Object> returnData = new HashMap<>();
		returnData.put("newDataCenter", new DataCenter());
		return returnData;
	}
	@RequestMapping(value = "/addNewDataCenter", method = RequestMethod.POST)
	public Map<String, Object> addNewDataCenter(DataCenter dataCenter){
		dataCenterDao.save(dataCenter);
		return new HashMap<>();
	}

}
