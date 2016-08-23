package com.infy.jcart.web.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.jcart.beans.ProductDetails;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@SessionScoped
public class WishListBean {

	private List<ProductDetails> wishList;
	private String userId;
	private String errorMessage;
	private HtmlDataTable productToRemove;
	private HttpSession session;
	private FacesContext ftx;
	private ExternalContext etx;

	public HtmlDataTable getProductToRemove() {
		return productToRemove;
	}

	public void setProductToRemove(HtmlDataTable productToRemove) {
		this.productToRemove = productToRemove;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<ProductDetails> getWishList() {
		return wishList;
	}

	public void setWishList(List<ProductDetails> wishList) {
		this.wishList = wishList;
	}

	/***
	 *@description This method is used to populate the wishlist details of loggedin user
	 * 
	 * @exception Exception
	 */

	public WishListBean() {
		try {
			ftx = FacesContext.getCurrentInstance();
			etx = ftx.getExternalContext();
			session = (HttpSession) etx.getSession(true);
			/** set the user id from the session **/
			this.userId = session.getAttribute("userId").toString();
			/** invoke the manager class method and initialize the list **/
			
			this.wishList=Factory.createWishListService().getWishListDetails(this.userId);
		} catch (Exception e) {
			
			JCartLogger.logError(this.getClass().getName(), "WishListBean", e
					.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.errorMessage=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
	}

	/***
	 * @description This method is used to remove selected item from wishlist
	 * @return String
	 * @exception Exception
	 *
	 */

	public String removeFromWishList() 
	{
		String path=null;
		try {
			/**
			 * invoke the manager class method and remove the product from the
			 * wish list
			 **/
			ProductDetails selectedProduct = (ProductDetails) productToRemove.getRowData();
			
			Factory.createWishListService().removeFromWishList(selectedProduct, userId);
			/** invoke the manager class method and populate wish list **/
			
			
			this.wishList=Factory.createWishListService().getWishListDetails(this.userId);
			if(wishList.isEmpty())
			{
				throw new Exception("WishListService.WISHLIST_EMPTY");
			}
			path= "/pages/user/viewWishList";
		} catch (Exception e) 
		{
			JCartLogger.logError(this.getClass().getName(),
					"removeFromWishList", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
			path="/user/errorPage";
			}
			else
			{
			
				this.errorMessage=JCartConfig.getMessageFromProperties(e.getMessage());
				path= "/pages/user/viewWishList";	
			}
		}
		return path;
	}
}
