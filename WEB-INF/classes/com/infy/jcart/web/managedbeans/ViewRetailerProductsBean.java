package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import com.infy.jcart.beans.Category;
import com.infy.jcart.beans.ProductDetails;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;


@ManagedBean
@RequestScoped

public class ViewRetailerProductsBean {

	private Integer categoryId;
	private String retailerId;
	private List<ProductDetails> productList = new ArrayList<ProductDetails>();
	private List<SelectItem> categoryList = new ArrayList<SelectItem>();
	private List<SelectItem> retailerList = new ArrayList<SelectItem>();
	private String message;

	/***
	 * @description This method is used to populate the retailer and category details
	 * 
	 * @exception Exception
	 */
	public ViewRetailerProductsBean() {
		this.message = null;
		this.categoryList.clear();
		try {

			List<String> l = Factory.createRetailerService().getAllRetailerIds();
			for (String id : l) {
				retailerList.add(new SelectItem(id));
			}

			List<Category> catList = Factory.createCategoryService().getAllCategories();
			for (Category c : catList) {
				this.categoryList.add(new SelectItem(c.getCategoryId(), c
						.getCategoryName()));
			}

		} 
		catch (Exception exception){
		
			JCartLogger.logError(this.getClass().getName(), "ViewRetailerProductsBean",
					exception.toString());			
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
		}

	}

	/**
	 *@description This method is used to populate product details for a particular retailer
	 * under selected category
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String getProductsByRetailer() {
		String path=null;
		this.message = null;
		this.productList.clear();
		try {
			this.productList = Factory.createProductService().getProductsByRetailer(
					this.retailerId, this.categoryId);
			path= "";
		} 
		catch (Exception exception) {
			
			JCartLogger.logError(this.getClass().getName(), "getProductsByRetailer",
					exception.toString());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
				path="/pages/errorPage";
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
			
		}
		return path;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public List<ProductDetails> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDetails> productList) {
		this.productList = productList;
	}

	public List<SelectItem> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<SelectItem> categoryList) {
		this.categoryList = categoryList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<SelectItem> getRetailerList() {
		return retailerList;
	}

	public void setRetailerList(List<SelectItem> retailerList) {
		this.retailerList = retailerList;
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

}
