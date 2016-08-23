package com.infy.jcart.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.IssueDetails;
import com.infy.jcart.beans.Product;
import com.infy.jcart.entites.IssueDetailsEntity;
import com.infy.jcart.entites.ProductEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;

public class ViewIssueDAOImpl implements ViewIssueDAO {

	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

	/***
	 * @description this method finds all the issues against a particular
	 *              retailer, populates a list of IssueDetails and returns it.
	 * @param String
	 *            , String
	 * @return List<IssueDetails>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<IssueDetails> getIssuesForRetailer(String retailerId,
			String userId) throws Exception {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<IssueDetails> issueList = new ArrayList<IssueDetails>();
		try {
			Query query = session
					.createQuery("Select e from IssueDetailsEntity e where e.retailerId =? and e.userId=?");
			query.setParameter(0, retailerId);
			query.setParameter(1, userId);
			List<IssueDetailsEntity> entitylist = query.list();
			for (IssueDetailsEntity issueDetailsEntity : entitylist) {
				IssueDetails issue = new IssueDetails();
				issue.setDescription(issueDetailsEntity.getDescription());
				issue.setIssueId(issueDetailsEntity.getIssueId());
				issue.setProductId(issueDetailsEntity.getProductId());
				issue.setRetailerId(issueDetailsEntity.getRetailerId());
				issue.setStatus(issueDetailsEntity.getStatus());
				issue.setUserId(issueDetailsEntity.getUserId());
				issueList.add(issue);
			}
			session.getTransaction().commit();

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getIssuesForRetailer", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}

		return issueList;
	}

	/***
	 * @description this method finds product details for a particular product,
	 *              populates a Product and returns it
	 * @param Integer
	 * @return Product
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public Product getProductDetails(Integer productId) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Product product = new Product();
		try {
			ProductEntity en = (ProductEntity) session.get(ProductEntity.class,
					productId);

			product.setCategoryId(en.getCategoryId());
			product.setProductId(en.getProductId());
			product.setProductType(en.getProductType());
			product.setSellerId(en.getSellerId());
			product.setSubCategory(en.getSubCategory());
			
			session.getTransaction().commit();

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getProductDetails", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}

		return product;
	}

}
