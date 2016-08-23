package com.infy.jcart.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.OrderLog;
import com.infy.jcart.entites.CategoryEntity;
import com.infy.jcart.entites.OrderLogEntity;
import com.infy.jcart.entites.SubCategoryEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;

public class ViewMyOrdersDAOImpl implements ViewMyOrdersDAO {

	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

	/***
	 * @description this method finds the order of a particular user, populates
	 *              a list of OrderLog and returns it.
	 * @param String
	 * @return List<OrderLog>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OrderLog> getMyOrders(String userId) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<OrderLog> list = new ArrayList<OrderLog>();
		try {
			Query query = session
					.createQuery("select e from OrderLogEntity e where e.userId=?");
			query.setParameter(0, userId);
			List<OrderLogEntity> entityList = query.list();
			for (OrderLogEntity orderLogEntity : entityList) {
				OrderLog log = new OrderLog();
				if (orderLogEntity.getDeliveryDate() != null) {
					log.setDeliveryDate(orderLogEntity.getDeliveryDate());
				}
				log.setDeliveryStatus(orderLogEntity.getDeliveryStatus());
				log.setNumOfProducts(orderLogEntity.getNumOfProducts());
				log.setOrderDate(orderLogEntity.getOrderDate());
				log.setOrderId(orderLogEntity.getOrderId());
				log.setShipmentDate(orderLogEntity.getShipmentDate());
				log.setUserId(orderLogEntity.getUserId());
				log.setPaymentId(orderLogEntity.getPaymentId());
				list.add(log);
			}
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getMyOrders",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}

		return list;
	}

	
	/***
	 * @description this method finds the category name and sub category name
	 *              for a particular category id and sub category id
	 *              respectively populates a list of String with category name
	 *              as first element and sub category name as second element
	 * @param Integer
	 *            , Integer
	 * @return List<String>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public List<String> getCategorySubcategory(Integer categoryId,
			Integer subCategory) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<String> result = new ArrayList<String>();
		try {
			CategoryEntity categoryEntity = (CategoryEntity) session.get(
					CategoryEntity.class, categoryId);

			if (categoryEntity != null) {
				result.add(categoryEntity.getCategoryName());
			}

			SubCategoryEntity subCategoryEntity = (SubCategoryEntity) session
					.get(SubCategoryEntity.class, subCategory);

			if (subCategoryEntity != null) {
				result.add(subCategoryEntity.getSubCategoryName());
			}
			session.getTransaction().commit();

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getCategorySubcategory", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}

		return result;
	}

	/***
	 * @description this method finds the company name and product name for a
	 *              particular product, populates an Object array with company
	 *              name as the first element and product name as the second
	 *              element
	 * @param Integer
	 *            , Integer
	 * @return Object[]
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("rawtypes")
	public Object[] getProductName(Integer categoryId, Integer productId)
			throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Object[] ob = null;
		try {
			String entity = null;
			switch (categoryId) {
			case 101:
				entity = "BooksEntity";
				break;

			case 102:
				entity = "ComputersEntity";
				break;

			case 103:
				entity = "CameraEntity";
				break;

			case 104:
				entity = "TelevisionEntity";
				break;

			case 105:
				entity = "MobileEntity";
				break;

			case 106:
				entity = "ClothingEntity";
				break;

			case 107:
				entity = "FootWearEntity";
				break;

			case 108:
				entity = "BagsEntity";
				break;

			}

			String s = "";
			if (entity.equalsIgnoreCase("BooksEntity")) {
				s = "c.publisher";
			} else {
				s = "c.company";
			}

			if (entity != null) {

				Query q = session.createQuery("select " + s + " ,c.productName"
						+ " from " + entity + " c where c.productId=?");
				q.setParameter(0, productId);
				List list = q.list();
				if (list != null) {
					ob = (Object[]) list.get(0);
				}

			}
			session.getTransaction().commit();

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getProductName",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}

		return ob;
	}

}
