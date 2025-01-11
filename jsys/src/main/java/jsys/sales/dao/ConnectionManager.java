/**
 * ConnectionManager.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jsys.sales.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author FLM
 * @version 1.0.0
 */
public class ConnectionManager {
	// データベース接続URL
	private static final String URL = "jdbc:mysql://localhost:3306/jsysdb";
	// UserName
	private static final String USER = "mysql";
	// Password
	private static final String PASSWORD = "mysql";

	// データベースの接続を取得
	public static Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		return con;
	}
}
