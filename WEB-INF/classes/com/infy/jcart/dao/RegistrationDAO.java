package com.infy.jcart.dao;

import com.infy.jcart.beans.Registration;

public interface RegistrationDAO {
	
	public String checkUser(String userId) throws Exception;
	public Registration registerNewMember(Registration rto, Character type) throws Exception ;
	public Registration getProfileDetails(String userId) throws Exception;
	public void editProfileDetails(Registration rto) throws Exception;
	public String getSecurityQstnforUser(String userId) throws Exception ;
	public Boolean validateSecurityAnswer(String userId, String answer) throws Exception;
	
}
