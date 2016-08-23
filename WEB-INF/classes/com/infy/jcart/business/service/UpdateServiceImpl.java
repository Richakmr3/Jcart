package com.infy.jcart.business.service;

import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;

public class UpdateServiceImpl implements UpdateService {

	/***
	 * @description this method invokes updateStock() method of UpdateDAO and
	 *              returns the String obtained
	 * @param Integer
	 *            , String
	 * @return String
	 * @catch Exception
	 */
	public String updateStock(Integer productId, String variationCode)
			throws Exception {

		try {

			return Factory.createUpdateDAO()
					.updateStock(productId, variationCode);

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "updateStock",
					exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method invokes addUserPointsOnPurchase() method of
	 *              UpdateDAO and returns the String obtained
	 * @param String
	 *            ,Double
	 * @return String
	 * @throws Exception
	 */
	public String addUserPointsOnPurchase(String userId, Double bill)
			throws Exception {

		try {
			return Factory.createUpdateDAO()
					.addUserPointsOnPurchase(userId, bill);
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "addUserPointsOnPurchase",
					exception.getMessage());
			throw exception;
		}

	}

}
