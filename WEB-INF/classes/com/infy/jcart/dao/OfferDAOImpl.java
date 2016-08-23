package com.infy.jcart.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;




import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.ProductOffer;
import com.infy.jcart.entites.ProductOfferEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;

public class OfferDAOImpl implements OfferDAO {

	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

	/***
	 * @description This method finds and returns the ProductOfferEntity object
	 *              based on the offerCode.
	 * @param Integer
	 * @return ProductOfferEntity
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	private ProductOfferEntity getProductOfferEntityDetailsByOfferCode(
			Integer offerCode) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		ProductOfferEntity entity = null;
		
		try {
			entity = (ProductOfferEntity) session.get(ProductOfferEntity.class,
					offerCode);
			
			session.getTransaction().commit();
			
		}
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getProductOfferEntityDetailsByOfferCode",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}

		return entity;
	}

	/***
	 * @description This method finds the ProductOfferEntity and checks whether
	 *              it is empty or not and accordingly returns a String.
	 * @param Login
	 * @return String
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public String getProductOffers(Integer offerCode) throws Exception {
		Session session = sessionFactory.openSession();
		ProductOfferEntity entity = null;
		String offerDetailsFound = null;
		session.beginTransaction();
		try {
			entity = getProductOfferEntityDetailsByOfferCode(offerCode);
			if (entity == null) {
				offerDetailsFound = "Not Found";
			} else {
				offerDetailsFound = "Found";
			}
			session.getTransaction().commit();
			
		}
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getProductOffers",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}
		return offerDetailsFound;
	}

	/***
	 * @description this method populates a ProductOfferEntity, persists it and
	 *              returns the offer code obtained
	 * @param ProductOffer
	 * @return Integer
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public Integer fixOfferDetails(ProductOffer offer) throws Exception {
		Session session = sessionFactory.openSession();

		try {
			ProductOfferEntity entity = new ProductOfferEntity();
			entity.setEndDate(offer.getEndDate());
			entity.setOfferDetails(offer.getOfferDetails());
			entity.setOfferDiscount(offer.getOfferDiscount());
			entity.setStartDate(offer.getStartDate());
			session.beginTransaction();
			session.persist(entity);
			session.getTransaction().commit();
			return entity.getOfferCode();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "fixOfferDetails",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close(); 
		}
	}

	/***
	 * @description this method finds every offer and updates it
	 * @param List
	 *            <ProductOffer>
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public void updateOfferDetails(List<ProductOffer> offerList)
			throws Exception {
		Session session = sessionFactory.openSession();

		try {
			for (ProductOffer offer : offerList) {
				ProductOfferEntity entity = getProductOfferEntityDetailsByOfferCode(offer
						.getOfferCode());
				if (entity != null) {
					entity.setEndDate(offer.getEndDate());
					entity.setOfferDetails(offer.getOfferDetails());
					entity.setOfferDiscount(offer.getOfferDiscount());
					entity.setStartDate(offer.getStartDate());
				}
				session.beginTransaction();
				session.saveOrUpdate(entity);
				session.getTransaction().commit();
			}
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"updateOfferDetails", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {

			session.close(); 
		}
	}

	/***
	 * @description this method finds the offer, sets its end date as one day
	 *              before today's date and updates it
	 * @param Integer
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public void removeOfferDetails(Integer offerCode) throws Exception {
		Session session = sessionFactory.openSession();

		try {
			ProductOfferEntity entity = getProductOfferEntityDetailsByOfferCode(offerCode);
			if (entity != null) {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1);
				entity.setEndDate(cal);
				session.beginTransaction();
				session.saveOrUpdate(entity);
				session.getTransaction().commit();
			}
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"removeOfferDetails", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {

			session.close(); 
		}
	}

	/***
	 * @description this method finds the particular offer populates a
	 *                  ProductOffer and returns it
	 * @param Integer
	 * @return ProductOffer
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public ProductOffer getOfferDetails(Integer offerCode) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		ProductOffer offer = new ProductOffer();
		try {
			ProductOfferEntity entity = getProductOfferEntityDetailsByOfferCode(offerCode);
			if (entity != null) {
				offer.setOfferCode(entity.getOfferCode());
				offer.setEndDate(entity.getEndDate());
				offer.setOfferDetails(entity.getOfferDetails());
				offer.setOfferDiscount(entity.getOfferDiscount());
				offer.setStartDate(entity.getStartDate());
			}
			session.getTransaction().commit();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getOfferDetails",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
	
			session.close(); 
		}
		return offer;
	}

	/***
	 * @description this method finds all the offer codes and returns the list
	 *              obtained
	 * @return List<Integer>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAllOfferCodes() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Query query = session
					.createQuery("select s.offerCode from ProductOfferEntity s where s.endDate>=?");
			query.setParameter(0, Calendar.getInstance());
			session.getTransaction().commit();
			
			return query.list();

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllOfferCodes",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {

			session.close(); 
		}
	}

	/***
	 * @description this method finds all the offers whose end date is after
	 *              today's date, populates a list of ProductOffer and returns
	 *              it
	 * @return List<ProductOffer>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ProductOffer> getAllOffers() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<ProductOffer> toList=new ArrayList<ProductOffer>();

		try {
			Query query=session.createQuery("select s from ProductOfferEntity s where s.endDate>=?");
			query.setParameter(0, Calendar.getInstance());
			List<ProductOfferEntity> list=query.list();
			
			for(ProductOfferEntity entity:list){
				ProductOffer offer=new ProductOffer();
				offer.setOfferCode(entity.getOfferCode());
				offer.setEndDate(entity.getEndDate());
				offer.setOfferDetails(entity.getOfferDetails());
				offer.setOfferDiscount(entity.getOfferDiscount());
				offer.setStartDate(entity.getStartDate());
				toList.add(offer);
			}
			
			session.getTransaction().commit();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllOffers",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}
		return toList;
	}

}
