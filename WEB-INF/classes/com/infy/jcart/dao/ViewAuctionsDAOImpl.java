package com.infy.jcart.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.ProductBid;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;

public class ViewAuctionsDAOImpl implements ViewAuctionsDAO {

	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

	/***
	 * @description this method finds all the open auctions and sort them
	 *              according to date or popularity as wished by user, populates
	 *              a list of ProductBid and returns it
	 * @param String
	 * @return List<ProductBid>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ProductBid> getOpenAuctions(String sortBy) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<ProductBid> list = new ArrayList<ProductBid>();
		try {
			Query q = null;
			if (sortBy.equalsIgnoreCase("date")) {
				q = session
						.createQuery("select p.productId,b.basePrice, "
								+ "b.bidStatus,b.bidStartDate, b.duration,b.numberOfBids, "
								+ "b.lastBidderId,c.categoryName,s.subCategoryName ,p.sellerId "
								+ "from ProductBidEntity b, ProductEntity p, "
								+ "SubCategoryEntity s , CategoryEntity c "
								+ " where b.productId=p.productId "
								+ "and p.categoryId=c.categoryId and "
								+ "p.subCategory=s.subCategoryId and b.bidStatus='O'"
								+ " order by b.bidStartDate desc");
			}

			else if (sortBy.equalsIgnoreCase("popularity")) {
				q = session
						.createQuery("select p.productId,b.basePrice, "
								+ "b.bidStatus,b.bidStartDate, b.duration,b.numberOfBids, "
								+ "b.lastBidderId,c.categoryName,s.subCategoryName ,p.sellerId "
								+ "from ProductBidEntity b, ProductEntity p, "
								+ "SubCategoryEntity s , CategoryEntity c "
								+ " where b.productId=p.productId "
								+ "and p.categoryId=c.categoryId and "
								+ "p.subCategory=s.subCategoryId and b.bidStatus='O'"
								+ " order by b.numberOfBids desc");
			}
			List<Object[]> l = q.list();
			for (Object[] obj : l) {
				ProductBid to = new ProductBid();
				to.setProductId((Integer) obj[0]);
				to.setBasePrice((Double) obj[1]);
				to.setBidStatus((Character) obj[2]);
				to.setBidStartDate((Calendar) obj[3]);
				to.setDuration((Integer) obj[4]);
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(to.getBidStartDate().getTimeInMillis());
				c.add(Calendar.DATE, to.getDuration());
				to.setBidCloseDate(c);

				to.setNumberOfBids((Integer) obj[5]);
				to.setLastBidderId((String) obj[6]);
				to.setCategoryName((String) obj[7]);
				to.setSubCategoryName((String) obj[8]);
				to.setSellerId((String) obj[9]);
				list.add(to);
			}
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getOpenAuctions",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}
		return list;
	}

	/***
	 * @description this method finds all the closed auctions within a period
	 *              and sort them according to date or popularity as wished by
	 *              user, populates a list of ProductBid and returns it
	 * @param String
	 *            , Calendar, Calendar
	 * @return List<ProductBid>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ProductBid> getClosedAuctions(String sortBy, Calendar fromDate,
			Calendar toDate) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<ProductBid> list = new ArrayList<ProductBid>();
		try {
			Query q = null;
			if (sortBy.equalsIgnoreCase("date")) {
				q = session
						.createQuery("select p.productId,b.basePrice, "
								+ "b.bidStatus,b.bidStartDate, b.duration,b.numberOfBids, "
								+ "b.lastBidderId,c.categoryName,s.subCategoryName ,p.sellerId "
								+ "from ProductBidEntity b, ProductEntity p, "
								+ "SubCategoryEntity s , CategoryEntity c "
								+ " where b.productId=p.productId "
								+ "and p.categoryId=c.categoryId and "
								+ "p.subCategory=s.subCategoryId and b.bidStatus='C' "
								+ "and b.bidStartDate between ? and ? "
								+ " order by b.bidStartDate desc");
			} else if (sortBy.equalsIgnoreCase("popularity")) {
				q = session
						.createQuery("select p.productId,b.basePrice, "
								+ "b.bidStatus,b.bidStartDate, b.duration,b.numberOfBids, "
								+ "b.lastBidderId,c.categoryName,s.subCategoryName ,p.sellerId "
								+ "from ProductBidEntity b, ProductEntity p, "
								+ "SubCategoryEntity s , CategoryEntity c "
								+ " where b.productId=p.productId "
								+ "and p.categoryId=c.categoryId and "
								+ "p.subCategory=s.subCategoryId and b.bidStatus='C' "
								+ "and b.bidStartDate between ? and ? "
								+ " order by b.numberOfBids desc");
			}
			q.setParameter(0, fromDate);
			q.setParameter(1, toDate);

			List<Object[]> l = q.list();
			for (Object[] obj : l) {
				ProductBid to = new ProductBid();
				to.setProductId((Integer) obj[0]);
				to.setBasePrice((Double) obj[1]);
				to.setBidStatus((Character) obj[2]);
				to.setBidStartDate((Calendar) obj[3]);
				to.setDuration((Integer) obj[4]);
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(to.getBidStartDate().getTimeInMillis());
				c.add(Calendar.DATE, to.getDuration());
				to.setBidCloseDate(c);

				to.setNumberOfBids((Integer) obj[5]);
				to.setLastBidderId((String) obj[6]);
				to.setCategoryName((String) obj[7]);
				to.setSubCategoryName((String) obj[8]);
				to.setSellerId((String) obj[9]);
				list.add(to);
			}
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getClosedAuctions",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
	
			session.close(); 
		}
		return list;
	}

}
