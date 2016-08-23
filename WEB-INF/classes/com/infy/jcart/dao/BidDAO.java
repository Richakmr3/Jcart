package com.infy.jcart.dao;

import java.util.List;

import com.infy.jcart.beans.Category;
import com.infy.jcart.beans.Product;
import com.infy.jcart.beans.ProductBid;
import com.infy.jcart.beans.ProductDetails;


public interface BidDAO {
	public List<Product> getProductsList(String userId) throws Exception;
	public List<Integer> getBiddedProductsList() throws Exception;	
	public List<ProductDetails> viewBiddingProducts()throws Exception;
	public void saveBid(ProductDetails productBidDetails)throws Exception;
	public ProductBid getLastBidAmount(Integer productId)throws Exception;
	public List<Category> getAllCategoryList()throws Exception;
	public Integer addUsedProductsForBidding(Product productDetails) throws Exception;
	
	
}
