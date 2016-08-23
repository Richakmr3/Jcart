package com.infy.jcart.business.service;

import java.util.List;

public interface SearchService {

	@SuppressWarnings("rawtypes")
	public List getSearchElementsByCategory(String searchString,
			String selectedCategory) throws Exception;

	@SuppressWarnings("rawtypes")
	public List getSearchElementsByAllCategory(String searchString)
			throws Exception;

}
