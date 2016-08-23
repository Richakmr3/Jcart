package com.infy.jcart.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.Registration;
import com.infy.jcart.entites.MessagesEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;

public class MessageDAOImpl implements MessageDAO {

	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	/***
	 * @description this method populates a MessagesEntity and persists it
	 * @param String, String, String
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public void sendMessage(String message, String recieverId, String senderId)
			throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			MessagesEntity mesEntity=new MessagesEntity();
			mesEntity.setMessage(message);
			mesEntity.setSenderId(senderId);
			mesEntity.setRecieverId(recieverId);
			Calendar currentTime=Calendar.getInstance();
			mesEntity.setMessageDate(currentTime);
			
			session.persist(mesEntity);
			
			session.getTransaction().commit();
			
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"sendMessage", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}
		
	}

	/***
	 * @description this method finds whether message setting is enabled for user or not
	 * 				if enabled then returns true otherwise false
	 * @param String
	 * @return Boolean
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public Boolean checkActiveMessageService(String userId) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Boolean flag = false;
		
		try {
			Registration reg = Factory.createEditProfileDAO().getProfileDetails(userId);
			if(reg.getMessageService() == 'Y')
			{
				flag = true;
			}
			session.getTransaction().commit();
			
		}
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"checkActiveMessageService", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}
		return flag;
	}

	/***
	 * @description this method returns the list of all user id present in the 
	 * 				database
	 * @return List<String>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAllUserIds() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<String> empList=new ArrayList<String>();
		try {
			Query query=session.createQuery("SELECT l.password FROM LoginEntity l");
			empList=query.list();
			
			session.getTransaction().commit();
		} 
		catch (Exception exception) 
		{
			JCartLogger.logError(this.getClass().getName(), "getAllUserIds", exception.getMessage());
			
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally 
		{
			
			session.close(); 
		}
		return empList;
	}

}
