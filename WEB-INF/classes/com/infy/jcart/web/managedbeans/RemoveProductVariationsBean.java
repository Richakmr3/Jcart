package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.infy.jcart.beans.ProductDetails;
import com.infy.jcart.beans.ProductVariations;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;


@ManagedBean
@SessionScoped
public class RemoveProductVariationsBean {

	private Integer productId;
	private String variationCode;
	private List<SelectItem> productList = new ArrayList<SelectItem>();
	private List<SelectItem> variationCodeList = new ArrayList<SelectItem>();
	private List<ProductVariations> productVariations = new ArrayList<ProductVariations>();
	private String message;
	private String styleClass;

	/***
	 * @description This method is used to populate the product details of logged in retailer
	 * 
	 * @exception  Exception
	 */
	public RemoveProductVariationsBean() {
		this.message = null;
		this.productList.clear();
		this.variationCodeList.clear();
		try {
			FacesContext ftx = FacesContext.getCurrentInstance();
			ExternalContext etx = ftx.getExternalContext();
			HttpSession session = (HttpSession) etx.getSession(true);
			String retailerId = (String) session.getAttribute("userId");

			List<ProductDetails> list = Factory.createProductService()
					.getRetailerProducts(retailerId);

			for (ProductDetails to : list) {
				this.productList.add(new SelectItem(to.getProductId()));
			}
		}
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "RemoveProductVariationsBean",
					exception.toString());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			}

		}
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<SelectItem> getVariationCodeList() {
		return variationCodeList;
	}

	public void setVariationCodeList(List<SelectItem> variationCodeList) {
		this.variationCodeList = variationCodeList;
	}

	public String getVariationCode() {
		return variationCode;
	}

	public void setVariationCode(String variationCode) {
		this.variationCode = variationCode;
	}

	public List<SelectItem> getProductList() {
		return productList;
	}

	public void setProductList(List<SelectItem> productList) {
		this.productList = productList;
	}

	public List<ProductVariations> getProductVariations() {
		return productVariations;
	}

	public void setProductVariations(List<ProductVariations> productVariations) {
		this.productVariations = productVariations;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

}
