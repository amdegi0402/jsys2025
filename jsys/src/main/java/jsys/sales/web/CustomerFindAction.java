
package jsys.sales.web;

import jakarta.servlet.http.HttpServletRequest;
import jsys.common.BusinessException;
import jsys.common.SystemException;
import jsys.sales.entity.Customer;
import jsys.sales.logic.CustomerFindLogic;


public class CustomerFindAction implements ActionIF {

	/**
	 * @see jsys.sales.web.ActionIF#execute(jakarta.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		String page = "V201_01CustomerFindResultView.jsp";
		Customer customer = null;
		try {
			String custCode = request.getParameter("custCode");
			//String custCode = "001";
			if(custCode == null || custCode.equals("")) {
				throw new BusinessException("得意先コードが入力されていません");
			}
			// logic呼び出し
			CustomerFindLogic logic = new CustomerFindLogic();
			customer = logic.findCustomer(custCode);
			request.setAttribute("customer", customer);

		} catch (BusinessException e) {
			// 業務エラー（エラーメッセージをリクエストスコープに格納）
			request.setAttribute("errorMessage", e.getMessage());
			// 遷移先ページ
			page = "V201_01CustomerFindResultView.jsp";
		} catch (SystemException e) {
			// システムエラー（エラーメッセージをリクエストスコープに格納）
			request.setAttribute("errorMessage", e.getMessage());
			// 遷移先ページ名の設定
			page = "V901_01SystemErrorPage.jsp";
		}
		return page;
	}

}
