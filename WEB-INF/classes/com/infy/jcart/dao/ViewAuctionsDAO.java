package com.infy.jcart.dao;

import java.util.Calendar;
import java.util.List;

import com.infy.jcart.beans.ProductBid;

public interface ViewAuctionsDAO {

	public List<ProductBid> getOpenAuctions(String sortBy) throws Exception;
	
	
	public List<ProductBid> getClosedAuctions(String sortBy,Calendar fromDate, Calendar toDate) throws Exception;
	
}
