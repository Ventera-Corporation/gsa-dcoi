package gov.gsa.dcoi;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class DcoiExceptionHandler {

	public static DcoiException throwDcoiException(String message) {
		DcoiException dex = new DcoiException(message);
		return dex;
	}
	
}
