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

import gov.gsa.dcoi.DcoiException;
import gov.gsa.dcoi.DcoiExceptionHandler;
import gov.gsa.dcoi.refValueEntity.GenericReferenceValueObject;

/**
 * Repository class to handle sql queries to gather id's and values for all the
 * ref value list
 * 
 * @author sgonthier
 *
 */
@Repository
public class ReferenceValueListRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceValueListRepository.class);

	private static final String GET_RECORD_VALIDITY_REF_LIST = " SELECT record_validity_id, record_validity_name FROM ref_record_validity ";
	private static final String GET_RECORD_STATUS_REF_LIST = " SELECT record_status_id, record_status_name FROM ref_record_status";
	private static final String GET_STATE_REF_LIST = " SELECT state_id, state_code FROM ref_state rs";
	private static final String GET_FISCAL_YEAR_REF_LIST = " SELECT fiscal_year_id, fiscal_year FROM ref_fiscal_year";
	private static final String GET_FISCAL_QUARTER_REF_LIST = " SELECT fiscal_quarter_id, fiscal_quarter FROM ref_fiscal_quarter";
	private static final String GET_REGION_REF_LIST = " SELECT region_id, region_name FROM ref_region";
	private static final String GET_OWNERSHIP_TYPE_REF_LIST = " SELECT ownership_type_id, ownership_type_name FROM ref_ownership_type";
	private static final String GET_ISS_POSITION_REF_LIST = " SELECT iss_position_id, iss_position_name FROM ref_iss_position";
	private static final String GET_DATA_CENTER_TIER_REF_LIST = " SELECT data_center_tier_id, data_center_tier_name FROM ref_data_center_tier";
	private static final String GET_COUNTRY_REF_LIST = " SELECT country_id, country_name FROM ref_country";
	private static final String GET_CORE_CLASSIFICATION_REF_LIST = " SELECT core_classification_id, core_classification_name FROM ref_core_classification";
	private static final String GET_CLOSING_STAGE_REF_LIST = " SELECT closing_stage_id, closing_stage_name FROM ref_closing_stage";
	private static final String GET_COMPONENT_REF_LIST = " SELECT field_office_id, field_office_name FROM field_office";

	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;

	/**
	 * Get the record validity ref value list
	 * 
	 * @return
	 */
	public List<GenericReferenceValueObject> findAllRecordValidities() throws DcoiException {
		try {
			return jdbcTemplate.query(GET_RECORD_VALIDITY_REF_LIST,
					new ResultSetExtractor<List<GenericReferenceValueObject>>() {
						public List<GenericReferenceValueObject> extractData(ResultSet rs) throws SQLException {
							List<GenericReferenceValueObject> refValueObjects = new ArrayList<>();

							while (rs.next()) {
								GenericReferenceValueObject refValueObject = new GenericReferenceValueObject();
								refValueObject.setId(rs.getInt("record_validity_id"));
								refValueObject.setValue(rs.getString("record_validity_name"));
								refValueObjects.add(refValueObject);
							}
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug(
										"Issue with gathering record validity information for reference value list");
							}
							return refValueObjects;
						}
					});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler
					.throwDcoiException("Exception Finding record validity information: " + e.getMessage());
		}
	}

	/**
	 * Get the record status ref value list
	 * 
	 * @return
	 * @throws DcoiException
	 */
	public List<GenericReferenceValueObject> findAllRecordStatuses() throws DcoiException {
		try {
			return jdbcTemplate.query(GET_RECORD_STATUS_REF_LIST,
					new ResultSetExtractor<List<GenericReferenceValueObject>>() {
						public List<GenericReferenceValueObject> extractData(ResultSet rs) throws SQLException {
							List<GenericReferenceValueObject> refValueObjects = new ArrayList<>();

							while (rs.next()) {
								GenericReferenceValueObject refValueObject = new GenericReferenceValueObject();
								refValueObject.setId(rs.getInt("record_status_id"));
								refValueObject.setValue(rs.getString("record_status_name"));
								refValueObjects.add(refValueObject);
							}
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug("Issue with gathering record status information for reference value list");
							}
							return refValueObjects;
						}
					});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler
					.throwDcoiException("Exception Finding record status information: " + e.getMessage());
		}
	}

	/**
	 * Get the states ref value list
	 * 
	 * @return
	 * @throws DcoiException
	 */

	public List<GenericReferenceValueObject> findAllStates() throws DcoiException {
		try {
			return jdbcTemplate.query(GET_STATE_REF_LIST, new ResultSetExtractor<List<GenericReferenceValueObject>>() {
				public List<GenericReferenceValueObject> extractData(ResultSet rs) throws SQLException {
					List<GenericReferenceValueObject> refValueObjects = new ArrayList<>();

					while (rs.next()) {
						GenericReferenceValueObject refValueObject = new GenericReferenceValueObject();
						refValueObject.setId(rs.getInt("state_id"));
						refValueObject.setValue(rs.getString("state_code"));
						refValueObjects.add(refValueObject);
					}
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Issue with gathering state information for reference value list");
					}
					return refValueObjects;
				}
			});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler.throwDcoiException("Exception Finding state information: " + e.getMessage());
		}
	}

	/**
	 * Get the fiscal years ref value list
	 * 
	 * @return
	 * @throws DcoiException
	 */
	public List<GenericReferenceValueObject> findAllFiscalYears() throws DcoiException {
		try {
			return jdbcTemplate.query(GET_FISCAL_YEAR_REF_LIST,
					new ResultSetExtractor<List<GenericReferenceValueObject>>() {
						public List<GenericReferenceValueObject> extractData(ResultSet rs) throws SQLException {
							List<GenericReferenceValueObject> refValueObjects = new ArrayList<>();

							while (rs.next()) {
								GenericReferenceValueObject refValueObject = new GenericReferenceValueObject();
								refValueObject.setId(rs.getInt("fiscal_year_id"));
								refValueObject.setValue(rs.getString("fiscal_year"));
								refValueObjects.add(refValueObject);
							}
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug("Issue with gathering fiscal year information for reference value list");
							}
							return refValueObjects;
						}
					});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler
					.throwDcoiException("Exception Finding fiscal year information: " + e.getMessage());
		}
	}

	/**
	 * Get the fiscal quarters ref value list
	 * 
	 * @return
	 * @throws DcoiException
	 */
	public List<GenericReferenceValueObject> findAllFiscalQuarters() throws DcoiException {
		try {
			return jdbcTemplate.query(GET_FISCAL_QUARTER_REF_LIST,
					new ResultSetExtractor<List<GenericReferenceValueObject>>() {
						public List<GenericReferenceValueObject> extractData(ResultSet rs) throws SQLException {
							List<GenericReferenceValueObject> refValueObjects = new ArrayList<>();

							while (rs.next()) {
								GenericReferenceValueObject refValueObject = new GenericReferenceValueObject();
								refValueObject.setId(rs.getInt("fiscal_quarter_id"));
								refValueObject.setValue(rs.getString("fiscal_quarter"));
								refValueObjects.add(refValueObject);
							}
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug(
										"Issue with gathering fiscal quarter information for reference value list");
							}
							return refValueObjects;
						}
					});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler
					.throwDcoiException("Exception Finding fiscal quarter information: " + e.getMessage());
		}
	}

	/**
	 * Get the regions ref value list
	 * 
	 * @return
	 * @throws DcoiException
	 */
	public List<GenericReferenceValueObject> findAllRegions() throws DcoiException {
		try {
			return jdbcTemplate.query(GET_REGION_REF_LIST, new ResultSetExtractor<List<GenericReferenceValueObject>>() {
				public List<GenericReferenceValueObject> extractData(ResultSet rs) throws SQLException {
					List<GenericReferenceValueObject> refValueObjects = new ArrayList<>();

					while (rs.next()) {
						GenericReferenceValueObject refValueObject = new GenericReferenceValueObject();
						refValueObject.setId(rs.getInt("region_id"));
						refValueObject.setValue(rs.getString("region_name"));
						refValueObjects.add(refValueObject);
					}
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Issue with gathering region information for reference value list");
					}
					return refValueObjects;
				}
			});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler.throwDcoiException("Exception Finding region information: " + e.getMessage());
		}
	}

	/**
	 * Get the ownership types ref value list
	 * 
	 * @return
	 * @throws DcoiException
	 */
	public List<GenericReferenceValueObject> findAllOwnershipTypes() throws DcoiException {
		try {
			return jdbcTemplate.query(GET_OWNERSHIP_TYPE_REF_LIST,
					new ResultSetExtractor<List<GenericReferenceValueObject>>() {
						public List<GenericReferenceValueObject> extractData(ResultSet rs) throws SQLException {
							List<GenericReferenceValueObject> refValueObjects = new ArrayList<>();

							while (rs.next()) {
								GenericReferenceValueObject refValueObject = new GenericReferenceValueObject();
								refValueObject.setId(rs.getInt("ownership_type_id"));
								refValueObject.setValue(rs.getString("ownership_type_name"));
								refValueObjects.add(refValueObject);
							}
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug(
										"Issue with gathering ownership type information for reference value list");
							}
							return refValueObjects;
						}
					});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler
					.throwDcoiException("Exception Finding ownership type information: " + e.getMessage());
		}
	}

	/**
	 * Get the ISS Position ref value list
	 * 
	 * @return
	 * @throws DcoiException
	 */
	public List<GenericReferenceValueObject> findAllISSPositions() throws DcoiException {
		try {
			return jdbcTemplate.query(GET_ISS_POSITION_REF_LIST,
					new ResultSetExtractor<List<GenericReferenceValueObject>>() {
						public List<GenericReferenceValueObject> extractData(ResultSet rs) throws SQLException {
							List<GenericReferenceValueObject> refValueObjects = new ArrayList<>();

							while (rs.next()) {
								GenericReferenceValueObject refValueObject = new GenericReferenceValueObject();
								refValueObject.setId(rs.getInt("iss_position_id"));
								refValueObject.setValue(rs.getString("iss_position_name"));
								refValueObjects.add(refValueObject);
							}
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug("Issue with gathering iss position information for reference value list");
							}
							return refValueObjects;
						}
					});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler
					.throwDcoiException("Exception Finding iss position information: " + e.getMessage());
		}
	}

	/**
	 * Get the data center tiers ref value list
	 * 
	 * @return
	 * @throws DcoiException
	 */
	public List<GenericReferenceValueObject> findAllDataCenterTiers() throws DcoiException {
		try {
			return jdbcTemplate.query(GET_DATA_CENTER_TIER_REF_LIST,
					new ResultSetExtractor<List<GenericReferenceValueObject>>() {
						public List<GenericReferenceValueObject> extractData(ResultSet rs) throws SQLException {
							List<GenericReferenceValueObject> refValueObjects = new ArrayList<>();

							while (rs.next()) {
								GenericReferenceValueObject refValueObject = new GenericReferenceValueObject();
								refValueObject.setId(rs.getInt("data_center_tier_id"));
								refValueObject.setValue(rs.getString("data_center_tier_name"));
								refValueObjects.add(refValueObject);
							}
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug(
										"Issue with gathering data center tier information for reference value list");
							}
							return refValueObjects;
						}
					});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler
					.throwDcoiException("Exception Finding data center tier information: " + e.getMessage());
		}
	}

	/**
	 * Get the country ref value list
	 * 
	 * @return
	 * @throws DcoiException
	 */
	public List<GenericReferenceValueObject> findAllCountries() throws DcoiException {
		try {
			return jdbcTemplate.query(GET_COUNTRY_REF_LIST,
					new ResultSetExtractor<List<GenericReferenceValueObject>>() {
						public List<GenericReferenceValueObject> extractData(ResultSet rs) throws SQLException {
							List<GenericReferenceValueObject> refValueObjects = new ArrayList<>();

							while (rs.next()) {
								GenericReferenceValueObject refValueObject = new GenericReferenceValueObject();
								refValueObject.setId(rs.getInt("country_id"));
								refValueObject.setValue(rs.getString("country_name"));
								refValueObjects.add(refValueObject);
							}
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug("Issue with gathering country information for reference value list");
							}
							return refValueObjects;
						}
					});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler.throwDcoiException("Exception Finding country information: " + e.getMessage());
		}
	}

	/**
	 * Get the core classification ref value list
	 * 
	 * @return
	 * @throws DcoiException
	 */
	public List<GenericReferenceValueObject> findAllCoreClassifications() throws DcoiException {
		try {
			return jdbcTemplate.query(GET_CORE_CLASSIFICATION_REF_LIST,
					new ResultSetExtractor<List<GenericReferenceValueObject>>() {
						public List<GenericReferenceValueObject> extractData(ResultSet rs) throws SQLException {
							List<GenericReferenceValueObject> refValueObjects = new ArrayList<>();

							while (rs.next()) {
								GenericReferenceValueObject refValueObject = new GenericReferenceValueObject();
								refValueObject.setId(rs.getInt("core_classification_id"));
								refValueObject.setValue(rs.getString("core_classification_name"));
								refValueObjects.add(refValueObject);
							}
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug("Issue with gathering core classification for reference value list");
							}
							return refValueObjects;
						}
					});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler
					.throwDcoiException("Exception Finding core classification information: " + e.getMessage());
		}
	}

	/**
	 * Get the closing stages ref value list
	 * 
	 * @return
	 * @throws DcoiException
	 */
	public List<GenericReferenceValueObject> findAllClosingStages() throws DcoiException {
		try {
			return jdbcTemplate.query(GET_CLOSING_STAGE_REF_LIST,
					new ResultSetExtractor<List<GenericReferenceValueObject>>() {
						public List<GenericReferenceValueObject> extractData(ResultSet rs) throws SQLException {
							List<GenericReferenceValueObject> refValueObjects = new ArrayList<>();

							while (rs.next()) {
								GenericReferenceValueObject refValueObject = new GenericReferenceValueObject();
								refValueObject.setId(rs.getInt("closing_stage_id"));
								refValueObject.setValue(rs.getString("closing_stage_name"));
								refValueObjects.add(refValueObject);
							}
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug("Issue with gathering core classification for reference value list");
							}
							return refValueObjects;
						}
					});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler
					.throwDcoiException("Exception Finding core classification information: " + e.getMessage());
		}
	}

	/**
	 * Get the components ref value list
	 * 
	 * @return
	 * @throws DcoiException
	 */
	public List<GenericReferenceValueObject> findAllComponents() throws DcoiException {
		try {
			return jdbcTemplate.query(GET_COMPONENT_REF_LIST,
					new ResultSetExtractor<List<GenericReferenceValueObject>>() {
						public List<GenericReferenceValueObject> extractData(ResultSet rs) throws SQLException {
							List<GenericReferenceValueObject> refValueObjects = new ArrayList<>();

							while (rs.next()) {
								GenericReferenceValueObject refValueObject = new GenericReferenceValueObject();
								refValueObject.setId(rs.getInt("field_office_id"));
								refValueObject.setValue(rs.getString("field_office_name"));
								refValueObjects.add(refValueObject);
							}
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug("Issue with gathering field office information for reference value list");
							}
							return refValueObjects;
						}
					});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler
					.throwDcoiException("Exception Finding field office information: " + e.getMessage());
		}
	}

}
