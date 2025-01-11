/**
 * CustomerListAction.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsys.sales.web;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jsys.common.BusinessException;
import jsys.common.SystemException;
import jsys.sales.entity.Customer;
import jsys.sales.logic.DelCustomerListLogic;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
public class DelCustomerListAction implements ActionIF {

	/**
	 * @see jsys.sales.web.ActionIF#execute(jakarta.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		String page = "V206_01DelCustomerListView.jsp";
		ArrayList<Customer> customerList = new ArrayList<>();
		try {
			// logic呼び出し
			DelCustomerListLogic logic = new DelCustomerListLogic();
			customerList = logic.findDelAllCustomer();
			request.setAttribute("customerList", customerList);

		} catch (BusinessException e) {
			// 業務エラー（エラーメッセージをリクエストスコープに格納）
			request.setAttribute("errorMessage", e.getMessage());
			// 遷移先ページ
			page = "V206_01DelCustomerListView.jsp";
		} catch (SystemException e) {
			// システムエラー（エラーメッセージをリクエストスコープに格納）
			request.setAttribute("errorMessage", e.getMessage());
			// 遷移先ページ名の設定
			page = "V901_01SystemErrorPage.jsp";
		}
		return page;
	}

}
