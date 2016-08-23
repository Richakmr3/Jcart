package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.Registration;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;


public class ApproveRetailerServiceImpl implements ApproveRetailerService{
	/**
	 * @description this method invokes getRetailersByStatus() method of
	 *              ApproveRetailerDAOImpl class and returns the obtained list
	 * 
	 * @param Character
	 * @return List<Registration>
	 * @throws ApproveRetailerService.NO_RETAILER_TO_APPROVE
	 * @catch Exception
	 */
	public List<Registration> getRetailersByStatus(Character status)
			throws Exception {
		try {
			List<Registration> list = Factory.createApproveRetailerDAO()
					.getRetailersByStatus(status);
			if (list.isEmpty()) {
				throw new Exception("ApproveRetailerService.NO_RETAILER_TO_APPROVE");
			}
			return list;
		}  catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getRetailersByStatus",
					exception.getMessage());
			throw exception;
		}
	}

	/**
	 * @description this method invokes updateRetailerStatus() method of
	 *              ApproveRetailerDAOImpl class
	 * 
	 * @param List
	 *            <Registration>, Character
	 * @return void
	 * @throws ApproveRetailerService.SELECT_RETAILER
	 * @catch Exception
	 */
	public void updateRetailerStatus(List<Registration> selectedRetailers,
			Character status) throws Exception {
		try {
			if (selectedRetailers.isEmpty()) {
				throw new Exception("ApproveRetailerService.SELECT_RETAILER");
			}
			Factory.createApproveRetailerDAO().updateRetailerStatus(
					selectedRetailers, status);
		} catch (Exception exception) {
			
			JCartLogger.logError(this.getClass().getName(), "updateRetailerStatus",
					exception.getMessage());
			throw exception;
		}
	}
}
