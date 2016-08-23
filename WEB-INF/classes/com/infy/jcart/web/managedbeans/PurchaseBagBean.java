package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;


import java.util.List;

import javax.faces.bean.ManagedBean;

import com.infy.jcart.beans.Bags;
import com.infy.jcart.beans.ProductDetails;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
public class PurchaseBagBean {
	private String subCategoryName;
	private List<Bags> productList;
	private String errorMessage;
	private String idealFor;
	private HtmlDataTable selectedData;
	private ProductDetails productDetails;
	private HttpServletRequest request;
	private FacesContext ftx;
	private ExternalContext etx;
	private String errorMsg;

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	public HtmlDataTable getSelectedData() {
		return selectedData;
	}

	public void setSelectedData(HtmlDataTable selectedData) {
		this.selectedData = selectedData;
	}

	public String getIdealFor() {
		return idealFor;
	}

	public void setIdealFor(String idealFor) {
		this.idealFor = idealFor;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<Bags> getProductList() {
		return productList;
	}

	public void setProductList(List<Bags> productList) {
		this.productList = productList;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	/****
	 * @description This method is used to populate the product detail and sub category name
	 * 
	 * @exception Exception
	 */
	public PurchaseBagBean() {
		try {
			ftx = FacesContext.getCurrentInstance();
			etx = ftx.getExternalContext();
			request = (HttpServletRequest) etx.getRequest();
			this.subCategoryName = request.getParameter("subCategory");
			this.productList = new ArrayList<Bags>();
			this.errorMessage = "";
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "PurchaseBagBean",
					exception.toString());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.errorMessage=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
		}
	}

	/***
	 *@description  This method is used to populate product(Bag) details ideal for Men under the
	 * selected sub category
	 * @return String
	 * @exception Exception
	 */
	public String purchaseMensBag() {
		String path="";
		try {
			this.errorMessage = "";
			this.productList.clear();
			this.idealFor = "Bags for Men    -";
			ftx = FacesContext.getCurrentInstance();
			etx = ftx.getExternalContext();
			request = (HttpServletRequest) etx.getRequest();
			this.subCategoryName = request.getParameter("subCategory");
			this.productList = Factory.createPurchaseService().getAllBagProducts(
					subCategoryName, 'M');
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "purchaseMensBag",
					e.toString());
			
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.errorMessage=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
		return path;

	}

	/****
	 * @description This method is used to populate product(Bag) details ideal for Women under the
	 * selected sub category
	 * 
	 * @return String
	 * @exception Exception
	 */
	public String purchaseWomensBag() {
		String path="";
		try {
			this.errorMessage = "";
			this.productList.clear();
			this.idealFor = "Bags for Women   -";
			ftx = FacesContext.getCurrentInstance();
			etx = ftx.getExternalContext();
			request = (HttpServletRequest) etx.getRequest();
			this.subCategoryName = request.getParameter("subCategory");
			this.productList = Factory.createPurchaseService().getAllBagProducts(
					subCategoryName, 'F');
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"purchaseWomensBag", exception.toString());
			
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.errorMessage=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
		}
		return path;
	}

}
