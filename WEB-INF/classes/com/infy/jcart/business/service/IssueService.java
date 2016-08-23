package com.infy.jcart.business.service;

import java.util.List;
import java.util.Map;

import com.infy.jcart.beans.IssueDetails;

public interface IssueService 
{
	
	
	public List<IssueDetails> getIssuesForRetailer(String retailerId) throws Exception;
	public Map<String, Integer> getIssuesSummary()throws Exception;
	public void deactivateRetailer(String retailerId) throws Exception;
	public void acknowledgeIssues(List<IssueDetails> selectedIssues) throws Exception;
}
