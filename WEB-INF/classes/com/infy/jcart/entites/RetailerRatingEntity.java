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
@Table(name = "JCART_RETAILERRATING")
public class RetailerRatingEntity {

	@Id
	@SequenceGenerator(name = "retailerRatingID", sequenceName = "db_retailerRatingID", allocationSize = 1, initialValue = 7006)
	@GeneratedValue(generator = "retailerRatingID", strategy = GenerationType.SEQUENCE)
	private Integer ratingId;
	private String retailerId;
	private String userId;
	private String reviewComments;
	private Integer rating;
	@Temporal(TemporalType.DATE)
	private Calendar dos;
	private Character status;

	public Integer getRatingId() {
		return ratingId;
	}

	public void setRatingId(Integer ratingId) {
		this.ratingId = ratingId;
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReviewComments() {
		return reviewComments;
	}

	public void setReviewComments(String reviewComments) {
		this.reviewComments = reviewComments;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Calendar getDos() {
		return dos;
	}

	public void setDos(Calendar dos) {
		this.dos = dos;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

}