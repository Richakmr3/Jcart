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
@Table(name="JCART_EMI")
public class EMIEntity {
	
	@Id
	@SequenceGenerator(name="emiID",sequenceName="db_emiID",allocationSize=1,initialValue=70000)
	@GeneratedValue(generator="emiID",strategy=GenerationType.SEQUENCE)
	private Integer emiId;
	private Integer paymentId;
	private Double rateOfInterest;
	private Integer duration;
	private Double duePayment;
	private Double emiAmount;
	private Double emiRemaining;
	@Temporal(TemporalType.DATE)
	private Calendar nextEmiDate;
	
	public Integer getEmiId() {
		return emiId;
	}
	public void setEmiId(Integer emiId) {
		this.emiId = emiId;
	}
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	public Double getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(Double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Double getDuePayment() {
		return duePayment;
	}
	public void setDuePayment(Double duePayment) {
		this.duePayment = duePayment;
	}
	public Double getEmiAmount() {
		return emiAmount;
	}
	public void setEmiAmount(Double emiAmount) {
		this.emiAmount = emiAmount;
	}
	public Double getEmiRemaining() {
		return emiRemaining;
	}
	public void setEmiRemaining(Double emiRemaining) {
		this.emiRemaining = emiRemaining;
	}
	public Calendar getNextEmiDate() {
		return nextEmiDate;
	}
	public void setNextEmiDate(Calendar nextEmiDate) {
		this.nextEmiDate = nextEmiDate;
	}
	
	
}