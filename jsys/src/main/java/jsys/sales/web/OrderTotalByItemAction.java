
package jsys.sales.web;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jsys.common.BusinessException;
import jsys.common.SystemException;
import jsys.sales.entity.OrderTotalByItem;
import jsys.sales.logic.OrderTotalByItemLogic;


public class OrderTotalByItemAction implements ActionIF {

	/**
	 * @see jsys.sales.web.ActionIF#execute(jakarta.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		String page = "V303_01OrderTotalByItemView.jsp";
		ArrayList<OrderTotalByItem> orderList = new ArrayList<>();
		try {
			String custCode = request.getParameter("custCode"); //ユーザーが入力した得意先コード取得

			if(custCode== null || custCode.equals("")) {
				throw new BusinessException("得意先コードが入力されていません");
			}

			// logic呼び出し
			OrderTotalByItemLogic logic = new OrderTotalByItemLogic();
			orderList = logic.total(custCode);


			request.setAttribute("orderList", orderList);
			request.setAttribute("customer", logic.getCustomer());
			request.setAttribute("sumTotal", logic.getSumTotal());

		} catch (BusinessException e) {
			// 業務エラー（エラーメッセージをリクエストスコープに格納）
			request.setAttribute("errorMessage", e.getMessage());
			// 遷移先ページ
			page = "V303_01OrderTotalByItemView.jsp";
		} catch (SystemException e) {
			// システムエラー（エラーメッセージをリクエストスコープに格納）
			request.setAttribute("errorMessage", e.getMessage());
			// 遷移先ページ名の設定
			page = "V901_01SystemErrorPage.jsp";
		}
		return page;
	}

}
