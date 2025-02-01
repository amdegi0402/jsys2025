
package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.OrderDAO;
import jsys.sales.entity.Customer;
import jsys.sales.entity.OrderTotalByCustomer;

public class CreateOrderTotalListCustomerTest {
	public static void main(String[] args) {
		Connection con = null;
		ArrayList<OrderTotalByCustomer> customerList = new ArrayList<>();
		ArrayList<OrderTotalByCustomer> customerList2 = new ArrayList<>();
		String target = "2021-11";
		String target2 = "2022-";
		try {
			con = ConnectionManager.getConnection();
			// テスト対象のdao生成
			OrderDAO dao = new OrderDAO(con);
			customerList = dao.CreateOrderTotalListByCustomer(target);
			// 存在する顧客コードでのテスト
			System.out.println("size: " + customerList.size());
			System.out.println("月別---------------------------------------------");
			if (!customerList.isEmpty()) {
				for(OrderTotalByCustomer customer : customerList) {
					System.out.println("custCode: " + customer.getCustCode());
					System.out.println("custName: " + customer.getCustName());
					System.out.println("totalPrice: " + customer.getTotalPrice());
					System.out.println();
				}
			}else {
				System.out.println("値が入っていません");
			}

			customerList2 = dao.CreateOrderTotalListByCustomer(target2);
			System.out.println("size: " + customerList2.size());
			System.out.println("年別---------------------------------------------");
			if (!customerList2.isEmpty()) {
				for(OrderTotalByCustomer customer : customerList2) {
					System.out.println("custCode: " + customer.getCustCode());
					System.out.println("custName: " + customer.getCustName());
					System.out.println("totalPrice: " + customer.getTotalPrice());
					System.out.println();
				}
			}else {
				System.out.println("値が入っていません");
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
