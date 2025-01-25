
package jsys.sales.web;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jsys.common.BusinessException;
import jsys.common.SystemException;
import jsys.sales.entity.Employee;
import jsys.sales.logic.LoginLogic;

public class LoginAction implements ActionIF {
	/**
	 * @see jsys.sales.web.ActionIF#execute(jakarta.servlet.http.HttpServletRequest)
	 */
	@Override
	public String execute(HttpServletRequest request) {
		String page = "V101_01MainMenu.jsp";
		Employee employee = null;
		ArrayList<String> errorMessageList = new ArrayList<>();
		int count = 0;
		// セッション取得
		HttpSession session = request.getSession();
		// セッションからログイン試行履歴を取得
		ArrayList<Boolean> loginAttempts
				= (ArrayList<Boolean>) session.getAttribute("loginAttempts");
		Long lockTime = (Long) session.getAttribute("lockTime");
		try {
			// 現在時刻がロック解除時刻より前ならロック中
			// ロックチェック
			if (lockTime != null && System.currentTimeMillis() < lockTime) {
				errorMessageList.add("ログイン制限中");
				throw new BusinessException(errorMessageList);
			}
			if (System.currentTimeMillis() > lockTime) {
			    // ロック時間経過済みなのでロック解除
			    session.removeAttribute("lockTime");
			    session.removeAttribute("loginAttempts");
			    // または loginAttempts.clear();
			}

			// 初回アクセス時は新規リストを作成
			if (loginAttempts == null) {
				loginAttempts = new ArrayList<>();
				session.setAttribute("loginAttempts", loginAttempts);
			}

			String employeeNo = request.getParameter("employeeNo"); // 従業員番号
			String password = request.getParameter("password");// パスワード

			if (employeeNo == null || employeeNo.equals("")) {
				errorMessageList.add("従業員番号が入力されていません");
			}
			if (password == null || password.equals("")) {
				errorMessageList.add("パスワードが入力されていません");
			}
			if (!errorMessageList.isEmpty()) {
				throw new BusinessException(errorMessageList);
			}
			// logic呼び出し
			LoginLogic logic = new LoginLogic();
			employee = logic.login(employeeNo, password);
			loginAttempts.add(true);
			request.setAttribute("employee", employee);
		} catch (BusinessException e) {
			// ログイン失敗時
			loginAttempts.add(false);

			// 直近３回が失敗かどうか確認
			if (loginAttempts.size() >= 3) {
				ArrayList<Boolean> failCount = new ArrayList<>(
						loginAttempts.subList(loginAttempts.size() - 3,
								loginAttempts.size()));
				// falseをカウント
				for (boolean result : failCount) {
					if (!result) {
						count++;
					}
				}
				if (count == 3) {
					// ロック時間をセット
					session.setAttribute("lockTime",
							System.currentTimeMillis() + (5 * 60 * 1000)); // 現在時刻(13:00:00のミリ秒+5分(300000ミリ秒)
				}
			}
			// 業務エラー（エラーメッセージをリクエストスコープに格納）
			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute("errorMessageList", e.getMessageList());
			// 遷移先ページ
			page = "V100_01Login.jsp";

		} catch (SystemException e) {
			// システムエラー（エラーメッセージをリクエストスコープに格納）
			request.setAttribute("errorMessage", e.getMessage());
			// 遷移先ページ名の設定
			page = "V901_01SystemErrorPage.jsp";
		}
		return page;
	}

}
