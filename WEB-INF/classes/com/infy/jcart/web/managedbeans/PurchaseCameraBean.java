package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.infy.jcart.beans.Camera;
import com.infy.jcart.beans.ProductDetails;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;


@ManagedBean
@SessionScoped
public class PurchaseCameraBean {
	private String subCategoryName;
	private List<Camera> productList;
	private String errorMessage;
	private HtmlDataTable selectedData;
	private ProductDetails productDetails;
	private HttpServletRequest request;
	private FacesContext ftx;
	private ExternalContext etx;

	public ProductDetails getProductDetails() {
		return productDetails;
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<Camera> getProductList() {
		return productList;
	}

	public void setProductList(List<Camera> productList) {
		this.productList = productList;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	/****
	 * @description This method is used to retrieve the selected sub category
	 * 
	 * @exception Exception
	 */
	public PurchaseCameraBean() {
		try {
			ftx = FacesContext.getCurrentInstance();
			etx = ftx.getExternalContext();
			request = (HttpServletRequest) etx.getRequest();
			this.subCategoryName = request.getParameter("subCategory");
			this.productList = new ArrayList<Camera>();
			this.errorMessage = "";
			this.errorMessage = "";
			this.productList.clear();
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "PurchaseCameraBean",
					e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.errorMessage=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
	}

	/***
	 * @description This method is used to display camera details under the selected sub
	 * category
	 * @return String
	 * @exception  Exception
	 */
	public String purchaseCamera() {
		String path="";
		try {
			this.errorMessage = "";
			this.productList.clear();
			ftx = FacesContext.getCurrentInstance();
			etx = ftx.getExternalContext();
			request = (HttpServletRequest) etx.getRequest();
			this.subCategoryName = request.getParameter("subCategory");
			this.productList = Factory.createPurchaseService().getAllCameraProducts(subCategoryName);
					//.getCameraProductList(subCategoryName);
			path="/pages/purchaseCamera";
		}catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "purchaseCamera", e
					.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.errorMessage=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
		return path;
	}

}
