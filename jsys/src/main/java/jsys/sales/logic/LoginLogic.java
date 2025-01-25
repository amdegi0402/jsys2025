
package jsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;

import jsys.common.BusinessException;
import jsys.common.SystemException;
import jsys.sales.dao.ConnectionManager;
import jsys.sales.dao.EmployeeDAO;
import jsys.sales.entity.Customer;
import jsys.sales.entity.Employee;

public class LoginLogic {

	public Employee login(String employeeNo, String password)
			throws BusinessException, SystemException {
		Connection con = null;
		Employee employee = null;

		try {
			// コネクション取得
			con = ConnectionManager.getConnection();
			// dao生成
			EmployeeDAO dao = new EmployeeDAO(con);
			employee = dao.findEmployee(employeeNo, password);
			// 結果がnullであれば業務エラーを発生させる
			if (employee == null) {
				throw new BusinessException("ログインに失敗しました。");
			}
		} catch (SQLException e) {
			throw new SystemException("システムエラーが発生しました。サービス管理者にお問い合わせください。");
		}
		return employee;

	}
}
