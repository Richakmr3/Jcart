package com.infy.jcart.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.Login;
import com.infy.jcart.beans.Registration;
import com.infy.jcart.entites.RegistrationEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;


public class RegistrationDAOImpl implements RegistrationDAO{
	
	
	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

	
	/***
	 * @description this method returns "Found" if the given userId 
	 * found in the JCART_REGISTRATION table else returns "Not Found"
	 * @param String
	 * @return String

	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String checkUser(String userId) throws Exception{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String checkStatus="Not Found";
		try {
			
			Query q = session.getNamedQuery("getUserDetails");
			q.setParameter(0, userId);
			
			List<RegistrationEntity> entity =q.list();
			if(entity.size()!=0){
				checkStatus="Found";
			}
			session.getTransaction().commit();
			return checkStatus;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"checkUser", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}finally{
		
			session.close(); 
		}
	}
	
	/***
	 * @description this method generates the list of RegistrationEntity 
	 * based on user id and returns the same.
	 * @param String
	 * @return List<RegistrationEntity>

	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<RegistrationEntity> registrationList(String userId) throws Exception{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Query q = session.getNamedQuery("getUserDetails");
			q.setParameter(0, userId);
			session.getTransaction().commit();
			return q.list();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"registrationList", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}finally{
			
			session.close(); 
		}
	}
	
	/***
	 * @description this method generates the user id according to the business
	 * logic,sets the role and status according to the role, populates a 
	 * RegistrationEntity , and returns Registration populated with all the values
	 * @param Registration, Character
	 * @return Registration

	 * @throws Exception
	 */
	public Registration registerNewMember(Registration rto, Character type)
			throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Query q = session.getNamedQuery("getUserNameCount");
			q.setParameter(0, rto.getFirstName());
			q.setParameter(1, rto.getLastName());
			int nos = q.list().size();
			String userName = null;
			if (nos < 10) 
			{
				userName = rto.getFirstName() + "_" + rto.getLastName() + "0"
						+ nos;
			}
			else 
			{
				userName = rto.getFirstName() + "_" + rto.getLastName() + nos;
			}
			rto.setUserId(userName);
			Login lto = new Login();
			if (type == 'U') {
				lto.setUserId(userName);
				lto.setPassword(rto.getPassword());
				lto.setStatus('A');
				lto.setRole('U');
			} else if (type == 'R') {
				lto.setUserId(userName);
				lto.setPassword(rto.getPassword());
				lto.setStatus('N');
				lto.setRole('R');
			}
			new LoginDAOImpl().saveLoginDetails(lto);
			RegistrationEntity re = new RegistrationEntity();
			re.setUserId(userName);
			re.setFirstName(rto.getFirstName());
			re.setLastName(rto.getLastName());
			re.setPermanentAddress(rto.getPermanentAddress());
			re.setShipmentAddress(rto.getShipmentAddress());
			re.setPhoneNumber(rto.getPhoneNumber());
			re.setSecurityQuestion(rto.getSecurityQuestion());
			re.setSecurityAnswer(rto.getSecurityAnswer());
			re.setDob(rto.getDob());
			re.setMessageService(rto.getMessageService());
			re.setGender(rto.getGender());
			
			session.save(re);
			
			rto.setRegistrationId(re.getRegistrationId());
			
			session.getTransaction().commit();
		}
		catch (Exception exception) {
			
			JCartLogger.logError(this.getClass().getName(),
					"registerNewMember", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}
		return rto;
	}
	/***
	 * @description this method finds the profile details for a particular user,
	 * populates Registration and returns it
	 * @param String
	 * @return Registration

	 * @throws Exception
	 */

	public Registration getProfileDetails(String userId) throws Exception {
		
		
		
		Registration rto = null;
		try {
			
			List<RegistrationEntity> l =this.registrationList(userId);
			
			if (l.size() > 0) {
				rto=new Registration();
				RegistrationEntity re = l.get(0);
				rto.setRegistrationId(re.getRegistrationId());
				rto.setUserId(re.getUserId());
				rto.setFirstName(re.getFirstName());
				rto.setLastName(re.getLastName());
				rto.setDob(re.getDob());
				rto.setPermanentAddress(re.getPermanentAddress());
				if (re.getShipmentAddress() == null) {
					rto.setShipmentAddress("Not Available");
				} else {
					rto.setShipmentAddress(re.getShipmentAddress());
				}
				rto.setGender(re.getGender());
				rto.setPhoneNumber(re.getPhoneNumber());
				rto.setMessageService(re.getMessageService());
				rto.setSecurityAnswer(re.getSecurityAnswer());
				rto.setSecurityQuestion(re.getSecurityQuestion());
			}
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getProfileDetails", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		return rto;
	}
	
	/***
	 * @description this method edits the profile details for a particular user
	 * @param Registration
	 * @return void

	 * @throws Exception
	 */
	public void editProfileDetails(Registration rto) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			
			RegistrationEntity re = (RegistrationEntity)session.get(RegistrationEntity.class, rto.getRegistrationId());
			re.setMessageService(rto.getMessageService());
			re.setPermanentAddress(rto.getPermanentAddress());
			re.setShipmentAddress(rto.getShipmentAddress());
			re.setPhoneNumber(rto.getPhoneNumber());
			re.setSecurityQuestion(rto.getSecurityQuestion());
			re.setSecurityAnswer(rto.getSecurityAnswer());
			
			session.getTransaction().commit();
		}
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getProfileDetails", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}
	}
	/***
	 * @description this method returns the security question for a particular user
	 * @param String
	 * @return String
	 * @throws Exception
	 */
	
	public String getSecurityQstnforUser(String userId) throws Exception {
		
		try {
			String securityQuetion=null;
			List<RegistrationEntity> l =this.registrationList(userId);
			if (l.size() > 0) {
				securityQuetion=l.get(0).getSecurityQuestion();
			} 
			return securityQuetion;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getSecurityQstnforUser", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
	}
	/***
	 * @description this method checks whether the security answer entred by the
	 * user matches with that one on database or not and returns the boolean value
	 * accordingly
	 * @param String, String
	 * @return boolean
	 * @throws Exception
	 */
	
	public Boolean validateSecurityAnswer(String userId, String answer)
			throws Exception {
			Boolean valid=null;
		try {
			
			List<RegistrationEntity> l = this.registrationList(userId);
			if (l.size() > 0) {
				if (l.get(0).getSecurityAnswer().equals(answer))
					valid=true;
				else
					valid=false;
			} 
			return valid;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"validateSecurityAnswer", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
	}
}
