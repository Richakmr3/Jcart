package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.infy.jcart.beans.IssueDetails;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@SessionScoped

public class ViewIssuesBean {
	private Integer choice;
	private List<IssueDetails> issueList;
	private List<Entry<String, Integer>> summary;
	private String retailerId;
	private List<SelectItem> retailerList = new ArrayList<SelectItem>();
	private String message;

	/***
	 * @description This method is used to populate the issue summary details
	 * 
	 * @exception Exception
	 */
	public ViewIssuesBean() {
		try {
			Map<String, Integer> map = Factory.createIssueService().getIssuesSummary();
			summary = new ArrayList<Entry<String, Integer>>(map.entrySet());
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "ViewIssuesBean", e
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
	 * @description This method is used to populate the issues for the selected retailer
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String getIssuesForRetailer() {
		String path="";
		try {
			message = null;
			issueList = Factory.createIssueService().getIssuesForRetailer(retailerId);
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"getIssuesForRetailer", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
				path="/pages/errorPage";
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
		return path;
	}


	/***
	 * @description This method is used to deactivate a retailer once no.of issue count is
	 * >=3
	 * @return String
	 * @exception Exception
	 *
	 */
	public String deactivateRetailer() {
		String path="";
		try {
			if (issueList.size() >= 3) {
				Factory.createIssueService().deactivateRetailer(retailerId);
				message = "Retailer deactivated successfully";
			} else {
				message = "Retailer can be deactivated only after 3 issues";
			}
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"deactivateRetailer", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
				path="/pages/errorPage";
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
		return path;
	}

	public Integer getChoice() {
		return choice;
	}

	public void setChoice(Integer choice) {
		this.choice = choice;
	}

	public List<IssueDetails> getIssueList() {
		return issueList;
	}

	public void setIssueList(List<IssueDetails> issueList) {
		this.issueList = issueList;
	}

	public List<Entry<String, Integer>> getSummary() {
		return summary;
	}

	public void setSummary(List<Entry<String, Integer>> summary) {
		this.summary = summary;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public List<SelectItem> getRetailerList() {
		return retailerList;
	}
	public void setRetailerList(List<SelectItem> retailerList) {
		
		this.retailerList = retailerList;
	}

}
