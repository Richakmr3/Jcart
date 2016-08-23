package com.infy.jcart.dao;

import java.util.List;

public interface SearchDAO {

	@SuppressWarnings("rawtypes")
	public List getSearchElementsByCategory(String searchString,String selectedCategory) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List getSearchElementsInAllCategories(String searchString) throws Exception;
}
