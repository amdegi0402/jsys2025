
package jsys.sales.web;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jsys.common.BusinessException;
import jsys.common.SystemException;
import jsys.sales.entity.Customer;
import jsys.sales.logic.CustomerRegistLogic;


public class CustomerRegistAction implements ActionIF {

	/**
	 * @see jsys.sales.web.ActionIF#execute(jakarta.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		String page = "V202_02CustomerRegistResultView.jsp";
		ArrayList<String> errorMessageList = new ArrayList<>();
		Customer customer = null;
		try {
			//パラメータ取得
			String custName = request.getParameter("custName");
			String telNo = request.getParameter("telNo");
			String postalCode = request.getParameter("postalCode");
			String address = request.getParameter("address");
			String discountRate = request.getParameter("discountRate");

			if(custName == null || custName.equals("")) {
				errorMessageList.add("得意先名が入力されていません");
			}
			if(telNo == null || telNo.equals("")) {
				errorMessageList.add("電話番号が入力されていません");
			}
			if(postalCode == null || postalCode.equals("")) {
				errorMessageList.add("郵便番号が入力されていません");
			}
			if(address == null || address.equals("")) {
				errorMessageList.add("住所が入力されていません");
			}
			if(discountRate == null || discountRate.equals("")) {
				errorMessageList.add("割引率が入力されていません");
			}
			if(!errorMessageList.isEmpty()) {
				throw new BusinessException(errorMessageList);
			}
			customer = new Customer(custName, telNo, postalCode, address, Integer.parseInt(discountRate));
			// logic呼び出し
			CustomerRegistLogic logic = new CustomerRegistLogic();
			Customer resultCustomer = logic.registCustomer(customer);
			request.setAttribute("customer", resultCustomer);

		} catch (BusinessException e) {
			// 業務エラー（エラーメッセージをリクエストスコープに格納）
			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute("errorMessageList", e.getMessageList());
			// 遷移先ページ
			page = "V202_01CustomerRegistView.jsp";
		} catch (SystemException e) {
			// システムエラー（エラーメッセージをリクエストスコープに格納）
			request.setAttribute("errorMessage", e.getMessage());
			// 遷移先ページ名の設定
			page = "V901_01SystemErrorPage.jsp";
		}
		return page;
	}

}
