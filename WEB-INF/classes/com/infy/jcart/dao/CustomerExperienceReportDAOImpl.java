package com.infy.jcart.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;


public class CustomerExperienceReportDAOImpl implements CustomerExperienceReportDAO {

	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	@SuppressWarnings("unchecked")
	public Integer numberOfReviews(Integer choice, Double min, Double max,String retailerId) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Query q = null;
			if (choice == 1) {
				q = session.createQuery("select count(p.ratingId) "
						+ "from ProductRatingEntity p "
						+ "where p.rating between ? and ?");
			} else if (choice == 2) {
				q = session.createQuery("select count(p.ratingId) "
						+ "from RetailerRatingEntity p "
						+ "where p.rating between ? and ?");
			}
			else if (retailerId != null) {
				q = session
						.createQuery("select count(p.ratingId) "
								+ "from RetailerRatingEntity p "
								+ "where p.rating between ? and ? and p.retailerId=:retId");
				q.setParameter("retId", retailerId);
			}
			Integer i=min.intValue();
			Integer j=max.intValue();
			
			q.setParameter(0, i);
			q.setParameter(1, j);
			
			List<Long> list = q.list();
			session.getTransaction().commit();
			return list.get(0).intValue();
		} 
		catch (Exception exception) 
		{
			JCartLogger.logError(this.getClass().getName(),
					"numberOfReviews", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally 
		{
			
			session.close(); 
		}
		
	}

}
