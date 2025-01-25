
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
			String custValue = request.getParameter("custValue"); //得意先コードもしくは得意先名
			String category = request.getParameter("category");//custCode or custName (radio)

			if(custValue== null || custValue.equals("")) {
				throw new BusinessException("得意先情報が入力されていません");
			}
			// logic呼び出し
			CustomerFindLogic logic = new CustomerFindLogic();
			if(category.equals("custCode")) {
				// custCodeの文字列最後から２文字を抜き出す（あいまい検索を行うための処理）
				 String suffixCode = custValue.substring(custValue.length() - 2);
				// findCustomerメソッドを呼び出しIDあいまい検索の結果を受け取る
				customer = logic.findCustomer(suffixCode);
			}else {
				// findCustomerメソッドを呼び出し名前検索の結果を受け取る
				customer = logic.findCustomer(custValue);
			}

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
