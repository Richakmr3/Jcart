package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.Category;
import com.infy.jcart.beans.SubCategory;
import com.infy.jcart.beans.UserProductRequest;

public interface RequestProductService {

	
	public Integer requestProduct(UserProductRequest productDetails) throws Exception;
	
	
	public List<Category> getAllCategory() throws Exception;
	
	
	
	public List<SubCategory> getAllSubCategory(Integer categoryId) throws Exception;
	
	
	
}
