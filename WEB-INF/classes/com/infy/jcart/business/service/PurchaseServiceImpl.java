package com.infy.jcart.business.service;

import java.util.ArrayList;
import java.util.Iterator;
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
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;

public class PurchaseServiceImpl implements PurchaseService {

	
	public List<Bags> getAllBagProducts(String subCategoryName,
			Character idealFor) throws Exception {
		List<Bags> bagList = null;
		try {
			bagList=Factory.createPurchaseDAO().getAllBagProducts(subCategoryName, idealFor);
			if (bagList.isEmpty()) {
				throw new Exception("PurchaseService.NO_PRODUCTS_AVAILABLE");
			}
		} 
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllBagProducts",
					exception.getMessage());
			throw exception;
		}
		return bagList;
	}

	
	public List<Books> getAllBookProducts(String subCategoryName)
			throws Exception {
		
		List<Books> bookList = null;
		try {
			bookList=Factory.createPurchaseDAO().getAllBookProducts(subCategoryName);
			if (bookList.isEmpty()) {
				throw new Exception("PurchaseService.NO_PRODUCTS_AVAILABLE");
			}
		} 
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllBookProducts",
					exception.getMessage());
			throw exception;
		}
		return bookList;
	}

	
	public List<Camera> getAllCameraProducts(String subCategoryName)
			throws Exception {
		List<Camera> camList = null;
		try {
			camList=Factory.createPurchaseDAO().getAllCameraProducts(subCategoryName);
			if (camList.isEmpty()) {
				throw new Exception("PurchaseService.NO_PRODUCTS_AVAILABLE");
			}
		} 
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllCameraProducts",
					exception.getMessage());
			throw exception;
		}
		return camList;
	}

	
	public List<Clothing> getAllClothingProducts(String subCategoryName,
			Character idealFor) throws Exception {
		List<Clothing> clothList = null;
		try {
			clothList=Factory.createPurchaseDAO().getAllClothingProducts(subCategoryName, idealFor);
			if (clothList.isEmpty()) {
				throw new Exception("PurchaseService.NO_PRODUCTS_AVAILABLE");
			}
		} 
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllCameraProducts",
					exception.getMessage());
			throw exception;
		}
		return clothList;
	}

	
	public List<Computers> getAllComputerProducts(String subCategoryName)
			throws Exception {
		List<Computers> compList = null;
		try {
			compList=Factory.createPurchaseDAO().getAllComputerProducts(subCategoryName);
			if (compList.isEmpty()) {
				throw new Exception("PurchaseService.NO_PRODUCTS_AVAILABLE");
			}
		} 
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllComputerProducts",
					exception.getMessage());
			throw exception;
		}
		return compList;
	}

	
	public List<FootWear> getAllFootwearProducts(String subCategoryName,
			Character idealFor) throws Exception {
		List<FootWear> footList = null;
		try {
			footList=Factory.createPurchaseDAO().getAllFootwearProducts(subCategoryName, idealFor);
			if (footList.isEmpty()) {
				throw new Exception("PurchaseService.NO_PRODUCTS_AVAILABLE");
			}
		} 
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllComputerProducts",
					exception.getMessage());
			throw exception;
		}
		return footList;
	}

	
	public List<PurchaseHistory> getTransactionDetails(String userId)
			throws Exception {
		List<PurchaseHistory> list=new ArrayList<PurchaseHistory>();
		try{
			List<PurchaseHistory> temp=Factory.createPurchaseDAO().getPaymentDetails(userId);	
				Iterator<PurchaseHistory> it=temp.iterator();
				while(it.hasNext()){
					PurchaseHistory purchaseHistory=it.next();
					List<Integer> ilist=purchaseHistory.getProductId();
					List<String> productname=new ArrayList<String>();
					Iterator<Integer> i=ilist.iterator();
					while(i.hasNext()){
					Integer pid=i.next();
					Object[] o=Factory.createCartService().getProductDetails(pid);
					productname.add(o[4].toString());
					}
					purchaseHistory.setProductName(productname);
					list.add(purchaseHistory);
				}
			}
			catch (Exception exception) {
				JCartLogger.logError(this.getClass().getName(), "getTransactionDetails",
						exception.getMessage());
				throw exception;
			}
		return list;
	}

	
	public List<Mobile> getAllMobileProducts(String subCategoryName)
			throws Exception {
		
		List<Mobile> mobList = null;
		try {
			mobList=Factory.createPurchaseDAO().getAllMobileProducts(subCategoryName);
			if (mobList.isEmpty()) {
				throw new Exception("PurchaseService.NO_PRODUCTS_AVAILABLE");
			}
		} 
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllMobileProducts",
					exception.getMessage());
			throw exception;
		}
		return mobList;
	}

	
	public List<Television> getAllTVProducts(String subCategoryName)
			throws Exception {
		
		List<Television> tvList = null;
		try {
			tvList=Factory.createPurchaseDAO().getAllTVProducts(subCategoryName);
			if (tvList.isEmpty()) {
				throw new Exception("PurchaseService.NO_PRODUCTS_AVAILABLE");
			}
		} 
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllTVProducts",
					exception.getMessage());
			throw exception;
		}
		return tvList;
	}

}
