package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.infy.jcart.beans.Category;
import com.infy.jcart.beans.SubCategory;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;


@ManagedBean
@SessionScoped
public class SubCategoryBean {

	private Integer categoryId;
	private Integer subCategoryId;
	private String subCategoryName;
	private List<SelectItem> categoryList = new ArrayList<SelectItem>();
	private List<SelectItem> subCategoryList = new ArrayList<SelectItem>();
	private String message;
	private String styleClass;

	/***
	 * @description This method is used to populate all category details for selection
	 * 
	 * @exception Exception
	 * 
	 */
	public SubCategoryBean() {
		this.message = null;
		this.categoryList.clear();
		try {

			List<Category> catList = Factory.createCategoryService().getAllCategories();
			for (Category c : catList) {
				this.categoryList.add(new SelectItem(c.getCategoryId(), c
						.getCategoryName()));
			}
		} 
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "SubCategoryBean",
					exception.toString());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
			
		}
	}

	/***
	 * @description This method is used to retrieve all the subcategory details for the
	 * selected category
	 * @param ValueChangeEvent
	 * @return void
	 * @exception Exception
	 */
	public void getSubCategories(ValueChangeEvent ve) {
		this.message = null;
		this.subCategoryList.clear();
		try {

			this.categoryId = Integer.parseInt((String) ve.getNewValue());
			List<SubCategory> catList = Factory.createCategoryService()
					.getSubCategories(categoryId);
			for (SubCategory sc : catList) {
				this.subCategoryList.add(new SelectItem(sc.getSubCategoryId(),
						sc.getSubCategoryName()));
			}
		} 
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "getSubCategories",
					exception.toString());
			
			if (exception.getMessage().contains("TECHNICAL_ERROR")) {
				ErrorRedirect.reDirect();
			}			
			else{
				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
			
			
		}
	}


	/**
	 * @description This method is used to remove a particular subcategory
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String removeSubCategory() {
		String path=null;
		this.message = null;
		try {

			Factory.createCategoryService().removeSubCategory(subCategoryId);
			this.message = JCartConfig.getMessageFromProperties("SubCategoryBean.SUBCATEGORY_REMOVED");
			this.message =this.message + subCategoryId;					
			this.styleClass = "successMsg";
			path= "";
		}
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "removeSubCategory",
					exception.toString());
			if (exception.getMessage().contains("TECHNICAL_ERROR")) {
				ErrorRedirect.reDirect();
			}			
			else{
				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
			
		}
		return path;
	}

	public Integer getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public List<SelectItem> getCategoryList() {
		return categoryList;
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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

}
