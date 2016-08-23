package com.infy.jcart.web.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.jcart.beans.Login;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@SessionScoped
public class LoginBean {



	public LoginBean() {
		this.attempts = 0;
	}
	private String userId;
	private String password;
	private Integer attempts;
	private String message;
	private String styleClass;

	public String getUserId() 
	{
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAttempts() {
		return attempts;
	}

	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/****
	 * @description This method does user login and lock the account if user
	 *              fails to login after 3 attempts
	 * @return String
	 * @throws LoginBean.ACCOUNT_LOCKED
	 * @catch Exception
	 */
	public String doLogin() {
		String path = "";
		this.styleClass = "";
		this.message = null;
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		String id = (String) session.getAttribute("userId");
		try {
			if (id == null || !(id.equals(userId)))
				{
				attempts = 0;
				}
			session.setAttribute("userId", userId);

			Login login = new Login();
			login.setUserId(userId);
			login.setPassword(password);
			if (attempts >= 3) 
			{
			
				Factory.createLoginService().lockAccount(login);
				this.styleClass = "errorMsg";
				throw new Exception("LoginBean.ACCOUNT_LOCKED");
			}
			Login log = Factory.createLoginService().authorize(login);
			
			if(log.getStatus()=='R')
			{
				throw new Exception("LoginBean.RETAILER_REJECTED");
			}
			else if (log.getStatus().charValue() == 'A') 
			{
				path ="";
				attempts = 0;
				session.setAttribute("userId", log.getUserId());
				session.setAttribute("role", log.getRole().toString());
				session.setAttribute("status", log.getStatus().toString());
				if (log.getRole().charValue() == 'A') {
					path = "/pages/admin/Home";
				} else if (log.getRole().charValue() == 'R') {
					path = "/pages/retailer/Home";
				} else if (log.getRole().charValue() == 'U') {
					path = "/pages/user/Home";
				} else {
					path = "/pages/errorPage";
				}
			} else {
				throw new Exception("LoginBean.ACCOUNT_LOCKED");
			}
			return path;
		} catch (Exception exception) 
		{
			path = "";
			styleClass="errorMsg";
			JCartLogger.logError(this.getClass().getName(), "doLogin",
					exception.toString());
			if (exception.getMessage().contains("INVALID_PASSWORD")) {
				attempts++;
			}
			if (exception.getMessage().contains("TECHNICAL_ERROR")) {
				path = "/pages/errorPage";
			}
			this.message = JCartConfig.getMessageFromProperties(exception.getMessage());
			return path;
		}
	}

	/****
	 * @description This method make user to logout and invalidate the session
	 * @return String
	 * @catch Exception
	 */
	public String doLogout() {
		String path = "";
		this.message = null;
		this.styleClass = "";
		try {
			FacesContext ftx = FacesContext.getCurrentInstance();
			ExternalContext etx = ftx.getExternalContext();
			HttpSession session = (HttpSession) etx.getSession(true);

			String userId = (String) session.getAttribute("userId");
			Factory.createLoginService().logout(userId);
			session.invalidate();
			this.message = JCartConfig.getMessageFromProperties("LoginBean.LOGOUT_SUCCESS");
			path = "/pages/logout";
			this.styleClass = "successMsg";
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "doLogout",
					exception.toString());
			if (exception.getMessage().contains("TECHNICAL_ERROR")) {
		
				path = "/pages/errorPage";
			}
			this.message = JCartConfig.getMessageFromProperties(exception.getMessage());
				
		}
		return path;
	}

}