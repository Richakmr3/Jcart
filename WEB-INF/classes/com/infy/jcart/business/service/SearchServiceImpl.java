package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;

public class SearchServiceImpl implements SearchService {

	/***
	 * @description this method 
	 * invokes getSearchElementsByCategory() method of SearchDAOImpl class
	 * and returns the list obtained
	 * @param String , String
	 * @return List
	 * @throws SearchService.NO_SEARCH_DETAILS_FOUND
	 * @catch Exception
	 */
	@SuppressWarnings("rawtypes")
	public List getSearchElementsByCategory(String searchString,
			String selectedCategory) throws Exception {
		try {
			List t = Factory.createSearchDAO().getSearchElementsByCategory(searchString, selectedCategory);
			if (t == null || t.size() == 0) {
				throw new Exception("SearchService.NO_SEARCH_DETAILS_FOUND");
			} else {
				return t;
			}

		}catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getSearchElementsByCategory",
					exception.getMessage());
			throw exception;
		}
	}
	/***
	 * @description this method 
	 * invokes getSearchElementsInAllCategories() method of SearchDAOImpl class
	 * and returns the list obtained
	 * @param String 
	 * @return List
	 * @throws  SearchService.NO_SEARCH_DETAILS_FOUND
	 * @catch Exception
	 */
	@SuppressWarnings("rawtypes")
	public List getSearchElementsByAllCategory(String searchString)	throws Exception {

		try {
			List l = Factory.createSearchDAO().getSearchElementsInAllCategories(searchString);
			if (l == null || l.size() == 0) {
				throw new Exception("SearchService.NO_SEARCH_DETAILS_FOUND");
			} else {
				return l;
			}
		}	catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getSearchElementsByAllCategory",
					exception.getMessage());
			throw exception;
		}

	}

}
