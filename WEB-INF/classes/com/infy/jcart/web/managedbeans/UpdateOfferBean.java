package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.infy.jcart.beans.ProductOffer;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;


@ManagedBean
@SessionScoped
public class UpdateOfferBean {

	private List<ProductOffer> offerList = new ArrayList<ProductOffer>();
	private List<ProductOffer> updateList = new ArrayList<ProductOffer>();
	private String message;
	private String styleClass;

	/***
	 * @description This method is used to populate the offer details
	 * 
	 * @exception Exception
	 */
	public UpdateOfferBean() {
		this.message = null;
		this.offerList.clear();
		try {
		
			this.offerList = Factory.createOfferService().getAllOffers();
			

		} 
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "UpdateOfferBean",
					exception.toString());
			if(exception.getMessage().contains("TECHNICAL_ERROR")){
			ErrorRedirect.reDirect();}
			else
			{
				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
			
		}
	}

	/**
	 * @description This method is used to select an offer for update
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String selectToUpdate() {
		String path=null;
		this.message = null;
		this.updateList.clear();
		try {
			
			for (ProductOffer offlist : this.offerList) {
				if (offlist.getSelected())
					this.updateList.add(offlist);
			}
			
			if (this.updateList.isEmpty()) {
				this.message = JCartConfig.getMessageFromProperties("UpdateOfferBean.SELECT_OFFER");					
				this.styleClass = "errorMsg";
				path= "";
			}
			path= "UpdateOfferDetails";
		}
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "selectToUpdate",
					exception.toString());
			path= "/pages/errorPage";		
		}
		return path;
	}


	
	/***
	 * @description This method is used to populate the available offer details
	 * @return String
	 * @exception  Exception
	 * 
	 */
	public String reBuildList() {
		String path=null;
		this.message = null;
		this.offerList.clear();
		try {
			this.offerList =Factory.createOfferService().getAllOffers();

			path="SelectOffersToUpdate";
		}
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "reBuildList",
					exception.toString());
			if(exception.getMessage().contains("TECHNICAL_ERROR")){
				path= "/pages/errorPage";
				}
				else
				{
					this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
				}
			

			
		}
		return path;
	}
	/**
	 * @description This method is used to update selected offer details
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String updateOfferDetails() 
	{
		String path=null;
		this.message = null; 
		try {
			
			Factory.createOfferService().updateOfferDetails(this.updateList);
			this.message = JCartConfig.getMessageFromProperties("UpdateOfferBean.UPDATED")+this.updateList.size();	 
			this.styleClass = "successMsg";
			path= "UpdateOfferDetails";
			
		} 
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "updateOfferDetails",
					exception.toString());
			if(exception.getMessage().contains("TECHNICAL_ERROR")){
				path="/pages/errorPage";}
				else
				{
					this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
				}
						
		}
		return path;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ProductOffer> getOfferList() {
		return offerList;
	}

	public void setOfferList(List<ProductOffer> offerList) {
		this.offerList = offerList;
	}

	public List<ProductOffer> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<ProductOffer> updateList) {
		this.updateList = updateList;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

}
