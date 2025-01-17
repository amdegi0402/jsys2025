
package jsys.sales.web;

import jakarta.servlet.http.HttpServletRequest;
import jsys.common.BusinessException;
import jsys.common.SystemException;
import jsys.sales.entity.Customer;
import jsys.sales.logic.CustomerChangeLogic;


public class CustomerChangeFindAction implements ActionIF {

	/**
	 * @see jsys.sales.web.ActionIF#execute(jakarta.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		System.out.println("(CustomerChangeFindAction18)OK");
		String page = "V204_01CustomerChangeView.jsp";
		Customer customer = null;
		try {
			//パラメータ取得
			String custCode = request.getParameter("custCode");
			//String custCode = "001";
			if(custCode == null || custCode.equals("")) {
				throw new BusinessException("得意先コードが入力されていません");
			}
			// logic呼び出し
			CustomerChangeLogic logic = new CustomerChangeLogic();
			customer = logic.findCustomer(custCode);
			request.setAttribute("customer", customer);

		} catch (BusinessException e) {
			// 業務エラー（エラーメッセージをリクエストスコープに格納）
			request.setAttribute("errorMessage", e.getMessage());
			// 遷移先ページ
			page = "V204_01CustomerChangeView.jsp";
		} catch (SystemException e) {
			// システムエラー（エラーメッセージをリクエストスコープに格納）
			request.setAttribute("errorMessage", e.getMessage());
			// 遷移先ページ名の設定
			page = "V901_01SystemErrorPage.jsp";
		}
		return page;
	}

}
