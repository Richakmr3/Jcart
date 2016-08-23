package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.LogDetail;

public interface AccountService {
	
	public List<LogDetail> getLockedAccounts() throws Exception;
	
	public List<LogDetail> getBlockedAccounts() throws Exception; 
	
	public void activateLockedOrBlockedAccounts(List<LogDetail> accountList) throws Exception;
	
}
