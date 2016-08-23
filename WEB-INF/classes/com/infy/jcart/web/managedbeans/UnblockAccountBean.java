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
public class UnblockAccountBean {
	private List<LogDetail> blockedAccountList;
	private String message;
private String styleClass;

	public String getStyleClass() {
	return styleClass;
}

public void setStyleClass(String styleClass) {
	this.styleClass = styleClass;
}

	/***
	 * @description This method is used to retrieve the details of blocked user accounts
	 * 
	 * @exception Exception
	 */
	public UnblockAccountBean() {
		try {
			blockedAccountList = Factory.createAccountService().getBlockedAccounts();
			if(blockedAccountList.isEmpty()){
				throw new Exception("UnblockAccountBean.NO_BLOCKED_ACCOUNTS");
			}
		} catch (Exception e) 
		{
			
			JCartLogger.logError(this.getClass().getName(), "UnblockAccountMB",
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

	/***
	 * @description This method is used to unblock the user accounts and update the details
	 * of blocked accounts
	 * @return String
	 * @exception NoRowSelectedException,Exception
	 * 
	 */
	public String unblockAccounts() {
		String path="";
		try {
			List<LogDetail> selectedAccountList = new ArrayList<LogDetail>();
			for (LogDetail to : blockedAccountList) {
				if (to.isSelected() == true) 
				{
					selectedAccountList.add(to);
				}
			}
			Factory.createAccountService()
					.activateLockedOrBlockedAccounts(selectedAccountList);
			this.message= JCartConfig.getMessageFromProperties("UnblockAccountsBean.UNBLOCK_MESSAGE");
			this.styleClass = "successMsg";
			blockedAccountList = Factory.createAccountService().getBlockedAccounts();

		
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "unblockAccounts",
					e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
				path = "/pages/errorPage";
			}
			else
			{
				this.styleClass="errorMsg";
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
		return path;
	}

	public List<LogDetail> getBlockedAccountList() {
		return blockedAccountList;
	}

	public void setBlockedAccountList(List<LogDetail> blockedAccountList) {
		this.blockedAccountList = blockedAccountList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
