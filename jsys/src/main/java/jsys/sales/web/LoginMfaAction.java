
package jsys.sales.web;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jsys.common.BusinessException;
import jsys.common.SystemException;
import jsys.sales.entity.Employee;

public class LoginMfaAction implements ActionIF {
	/**
	 * @see jsys.sales.web.ActionIF#execute(jakarta.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		String page = "V101_02MainMenu.jsp";
		Employee employee = null;

		// ユーザーが入力したコードを取得
		String inputOTP = request.getParameter("otp");
		// セッション取得
		HttpSession session = request.getSession();
		// 正しいコードを取得
		String storedOTP = (String) session.getAttribute("otp");
		// 認証コードの発行時間を取得
		Long timestamp = (Long) session.getAttribute("otpTimestamp");

		try {
			// 5分間の有効期限をチェック(ミリ秒（1秒 = 1000ミリ秒）
			if (System.currentTimeMillis() - timestamp > 300000) {
				// 遷移先ページ
				page = "V100_01Login.jsp";
				throw new BusinessException("有効期限が切れてます。ログインをやり直してください。");
			}
			// ユーザーが入力した認証コードが正しいかチェック
			if (storedOTP != null && storedOTP.equals(inputOTP)) {
				// 仮のログインユーザ情報を取得し、をログイン済みユーザーであるemployeeに格納
				employee = (Employee) session.getAttribute("tmpEmployee");
				request.setAttribute("employee", employee);
			} else {
				// 遷移先ページ
				page = "V100_03LoginMailAuth.jsp";
				throw new BusinessException("無効なコードです。");
			}

		} catch (BusinessException e) {
			// 業務エラー（エラーメッセージをリクエストスコープに格納）
			request.setAttribute("errorMessage", e.getMessage());
		}
		return page;
	}

}
