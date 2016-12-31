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
import gov.gsa.dcoi.refValueEntity.GenericReferenceValueObject;

/**
 * Description: Repository for handling data access for User and User Role
 * tables.
 */
@Repository
public class UserRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

	private static final String GET_ALL_USERS_SQL = "SELECT dcoi_user_id, first_name, last_name, email_address FROM dcoi_user";
	private static final String GET_USER_BY_EMAIL_SQL = "SELECT dcoi_user_id, first_name, last_name, email_address, password, active_flag FROM dcoi_user "
			+ "du WHERE du.email_address = ? ";
	private static final String GET_USER_ROLES_SQL = "SELECT dur.dcoi_role_id, role_name FROM dcoi_user_role dur "
			+ "INNER JOIN dcoi_role dr ON dr.dcoi_role_id = dur.dcoi_role_id" + " WHERE dur.dcoi_user_id = ? ";
	private static final String GET_USER_FIELD_OFFICES_SQL = "SELECT field_office_id FROM dcoi_user_field_office duf "
			+ "WHERE duf.dcoi_user_id = ? and duf.active_flag = 1";

	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	ReferenceValueListRepository refValueService;
	
	/**
	 * Get a list of all users
	 * 
	 * @return
	 */
	public List<User> findAllUsers() throws DcoiException {
		try {
			return jdbcTemplate.query(GET_ALL_USERS_SQL,new ResultSetExtractor<List<User>>() {
				public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
					List<User> allUsers = new ArrayList<User>();
					while (rs.next()) {
						User user = new User();
						user.setDcoiUserId(rs.getInt("dcoi_user_id"));
						user.setFirstName(rs.getString("first_name"));
						user.setLastName(rs.getString("last_name"));
						user.setEmailAddress(rs.getString("email_address"));
						user.setRoles(findRolesByUserId(user.getDcoiUserId()));
						user.setUserFieldOffices(findUserFieldOffices(user.getDcoiUserId()));
						allUsers.add(user);
						
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("User with id: " + user.getDcoiUserId() + " found.");
						}
					}
					return allUsers;
				}
			});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler.throwDcoiException("Exception in findAllUsers: " + e.getMessage());
		}
	}

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
			return jdbcTemplate.query(GET_USER_BY_EMAIL_SQL, new String[] { emailAddress }, new ResultSetExtractor<User>() {
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
							List<UserRole> roles = new ArrayList<UserRole>();
							while (rs.next()) {
								UserRole ur = new UserRole();
								ur.setDcoiRoleId(rs.getInt("dcoi_role_id"));
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
	

	/**
	 * Get a list of User Agency Components for a given user.
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> findUserFieldOffices(int userId) throws DcoiException {
		try {
			return jdbcTemplate.query(GET_USER_FIELD_OFFICES_SQL, new Integer[] { userId },
					new ResultSetExtractor<List<String>>() {
				public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
					List<String> components = new ArrayList<String>();
					List<Integer> fieldOffices = new ArrayList<Integer>();
					while (rs.next()) {
						fieldOffices.add(rs.getInt("field_office_id"));			
					}
					for (GenericReferenceValueObject component : refValueService.findAllComponents()){
						for(Integer fieldOffice : fieldOffices){
							if(component.getId()==fieldOffice){
								components.add(component.getValue());
							}
						}
					}
					return components;
				}
			});
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			throw DcoiExceptionHandler.throwDcoiException("Exception in findUserFieldOffices: " + e.getMessage());
		}
	}
}
