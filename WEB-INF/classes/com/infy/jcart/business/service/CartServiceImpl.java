package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.Bill;
import com.infy.jcart.beans.Product;
import com.infy.jcart.beans.ProductVariations;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;

public class CartServiceImpl implements CartService {

	/***
	 * @description this method invokes getProductPrice() method of CartDAO
	 *              class if obtained price is 0 then throws an exception
	 *              otherwise returns the obtained price
	 * @param Integer
	 * @return Double
	 * @throws CartService.NOT_A_NEW_PRODUCT
	 * @catch Exception
	 */
	public Double getProductPrice(Integer productId) throws Exception {
		Double price = 0.0;
		try {
			price = Factory.createCartDAO().getProductPrice(productId);
			if (price == 0.0) {
				throw new Exception("CartService.NOT_A_NEW_PRODUCT");
			}
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getProductPrice",
					exception.getMessage());
			throw exception;
		}
		return price;
	}

	/***
	 * @description this method invokes getProductVariations()
     * 				method of CartDAO class and returns the list obtained
	 * @param Integer
	 * @return List<ProductVariations>
	 * @catch Exception
	 */
	public List<ProductVariations> getProductVariations(Integer productId)
			throws Exception {
		List<ProductVariations> list = null;
		try {
			list = Factory.createCartDAO().getProductVariations(productId);
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getProductVariations", exception.getMessage());
			throw exception;
		}
		return list;
	}

	
	/***
	 * @description this method invokes getProductDetails() of ViewIssueDAO 
     * 				class to get the product details,
     * 				then invokes getCategorySubcategory method of ViewMyOrdersDAO class
     * 				which returns a list of String containing first element as categoryName 
     * 				and second element as subCategoryName
     * 				then invokes getProductName method of ViewMyOrdersDAO class which returns
     * 				an Object array containing companyName as first element and productName as
     * 				second element 
     * 				then populates an Object array containing sellerId, categoryName, subCategoryName
     * 				companyName and productName and returns it
	 * @param Integer
	 * @return Object[]
	 * @catch Exception
	 */
	public Object[] getProductDetails(Integer productId) throws Exception {
		try {
						// get product details
						Product product = new Product();
						product = Factory.createViewIssueDAO().getProductDetails(productId);
						// get category n subcategory names
						List<String> categories = Factory.createViewMyOrdersDAO().getCategorySubcategory(product
								.getCategoryId(), product.getSubCategory());
						String categoryName = categories.get(0);
						String subCategoryName = categories.get(1);
						// get product name n company name
						Object[] ob = Factory.createViewMyOrdersDAO().getProductName(product.getCategoryId(),
								product.getProductId());
						String productName = null;
						String companyName = null;
						if (ob != null) {
							companyName = (String) ob[0];
							productName = (String) ob[1];
						}

						// populate the object array to be returned
						Object[] result = new Object[5];
						result[0] = product.getSellerId();
						result[1] = categoryName;
						result[2] = subCategoryName;
						result[3] = companyName;
						result[4] = productName;
						return result;
						
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getProductDetails", exception.getMessage());
			throw exception;
		}
		
	}

	
	
	
	/***
	 * @description this method invokes getProductDetails()
     * 				method of CartDAO class and returns the Bill obtained
	 * @param Integer
	 * @return Bill
	 * @catch Exception
	 */
	public Bill getProductDetailsById(Integer productId) throws Exception {
		Bill bill = new Bill();
		try {
			bill = Factory.createCartDAO().getProductDetails(productId);
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getProductDetailsById", exception.getMessage());
			throw exception;
		}
		return bill;
	}



}
