package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.infy.jcart.beans.ProductDetails;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;


@ManagedBean
@RequestScoped
public class RecentlyAddedProductsBean {

	private List<ProductDetails> recentProducts = new ArrayList<ProductDetails>();
	private String message;

	/***
	 * @description This method is used to retrieve details of recently added products
	 * 
	 * @exception Exception
	 */
	public RecentlyAddedProductsBean() {
		this.message = null;
		this.recentProducts.clear();
		try {

			this.recentProducts = Factory.createProductService()
					.getRecentlyAddedProducts();

		}
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "RecentlyAddedProductsBean",
					exception.toString());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
		}
	}

	public List<ProductDetails> getRecentProducts() {
		return recentProducts;
	}

	public void setRecentProducts(List<ProductDetails> recentProducts) {
		this.recentProducts = recentProducts;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
