package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.ProductVariations;

public interface VariantService {

	public String addVariant(ProductVariations productVariations)
			throws Exception;

	public List<ProductVariations> getAllVariants(Integer productId)
			throws Exception;



	public ProductVariations getVariantDetails(Integer productId,
			String variationCode) throws Exception;

	public void updateStock(ProductVariations to) throws Exception;
}
