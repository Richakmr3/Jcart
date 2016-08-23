package com.infy.jcart.entites;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PaymentDetailsPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer paymentId;
	private Integer productId;
	private String variationCode;

	public String getVariationCode() {
		return variationCode;
	}

	public void setVariationCode(String variationCode) {
		this.variationCode = variationCode;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}
