package com.infy.jcart.dao;

import java.util.List;

import com.infy.jcart.beans.ProductVariations;

public interface VariantDAO {

	public String addVariant(ProductVariations variations) throws Exception;
	
	public String getNextVariationCode(Integer productId) throws Exception;
	
	public void addImage(Integer productId, String variationCode,String imageUrl) throws Exception;
	
	public List<ProductVariations> getAllVariants(Integer productId) throws Exception;
	
	
	
	
	
	public ProductVariations getVariantDetails(Integer productId,String variationCode) throws Exception;
	
	public void updateStock(ProductVariations variations) throws Exception;
	
}
