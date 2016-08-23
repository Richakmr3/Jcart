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
@Table(name="JCART_PAYMENT")
public class PaymentEntity {
	
	@Id
	@SequenceGenerator(name="paymentID",sequenceName="db_paymentID",allocationSize=1,initialValue=5012)
	@GeneratedValue(generator="paymentID",strategy=GenerationType.SEQUENCE)
	private Integer paymentId;
	private String buyerId;
	private Double netBill;
	private Character paymentType;
	@Temporal(TemporalType.DATE)
	private Calendar purchaseDate;
	
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
	public Double getNetBill() {
		return netBill;
	}
	public void setNetBill(Double netBill) {
		this.netBill = netBill;
	}
	public Character getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(Character paymentType) {
		this.paymentType = paymentType;
	}
	public Calendar getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Calendar purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	
}