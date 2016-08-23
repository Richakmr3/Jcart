package com.infy.jcart.business.service;

import com.infy.jcart.beans.Login;

public interface LoginService 
{
	public Login authorize(Login login) throws Exception ;
	
	public void lockAccount(Login login) throws Exception ;
	
	public void blockAccount(String userId) throws Exception ;
	
	public void unlockAccount(String userId) throws Exception ;
	
	public void saveLoginDetails(Login login) throws Exception ;
	
	public void activateRetailer(String userId) throws Exception ;
	
	public void rejectRetailer(String userId) throws Exception ;
	
	public void deactivateRetailer(String userId) throws Exception ;
	
	public void logout(String userId) throws Exception ;
}
