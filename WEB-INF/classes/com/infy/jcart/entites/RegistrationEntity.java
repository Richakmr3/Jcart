package com.infy.jcart.entites;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({

	@NamedQuery(name="getUserNameCount",query="select c from RegistrationEntity c where c.firstName=? and c.lastName=?"),
	@NamedQuery(name="getUserDetails",query="select c from RegistrationEntity c where c.userId=?")
	
}		
)
@Table(name="JCART_REGISTRATION")
public class RegistrationEntity {
	@Id
	@SequenceGenerator(name="registrationID",sequenceName="db_registrationID",allocationSize=1,initialValue=1017)
	@GeneratedValue(generator="registrationID",strategy=GenerationType.SEQUENCE)
	private Integer registrationId;
	private String userId;
	private String firstName;
	private String lastName;
	private Character gender;
	private String permanentAddress;
	private String shipmentAddress;
	private Long phoneNumber;
	private String securityQuestion;
	private String securityAnswer;
	@Temporal(TemporalType.DATE)
	private Calendar dob;
	private Character messageService;
	
	public Integer getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(Integer registrationId) {
		this.registrationId = registrationId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getShipmentAddress() {
		return shipmentAddress;
	}
	public void setShipmentAddress(String shipmentAddress) {
		this.shipmentAddress = shipmentAddress;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	
	public Calendar getDob() {
		return dob;
	}
	public void setDob(Calendar dob) {
		this.dob = dob;
	}
	public Character getMessageService() {
		return messageService;
	}
	public void setMessageService(Character messageService) {
		this.messageService = messageService;
	}
	public Character getGender() {
		return gender;
	}
	public void setGender(Character gender) {
		this.gender = gender;
	}
		
}
