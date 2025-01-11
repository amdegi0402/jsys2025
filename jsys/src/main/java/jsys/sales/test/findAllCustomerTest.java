/**
 * findAllCustomerTest.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;

public class findAllCustomerTest {
	public static void main(String[] args) {
		Connection con = null;
		ArrayList<Customer> customerList = new ArrayList<>();
		try {
			// データベース接続の設定（適切なURL、ユーザー名、パスワードを設定してください）
			con = ConnectionManager.getConnection();
			// テスト対象のCustomerDAOのインスタンスを作成
			CustomerDAO dao = new CustomerDAO(con);

			// 存在する顧客コードでのテスト
			customerList = dao.findAllCustomer();
			if (!customerList.isEmpty()) {
				for (Customer customer : customerList) {
					System.out.println();
					System.out.println(
							"Customer found: " + customer.getCustCode());
					System.out.println(
							"Customer found: " + customer.getCustName());
					System.out.println(
							"Customer found: " + customer.getTelNo());
					System.out.println(
							"Customer found: " + customer.getPostalCode());
					System.out.println(
							"Customer found: " + customer.getAddress());
					System.out.println(
							"Customer found: " + customer.getDiscountRate());
				}

			} else {
				System.out.println("Customer not found.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
