package com.infy.jcart.dao;

import java.util.List;

import com.infy.jcart.beans.IssueDetails;

public interface RaiseIssueDAO {
	public Integer raiseIssue(IssueDetails to) throws Exception ;
	public List<String> getAllRetailers() throws Exception;
	public List<Integer> getProductsForRetailer(String retailer)throws Exception;
}
