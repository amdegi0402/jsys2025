
package jsys.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsys.sales.entity.Customer;
import jsys.sales.entity.Employee;

public class EmployeeDAO {
	private Connection con = null;

	public EmployeeDAO(Connection con) {
		this.con = con;
	}

	// ログイン情報確認
	public Employee findEmployee(String employeeNo, String password) throws SQLException {
		String sql
				= "SELECT employee_no, employee_name, password FROM employee WHERE employee_no=? AND password=?";
		Employee employee = null;
		PreparedStatement stmt = null;
		ResultSet res = null;

		try {
			// DBに渡すsqlを格納
			stmt = con.prepareStatement(sql);
			// 受け取ったcustCodeをセット
			stmt.setString(1, employeeNo);
			stmt.setString(2, password);
			// DB空の結果を受け取る
			res = stmt.executeQuery();
			// 結果をエンティティに格納
			if (res.next()) {
				employee = new Employee(res.getString("employee_no"),
						res.getString("employee_name"),
						res.getString("password"));
			}
		} finally {
			if (res != null)
				res.close();
			if (stmt != null)
				stmt.close();
		}
		return employee;

	}

}
