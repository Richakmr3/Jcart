package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import com.infy.jcart.beans.Registration;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;



@ManagedBean
@RequestScoped
public class RegistrationBean {
	private String firstName;
	private String lastName;
	private String password;
	private String confirmPassword;
	private String permanentAddress;
	private String shipmentAddress;
	private Long phoneNumber;
	private String securityQuestion;
	private List<SelectItem> questions;
	private String securityAnswer;
	private Calendar dob;
	private Character messageService;
	private Character gender;
	private Character userType;
	private String message;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public List<SelectItem> getQuestions() {
		return questions;
	}

	public void setQuestions(List<SelectItem> questions) {
		this.questions = questions;
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

	public Character getUserType() {
		return userType;
	}

	public void setUserType(Character userType) {
		this.userType = userType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/***
	 * @description  This method is used to populate security question details for selection
	 * 
	 * @exception Exception
	 */
	public RegistrationBean() {
		try {
			this.questions = new ArrayList<SelectItem>();
			questions.add(new SelectItem("What is your favourite color?"));
			questions.add(new SelectItem("What is your favourite Sport?"));
			questions.add(new SelectItem("What was your childhood nickname?"));
			questions.add(new SelectItem("What was your dream job as a child?"));

		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "RegistrationBean",
					e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}
	}

	/***
	 * @description This method register an user by getting all the profile details
	 * 
	 * @return String
	 * @exception Exception
	 */
	public String registerUser() {
		String path="";
		try {
			Integer regId;
			String userId;
			if (!this.password.equals(this.confirmPassword)) {
				this.message = JCartConfig.getMessageFromProperties("RegistrationBean.MATCH_PASSWORD");
				return "failure";
			} 
			else 
			{
				Registration rto = new Registration();
				rto.setDob(dob);
				rto.setFirstName(firstName);
				rto.setLastName(lastName);
				rto.setPassword(password);
				rto.setPermanentAddress(permanentAddress);
				rto.setShipmentAddress(shipmentAddress);
				rto.setPhoneNumber(phoneNumber);
				rto.setSecurityQuestion(securityQuestion);
				rto.setSecurityAnswer(securityAnswer);
				rto.setMessageService(messageService);
				rto.setGender(gender);
				rto = Factory.createRegistrationService().registerNewMember(rto, userType);
				regId=rto.getRegistrationId();
				userId=rto.getUserId();
				this.message =JCartConfig.getMessageFromProperties("RegistrationBean.REGISTRATION_SUCCESS")+" : "
						+ " "+regId+" : "+userId;
			}
		} catch (Exception exception) 
		{
		
			JCartLogger.logError(this.getClass().getName(), "registerUser",
					exception.toString());
			this.message = exception.getMessage();
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR")){
				path = "/pages/errorPage";
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
			
			
		}
return path;
	}
}
