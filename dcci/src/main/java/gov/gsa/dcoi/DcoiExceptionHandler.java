package gov.gsa.dcoi;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Application level Exception Handler
 */
@ControllerAdvice
public class DcoiExceptionHandler {
	
	/**
	 * Create new DcoiException.
	 * @param message
	 * @return
	 */
	public static DcoiException throwDcoiException(String message) {
		return new DcoiException(message);
	}
	
}
