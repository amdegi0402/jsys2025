package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;
import jsys.sales.entity.Customer;

public class CustomerRegistTest {
	public static void main(String[] args) {

		Connection con = null;

		try {
			// コネクション取得
			con = ConnectionManager.getConnection();
			// テスト用の顧客データを作成
	        Customer testCustomer = new Customer();
	        testCustomer.setCustCode("KA0016");
	        testCustomer.setCustName("テスト顧客");
	        testCustomer.setTelNo("123-456-7890");
	        testCustomer.setPostalCode("123-4567");
	        testCustomer.setAddress("テスト住所");
	        testCustomer.setDiscountRate(10);

	        // 顧客データを登録
	        CustomerDAO dao = new CustomerDAO(con);
	        Customer resultCustomer = dao.insertCustomer(testCustomer);

	        System.out.println("custCode: " + resultCustomer.getCustCode());
	        System.out.println("custName: " + resultCustomer.getCustName());
	        System.out.println("telNo: " + resultCustomer.getTelNo());
	        System.out.println("postalCode: " + resultCustomer.getPostalCode());
	        System.out.println("address: " + resultCustomer.getAddress());
	        System.out.println("discountRate: " + resultCustomer.getDiscountRate());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
