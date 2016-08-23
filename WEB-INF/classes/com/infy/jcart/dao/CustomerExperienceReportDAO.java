package com.infy.jcart.dao;

public interface CustomerExperienceReportDAO {

	public Integer numberOfReviews(Integer choice, Double min, Double max,String retailerId) throws Exception;
	
}
