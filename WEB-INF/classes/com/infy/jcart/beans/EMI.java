package com.infy.jcart.beans;

import java.util.Date;

public class EMI {
	
	private Integer emiId;
	private Integer paymentId;
	private Double rateOfInterest;
	private Integer duration;
	private Double duePayment;
	private Double emiAmount;
	private Double emiRemaining;
	private Date nextEmiDate;
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
	public Date getNextEmiDate() {
		return nextEmiDate;
	}
	public void setNextEmiDate(Date nextEmiDate) {
		this.nextEmiDate = nextEmiDate;
	}
	
}