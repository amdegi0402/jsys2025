
package jsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.CustomerDAO;

public class RestoreCustomerTest {
	public static void main(String[] args) {


        // テスト用のデータベース接続を作成
        Connection con = null;
        try {
            // ここでは、適切なJDBC URL, ユーザー名, パスワードを指定する必要があります
            con = ConnectionManager.getConnection();

            // テスト対象メソッドを呼び出す
            CustomerDAO dao = new CustomerDAO(con);
            boolean result = dao.restoreCustomer("KA0001");

            // 結果を確認
            if (result) {
                System.out.println("restoreCustomer: 成功");
            } else {
                System.out.println("restoreCustomer: 失敗");
            }

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
