package com.infy.jcart.dao;

import java.util.List;

public interface MessageDAO {

	public void sendMessage(String message,String recieverId,String senderId) throws Exception;
	
	public Boolean checkActiveMessageService(String userId) throws Exception;
	
	public List<String> getAllUserIds() throws Exception;
	
}
