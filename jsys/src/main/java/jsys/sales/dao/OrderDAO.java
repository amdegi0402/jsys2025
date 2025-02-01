
package jsys.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.sales.entity.Employee;
import jsys.sales.entity.OrderTotalByCustomer;

public class OrderDAO {
	private Connection con = null;

	public OrderDAO(Connection con) {
		this.con = con;
	}

	// 集計
	public ArrayList<OrderTotalByCustomer> CreateOrderTotalListByCustomer(String target) throws SQLException {
		String sql
				= "SELECT A.customer_code, A.customer_name, B.total_price FROM customer A INNER JOIN orders B ON A.customer_code=B.customer_code WHERE B.order_date LIKE ?";
		ArrayList<OrderTotalByCustomer> totalByCustomerList = new ArrayList<>();
		OrderTotalByCustomer totalByCustomer = null;
		PreparedStatement stmt = null;
		ResultSet res = null;

		try {
			// DBに渡すsqlを格納
			stmt = con.prepareStatement(sql);
			// 受け取ったcustCodeをセット
			stmt.setString(1, target + "%");
			// DB空の結果を受け取る
			res = stmt.executeQuery();
			// 結果をエンティティに格納
			while (res.next()) {
				totalByCustomer = new OrderTotalByCustomer(res.getString("customer_code"),
						res.getString("customer_name"),
						res.getInt("total_price"));
				totalByCustomerList.add(totalByCustomer);
			}
		} finally {
			if (res != null)
				res.close();
			if (stmt != null)
				stmt.close();
		}
		return totalByCustomerList;

	}

}
