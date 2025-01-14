
package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.common.BusinessException;
import jsys.common.SystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;

public class CustomerFindLogic {

	public Customer findCustomer(String custValue, String category)
			throws BusinessException, SystemException {
		Connection con = null;
		Customer customer = null;

		try {
			// コネクション取得
			con = ConnectionManager.getConnection();
			// dao生成
			CustomerDAO dao = new CustomerDAO(con);

			if(category.equals("custCode")) {
				// custCodeの文字列最後から２文字を抜き出す（あいまい検索を行うための処理）
				 String lastCode = custValue.substring(custValue.length() - 2);
				// findCustomerメソッドを呼び出し結果を受け取る
				customer = dao.findCustomer(lastCode);
			}else {
				// findCustomerメソッドを呼び出し結果を受け取る
				customer = dao.findCustomer(custValue);
			}

			// 結果がnullであれば業務エラーを発生させる
			if (customer == null) {
				throw new BusinessException("得意先が見つかりません");
			}
		} catch (SQLException e) {
			throw new SystemException("システムエラーが発生しました。サービス管理者にお問い合わせください。");
		}
		return customer;

	}
}
