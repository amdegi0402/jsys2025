
package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.common.BusinessException;
import jsys.common.SystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;

public class CustomerChangeLogic {

	public Customer findCustomer(String custCode)
			throws BusinessException, SystemException {
		Connection con = null;
		Customer customer = null;

		try {
			// コネクション取得
			con = ConnectionManager.getConnection();
			// dao生成
			CustomerDAO dao = new CustomerDAO(con);

			// findCustomerメソッドを呼び出し結果を受け取る
			customer = dao.findCustomer(custCode);
			// 結果がnullであれば業務エラーを発生させる
			if (customer == null) {
				throw new BusinessException("得意先が見つかりません");
			}
		} catch (SQLException e) {
			throw new SystemException("システムエラーが発生しました。サービス管理者にお問い合わせください。");
		}
		return customer;

	}

	public Customer changeCustomer(Customer customer)
			throws BusinessException, SystemException {
		Connection con = null;
		boolean result = false;

		try {
			// コネクション取得
			con = ConnectionManager.getConnection();
			// autoCommit解除
			con.setAutoCommit(false);
			// dao生成
			CustomerDAO dao = new CustomerDAO(con);
			// changeCustomerメソッドを呼び出しDBの得意先データを更新
			result = dao.updateCustomer(customer);

			// 結果がfalseであれば業務エラーを発生させる
			if (result == false) {
				throw new BusinessException("得意先情報の更新に失敗しました。");
			}
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new SystemException("システムエラーが発生しました。管理者にお問い合わせください。");
		}
		return customer;
	}
}
