package gov.gsa.dcoi.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserIdStoredProcedure {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserIdStoredProcedure.class);

	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;

	/**
	 * Find the results of the view by quarter id, returns back only those data
	 * centers we need to display in a report for a given quarter
	 * 
	 * @param quarterId
	 * @return
	 */
	@Transactional
	public void setUserId(Integer userId) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("usp_DCOI_SetUserID");

		SqlParameterSource in = new MapSqlParameterSource().addValue("pCallerID", userId);
		jdbcCall.execute(in);

	}
}
