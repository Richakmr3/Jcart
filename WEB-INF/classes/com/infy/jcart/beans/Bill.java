package com.infy.jcart.beans;

import java.util.Calendar;


public class Bill {
	
	private Integer productId;
	private Double discount;
	private String productName;
	private String additionalDiscount;
	private Double price;
	private Calendar offerStartDate;
	private Calendar offerEndDate;
	private Integer offercode;
	private String offerDetails;
	private Double offerDiscount;
	private Double discountedPrice;
	
	
	public Double getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(Double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	public Double getOfferDiscount() {
		return offerDiscount;
	}
	public void setOfferDiscount(Double offerDiscount) {
		this.offerDiscount = offerDiscount;
	}
	public String getOfferDetails() {
		return offerDetails;
	}
	public void setOfferDetails(String offerDetails) {
		this.offerDetails = offerDetails;
	}
	public Integer getOffercode() {
		return offercode;
	}
	public void setOffercode(Integer offercode) {
		this.offercode = offercode;
	}
	public Calendar getOfferStartDate() {
		return offerStartDate;
	}
	public void setOfferStartDate(Calendar offerStartDate) {
		this.offerStartDate = offerStartDate;
	}
	public Calendar getOfferEndDate() {
		return offerEndDate;
	}
	public void setOfferEndDate(Calendar offerEndDate) {
		this.offerEndDate = offerEndDate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getAdditionalDiscount() {
		return additionalDiscount;
	}
	public void setAdditionalDiscount(String additionalDiscount) {
		this.additionalDiscount = additionalDiscount;
	}
	
	
	

}
