package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.jcart.beans.Registration;
import com.infy.jcart.business.service.EditProfileService;

import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@RequestScoped
@SuppressWarnings("unused")
public class ChangePasswordBean {

	private String password;
	private String newPassword;
	private String newpassword2;
	private String securityQuestion;
	private String answer;
	private String message;
	private String userId;
	private String style1;

	/****
	 * @description This method is used in getting the user's profile details for changing
	 * password
	 * 
	 * @exception Exception
	 */
	public ChangePasswordBean() {
		String path="";
		this.message="";
		try {
			
			
			EditProfileService editProfileService =Factory.createEditProfileServiceImpl();
			FacesContext ftx = FacesContext.getCurrentInstance();
			ExternalContext etx = ftx.getExternalContext();
			HttpSession session = (HttpSession) etx.getSession(true);

			userId = (String) session.getAttribute("userId");

			Registration to = editProfileService.getProfileDetails(userId);
	
			this.securityQuestion = to.getSecurityQuestion();
			//this.message=JCartConfig.getErrorMessage("EditProfileBean.UPDATE_SUCCESS");
		}  catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "ChangePasswordBean",
					exception.toString());
		if (exception.getMessage().contains("TECHNICAL_ERROR")) {
				path = "/pages/errorPage";
			}
				else{
					style1="red";
			this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
		}
		}
	}

	public String getStyle1() {
		return style1;
	}

	public void setStyle1(String style1) {
		this.style1 = style1;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNewpassword2() {
		return newpassword2;
	}

	public void setNewpassword2(String newpassword2) {
		this.newpassword2 = newpassword2;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	/****
	 * @description This method is used to change password for the user by getting new
	 * password
	 * 
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String changePassword() 
	{
		String path="";
		this.message="";
		try {

			EditProfileService man = Factory.createEditProfileServiceImpl();
			List<String> list = new ArrayList<String>();
			list.add(this.newPassword);
			list.add(this.newpassword2);
			list.add(this.password);
			list.add(this.userId);
			list.add(this.answer);
			int i = man.changePassword(list);
			style1="green";
			this.message = JCartConfig.getMessageFromProperties("ChangePasswordBean.PASSWORD_CHANGED");
			this.answer="";
		
		} catch (Exception exception) {
			
			style1="red";
			this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			path="";
			JCartLogger.logError(this.getClass().getName(), "ChangePassword",
					exception.toString());
			
			if (exception.getMessage().contains("TECHNICAL_ERROR")) 
			{
				path = "/pages/errorPage";
			}
		
		}
return path;
	}
}
