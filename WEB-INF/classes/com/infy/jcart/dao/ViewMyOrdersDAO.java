package com.infy.jcart.dao;

import java.util.List;

import com.infy.jcart.beans.OrderLog;

public interface ViewMyOrdersDAO {

	public List<OrderLog> getMyOrders(String userId) throws Exception;
	
	
	
	
	
	public List<String> getCategorySubcategory(Integer categoryId, Integer subCategory) throws Exception;
	
	public Object[] getProductName(Integer categoryId, Integer productId) throws Exception;
	
}
