/**
 * SystemException.java
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
public class SystemException extends Exception{

	ArrayList<String> messageList = new ArrayList<>();

	public SystemException(String message) {
		super(message);
	}
}
