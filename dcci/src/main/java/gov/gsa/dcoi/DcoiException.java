package gov.gsa.dcoi;

import java.util.ArrayList;
import java.util.List;

public class DcoiException extends RuntimeException {
	
	private static final long serialVersionUID = 1123959364459380067L;
	
	private String code;
	private String status;
	private List<String> errors = new ArrayList<>();
	private String exceptionMessage;

	public DcoiException(String msg) {
		this.setExceptionMessage(msg);
	}

	public DcoiException(String code, String status, String error) {
		this.code = code;
		this.status = status;
		this.errors.add(error);
	}

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
