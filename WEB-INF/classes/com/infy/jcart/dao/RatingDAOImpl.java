package com.infy.jcart.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;




import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.Product;
import com.infy.jcart.beans.ProductRating;
import com.infy.jcart.beans.RetailerRating;
import com.infy.jcart.entites.ProductRatingEntity;
import com.infy.jcart.entites.RetailerRatingEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;



public class RatingDAOImpl implements RatingDAO{
	
	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	/***
	 * @description this method populates a RetailerRatingEntity by setting status
	 * as 'P' and persists it
	 * @param RetailerRating
	 * @return void

	 * @throws Exception
	 */
	public void provideRetailerRating(RetailerRating rating) throws Exception{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			
			RetailerRatingEntity ratingEntity=new RetailerRatingEntity();
			ratingEntity.setDos(Calendar.getInstance());
			ratingEntity.setRating(rating.getRating());
			ratingEntity.setRetailerId(rating.getRetailerId());
			ratingEntity.setReviewComments(rating.getReviewComments());
			ratingEntity.setStatus('P');
			ratingEntity.setUserId(rating.getUserId());
			session.save(ratingEntity);
			
			session.getTransaction().commit();
			
		}catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"provideRetailerRating", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}finally {
		
			session.close(); 
		}
	}
	/***
	 * @description this method  populates a ProductRatingEntity by setting status
	 * as 'P' and persists it
	 * @param RetailerRating
	 * @return void

	 * @throws Exception
	 */
	public void provideProductRating(ProductRating rating) throws Exception{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			
			ProductRatingEntity ratingEntity=new ProductRatingEntity();
			ratingEntity.setDos(Calendar.getInstance());
			ratingEntity.setRating(rating.getRating());
			ratingEntity.setProductId(rating.getProductId());
			ratingEntity.setReviewComments(rating.getReviewComments());
			ratingEntity.setStatus('P');
			ratingEntity.setUserId(rating.getUserId());
			
			session.save(ratingEntity);
			
			session.getTransaction().commit();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"provideProductRating", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}finally {
	
			session.close(); 
		}
	}
	/***
	 * @description this method invokes getRetailersList() method of
	 *  RatingService class and returns the list obtained 
	 * @return List<Product>

	 * @throws NoRetailerFoundException, Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Product> getRetailersList() throws Exception{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Product> retailerList=null;	
		try {
			
			retailerList=new ArrayList<Product>();
			Query query=session.createQuery("select distinct p.sellerId from ProductEntity p");
			List<String> object=query.list();
			for (int i = 0; i < object.size(); i++) {
				Product product=new Product();
				product.setSellerId(object.get(i).intern());
				retailerList.add(product);
			}	
			session.getTransaction().commit();
			
		}catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getRetailersList", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}finally {
		
			session.close(); 
		}
		return retailerList;
	}
	/***
	 * @description this method finds all the new products , populates
	 *  a list of Product and returns it
	 * @return List<Product>

	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Product> getProductsList() throws Exception{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Product> productList=null;
		try {
			
			productList=new ArrayList<Product>();
			Query query=session.createQuery("select p.productId from ProductEntity p where p.productType='N'");
			List<Integer> object=query.list();
			for (int i = 0; i < object.size(); i++) {
				Product product=new Product();
				product.setProductId(object.get(i).intValue());
				productList.add(product);
			}
			session.getTransaction().commit();
		}catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getProductsList", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}finally {
			
			session.close(); 
		}
		return productList;
	}
}
