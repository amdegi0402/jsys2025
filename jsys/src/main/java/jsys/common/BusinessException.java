
package jsys.common;

import java.util.ArrayList;


public class BusinessException extends Exception {

	ArrayList<String> messageList = new ArrayList<>();

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(ArrayList<String> messageList) {
		this.messageList = messageList;
	}

	/**
	 * messageListのGetter
	 * @return messageList
	 */
	public ArrayList<String> getMessageList() {
		return messageList;
	}

	/**
	 * messageListのSetter
	 * @param messageList
	 */
	public void setMessageList(ArrayList<String> messageList) {
		this.messageList = messageList;
	}

}
