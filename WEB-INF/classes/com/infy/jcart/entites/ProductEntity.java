package com.infy.jcart.entites;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="JCART_PRODUCT")
public class ProductEntity {
	
	@Id
	@SequenceGenerator(name="productID",sequenceName="db_productID",allocationSize=1,initialValue=10081)
	@GeneratedValue(generator="productID",strategy=GenerationType.SEQUENCE)
	private Integer productId;
	private String sellerId;
	private Character productType;
	private Integer subCategory;
	private Integer categoryId;
	
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