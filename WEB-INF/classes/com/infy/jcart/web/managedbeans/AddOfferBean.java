package com.infy.jcart.web.managedbeans;

import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.infy.jcart.beans.ProductOffer;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@RequestScoped

public class AddOfferBean {

	private String offerDetails;
	private Double offerDiscount;
	private Calendar startDate;
	private Calendar endDate;
	private String message;
	private String styleClass;

	/***
	 * @description This method is used to save the new offer details
	 * 
	 * @return String
	 * @exception Exception
	 */
	public String fixOfferDetails() 
	{
		String path= null;
		this.message = null;
		this.styleClass = "";
		try {
			ProductOffer po = new ProductOffer();
			po.setEndDate(endDate);
			po.setOfferDetails(offerDetails);
			po.setOfferDiscount(offerDiscount);
			po.setStartDate(startDate);
			Integer id = Factory.createOfferService().fixOfferDetails(po);
			this.styleClass = "successMsg";
			this.message = JCartConfig.getMessageFromProperties("AddOfferBean.DETAILES_SAVED");
			this.message = this.message	+ id;
			
			path= "";
		} 
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "fixOfferDetails",
					exception.toString());
			this.styleClass = "errorMsg";
			this.message =JCartConfig.getMessageFromProperties(exception.getMessage());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
				path="/pages/errorPage";
			}
		}
		return path;
	}

	public String getOfferDetails() {
		return offerDetails;
	}

	public void setOfferDetails(String offerDetails) {
		this.offerDetails = offerDetails;
	}

	public Double getOfferDiscount() {
		return offerDiscount;
	}

	public void setOfferDiscount(Double offerDiscount) {
		this.offerDiscount = offerDiscount;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

}
