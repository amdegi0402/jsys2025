
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
			buttonId = "V206_01";
		}

		// 遷移先ページ
		switch (buttonId) {
			case "V101_01":// 初期値
				page = "V205_01CustomerListView.jsp";
				break;
			case "V205_01":// 一覧検索
				action = new CustomerListAction();
				page = action.execute(req);
			case "V206_01":// 削除済み一覧検索
				action = new DelCustomerListAction();
				page = action.execute(req);
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
