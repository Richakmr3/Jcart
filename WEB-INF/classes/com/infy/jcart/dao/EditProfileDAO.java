package com.infy.jcart.dao;

import com.infy.jcart.beans.Registration;

public interface EditProfileDAO 
{
	public Boolean deactivateUser(String userId) throws Exception;
	public Boolean toggleMessageSettings(String userId, Character status) throws Exception;
	public Registration getProfileDetails(String userId) throws Exception;
	public String getPasswordDetails(String userId) throws Exception;
	public Integer updateProfile(Registration to) throws Exception;
	public Integer changePassword(String userId, String newPassword) throws Exception;
	public Boolean editMessageSetting(String userId) throws Exception;
	public Boolean checkOpenBids(String userId) throws Exception ;
	public Character getRoleFromUserId(String userId) throws Exception;
}
