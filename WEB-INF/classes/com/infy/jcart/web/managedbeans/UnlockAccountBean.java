package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.infy.jcart.beans.LogDetail;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@RequestScoped
public class UnlockAccountBean {
	private List<LogDetail> lockedAccountList;
	private String message;
private String styleClass;

	public String getStyleClass() {
	return styleClass;
}

public void setStyleClass(String styleClass) {
	this.styleClass = styleClass;
}

	/***
	 * @description This method is used to retrieve the details of locked user accounts
	 * 
	 * @exception Exception
	 */
	public UnlockAccountBean() {
		try {
			
			lockedAccountList = Factory.createAccountService().getLockedAccounts();
			if(lockedAccountList.isEmpty()){
				throw new Exception("UnlockAccountBean.NO_LOCKED_ACCOUNTS");
			}
		} 
		catch (Exception e) 
		{
			
			JCartLogger.logError(this.getClass().getName(), "UnlockAccountBean",
					e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
				ErrorRedirect.reDirect();	
			}
			else{
				this.styleClass="errorMsg";
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
	}

	/***
	 * @description This method is used to unlock the user accounts and update the details of
	 * locked accounts
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String unlockAccounts() {
		String path="";
		try {
			List<LogDetail> selectedAccountList = new ArrayList<LogDetail>();
			for (LogDetail to : lockedAccountList) {
				if (to.isSelected() == true) {
					selectedAccountList.add(to);
				}
			}
			Factory.createAccountService()
					.activateLockedOrBlockedAccounts(selectedAccountList);
			this.styleClass="successMsg";
			this.message = JCartConfig.getMessageFromProperties("UnlockAccountBean.UNLOCK_SUCCESS");
			lockedAccountList = Factory.createAccountService().getLockedAccounts();
			
		} catch (Exception e) {
			
			JCartLogger.logError(this.getClass().getName(), "unlockAccounts", e
					.getMessage());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
				path="/pages/errorPage";
			}
			else
			{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
				this.styleClass="errorMsg";
			}
		}
		return path;
	}

	public List<LogDetail> getLockedAccountList() {
		return lockedAccountList;
	}

	public void setLockedAccountList(List<LogDetail> lockedAccountList) {
		this.lockedAccountList = lockedAccountList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
