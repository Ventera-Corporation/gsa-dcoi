package gov.gsa.dcoi.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * Class to handle Request Denial.
 */
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {
	
	@Autowired
	SecurityUtils securityUtils;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception)
			throws IOException, ServletException {
		securityUtils.sendError(response, exception, HttpServletResponse.SC_FORBIDDEN);
	}
}
