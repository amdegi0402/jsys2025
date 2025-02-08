
package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.common.BusinessException;
import jsys.common.SystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.dao.CustomerNumberingDAO;
import jsys.sales.entity.Customer;

public class CustomerRegistLogic {

	public Customer registCustomer(Customer customer)
			throws BusinessException, SystemException {
		Connection con = null;
		int latestCustomerCode = 0;
		Customer resultCustomer = null;
		try {

			// コネクション取得
			con = ConnectionManager.getConnection();
			// autoCommit解除
			con.setAutoCommit(false);
			// dao生成
			CustomerNumberingDAO custNumDAO = new CustomerNumberingDAO(con);
			CustomerDAO custDAO = new CustomerDAO(con);
			// findCustomerメソッドを呼び出し結果を受け取る
			latestCustomerCode = custNumDAO.findCustomerCode();
			//文字列にしてフォーマットを整える
			String custCode = "KA" + String.format("%04d", latestCustomerCode + 1);
			//Customerに得意先番号をセット
			customer.setCustCode(custCode);
			resultCustomer = custDAO.insertCustomer(customer);

			boolean updateCustomer = custNumDAO.updateCustomer();
			System.out.println("(logic)updateCustomer: " + updateCustomer);
			// 結果がfalseであれば業務エラーを発生させる
			if (updateCustomer == false) {
				try {
					con.rollback();
					con.setAutoCommit(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				throw new BusinessException("得意先が登録できません");
			}
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new SystemException("システムエラーが発生しました。サービス管理者にお問い合わせください。");
		}
		return resultCustomer;

	}


}
