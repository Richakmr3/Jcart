package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.IssueDetails;
import com.infy.jcart.beans.Product;


public interface ViewIssueService {

	
	public List<IssueDetails> getIssuesForRetailer(String retailer,
			String userId) throws Exception ;
	public Product getProductDetails(Integer pid) throws Exception ;

}
