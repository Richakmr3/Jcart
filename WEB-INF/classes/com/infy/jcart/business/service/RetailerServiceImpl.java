package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;


public class RetailerServiceImpl implements RetailerService{
	/***
	 * @description this method 
	 * 				invokes getAllRetailerIds() method of RetailerService 
	 * 				and returns the request id obtained
	 * @return List<String> 
	 * @throws RetailerService.NO_RETAILER_FOUND
	 * @catch Exception
	 */
	public List<String> getAllRetailerIds() throws Exception {
		try {
			List<String> l = Factory.createRetailerDAO().getAllRetailerIds();
			if (l.isEmpty()) {
				throw new Exception("RetailerService.NO_RETAILER_FOUND");
			}
			return l;
		}  catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllRetailerIds",
					exception.getMessage());
			throw exception;
		}
	}

}
