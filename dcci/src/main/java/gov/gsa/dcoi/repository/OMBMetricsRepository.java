package gov.gsa.dcoi.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gov.gsa.dcoi.DcoiExceptionHandler;
import gov.gsa.dcoi.entity.OMBMetrics;

/**
 * Repository for the OMB metrics repository view
 */
@Repository
public class OMBMetricsRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(OMBMetricsRepository.class);

	private static final String GET_OMB_METRICS_ALL_QUARTERS = "SELECT * FROM vw_DCOI_OMBMetricByQuarter vdomb";

	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;

	/**
	 * Find the results of the view by quarter id, returns back only those data
	 * centers we need to display in a report for a given quarter
	 * 
	 * @param quarterId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<OMBMetrics> findAllOMBMetrics() {
		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Getting all omb metrics for all quarters");
			}
			return jdbcTemplate.query(GET_OMB_METRICS_ALL_QUARTERS, new ResultSetExtractor<List<OMBMetrics>>() {
				@Override
				public List<OMBMetrics> extractData(ResultSet rs) throws SQLException {
					return processResults(rs);
				}
			});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler.throwDcoiException("Exception getting all OMB Metrics: " + e.getMessage());
		}

	}

	/**
	 * Process the column names from the result set and placce them into the
	 * value object
	 */
	private List<OMBMetrics> processResults(ResultSet resultSet) throws SQLException {
		List<OMBMetrics> metricList = new ArrayList<>();
		while (resultSet.next()) {
			OMBMetrics metrics = new OMBMetrics();
			metrics.setFiscalYear(resultSet.getInt("year"));
			metrics.setFiscalQuarter(resultSet.getString("quarter"));
			metrics.setEnergyMetering(resultSet.getInt("energy_metering"));
			metrics.setVirtualization(resultSet.getInt("virtualization"));
			metrics.setAutomatedMonitoring(resultSet.getInt("server_utilization_automated_monitor"));
			metrics.setFacilityUtilization(resultSet.getInt("facility_utilization"));
			metricList.add(metrics);
		}
		return metricList;
	}

}
