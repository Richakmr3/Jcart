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

import com.infy.jcart.beans.Category;
import com.infy.jcart.beans.ProductDetails;
import com.infy.jcart.beans.SubCategory;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;


@ManagedBean
@SessionScoped
public class ViewProductsBean {

	private Integer categoryId;
	private Integer subCategory;
	private List<ProductDetails> productList = new ArrayList<ProductDetails>();
	private List<SelectItem> categoryList = new ArrayList<SelectItem>();
	private List<SelectItem> subCategoryList = new ArrayList<SelectItem>();
	private String message;
	private String styleClass;

	/***
	 * @description This method is used to populate the category details
	 * 
	 * @exception Exception
	 */
	public ViewProductsBean() {
		this.message = null;
		this.categoryList.clear();
		try {

			List<Category> toList = Factory.createCategoryService().getAllCategories();
			for (Category to : toList) {
				this.categoryList.add(new SelectItem(to.getCategoryId(), to
						.getCategoryName()));
			}
		}  catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "ViewProductsBean", e
					.toString());
			if (e.getMessage().contains("DAO.TECHNICAL_ERROR")) 
			{
				ErrorRedirect.reDirect();
			}
			
		}
	}

	/***
	 *@description This method is used to retrieve all subcategories for the selected
	 * category
	 * @param ValueChangeEvent
	 * @return void
	 * @exception Exception
	 * 
	 */
	public void getSubCategories(ValueChangeEvent ve) {
		this.message = null;
		this.subCategoryList.clear();
		try {
			if (ve.getNewValue() == null) {
				message =JCartConfig.getMessageFromProperties("ViewProductsBean.VALID_CATEGORY");
				return;
			}
			this.categoryId = Integer.parseInt((String) ve.getNewValue());
			List<SubCategory> toList = Factory.createCategoryService()
					.getSubCategories(this.categoryId);
			for (SubCategory to : toList) {
				this.subCategoryList.add(new SelectItem(to.getSubCategoryId(),
						to.getSubCategoryName()));
			}
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "getSubCategories",
					e.toString());
			if (e.getMessage().contains("DAO.TECHNICAL_ERROR")) {
				ErrorRedirect.reDirect();
			}
		}
	}

	/***
	 * @description This method is used to retrieve the product details for the loggedin user
	 * (retailer) under selected category
	 *  @param ValueChangeEvent
	 * @return void
	 * @exception Exception
	 *
	 */
	public void getAllProducts(ValueChangeEvent ve) {
		this.message = null;
		this.productList.clear();
		try {

			if (ve.getNewValue() == null) {
				message = "Please select valid Sub Category";
				return;
			}
			FacesContext ftx = FacesContext.getCurrentInstance();
			ExternalContext etx = ftx.getExternalContext();
			HttpSession session = (HttpSession) etx.getSession(true);
			String retailerId = (String) session.getAttribute("userId");
			this.subCategory = Integer.parseInt(ve.getNewValue().toString());
			this.styleClass="successMsg";
			this.productList =Factory.createProductService().getRetailerProducts(
					this.subCategory, retailerId);
		} catch (Exception e) {
			this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			JCartLogger.logError(this.getClass().getName(), "getAllProducts", e
					.toString());
			if (e.getMessage().contains("DAO.TECHNICAL_ERROR")) {
				ErrorRedirect.reDirect();
			}
		}
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Integer subCategory) {
		this.subCategory = subCategory;
	}

	public List<SelectItem> getCategoryList() {
		return categoryList;
	}

	public List<ProductDetails> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDetails> productList) {
		this.productList = productList;
	}

	public void setCategoryList(List<SelectItem> categoryList) {
		this.categoryList = categoryList;
	}

	public List<SelectItem> getSubCategoryList() {
		return subCategoryList;
	}

	public void setSubCategoryList(List<SelectItem> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

}
