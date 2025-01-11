/**
 * ActionIF.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsys.sales.web;

import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
public interface ActionIF {
	public String execute(HttpServletRequest request);
}
