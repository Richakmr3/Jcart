package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.ProductOffer;

public interface OfferService {

	public Integer fixOfferDetails(ProductOffer offer) throws Exception;
	
	public void updateOfferDetails(List<ProductOffer> offerList) throws Exception;
	
	public void removeOfferDetails(Integer offerCode) throws Exception;
	
	public ProductOffer getOfferDetails(Integer offerCode) throws Exception;
	
	public List<Integer> getAllOfferCodes() throws Exception;
	
	public List<ProductOffer> getAllOffers() throws Exception;
	
}
