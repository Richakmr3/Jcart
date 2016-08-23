package com.infy.jcart.business.service;

import java.util.LinkedHashMap;
import java.util.Map;

import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;

public class CustomerExperienceReportServiceImpl implements
		CustomerExperienceReportService {

	/***
	 * @description this method invokes numberOfReviews() method of
	 *              CustomerExperienceReportDAO interface for each range of ratings
	 *              sums up the number of reviews for each range then populates
	 *              the map by putting each range as key and percentage of
	 *              reviews for that range as value
	 * @param Integer
	 *            ,String
	 * @return Map<String, Double>
	 * @catch Exception
	 */
	public Map<String, Double> getCustomerExperience(Integer choice,
			String retailerId) throws Exception {
		Map<String, Double> map = new LinkedHashMap<String, Double>();
		try {
			int range1 =Factory.createCustomerExperienceReportDAO().numberOfReviews(
					choice, 4.6, 5.0, retailerId);

			int range2 = Factory.createCustomerExperienceReportDAO().numberOfReviews(
					choice, 3.1, 4.5, retailerId);

			int range3 = Factory.createCustomerExperienceReportDAO().numberOfReviews(
					choice, 2.1, 3.0, retailerId);

			int range4 = Factory.createCustomerExperienceReportDAO().numberOfReviews(
					choice, 0.0, 2.0, retailerId);

			int sum = range1 + range2 + range3 + range4;
			map.put("Excellent (4.6 - 5.0)", Math.round(range1 * 100.0 / sum
					* 100) / 100.0);
			map.put("Good (3.1 - 4.5)",
					Math.round(range2 * 100.0 / sum * 100) / 100.0);
			map.put("Average (2.1 - 3.0)", Math.round(range3 * 100.0 / sum
					* 100) / 100.0);
			map.put("Poor (0.0 - 2.0)",
					Math.round(range4 * 100.0 / sum * 100) / 100.0);
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getCustomerExperience", exception.getMessage());
			throw exception;
		}
		return map;
	}

}
