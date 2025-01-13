
package jsys.sales.entity;

import java.io.Serializable;

public class Customer implements Serializable{
	private String custCode;
	private String custName;
	private String telNo;
	private String postalCode;
	private String address;
	private int discountRate;

	public Customer() {}
	/**
	 * @param custCode
	 * @param custName
	 * @param telNo
	 * @param postalCode
	 * @param address
	 * @param discountRate
	 */
	public Customer(String custCode, String custName, String telNo,
			String postalCode, String address, int discountRate) {
		super();
		this.custCode = custCode;
		this.custName = custName;
		this.telNo = telNo;
		this.postalCode = postalCode;
		this.address = address;
		this.discountRate = discountRate;
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
	 * telNoのGetter
	 * @return telNo
	 */
	public String getTelNo() {
		return telNo;
	}
	/**
	 * telNoのSetter
	 * @param telNo
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	/**
	 * postalCodeのGetter
	 * @return postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * postalCodeのSetter
	 * @param postalCode
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * addressのGetter
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * addressのSetter
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * discountRateのGetter
	 * @return discountRate
	 */
	public int getDiscountRate() {
		return discountRate;
	}
	/**
	 * discountRateのSetter
	 * @param discountRate
	 */
	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}



}
