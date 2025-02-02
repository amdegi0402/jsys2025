
package jsys.sales.entity;

import java.io.Serializable;

public class OrderTotalByItem implements Serializable {
	private String itemCode;
	private String itemName;
	private int totalAmount;
	private int price;
	private int totalPrice;

	public OrderTotalByItem() {}

	/**
	 * @param itemCode
	 * @param itemName
	 * @param totalAmount
	 * @param price
	 * @param totalPrice
	 */
	public OrderTotalByItem(String itemCode, String itemName, int totalAmount,
			int price, int totalPrice) {
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.totalAmount = totalAmount;
		this.price = price;
		this.totalPrice = totalPrice;
	}

	/**
	 * itemCodeのGetter
	 * @return itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * itemCodeのSetter
	 * @param itemCode
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * itemNameのGetter
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * itemNameのSetter
	 * @param itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * totalAmountのGetter
	 * @return totalAmount
	 */
	public int getTotalAmount() {
		return totalAmount;
	}

	/**
	 * totalAmountのSetter
	 * @param totalAmount
	 */
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * priceのGetter
	 * @return price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * priceのSetter
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
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
