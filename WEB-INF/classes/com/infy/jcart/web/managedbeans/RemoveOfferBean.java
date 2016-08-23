package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.infy.jcart.beans.ProductOffer;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;


@ManagedBean
@SessionScoped
public class RemoveOfferBean {

	private Integer offerCode;
	private ProductOffer productOffer = new ProductOffer();
	private List<SelectItem> offerCodelist = new ArrayList<SelectItem>();
	private String message;
	private String styleClass;

	/***
	 * @description This method is used to populate details of offer code
	 * 
	 * @exception  Exception
	 */
	public RemoveOfferBean() {
		this.message = null;
		this.styleClass = "";
		this.offerCodelist.clear();
		try {
			List<Integer> list = Factory.createOfferService().getAllOfferCodes();
			for (Integer obj : list)
				this.offerCodelist.add(new SelectItem(obj));

		}
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "RemoveOfferBean",
					exception.toString());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
				ErrorRedirect.reDirect();
			}
			else
			{
				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
			
		}
	}

	/***
	 * @description This method is used to populate the offer code details for selection
	 * @return String
	 * @exception Exception
	 */
	public String reBuildList() {
		String path=null;
		this.message = null;
	
		this.styleClass = "";
		this.offerCodelist.clear();
		try {
			List<Integer> list = Factory.createOfferService().getAllOfferCodes();
			for (Integer obj : list)
				this.offerCodelist.add(new SelectItem(obj));

			path= "RemoveOfferDetails";
		} 
		catch(Exception exception)
		{			
			
			JCartLogger.logError(this.getClass().getName(), "reBuildList",
					exception.toString());	
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
					path= "/pages/errorPage";
			}
			else
			{
				
				styleClass="errorMsg";
				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
				
				path= "RemoveOfferDetails";
			}
		}
		
		return path;
	}

	/***
	 * @description This method retrieves the offer details available for the selected offer
	 * code
	 * @param ValueChangeEvent
	 * @return void
	 * @exception Exception
	 *
	 */
	public void getOfferDetails(ValueChangeEvent ve) {
		this.message = null;
		this.styleClass = "";
		try {

			this.offerCode = Integer.parseInt(ve.getNewValue().toString());
			this.productOffer = Factory.createOfferService().getOfferDetails(offerCode);
			this.styleClass = "successMsg";
		}
		catch(Exception exception){		
			this.styleClass = "errorMsg";
			JCartLogger.logError(this.getClass().getName(), "getOfferDetails",
					exception.toString());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
				ErrorRedirect.reDirect();
			}
			else
			{
				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
		
		
		
		
			
		}
		
	}

	/****
	 * @description This method is used to remove offer details for the selected offer code
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String removeOfferDetails() {
		String path=null;
		this.message = null;
		this.styleClass = "";
		try {
			Factory.createOfferService().removeOfferDetails(offerCode);
			this.message = JCartConfig.getMessageFromProperties("RemoveOfferBean.DETAILS_REMOVED");	
			this.message = this.message	+ " "+offerCode;
			this.productOffer = null;
			this.offerCode = null;
			this.offerCodelist.clear();
			
				List<Integer> list = Factory.createOfferService().getAllOfferCodes();
				for (Integer obj : list)
					this.offerCodelist.add(new SelectItem(obj));
				this.styleClass = "successMsg";
				path= "";
			
			
		} 
		catch(Exception exception){		
			this.styleClass = "errorMsg";
			JCartLogger.logError(this.getClass().getName(), "removeOfferDetails",
					exception.toString());
		
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
					path= "/pages/errorPage";
			}
			else
			{
				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
		
		
		
		}
		return path;
	}

	public Integer getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(Integer offerCode) {
		this.offerCode = offerCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ProductOffer getProductOffer() {
		return productOffer;
	}

	public void setProductOffer(ProductOffer productOffer) {
		this.productOffer = productOffer;
	}

	public List<SelectItem> getOfferCodeList() {
		return offerCodelist;
	}

	public void setOfferCodeList(List<SelectItem> offerCodeList) {
		this.offerCodelist = offerCodeList;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

}
