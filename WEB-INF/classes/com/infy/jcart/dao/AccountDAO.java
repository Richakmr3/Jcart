package com.infy.jcart.dao;

import java.util.List;

import com.infy.jcart.beans.LogDetail;

public interface AccountDAO {

	public List<LogDetail> getAccountsByStatus(Character status) throws Exception;
	
	public void activateLockedOrBlockedAccounts(List<LogDetail> accountList) throws Exception;
	
}
