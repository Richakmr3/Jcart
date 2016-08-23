package com.infy.jcart.entites;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class UserWishListPK implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private Integer productId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
}
