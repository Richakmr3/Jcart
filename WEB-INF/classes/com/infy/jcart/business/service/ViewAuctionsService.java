package com.infy.jcart.business.service;

import java.util.Calendar;
import java.util.List;

import com.infy.jcart.beans.ProductBid;

public interface ViewAuctionsService {

	
	public List<ProductBid> getOpenAuctions(String sortBy) throws Exception ;
	public List<ProductBid> getClosedAuctions(String sortBy, Calendar fromDate,
			Calendar toDate) throws Exception;
}
