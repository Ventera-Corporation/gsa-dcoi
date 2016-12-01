package gov.gsa.dcoi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import gov.gsa.dcoi.entity.DataCenterQuarter;
import gov.gsa.dcoi.repository.DataCenterQuarterRepository;

public class DataCenterService {
	
	@Autowired 
	DataCenterQuarterRepository dataCenterQuarterRepository;
	
	public List<DataCenterQuarter> findByQuarterReportId(Integer curQuarterId){
		return dataCenterQuarterRepository.findByQuarterReportId(curQuarterId);
	}

}
