package gov.gsa.dcoi;

/**
 * Rest error message to hold information about the errors that occur from
 * validation
 */
public class DcoiRestMessage {
	/**
	 *
	 */
	private String key = null;
	/**
	 *
	 */
	private String message = null;

	/**
	 *
	 * @param message
	 */
	public DcoiRestMessage(String message) {
		this.message = message;
	}

	/**
	 * @param key
	 * @param message
	 */
	public DcoiRestMessage(String key, String message) {
		this.key = key;
		this.message = message;
	}

	/**
	 * @param key
	 * @param message
	 */
	public DcoiRestMessage(Integer key, String message) {
		this.key = key + "";
		this.message = message;
	}

	/**
	 *
	 * @return
	 */
	public String getKey() {
		return key;
	}

	/**
	 *
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 *
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 *
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Overriding Object's toString method
	 */
	@Override
	public String toString() {
		return "key:" + key + " message:" + message;
	}

}
