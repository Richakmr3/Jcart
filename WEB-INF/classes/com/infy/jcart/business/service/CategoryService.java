package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.Category;
import com.infy.jcart.beans.SubCategory;

public interface CategoryService {

	
	public List<Category> getAllCategories() throws Exception;
	
	public List<SubCategory> getSubCategories(Integer categoryId) throws Exception;
	
	public Integer addSubCategory(Integer categoryId, String subCategoryName) throws Exception;
	
	public void removeSubCategory(Integer subCategoryId) throws Exception;
}
