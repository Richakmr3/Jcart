package com.infy.jcart.beans;

import java.util.Calendar;




public class Payment {
	
	private Integer paymentId;
	private String buyerId;
	private Integer numOfProducts;
	private Double netBill;
	private String paymentType;
	private Calendar purchaseDate;
	private Integer numOfMonths;
	
	
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public Integer getNumOfProducts() {
		return numOfProducts;
	}
	public void setNumOfProducts(Integer numOfProducts) {
		this.numOfProducts = numOfProducts;
	}
	public Double getNetBill() {
		return netBill;
	}
	public void setNetBill(Double netBill) {
		this.netBill = netBill;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public Calendar getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Calendar purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Integer getNumOfMonths() {
		return numOfMonths;
	}
	public void setNumOfMonths(Integer numOfMonths) {
		this.numOfMonths = numOfMonths;
	}
	
	
	
	
	
}