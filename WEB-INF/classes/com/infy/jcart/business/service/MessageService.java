package com.infy.jcart.business.service;

import java.util.List;


public interface MessageService {

	public void sendMessage(String message,String recieverId,String senderId) throws Exception;
	
	public List<String> getAllUserIds() throws Exception;
	
}
