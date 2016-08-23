package com.infy.jcart.business.service;


import java.util.List;

import com.infy.jcart.beans.OrderLog;

import com.infy.jcart.beans.Product;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;

public class ViewMyOrdersServiceImpl implements ViewMyOrdersService {

	/***
	 * @description this method 
	 * invokes getMyOrders() method of ViewMyOrdersDAO
	 * and returns the list obtained
	 * @param String
	 * @return List<OrderLog>
	 * @throws ViewMyOrdersService.NO_ORDERS_FOUND
	 * @catch Exception
	 */
	public List<OrderLog> getMyOrders(String userId)
			throws Exception {
		try {
			List<OrderLog> rl = Factory.createViewMyOrdersDAO().getMyOrders(userId);
			if (rl.size() == 0) {
				throw new Exception("ViewMyOrdersService.NO_ORDERS_FOUND");
			}
			return rl;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getMyOrders",
					exception.getMessage());
			throw exception;
		}
	}
	
	/***
	 * @description this method invokes getProductDetails() of ViewMyOrdersDAOImpl 
     * class to get the product details,
     * then invokes getCategorySubcategory method of ViewMyOrdersDAO class
     * which returns a list of String containing first element as categoryName 
     * and second element as subCategoryName
     * then invokes getProductName method of ViewMyOrdersDAO class which returns
     * an Object array containing companyName as first element and productName as
     * second element 
     * then populates an Object array containing sellerId, categoryName, subCategoryName
     * companyName and productName and returns it
	 * @param Integer
	 * @return List<ProductVariations>
	 * @throws Exception
	 * @catch Exception
	 */
	public Object[] getProductDetails(Integer proid) throws Exception {

		try {
			// get product details
			Product productTo = new Product();
			productTo = Factory.createViewIssueDAO().getProductDetails(proid);
			// get category n subcategory names
			List<String> cat = Factory.createViewMyOrdersDAO().getCategorySubcategory(productTo
					.getCategoryId(), productTo.getSubCategory());
			String categoryName = cat.get(0);
			String subCategoryName = cat.get(1);
			// get product name n company name
			Object[] ob = Factory.createViewMyOrdersDAO().getProductName(productTo.getCategoryId(),
					productTo.getProductId());
			String productName = null;
			String companyName = null;
			if (ob != null) {
				companyName = (String) ob[0];
				productName = (String) ob[1];
			}

			// populate the object array to be returned
			Object[] res = new Object[5];
			res[0] = productTo.getSellerId();
			res[1] = categoryName;
			res[2] = subCategoryName;
			res[3] = companyName;
			res[4] = productName;
			return res;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getProductDetails",
					exception.getMessage());
			throw exception;
		}

	}
}
