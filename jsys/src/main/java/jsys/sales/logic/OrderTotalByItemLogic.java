
package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.common.BusinessException;
import jsys.common.SystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.dao.OrderDAO;
import jsys.sales.entity.Customer;
import jsys.sales.entity.OrderTotalByCustomer;
import jsys.sales.entity.OrderTotalByItem;

public class OrderTotalByItemLogic {
	private int sumTotal = 0;
	private Customer customer = null;
	public ArrayList<OrderTotalByItem> total(String custCode)
			throws BusinessException, SystemException {

		ArrayList<OrderTotalByItem> orderList = new ArrayList<>();
		Connection con = null;

		try {
			// コネクション取得
			con = ConnectionManager.getConnection();
			// dao生成
			CustomerDAO custDAO = new CustomerDAO(con);
			customer = custDAO.findIgnoreDeleteCustomer(custCode);
			OrderDAO orderDAO = new OrderDAO(con);
			orderList = orderDAO.CreateOrderTotalListByItem(custCode);

			// 結果がnullであれば業務エラーを発生させる
			if (orderList.isEmpty()) {
				throw new BusinessException("得意先が見つかりません");
			}

			//総計を求める
			for(OrderTotalByItem orderItem : orderList) {
				sumTotal += orderItem.getTotalPrice();
			}

		} catch (SQLException e) {
			throw new SystemException("システムエラーが発生しました。サービス管理者にお問い合わせください。");
		}
		return orderList;

	}
	/**
	 * customerのGetter
	 * @return customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * customerのSetter
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
