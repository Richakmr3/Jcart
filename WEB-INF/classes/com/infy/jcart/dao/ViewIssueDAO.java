package com.infy.jcart.dao;

import java.util.List;

import com.infy.jcart.beans.IssueDetails;
import com.infy.jcart.beans.Product;

public interface ViewIssueDAO {

	public List<IssueDetails> getIssuesForRetailer(String retailerId, String userId) throws Exception;
	
	public Product getProductDetails(Integer productId) throws Exception ;
		
}
