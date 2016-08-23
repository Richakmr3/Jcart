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
public class PopularProductsBean {
	String path=null;
	private List<ProductDetails> popularProducts = new ArrayList<ProductDetails>();
	private String message;

	/*****
	 * @description This method is used to populate the popular product details
	 * 
	 * @exception Exception
	 */
	public PopularProductsBean() {
		this.message = null;
		this.popularProducts.clear();
		try 
		{
			this.popularProducts = Factory.createProductService()
					.getMostPopularProducts();
		}
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "PopularProductsBean",
					exception.toString());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
			
		}
	}

	public List<ProductDetails> getPopularProducts() {
		return popularProducts;
	}

	public void setPopularProducts(List<ProductDetails> popularProducts) {
		this.popularProducts = popularProducts;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
