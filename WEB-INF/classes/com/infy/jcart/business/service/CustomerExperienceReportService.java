package com.infy.jcart.business.service;

import java.util.Map;

public interface CustomerExperienceReportService {

	
	public Map<String, Double> getCustomerExperience(Integer choice,String retailerId) throws Exception;
	
	
}
