package com.infy.jcart.dao;

import java.util.List;

public interface HomeDAO {

	public List<String> getSubCategoryNameList(Integer categoryId) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List getElementsByCategory(String categoryName) throws Exception;
	
}
