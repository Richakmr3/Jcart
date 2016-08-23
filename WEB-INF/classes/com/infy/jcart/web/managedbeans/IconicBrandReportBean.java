package com.infy.jcart.web.managedbeans;

import java.util.List;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class IconicBrandReportBean {
	private String message;
	private List<Entry<String, Double>> entries;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;	
	}

	public List<Entry<String, Double>> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry<String, Double>> entries) {
		this.entries = entries;
	}

}
