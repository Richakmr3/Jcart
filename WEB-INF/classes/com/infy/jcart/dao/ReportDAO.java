package com.infy.jcart.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.infy.jcart.beans.Registration;

public interface ReportDAO {

	
	
	public Map<String, Integer> getCategorySalesData(Calendar fromDate,	Calendar toDate) throws Exception;
	
	public Map<String, Integer> getSubCategorySalesData(String categoryName, Calendar fromDate, Calendar toDate) throws Exception;
	
	public List<Registration> getValuableCustomers() throws Exception;
	
	
	
}
