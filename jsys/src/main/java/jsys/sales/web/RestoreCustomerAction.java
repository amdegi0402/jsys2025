
package jsys.sales.web;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jsys.common.BusinessException;
import jsys.common.SystemException;
import jsys.sales.entity.Customer;
import jsys.sales.logic.RestoreCustomerLogic;

public class RestoreCustomerAction implements ActionIF {

	/**
	 * @see jsys.sales.web.ActionIF#execute(jakarta.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		// 遷移先ページ
		String page = "V207_01RestoreCustomerResultView.jsp";
		ArrayList<Customer> customerList = new ArrayList<>();
		try {
			// パラメータ取得(custCodesは配列）
			String[] custCodes = request.getParameterValues("custCodes");
			if(custCodes == null) {
				throw new BusinessException("復元する得意先が見つかりません。");
			}
			//String custCode = "KA0001";
			RestoreCustomerLogic logic = new RestoreCustomerLogic();
			customerList = logic.restoreCustomer(custCodes);
			request.setAttribute("customerList", customerList);
			//request.setAttribute("customer", customer);
		} catch (BusinessException e) {
			request.setAttribute("errorMessage", e.getMessage());
		} catch (SystemException e) {
			request.setAttribute("errorMessage", e.getMessage());
			page = "V901_01SystemErrorPage.jsp";
		}
		return page;
	}
}
