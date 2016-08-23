package com.infy.jcart.business.service;

import java.util.ArrayList;
import java.util.List;

import com.infy.jcart.beans.Category;
import com.infy.jcart.beans.Product;
import com.infy.jcart.beans.ProductBid;
import com.infy.jcart.beans.ProductDetails;
import com.infy.jcart.dao.BidDAO;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;


public class BidServiceImpl implements BidService{
	 /**
     * @description this method invokes getProductsList() method of BidDAOImpl class
     * and returns the obtained list
     * @param String
     * @return List <Product>
     * @throws BidService.NO_PRODUCTS_AVAILABLE
     * @catch Exception
     */
	public List<Product> getProductsList(String userId) throws Exception{
		List<Product> productList= new ArrayList<Product>();
		try {
			productList=Factory.createBidDAO().getProductsList(userId);
			return productList;
		} 
		 catch (Exception exception) {
				JCartLogger.logError(this.getClass().getName(), "getProductsList",
						exception.getMessage());
				throw exception;
			}
		
	}
	 /**
     * @description this method invokes getBiddedProductsList() method of BidDAOImpl class
     * returns the obtained list
     * 
     * @return List <Integer>
     * @throws Exception
     * @catch Exception
     */
	public List<Integer> getBiddedProductsList() throws Exception{
		
		try {
			return Factory.createBidDAO().getBiddedProductsList();
		}
	 catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getBiddedProductsList",
					exception.getMessage());
			throw exception;
		}
		
	}
	 
	 
	
	 /**
     * @description this method invokes viewBiddingProducts() method of BidDAOImpl class
     * and returns the obtained list
     *
     * @return List <ProductDetails>
     * @throws Exception
     * @catch Exception
     */
	public List<ProductDetails> viewBiddingProducts()throws Exception{
		try {
			return Factory.createBidDAO().viewBiddingProducts();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "viewBiddingProducts",
					exception.getMessage());
			throw exception;
		}
	}
	/**
     * @description this method invokes getLastBidAmount() method of BidDAOImpl class
     * if amount in  bid amount is less than the actual bid amount then 
     * LesserBidAmountException is thrown otherwise saveBid() method of BidDAOImpl
     * class is invoked
     
     * @param ProductDetails
     * @return void
     * @throws Exception
     * @catch Exception
     */
	public void saveBid(ProductDetails productBidDetails)throws Exception{
		BidDAO service=Factory.createBidDAO();
		ProductBid bidTO=new ProductBid();
		try {
			bidTO=service.getLastBidAmount(productBidDetails.getProductId());
			if(productBidDetails.getBidAmount() < bidTO.getBasePrice()){
				/** throw exception if bid amount is less than actual bid price **/
				throw new Exception("BidService.LESSER_BIDAMOUNT");
			}
			else{
				/** invoke the BidDAOImpl class method named saveBid by passing the argument **/
				service.saveBid(productBidDetails);
			}
		} 
		 catch (Exception exception) {
				JCartLogger.logError(this.getClass().getName(), "saveBid",
						exception.getMessage());
				throw exception;
			}
	}
	/**
     * @description this method invokes getAllCategoryList() method of BidDAOImpl class
     * and returns the obtained list
     * @return List<Category>
     * @throws Exception
     * @catch Exception
     */
	public List<Category> getAllCategoryList() throws Exception{
		try {
			return Factory.createBidDAO().getAllCategoryList();
		} 
		 catch (Exception exception) {
				JCartLogger.logError(this.getClass().getName(), "getAllCategoryList",
						exception.getMessage());
				throw exception;
			}
	}
	
	/**
     * @description this method invokes addUsedProductsForBidding() method of BidDAOImpl class
     * and returns the obtained Product
     * @param Product
     * @return Integer
     * @throws Exception
     * @catch Exception
     */
	public Integer addUsedProductsForBidding(Product productDetails) throws Exception{
		try {
			return Factory.createBidDAO().addUsedProductsForBidding(productDetails);
		} 
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "addUsedProductsForBidding",
					exception.getMessage());
			throw exception;
		}
	}
}
