package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.jcart.beans.Cart;
import com.infy.jcart.beans.ProductVariations;
import com.infy.jcart.beans.Selected;
import com.infy.jcart.business.service.CartService;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@SessionScoped
public class CartBean {

	private List<Object[]> productsList = new ArrayList<Object[]>();

	private Double totalAmount;
	private String message;

	private List<ProductVariations> variations;

	private Object[] productDetails;
	private List<Selected> rBooleanList = new ArrayList<Selected>();
	private List<Cart> cartList;

	/****
	 * @description This method is used to populate the user's cart details
	 */
	@SuppressWarnings("unchecked")
	public CartBean() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext etx = ctx.getExternalContext();
		HttpSession session = (HttpSession) etx.getSession(true);
		List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
		if (cartList == null || cartList.size() == 0) {
			this.cartList=new ArrayList<Cart>();
		}else{
			this.cartList=cartList;
		}
	}

	public List<Selected> getRBooleanList() {
		return rBooleanList;
	}

	public void setRBooleanList(List<Selected> booleanList) {
		rBooleanList = booleanList;
	}

	public List<Object[]> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<Object[]> productsList) {
		this.productsList = productsList;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object[] getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(Object[] productDetails) {
		this.productDetails = productDetails;
	}

	public List<ProductVariations> getvariations() {
		return variations;
	}

	public void setvariations(List<ProductVariations> variations) {
		this.variations = variations;
	}

	public List<Cart> getCartList() {
		return cartList;
	}

	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}

	/****
	 * @description This method is used to view variation and product details of selected
	 * product
	 * 
	 * @return String
	 * @exception Exception
	 */
	public String viewProductDetails() {
		String path=null;
		try {

			
			FacesContext context=FacesContext.getCurrentInstance();
			Map<String,String> params=context.getExternalContext

().getRequestParameterMap();
			
			Integer prodId=Integer.parseInt(params.get("param2"));
			

			CartService man = Factory.createCartService();
			this.productDetails = man.getProductDetails(prodId);
			this.variations = man.getProductVariations(prodId);
			
			
			
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put

("selectedProductId", prodId.toString());
			
			path= "/pages/ViewProductVariations";

		} 
		catch(Exception exception){	
			
			JCartLogger.logError(this.getClass().getName(), "viewProductDetails",
					exception.toString());
			path="/pages/errorPage";
			
		}
		return path;
	}

	/***
	 *@description This method is used in viewing user's cart
	 * 
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String viewMycart() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext etx = ctx.getExternalContext();
		HttpSession session = (HttpSession) etx.getSession(true);
		List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
		if (cartList == null || cartList.size() == 0) {
			this.cartList=new ArrayList<Cart>();
		}else{
			this.cartList=cartList;
		}
		return "/pages/user/viewCart";
	}

	/*****
	 * @description This method is used to add products to cart
	 * 
	 * @return String
	 * @exception Exception
	 */
	public String addToCart() {
		String path=null;
		try {
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext().getSession(true);

			if(this.variations.size()!=0){
			for (ProductVariations variations : this.variations) {
				if (variations.getSelected()) 
				{
					Cart cart = new Cart();
					cart.setSelected(false);
					cart.setSellerId(this.productDetails[0].toString());
					cart.setCategoryName(this.productDetails[1].toString());
					cart
							.setSubCategoryName(this.productDetails[2]
									.toString());
					cart.setCompanyName(this.productDetails[3].toString());
					cart.setProductName(this.productDetails[4].toString());
					cart.setVariations(variations);
					cart.setProductId(variations.getProductId().toString());

					cartList.add(cart);

				}
			}
			}
			else{
				FacesContext context=FacesContext.getCurrentInstance();
				Map<String,Object> params=context.getExternalContext().getSessionMap

();
				String productId=params.get("selectedProductId").toString();
				
				
				Cart cart = new Cart();
				cart.setSelected(false);
				cart.setSellerId(this.productDetails[0].toString());
				cart.setCategoryName(this.productDetails[1].toString());
				cart.setSubCategoryName(this.productDetails[2].toString());
				cart.setCompanyName(this.productDetails[3].toString());
				cart.setProductName(this.productDetails[4].toString());
				cart.setVariations(null);
				cart.setProductId(productId);

				cartList.add(cart);
			}
			session.setAttribute("cartList", cartList);
			path= "/pages/searchPage";
		}

	
		catch(Exception exception){			
			JCartLogger.logError(this.getClass().getName(), "addToCart",
					exception.toString());
			path="/pages/errorPage";
			
		}
		return path;
	}

	/****
	 * @description This method is used to remove products from cart and update the cart
	 * 
	 * @return String
	 */
	public String removeItems() {
String path="";
try{
		FacesContext ftx = FacesContext.getCurrentInstance();
		ExternalContext etx = ftx.getExternalContext();
		HttpSession session = (HttpSession) etx.getSession(true);

		List<Cart> nCartList = new ArrayList<Cart>();

		for (int i = 0; i < cartList.size(); i++) {
			Cart cart = cartList.get(i);
			if (cart.getSelected() == false) {
				nCartList.add(cartList.get(i));
			}

		}
		if(nCartList.size()==cartList.size())
		{
			throw new Exception("CartBean.SELECT_ITEM_TO_REMOVE");
		}
		
		cartList = new ArrayList<Cart>();
		cartList.addAll(nCartList);

		session.setAttribute("cartList", cartList);
		this.message = JCartConfig.getMessageFromProperties("CartBean.ITEMS_REMOVED");	

}catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "removeItems",
					exception.toString());
			if (exception.getMessage().contains("DAO.TECHNICAL_ERROR")) {
				path = "/pages/errorPage";
			}

		}
		return path;



	}
}
