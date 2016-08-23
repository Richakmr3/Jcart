package com.infy.jcart.beans;

import java.util.Date;

public class BrowsingHistory {
	
	private String userId;
	private Integer productId;
	private Date lastBrowsed;
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
	public Date getLastBrowsed() {
		return lastBrowsed;
	}
	public void setLastBrowsed(Date lastBrowsed) {
		this.lastBrowsed = lastBrowsed;
	}
	
}