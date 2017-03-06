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
import gov.gsa.dcoi.entity.DataCenterView;

/**
 * Repository to grab the information from the view that is needed for the final
 * report export, as well as to perform searches.
 * 
 * @author sgonthier
 *
 */
@Repository
public class DataCenterViewRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataCenterViewRepository.class);

	private static final String GET_ALL_DATA_CENTERS_FOR_QUARTER = "SELECT * FROM vw_DCOI_DataCenters vddc";
	private static final String GET_DATA_CENTERS_FOR_QUARTER = GET_ALL_DATA_CENTERS_FOR_QUARTER
			+ " WHERE vddc.quarter_report_id = ?";

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
	public List<DataCenterView> findViewResultsByQuarterId(Long quarterId) {
		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Getting Data Center View Data for: " + quarterId);
			}
			return jdbcTemplate.query(GET_DATA_CENTERS_FOR_QUARTER, new String[] { quarterId.toString() },
					new ResultSetExtractor<List<DataCenterView>>() {
						@Override
						public List<DataCenterView> extractData(ResultSet rs) throws SQLException {
							return processResults(rs);
						}
					});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler
					.throwDcoiException("Exception Data Center View by Quarter ID: " + e.getMessage());
		}

	}

	/**
	 * Return all Data Center Records (All Quarters)
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<DataCenterView> findAllDataCenterRecords() {
		try {

			return jdbcTemplate.query(GET_ALL_DATA_CENTERS_FOR_QUARTER, new ResultSetExtractor<List<DataCenterView>>() {
				@Override
				public List<DataCenterView> extractData(ResultSet rs) throws SQLException {
					return processResults(rs);
				}
			});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler
					.throwDcoiException("Exception Finding all data center view records: " + e.getMessage());
		}

	}

	/**
	 * Return all Data Center Records for all quarters specifically for the cost
	 * calculation screen
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<DataCenterView> findDataCenterRecordsForCostCalc() {
		try {

			return jdbcTemplate.query(GET_ALL_DATA_CENTERS_FOR_QUARTER, new ResultSetExtractor<List<DataCenterView>>() {
				@Override
				public List<DataCenterView> extractData(ResultSet rs) throws SQLException {
					return processResultsForCostCalc(rs);
				}
			});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler.throwDcoiException(
					"Exception Finding all data center view records for cost calc: " + e.getMessage());
		}

	}

	/**
	 * Process View Results
	 *
	 * @param rs
	 * @return
	 */
	private List<DataCenterView> processResults(ResultSet rs) throws SQLException {
		List<DataCenterView> dataCenterViews = new ArrayList<>();
		while (rs.next()) {
			DataCenterView dataCenterView = new DataCenterView();
			dataCenterView.setQuarterReportId(rs.getString("quarter_report_id"));
			dataCenterView.setDataCenterId(rs.getString("data_center_id"));
			dataCenterView.setDataCenterName(rs.getString("data_center_name"));
			dataCenterView.setStreetAddress(rs.getString("address"));
			dataCenterView.setStreetAddress2(rs.getString("address2"));
			dataCenterView.setCity(rs.getString("city"));
			dataCenterView.setZipCode(rs.getString("zip_code"));
			dataCenterView.setStateName(rs.getString("state_name"));
			dataCenterView.setCountryName(rs.getString("country_name"));
			dataCenterView.setDcoiDataCenterId(rs.getString("dcoi_data_center_id"));
			dataCenterView.setAgencyDataCenterName(rs.getString("agency_data_center_id"));
			dataCenterView.setPublishedName(rs.getString("published_name"));
			dataCenterView.setRecordStatusName(rs.getString("record_status_name"));
			dataCenterView.setRecordValidityName(rs.getString("record_validity_name"));
			dataCenterView.setOwnershipTypeName(rs.getString("ownership_type_name"));
			dataCenterView.setDataCenterTierName(rs.getString("data_center_tier_name"));
			dataCenterView.setGrossFloorArea(rs.getString("gross_floor_area"));
			dataCenterView.setTotalCustomerFloorArea(rs.getString("customer_floor_area_total"));
			dataCenterView.setAnnualCostPerSqFt(rs.getString("annual_cost_sq_ft"));
			dataCenterView.setOtherAgenciesServiced(rs.getString("other_agencies_serviced"));
			dataCenterView.setElectricityIncludedInCost(rs.getString("electricity_included_in_cost_flag"));
			dataCenterView.setElectricityIsMetered(rs.getString("electricity_metered_flag"));
			dataCenterView.setTotalPowerCapacity(rs.getString("power_capacity_total"));
			dataCenterView.setTotalITPowerCapacity(rs.getString("it_power_capacity_total"));
			dataCenterView.setAvgElectricityUsage(rs.getString("electricity_usage_avg"));
			dataCenterView.setAvgITElectricityUsage(rs.getString("it_electricity_usage_avg"));
			dataCenterView.setCostPerkWh(rs.getString("cost_per_kwh"));
			dataCenterView.setAutomatedMonitoring(rs.getString("automated_monitoring_flag"));
			dataCenterView.setServerUtilization(rs.getString("server_utilization"));
			dataCenterView.setFte(rs.getString("fte"));
			dataCenterView.setFteCost(rs.getString("fte_cost"));
			dataCenterView.setRackCount(rs.getString("rack_count"));
			dataCenterView.setTotalMainframes(rs.getString("mainframe_count"));
			dataCenterView.setTotalWindowsServers(rs.getString("windows_server_count"));
			dataCenterView.setTotalOtherServers(rs.getString("other_server_count"));
			dataCenterView.setTotalHPCClusterNodes(rs.getString("hpc_cluster_node_count"));
			dataCenterView.setTotalVirtualHosts(rs.getString("virtual_host_count"));
			dataCenterView.setTotalVirtualOS(rs.getString("virtual_os_count"));
			dataCenterView.setTotalStorage(rs.getString("storage_total"));
			dataCenterView.setUsedStorage(rs.getString("storage_used"));
			dataCenterView.setCoreClassificationName(rs.getString("core_classification_name"));
			dataCenterView.setClosingStageName(rs.getString("closing_stage_name"));
			dataCenterView.setFiscalYear(rs.getString("fiscal_year"));
			dataCenterView.setFiscalQuarter(rs.getString("fiscal_quarter"));
			dataCenterView.setIssPositionName(rs.getString("iss_position_name"));
			dataCenterView.setIssProvider(rs.getString("iss_provider"));
			dataCenterView.setFieldOffices(rs.getString("field_offices"));
			dataCenterViews.add(dataCenterView);
		}
		return dataCenterViews;
	}

	/**
	 * Process View Results
	 *
	 * @param rs
	 * @return
	 */
	private List<DataCenterView> processResultsForCostCalc(ResultSet rs) throws SQLException {
		List<DataCenterView> dataCenterViews = new ArrayList<>();
		while (rs.next()) {
			DataCenterView dataCenterView = new DataCenterView();
			dataCenterView.setQuarterReportId(rs.getString("quarter_report_id"));
			dataCenterView.setDataCenterId(rs.getString("data_center_id"));
			dataCenterView.setDataCenterName(rs.getString("data_center_name"));
			dataCenterView.setFiscalYear(rs.getString("fiscal_year"));
			dataCenterView.setFiscalQuarter(rs.getString("fiscal_quarter"));

			// Cost Model
			dataCenterView.setServerAmount(rs.getString("server_amount"));
			dataCenterView.setStorageAmount(rs.getString("storage_amount"));
			dataCenterView.setOtherAmount(rs.getString("other_amount"));
			dataCenterView.setTotalAmount(rs.getString("total_amount"));
			dataCenterView.setSavingsAmount(rs.getString("savings_amount"));
			dataCenterView.setAvoidanceYear1(rs.getString("avoidance_year_1"));
			dataCenterView.setAvoidanceAmountYear1(rs.getString("avoidance_amount_year_1"));
			dataCenterView.setAvoidanceYear2(rs.getString("avoidance_year_2"));
			dataCenterView.setAvoidanceAmountYear2(rs.getString("avoidance_amount_year_2"));
			dataCenterView.setAvoidanceYear3(rs.getString("avoidance_year_3"));
			dataCenterView.setAvoidanceAmountYear3(rs.getString("avoidance_amount_year_3"));
			dataCenterViews.add(dataCenterView);
		}
		return dataCenterViews;
	}

}
