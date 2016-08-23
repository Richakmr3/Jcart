package com.infy.jcart.beans;

import java.util.Calendar;

public class OrderLog {
	
	private Integer orderId;
	private Integer paymentId;
	private String userId;
	private Character deliveryStatus;
	private Calendar orderDate;
	private Calendar shipmentDate;
	private Calendar deliveryDate;
	private Integer numOfProducts;
	private Boolean selected;
	
	public Boolean isSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Character getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(Character deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public Calendar getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Calendar orderDate) {
		this.orderDate = orderDate;
	}
	public Calendar getShipmentDate() {
		return shipmentDate;
	}
	public void setShipmentDate(Calendar shipmentDate) {
		this.shipmentDate = shipmentDate;
	}
	public Calendar getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Calendar deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Integer getNumOfProducts() {
		return numOfProducts;
	}
	public void setNumOfProducts(Integer numOfProducts) {
		this.numOfProducts = numOfProducts;
	}
	
}