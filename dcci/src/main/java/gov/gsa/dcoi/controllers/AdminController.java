package gov.gsa.dcoi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gov.gsa.dcoi.entity.OMBMetrics;
import gov.gsa.dcoi.repository.OMBMetricsRepository;
import gov.gsa.dcoi.repository.UserRepository;
import gov.gsa.dcoi.security.User;

/**
 * Admin controller to handle functionality for the admin module
 * 
 * @author sgonthier
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	OMBMetricsRepository metricsService;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Method to get the OMB metrics for all quarters
	 * 
	 * @return
	 */
	@RequestMapping(value = "/metrics", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ResponseBody
	public List<OMBMetrics> getMetrics() {
		return metricsService.findAllOMBMetrics();
	}

	/**
	 * Show the Admin users settings page
	 */
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ResponseBody
	public List<User> getAllUsers() {
		return userRepository.findAllUsers();
	}

}
