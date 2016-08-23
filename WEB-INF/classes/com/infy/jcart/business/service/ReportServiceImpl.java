package com.infy.jcart.business.service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.infy.jcart.beans.Registration;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;

public class ReportServiceImpl implements ReportService {

	/***
	 * @description this method invokes getCategorySalesData() method of
	 *              ReportDAOImpl class finds out the percentage of sales for
	 *              each category by iterating over the obtained map populates a
	 *              map by putting category name as key and calculated
	 *              percentage as value and return it
	 * @param Calendar
	 *            , Calendar
	 * @return Map<String, Double>
	 * @throws ReportService.NO_DATA
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Double> getCategorySalesData(Calendar fromDate,
			Calendar toDate) throws Exception {
		try {
			Map<String, Integer> map = Factory.createReportDAO()
					.getCategorySalesData(fromDate, toDate);
			if (map.isEmpty()) {
				throw new Exception("ReportService.NO_DATA");
			}
			Map<String, Double> map1 = new HashMap<String, Double>();
			int total = 0;
			for (Object object : map.entrySet()) {
				Entry<String, Integer> entry = (Entry<String, Integer>) object;
				total = total + entry.getValue();
			}

			for (Object object : map.entrySet()) {
				Entry<String, Integer> entry = (Entry<String, Integer>) object;
				double d = (entry.getValue() * 100.0) / total;
				double drounded = Math.round(d * 100) / 100.0;
				map1.put(entry.getKey(), drounded);
			}
			Map<String, Double> mapSorted = sortByComparator(map1);
			return mapSorted;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getCategorySalesData", exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method invokes getSubCategorySalesData() method of
	 *              ReportDAOImpl class finds out the percentage of sales for
	 *              each subCategory by iterating over the obtained map
	 *              populates a map by putting subCategory name as key and
	 *              calculated percentage as value sort it by invoking
	 *              sortByComparator()method and return it
	 * @param String
	 *            ,Calendar, Calendar
	 * @return Map<String, Double>
	 * @throws ReportService.NO_DATA
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Double> getSubCategorySalesData(String categoryName,
			Calendar fromDate, Calendar toDate) throws Exception {
		try {
			Map<String, Integer> map = Factory.createReportDAO()
					.getSubCategorySalesData(categoryName, fromDate, toDate);
			if (map.isEmpty()) {
				throw new Exception("ReportService.NO_DATA");
			}
			Map<String, Double> map1 = new HashMap<String, Double>();
			int total = 0;
			for (Object object : map.entrySet()) {
				Entry<String, Integer> entry = (Entry<String, Integer>) object;
				total = total + entry.getValue();
			}

			for (Object object : map.entrySet()) {
				Entry<String, Integer> entry = (Entry<String, Integer>) object;
				double d = (entry.getValue() * 100.0) / total;
				double drounded = Math.round(d * 100) / 100.0;
				map1.put(entry.getKey(), drounded);
			}
			Map<String, Double> mapSorted = sortByComparator(map1);
			return mapSorted;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getCategorySalesData", exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method invokes getValuableCustomers() method of
	 *              ReportDAOImpl class and returns the obtained list
	 * @return List<Registration>
	 * @throws ReportService.NO_DATA
	 * @catch Exception
	 */
	public List<Registration> getValuableCustomers() throws Exception {
		try {
			List<Registration> list = Factory.createReportDAO()
					.getValuableCustomers();
			if (list.isEmpty()) {
				throw new Exception("ReportService.NO_DATA");
			}
			return list;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getValuableCustomers", exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method sorts the list of entry set by Comparator puts
	 *              the sorted list into Linked Hash Map and returns the map
	 * @param Map
	 *            <String, Double>
	 * @return Map<String, Double>
	 * @catch Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Double> sortByComparator(Map<String, Double> unsortMap) {

		List list = new LinkedList(unsortMap.entrySet());

		// sort list based on comparator
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue())
						.compareTo(((Map.Entry) (o1)).getValue());
			}
		});

		// put sorted list into map again
		// LinkedHashMap make sure order in which keys were inserted
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

}
