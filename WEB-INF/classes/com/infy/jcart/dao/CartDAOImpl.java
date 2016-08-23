package com.infy.jcart.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.Bill;
import com.infy.jcart.beans.ProductOffer;
import com.infy.jcart.beans.ProductVariations;
import com.infy.jcart.entites.FootWearEntity;
import com.infy.jcart.entites.NewProductsEntity;
import com.infy.jcart.entites.ProductEntity;
import com.infy.jcart.entites.ProductOfferEntity;
import com.infy.jcart.entites.ProductVariationsEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;
import com.infy.jcart.resources.ListFiles;

public class CartDAOImpl implements CartDAO {

	private SessionFactory sessionFactory = HibernateUtility
			.getSessionFactory();

	/***
	 * @description this method finds the particular product and returns its
	 *              price
	 * @param Integer
	 * @return Double
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public Double getProductPrice(Integer productId) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Double price = 0.0;
		try {

			NewProductsEntity en = new NewProductsEntity();
			en = (NewProductsEntity) session.get(NewProductsEntity.class,
					productId);
			if (en != null) 
			{
				price = en.getPrice();
			}
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getProductPrice",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close();
			 
		}

		return price;
	}

	/***
	 * @description this method finds variations for a particular product, finds
	 *              image url path for it, populates a list of ProductVariations
	 *              and returns it
	 * @param Integer
	 * @return List<ProductVariations>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ProductVariations> getProductVariations(Integer productId)
			throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<ProductVariations> tolist = new ArrayList<ProductVariations>();
		String dbPath = JCartConfig.getPath("imageUrlPath");
		String dirPath = JCartConfig.getPath("resourcePath");
		try {
			Query q = session
					.createQuery("Select e from ProductVariationsEntity e where e.productVariationsPk.productId=? ");
			q.setParameter(0, productId);
			List<ProductVariationsEntity> plist = q.list();



			if (plist.size() != 0) {

				List<String> listNames = ListFiles.listFiles(JCartConfig
						.getPath("imageUrlPath"));

				for (ProductVariationsEntity productVariationsEntity : plist) {
					ProductVariations variations = new ProductVariations();
					variations.setColor(productVariationsEntity.getColor());
					variations.setFit(productVariationsEntity.getFit());

					int flag = 0;
					for (int i = 0; i < listNames.size(); i++) {
						String dirPic = listNames.get(i);
						String dbPic = productVariationsEntity.getImageUrl();
						if (dirPic.equals(dbPic)) {
							variations.setImageUrl(dbPath
									+ productVariationsEntity.getImageUrl());
							flag++;
							break;
						}

					}

					if (flag == 0) {
						ProductEntity entity = (ProductEntity) session.get(
								ProductEntity.class, productId);
						switch (entity.getCategoryId()) {
						case 108:
							variations
									.setImageUrl(dirPath + "bags_default.png");
							break;
						case 101:
							variations
									.setImageUrl(dirPath + "book_default.png");

							break;
						case 103:
							variations.setImageUrl(dirPath
									+ "camera_default.png");
							break;
						case 106:
							variations.setImageUrl(dirPath
									+ "clothing_default.png");
							break;
						case 102:
							variations.setImageUrl(dirPath
									+ "computer_default.png");
							break;
						case 107:
							FootWearEntity footWear = (FootWearEntity) session
									.get(FootWearEntity.class, productId);
							if (footWear.getIdealFor().equals('M')) {
								variations.setImageUrl(dirPath
										+ "male_footwear_default.png");
							} else if (footWear.getIdealFor().equals('F')) {
								variations.setImageUrl(dirPath
										+ "female_footwear_default.png");
							}
							break;
						case 105:
							variations.setImageUrl(dirPath
									+ "mobile_default.png");
							break;
						case 104:
							variations.setImageUrl(dirPath
									+ "television_default.png");
							break;
						default:
							variations.setImageUrl(dirPath + "oathofvps.png");
							break;
						}

					}
					variations.setProductId(productVariationsEntity
							.getProductVariationsPk().getProductId());
					variations.setStock(productVariationsEntity.getStock());
					variations.setVariationCode(productVariationsEntity
							.getProductVariationsPk().getVariationCode());
					tolist.add(variations);
				}
			}
			session.getTransaction().commit();
		} catch (Exception exception) {
		
			JCartLogger.logError(this.getClass().getName(),
					"getProductVariations", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close();
			 
		}

		return tolist;
	}

	

	/***
	 * @descriptionthis this method finds NewProductsEntity and sets all its
	 *                  details to Bill and returns it
	 * @param Integer
	 * @return Bill
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public Bill getProductDetails(Integer productId) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Bill bill = new Bill();
		try {
			NewProductsEntity en = (NewProductsEntity) session.get(
					NewProductsEntity.class, productId);
			if (en != null) {
				bill.setDiscount(en.getDiscount());
				bill.setProductId(en.getProductId());
				bill.setPrice(en.getPrice());
				bill.setOffercode(en.getOfferCode());
				// retrive offer details according to offercode and set to
				if (en.getOfferCode() != null) {
					ProductOffer pto = getOfferDetails(bill.getOffercode());
					bill.setOfferStartDate(pto.getStartDate());
					bill.setOfferEndDate(pto.getEndDate());
					bill.setOfferDiscount(pto.getOfferDiscount());
					bill.setOfferDetails(pto.getOfferDetails());
				}
			}
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getProductDetails", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close();
			 
		}

		return bill;
	}

	/***
	 * @descriptionthis this method finds Product Offer for an offer code
	 *                  populates a ProductOffer and returns it
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
			ProductOfferEntity en = (ProductOfferEntity) session.get(
					ProductOfferEntity.class, offerCode);
			if (en != null) {
				offer.setEndDate(en.getEndDate());
				offer.setStartDate(en.getStartDate());
				offer.setOfferCode(en.getOfferCode());
				offer.setOfferDetails(en.getOfferDetails());
				offer.setOfferDiscount(en.getOfferDiscount());

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

}
