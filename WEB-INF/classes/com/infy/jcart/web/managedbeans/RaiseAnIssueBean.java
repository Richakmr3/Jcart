package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.infy.jcart.business.service.RaiseIssueService;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@SessionScoped
public class RaiseAnIssueBean {

	private String retailer;
	private List<SelectItem> retailersList;
	private List<SelectItem> productList = new ArrayList<SelectItem>();
	private String description;
	private String userId;
	private Integer productId;
	private String message;

	/***
	 * @description This method is used to populate retailer details for selection
	 * 
	 * @exception Exception
	 */
	public RaiseAnIssueBean() {
		try {
			this.retailersList = new ArrayList<SelectItem>();
			List<String> rl = new ArrayList<String>();
			RaiseIssueService man = Factory.createRaiseIssueService();
			rl = man.getAllRetailers();
			for (String string : rl) {
				this.retailersList.add(new SelectItem(string));
			}

		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "RaiseAnIssueBean", e
					.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
			
		}
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public List<SelectItem> getRetailersList() {
		return retailersList;
	}

	public void setRetailersList(List<SelectItem> retailersList) {
		this.retailersList = retailersList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<SelectItem> getProductList() {
		return productList;
	}

	public void setProductList(List<SelectItem> productList) {
		this.productList = productList;
	}

	/***
	 *@description This method is used to raise issues regarding particular product
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String raiseIssue() {
		String path="";
		try {
			RaiseIssueService man = Factory.createRaiseIssueService();

			List<String> list = new ArrayList<String>();
			list.add(this.retailer);
			list.add(description);
			FacesContext ftx = FacesContext.getCurrentInstance();
			ExternalContext etx = ftx.getExternalContext();
			HttpSession session = (HttpSession) etx.getSession(true);

			this.userId = (String) session.getAttribute("userId");
			list.add(this.userId);

			Integer i = man.raiseIssue(list, this.productId);
			this.message = "Issue Raised Successfully !! Your issue Id is " + i;
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "raiseIssue", e
					.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				path = "/pages/errorPage";
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
		return path;
	}

	/****
	 * @description This method is used to retrieve product details for the selected retailer
	 * @param ValueChangeEvent
	 * @return void
	 * @exception Exception
	 */
	public void getProductsForRetailer(ValueChangeEvent event) {
		
		try {
			
			this.message = "";
			this.productId = null;
			this.retailer = (String) event.getNewValue();
			this.productList.clear();
			List<Integer> pl = new ArrayList<Integer>();
			RaiseIssueService man = Factory.createRaiseIssueService();

			pl = man.getProductsForRetailer(this.retailer);
			
			for (Integer pid : pl) {
				this.productList.add(new SelectItem(pid));
			}
		}  catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"getProductsForRetailer", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}

	}
}
