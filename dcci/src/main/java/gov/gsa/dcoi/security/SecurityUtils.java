package gov.gsa.dcoi.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import gov.gsa.dcoi.controllers.SecurityController;
import gov.gsa.dcoi.repository.UserIdStoredProcedure;

/**
 * Utility class for Spring Security.
 */
@Service
public class SecurityUtils {

	private static final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	UserIdStoredProcedure userIdStoredProcedure;

	@Autowired
	SecurityController securityController;

	/**
	 * Helper method to set the current user id into the database so that it is
	 * tracked correctly during auditing
	 */
	public void setUserIdForAudit() {
		userIdStoredProcedure.setUserId(securityController.getUserAccount().getDcoiUserId());
	}

	/**
	 * Get the login of the current user.
	 */
	public String getCurrentLogin() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		String userName = null;

		if (authentication != null) {
			if (authentication.getPrincipal() instanceof UserDetails) {
				UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
				userName = springSecurityUser.getUsername();
			} else if (authentication.getPrincipal() instanceof String) {
				userName = (String) authentication.getPrincipal();
			}
		}

		return userName;
	}

	/**
	 * Logout user from Security Context
	 * 
	 * @param request
	 * @param response
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
	}

	/**
	 * Send Error
	 * 
	 * @param response
	 * @param exception
	 * @param status
	 * @throws IOException
	 */
	public void sendError(HttpServletResponse response, Exception exception, int status) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(status);
		PrintWriter writer = response.getWriter();
		writer.write(mapper.writeValueAsString(exception.getMessage()));
		writer.flush();
		writer.close();
	}

	/**
	 * Send Response
	 * 
	 * @param response
	 * @param status
	 * @param object
	 * @throws IOException
	 */
	public void sendResponse(HttpServletResponse response, int status, Object object) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(mapper.writeValueAsString(object));
		response.setStatus(status);
		writer.flush();
		writer.close();
	}

}