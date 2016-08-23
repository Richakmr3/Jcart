package com.infy.jcart.web.managedbeans;

import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.jcart.beans.Registration;
import com.infy.jcart.business.service.EditProfileService;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@SessionScoped

public class EditProfileBean {

	private String firstName;
	private String lastName;
	private Date dob;
	private int flag;
	private Long phoneNo;
	private boolean disable;
	private String permanentAddress;
	private String shipmentAddress;
	private String message;
	private Integer regId;
	private String msMesssage;
	private String decMessage;
	private String userId;
	private Character messageService;
	private Character yes = 'Y';
	private Character no = 'N';

	/****
	 * @description This method is used to edit user's profile details
	 * 
	 * @exception * Exception
	 */
	public EditProfileBean() {

		try {
			Registration to = new Registration();
			EditProfileService man=Factory.createEditProfileServiceImpl();
			// changed from Strnig userId to this.userId
			FacesContext ftx = FacesContext.getCurrentInstance();
			ExternalContext etx = ftx.getExternalContext();
			HttpSession session = (HttpSession) etx.getSession(true);

			this.userId = (String) session.getAttribute("userId");

			to = man.getProfileDetails(userId);
			this.messageService = to.getMessageService();
			this.disable = true;
			this.flag = 0;
			this.firstName = to.getFirstName();
			this.lastName = to.getLastName();
			this.dob = to.getDob().getTime();
			this.regId = to.getRegistrationId();

			this.phoneNo = to.getPhoneNumber();

			this.permanentAddress = to.getPermanentAddress();
			this.shipmentAddress = to.getShipmentAddress();
		}  catch (Exception e) {

			JCartLogger.logError(this.getClass().getName(), "EditProfileBean",
					e.toString());

			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				this.message=JCartConfig.getMessageFromProperties(e.getMessage());
			}
		}

	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public Character getYes() {
		return yes;
	}

	public void setYes(Character yes) {
		this.yes = yes;
	}

	public Character getNo() {
		return no;
	}

	public void setNo(Character no) {
		this.no = no;
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

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public boolean isDisable() {
		return disable;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	/****
	 * @description This method changes the address flag to 0 or 1
	 * 
	 * @return String
	 */
	public String changeAddressFlag() {
		if (this.flag == 0) {
			this.flag = 1;
		} else if (this.flag == 1) {
			this.flag = 0;
		}
		return "";
	}

	public void setRegId(Integer regId) {
		this.regId = regId;
	}

	public Integer getRegId() {
		return regId;
	}

	public String editProfile() {
		this.disable = false;
		return "SamePage";
	}

	public String getMsMesssage() {
		return msMesssage;
	}

	public void setMsMesssage(String msMesssage) {
		this.msMesssage = msMesssage;
	}

	public String getDecMessage() {
		return decMessage;
	}

	public void setDecMessage(String decMessage) {
		this.decMessage = decMessage;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Character getMessageService() {
		return messageService;
	}

	public void setMessageService(Character messageService) {
		this.messageService = messageService;
	}

	/***
	 * @description This method is used in updating user profile details
	 * 
	 * 
	 * @return String
	 * @exception Exception
	 */
	@SuppressWarnings("unused")
	public String updateProfile() {
		String path="";
		try {
			EditProfileService man=Factory.createEditProfileServiceImpl();

			Registration to = new Registration();
			Calendar c = Calendar.getInstance();
			c.setTime(this.dob);
			to.setDob(c);
			to.setFirstName(this.firstName);
			to.setLastName(this.lastName);
			to.setPermanentAddress(this.permanentAddress);
			to.setPhoneNumber(this.phoneNo);
			to.setShipmentAddress(this.shipmentAddress);
			to.setRegistrationId(this.regId);

			int i = man.updateProfile(to);
			//this.styleClass="errorMsg";
			this.message=JCartConfig.getMessageFromProperties("EditProfileBean.UPDATE_SUCCESS");
			
			this.disable = true;


		} catch (Exception e) 
		{
			JCartLogger.logError(this.getClass().getName(), "updateProfile",
					e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
			path = "/pages/errorPage";
			}
			else
			{
				
			this.message=JCartConfig.getMessageFromProperties(e.getMessage());	
			}
		}
		return path;
	}

	/***
	 * @description This method is used to deactivate user account
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String deactivateAccount() {
		String path="";
		String messageTemp;
		try {
			EditProfileService man=Factory.createEditProfileServiceImpl();
			Boolean ret = man.deactivateUser(this.userId);
			
			if (ret == false) {
				messageTemp=JCartConfig.getMessageFromProperties("EditProfileBean.DEACTIVATE_ACCOUNT");
				this.setDecMessage(messageTemp);
				

			}
			this.decMessage=JCartConfig.getMessageFromProperties("EditProfileBean.SUCCESSFULLY_DEACTIVATED");
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "deactivateAccount",
					e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
			path = "/pages/errorPage";
			}
			else
			{
				this.decMessage=JCartConfig.getMessageFromProperties(e.getMessage());	
			}

		}
		return path;
	}

	/****
	 * @description This method is used to toggle message setting (activate/deactivate
	 * message) for the user based on selection
	 * 
	 * @return String
	 * @exception Exception
	 */
	public String toggleMessageSettings() {
		String messageTemp;
		String path="";
		try {

			Boolean ret =Factory.createEditProfileDAOImpl().editMessageSetting(this.userId); 

			if (ret == false) 
			{
				messageTemp=JCartConfig.getMessageFromProperties("EditProfileBean.ACCOUNT_ACTIVATED");
				this
				.setMsMesssage(messageTemp);
				this.messageService = 'Y';
			} else 
			{
				messageTemp=JCartConfig.getMessageFromProperties("EditProfileBean.ACCOUNT_DEACTIVATED");
				this
				.setMsMesssage(messageTemp);
				this.messageService = 'N';
			}
			path="";
			return "success";
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "toggleMessageSettings",
					e.toString());
			
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
			path = "/pages/errorPage";
			}

		}
		return path;
	}

}
