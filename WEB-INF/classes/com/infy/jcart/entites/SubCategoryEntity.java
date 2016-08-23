package com.infy.jcart.entites;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="JCART_SUBCATEGORY")
public class SubCategoryEntity {
	
	@Id
	@SequenceGenerator(name="subCategoryID",sequenceName="db_subCategoryID",allocationSize=1,initialValue=246)
	@GeneratedValue(generator="subCategoryID",strategy=GenerationType.SEQUENCE)
	private Integer subCategoryId;
	private String subCategoryName;
	private Integer categoryId;
	
	public Integer getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
		
}