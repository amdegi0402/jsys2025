
package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.common.BusinessException;
import jsys.common.SystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;

public class RestoreCustomerLogic {
	public ArrayList<Customer> restoreCustomer(String[] custCodes)
			throws BusinessException, SystemException {
		Connection con = null;
		ArrayList<Customer> customerList = new ArrayList<>();
		boolean result = false;
		try {
			// コネクション取得
			con = ConnectionManager.getConnection();
			// 整合性を保つためにautocommit解除
			con.setAutoCommit(false);
			// dao生成
			CustomerDAO dao = new CustomerDAO(con);
			// 復元メソッドを呼ぶ
			for (String custCode : custCodes) {
				result = dao.restoreCustomer(custCode);
				if (result == false)
					throw new BusinessException("得意先の復元に失敗しました。");
				// 正常に処理が完了できていればコミットする
				con.commit();
			}
			// 復元した得意先情報を取得
			for (String custCode : custCodes) {
				customerList.add(dao.findCustomer(custCode));
			}

			// 得意先情報を取得
			// customer = dao.findCustomer(custCodes);
		} catch (SQLException e) {
			try {
				con.rollback();
				con.setAutoCommit(true);
				throw new SystemException("システムエラーが発生しました。管理者にお問い合わせください。");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return customerList;
	}
}
