package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.OrderLog;

public interface ShipmentService {
	public List<OrderLog> getOrdersToDeliver() throws Exception;
	public List<OrderLog> getOrdersToShip() throws Exception;
	public List<OrderLog> getOrdersToReDeliver() throws Exception;
	public void deliverProducts(List<Integer> list) throws Exception;

	

	
}
