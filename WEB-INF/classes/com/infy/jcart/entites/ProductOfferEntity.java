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
@Table(name="JCART_PRODUCTOFFER")
public class ProductOfferEntity {
	
	@Id
	@SequenceGenerator(name="offerCode",sequenceName="db_offerCode",allocationSize=1,initialValue=5007)
	@GeneratedValue(generator="offerCode",strategy=GenerationType.SEQUENCE)
	private Integer offerCode;
	private String offerDetails;
	private Double offerDiscount;
	@Temporal(TemporalType.DATE)
	private Calendar startDate;
	@Temporal(TemporalType.DATE)
	private Calendar endDate;
	
	public Integer getOfferCode() {
		return offerCode;
	}
	
	public void setOfferCode(Integer offerCode) {
		this.offerCode = offerCode;
	}
	public String getOfferDetails() {
		return offerDetails;
	}
	public void setOfferDetails(String offerDetails) {
		this.offerDetails = offerDetails;
	}
	
	public Double getOfferDiscount() {
		return offerDiscount;
	}
	public void setOfferDiscount(Double offerDiscount) {
		this.offerDiscount = offerDiscount;
	}
	public Calendar getStartDate() {
		return startDate;
	}
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	public Calendar getEndDate() {
		return endDate;
	}
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	
	
	
}