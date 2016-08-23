package com.infy.jcart.dao;

import java.sql.Connection;
import java.util.List;

import com.infy.jcart.beans.OverAllFeedBack;

public interface FeedBackDAO {
	
	public Connection getConnection() throws Exception ;
		
	public void submitFeedback(OverAllFeedBack overAllFeedBackTO) throws Exception;
	
	public List<OverAllFeedBack> getFeedbackComment(String userId) 	throws Exception;
	
	
}
