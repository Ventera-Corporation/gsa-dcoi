package gov.gsa.dcoi;

import java.util.ArrayList;
import java.util.List;

/**
 * Main DCOI Exception class.
 */
public class DcoiException extends RuntimeException {
	
	private static final long serialVersionUID = 1123959364459380067L;
	
	private String code;
	private String status;
	private List<String> errors = new ArrayList<String>();
	private String exceptionMessage;

	/**
	 * Default Constructor taking a Message 
	 * @param msg
	 */
	public DcoiException(String msg) {
		this.setExceptionMessage(msg);
	}

	/**
	 * Default Constructor expecting Code, Status and error
	 * @param code
	 * @param status
	 * @param error
	 */
	public DcoiException(String code, String status, String error) {
		this.code = code;
		this.status = status;
		this.errors.add(error);
	}

	/**
	 * Default Constructor expecting code, status and list of errors
	 * @param code
	 * @param status
	 * @param errors
	 */
	public DcoiException(String code, String status, List<String> errors) {
		this.code = code;
		this.status = status;
		this.errors = errors;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

}
