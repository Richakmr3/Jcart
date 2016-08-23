package com.infy.jcart.dao;

import java.util.Calendar;
import java.util.List;

import com.infy.jcart.beans.Login;

public interface LoginDAO {
	
	public String getLogin(String userId) throws Exception;
	
	public List<Calendar> getLastLogoutTime(String userId) throws Exception;
	
	public void logout(String userId) throws Exception ;
	
	public void deactivateRetailer(String userId) throws Exception;
	
	public void rejectRetailer(String userId) throws Exception;
	
	public void activateRetailer(String userId) throws Exception;
	
	public void saveLoginDetails(Login login) throws Exception ;
	
	public void blockAccount(String userId) throws Exception;
	
	public void unlockAccount(String userId) throws Exception;
	
	public void lockAccount(String userId) throws Exception;
	
	public Login authorize(Login login) throws Exception;
	
}
