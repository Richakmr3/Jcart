package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.infy.jcart.beans.Category;
import com.infy.jcart.beans.Product;
import com.infy.jcart.beans.ProductVariations;
import com.infy.jcart.beans.SubCategory;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@SessionScoped
public class UpdateStockBean {

	private Integer productId;
	private String variationCode;
	private Integer categoryId;
	private Integer subCategory;
	private Integer existingStock;
	private Integer stock;
	private List<SelectItem> categoryList = new ArrayList<SelectItem>();
	private List<SelectItem> subCategoryList = new ArrayList<SelectItem>();
	private List<SelectItem> productList = new ArrayList<SelectItem>();
	private List<SelectItem> variantList = new ArrayList<SelectItem>();
	private String message;
	private String styleClass;

	/***
	 * @description This method is used to populate the product category details
	 * 
	 * @exception Exception
	 */
	public UpdateStockBean() {
		this.message = null;
		this.styleClass = "";
		this.categoryList.clear();
		try {

			
			List<Category> toList=Factory.createCategoryService().getAllCategories();
			for (Category to : toList) {
				this.categoryList.add(new SelectItem(to.getCategoryId(), to
						.getCategoryName()));
			}
		}
		 catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "UpdateStockBean", e
					.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
			
		}
	}

	/***
	 * @description This method is used to retrieve all the subcategory details under the
	 * selected category
	 * @param ValueChangeEvent
	 * @return void
	 * @exception Exception

	 * 
	 */
	public void getSubCategories(ValueChangeEvent ve) {
		this.message = null;
		this.styleClass = "";
		this.subCategoryList.clear();
		try {
			this.categoryId = Integer.parseInt((String) ve.getNewValue());
			
			List<SubCategory> toList=Factory.createCategoryService().getSubCategories(this.categoryId);
			for (SubCategory to : toList) {
				this.subCategoryList.add(new SelectItem(to.getSubCategoryId(),
						to.getSubCategoryName()));
			}
		}  catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "getSubCategories",
					e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
			
		}
	}

	/***
	 * @description This method is used to retrieve all the products available under the
	 * selected subcategory
	 * @param ValueChangeEvent
	 * @return void
	 * @exception Exception
	 * 
	 */
	public void getAllProducts(ValueChangeEvent ve) {
		this.message = null;
		this.styleClass = "";
		this.productList.clear();
		try {

			this.subCategory = Integer.parseInt(ve.getNewValue().toString());
			
			List<Product> list=Factory.createProductService().getAllProductsByMe(this.subCategory);
			for (Product to : list) {
				this.productList.add(new SelectItem(to.getProductId(), to
						.getProductName()));
			}
		} catch (Exception e) {
			this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			this.styleClass="errorMessage";
			JCartLogger.logError(this.getClass().getName(), "getAllProducts", e
					.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
			
		}
	}

	/***
	 * @description This method is used to retrieve all the variation details for the
	 * selected productid
	 * @param ValueChangeEvent
	 * @return void
	 * @exception  Exception
	 *
	 */
	public void getAllVariants(ValueChangeEvent ve) {
		this.message = null;
		this.styleClass = "";
		this.variantList.clear();
		try {

			this.productId = Integer.parseInt(ve.getNewValue().toString());
			
			List<ProductVariations> list=Factory.createVariantService().getAllVariants(this.productId);
			for (ProductVariations to : list) {
				this.variantList.add(new SelectItem(to.getVariationCode(), to
						.getVariationCode()
						+ "-" + to.getColor()));
			
			}
		} catch (Exception e) {
			//e.printStackTrace();
			this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			this.styleClass="errorMessage";
			JCartLogger.logError(this.getClass().getName(), "getAllVariants", e
					.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
				}
	}

	/***
	 * @description This method is used to populate available stock for the selected product
	 * variation code
	 * @param ValueChangeEvent
	 * @return void
	 * @exception Exception
	 * 
	 * 
	 */
	public void populateExistingStock(ValueChangeEvent ve) {
		this.message = null;
		this.styleClass = "";
		try {

			this.variationCode = ve.getNewValue().toString();
			ProductVariations to = Factory.createVariantService().getVariantDetails(this.productId, this.variationCode);
			this.existingStock = to.getStock();
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"populateExistingStock", e.toString());
		if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
			ErrorRedirect.reDirect();
		}
		else{
			this.message=JCartConfig.getMessageFromProperties(e.getMessage());
		}
		}
	}

	/***
	 * @description This method is used to update the stock for a particular product
	 * variation
	 * @return String
	 * @exception Exception
	 *
	 */
	public String updateStock() {
		String path=null;
		this.message = null;
		this.styleClass = "";
		try {
			ProductVariations to = new ProductVariations();
			to.setProductId(productId);
			to.setVariationCode(variationCode);
			to.setStock(stock);
			
			Factory.createVariantService().updateStock(to);
			this.message = JCartConfig.getMessageFromProperties("UpdateStockBean.UPDATE_SUCCESS");
			this.subCategoryList.clear();
			this.productList.clear();
			this.variantList.clear();
			this.existingStock = stock;
			this.variationCode = null;
			this.stock = null;
			this.styleClass = "successMsg";
			path="";
		} catch (Exception e) 
		{
			JCartLogger.logError(this.getClass().getName(), "updateStock", e
					.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
			path= "/pages/errorPage";
			}
			else
			{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
		return path;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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

	public List<SelectItem> getProductList() {
		return productList;
	}

	public void setProductList(List<SelectItem> productList) {
		this.productList = productList;
	}

	public List<SelectItem> getVariantList() {
		return variantList;
	}

	public void setVariantList(List<SelectItem> variantList) {
		this.variantList = variantList;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getVariationCode() {
		return variationCode;
	}

	public void setVariationCode(String variationCode) {
		this.variationCode = variationCode;
	}

	public Integer getExistingStock() {
		return existingStock;
	}

	public void setExistingStock(Integer existingStock) {
		this.existingStock = existingStock;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

}
