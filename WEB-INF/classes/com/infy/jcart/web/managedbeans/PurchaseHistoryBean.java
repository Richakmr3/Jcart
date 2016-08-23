package com.infy.jcart.web.managedbeans;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.jcart.beans.PurchaseHistory;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@RequestScoped
public class PurchaseHistoryBean {

	private String userId;
	private String message;
	private List<PurchaseHistory> displayList;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<PurchaseHistory> getDisplayList() {
		return displayList;
	}

	public void setDisplayList(List<PurchaseHistory> displayList) {
		this.displayList = displayList;
	}

	/***
	 * This method is used to populate the user's transaction detail
	 * 
	 * @exception Exception
	 */
	public PurchaseHistoryBean() {
		FacesContext ftx = FacesContext.getCurrentInstance();
		ExternalContext etx = ftx.getExternalContext();
		HttpSession session = (HttpSession) etx.getSession(true);
		this.userId = (String) session.getAttribute("userId");
		displayList = new ArrayList<PurchaseHistory>();
		try {
			displayList =Factory.createPurchaseService()
					.getTransactionDetails(userId);
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"PurchaseHistoryBean", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
	}

	/***
	 * This method is used to populate the user's transaction detail
	 * 
	 * @exception Exception
	 * @return String
	 */
	public String getTransactionDetails() {
String path="";
		try {

			displayList =Factory.createPurchaseService()
					.getTransactionDetails(userId);
			path="/user/PaymentSuccess";
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"getTransactionDetails", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				path = "/pages/errorPage";
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
			
		}
return path;
	}

}
