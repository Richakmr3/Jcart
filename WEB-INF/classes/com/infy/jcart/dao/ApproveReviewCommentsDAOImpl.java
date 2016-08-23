package com.infy.jcart.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;





import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.ProductRating;
import com.infy.jcart.entites.ProductRatingEntity;
import com.infy.jcart.entites.RetailerRatingEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;

public class ApproveReviewCommentsDAOImpl implements ApproveReviewCommentsDAO {
	private SessionFactory sessionFactory = HibernateUtility
			.getSessionFactory();

	/**
	 * @description this method finds the unapproved ratings populates a list of
	 *              ProductRating and returns it
	 * @return List<ProductRating>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ProductRating> getUnapprovedRatings() throws Exception 
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<ProductRating> list = new ArrayList<ProductRating>();
		try {

			Query query = session
					.createSQLQuery("SELECT userId, productId,TO_CHAR(NULL) ,reviewComments, rating, dos,ratingId "
							+ "FROM JCART_PRODUCTRATING where status='P' UNION	SELECT userId, 0 , retailerId ,reviewComments, rating, "
							+ "dos,ratingId	FROM JCART_RETAILERRATING where status='P' order by dos");

			List<Object[]> l = query.list();

			for (Object[] v : l) {
				ProductRating to = new ProductRating();
				Timestamp d = (Timestamp) v[5];
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(d.getTime());
				to.setDos(c);

				if (v[1] != null) {
					to.setProductId(((BigDecimal) v[1]).intValue());
				}
				if (v[4] != null) {
					to.setRating(((BigDecimal) v[4]).intValue());
				}
				to.setRetailerId((String) v[2]);
				to.setReviewComments((String) v[3]);
				to.setUserId((String) v[0]);
				if (v[6] != null) {
					to.setRatingId(((BigDecimal) v[6]).intValue());
				}
				list.add(to);
			}
			session.getTransaction().commit();
			return list;
		}

		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getUnapprovedRatings", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}
	}

	/**
	 * @description this method finds the RetailerRatingEntity and set its
	 *              status as required
	 * @param ProductRating
	 *            , char
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public void updateRetailerReviews(ProductRating to, Character c)
			throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			RetailerRatingEntity entity = (RetailerRatingEntity) session.get(
					RetailerRatingEntity.class, to.getRatingId());
			if (entity != null)
			{
				entity.setStatus(c);
			}
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"updateRetailerReviews", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}

	}

	/**
	 * @description this method finds the ProductRatingEntity and set its status
	 *              as required
	 * @param ProductRating
	 *            , char
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public void updateProductReviews(ProductRating to, Character c)
			throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			ProductRatingEntity entity = (ProductRatingEntity) session.get(
					ProductRatingEntity.class, to.getRatingId());
			entity.setStatus(c);
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"updateProductReviews", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}

	}
}
