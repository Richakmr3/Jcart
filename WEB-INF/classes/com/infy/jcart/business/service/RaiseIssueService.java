package com.infy.jcart.business.service;

import java.util.List;

public interface RaiseIssueService {
	public Integer raiseIssue(List<String> list, Integer productId)
			throws Exception;
	public List<String> getAllRetailers() throws
	Exception ;
	public List<Integer> getProductsForRetailer(String retailer)
			throws Exception ;
}
