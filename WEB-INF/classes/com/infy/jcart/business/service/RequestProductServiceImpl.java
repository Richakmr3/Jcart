package com.infy.jcart.business.service;

import java.util.ArrayList;
import java.util.List;

import com.infy.jcart.beans.Category;
import com.infy.jcart.beans.SubCategory;
import com.infy.jcart.beans.UserProductRequest;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;



public class RequestProductServiceImpl implements RequestProductService{
	/***
	 * @description this method 
	 * invokes requestProduct() method of RequestProductDAOImpl class 
	 * and returns the request id obtained
	 * @param UserProductRequest
	 * @return Integer
	 * @throws  Exception
	 * @catch Exception
	 */
	public Integer requestProduct(UserProductRequest productDetails) throws Exception{
		Integer id=null;
		try {
			if(productDetails!=null){
				id= Factory.createRequestProductDAO().requestProduct(productDetails);
			}
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "requestProduct",
					exception.getMessage());
			throw exception;
		}
		return id;
	}
	/***
	 * @description this method 
	 * invokes getAllCategory() method of RequestProductDAOImpl class 
	 * and returns the list obtained
	 * @return List<Category>
	 * @throws  Exception
	 * @catch Exception
	 */
	public List<Category> getAllCategory() throws Exception{
		List<Category> categoryList=new ArrayList<Category>();
		try {
			categoryList=Factory.createRequestProductDAO().getAllCategory();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllCategory",
					exception.getMessage());
			throw exception;
		}
		return categoryList;
	}
	/***
	 * @description this method 
	 * invokes getAllSubCategory() method of RequestProductDAOImpl class 
	 * and returns the list obtained
	 * @param Integer
	 * @return List<SubCategory>
	 * @throws  Exception
	 * @catch Exception
	 */
	public List<SubCategory> getAllSubCategory(Integer categoryId) throws Exception{
		List<SubCategory> subCategoryList=new ArrayList<SubCategory>();
		try {
			subCategoryList=Factory.createRequestProductDAO().getAllSubCategory(categoryId);
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllSubCategory",
					exception.getMessage());
			throw exception;
		}
		return subCategoryList;
	}
}
