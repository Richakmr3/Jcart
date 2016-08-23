package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.infy.jcart.beans.Clothing;
import com.infy.jcart.beans.ProductDetails;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@SessionScoped
public class PurchaseClothingBean {
	private String subCategoryName;
	private List<Clothing> productList;
	private String errorMessage;
	private HtmlDataTable selectedData;
	private ProductDetails productDetails;
	private HttpServletRequest request;
	private FacesContext ftx;
	private ExternalContext etx;
	private String idealFor;

	public String getIdealFor() {
		return idealFor;
	}

	public void setIdealFor(String idealFor) {
		this.idealFor = idealFor;
	}

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

	public List<Clothing> getProductList() {
		return productList;
	}

	public void setProductList(List<Clothing> productList) {
		this.productList = productList;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	/***
	 * @description  This method is used to retrieve the selected sub category
	 * 
	 * @exception Exception
	 */
	public PurchaseClothingBean() {
		try {
			ftx = FacesContext.getCurrentInstance();
			etx = ftx.getExternalContext();
			request = (HttpServletRequest) etx.getRequest();
			this.subCategoryName = request.getParameter("subCategory");
			this.productList = new ArrayList<Clothing>();
			this.errorMessage = "";
			
			this.productList.clear();
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"PurchaseClothingBean", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.errorMessage=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
	}

	/***
	 * @description This method is used to populate the clothing details for Men under the
	 * selected sub category
	 * @return String
	 * @exception Exception
	 */
	public String purchaseMensWear() {
		String path="";
		try {
			this.errorMessage = "";
			this.productList.clear();
			this.idealFor = "Men's Wear   -";
			ftx = FacesContext.getCurrentInstance();
			etx = ftx.getExternalContext();
			request = (HttpServletRequest) etx.getRequest();
			this.subCategoryName = request.getParameter("subCategory");
			this.productList = Factory.createPurchaseService().getAllClothingProducts(subCategoryName, 'M');
					
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "purchaseMensWear",
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

	/***
	 * @description This method is used to populate the clothing details for Women under the
	 * selected sub category
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String purchaseWomensWear() {
		String path="";
		try {
			this.errorMessage = "";
			this.productList.clear();
			this.idealFor = "Women's Wear    -";
			ftx = FacesContext.getCurrentInstance();
			etx = ftx.getExternalContext();
			request = (HttpServletRequest) etx.getRequest();
			this.subCategoryName = request.getParameter("subCategory");
			this.productList = Factory.createPurchaseService().getAllClothingProducts(subCategoryName, 'F');
					
			return "purchaseWomensFootWear";
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"purchaseWomensWear", e.toString());
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
