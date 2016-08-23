package com.infy.jcart.business.service;

import java.util.ArrayList;
import java.util.List;

import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;

public class MessageServiceImpl implements MessageService {

	/***
	 * @description this method checks whether the message service is active for
	 *              the recipient or not, if active then invokes sendMessage()
	 *              method of MessageDAOImpl class
	 * @param String
	 *            ,String,String
	 * @return void
	 * @throws MessageService.MESSAGE_SERVICE_DISABLED
	 * @catch Exception
	 */
	public void sendMessage(String message, String recieverId, String senderId)
			throws Exception {
		try {
			if(Factory.createMessageDAO().checkActiveMessageService(recieverId)==false)
			{
				throw new Exception("MessageService.MESSAGE_SERVICE_DISABLED");
			}
			
			Factory.createMessageDAO().sendMessage(message, recieverId, senderId);
			
		} catch (Exception exception) {
			
			JCartLogger.logError(this.getClass().getName(), "sendMessage",
					exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method invokes getAllUserIds() method of MessageDAO
	 *              class
	 * @return List<String>
	 * @throws Exception
	 * @catch Exception
	 */
	public List<String> getAllUserIds() throws Exception {
		try {
			List<String> list=new ArrayList<String>();
			list=Factory.createMessageDAO().getAllUserIds();
			
			return list;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllUserIds",
					exception.getMessage());
			throw exception;
		}
		
	}

}
