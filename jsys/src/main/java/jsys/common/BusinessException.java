/**
 * BusinessException.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsys.common;

import java.util.ArrayList;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
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
