package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.jcart.beans.Bill;
import com.infy.jcart.beans.Cart;

import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@SessionScoped
public class BillingBean {
	private String message;
	private Double discountedBill;
	private List<Bill> billDetails = new ArrayList<Bill>();
	private Integer discountPoints;
	private Integer pointsToUse;
	private String userId;
	private Boolean validpoints;
	private Character paymentType;
	private int numOfMonths;
	private String usePointsMessage;
	private boolean paymentFlag;
	private String shipmentAddressOption;
	private String styleClass;
	public String getStyleClass() {
		return styleClass;
	}
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public boolean isPaymentFlag() {
		return paymentFlag;
	}

	public void setPaymentFlag(boolean paymentFlag) {
		this.paymentFlag = paymentFlag;
	}

	public String getUsePointsMessage() {
		return usePointsMessage;
	}

	public void setUsePointsMessage(String usePointsMessage) {
		this.usePointsMessage = usePointsMessage;
	}

	public int getNumOfMonths() {
		return numOfMonths;
	}

	public void setNumOfMonths(int numOfMonths) {
		this.numOfMonths = numOfMonths;
	}

	public Character getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Character paymentType) {
		this.paymentType = paymentType;
	}

	public Boolean getValidpoints() {
		return validpoints;
	}

	public void setValidpoints(Boolean validpoints) {
		this.validpoints = validpoints;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getDiscountPoints() {
		return discountPoints;
	}

	public void setDiscountPoints(Integer discountPoints) {
		this.discountPoints = discountPoints;
	}

	public List<Bill> getBillDetails() {
		return billDetails;
	}

	public void setBillDetails(List<Bill> billDetails) {
		this.billDetails = billDetails;
	}

	public Double getDiscountedBill() {
		return discountedBill;
	}

	public void setDiscountedBill(Double discountedBill) {
		this.discountedBill = discountedBill;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getPointsToUse() {
		return pointsToUse;
	}

	public void setPointsToUse(Integer pointsToUse) {
		this.pointsToUse = pointsToUse;
	}

	public String getShipmentAddressOption() {
		return shipmentAddressOption;
	}

	public void setShipmentAddressOption(String shipmentAddressOption) {
		this.shipmentAddressOption = shipmentAddressOption;
	}

	/***
	 * @description This method is used to retrieve discount points of logged in
	 *              user
	 * 
	 * @exception Exception
	 */
	public BillingBean() {
		try {
			this.paymentFlag = false;
			validpoints = true;

			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext()
					.getSession(true);
			this.userId = (String) session.getAttribute("userId");



		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "BillingBean",
					e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();

			}
			else{
				this.styleClass="errorMsg";
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}

	}

	/****
	 * @description This method is used in generating bill for the products
	 *              present in cart
	 * 
	 * @return String
	 * @exception Exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String generateBill() {
		String path=null;
		/* for having fresh bean after every purchase on click of continue */
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext etx = ctx.getExternalContext();

		discountedBill = 0.0;
		billDetails = new ArrayList<Bill>();
		try {

			HttpSession session = (HttpSession) etx.getSession(true);
			List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
			if (cartList == null || cartList.size() == 0) {
				throw new Exception(JCartConfig.getMessageFromProperties("BillingBean.CART_EMPTY"));
			}
			for (Cart o : cartList) {
				if (o.getSelected() == true) {
					String productId = o.getProductId();
					////////////////////////////////////////////////////////////////////////////////////////////////////
					Bill to = Factory.createCartService().getProductDetailsById(Integer.parseInt(productId)); // method name changed
					//////////////////////////////////////////////////////////////////////////////////////////////////
					// setting product name from object array
					to.setProductName(o.getProductName());

					Double discoutprice = to.getPrice()
							* (1 - (to.getDiscount() / 100));

					if (to.getOffercode() != null) {

						if ((to.getOfferStartDate().getTimeInMillis() - new Date()
						.getTime()) <= 0) {
							if (to.getOfferEndDate().getTime().after(new Date())) {
								discoutprice = discoutprice
										* (1 - (to.getOfferDiscount() / 100));
							}

						}
					}
					to.setDiscountedPrice(discoutprice);

					billDetails.add(to);
					discountedBill += to.getDiscountedPrice();
				}
			}
			path= "BillDetails";

		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "generateBill",
					e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				path= "/pages/errorPage";}
		}
		return path;
	}

}
