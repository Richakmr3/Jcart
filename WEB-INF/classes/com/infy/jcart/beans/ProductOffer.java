package com.infy.jcart.beans;

import java.util.Calendar;

public class ProductOffer {
	
	private Integer offerCode;
	private String offerDetails;
	private Double offerDiscount;
	private Calendar startDate;
	private Calendar endDate;
	private Boolean selected;
	
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public Integer getOfferCode() {
		return offerCode;
	}
	public void setOfferCode(Integer offerCode) {
		this.offerCode = offerCode;
	}
	public String getOfferDetails() {
		return offerDetails;
	}
	public void setOfferDetails(String offerDetails) {
		this.offerDetails = offerDetails;
	}
	public Double getOfferDiscount() {
		return offerDiscount;
	}
	public void setOfferDiscount(Double offerDiscount) {
		this.offerDiscount = offerDiscount;
	}
	public Calendar getStartDate() {
		return startDate;
	}
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	public Calendar getEndDate() {
		return endDate;
	}
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
}