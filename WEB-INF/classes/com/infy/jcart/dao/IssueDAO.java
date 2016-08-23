package com.infy.jcart.dao;

import java.util.List;
import java.util.Map;

import com.infy.jcart.beans.IssueDetails;

public interface IssueDAO
{
	
	
	public List<IssueDetails> getIssuesForRetailer(String retailerId) throws Exception;
	public Map<String, Integer> getIssuesSummary() throws Exception ;
	public void acknowledgeIssues(List<IssueDetails> selectedIssues) throws Exception;
	public void deactivateRetailer(String retailerId) throws Exception;
}
