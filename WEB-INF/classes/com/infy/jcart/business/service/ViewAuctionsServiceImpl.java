package com.infy.jcart.business.service;

import java.util.Calendar;
import java.util.List;

import com.infy.jcart.beans.ProductBid;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;

public class ViewAuctionsServiceImpl implements ViewAuctionsService{

	/***
	 * @description this method invokes getOpenAuctions() method of
	 *              ViewAuctionsDAO and returns the list obtained
	 * @param String
	 * @return List<ProductBid>
	 * @throws ViewAuctionsService.NO_OPEN_AUCTIONS
	 * @catch Exception
	 */
	public List<ProductBid> getOpenAuctions(String sortBy) throws Exception {
		try {
			List<ProductBid> l = Factory.createViewAuctionsDAO()
					.getOpenAuctions(sortBy);
			if (l.isEmpty()) {
				throw new Exception("ViewAuctionsService.NO_OPEN_AUCTIONS");
			}
			return l;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getOpenAuctions",
					exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method invokes getClosedAuctions() method of
	 *              ViewAuctionsDAO and returns the list obtained
	 * @param String
	 *            , Calendar, Calendar
	 * @return List<ProductBidTO>
	 * @throws ViewAuctionsService.NO_CLOSED_AUCTIONS
	 * @catch Exception
	 */
	public List<ProductBid> getClosedAuctions(String sortBy, Calendar fromDate,
			Calendar toDate) throws Exception {
		try {
			List<ProductBid> l = Factory.createViewAuctionsDAO()
					.getClosedAuctions(sortBy, fromDate, toDate);
			if (l.isEmpty()) {
				throw new Exception("ViewAuctionsService.NO_CLOSED_AUCTIONS");
			}
			return l;
		}
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getClosedAuctions", exception.getMessage());
			throw exception;
		}
	}
}
