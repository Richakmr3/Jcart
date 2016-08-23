package com.infy.jcart.entites;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="JCART_ORDERLOGTABLE")
public class OrderLogEntity {
	
	@Id
	@SequenceGenerator(name="orderID",sequenceName="db_orderID",allocationSize=1,initialValue=8012)
	@GeneratedValue(generator="orderID",strategy=GenerationType.SEQUENCE)
	private Integer orderId;
	private Integer paymentId;
	private String userId;
	private Character deliveryStatus;
	@Temporal(TemporalType.DATE)
	private Calendar orderDate;
	@Temporal(TemporalType.DATE)
	private Calendar shipmentDate;
	@Temporal(TemporalType.DATE)
	private Calendar deliveryDate;
	private Integer numOfProducts;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
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