package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.Registration;

public interface EditProfileService 
{
	public Integer updateProfile(Registration to) throws Exception;
	public Boolean deactivateUser(String userId) throws Exception;
	public String getPasswordDetails(String userId) throws Exception;
	public Boolean editMessageSetting(String userId) throws Exception;
	public Integer changePassword(List<String> list) throws Exception;
	public Registration getProfileDetails(String userId) throws  Exception;
}
