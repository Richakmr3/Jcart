package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.model.SelectItem;

import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@RequestScoped

public class SendMessageBean {

	private List<SelectItem> empIds;
	private String errMessage;
	private String senderId;
	private String recieverId;
	private String message;

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(String recieverId) {
		this.recieverId = recieverId;
	}

	public List<SelectItem> getEmpIds() {
		return empIds;
	}

	public void setEmpIds(List<SelectItem> empIds) {
		this.empIds = empIds;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/****
	 * @description This method is used to populate all avialable userid's for selection
	 * 
	 * @exception Exception
	 */
	public SendMessageBean() {
		try {

			empIds = new ArrayList<SelectItem>();
			List<String> empLists = Factory.createMessageService().getAllUserIds();
			for (int i = 0; i < empLists.size(); i++) {
				empIds.add(new SelectItem(empLists.get(i)));
			}
		}

		catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "SendMessageBean", e
					.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
	}

	/***
	 *  @description This method is used to send message to the user's who has toggled message
	 * service
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String sendMessage() {
		String path="";
		try {
			
			// hardcoding userId of admin as he is only sending message
			this.setSenderId("Jcart");
			Factory.createMessageService().sendMessage(message, recieverId, senderId);
			this.errMessage= JCartConfig.getMessageFromProperties("SendMessageBean.ERROR_MESSAGE");

		} 
		catch (Exception exception) 
		{
			
			
			JCartLogger.logError(this.getClass().getName(), "sendMessage", exception
					.toString());
			
			if (exception.getMessage().contains("TECHNICAL_ERROR")) {
				path = "/pages/errorPage";
			}			
			else{
				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
		}
		return path;

	}

}
