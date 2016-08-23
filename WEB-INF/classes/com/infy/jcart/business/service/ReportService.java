package com.infy.jcart.business.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.infy.jcart.beans.Registration;

public interface ReportService {
	
	public Map<String, Double> getCategorySalesData(Calendar fromDate,
			Calendar toDate) throws Exception;
	public Map<String, Double> getSubCategorySalesData(String categoryName,
			Calendar fromDate, Calendar toDate) throws 
			Exception;
	public List<Registration> getValuableCustomers()
	throws Exception;
	
	public Map<String, Double> sortByComparator(Map<String, Double> unsortMap);
	
	
}
