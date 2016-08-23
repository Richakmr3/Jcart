package com.infy.jcart.dao;

import java.util.ArrayList;
import java.util.List;





import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.IssueDetails;
import com.infy.jcart.entites.IssueDetailsEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;



public class RaiseIssueDAOImpl implements RaiseIssueDAO {
	
	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	/***
	 * @description this method populates a IssueDetailsEntity and persists it
	 * and returns the issue id generated
	 * invokes raiseIssue of RaiseIssueService class and
	 * returns the issue id obtained
	 * @param List<String>, Integer
	 * @return Integer

	 * @throws  Exception
	 */
	public Integer raiseIssue(IssueDetails to) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try {
			

			IssueDetailsEntity en = new IssueDetailsEntity();
			en.setDescription(to.getDescription());
			en.setProductId(to.getProductId());
			en.setRetailerId(to.getRetailerId());
			en.setStatus(to.getStatus());
			en.setUserId(to.getUserId());
			
			session.save(en);
			session.getTransaction().commit();
			
			return en.getIssueId();

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"raiseIssue", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}finally {
		
			session.close(); 
		}
	}
	/***
	 * @description this method invokes finds all the retailer id and returns it
	 * returns the list obtained
	 * @return List<String>

	 * @throws   Exception
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAllRetailers() throws Exception {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try {
			
			Query query = session
					.createQuery("Select e.userId from LoginEntity e where e.role ='R'");

			List<String> list = new ArrayList<String>();

			list = query.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getAllRetailers", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}finally {
		
			session.close(); 
		}
	}
	/***
	 * @description this method finds the product id for a particular seller
	 * returns the list obtained
	 * @param String
	 * @return List<Integer>

	 * @throws  NoProductsForRetailerFoundException, Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getProductsForRetailer(String retailer)
			throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try {
			
			Query query = session
					.createQuery("Select e.productId from ProductEntity e where e.sellerId =?");
			query.setParameter(0, retailer);

			List<Integer> list = new ArrayList<Integer>();

			list = query.list();
			session.getTransaction().commit();
			
			return list;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getProductsForRetailer", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}finally {
		
			session.close(); 
		}
	}

}
