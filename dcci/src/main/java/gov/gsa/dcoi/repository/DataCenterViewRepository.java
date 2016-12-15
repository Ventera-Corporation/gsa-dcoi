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
	private static final String GET_DATA_CENTERS_FOR_QUARTER = GET_ALL_DATA_CENTERS_FOR_QUARTER + " WHERE vddc.quarter_report_id = ?";

	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;

	/**
	 * Find the results of the view by quarter id, returns back only those data
	 * centers we need to display in a report for a given quarter
	 * 
	 * @param quarterId
	 * @return
	 */
	public List<DataCenterView> findViewResultsByQuarterId(Long quarterReportId) {
		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Getting Data Center View Data for: " + quarterReportId);
			}
			return jdbcTemplate.query(GET_DATA_CENTERS_FOR_QUARTER,  new String[] { quarterReportId.toString() }, new ResultSetExtractor<List<DataCenterView>>() {
				@Override
				public List<DataCenterView> extractData(ResultSet rs) throws SQLException {
					return processResults(rs);
				}
			});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler.throwDcoiException("Exception Finding User: " + e.getMessage());
		}

	}
	
	/**
	 * Return all results
	 * 
	 * @return
	 */
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
			throw DcoiExceptionHandler.throwDcoiException("Exception Finding User: " + e.getMessage());
		}

	}
	
	/**
	 * Process View Results 
	 *
	 * @param rs
	 * @return
	 */
	private List<DataCenterView> processResults(ResultSet rs) throws SQLException{
		List<DataCenterView> dataCenterViews = new ArrayList<DataCenterView>();
		while (rs.next()) {
			DataCenterView dataCenterView = new DataCenterView();
			dataCenterView.setQuarterReportId(rs.getInt("quarter_report_id"));
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
			dataCenterView.setGrossFloorArea(rs.getInt("gross_floor_area"));
			dataCenterView.setTotalCustomerFloorArea(rs.getInt("customer_floor_area_total"));
			dataCenterView.setAnnualCostPerSqFt(rs.getDouble("annual_cost_sq_ft"));
			dataCenterView.setOtherAgenciesServiced(rs.getString("other_agencies_serviced"));
			dataCenterView.setElectricityIncludedInCost(rs.getInt("electricity_included_in_cost_flag"));
			dataCenterView.setElectricityIsMetered(rs.getInt("electricity_metered_flag"));
			dataCenterView.setTotalPowerCapacity(rs.getDouble("power_capacity_total"));
			dataCenterView.setTotalITPowerCapacity(rs.getDouble("it_power_capacity_total"));
			dataCenterView.setAvgElectricityUsage(rs.getDouble("electricity_usage_avg"));
			dataCenterView.setAvgITElectricityUsage(rs.getDouble("it_electricity_usage_avg"));
			dataCenterView.setCostPerkWh(rs.getDouble("cost_per_kwh"));
			dataCenterView.setAutomatedMonitoring(rs.getInt("automated_monitoring_flag"));
			dataCenterView.setServerUtilization(rs.getDouble("server_utilization"));
			dataCenterView.setFte(rs.getDouble("fte"));
			dataCenterView.setFteCost(rs.getInt("fte_cost"));
			dataCenterView.setRackCount(rs.getInt("rack_count"));
			dataCenterView.setTotalMainframes(rs.getInt("mainframe_count"));
			dataCenterView.setTotalWindowsServers(rs.getInt("windows_server_count"));
			dataCenterView.setTotalOtherServers(rs.getInt("other_server_count"));
			dataCenterView.setTotalHPCClusterNodes(rs.getInt("hpc_cluster_node_count"));
			dataCenterView.setTotalVirtualHosts(rs.getInt("virtual_host_count"));
			dataCenterView.setTotalVirtualOS(rs.getInt("virtual_os_count"));
			dataCenterView.setTotalStorage(rs.getDouble("storage_total"));
			dataCenterView.setUsedStorage(rs.getDouble("storage_used"));
			dataCenterView.setCoreClassificationName(rs.getString("core_classification_name"));
			dataCenterView.setClosingStageName(rs.getString("closing_stage_name"));
			dataCenterView.setFiscalYear(rs.getInt("fiscal_year"));
			dataCenterView.setFiscalQuarter(rs.getString("fiscal_quarter"));
			dataCenterView.setIssPositionName(rs.getString("iss_position_name"));
			dataCenterView.setIssProvider(rs.getString("iss_provider"));
			dataCenterViews.add(dataCenterView);
		}
		return dataCenterViews;
	}

}
