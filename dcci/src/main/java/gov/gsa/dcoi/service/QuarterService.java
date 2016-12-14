package gov.gsa.dcoi.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import gov.gsa.dcoi.DcoiExceptionHandler;
import gov.gsa.dcoi.dto.FiscalQuarterReportDto;
import gov.gsa.dcoi.dto.QuarterDto;
import gov.gsa.dcoi.dto.RegionDto;
import gov.gsa.dcoi.entity.DataCenterView;
import gov.gsa.dcoi.entity.QuarterReport;
import gov.gsa.dcoi.repository.DataCenterQuarterRepository;
import gov.gsa.dcoi.repository.DataCenterViewRepository;
import gov.gsa.dcoi.repository.QuarterStoredProcedure;
import gov.gsa.dcoi.repository.QuarterReportRepository;

/**
 * Service class functionality for functionality related to the quarter overall
 * 
 * @author sgonthier
 *
 */
@Component
public class QuarterService {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuarterService.class);

	@Autowired
	QuarterStoredProcedure quarterRepository;

	@Autowired
	QuarterReportRepository quarterReportRepository;

	@Autowired
	DataCenterQuarterRepository dataCenterQuarterRepository;

	@Autowired
	FieldOfficeService fieldOfficeService;

	@Autowired
	DataCenterService dataCenterService;

	@Autowired
	DataCenterViewRepository exportRepository;

	/**
	 * This call creates the new quarter report and, will then populate all the
	 * datacenters with the "new" info - will eventually pass back a fully
	 * created
	 * 
	 * @return
	 */
	@Transactional
	public Map<String, Object> initQuarter() {

		quarterRepository.initQuarter();
		Map<String, Object> returnMap = new HashMap<>();

		QuarterReport quarterReport = quarterReportRepository.findByQuarterInProgressFlag(1);
		returnMap.put("quarterReport", quarterReport);
		return returnMap;

	}

	/**
	 * Method to view any past (non-active/non-in progress) OR
	 * active/in-progress quarter
	 * 
	 * @param quarterId
	 * @return
	 */
	@Transactional
	public QuarterDto viewQuarter(Long quarterId) {
		QuarterDto viewQuarter = new QuarterDto();
		QuarterReport quarterReport = quarterReportRepository.findOne(quarterId);
		viewQuarter.setFiscalQuarterReport(populateFiscalQuarterReportDto(quarterReport));
		List<RegionDto> regions = dataCenterService.populateRegionDtosList(quarterId);
		viewQuarter.setRegions(regions);
		return viewQuarter;

	}

	/**
	 * Populate the fiscal quarter report dto for use on the front end from the
	 * quarter report information we received
	 * 
	 * @param quarterReport
	 * @return
	 */
	private FiscalQuarterReportDto populateFiscalQuarterReportDto(QuarterReport quarterReport) {
		FiscalQuarterReportDto quarterReportDto = new FiscalQuarterReportDto();
		BeanUtils.copyProperties(quarterReport, quarterReportDto);
		return quarterReportDto;
	}

	/**
	 * Update the active flag to one so that we know this is the active quarter
	 * (the datacenters will be saved through the field office service) TO-DO
	 * set the in progress flag to 0??
	 * 
	 * @param dueDate
	 * @return
	 */
	@Transactional
	public Map<String, Object> createQuarter(Date dueDate) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			QuarterReport quarterReport = quarterReportRepository.findByQuarterInProgressFlag(1);

			quarterReport.setQuarterDueDate(dueDate);
			quarterReport.setQuarterActiveFlag(1);
			quarterReport.setQuarterInProgressFlag(0);
			quarterReportRepository.save(quarterReport);
			returnMap.put("successMessage",
					"The quarter is successfully created. Please inform your staff users that they should fill in the required data.");
			return returnMap;
		} catch (DataAccessException dae) {
			LOGGER.error(dae.getMessage());
			throw DcoiExceptionHandler.throwDcoiException(
					"Exception creating quarter with updated active flag" + "and due date: " + dae.getMessage());
		}

	}

	/**
	 * Find the data centers by the active flag OR the in progress flag
	 * 
	 * @return
	 */
	public Boolean findQuarterByActiveOrInProgressFlag() {
		QuarterReport quarterReport = quarterReportRepository.findByQuarterActiveFlagOrQuarterInProgressFlag(1, 1);
		if (quarterReport == null) {
			return false;
		}
		return true;

	}

	/**
	 * Find the data centers by the active flag
	 * 
	 * @return
	 */
	public Boolean findQuarterByActiveFlag() {
		QuarterReport quarterReport = quarterReportRepository.findByQuarterActiveFlag(1);
		if (quarterReport == null) {
			return false;
		}
		return true;

	}

	/**
	 * Get the data center view results that are necessary for the final report
	 * 
	 * @param quarterId
	 * @return
	 */
	public List<DataCenterView> findViewResultsByQuarterId(Long quarterId) {
		return exportRepository.findViewResultsByQuarterId(quarterId);
	}
}
