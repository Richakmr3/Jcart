package com.infy.jcart.beans;

public class ProductVariations {
	
	private Integer productId;
	private String variationCode;
	private String fit;
	private String imageUrl;
	private String color;
	private Integer stock;
	private Boolean selected;
	
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
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
	public String getFit() {
		return fit;
	}
	public void setFit(String fit) {
		this.fit = fit;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
		
}