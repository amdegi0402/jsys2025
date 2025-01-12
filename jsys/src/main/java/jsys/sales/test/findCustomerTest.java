
package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;

public class findCustomerTest {
	public static void main(String[] args) {
		Connection con = null;
		Customer customer = null;
		try {
			con = ConnectionManager.getConnection();
			// テスト対象のdao生成
			CustomerDAO dao = new CustomerDAO(con);
			customer = dao.findCustomer("KA0001");
			// 存在する顧客コードでのテスト
			if (customer != null) {
				System.out.println();
				System.out.println(
						"CustCode found: " + customer.getCustCode());
				System.out.println(
						"CustName found: " + customer.getCustName());
				System.out.println(
						"TelNo found: " + customer.getTelNo());
				System.out.println(
						"PostalCode found: " + customer.getPostalCode());
				System.out.println(
						"Address found: " + customer.getAddress());
				System.out.println(
						"Discount found: " + customer.getDiscountRate());
			} else {
				System.out.println("Customer not found.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
