package com.infy.jcart.beans;


public class Product {
	
	private Integer productId;
	private String productName;
	private String sellerId;
	private Character productType;
	private Integer subCategory;
	private Integer categoryId;
	private Double price;
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
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
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public Character getProductType() {
		return productType;
	}
	public void setProductType(Character productType) {
		this.productType = productType;
	}
	public Integer getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(Integer subCategory) {
		this.subCategory = subCategory;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
}