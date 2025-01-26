
package jsys.sales.web;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = { "/jsysF" })
public class FrontController extends HttpServlet {
	/**
	 * @see jakarta.servlet.http.HttpServlet#doPost(jakarta.servlet.http.HttpServletRequest,
	 *          jakarta.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//super.doPost(req, resp);

		// page path
		String path = "/WEB-INF/jsp/";
		String page = null;
		ActionIF action = null;
		// buttonId取得
		String buttonId = req.getParameter("buttonId");
		// 初回アクセス処理
		if (buttonId == null || buttonId.equals("")) {
			buttonId = "V205_01";
		}

		// 遷移先ページ
		switch (buttonId) {
			case "V100_01":// ログイン
				page = "V100_01Login.jsp";
				break;
			case "V101_01":// ログイン
				action = new LoginAction();
				page = action.execute(req);
				break;
			case "V201_01":// 得意先検索初期画面
				page = "V201_01CustomerFindView.jsp";
				break;
			case "V201_02":// 得意先検索
				action = new CustomerFindAction();
				page = action.execute(req);
				break;
			case "V205_01":// 一覧検索
				action = new CustomerListAction();
				page = action.execute(req);
				break;
			case "V206_01":// 削除済み一覧検索
				action = new DelCustomerListAction();
				page = action.execute(req);
				break;
			case "V207_01":// 得意先復元
				action = new RestoreCustomerAction();
				page = action.execute(req);
				break;
			case "V203_01":// 得意先削除(検索）
				page = "V203_01CustomerDeleteView.jsp";
				break;
			case "V203_02":// 得意先削除（削除）
				action = new CustomerDeleteFindAction();
				page = action.execute(req);
				break;
			case "V203_03":// 得意先削除（結果）
				action = new CustomerDeleteExecuteAction();
				page = action.execute(req);
				break;
			case "V204_01":// 得意先更新（検索）
				page = "V204_01CustomerChangeView.jsp";
				break;
			case "V204_02":// 得意先更新（更新）
				action = new CustomerChangeFindAction();
				page = action.execute(req);
				break;
			case "V204_03":// 得意先更新（結果）
				action = new CustomerChangeExecuteAction();
				page = action.execute(req);
				break;
		}

		// 結果画面に転送
		RequestDispatcher rd = req.getRequestDispatcher(path + page);
		rd.forward(req, resp);
	}

	/**
	 * @see jakarta.servlet.http.HttpServlet#doGet(jakarta.servlet.http.HttpServletRequest,
	 *          jakarta.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		doPost(req, resp);
	}

}
