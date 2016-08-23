package com.infy.jcart.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.ProductDetails;
import com.infy.jcart.entites.UserWishListEntity;
import com.infy.jcart.entites.UserWishListPK;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;

public class WishListDAOImpl implements WishListDAO {

	private SessionFactory sessionFactory = HibernateUtility
			.getSessionFactory();

	/***
	 * @description this method finds wishes for a particular user, finds
	 *              product details according to the wishes of user, populates a
	 *              list of ProductDetails and returns it
	 * @param String
	 * @return List<ProductDetails>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ProductDetails> getWishListDetails(String userId)
			throws Exception {
		String selectedQuery = null;
		List<ProductDetails> wishListDetails = new ArrayList<ProductDetails>();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Query getProductIds = session
					.createQuery("select w from UserWishListEntity w where w.listPK.userId=?");
			getProductIds.setParameter(0, userId);
			List<UserWishListEntity> wishList = getProductIds.list();
			
			for (UserWishListEntity u : wishList) {
				ProductDetails productDetails = new ProductDetails();
				/** get the price for a particular product **/
				Query getProductPrice = session
						.createQuery("select p.price from NewProductsEntity p where p.productId=?");
				getProductPrice.setParameter(0, u.getListPK().getProductId());
				List<Object> price = getProductPrice.list();
				
				/** populate product price to productDetailsTO **/
				productDetails.setPrice((Double) price.get(0));
				
				/** get the category of the product **/
				Query getProductCategory = session
						.createQuery("select p.categoryId from ProductEntity p where p.productId=?");
				getProductCategory
						.setParameter(0, u.getListPK().getProductId());
				List<Object> categoryId = getProductCategory.list();
				
				/** get the category name for the particular category **/
				Query getCategory = session
						.createQuery("select c.categoryName from CategoryEntity c where c.categoryId=?");
				getCategory.setParameter(0, (Integer) categoryId.get(0));
				List<Object> categoryName = getCategory.list();
				/** populate the category name to the productDetailsTO **/
				productDetails.setCategoryName(categoryName.get(0).toString());
				/** get the product details from the selected category **/
				
				
				if (categoryName.get(0).toString().equalsIgnoreCase("Clothing")) {
					selectedQuery = "select c.productName from ClothingEntity c where c.productId=?";
				} else if (categoryName.toString().equalsIgnoreCase("Bags")) {
					selectedQuery = "select b.productName from BagEntity b where b.productId=?";
				} else if (categoryName.toString().equalsIgnoreCase("Books")) {
					selectedQuery = "select b.productName from BooksEntity b where b.productId=?";
				} else if (categoryName.toString().equalsIgnoreCase("Computer")) {
					selectedQuery = "select c.productName from ComputersEntity c where c.productId=?";
				} else if (categoryName.toString().equalsIgnoreCase("Camera")) {
					selectedQuery = "select c.productName from CameraEntity c where c.productId=?";
				} else if (categoryName.toString().equalsIgnoreCase("TV")) {
					selectedQuery = "select t.productName from TelevisionEntity t where t.productId=?";
				} else if (categoryName.toString().equalsIgnoreCase("Mobile")) {
					selectedQuery = "select m.productName from MobileEntity m where m.productId=?";
				} else if (categoryName.toString().equalsIgnoreCase("Footwear")) {
					selectedQuery = "select f.productName from FootwearEntity f where f.productId=?";
				}
				/** execute the selected query and get the product details **/
				Query getProductDetails = session.createQuery(selectedQuery);
				getProductDetails.setParameter(0, u.getListPK().getProductId());
				List l = getProductDetails.list();
				Object[] productName = (Object[]) l.toArray();
				/**
				 * populate the product name and product id to the
				 * productDetailsTO
				 **/
				productDetails.setProductName(productName[0].toString());
				productDetails.setProductId(u.getListPK().getProductId());
				/** populate the wishListDetails list with the product details **/
				wishListDetails.add(productDetails);
				session.getTransaction().commit();
			}

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getWishListDetails", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close();
			 
		}
		return wishListDetails;
	}

	/***
	 * @description this method removes a particular user wish from database
	 * @param ProductDetails
	 *            , String
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public void removeFromWishList(ProductDetails productToRemove, String userId)
			throws Exception {
		Session session = sessionFactory.openSession();

		try {
			UserWishListPK userPk = new UserWishListPK();
			userPk.setProductId(productToRemove.getProductId());
			userPk.setUserId(userId);
			/** find the product detail from the user wish list **/
			UserWishListEntity userWish = (UserWishListEntity) session.get(
					UserWishListEntity.class, userPk);
			session.beginTransaction();
			/** remove the product from the wish list **/
			session.delete(userWish);
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"removeFromWishList", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close();
			 
		}
	}

}
