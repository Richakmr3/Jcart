package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.infy.jcart.beans.IssueDetails;
import com.infy.jcart.beans.Product;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;


@ManagedBean
@SessionScoped
public class ViewIssuesRaisedByMeBean {
	private List<SelectItem> retailersList;
	private String retailer;
	private List<IssueDetails> issuesList;
	private String message;
	private Integer pid;
	private Product productTo;

	/***
	 * @description This method is used to populate the issue details raised by loggedin
	 * user(retailer)
	 * 
	 * @exception Exception
	 */
	public ViewIssuesRaisedByMeBean() {
		try {
			this.retailersList = new ArrayList<SelectItem>();
			List<String> rl = new ArrayList<String>();
		
		rl=Factory.createRaiseIssueService().getAllRetailers();
			for (String string : rl) {
				this.retailersList.add(new SelectItem(string));
			}

		
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"ViewIssuesRaisedByMeBean", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<SelectItem> getRetailersList() {
		return retailersList;
	}

	public void setRetailersList(List<SelectItem> retailersList) {
		this.retailersList = retailersList;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Product getProductTo() {
		return productTo;
	}

	public void setProductTo(Product productTo) {
		this.productTo = productTo;
	}

	public List<IssueDetails> getIssuesList() {
		return issuesList;
	}

	public void setIssuesList(List<IssueDetails> issuesList) {
		this.issuesList = issuesList;
	}

	/***
	 * @description This method is used to retrieve the issue details for the selected
	 * retailer
	 * @param ValueChangeEvent
	 * @return void
	 * @exception  Exception
	 *
	 * 
	 */
	public void getIssuesForRetailer(ValueChangeEvent event) {
		try {
			this.message = "";
			this.productTo = null;
			this.retailer = (String) event.getNewValue();

			
			this.issuesList = new ArrayList<IssueDetails>();
			
			FacesContext ftx = FacesContext.getCurrentInstance();
			ExternalContext etx = ftx.getExternalContext();
			HttpSession session = (HttpSession) etx.getSession(true);

			String userId = (String) session.getAttribute("userId");
			
			this.issuesList=Factory.createViewIssueDAO().getIssuesForRetailer(this.retailer, userId);
		} 

		 catch (Exception e) {
		JCartLogger.logError(this.getClass().getName(),
					"getIssuesForRetailer", e.toString());
		if(e.getMessage().contains("DAO.TECHNICAL_ERROR"))
		{
			ErrorRedirect.reDirect();
			
		}
		else{
			this.message=JCartConfig.getMessageFromProperties(e.getMessage());
		}
		}

	}

	/***
	 * @description This method is used to retrieve the product details for the selected
	 * product
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String getProductDetails() {
		String path=null;
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Map<String, String> params = context.getExternalContext()
					.getRequestParameterMap();
			this.pid = Integer.parseInt(params.get("pid"));
		
			this.productTo=Factory.createViewIssueDAO().getProductDetails(this.pid);
			path= "/user/ViewProductsPurchasedDetails";
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"getProductDetails", e.toString());
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
}
