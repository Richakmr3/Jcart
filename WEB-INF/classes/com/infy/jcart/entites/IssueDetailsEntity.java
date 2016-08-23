package com.infy.jcart.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="JCART_ISSUEDETAILS")
public class IssueDetailsEntity {
	
	@Id
	@SequenceGenerator(name="statusID",sequenceName="db_statusID",allocationSize=1,initialValue=60000)
	@GeneratedValue(generator="statusID",strategy=GenerationType.SEQUENCE)
	private Integer issueId;
	private String retailerId;
	private Integer productId;
	private String userId;
	private String description;
	private Character status;
	
	public Integer getIssueId() {
		return issueId;
	}
	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}
	public String getRetailerId() {
		return retailerId;
	}
	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	
}