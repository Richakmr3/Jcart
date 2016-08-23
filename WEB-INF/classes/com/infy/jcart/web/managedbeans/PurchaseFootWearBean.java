package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.infy.jcart.beans.FootWear;
import com.infy.jcart.beans.ProductDetails;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;


@ManagedBean
@SessionScoped
public class PurchaseFootWearBean {

	private String subCategoryName;
	private List<FootWear> productList;
	private String errorMessage;
	private String idealFor;
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

	public List<FootWear> getProductList() {
		return productList;
	}

	public void setProductList(List<FootWear> productList) {
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
	public PurchaseFootWearBean() {
		try {
			ftx = FacesContext.getCurrentInstance();
			etx = ftx.getExternalContext();
			request = (HttpServletRequest) etx.getRequest();
			this.subCategoryName = request.getParameter("subCategory");
			this.productList = new ArrayList<FootWear>();
			this.errorMessage = "";
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"PurchaseFootWearBean", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.errorMessage
				=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
	}

	/***
	 * @description This method is used to populate Men's footwear details under the selected
	 * sub category
	 * @return String
	 * @exception  Exception
	 *
	 */
	public String purchaseMensFootWear() {
String path="";
		try {
			this.errorMessage = "";
			this.productList.clear();
			this.idealFor = "Men's FootWear   -";
			ftx = FacesContext.getCurrentInstance();
			etx = ftx.getExternalContext();
			request = (HttpServletRequest) etx.getRequest();
			this.subCategoryName = request.getParameter("subCategory");
			this.productList =Factory.createPurchaseService().getAllFootwearProducts(subCategoryName, 'M');
			
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"purchaseMensFootWear", e.toString());
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
	 * @description This method is used to populate Women's footwear details under the
	 * selected sub category
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String purchaseWomensFootWear() {
	String path="";
		try {
			this.errorMessage = "";
			this.productList.clear();
			this.idealFor = "Women's FootWear    -";
			ftx = FacesContext.getCurrentInstance();
			etx = ftx.getExternalContext();
			request = (HttpServletRequest) etx.getRequest();
			this.subCategoryName = request.getParameter("subCategory");
			this.productList =Factory.createPurchaseService().getAllFootwearProducts(subCategoryName, 'F');
			
		}  catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"purchaseWomensFootWear", e.toString());
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
