package com.infy.jcart.entites;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="JCART_PRODUCTVARIATIONS")

public class ProductVariationsEntity {
	
	@EmbeddedId
	private ProductVariationsPK productVariationsPk;
	private String fit;
	private String imageUrl;
	private String color;
	private Integer stock;
	
	
	public ProductVariationsPK getProductVariationsPk() {
		return productVariationsPk;
	}
	public void setProductVariationsPk(ProductVariationsPK productVariationsPk) {
		this.productVariationsPk = productVariationsPk;
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