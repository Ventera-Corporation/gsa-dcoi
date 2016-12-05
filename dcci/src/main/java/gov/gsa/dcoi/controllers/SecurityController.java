package gov.gsa.dcoi.controllers;

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

@RestController
@RequestMapping("/security")
public class SecurityController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	SecurityUtils securityUtils;
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public @ResponseBody User getUserAccount() {
		User user = userRepository.findByEmailAddress(securityUtils.getCurrentLogin());
		user.setPassword(null);
		return user;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public @ResponseBody void logout(HttpServletRequest request, HttpServletResponse response) {
		securityUtils.logout(request, response);
	}

}
