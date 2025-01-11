/**
 * CustomerListLogic.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.common.BusinessException;
import jsys.common.SystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
public class DelCustomerListLogic{

	public ArrayList<Customer> findDelAllCustomer()  throws BusinessException,SystemException{
		Connection con = null;
		ArrayList<Customer> customerList = new ArrayList<>();

		try {
			// コネクション取得
			con = ConnectionManager.getConnection();
			// dao生成
			CustomerDAO dao = new CustomerDAO(con);
			// findAllCustomerメソッドを呼び出し結果を受け取る
			customerList = dao.findDelAllCustomer();
			// 結果がnullであれば業務エラーを発生させる
			if (customerList.isEmpty()) {
				throw new BusinessException("得意先が見つかりません");
			}
		} catch (SQLException e) {
			throw new SystemException("システムエラーが発生しました。サービス管理者にお問い合わせください。");
		}
		return customerList;

	}
}
