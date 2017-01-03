package gov.gsa.dcoi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gov.gsa.dcoi.entity.OMBMetrics;
import gov.gsa.dcoi.repository.OMBMetricsRepository;
import gov.gsa.dcoi.repository.UserRepository;
import gov.gsa.dcoi.security.User;

/**
 * Service class to contain functionality for the admin controller and admin
 * repository
 */
@Component
public class AdminService {

	@Autowired
	OMBMetricsRepository metricsRepository;

	@Autowired
	UserRepository userRepository;

	/**
	 * Function to return the OMB Metrics from the omb repository
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<OMBMetrics> findAllOMBMetrics() {
		return metricsRepository.findAllOMBMetrics();
	}

	/**
	 * Function to update the user roles and field offices of a user as well as
	 * the general information like name and email
	 * 
	 * @param user
	 * @return
	 */
	@Transactional
	public Boolean updateUserInformation(User user) {
		userRepository.updateUserRoles(user.getDcoiUserId(), user.getRoleIds());
		userRepository.updateUserFieldOffices(user.getDcoiUserId(), user.getFieldOfficeIds());
		userRepository.updateUser(user);
		return true;
	}
}
