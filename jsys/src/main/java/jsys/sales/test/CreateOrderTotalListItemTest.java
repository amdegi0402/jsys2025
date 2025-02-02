
package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.OrderDAO;
import jsys.sales.entity.OrderTotalByCustomer;
import jsys.sales.entity.OrderTotalByItem;

public class CreateOrderTotalListItemTest {
	public static void main(String[] args) {
		Connection con = null;
		ArrayList<OrderTotalByItem> itemList = new ArrayList<>();
		String custCode = "KA0001";

		try {
			con = ConnectionManager.getConnection();
			// テスト対象のdao生成
			OrderDAO dao = new OrderDAO(con);
			itemList = dao.CreateOrderTotalListByItem(custCode);
			// 存在する顧客コードでのテスト
			System.out.println("size: " + itemList.size());
			if (!itemList.isEmpty()) {
				for (OrderTotalByItem item : itemList) {
					System.out.println("itemCode: " + item.getItemCode());
					System.out.println("itemName: " + item.getItemName());
					System.out.println("totalAmount: " + item.getTotalAmount());
					System.out.println("price: " + item.getPrice());
					System.out.println("totalPrice: " + item.getTotalPrice());
					System.out.println();
				}
			} else {
				System.out.println("値が入っていません");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
