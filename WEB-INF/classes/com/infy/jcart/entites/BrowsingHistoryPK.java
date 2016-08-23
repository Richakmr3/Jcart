package com.infy.jcart.entites;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class BrowsingHistoryPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastBrowsed;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Calendar getLastBrowsed() {
		return lastBrowsed;
	}
	public void setLastBrowsed(Calendar lastBrowsed) {
		this.lastBrowsed = lastBrowsed;
	}
	
	
	
}
