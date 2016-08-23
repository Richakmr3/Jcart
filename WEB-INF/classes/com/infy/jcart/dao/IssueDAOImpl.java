package com.infy.jcart.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.IssueDetails;
import com.infy.jcart.entites.IssueDetailsEntity;
import com.infy.jcart.entites.LoginEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;


public class IssueDAOImpl implements IssueDAO
{
	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	/***
	 * @description this method finds the issues against a particular user
	 * in decreasing order of issue id , populates a list of IssueDetailsTO and returns it
	 * @param String
	 * @return List<IssueDetailsTO>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<IssueDetails> getIssuesForRetailer(String retailerId)
			throws Exception {
		Session session = sessionFactory.openSession();
		
		List<IssueDetails> list = new ArrayList<IssueDetails>();

		try {
			session.beginTransaction();
	
			Query q = session
					.createQuery("select i from IssueDetailsEntity i where i.retailerId=? order by i.issueId desc ");
			q.setParameter(0, retailerId);
			List<IssueDetailsEntity> l = q.list();
			for (int i = 0; i < l.size(); i++) {
				IssueDetailsEntity entity = l.get(i);
				IssueDetails to = new IssueDetails();
				to.setDescription(entity.getDescription());
				to.setIssueId(entity.getIssueId());
				to.setProductId(entity.getProductId());
				to.setRetailerId(entity.getRetailerId());
				to.setStatus(entity.getStatus());
				to.setUserId(entity.getUserId());
				list.add(to);
			}
			
			session.getTransaction().commit();
			
			return list;
		}

		catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"getIssuesForRetailer", e.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally {
		
			session.close(); 
		}
	}

	/***
	 * @description this method finds the issue summary , populates a map
	 * by putting retailer id as key and number of issues as value
	 * @return Map<String, Integer>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Integer> getIssuesSummary() throws Exception {
		Session session = sessionFactory.openSession();
	
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();

		try {
			
			session.beginTransaction();
			Query q = session.createSQLQuery("select COUNT(i.issueId),i.retailerId from JCART_ISSUEDETAILS i where i.status='A' group by i.retailerId order by count(i.issueId) desc");

			List<Object[]> l = q.list();
			for (Object[] objects : l) {
				BigDecimal bd=(BigDecimal)objects[0];
				String st=(String)objects[1];
				map.put(st, bd.intValue());
			}
			
			session.getTransaction().commit();
			
			return map;
		}

		catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "getIssuesSummary",
					e.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally {
			
			session.close(); 
		}
	}

	/***
	 * @description finds each issue and sets its status to 'A'
	 * @param List<IssueDetailsTO> 
	 * @return void
	 * @throws NoRowSelectedException, Exception
	 */
	public void acknowledgeIssues(List<IssueDetails> selectedIssues)
			throws Exception {
		Session session = sessionFactory.openSession();
		
		try {
			
			session.beginTransaction();
			for (int i = 0; i < selectedIssues.size(); i++) {
				IssueDetails to = selectedIssues.get(i);
				IssueDetailsEntity entity = (IssueDetailsEntity)session.get(IssueDetailsEntity.class, to.getIssueId());
				entity.setStatus('A');
			}
			session.getTransaction().commit();
		}

		catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"acknowledgeIssues", e.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally {
			
			session.close(); 
		}
	}

	/***
	 * @description finds the retailer and sets its status as 'D'
	 * @param String
	 * @return void
	 * @throws Exception
	 */
	public void deactivateRetailer(String retailerId) throws Exception {
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
		
			LoginEntity entity = (LoginEntity)session.get(LoginEntity.class,
					retailerId);
			entity.setStatus('D');
			
			session.getTransaction().commit();
			
		}

		catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"deactivateRetailer", e.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally {
		
			session.close(); 
		}
	}
}
