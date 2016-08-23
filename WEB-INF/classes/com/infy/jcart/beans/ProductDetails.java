package com.infy.jcart.beans;

import java.util.Calendar;

public class ProductDetails {
	private Integer productId;
	private String productName;
	private Character productType;
	private String company;
	private String category;
	private String subcategory;
	private Integer numOfProducts;
	private Double price;
	private Calendar dateOfPurchase;
	private Double bidAmount;
	private String bidderId;
	private String categoryName;
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Double getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(Double bidAmount) {
		this.bidAmount = bidAmount;
	}
	public String getBidderId() {
		return bidderId;
	}
	public void setBidderId(String bidderId) {
		this.bidderId = bidderId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Calendar getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Calendar dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public Integer getNumOfProducts() {
		return numOfProducts;
	}
	public void setNumOfProducts(Integer numOfProducts) {
		this.numOfProducts = numOfProducts;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Character getProductType() {
		return productType;
	}
	public void setProductType(Character productType) {
		this.productType = productType;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
