package com.infy.jcart.business.service;

import java.util.Calendar;
import java.util.List;

import com.infy.jcart.beans.Login;
import com.infy.jcart.resources.CalendarUtility;
import com.infy.jcart.resources.JCartLogger;
import com.infy.jcart.resources.Factory;

public class LoginServiceImpl implements LoginService {
	
	/***
	 * @description invokes
	 *              getLogin(),authorize(),getLastLogoutTime(),blockAccount()
	 *              method's of LoginDAOImpl class and returns the Login
	 *              obtained
	 * @param Login
	 * @return Login
	 * @throws LoginService.INVALID_USERID
	 *             ,LoginService.ACCOUNT_BLOCKED,LoginService.ACCOUNT_LOCKED,
	 *             LoginService.NEW_RETAILER,
	 *             LoginService.ACCOUNT_DEACTIVATED,LoginService
	 *             .INVALID_PASSWORD
	 * @catch Exception
	 */
	public Login authorize(Login login) throws Exception {
		try {
			int dayDiff=0;
			if (Factory.createLoginDAO().getLogin(login.getUserId())
					.equals("Not Found")) {
				throw new Exception("LoginService.INVALID_USERID");
			} else {
				Login log = Factory.createLoginDAO().authorize(login);
			
				if (log != null) {
					if (log.getStatus() == 'B') {
						throw new Exception("LoginService.ACCOUNT_BLOCKED");
					} else if (log.getStatus() == 'L') {
						throw new Exception("LoginService.ACCOUNT_LOCKED");
					} else if (log.getStatus() == 'N') {
						throw new Exception("LoginService.NEW_RETAILER");
					} else if (log.getStatus() == 'D') {
						throw new Exception("LoginService.ACCOUNT_DEACTIVATED");
					}
					if (log.getPassword().equals(login.getPassword())) {
						
						if (log.getRole() == 'U') {
						
							if (log.getStatus() == 'A') {
								
								List<Calendar> calList = Factory
										.createLoginDAO()
										.getLastLogoutTime(log.getUserId());
								if (calList.size() > 0) {
								
									Calendar cal = calList.get(0);
									if(cal==null){
									
									 dayDiff=0;
									}
									else{
										
									 dayDiff = CalendarUtility
											.getDaysBetweenDates(cal,
													Calendar.getInstance());
									}
									if (dayDiff > 90) {
										
										Factory.createLoginDAO()
												.blockAccount(log.getUserId());
										throw new Exception(
												"LoginService.ACCOUNT_BLOCKED");
									} else {
									
										login.setRole(log.getRole());
										login.setStatus(log.getStatus());
									}
								} else {
									
									login.setRole(log.getRole());
									login.setStatus(log.getStatus());
								}
							}
						} 
						else if (log.getRole() == 'R') 
						{
							
							if (log.getStatus() == 'A') 
							{
							
								login.setRole(log.getRole());
								login.setStatus(log.getStatus());
							} 
						else {
						
							login.setRole(log.getRole());
							login.setStatus(log.getStatus());
						}
						}
						else if(log.getRole()=='A')
						{
							login.setRole(log.getRole());
							login.setStatus(log.getStatus());
						}
					} 
					else 
					{
						throw new Exception("LoginService.INVALID_PASSWORD");
					}
				}
			}
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "authorize",
					exception.getMessage());
			throw exception;
		}
		return login;
	}

	/***
	 * @description invokes getLogin(),lockAccount() method's of LoginDAOImpl
	 *              class.
	 * @param Login
	 * @return void
	 * @throws LoginService.INVALID_USERID
	 * @catch Exception
	 */
	public void lockAccount(Login login) throws Exception {
		try {
			if (Factory.createLoginDAO().getLogin(login.getUserId())
					.equals("Not Found")) {
				throw new Exception("LoginService.INVALID_USERID");
			} else {
				Factory.createLoginDAO().lockAccount(login.getUserId());
			}
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "lockAccount",
					exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description invokes getLogin(),blockAccount() method of LoginDAOImpl class
	 * @param String
	 * @return void
	 * @throws LoginService.INVALID_USERID
	 * @catch Exception
	 */

	public void blockAccount(String userId) throws Exception {
		try {
			Factory.createLoginDAO().blockAccount(userId);
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "blockAccount",
					e.getMessage());
			throw e;
		}
	}

	/***
	 * @description invokes getLogin(),unlockAccount() method of LoginDAOImpl class
	 * @param String
	 * @return void
	 * @throws LoginService.INVALID_USERID
	 * @catch Exception
	 */

	public void unlockAccount(String userId) throws Exception {
		try {
			if (Factory.createLoginDAO().getLogin(userId)
					.equals("Not Found")) {
				throw new Exception("LoginService.INVALID_USERID");
			} else {
				Factory.createLoginDAO().unlockAccount(userId);
			}
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "unlockAccount",
					e.getMessage());
			throw e;
		}
	}

	/***
	 * @description invokes getLogin(),saveLoginDetails() method of LoginDAOImpl class
	 * @param Login
	 * @return void
	 * @throws LoginService.INVALID_USERID
	 * @catch Exception
	 */

	public void saveLoginDetails(Login login) throws Exception {
		try {
			if (Factory.createLoginDAO().getLogin(login.getUserId())
					.equals("Not Found")) {
				throw new Exception("LoginService.INVALID_USERID");
			} else {
				Factory.createLoginDAO().saveLoginDetails(login);
			}
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "saveLoginDetails",
					e.getMessage());
			throw e;
		}
	}

	/***
	 * @description invokes getLogin(),activateRetailer() method of LoginDAOImpl class
	 * @param String
	 * @return void
	 * @throws LoginService.INVALID_USERID
	 * @catch Exception
	 */

	public void activateRetailer(String userId) throws Exception {
		try {
			if (Factory.createLoginDAO().getLogin(userId)
					.equals("Not Found")) {
				throw new Exception("LoginService.INVALID_USERID");
			} else {
				Factory.createLoginDAO().activateRetailer(userId);
			}
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "activateRetailer",
					e.getMessage());
			throw e;
		}
	}

	/***
	 * @description invokes getLogin(),rejectRetailer() method of LoginDAOImpl class
	 * @param String
	 * @return void
	 * @throws LoginService.INVALID_USERID
	 * @catch Exception
	 */

	public void rejectRetailer(String userId) throws Exception {
		try {
			if (Factory.createLoginDAO().getLogin(userId)
					.equals("Not Found")) {
				throw new Exception("LoginService.INVALID_USERID");
			} else {
				Factory.createLoginDAO().rejectRetailer(userId);
			}
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "rejectRetailer",
					e.getMessage());
			throw e;
		}
	}

	/***
	 * @description invokes getLogin(),deactivateRetailer() method of LoginDAOImpl class
	 * @param String
	 * @return void
	 * @throws LoginService.INVALID_USERID
	 * @catch Exception
	 */

	public void deactivateRetailer(String userId) throws Exception {
		try {
			if (Factory.createLoginDAO().getLogin(userId)
					.equals("Not Found")) {
				throw new Exception("LoginService.INVALID_USERID");
			} else {
				Factory.createLoginDAO().deactivateRetailer(userId);
			}
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"deactivateRetailer", e.getMessage());
			throw e;
		}
	}

	/***
	 * @description invokes getLogin(),logout() method of LoginDAOImpl class and returns
	 *              the Login obtained
	 * @param String
	 * @return void
	 * 
	 * @throws LoginService.INVALID_USERID
	 * @catch Exception
	 */

	public void logout(String userId) throws Exception {
		try {
			if (Factory.createLoginDAO().getLogin(userId)
					.equals("Not Found")) {
				throw new Exception("LoginService.INVALID_USERID");
			} else {
				Factory.createLoginDAO().logout(userId);
			}
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "logout",
					exception.getMessage());
			throw exception;
		}
	}

}





