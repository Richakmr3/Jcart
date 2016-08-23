package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.Bags;
import com.infy.jcart.beans.Books;
import com.infy.jcart.beans.Camera;
import com.infy.jcart.beans.Clothing;
import com.infy.jcart.beans.Computers;
import com.infy.jcart.beans.FootWear;
import com.infy.jcart.beans.Mobile;
import com.infy.jcart.beans.PurchaseHistory;
import com.infy.jcart.beans.Television;

public interface PurchaseService {

	//getBagProductList
	public List<Bags> getAllBagProducts(String subCategoryName,Character idealFor) throws Exception;
	
	//getBookProductList
	public List<Books> getAllBookProducts(String subCategoryName) throws Exception;
	
	//getCameraProductList
	public List<Camera> getAllCameraProducts(String subCategoryName) throws Exception;
	
	//getClothingProductList
	public List<Clothing> getAllClothingProducts(String subCategoryName,Character idealFor) throws Exception;
	
	//getComputerProductList
	public List<Computers> getAllComputerProducts(String subCategoryName) throws Exception;
	
	//getFootWearProductList
	public List<FootWear> getAllFootwearProducts(String subCategoryName,Character idealFor) throws Exception;
	
	//getTransactionDetails
	public List<PurchaseHistory> getTransactionDetails(String userId) throws Exception;
	
	//getMobileProductList
	public List<Mobile> getAllMobileProducts(String subCategoryName) throws Exception;
	
	//getTVProductList
	public List<Television> getAllTVProducts(String subCategoryName) throws Exception;
	

}
