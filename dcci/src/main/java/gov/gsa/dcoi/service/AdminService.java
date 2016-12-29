package gov.gsa.dcoi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.gsa.dcoi.entity.OMBMetrics;
import gov.gsa.dcoi.repository.OMBMetricsRepository;

/**
 * Service class to contain functionality for the admin controller and admin
 * repository
 */
@Component
public class AdminService {

	@Autowired
	OMBMetricsRepository metricsRepository;

	/**
	 * Function to return the OMB Metrics from the omb repository
	 * 
	 * @return
	 */
	public List<OMBMetrics> findAllOMBMetrics() {
		return metricsRepository.findAllOMBMetrics();
	}

}
