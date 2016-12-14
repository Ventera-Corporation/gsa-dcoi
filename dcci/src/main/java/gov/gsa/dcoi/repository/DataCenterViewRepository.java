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
 * report export as well as the search results
 * 
 * @author sgonthier
 *
 */
@Repository
public class DataCenterViewRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataCenterViewRepository.class);

	private static final String GET_DATA_CENTERS_FOR_QUARTER = " SELECT * FROM vw_DCOI_DataCenters vddc WHERE vddc.fiscal_year = 2016 ";

	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;

	/**
	 * Find the results of the view by quarter id, returns back only those data
	 * centers we need to display in a report for a given quarter
	 * 
	 * @param quarterId
	 * @return
	 */
	public List<DataCenterView> findViewResultsByQuarterId(Long quarterId) {
		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Getting Data Center View Data for: " + quarterId);
			}
			return jdbcTemplate.query(GET_DATA_CENTERS_FOR_QUARTER, new ResultSetExtractor<List<DataCenterView>>() {
				@Override
				public List<DataCenterView> extractData(ResultSet rs) throws SQLException {
					List<DataCenterView> dataCenterViews = new ArrayList<>();
					DataCenterView dataCenterView = new DataCenterView();
					while (rs.next()) {
						dataCenterView.setDataCenterName(rs.getString("data_center_name"));
						dataCenterView.setDcoiDataCenterId(rs.getString("dcoi_data_center_id"));
						dataCenterView.setStreetAddress(rs.getString("address"));
						dataCenterView.setStreetAddress2(rs.getString("address2"));
						dataCenterView.setCity(rs.getString("city"));
						dataCenterView.setZipCode(rs.getString("zip_code"));
						dataCenterView.setStateName(rs.getString("state_name"));
						dataCenterView.setCountryName(rs.getString("country_name"));
						dataCenterView.setPublishedName(rs.getString("published_name"));
						dataCenterView.setRecordStatusName(rs.getString("record_status_name"));
						dataCenterView.setRecordValidityName(rs.getString("record_validity_name"));
						dataCenterView.setOwnershipTypeName(rs.getString("ownership_type_name"));
						dataCenterView.setDataCenterTierName(rs.getString("data_center_tier_name"));
						dataCenterView.setGrossFloorArea(rs.getInt("gross_floor_area"));
						dataCenterView.setTotalCustomerFloorArea(rs.getInt("customer_floor_area_total"));
						dataCenterView.setAnnualCostPerSqFt(rs.getDouble("annual_cost_sq_ft"));
						dataCenterView.setOtherAgenciesServiced(rs.getString("other_agencies_serviced"));
						
						dataCenterViews.add(dataCenterView);
					}
					return dataCenterViews;
				}
			});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler.throwDcoiException("Exception Finding User: " + e.getMessage());
		}

	}

}
