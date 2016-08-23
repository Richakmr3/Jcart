package com.infy.jcart.business.service;


	
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.infy.jcart.beans.Registration;
import com.infy.jcart.dao.EditProfileDAOImpl;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;

	public class EditProfileServiceImpl implements EditProfileService
	{
		 /**
	     * @description this method invokes updateProfile()
	     * method of EditProfileDAOImpl class and returns the id obtained
	     * @param Registration
	     * @return Integer 
	     * @throws  Exception
	     * @catch Exception
	     */
		public Integer updateProfile(Registration to) throws Exception {
			try {
				int i;
				i=Factory.createEditProfileDAO().updateProfile(to);
				return i;
			} catch (Exception e) {
				JCartLogger.logError(this.getClass().getName(), "updateProfile", e
						.toString());
				throw e;
			}

		}
		
		/**
	     * @description this method invokes deactivateUser()
	     * method of EditProfileDAOImpl class and returns the boolean value obtained
	     * @param String
	     * @return boolean 
	     * @throws  Exception
	     * @catch Exception
	     */
		public Boolean deactivateUser(String userId) throws Exception {

			try {
				
		
			return Factory.createEditProfileDAO().deactivateUser(userId);
				
			} catch (Exception e) {
				JCartLogger.logError(this.getClass().getName(), "deactivateUser", e
						.toString());
				throw e;
			}
		}
		
		/**
	     * @description this method invokes getProfileDetails()
	     * method of EditProfileDAOImpl class and returns the Registration obtained
	     * @param String
	     * @return Registration 
	     * @throws EditProfileService.NO_PROFILE_DETAILS_FOUND_EXCEPTION
	     * @catch Exception
	     */
		public Registration getProfileDetails(String userId) throws  Exception {

			try {
				
				Registration to = new Registration();
			
				to=Factory.createEditProfileDAO().getProfileDetails(userId);
				if (to == null) {
					throw new Exception("EditProfileService.NO_PROFILE_DETAILS_FOUND_EXCEPTION");
				}
				return to;

			} 
			catch (Exception e) {
				JCartLogger.logError(this.getClass().getName(),
						"getProfileDetails", e.toString());
				throw e;
			}
		}
		/**
	     * @description this method invokes getPasswordDetails()
	     * method of EditProfileDAOImpl class and returns the password obtained
	     * @param String
	     * @return String 
	     * @throws  Exception
	     * @catch Exception
	     */
		public String getPasswordDetails(String userId) throws Exception {
			try {
				
				String password = new String();
				
				password=Factory.createEditProfileDAO().getPasswordDetails(userId);
				return password;

			}

			catch (Exception e) {
				JCartLogger.logError(this.getClass().getName(),
						"getPasswordDetails", e.toString());
				throw e;
			}
		}
		/**
	     * @description this method checks whether old password matches the new password,
	     * 				then checks that the new passwords match each other, then it is checked that
	     *				old password should not match new password ,then it is checked that new password 
	     * meets the business requirements and the security answer provided by the user 
	     * matches the saved answer or not and then password is changed by invoking
	     * changePassword of EditProfileDAOImpl class
	     * @param List<String>
	     * @return int 
	     * @throws  NEditProfileService.NEW_PASSWORD_NOT_MATCHING_EXCEPTION, EditProfileService.NEW_PASSWORD_NOT_MATCHING_EXCEPTION,
	     * EditProfileService.OLD_AND_NEW_PASSWORD_NOT_MATCHING_EXCEPTION, InvalidNewPasswordException,SecurityAnswerNotMatchingException
	     * @catch Exception
	     */
		public Integer changePassword(List<String> list) throws Exception {
			try {

				String newPassword = list.get(0);
				String newpassword2 = list.get(1);
				String password = list.get(2);
				String userId = list.get(3);
				String answer = list.get(4);
				String oldPassword=Factory.createEditProfileDAO().getPasswordDetails(userId);
				

				if (!(oldPassword.equals(password))) {
					throw new Exception("EditProfileService.OLD_PASSWORD_NOT_MATCHING_EXCEPTION");
				}

				if (!(newPassword.equals(newpassword2))) {

					throw new Exception("EditProfileService.NEW_PASSWORD_NOT_MATCHING_EXCEPTION");
				}

				if ((password.equals(newPassword))) 
				{
					throw new Exception("EditProfileService.OLD_AND_NEW_PASSWORD_MATCHING_EXCEPTION");
				}

				/*
				 * 6 to 20 characters string with at least one digit, one upper case
				 * letter, one lower case letter and one special symbol (“@#$%”).
				 */

				String pwdPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%_*]).{6,20})";
				Pattern pattern;
				Matcher matcher;
			
				pattern = Pattern.compile(pwdPattern);
				matcher = pattern.matcher(newPassword);
				boolean result = matcher.matches();
				if (!(result)) 
				{
					throw new Exception("EditProfileService.INVALID_NEW_PASSWORD_EXCEPTION");
				}

				EditProfileDAOImpl ser = new EditProfileDAOImpl();
				Registration to = new Registration();
				to = ser.getProfileDetails(userId);
				if (!(answer.equalsIgnoreCase(to.getSecurityAnswer()))) 
				{
					throw new Exception("EditProfileService.SECURITY_ANSWER_NOT_MATCHING_EXCEPTION");
				}

				 ser.changePassword(userId, newPassword);
				
			} catch (Exception e) 
			{
				JCartLogger.logError(this.getClass().getName(), "changePassword", e
						.toString());
				throw e;
			}

			return 1;
		}
		/**
	     * @description this method invokes editMessageSetting()
	     * 				method of EditProfileDAOImpl class and returns the password obtained
	     * @param String
	     * @return boolean 
	     * @throws  Exception
	     */
		public Boolean editMessageSetting(String userId) throws Exception {
			
			try {
				
			
				return Factory.createEditProfileDAO().editMessageSetting(userId);
			} catch (Exception e) {
				JCartLogger.logError(this.getClass().getName(),
						"editMessageSetting", e.toString());
				throw e;
			}
			
		}

	}

