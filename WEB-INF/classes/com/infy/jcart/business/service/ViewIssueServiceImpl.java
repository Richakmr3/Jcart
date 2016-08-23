package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.IssueDetails;
import com.infy.jcart.beans.Product;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;


public class ViewIssueServiceImpl implements ViewIssueService {

	/***
	 * @description this method 
	 * invokes getIssuesForRetailer() method of ViewIssueDAO 
	 * and returns the list obtained
	 * @param String, String
	 * @return List<IssueDetails>
	 * @throws ViewIssueService.NO_ISSUES_RETAILER
	 * @catch Exception
	 */
	public List<IssueDetails> getIssuesForRetailer(String retailer,
			String userId) throws Exception {
		try {
			List<IssueDetails> rl = Factory.createViewIssueDAO()
					.getIssuesForRetailer(retailer, userId);
			if (rl.size() == 0) {
				throw new Exception("ViewIssueService.NO_ISSUES_RETAILER");
			}
			return rl;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getIssuesForRetailer",
					exception.getMessage());
			throw exception;
		}
	}
	/***
	 * @description this method 
	 * invokes getProductDetails() method of ViewIssueDAO 
	 * and returns the Product obtained
	 * @param Integer
	 * @return Product 
	 * @catch Exception
	 */
	public Product getProductDetails(Integer pid) throws Exception {

		try {
			Product to;
			to = Factory.createViewIssueDAO().getProductDetails(pid);
			return to;
		}  catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getProductDetails",
					exception.getMessage());
			throw exception;
		}

	}

}
