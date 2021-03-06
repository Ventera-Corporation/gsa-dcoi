package gov.gsa.dcoi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gov.gsa.dcoi.entity.QuarterReport;
import gov.gsa.dcoi.repository.QuarterReportRepository;

/**
 * Service class to handle the work flow and data management between the
 * front-end controllers and the back-end repositories.
 * 
 * @author sgonthier
 *
 */
@Component
public class DashboardService {

	@Autowired
	QuarterReportRepository quarterReportRepository;

	@Autowired
	DataCenterService dataCenterService;

	@Autowired
	FieldOfficeService fieldOfficeService;

	/**
	 * Method to find information from all quarter reports and return to the
	 * front-end
	 * 
	 * @return List<QuarterReport>
	 */
	@Transactional(readOnly = true)
	public List<QuarterReport> initDashboard() {
		return (List<QuarterReport>) quarterReportRepository.findAll();
	}

}
