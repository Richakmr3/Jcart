package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.OrderLog;

public interface ViewMyOrdersService {

	
	public List<OrderLog> getMyOrders(String userId)
			throws Exception;
	
	public Object[] getProductDetails(Integer proid) throws Exception;
}
