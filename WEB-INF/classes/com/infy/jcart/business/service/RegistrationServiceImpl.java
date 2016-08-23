package com.infy.jcart.business.service;

import com.infy.jcart.beans.Registration;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;



public class RegistrationServiceImpl implements RegistrationService{
	
	
	/***
	 * @description this method invokes registerNewMember() method of 
	 * RegistrationDAOImpl class and returns the Registration object obtained
	 * @param Registration, Character
	 * @return Registration
	 * @throws Exception
	 * @catch Exception
	 */
	public Registration registerNewMember(Registration rto,Character type) throws Exception{
		try{
			return Factory.createRegistrationDAO().registerNewMember(rto, type);
			
		}catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "registerNewMember",
					exception.getMessage());
			throw exception;
		}
	}
	/***
	 * @description this method invokes getProfileDetails() method of 
	 * RegistrationDAOImpl class and returns the Registration object obtained
	 * @param String
	 * @return Registration
	 * @throws Exception
	 * @catch Exception
	 */
	public Registration getProfileDetails(String userId) throws Exception{
		try{
			Registration registration =Factory.createRegistrationDAO().getProfileDetails(userId);
			if(registration==null){
				throw new Exception("RegistrationService.NO_SUCH_USER_FOUND");
			}
			return registration;
		}catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getProfileDetails",
					exception.getMessage());
			throw exception;
		}
	}
	/***
	 * @description this method invokes editProfileDetails() method of 
	 * RegistrationDAOImpl class 
	 * @param Registration
	 * @return void
	 * @throws Exception
	 * @catch Exception
	 */
	public void editProfileDetails(Registration rto) throws Exception{
		try{
			 Factory.createRegistrationDAO().editProfileDetails(rto);
		}catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "editProfileDetails",
					exception.getMessage());
			throw exception;
		}
	}
	/***
	 * @description this method invokes getSecurityQstnforUser() method of 
	 * RegistrationDAOImpl class and returns the security question obtained
	 * @param String
	 * @return String
	 * @throws RegistrationService.NO_SUCH_USER_FOUND
	 * @catch Exception
	 */
	public String getSecurityQstnforUser(String userId) throws Exception{
		try{
			String securityQuetion=Factory.createRegistrationDAO().getSecurityQstnforUser(userId);
			if(securityQuetion==null){
				throw new Exception("RegistrationService.NO_SUCH_USER_FOUND");
			}
			return securityQuetion;
		}catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getSecurityQstnforUser",
					exception.getMessage());
			throw exception;
		}
	}
	/***
	 * @description this method invokes validateSecurityAnswer() method of 
	 * RegistrationDAOImpl class and returns the boolean value obtained
	 * @param String, String
	 * @return boolean
	 * @throws RegistrationService.NO_SUCH_USER_FOUND
	 * @catch Exception
	 */
	public Boolean validateSecurityAnswer(String userId, String answer) throws Exception{
		try{
			Boolean valid=Factory.createRegistrationDAO().validateSecurityAnswer(userId, answer);
			if(valid==null){
				throw new Exception("RegistrationService.NO_SUCH_USER_FOUND");
			}
			return valid;
		}catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "validateSecurityAnswer",
					exception.getMessage());
			throw exception;
		}
	}
}
