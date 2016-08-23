package com.infy.jcart.business.service;

public interface UpdateService {

	public String updateStock(Integer productId, String variationCode)
			throws Exception;

	public String addUserPointsOnPurchase(String userId, Double bill)
			throws Exception;
}
