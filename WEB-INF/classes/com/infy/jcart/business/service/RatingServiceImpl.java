package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.Product;
import com.infy.jcart.beans.ProductRating;
import com.infy.jcart.beans.RetailerRating;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;



public class RatingServiceImpl implements RatingService{
	/***
	 * @description this method invokes provideRetailerRating() method of
	 *  RatingDAOImpl class
	 * @param RetailerRating
	 * @return void
	 * @throws Exception
	 * @catch Exception
	 */
	public void provideRetailerRating(RetailerRating rating) throws Exception{
		try {
			Factory.createRatingDAO().provideRetailerRating(rating);
		}  catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "provideRetailerRating",
					exception.getMessage());
			throw exception;
		}
	}
	/***
	 * @description this method invokes provideProductRating() method of
	 *  RatingDAOImpl class
	 * @param RetailerRating
	 * @return void
	 * @throws Exception
	 * @catch Exception
	 */
	public void provideProductRating(ProductRating rating) throws Exception{
		try {
			Factory.createRatingDAO().provideProductRating(rating);
		}  catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "provideProductRating",
					exception.getMessage());
			throw exception;
		}
	}
	
	/***
	 * @description this method invokes getRetailersList() method of
	 *  RatingDAOImpl class and returns the list obtained 
	 * @return List<Product>
	 * @throws RatingService.NO_RETAILER_FOUND
	 *  @catch Exception
	 */
	public List<Product> getRetailersList() throws Exception{
		List<Product> retailerList=null;	
		try {
			retailerList=Factory.createRatingDAO().getRetailersList();
			if(retailerList.isEmpty()){
				throw new Exception("RatingService.NO_RETAILER_FOUND");
			}
		} 
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getRetailersList",
					exception.getMessage());
			throw exception;
		}
		return retailerList;
	}
	/***
	 * @description this method invokes getProductsList() method of
	 *  RatingDAOImpl class and returns the list obtained 
	 * @return List<Product>
	 * @throws RatingService.NO_PRODUCTS_AVAILABLE
	 * @catch Exception
	 */
	public List<Product> getProductsList() throws Exception{
		List<Product> productList=null;
		try {
			productList=Factory.createRatingDAO().getProductsList();
			if(productList.isEmpty()){
				throw new Exception("RatingService.NO_PRODUCTS_AVAILABLE");
			}
		} 
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getProductsList",
					exception.getMessage());
			throw exception;
		}
		return productList;
	}
}
