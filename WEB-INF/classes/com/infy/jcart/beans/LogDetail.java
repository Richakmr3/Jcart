package com.infy.jcart.beans;

import java.util.Calendar;

public class LogDetail {
	
	private Integer logId;
	private String userId;
	private Calendar logoutTime;
	private Character role; 
	private boolean selected;
	
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Calendar getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Calendar logoutTime) {
		this.logoutTime = logoutTime;
	}
	public Character getRole() {
		return role;
	}
	public void setRole(Character role) {
		this.role = role;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}