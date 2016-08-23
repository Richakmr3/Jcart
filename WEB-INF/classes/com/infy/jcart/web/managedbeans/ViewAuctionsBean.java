package com.infy.jcart.web.managedbeans;

import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import com.infy.jcart.beans.ProductBid;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@SessionScoped
public class ViewAuctionsBean {
	private Integer choice;
	private List<ProductBid> auctionList;
	private Calendar fromDate;
	private Calendar toDate;
	private String message;

	/***
	 * @description This method is used to populate the auction details for the selected
	 * option(open/close)
	 * @param ValueChangeEvent
	 * @return String
	 * @exception Exception
	 * 
	 *
	 */
	public void getAuctions(ValueChangeEvent event) {
		
		try {
			
			message = null;
			auctionList = null;
			fromDate = Calendar.getInstance();
			fromDate.add(Calendar.DATE, -30);
			toDate = Calendar.getInstance();
			choice = Integer.parseInt((String) event.getNewValue());
			if (choice == 1) 
			{
				auctionList = Factory.createViewAuctionsService().getOpenAuctions("date");
			} else if (choice == 2) {
				auctionList = Factory.createViewAuctionsService().getClosedAuctions(
						"date", fromDate, toDate);
			}
		} catch (Exception exception) {
		
			JCartLogger.logError(this.getClass().getName(), "getAuctions",
					exception.toString());
			if (exception.getMessage().contains("TECHNICAL_ERROR")) 
			{
				ErrorRedirect.reDirect();
			}
			this.message = JCartConfig.getMessageFromProperties(exception.getMessage());
		}
		
	}

	/***
	 * @description This method is used to populate the closed auction details between given
	 * dates
	 * @return String
	 * @exception Exception
	 *
	 */
	public String getAuctionsForDateRange() {
		String path = null;
		try {
			message = null;
			auctionList = null;
			auctionList = Factory.createViewAuctionsService().getClosedAuctions("date",
					fromDate, toDate);
		}  catch (Exception exception) {
			path = "";
			JCartLogger.logError(this.getClass().getName(), "getAuctionsForDateRange",
					exception.toString());
			if (exception.getMessage().contains("TECHNICAL_ERROR")) {
				path = "/pages/errorPage";
			}
			this.message = JCartConfig.getMessageFromProperties(exception.getMessage());
		}
		return path;
	}

	/***
	 * @description This method is used to sort the auction details by popularity based on selection (open/close)
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String sortByPopularity() {
		String path = null;
		try {
			if (choice == 1) {
				auctionList = Factory.createViewAuctionsService()
						.getOpenAuctions("popularity");
			} else if (choice == 2) {
				auctionList = Factory.createViewAuctionsService().getClosedAuctions(
						"popularity", fromDate, toDate);
			}
		}  catch (Exception exception) {
			path = "";
			JCartLogger.logError(this.getClass().getName(), "sortByPopularity",
					exception.toString());
			if (exception.getMessage().contains("TECHNICAL_ERROR")) {
				path = "/pages/errorPage";
			}
			this.message = JCartConfig.getMessageFromProperties(exception.getMessage());
		}
		return path;
	}

	public Integer getChoice() {
		return choice;
	}

	public List<ProductBid> getAuctionList() {
		return auctionList;
	}

	public void setAuctionList(List<ProductBid> auctionList) {
		this.auctionList = auctionList;
	}

	public Calendar getFromDate() {
		return fromDate;
	}

	public void setFromDate(Calendar fromDate) {
		this.fromDate = fromDate;
	}

	public Calendar getToDate() {
		return toDate;
	}

	public void setToDate(Calendar toDate) {
		this.toDate = toDate;
	}

	public void setChoice(Integer choice) {
		this.choice = choice;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
