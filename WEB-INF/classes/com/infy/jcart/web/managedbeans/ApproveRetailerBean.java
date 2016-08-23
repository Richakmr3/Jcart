package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.infy.jcart.beans.Registration;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;
@ManagedBean
@RequestScoped
public class ApproveRetailerBean {
	private List<Registration> retalierList;
	private String message;
	private String styleClass;
	private String errorMsg;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/****
	 * @description This method is used to populate the retailer details
	 * 
	 * @exception Exception
	 */
	public ApproveRetailerBean() {
		try {
			retalierList = new ArrayList<Registration>();
			retalierList = Factory.createApproveRetailerService() .getRetailersByStatus('N');
		}  catch (Exception e) {
		
			JCartLogger.logError(this.getClass().getName(), "approveRetailerBean",
					e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();		
			}
			else
			{
				this.styleClass="errorMsg";
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		
		}
	}

	/****
	 * @description This method is used to approve the selected 
	 * retailer by updating the status and populate the retailer 
	 * details who are not approved
	 * @return String 
	 * @exception Exception
	 * 
	 */
	public String approveRetailers() {
		String path="";
		try {
			List<Registration> selectedRetailers = new ArrayList<Registration>();
			if(!retalierList.isEmpty()){
			for (int i = 0; i < retalierList.size(); i++) 
			{
				Registration to = retalierList.get(i);
				if (to.getSelected() == true) 
				{
					selectedRetailers.add(to);
				}
			}
		}
			Factory.createApproveRetailerService().updateRetailerStatus(
					selectedRetailers, 'A');
			retalierList = Factory.createApproveRetailerService()
					.getRetailersByStatus('N');
			this.styleClass="successMsg";
			this.message= JCartConfig.getMessageFromProperties("ApproveRetailerBean.SUCCESSFUL_RETAILER_APPROVAL");
		path="/pages/admin/ApproveRetailer";
		} catch (Exception e) {
			
			JCartLogger.logError(this.getClass().getName(), "approveRetailers",
					e.toString());
			
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
				path = "/pages/errorPage";		
			}
			else
			{
				this.styleClass="errorMsg";
				this.errorMsg=JCartConfig.getMessageFromProperties(e.getMessage());
			}
			
		}
		return path;
	}

	/****
	 * @description This method is used to reject the selected retailer by updating the
	 * status and populate the retailer details who are not rejected
	 * 
	 * 
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String rejectRetailers() {
		String path="";
		try {
			List<Registration> selectedRetailers = new ArrayList<Registration>();
			if(retalierList.size() !=0)
			for (int i = 0; i < retalierList.size(); i++) {
				Registration to = retalierList.get(i);
				if (to.getSelected() == true) 
				{
					selectedRetailers.add(to);
				}
			}

			Factory.createApproveRetailerService().updateRetailerStatus(
					selectedRetailers, 'R');
			this.styleClass="successMsg";
			this.message = JCartConfig.getMessageFromProperties("ApproveRetailerBean.REJECTED_SUCCESS");
			retalierList = Factory.createApproveRetailerService()
					.getRetailersByStatus('N');
			path="/pages/admin/ApproveRetailer";
		} 
		catch (Exception e) 
		{
			
			JCartLogger.logError(this.getClass().getName(), "rejectRetailers",
					e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
				path = "/pages/errorPage";	
			}
			else
			{
				this.styleClass="errorMsg";
				this.errorMsg=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
		return path;
	}

	public List<Registration> getRetalierList() {
		return retalierList;
	}

	public void setRetalierList(List<Registration> retalierList) {
		this.retalierList = retalierList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
