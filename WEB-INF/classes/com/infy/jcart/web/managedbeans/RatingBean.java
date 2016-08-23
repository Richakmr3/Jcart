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

import com.infy.jcart.beans.Product;
import com.infy.jcart.beans.ProductRating;
import com.infy.jcart.beans.RetailerRating;
import com.infy.jcart.business.service.RatingService;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;


@ManagedBean
@SessionScoped
public class RatingBean {

	private String userId;
	private String retailerId;
	private Integer productId;
	private String reviewComment;
	private Integer rating;
	private String errorMsg;
	private String successMsg;
	private List<SelectItem> retailerList;
	private List<SelectItem> productList;
	private String ratingType;
	private HttpSession session;
	private FacesContext ftx;
	private ExternalContext etx;

	public String getRatingType() {
		return ratingType;
	}

	public void setRatingType(String ratingType) {
		this.ratingType = ratingType;
	}

	public List<SelectItem> getRetailerList() {
		return retailerList;
	}

	public void setRetailerList(List<SelectItem> retailerList) {
		this.retailerList = retailerList;
	}

	public List<SelectItem> getProductList() {
		return productList;
	}

	public void setProductList(List<SelectItem> productList) {
		this.productList = productList;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getSuccessMsg() {
		return successMsg;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getReviewComments() {
		return reviewComment;
	}

	public void setReviewComments(String reviewComments) {
		this.reviewComment = reviewComments;
	}

	/***
	 *@description  This method is used retrieve the loggedin user id
	 * 
	 * @exception Exception
	 */
	public RatingBean() {
		
		try 
		{
			ftx = FacesContext.getCurrentInstance();
			etx = ftx.getExternalContext();
			session = (HttpSession) etx.getSession(true);
			/** set the user id from the session **/
			this.userId = (String) session.getAttribute("userId");
		} 
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "RatingBean",
					exception.toString());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.errorMsg=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
			
		}
	}

	/***
	 * @description This method is used to display the rating options for a selected
	 * product/retailer
	 * @param event
	 * @return void
	 * @exception Exception
	 */
	public void showOptionToRate(ValueChangeEvent event) {
		this.clear();
		retailerList = new ArrayList<SelectItem>();
		productList = new ArrayList<SelectItem>();
		List<Product> list1 = new ArrayList<Product>();
		try {

			this.ratingType = event.getNewValue().toString();
			if (this.ratingType.equals("retailer")) {
				this.retailerList.clear();
				list1.clear();
				list1 = Factory.createRatingService().getRetailersList();
				for (Product retailer : list1) {
					retailerList.add(new SelectItem(retailer.getSellerId()));
				}
			}
			if (this.ratingType.equals("product")) {
				productList.clear();
				list1.clear();
				list1 = Factory.createRatingService().getProductsList();
				for (Product product : list1) {
					productList.add(new SelectItem(product.getProductId()));
				}
			}
		} 
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "showOptionToRate",
					exception.toString());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
			ErrorRedirect.reDirect();
			}
			else{
				this.errorMsg=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
		}
	}

	/****
	 * @description This method is used to save the rating provided by the user for a
	 * product/retailer
	 * 
	 * @return String
	 * @exception Exception
	 */
	public String saveRating() {
		String path=null;
		ProductRating product = new ProductRating();
		RetailerRating retailer = new RetailerRating();
		RatingService ratingService = Factory.createRatingService();
		try {
			if (this.ratingType.equals("retailer")) {
				retailer.setRating(this.rating);
				retailer.setRetailerId(this.retailerId);
				retailer.setReviewComments(this.reviewComment);
				retailer.setUserId(this.userId);
				ratingService.provideRetailerRating(retailer);
				this.successMsg=JCartConfig.getMessageFromProperties("RatingBean.RATING_RETAILER_ACCEPTED");				
			}
			if (this.ratingType.equals("product")) {
				product.setRating(this.rating);
				product.setProductId(this.productId);
				product.setReviewComments(this.reviewComment);
				product.setUserId(this.userId);
				ratingService.provideProductRating(product);
				this.successMsg=JCartConfig.getMessageFromProperties("RatingBean.RATING_PRODUCT_ACCEPTED");
				
			}
			path= "";
		}
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "saveRating",
					exception.toString());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
				path="/pages/errorPage";
			}
			else{
				this.errorMsg=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
			
			
		}
		return path;

	}

	/***
	 * @description This method is used to clear fixed values
	 * 
	 * @return void
	 */
	public void clear() {
		this.errorMsg = "";
		this.successMsg = "";
		this.ratingType = "";
		this.productId = null;
		this.retailerId = null;

	}
}
