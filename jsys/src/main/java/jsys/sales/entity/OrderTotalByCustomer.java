
package jsys.sales.entity;

import java.io.Serializable;

public class OrderTotalByCustomer implements Serializable {
	private String custCode;
	private String custName;
	private int totalPrice;

	public OrderTotalByCustomer() {}

	/**
	 * @param custCode
	 * @param custName
	 * @param totalPrice
	 */
	public OrderTotalByCustomer(String custCode, String custName, int totalPrice) {
		super();
		this.custCode = custCode;
		this.custName = custName;
		this.totalPrice = totalPrice;
	}

	/**
	 * custCodeのGetter
	 * @return custCode
	 */
	public String getCustCode() {
		return custCode;
	}

	/**
	 * custCodeのSetter
	 * @param custCode
	 */
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	/**
	 * custNameのGetter
	 * @return custName
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * custNameのSetter
	 * @param custName
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * totalPriceのGetter
	 * @return totalPrice
	 */
	public int getTotalPrice() {
		return totalPrice;
	}

	/**
	 * totalPriceのSetter
	 * @param totalPrice
	 */
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}
