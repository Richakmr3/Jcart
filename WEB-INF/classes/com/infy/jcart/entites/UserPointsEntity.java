package com.infy.jcart.entites;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JCART_USERPOINTS")
public class UserPointsEntity {

	@Id
	private String userId;
	private Integer discountPoints;
	
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
	
}
