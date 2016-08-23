package com.infy.jcart.entites;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProductVariationsPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer productId;
	private String variationCode;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getVariationCode() {
		return variationCode;
	}
	public void setVariationCode(String variationCode) {
		this.variationCode = variationCode;
	}
		
}
