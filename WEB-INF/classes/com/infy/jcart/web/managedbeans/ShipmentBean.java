package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



import com.infy.jcart.beans.OrderLog;

@ManagedBean
@SessionScoped

public class ShipmentBean {

	private Integer orderId;
	private List<OrderLog> ordersToShip = new ArrayList<OrderLog>();
	private List<OrderLog> ordersToDeliver = new ArrayList<OrderLog>();
	private List<OrderLog> ordersToReDeliver = new ArrayList<OrderLog>();
	private String message;
	private String styleClass;


	
		
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public List<OrderLog> getOrdersToShip() {
		return ordersToShip;
	}

	public void setOrdersToShip(List<OrderLog> ordersToShip) {
		this.ordersToShip = ordersToShip;
	}

	public List<OrderLog> getOrdersToDeliver() {
		return ordersToDeliver;
	}

	public void setOrdersToDeliver(List<OrderLog> ordersToDeliver) {
		this.ordersToDeliver = ordersToDeliver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<OrderLog> getOrdersToReDeliver() {
		return ordersToReDeliver;
	}

	public void setOrdersToReDeliver(List<OrderLog> ordersToReDeliver) {
		this.ordersToReDeliver = ordersToReDeliver;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

}
