package com.infy.jcart.dao;

import java.util.List;


import com.infy.jcart.beans.NewProducts;
import com.infy.jcart.beans.Product;
import com.infy.jcart.beans.ProductDetails;




public interface ProductDAO {

	public Integer addProduct(Product product, Object[] ob, NewProducts newProducts) throws Exception;
		
	public List<Product> getAllProducts(Integer subCategory) throws Exception ;
	
	public List<Product> getAllProductsByMe(Integer subCategory) throws Exception;
	
	public void removeProductsOnException(Integer productId) throws Exception;
	
	public List<ProductDetails> getRetailerProducts(Integer subCategory,String retailerId) throws Exception;
	
	public List<ProductDetails> getRecentlyAddedProducts() throws Exception;
	
	public List<ProductDetails> getMostPopularProducts() throws Exception;
	
	public List<ProductDetails> getProductsByRetailer(String retailerId,Integer categoryId) throws Exception;
	
	public List<ProductDetails> getRetailerProducts(String retailerId) throws Exception;
}
