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

import com.infy.jcart.beans.Bags;
import com.infy.jcart.beans.Books;
import com.infy.jcart.beans.Camera;
import com.infy.jcart.beans.Category;
import com.infy.jcart.beans.Clothing;
import com.infy.jcart.beans.Computers;
import com.infy.jcart.beans.FootWear;
import com.infy.jcart.beans.Mobile;
import com.infy.jcart.beans.NewProducts;
import com.infy.jcart.beans.Product;
import com.infy.jcart.beans.SubCategory;
import com.infy.jcart.beans.Television;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@SessionScoped
public class AddProductBean {

	private String sellerId;
	private Character productType;
	private Integer subCategory;
	private Integer categoryId;
	private Object[] objarray = { new Bags(), new Books(), new Camera(),
			new Clothing(), new Computers(), new FootWear(),
			new Mobile(), new Television() };
	private NewProducts newProduct = new NewProducts();
	private List<SelectItem> categoryList = new ArrayList<SelectItem>();
	private List<SelectItem> subCategoryList = new ArrayList<SelectItem>();
	private List<SelectItem> offerCodeList = new ArrayList<SelectItem>();
	private String message;
	private String styleClass;

	/****
	 * @description This method is used to populate offer code and category
	 *              details
	 * @exception  Exception
	 */
	public AddProductBean() {
		this.message = null;
		this.styleClass = "";
		this.categoryList.clear();
		this.offerCodeList.clear();
		try {

			List<Integer> list = Factory.createOfferService().getAllOfferCodes();
			for (Integer obj : list) {
				this.offerCodeList.add(new SelectItem(obj));
			}

			List<Category> categoryList = Factory.createCategoryService().getAllCategories();
			for (Category c : categoryList) 
			{
				this.categoryList.add(new SelectItem(c.getCategoryId(), c
						.getCategoryName()));
				
			}
		} 
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "AddProductBean",
					exception.toString());
			this.styleClass ="errorMsg";
			this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			
		}
	}

	

	public String getSellerId() {
		return sellerId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public Character getProductType() {
		return productType;
	}

	public void setProductType(Character productType) {
		this.productType = productType;
	}

	public Integer getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Integer subCategory) {
		this.subCategory = subCategory;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public Object[] getobjarray() {
		return objarray;
	}

	public void setobjarray(Object[] os) {
		objarray = os;
	}

	public NewProducts getNewProduct() {
		return newProduct;
	}

	public void setNewProduct(NewProducts newProduct) {
		this.newProduct = newProduct;
	}

	public List<SelectItem> getOfferCodeList() {
		return offerCodeList;
	}

	public void setOfferCodeList(List<SelectItem> offerCodeList) {
		this.offerCodeList = offerCodeList;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	
	/****
	 * @description This method is used to get all sub categories for the
	 *              selected category
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
			List<SubCategory> subCatList = Factory.createCategoryService()
					.getSubCategories(this.categoryId);
			for (SubCategory sc : subCatList) {
				this.subCategoryList.add(new SelectItem(sc.getSubCategoryId(),
						sc.getSubCategoryName()));
			}
		} 
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "getSubCategories",
					exception.toString());
			this.styleClass ="errorMsg";
			this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			
		}
	}

	/***
	 * @description This method is used to select a sub category and assign the selected
	 * value
	 * 
	 * @param ValueChangeEvent
	 * @return void
	 * @exception Exception
	 * 
	 */
	public void selectSubCategory(ValueChangeEvent ve) {
		this.message = null;
		this.styleClass = "";
		try {
			this.subCategory = Integer.parseInt((String) ve.getNewValue());

		}
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "selectSubCategory",
					exception.toString());
			this.styleClass ="errorMsg";
			this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
		
			
		}
	}

	/***
	 * @description This method is used to add new products with the required product details
	 * 
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String addProduct() {
		String path="";
		this.message = null;
		this.styleClass = "";
		try {
			FacesContext ftx = FacesContext.getCurrentInstance();
			ExternalContext etx = ftx.getExternalContext();
			HttpSession session = (HttpSession) etx.getSession(true);

			this.sellerId = (String) session.getAttribute("userId");

			Product product = new Product();
			product.setCategoryId(categoryId);
			product.setProductType('N');
			product.setSellerId(sellerId);
			product.setSubCategory(subCategory);

			int productId = Factory.createProductService().addProduct(product, objarray,
					newProduct);
			this.message = JCartConfig.getMessageFromProperties("AddProductBean.PRODUCT_ADDED");
			this.message =	this.message + productId;
			this.styleClass = "successMsg";
			//path="/pages/retailer/AddNewProduct";
			path= "/pages/retailer/AddNewProductSuccess";
		} 
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "addProduct",
					exception.toString());
			this.styleClass ="errorMsg";
			this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
		path= "/pages/errorPage";
			}
		}
		return path;
	}

}
