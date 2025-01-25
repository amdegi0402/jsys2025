
package jsys.sales.entity;

import java.io.Serializable;

public class Employee implements Serializable {
	private String employeeNo;
	private String employeeName;
	private String password;

	public Employee() {}

	/**
	 * @param employeeNo
	 * @param employeeName
	 * @param password
	 */
	public Employee(String employeeNo, String employeeName, String password) {
		super();
		this.employeeNo = employeeNo;
		this.employeeName = employeeName;
		this.password = password;
	}

	/**
	 * employeeNoのGetter
	 * @return employeeNo
	 */
	public String getEmployeeNo() {
		return employeeNo;
	}

	/**
	 * employeeNoのSetter
	 * @param employeeNo
	 */
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	/**
	 * employeeNameのGetter
	 * @return employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * employeeNameのSetter
	 * @param employeeName
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * passwordのGetter
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * passwordのSetter
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
