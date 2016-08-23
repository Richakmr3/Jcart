package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.infy.jcart.beans.OverAllFeedBack;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;


@ManagedBean
@RequestScoped
public class ViewOverAllFeedbackBean {

	private List<OverAllFeedBack> list = new ArrayList<OverAllFeedBack>();
	private String message;

	private String userId;

	/***
	 * @description This method is used to retrieve the feedback comments for loggedin user
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String getFeedbackComments() {
		String path=null;
		try {

			list = Factory.createFeedBackDAO().getFeedbackComment(userId);
			if (list == null || list.size() == 0) {
				this.message = JCartConfig.getMessageFromProperties("ViewOverAllFeedBackBean.RECORDS_NOT_FOUND");			
			}

			path= "";
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getFeedbackComments", exception.toString());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR")){
				path="/pages/errorpage";
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
			
		}
			return path;
	}

	public List<OverAllFeedBack> getList() {
		return list;
	}

	public void setList(List<OverAllFeedBack> list) {
		this.list = list;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
