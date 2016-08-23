package com.infy.jcart.business.service;

import java.util.ArrayList;
import java.util.List;

import com.infy.jcart.beans.LogDetail;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;

public class AccountServiceImpl implements AccountService{


	/***
	 * @description this method invokes getAccountsByStatus() method of AccountDAOImpl class
     * 				by passing 'L' as parameter and returns the obtained list
	 * @return List <LogDetail>
	 * @throws AccountService.NO_LOCKED_ACCOUNTS
	 * @catch Exception
	 */
	public List<LogDetail> getLockedAccounts() throws Exception {
		List<LogDetail> list = new ArrayList<LogDetail>();
		try {
			list = Factory.createAccountDAO().getAccountsByStatus('L');
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getLockedAccounts",
					exception.getMessage());
			throw exception;
		}
		return list;
	}

	/***
	 * @description this method invokes getAccountsByStatus() method of AccountDAOImpl class
     * 				by passing 'B' as parameter and returns the obtained list	
	 * @return List <LogDetail>
	 * @throws AccountService.NO_BLOCKED_ACCOUNTS
	 * @catch Exception
	 */
	public List<LogDetail> getBlockedAccounts() throws Exception {
		List<LogDetail> list = new ArrayList<LogDetail>();
		try {
			list = Factory.createAccountDAO().getAccountsByStatus('B');
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getBlockedAccounts",
					exception.getMessage());
			throw exception;
		}		return list;
	}

	/***
	 * @description this method invokes activateLockedOrBlockedAccounts() method of 
     * 				AccountDAOImpl class
     * @param List<LogDetail>
	 * @return void
	 * @throws AccountService.NO_ROWS_SELECTED
	 * @catch Exception
	 */
	public void activateLockedOrBlockedAccounts(List<LogDetail> accountList)
			throws Exception {
		try {
			if (accountList.isEmpty()) {
				throw new Exception("AccountService.NO_ROWS_SELECTED");
			}
			Factory.createAccountDAO().activateLockedOrBlockedAccounts(accountList);
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "activateLockedOrBlockedAccounts",
					exception.getMessage());
			throw exception;
		}		
	}

}
