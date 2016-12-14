package gov.gsa.dcoi;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Application level Exception Handler
 */
@ControllerAdvice
public class DcoiExceptionHandler {

	@Autowired
	MessageSource messageSource;

	public static final String ERROR_ALERT = "error.alert";

	/**
	 * Create new DcoiException.
	 * 
	 * @param message
	 * @return
	 */
	public static DcoiException throwDcoiException(String message) {
		return new DcoiException(message);
	}

	/**
	 * Handle validation exceptions
	 * @param manve
	 * @param request
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException manve, HttpServletRequest request) {
		DcoiRestErrorResponse response = new DcoiRestErrorResponse();
		List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
		for (FieldError fe : fieldErrors) {
			String message = response.getMessages().get(fe.getField());
			String newMessage = messageSource.getMessage(fe, null);
			String[] fieldNameArray = getFieldName(fe).split("\\.");
			// Get just the field name
			String fieldName = fieldNameArray[fieldNameArray.length - 1];
			if (message == null || message.isEmpty()) {
				response.getMessages().put(fieldName, newMessage);
			} else {
				response.getMessages().put(fieldName, message + "," + newMessage);
			}

		}
		DcoiRestMessage message = new DcoiRestMessage(ERROR_ALERT, messageSource.getMessage(ERROR_ALERT, null, null));
		response.getMessageList().add(message);
		// FrameworkExecutionContext.clearAllValues();
		return new ResponseEntity<>(response, null, HttpStatus.OK);
	}

	/**
	 * Get the field
	 * 
	 * @param fe
	 * @return
	 */
	private String getFieldName(FieldError fe) {
		if (fe.getObjectName().equals("generalInformationDto"))
			return "generalInformation." + fe.getField();
		return fe.getField();
	}
}
