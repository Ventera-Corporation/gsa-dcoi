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
import gov.gsa.dcoi.security.User;
import gov.gsa.dcoi.security.UserRole;

/**
 * Description: Repository for handling data access for User and User Role
 * tables.
 */
@Repository
public class UserRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

	private static final String GET_USER_SQL = " SELECT dcoi_user_id, first_name, last_name, email_address, password, active_flag FROM dcoi_user du WHERE du.email_address = ? ";
	private static final String GET_USER_ROLES_SQL = " SELECT role_name FROM dcoi_user_role dur "
			+ " INNER JOIN dcoi_role dr ON dr.dcoi_role_id = dur.dcoi_role_id" + " WHERE dur.dcoi_user_id = ? ";

	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;

	/**
	 * Get a User and User Roles by email address, expect only one user.
	 * 
	 * @param emailAddress
	 * @return
	 */
	public User findByEmailAddress(String emailAddress) throws DcoiException {
		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("getting User info for: " + emailAddress);
			}
			return jdbcTemplate.query(GET_USER_SQL, new String[] { emailAddress }, new ResultSetExtractor<User>() {
				public User extractData(ResultSet rs) throws SQLException, DataAccessException {
					User user = new User();
					while (rs.next()) {
						user.setDcoiUserId(rs.getInt("dcoi_user_id"));
						user.setFirstName(rs.getString("first_name"));
						user.setLastName(rs.getString("last_name"));
						user.setEmailAddress(rs.getString("email_address"));
						user.setPassword(rs.getString("password"));
						user.setActiveFlag(rs.getBoolean("active_flag"));
						List<UserRole> roles = findRolesByUserId(user.getDcoiUserId());
						user.setRoles(roles);
					}
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("User with id: " + user.getDcoiUserId() + " found for: " + emailAddress);
					}
					return user;
				}
			});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler.throwDcoiException("Exception Finding User: " + e.getMessage());
		}
	}

	/**
	 * Get list of User Roles for a given userId.
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserRole> findRolesByUserId(int userId) throws DcoiException {
		try {
			return jdbcTemplate.query(GET_USER_ROLES_SQL, new Integer[] { userId },
					new ResultSetExtractor<List<UserRole>>() {
						public List<UserRole> extractData(ResultSet rs) throws SQLException, DataAccessException {
							List<UserRole> roles = new ArrayList<>();
							while (rs.next()) {
								UserRole ur = new UserRole();
								ur.setRoleName(rs.getString("role_name"));
								roles.add(ur);
							}
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug("User Roles for id: " + userId + ": " + roles);
							}
							return roles;
						}
					});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler.throwDcoiException("Exception Getting roles for user: " + e.getMessage());
		}
	}
}
