package com.infy.jcart.entites;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JCART_PAYMENTDETAILS")
public class PaymentDetailsEntity {

	@EmbeddedId
	private PaymentDetailsPK paymentDetailsPK;
	private String sellerId;
	private Integer numOfProducts;

	public Integer getNumOfProducts() {
		return numOfProducts;
	}

	public void setNumOfProducts(Integer numOfProducts) {
		this.numOfProducts = numOfProducts;
	}

	public PaymentDetailsPK getPaymentDetailsPK() {
		return paymentDetailsPK;
	}

	public void setPaymentDetailsPK(PaymentDetailsPK paymentDetailsPK) {
		this.paymentDetailsPK = paymentDetailsPK;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

}