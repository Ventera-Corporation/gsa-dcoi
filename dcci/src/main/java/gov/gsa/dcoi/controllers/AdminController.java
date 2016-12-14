package gov.gsa.dcoi.controllers;


import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gov.gsa.dcoi.repository.UserRepository;
import gov.gsa.dcoi.security.SecurityUtils;
import gov.gsa.dcoi.security.User;

/**
 * Controller to manage Security
 *
 */
@RestController
@RequestMapping("/setting")
public class AdminController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	SecurityUtils securityUtils;
	
	/**
	 * Get the current logged in user account
	 */
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	@ResponseBody
	public User getUserAccount() {
		User user = userRepository.findByEmailAddress(securityUtils.getCurrentLogin());
		user.setPassword(null);
		return user;
	}
	
	/**
	 * Show the Admin/Settings page
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAllUsers();
		return users;
	}
	
	/**
	 * Method to logout user. 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		securityUtils.logout(request, response);
	}

}
