package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.IssueDetails;
import com.infy.jcart.dao.RaiseIssueDAO;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;


public class RaiseIssueServiceImpl implements RaiseIssueService {
	/***
	 * @description this method populates a IssueDetails from productId, list of 
	 * Strings, and sets it status as 'S'
	 * invokes raiseIssue of RaiseIssueDAOImpl class and
	 * returns the issue id obtained
	 * @param List<String>, Integer
	 * @return Integer
	 * @throws  Exception
	 * @catch Exception
	 */
	public Integer raiseIssue(List<String> list, Integer productId)
			throws Exception {
		try {
			IssueDetails to = new IssueDetails();
			String retailer = list.get(0);
			String description = list.get(1);
			String userId = list.get(2);
			to.setDescription(description);
			to.setProductId(productId);
			to.setRetailerId(retailer);
			to.setStatus('S');
			to.setUserId(userId);

			RaiseIssueDAO ser = Factory.createRaiseIssueDAO();
			Integer i;

			i = ser.raiseIssue(to);
			return i;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "raiseIssue",
					exception.getMessage());
			throw exception;
		}

	}
	/***
	 * @description this method invokes getAllRetailers() method of
	 *  RaiseIssueDAOImpl class and
	 * returns the list obtained
	 * @return List<String>
	 * @throws RaiseIssueService.NO_RETAILORS_FOUND
	 * @catch Exception
	 */
	public List<String> getAllRetailers() throws
			Exception {

		try {

			RaiseIssueDAO ser = Factory.createRaiseIssueDAO();
			List<String> rl = ser.getAllRetailers();
			if (rl == null) {
				throw new Exception("RaiseIssueService.NO_RETAILORS_FOUND");
			}
			return rl;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllRetailers",
					exception.getMessage());
			throw exception;
		}
	}
	/***
	 * @description this method invokes getProductsForRetailer() method of
	 *  RaiseIssueDAOImpl class and
	 * returns the list obtained
	 * @param String
	 * @return List<Integer>
	 * @throws  RaiseIssueService.NO_PRODUCTS_FOUND_FOR_RETAILOR
	 * @catch Exception
	 */
	public List<Integer> getProductsForRetailer(String retailer)
			throws Exception {

		try {

			RaiseIssueDAO ser =Factory.createRaiseIssueDAO();
			List<Integer> rl = ser.getProductsForRetailer(retailer);
			if (rl.size() == 0) {
				throw new Exception("RaiseIssueService.NO_PRODUCTS_FOUND_FOR_RETAILOR");
			}
			return rl;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getProductsForRetailer",
					exception.getMessage());
			throw exception;
		}
	}

}
