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
import com.infy.jcart.beans.SubCategory;
import com.infy.jcart.beans.UserProductRequest;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;


@ManagedBean
@SessionScoped

public class RequestProductBean {

	private List<SelectItem> categoryList = new ArrayList<SelectItem>();
	private List<SelectItem> subCategoryList = new ArrayList<SelectItem>();
	private String description;
	private String userId;
	private String errorMessage;
	private Integer subCategoryId;
	private String succesMessage;
	private HttpSession session;
	private FacesContext ftx;
	private ExternalContext etx;

	public String getSuccesMessage() {
		return succesMessage;
	}

	public void setSuccesMessage(String succesMessage) {
		this.succesMessage = succesMessage;
	}

	public Integer getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<SelectItem> getSubCategoryList() {
		return subCategoryList;
	}

	public List<SelectItem> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<SelectItem> categoryList) {
		this.categoryList = categoryList;
	}

	public void setSubCategoryList(List<SelectItem> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/***
	 * @description This method is used to retirieve loggedin userid and populate the
	 * available category details for selection
	 * 
	 * @exception Exception
	 */
	public RequestProductBean() {
		ftx = FacesContext.getCurrentInstance();
		etx = ftx.getExternalContext();
		session = (HttpSession) etx.getSession(true);
		List<Category> list1 = new ArrayList<Category>();
		try {
			/** set the user id from the session **/
			this.userId = session.getAttribute("userId").toString();

			categoryList.clear();
			list1 = Factory.createRequestProductService().getAllCategory();
			for (Category category : list1) {
				categoryList.add(new SelectItem(category.getCategoryId(),
						category.getCategoryName()));
			}
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "RequestProductBean", exception
					.toString());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else
			{
				this.errorMessage=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
		}
	}

	/***
	 * @description This method is used to populate all the sub category details for the
	 * selected category
	 * @param event
	 * @return void
	 * 
	 * @exception Exception
	 */
	public void populateSubCategory(ValueChangeEvent event) {
		List<SubCategory> list1 = new ArrayList<SubCategory>();
		try {
			subCategoryList.clear();
			Integer categoryId = Integer.parseInt(event.getNewValue()
					.toString());
			list1 = Factory.createRequestProductService().getAllSubCategory(categoryId);
			for (SubCategory subCategory : list1) {
				subCategoryList.add(new SelectItem(subCategory
						.getSubCategoryId(), subCategory.getSubCategoryName()));
			}
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),"populateSubCategory", exception
					.toString());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
				ErrorRedirect.reDirect();
			}
			else
			{
				this.errorMessage=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
		}
	}

	/***
	 * @description This method is used to retrieve the subcategoryid for the selected
	 * subcategory
	 * @param event
	 * @return void
	 * @exception Exception
	 */
	public void getSubCategory(ValueChangeEvent event) {
		try {
			subCategoryId = Integer.parseInt(event.getNewValue().toString());
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),"getSubCategory", exception
					.toString());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
				ErrorRedirect.reDirect();
			}
			else
			{
				this.errorMessage=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
		}
	}

	/***
	 * @description This method is used to add new product request details
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String requestProduct() {
		String path="";
		try {
			UserProductRequest productDetails = new UserProductRequest();
			productDetails.setDescription(this.description);
			productDetails.setSubCategory(this.subCategoryId);
			productDetails.setUserId(this.userId);
			Integer id =Factory.createRequestProductService().requestProduct(productDetails);
			this.succesMessage= JCartConfig.getMessageFromProperties("RequestProductBean.REQUEST_PRODUCT_SUCCESS");
			 
			this.succesMessage = this.succesMessage+ id;	
		
					} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),"requestProduct", exception
					.toString());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
				path = "/pages/errorPage";
			}
			else
			{
				this.errorMessage=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
			
			
		}
		return path;
	}
}
