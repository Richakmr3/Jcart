package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.Registration;

public interface ApproveRetailerService {

	
	public List<Registration> getRetailersByStatus(Character status)
			throws Exception;
	
	
	public void updateRetailerStatus(List<Registration> selectedRetailers,
			Character status) throws Exception ;
	
	
	
	
}
