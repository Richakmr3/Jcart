package com.infy.jcart.beans;

import java.util.Calendar;

public class NewProducts {
	
	private Integer productId;
	private Double price;
	private Double discount;
	private Integer offerCode;
	private Calendar productAddDate;
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Integer getOfferCode() {
		return offerCode;
	}
	public void setOfferCode(Integer offerCode) {
		this.offerCode = offerCode;
	}
	public Calendar getProductAddDate() {
		return productAddDate;
	}
	public void setProductAddDate(Calendar productAddDate) {
		this.productAddDate = productAddDate;
	}
		
}