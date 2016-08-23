package com.infy.jcart.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;






import com.infy.jcart.beans.ProductDetails;
import com.infy.jcart.beans.Registration;
import com.infy.jcart.entites.BagsEntity;
import com.infy.jcart.entites.BooksEntity;
import com.infy.jcart.entites.CameraEntity;
import com.infy.jcart.entites.ClothingEntity;
import com.infy.jcart.entites.ComputersEntity;
import com.infy.jcart.entites.FootWearEntity;
import com.infy.jcart.entites.MobileEntity;
import com.infy.jcart.entites.TelevisionEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;



public class ReportDAOImpl implements ReportDAO {

	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		/***
	 * @description this method finds the sale of products for each category,
	 * populates a map by keeping category name as key and number of sales for that
	 * category as value and returns it
	 * @param Calendar, Calendar
	 * @return Map<String, Double>

	 * @throws  Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Integer> getCategorySalesData(Calendar fromDate,
			Calendar toDate) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Map<String, Integer> map = new HashMap<String, Integer>();

		try {

			Query query = session
					.createQuery("select c.categoryName,"
							+ "sum(pd.numOfProducts) "
							+ "from PaymentEntity p, PaymentDetailsEntity pd, "
							+ "OrderLogEntity o,ProductEntity pr,CategoryEntity c "
							+ "where p.paymentId=pd.paymentDetailsPK.paymentId "
							+ "and p.paymentId=o.paymentId "
							+ "and pr.productId=pd.paymentDetailsPK.productId "
							+ "and pr.categoryId=c.categoryId and o.deliveryStatus<>'R'"
							+ "and p.purchaseDate between ? and ? "
							+ "group by c.categoryName");
			query.setParameter(0, fromDate);
			query.setParameter(1, toDate);
			List<Object[]> list = query.list();
			for (Object[] obj : list) {

				map.put((String) obj[0], ((Long) obj[1]).intValue());

			}
			session.getTransaction().commit();
			return map;
		}  catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getCategorySalesData", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}finally{
		
			session.close(); 
		}
	}
	/***
	 * @description this method finds the sale of products for each sub category,
	 * populates a map by keeping subcategory name as key and number of sales for that
	 * category as value and returns it
	 * @param String,Calendar, Calendar
	 * @return Map<String, Double>

	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Integer> getSubCategorySalesData(String categoryName,
			Calendar fromDate, Calendar toDate) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Map<String, Integer> map = new HashMap<String, Integer>();

		try {

			Query query = session
					.createQuery("select s.subCategoryName,"
							+ "sum(pd.numOfProducts) "
							+ "from PaymentEntity p, PaymentDetailsEntity pd, "
							+ "OrderLogEntity o,ProductEntity pr,CategoryEntity c, "
							+ "SubCategoryEntity s  "
							+ "where p.paymentId=pd.paymentDetailsPK.paymentId "
							+ "and p.paymentId=o.paymentId and"
							+ " pr.productId=pd.paymentDetailsPK.productId "
							+ "and pr.categoryId=c.categoryId and o.deliveryStatus<>'R' "
							+ "and c.categoryId=s.categoryId "
							+ "and s.subCategoryId=pr.subCategory  "
							+ "and c.categoryName=? "
							+ "and p.purchaseDate between ? and ? "
							+ "group by s.subCategoryName");
			query.setParameter(0, categoryName);
			query.setParameter(1, fromDate);
			query.setParameter(2, toDate);
			List<Object[]> list = query.list();
			for (Object[] obj : list) {

				map.put((String) obj[0], ((Long) obj[1]).intValue());

			}
			session.getTransaction().commit();
			return map;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getSubCategorySalesData", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}finally{
			
			session.close(); 
		}
	}
	/***
	 * @description this method finds the total bill amount for each customer in
	 * descending order, finds products purchased by each customer,
	 * populates a RegistrationTO and returns it
	 * @return List<Registration>

	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Registration> getValuableCustomers() throws Exception {
	
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Registration> list = new ArrayList<Registration>();
		try {
			Query query =session.createSQLQuery("select SUM(p.netBill) as s, p.buyerId from JCART_PAYMENT p, JCART_ORDERLOGTABLE o where p.paymentId=o.paymentId and o.deliveryStatus!='R' group by p.buyerId order by s desc");
			//Query q = session.getNamedQuery("getValuableCustomer");
			List<Object[]> rs = query.list();

			for (Object[] object : rs) {
				Registration to = new Registration();
			//	System.out.println("");
				to.setAmount(((BigDecimal)object[0]).doubleValue());
				to.setUserId((String)object[1]);
						List<ProductDetails> productList = new ArrayList<ProductDetails>();

				Query q1 = session
						.createQuery("select pr.productId,s.subCategoryName, "
								+ "s.categoryId, pd.numOfProducts, p.purchaseDate "
								+ "from PaymentEntity p,PaymentDetailsEntity pd, "
								+ "ProductEntity pr, SubCategoryEntity s, "
								+ "OrderLogEntity o "
								+ "where p.paymentId=pd.paymentDetailsPK.paymentId "
								+ "and pr.productId=pd.paymentDetailsPK.productId "
								+ "and o.paymentId=p.paymentId and pr.subCategory= s.subCategoryId "
								+ "and o.deliveryStatus<>'R' and p.buyerId=? "
								+ "order by p.purchaseDate desc");
				q1.setParameter(0, to.getUserId());

				List<Object[]> rs2 = q1.list();
				for (Object[] obj : rs2) {
					ProductDetails product = new ProductDetails();
					product.setDateOfPurchase((Calendar) obj[4]);
					product.setSubcategory((String) obj[1]);
					product.setNumOfProducts((Integer) obj[3]);

					Integer categoryId = (Integer) obj[2];
					Integer productId = (Integer) obj[0];
					String name = null, company = null;

					switch (categoryId) {
					case 101:
						BooksEntity entity = (BooksEntity)session.get(
								BooksEntity.class, productId);
						name = entity.getProductName();
						company = entity.getPublisher();
						break;
					case 102:
						ComputersEntity computerEntity = (ComputersEntity)session.get(
								ComputersEntity.class, productId);
						name = computerEntity.getProductName();
						company = computerEntity.getCompany();
						break;

					case 103:
						CameraEntity cameraEntity = (CameraEntity)session.get(
								CameraEntity.class, productId);
						name = cameraEntity.getProductName();
						company = cameraEntity.getCompany();
						break;

					case 104:
						TelevisionEntity televisionEntity = (TelevisionEntity)session.get(
								TelevisionEntity.class, productId);
						name = televisionEntity.getProductName();
						company = televisionEntity.getCompany();
						break;

					case 105:
						MobileEntity mobileEntity = (MobileEntity)session.get(
								MobileEntity.class, productId);
						name = mobileEntity.getProductName();
						company = mobileEntity.getCompany();
						break;

					case 106:
						ClothingEntity clothingEntity = (ClothingEntity)session.get(
								ClothingEntity.class, productId);
						name = clothingEntity.getProductName();
						company = clothingEntity.getCompany();
						break;

					case 107:
						FootWearEntity footWearEntity = (FootWearEntity)session.get(
								FootWearEntity.class, productId);
						name = footWearEntity.getProductName();
						company = footWearEntity.getCompany();
						break;

					case 108:
						BagsEntity bagsEntity = (BagsEntity)session.get(
								BagsEntity.class, productId);
						name = bagsEntity.getProductName();
						company = bagsEntity.getCompany();
						break;
					}
					product.setCompany(company);
					product.setProductName(name);
					productList.add(product);
				}
				to.setList(productList);
				list.add(to);
			}
			session.getTransaction().commit();
			return list;
		} catch (Exception exception) {
			
			
			JCartLogger.logError(this.getClass().getName(),
					"getValuableCustomers", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}finally{
			
			session.close(); 
		}
	}
		}
