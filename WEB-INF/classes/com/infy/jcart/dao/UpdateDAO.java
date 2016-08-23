package com.infy.jcart.dao;

public interface UpdateDAO {

	public String updateStock(Integer productId, String variationCode) throws Exception;
	
	public String addUserPointsOnPurchase(String userId, Double bill) throws Exception;
	
	
}
