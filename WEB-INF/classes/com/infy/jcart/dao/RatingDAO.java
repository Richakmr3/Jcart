package com.infy.jcart.dao;

import java.util.List;

import com.infy.jcart.beans.Product;
import com.infy.jcart.beans.ProductRating;
import com.infy.jcart.beans.RetailerRating;

public interface RatingDAO {
	public void provideRetailerRating(RetailerRating rating) throws Exception;
	public void provideProductRating(ProductRating rating) throws Exception;
	public List<Product> getRetailersList() throws Exception;
	public List<Product> getProductsList() throws Exception;
	
}
