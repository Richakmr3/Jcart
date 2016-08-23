package com.infy.jcart.beans;



public class PaymentDetails {
	private Integer paymentId;
	private Integer  productId;
	private String sellerId;
	private String variationCode;
	private Integer noOfProducts;
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
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getVariationCode() {
		return variationCode;
	}
	public void setVariationCode(String variationCode) {
		this.variationCode = variationCode;
	}
	public Integer getNoOfProducts() {
		return noOfProducts;
	}
	public void setNoOfProducts(Integer noOfProducts) {
		this.noOfProducts = noOfProducts;
	}
	
	
		
		
	
}
