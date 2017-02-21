package gov.gsa.dcoi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Rest error response to send messages back to the front end
 */
public class DcoiRestErrorResponse {

	/**
	 *
	 */
	private boolean error = true;
	/**
	 *
	 */
	private List<DcoiRestMessage> messageList = new ArrayList<DcoiRestMessage>();
	/**
	 *
	 */
	private Map<String, String> messages = new HashMap<String, String>();

	/**
	 *
	 * @return
	 */
	public List<DcoiRestMessage> getMessageList() {
		return messageList;
	}

	/**
	 *
	 * @param messageList
	 */
	public void setMessageList(List<DcoiRestMessage> messageList) {
		this.messageList = messageList;
	}

	/**
	 * @return the messages
	 */
	public Map<String, String> getMessages() {
		return messages;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public void setMessages(Map<String, String> messages) {
		this.messages = messages;
	}

	/**
	 * @return the error
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(boolean error) {
		this.error = error;
	}

}
