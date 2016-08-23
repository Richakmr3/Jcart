package com.infy.jcart.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="JCART_CATEGORY")
public class CategoryEntity {
	
	@Id
	@SequenceGenerator(name="categoryID",sequenceName="db_categoryID",allocationSize=1,initialValue=109)
	@GeneratedValue(generator="categoryID",strategy=GenerationType.SEQUENCE)
	private Integer categoryId;
	private String categoryName;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
		
}