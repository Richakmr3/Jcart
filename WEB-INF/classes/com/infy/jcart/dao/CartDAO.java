package com.infy.jcart.dao;

import java.util.List;

import com.infy.jcart.beans.Bill;
import com.infy.jcart.beans.ProductOffer;
import com.infy.jcart.beans.ProductVariations;

public interface CartDAO {

	public Double getProductPrice(Integer productId) throws Exception;
	
	public List<ProductVariations> getProductVariations(Integer productId) throws Exception;
	
	public Bill getProductDetails(Integer productId) throws Exception;
	
	public ProductOffer getOfferDetails(Integer offerCode) throws Exception;

}
