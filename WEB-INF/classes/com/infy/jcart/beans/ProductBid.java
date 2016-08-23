package com.infy.jcart.beans;

import java.util.Calendar;

public class ProductBid {
	
	private Integer productId;
	private Double basePrice;
	private Integer incrementValue;
	private Character bidStatus;
	private Calendar bidStartDate;
	private Integer duration;
	private Integer numberOfBids;
	private String lastBidderId;
	private String lastButOneBidderId;
	private Double lastButOneBasePrice;
	private Calendar bidCloseDate;
	private String categoryName;
	private String subCategoryName;
	private String sellerId;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
	public Integer getIncrementValue() {
		return incrementValue;
	}
	public void setIncrementValue(Integer incrementValue) {
		this.incrementValue = incrementValue;
	}
	public Character getBidStatus() {
		return bidStatus;
	}
	public void setBidStatus(Character bidStatus) {
		this.bidStatus = bidStatus;
	}
	
	public Calendar getBidStartDate() {
		return bidStartDate;
	}
	public void setBidStartDate(Calendar bidStartDate) {
		this.bidStartDate = bidStartDate;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Integer getNumberOfBids() {
		return numberOfBids;
	}
	public void setNumberOfBids(Integer numberOfBids) {
		this.numberOfBids = numberOfBids;
	}
	public String getLastBidderId() {
		return lastBidderId;
	}
	public void setLastBidderId(String lastBidderId) {
		this.lastBidderId = lastBidderId;
	}
	public String getLastButOneBidderId() {
		return lastButOneBidderId;
	}
	public void setLastButOneBidderId(String lastButOneBidderId) {
		this.lastButOneBidderId = lastButOneBidderId;
	}
	public Double getLastButOneBasePrice() {
		return lastButOneBasePrice;
	}
	public void setLastButOneBasePrice(Double lastButOneBasePrice) {
		this.lastButOneBasePrice = lastButOneBasePrice;
	}
	public Calendar getBidCloseDate() {
		return bidCloseDate;
	}
	public void setBidCloseDate(Calendar bidCloseDate) {
		this.bidCloseDate = bidCloseDate;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	
}