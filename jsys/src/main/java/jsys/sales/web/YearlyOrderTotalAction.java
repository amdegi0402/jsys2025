
package jsys.sales.web;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jsys.common.BusinessException;
import jsys.common.SystemException;
import jsys.sales.entity.OrderTotalByCustomer;
import jsys.sales.logic.YearlyOrderTotalLogic;


public class YearlyOrderTotalAction implements ActionIF {

	/**
	 * @see jsys.sales.web.ActionIF#execute(jakarta.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		String page = "V302_01YearlyOrderTotalView.jsp";
		ArrayList<OrderTotalByCustomer> orderTotal = new ArrayList<>();
		try {
			String strYear = request.getParameter("year"); //年を取得
			if(strYear== null || strYear.equals("")) {
				throw new BusinessException("年が選択されていません");
			}
			// logic呼び出し
			YearlyOrderTotalLogic logic = new YearlyOrderTotalLogic();
			orderTotal = logic.total(strYear);
			request.setAttribute("orderTotal", orderTotal);
			request.setAttribute("sumTotal", logic.getSumTotal());

		} catch (BusinessException e) {
			// 業務エラー（エラーメッセージをリクエストスコープに格納）
			request.setAttribute("errorMessage", e.getMessage());
			// 遷移先ページ
			page = "V302_01YearlyOrderTotalView.jsp";
		} catch (SystemException e) {
			// システムエラー（エラーメッセージをリクエストスコープに格納）
			request.setAttribute("errorMessage", e.getMessage());
			// 遷移先ページ名の設定
			page = "V901_01SystemErrorPage.jsp";
		}
		return page;
	}

}
