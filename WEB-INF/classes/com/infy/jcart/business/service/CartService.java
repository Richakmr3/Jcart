package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.Bill;
import com.infy.jcart.beans.ProductVariations;

public interface CartService {

	public Double getProductPrice(Integer productId) throws Exception;
	
	public List<ProductVariations> getProductVariations(Integer productId) throws Exception; 
	
	public Object[] getProductDetails(Integer productId) throws Exception;
	
	public Bill getProductDetailsById(Integer productId) throws Exception; 
	
	
}
