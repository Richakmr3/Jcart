package com.infy.jcart.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.entites.ProductVariationsEntity;
import com.infy.jcart.entites.ProductVariationsPK;
import com.infy.jcart.entites.UserPointsEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;

public class UpdateDAOImpl implements UpdateDAO {

private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	
	
	/***
	 * @description this method updates the stock of variation corresponding to the
	 * 				product and returns "updated" after successful updation.
	 * @param Integer, String
	 * @return String
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public String updateStock(Integer productId, String variationCode)
			throws Exception {
		Session session = sessionFactory.openSession();
		
		try {
			ProductVariationsPK pk = new ProductVariationsPK();
			pk.setProductId(productId);
			pk.setVariationCode(variationCode);

			session.beginTransaction();
			ProductVariationsEntity entity = (ProductVariationsEntity) session.get(
					ProductVariationsEntity.class, pk);
			entity.setStock(entity.getStock() - 1);
			session.getTransaction().commit();
			return "updated";
			
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"updateStock", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}		
	}

	/***
	 * @description this method adds the user points for a particular user
	 * 				according to the business logic and returns "updated" after successful updation
	 * 				and returns the String obtained
	 * @param String, Double
	 * @return String
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public String addUserPointsOnPurchase(String userId, Double bill)
			throws Exception {
		Session session = sessionFactory.openSession();
		
		try {
			// every 10 rupee gives one point on purchase
			
						int points= new Double(bill/10).intValue();
						
						UserPointsEntity f= (UserPointsEntity) session.get(UserPointsEntity.class,userId);
						if(f!=null){
							session.beginTransaction();
							f.setDiscountPoints(f.getDiscountPoints()+points);
							session.getTransaction().commit();
						}
						else{
							UserPointsEntity f1 = new UserPointsEntity();
							f1.setDiscountPoints(points);
							f1.setUserId(userId);
							session.beginTransaction();
							session.save(f1);
							session.getTransaction().commit();
						}
						return "updated";
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"addUserPointsOnPurchase", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close(); 
		}
	}

}
