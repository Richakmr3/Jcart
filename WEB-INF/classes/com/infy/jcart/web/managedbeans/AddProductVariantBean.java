package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
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

public class AddProductVariantBean {
	private Integer productId;
	private String variationCode;
	private List<ProductDetails> allProducts = new ArrayList<ProductDetails>();
	private ProductDetails productDetails = null;
	private ProductVariations productVariations = new ProductVariations();
	private List<SelectItem> productList = new ArrayList<SelectItem>();
	private String message;
	private String styleClass;

	/***
	 *@description This method is used to populate corresponding retailer product
	 * 
	 * @exception Exception
	 */
	public AddProductVariantBean() {
		this.message = null;
		this.styleClass = "";
		this.productList.clear();
		try {
			FacesContext ftx = FacesContext.getCurrentInstance();
			ExternalContext etx = ftx.getExternalContext();
			HttpSession session = (HttpSession) etx.getSession(true);
			String retailerId = (String) session.getAttribute("userId");

			this.allProducts = Factory.createProductService()
					.getRetailerProducts(retailerId);

			for (ProductDetails pd : this.allProducts) {
				if (!pd.getCategory().equals("Books"))
					this.productList.add(new SelectItem(pd.getProductId()));
			}

		} 
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "AddProductVariantBean",
					exception.toString());
			
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else
			{
				styleClass="errorMsg";
				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
			
		}
	}

	/****
	 * @description This method is used to get the selected product details
	 * 
	 * @param ValueChangeEvent
	 * @return void
	 * @exception Exception
	 */
	public void getProductDetails(ValueChangeEvent ve) {
		this.message = null;
		this.styleClass = "";
		this.productDetails = new ProductDetails();
		try {

			this.productId = Integer.parseInt(ve.getNewValue().toString());
			this.productDetails.setProductId(productId);
			for (ProductDetails pd : this.allProducts) {
				if (pd.getProductId().equals(productId)) {
					this.productDetails = pd;
					break;
				}
			}
			
		} 
		catch(Exception exception){		
			this.styleClass = "errorMsg";
			JCartLogger.logError(this.getClass().getName(), "getProductDetails",
					exception.toString());
					this.message =JCartConfig.getMessageFromProperties(exception.getMessage());
			ErrorRedirect.reDirect();
			
			
		}
	}

	/****
	 * @description This method is used to add new product variant details
	 * 
	 * @return String
	 * @exception Exception
	 */
	public String addVariant() {
		String path=null;
		this.message = null;
		this.styleClass = "";
		try {
			this.productVariations.setProductId(this.productDetails
					.getProductId());
			this.variationCode =Factory.createVariantService()
					.addVariant(this.productVariations);
			this.message = JCartConfig.getMessageFromProperties("AddProductVariantBean.VARIANT_SUCCESS");
			this.message = this.message+ productVariations.getProductId()
					 +", " + this.variationCode;
			this.styleClass = "successMsg";
			this.productDetails = null;
			this.productVariations = new ProductVariations();
			path= "/pages/retaile/AddProductVariantSuccess";
		}
		catch(Exception exception){			
			this.styleClass = "errorMsg";
			JCartLogger.logError(this.getClass().getName(), "addVariant",
					exception.toString());
			this.message =JCartConfig.getMessageFromProperties(exception.getMessage());
			path= "/pages/errorPage";
			
			
		}
		return path;
	}

	public ProductVariations getProductVariations() {
		return productVariations;
	}

	public void setProductVariations(ProductVariations productVariations) {
		this.productVariations = productVariations;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<SelectItem> getProductList() {
		return productList;
	}

	public void setProductList(List<SelectItem> productList) {
		this.productList = productList;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getVariationCode() {
		return variationCode;
	}

	public void setVariationCode(String variationCode) {
		this.variationCode = variationCode;
	}

	public List<ProductDetails> getAllProducts() {
		return allProducts;
	}

	public void setAllProducts(List<ProductDetails> allProducts) {
		this.allProducts = allProducts;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

}
