
package jsys.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.sales.entity.Customer;
import jsys.sales.entity.OrderTotalByCustomer;
import jsys.sales.entity.OrderTotalByItem;

public class CustomerNumberingDAO {
	private Connection con = null;

	public CustomerNumberingDAO(Connection con) {
		this.con = con;
	}

	public int findCustomerCode() throws SQLException {
		String sql
				= "SELECT customer_code FROM customer_numbering";
		PreparedStatement stmt = null;
		ResultSet res = null;
		int num = 0;
		try {
			// DBに渡すsqlを格納
			stmt = con.prepareStatement(sql);
			// DB空の結果を受け取る
			res = stmt.executeQuery();
			// 結果をエンティティに格納
			if (res.next()) {
		     num = res.getInt("customer_code");
			}
		} finally {
			if (res != null)
				res.close();
			if (stmt != null)
				stmt.close();
		}
		return num;

	}

	public boolean updateCustomer() throws SQLException {
		String sql
				= "UPDATE customer_numbering SET customer_code = customer_code+1";
		PreparedStatement stmt = null;
		boolean result = false;

		try {
			// DBに渡すsqlを格納
			stmt = con.prepareStatement(sql);
			// DB空の結果を受け取る
			int count = stmt.executeUpdate();
			System.out.println("(customerNumberingDAO) count: " + count);
			if (count != 0) {
				result = true;
			}
		} finally {
			if (stmt != null)
				stmt.close();
		}
		return result;

	}

}
