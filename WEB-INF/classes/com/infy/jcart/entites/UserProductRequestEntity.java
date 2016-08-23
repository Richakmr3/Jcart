package com.infy.jcart.entites;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="JCART_USERPRODUCTREQUEST")
public class UserProductRequestEntity {
	
	@Id
	@SequenceGenerator(name="requestID",sequenceName="db_requestID",allocationSize=1,initialValue=1008)
	@GeneratedValue(generator="requestID",strategy=GenerationType.SEQUENCE)
	private Integer requestId;
	private Integer subCategory;
	private String description;
	private String userId;
	private Character status;
	@Temporal(TemporalType.DATE)
	private Calendar requestDate;
	
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public Integer getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(Integer subCategory) {
		this.subCategory = subCategory;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	public Calendar getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Calendar requestDate) {
		this.requestDate = requestDate;
	}
	
	
}