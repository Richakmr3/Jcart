package com.infy.jcart.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;

public class RetailerDAOImpl implements RetailerDAO{
	
	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	
	/***
	 * @description this method finds the list of user id of all the retailers 
	 * 				who are not deactived and returns it
	 * @return List<String>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAllRetailerIds() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Query q = session.createQuery("select l.userId "
					+ "from LoginEntity l where l.role='R' and l.status<>'D'");
			List<String> list = q.list();
			session.getTransaction().commit();
			
			return list;

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getAllRetailerIds", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}
	}
}
