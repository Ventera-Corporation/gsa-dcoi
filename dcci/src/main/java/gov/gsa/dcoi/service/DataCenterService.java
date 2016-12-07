package gov.gsa.dcoi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import gov.gsa.dcoi.entity.DataCenterQuarter;
import gov.gsa.dcoi.repository.DataCenterQuarterRepository;

/**
 * Service class to handle database connection and information collection for 
 * data centers by quarter
 * @author sgonthier
 *
 */
public class DataCenterService {

	@Autowired
	DataCenterQuarterRepository dataCenterQuarterRepository;

	/**
	 * Find data center quarter information by the quarter report ID
	 * @param curQuarterId
	 * @return
	 */
	public List<DataCenterQuarter> findByQuarterReportId(Integer curQuarterId) {
		return dataCenterQuarterRepository.findByQuarterReportId(curQuarterId);
	}

}
