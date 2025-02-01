
package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.common.BusinessException;
import jsys.common.SystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.OrderDAO;
import jsys.sales.entity.OrderTotalByCustomer;

public class MonthlyOrderTotalLogic {
	private int sumTotal = 0;
	public ArrayList<OrderTotalByCustomer> total(String year, String month)
			throws BusinessException, SystemException {

		ArrayList<OrderTotalByCustomer> orderList = new ArrayList<>();
		Connection con = null;

		try {
			// コネクション取得
			con = ConnectionManager.getConnection();
			// dao生成
			OrderDAO dao = new OrderDAO(con);
			String targetMonth = year + "-" + month;
			orderList = dao.CreateOrderTotalListByCustomer(targetMonth);


			// 結果がnullであれば業務エラーを発生させる
			if (orderList.isEmpty()) {
				throw new BusinessException("得意先が見つかりません");
			}

			//総計を求める
			for(OrderTotalByCustomer orderCustomer : orderList) {
				sumTotal += orderCustomer.getTotalPrice();
			}

		} catch (SQLException e) {
			throw new SystemException("システムエラーが発生しました。サービス管理者にお問い合わせください。");
		}
		return orderList;

	}
	/**
	 * sumTotalのGetter
	 * @return sumTotal
	 */
	public int getSumTotal() {
		return sumTotal;
	}
	/**
	 * sumTotalのSetter
	 * @param sumTotal
	 */
	public void setSumTotal(int sumTotal) {
		this.sumTotal = sumTotal;
	}
}
