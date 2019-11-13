package com.hello.java.test;

import java.math.BigDecimal;

public class OrderLine extends OrderLineParent{

	private String desc;
	private Integer poLineId;
	private BigDecimal unitPrice;
	private BigDecimal quantity;
	private BigDecimal amount;
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getPoLineId() {
		return poLineId;
	}
	public void setPoLineId(Integer poLineId) {
		this.poLineId = poLineId;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "OrderLine [desc=" + desc + ", poLineId=" + poLineId + ", unitPrice=" + unitPrice + ", quantity="
				+ quantity + ", amount=" + amount + ", getId()=" + getId() + "]";
	}
	
	
	
}
