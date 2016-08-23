package com.infy.jcart.dao;


import java.util.ArrayList;
import java.util.List;




import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.ProductVariations;
import com.infy.jcart.entites.ProductVariationsEntity;
import com.infy.jcart.entites.ProductVariationsPK;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;

public class VariantDAOImpl implements VariantDAO {

	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

	/***
	 * @description this method populates a ProductVariationsEntity object,
	 *              persists it and returns the variation code generated using
	 *              getNextVariationCode() method
	 * @param ProductVariations
	 * @return String
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public String addVariant(ProductVariations variations) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			ProductVariationsEntity entity = new ProductVariationsEntity();
			if (variations.getColor() != null)
				entity.setColor(variations.getColor());
			else
				entity.setColor("NA");
			if (variations.getFit() != null)
				entity.setFit(variations.getFit());
			else
				entity.setFit("NA");
			entity.setStock(variations.getStock());
			ProductVariationsPK pk = new ProductVariationsPK();
			pk.setProductId(variations.getProductId());
			String vCode = getNextVariationCode(variations.getProductId());
			pk.setVariationCode(vCode);
			entity.setProductVariationsPk(pk);
			entity.setImageUrl(variations.getProductId() + ".png");
			session.save(entity);
			
			session.getTransaction().commit();
			
			return entity.getProductVariationsPk().getVariationCode();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "addVariant",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}

	}

	/***
	 * @description this method finds the number of variation for the particular
	 *              product and accordingly generates a new variation code
	 * @param Integer
	 * @return String
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("rawtypes")
	public String getNextVariationCode(Integer productId) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Query query = session
					.createSQLQuery("select trim('C' from max(VARIATIONCODE)) from jcart_productvariations where VARIATIONCODE!='NA' and productid=?");
			query.setParameter(0, productId);
			List list = query.list();
			String bd = (String) (list.get(0));
			//String bd = (String) ((Vector) list.get(0)).get(0);
			Integer vCode = null;
			if (bd == null)
				vCode = 1001;
			else
				vCode = Integer.parseInt(bd);
			
			session.getTransaction().commit();
			
			return "C" + (vCode + 1);
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getNextVariationCode", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {

			session.close(); 
		}
	}

	/***
	 * @description this method sets the image url path of the variation a
	 *              ccording to the product and variation code
	 * @param Integer
	 *            , String, String
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public void addImage(Integer productId, String variationCode,
			String imageUrl) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			ProductVariationsPK pk = new ProductVariationsPK();
			pk.setProductId(productId);
			pk.setVariationCode(variationCode);
			ProductVariationsEntity entity = (ProductVariationsEntity) session
					.get(ProductVariationsEntity.class, pk);
			entity.setImageUrl(imageUrl);
			session.saveOrUpdate(entity);
			
			session.getTransaction().commit();
			
		}
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "addImage",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}

	}

	
	

	/***
	 * @description this method finds all the available variations for a
	 *              particular product, populates a list of ProductVariations
	 *              and returns it
	 * @param Integer
	 * @return List<ProductVariations>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ProductVariations> getAllVariants(Integer productId)
			throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<ProductVariations> toList = new ArrayList<ProductVariations>();
		try {
			
		
					
			Query query = session.createQuery("select j from ProductVariationsEntity j where j.productVariationsPk.variationCode!='NA' and j.stock>0 and j.productVariationsPk.productId=?");
			query.setParameter(0, productId);
			List<ProductVariationsEntity> list = query.list();
			
			for (ProductVariationsEntity obj : list) 
			{
						ProductVariations to = new ProductVariations();
						to.setColor(obj.getColor());
						to.setFit(obj.getFit());
						to.setImageUrl(obj.getImageUrl());
						to.setProductId(obj.getProductVariationsPk().getProductId());
						to.setStock(obj.getStock());
						to.setVariationCode(obj.getProductVariationsPk().getVariationCode());
						toList.add(to);
			}
			session.getTransaction().commit();
		} 
		catch (Exception exception) 
		{
			
			JCartLogger.logError(this.getClass().getName(), "getAllVariants",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally 
		{
			
			session.close(); 
		}
		return toList;
	}

	/***
	 * @description this method finds the product variation details of a particular 
	 * 				product, populates a ProductVariations and returns it
	 * @param Integer, String
	 * @return ProductVariations
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public ProductVariations getVariantDetails(Integer productId,
			String variationCode) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		ProductVariations variations = new ProductVariations();
		try {
			ProductVariationsPK pk = new ProductVariationsPK();
			pk.setProductId(productId);
			pk.setVariationCode(variationCode);
			ProductVariationsEntity entity = (ProductVariationsEntity) session.get(
					ProductVariationsEntity.class, pk);
			if (entity != null) {

				variations.setColor(entity.getColor());
				variations.setFit(entity.getFit());
				variations.setImageUrl(entity.getImageUrl());
				variations.setProductId(entity.getProductVariationsPk().getProductId());
				variations.setStock(entity.getStock());
				variations.setVariationCode(entity.getProductVariationsPk()
						.getVariationCode());

			}
			session.getTransaction().commit();
			
			return variations;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getVariantDetails",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}
	}

	/***
	 * @description this method updates stock for a particular product variation 
	 * @param ProductVariations
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public void updateStock(ProductVariations variations) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			ProductVariationsPK pk = new ProductVariationsPK();
			pk.setProductId(variations.getProductId());
			pk.setVariationCode(variations.getVariationCode());
			ProductVariationsEntity entity = (ProductVariationsEntity) session.get(
					ProductVariationsEntity.class, pk);
			if (entity != null)
				entity.setStock(variations.getStock());
			session.saveOrUpdate(entity);
			
			session.getTransaction().commit();
		}
		catch (Exception exception) 
		{
			
			JCartLogger.logError(this.getClass().getName(), "updateStock",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally 
		{
		
			session.close(); 
		}

	}

}
