package com.infy.jcart.dao;

import java.util.List;

import com.infy.jcart.beans.ProductDetails;

public interface WishListDAO {

	public List<ProductDetails> getWishListDetails(String userId) throws Exception;
	
	public void removeFromWishList(ProductDetails productToRemove,String userId) throws Exception;
		
}
