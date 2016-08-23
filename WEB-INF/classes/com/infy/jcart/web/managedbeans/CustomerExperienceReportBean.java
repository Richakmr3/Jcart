package com.infy.jcart.web.managedbeans;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;


import com.infy.jcart.resources.ErrorRedirect;
//import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;
@ManagedBean
@SessionScoped
public class CustomerExperienceReportBean {
	private String message;
	private List<Entry<String, Double>> entries;
	private String styleClass;
	private List<SelectItem> retailerIdList = new ArrayList<SelectItem>();
	private int choice;
	public String getStyleClass() 
	{
		return styleClass;
	}
	public void setStyleClass(String styleClass) 
	{
		this.styleClass = styleClass;
	}

	/****
	 * @description This method is used to display the customer experience report for the
	 * selected product
	 * 
	 * @param ValueChangeEvent
	 * @return void
	 * @exception Exception
	 * 
	 */
	public void getReportData(ValueChangeEvent event) {
		try {
			message = "";
			Map<String, Double> map = null;
			choice = Integer.parseInt(event.getNewValue() + "");

			if (choice == 2) 
			{
				retailerIdList.clear();
				List<String> listIds = Factory.createRetailerService()
						.getAllRetailerIds();
				for (String s : listIds) {
					retailerIdList.add(new SelectItem(s));
				}
			}
			map = Factory.createCustomerExperienceReportService().getCustomerExperience(
					choice, null);
			entries = new ArrayList<Entry<String, Double>>(map.entrySet());
		} catch (Exception e) 
		{
			JCartLogger.logError(this.getClass().getName(), "getReportData", e
					.getMessage());
			
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
				ErrorRedirect.reDirect();
			}
			else{
				this.styleClass="errorMsg";
			
			this.message = JCartConfig.getMessageFromProperties(e.getMessage());
		}}
	}

	/****
	 *@description This method is used to display the customer experience report for the
	 * selected retailer
	 * 
	 * @param ValueChangeEvent
	 * @return void
	 * @exception Exception
	 * 
	 * 
	 */
	public void getRetailerReportData(ValueChangeEvent event) {
		try {
			message = "";
			Map<String, Double> map = null;
			String retailerId = event.getNewValue() + "";

			if (retailerId.equals("0")) {
				message = JCartConfig.getMessageFromProperties("CustomerExperienceReportBean.NO_RETAILER_SELECTED");
				entries.clear();
			} 
			else 
			{
				map =Factory.createCustomerExperienceReportService()
						.getCustomerExperience(0, retailerId);
				entries = new ArrayList<Entry<String, Double>>(map.entrySet());
			}
		} catch (Exception e) {
			
			JCartLogger.logError(this.getClass().getName(), "getRetailerReportData", e
					.getMessage());
		

			if(e.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
				ErrorRedirect.reDirect();
			}
			else{
				this.styleClass="errorMsg";
			
			this.message = JCartConfig.getMessageFromProperties(e.getMessage());
		}
		}
		}
	

	public List<SelectItem> getRetailerIdList() {
		return retailerIdList;
	}

	public void setRetailerIdList(List<SelectItem> retailerIdList) {
		this.retailerIdList = retailerIdList;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Entry<String, Double>> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry<String, Double>> entries) {
		this.entries = entries;
	}

}
