package com.infy.jcart.business.service;


import java.util.List;

import com.infy.jcart.beans.ProductDetails;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;

public class WishListServiceImpl implements WishListService {
	 /**
     * @description this method invokes getWishListDetails()
     * method of WishListDAOImpl class and returns the list obtained
     * @param String
     * @return List<ProductDetails>
     * @throws WishListService.WISHLIST_EMPTY 
     * @catch Exception
     */
	public List<ProductDetails> getWishListDetails(String userId) throws Exception{
		List<ProductDetails> wishList=null;
		try {
			/** invoke the service class method and initialize the list**/
			wishList=Factory.createWishListDAO().getWishListDetails(userId);
			
		}catch (Exception exception) {
			
			JCartLogger.logError(this.getClass().getName(), "getWishListDetails",
					exception.getMessage());
			throw exception;
		}
		/** return the wish list details **/
		return wishList;
	}
	 /**
     * @description this method invokes removeFromWishList()
     * method of WishListDAO class
     * @param ProductDetailsTO, String
     * @return void
     * @throws Exception
     * @catch Exception
     */
	public void removeFromWishList(ProductDetails productToRemove,String userId)throws Exception{
		try {
			/** invoke the service class method and remove the product from wish list **/
			Factory.createWishListDAO().removeFromWishList(productToRemove,userId);
		}catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "removeFromWishList",
					exception.getMessage());
			throw exception;
		}
	}
}
