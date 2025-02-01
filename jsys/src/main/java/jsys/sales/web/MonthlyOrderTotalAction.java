
package jsys.sales.web;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jsys.common.BusinessException;
import jsys.common.SystemException;
import jsys.sales.entity.OrderTotalByCustomer;
import jsys.sales.logic.MonthlyOrderTotalLogic;


public class MonthlyOrderTotalAction implements ActionIF {

	/**
	 * @see jsys.sales.web.ActionIF#execute(jakarta.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		String page = "V301_01MonthlyOrderTotalView.jsp";
		ArrayList<OrderTotalByCustomer> orderTotal = new ArrayList<>();
		try {
			String strYear = request.getParameter("year"); //年を取得
			String strMonth = request.getParameter("month");//月を取得
			System.out.println("year: " + strYear);
			if(strYear== null || strYear.equals("")) {
				throw new BusinessException("年が選択されていません");
			}
			if(strMonth== null || strMonth.equals("")) {
				throw new BusinessException("月が選択されていません");
			}
			// logic呼び出し
			MonthlyOrderTotalLogic logic = new MonthlyOrderTotalLogic();
			orderTotal = logic.total(strYear, strMonth);


			request.setAttribute("orderTotal", orderTotal);
			request.setAttribute("sumTotal", logic.getSumTotal());

		} catch (BusinessException e) {
			// 業務エラー（エラーメッセージをリクエストスコープに格納）
			request.setAttribute("errorMessage", e.getMessage());
			// 遷移先ページ
			page = "V301_01MonthlyOrderTotalView.jsp";
		} catch (SystemException e) {
			// システムエラー（エラーメッセージをリクエストスコープに格納）
			request.setAttribute("errorMessage", e.getMessage());
			// 遷移先ページ名の設定
			page = "V901_01SystemErrorPage.jsp";
		}
		return page;
	}

}
