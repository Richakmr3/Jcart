package com.infy.jcart.business.service;


import java.util.List;

import com.infy.jcart.beans.ProductDetails;

public interface WishListService {
	
	public List<ProductDetails> getWishListDetails(String userId) throws Exception;
	public void removeFromWishList(ProductDetails productToRemove,String userId)throws Exception;
}
