package com.infy.jcart.beans;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

public class PurchaseHistory {

	private Integer paymentId;
	private List<Integer> productId=new ArrayList<Integer>();
	private List<String> productName=new ArrayList<String>();
	private Double netBill;
	private Date dateOfPurchase;
	
	
	public List<String> getProductName() {
		return productName;
	}
	public void setProductName(List<String> productName) {
		this.productName = productName;
	}
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	
	public List<Integer> getProductId() {
		return productId;
	}
	public void setProductId(List<Integer> productId) {
		this.productId = productId;
	}
	
	public Double getNetBill() {
		return netBill;
	}
	public void setNetBill(Double netBill) {
		this.netBill = netBill;
	}
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	
	
	
	
}
