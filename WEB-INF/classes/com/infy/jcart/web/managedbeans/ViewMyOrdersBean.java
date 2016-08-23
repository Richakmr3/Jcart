package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.jcart.beans.OrderLog;
import com.infy.jcart.beans.Payment;
import com.infy.jcart.beans.ProductVariations;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@SessionScoped
public class ViewMyOrdersBean {

	private List<OrderLog> orderList;
	private Integer paymentid;
	private Payment paymentTo;
	private String message;
	private Object[] productDetails;
	private List<ProductVariations> productList = new ArrayList<ProductVariations>();

	/***
	 *@description This method is used to populate the order details of the loggedin user
	 * 
	 * @exception Exception
	 */
	public ViewMyOrdersBean() {

		try {
			this.orderList = new ArrayList<OrderLog>();
			
			FacesContext ftx = FacesContext.getCurrentInstance();
			ExternalContext etx = ftx.getExternalContext();
			HttpSession session = (HttpSession) etx.getSession(true);

			String userId = (String) session.getAttribute("userId");
			
			this.orderList=Factory.createViewMyOrdersService().getMyOrders(userId);
		} 

		 catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "ViewMyOrdersBean", e
					.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}

	}

	public List<OrderLog> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderLog> orderList) {
		this.orderList = orderList;
	}

	public Integer getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(Integer paymentid) {
		this.paymentid = paymentid;
	}

	public Payment getPaymentTo() {
		return paymentTo;
	}

	public void setPaymentTo(Payment paymentTo) {
		this.paymentTo = paymentTo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ProductVariations> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductVariations> productList) {
		this.productList = productList;
	}

	public Object[] getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(Object[] productDetails) {
		this.productDetails = productDetails;
	}

	
	/***
	 *@description This method is used to retrieve the product details for a particular
	 * product id
	 * @return String
	 * @exception Exception
	 *
	 */
	public String getDetailsOfProduct() {
		String path=null;
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Map<String, String> params = context.getExternalContext()
					.getRequestParameterMap();
			Integer proid = Integer.parseInt(params.get("pid3"));
			
			this.productDetails=Factory.createViewMyOrdersService().getProductDetails(proid);
			path="/user/viewCart";
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"getDetailsOfProduct", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
				path="/pages/errorPage";
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
		return path;
	}

}
