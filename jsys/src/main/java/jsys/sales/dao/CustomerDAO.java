
package jsys.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.sales.entity.Customer;

public class CustomerDAO {
	private Connection con = null;

	public CustomerDAO(Connection con) {
		this.con = con;
	}

	// 1件検索
	public Customer findCustomer(String custValue) throws SQLException {
		String sql
				= "SELECT customer_code, customer_name, customer_telno, customer_postalcode, customer_address, discount_rate FROM customer WHERE (customer_code LIKE ? OR customer_name=?) AND delete_flag=false";
		Customer customer = null;
		PreparedStatement stmt = null;
		ResultSet res = null;

		try {
			// DBに渡すsqlを格納
			stmt = con.prepareStatement(sql);
			// 受け取ったcustCodeをセット
			stmt.setString(1, "%" + custValue + "%");
			stmt.setString(2, custValue);
			// DB空の結果を受け取る
			res = stmt.executeQuery();
			// 結果をエンティティに格納
			if (res.next()) {
				customer = new Customer(res.getString("customer_code"),
						res.getString("customer_name"),
						res.getString("customer_telno"),
						res.getString("customer_postalcode"),
						res.getString("customer_address"),
						res.getInt("discount_rate"));
			}
		} finally {
			if (res != null)
				res.close();
			if (stmt != null)
				stmt.close();
		}
		return customer;

	}

	// 全件検索
	public ArrayList<Customer> findAllCustomer() throws SQLException {
		String sql
				= "SELECT customer_code, customer_name, customer_telno, customer_postalcode, customer_address, discount_rate FROM customer WHERE delete_flag=false";
		PreparedStatement stmt = null;
		ResultSet res = null;
		ArrayList<Customer> customerList = new ArrayList<>();

		try {
			// DBへ渡すSQLを格納
			stmt = con.prepareStatement(sql);
			// DBへアクセスして結果を受け取る
			res = stmt.executeQuery();
			// 結果をエンティティにセット
			while (res.next()) {
				Customer customer = new Customer(res.getString("customer_code"),
						res.getString("customer_name"),
						res.getString("customer_telno"),
						res.getString("customer_postalcode"),
						res.getString("customer_address"),
						res.getInt("discount_rate"));
				customerList.add(customer);
			}
		} finally {
			if (res != null)
				res.close();
			if (stmt != null)
				stmt.close();
		}
		return customerList;
	}

	// 削除済み全件検索
	public ArrayList<Customer> findDelAllCustomer() throws SQLException {
		String sql
				= "SELECT customer_code, customer_name, customer_telno, customer_postalcode, customer_address, discount_rate FROM customer WHERE delete_flag=true";
		PreparedStatement stmt = null;
		ResultSet res = null;
		ArrayList<Customer> customerList = new ArrayList<>();

		try {
			// DBへ渡すSQLを格納
			stmt = con.prepareStatement(sql);
			// DBへアクセスして結果を受け取る
			res = stmt.executeQuery();
			// 結果をエンティティにセット
			while (res.next()) {
				Customer customer = new Customer(res.getString("customer_code"),
						res.getString("customer_name"),
						res.getString("customer_telno"),
						res.getString("customer_postalcode"),
						res.getString("customer_address"),
						res.getInt("discount_rate"));
				customerList.add(customer);
			}
		} finally {
			if (res != null)
				res.close();
			if (stmt != null)
				stmt.close();
		}
		return customerList;
	}

	// 復元
	public boolean restoreCustomer(String custCode) throws SQLException {
		String sql
				= "UPDATE customer SET delete_flag=false WHERE customer_code=?";
		boolean result = false;
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, custCode);

			int count = stmt.executeUpdate();
			if (count == 1)
				result = true;
		} finally {
			if (stmt != null)
				stmt.close();
		}
		return result;
	}

	// 削除
	public boolean deleteCustomer(Customer customer) throws SQLException {
		String sql
				= "UPDATE customer SET delete_flag=true WHERE customer_code=? AND delete_flag=false";
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			// DBへ渡すSQLを格納
			stmt = con.prepareStatement(sql);
			// 引数で受け取ったオブジェクトからコードをSQL文に挿入
			stmt.setString(1, customer.getCustCode());
			// DBから結果を受け取る
			int count = stmt.executeUpdate();
			// 結果が1であれば戻り値をtrue
			if (count == 1)
				result = true;
		} finally {
			if (stmt != null)
				stmt.close();
		}
		return result;
	}

	// 更新
	public boolean updateCustomer(Customer customer) throws SQLException {
		String sql
				= "UPDATE customer SET customer_name=?, customer_telno=?, customer_postalcode=?, customer_address=?, discount_rate=? WHERE customer_code=? AND delete_flag=false";
		PreparedStatement stmt = null;
		boolean result = false;
		try {
			// DBへ渡すSQLを格納
			stmt = con.prepareStatement(sql);
			// 引数で受け取ったオブジェクトからコードをSQL文に挿入
			stmt.setString(1, customer.getCustName());
			stmt.setString(2, customer.getTelNo());
			stmt.setString(3, customer.getPostalCode());
			stmt.setString(4, customer.getAddress());
			stmt.setInt(5, customer.getDiscountRate());
			stmt.setString(6, customer.getCustCode());
			// DBから結果を受け取る
			int count = stmt.executeUpdate();
			// 結果が1であれば戻り値をtrue
			if (count == 1)
				result = true;
		} finally {
			if (stmt != null)
				stmt.close();
		}
		return result;
	}
}
