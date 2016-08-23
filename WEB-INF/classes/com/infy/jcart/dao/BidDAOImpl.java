package com.infy.jcart.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.Category;
import com.infy.jcart.beans.Product;
import com.infy.jcart.beans.ProductBid;
import com.infy.jcart.beans.ProductDetails;
import com.infy.jcart.entites.BagsEntity;
import com.infy.jcart.entites.BooksEntity;
import com.infy.jcart.entites.CameraEntity;
import com.infy.jcart.entites.CategoryEntity;
import com.infy.jcart.entites.ClothingEntity;
import com.infy.jcart.entites.ComputersEntity;
import com.infy.jcart.entites.FootWearEntity;
import com.infy.jcart.entites.MobileEntity;
import com.infy.jcart.entites.ProductBidEntity;
import com.infy.jcart.entites.ProductEntity;
import com.infy.jcart.entites.TelevisionEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;



public class BidDAOImpl implements BidDAO{
	
	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	  /**
     * @description this method finds the used products whose seller id 
     * is as passed by the user, populates a list of Product and returns it
     * @param String
     * @return List <Product>
     * @throws DAO.TECHNICAL_ERROR
     * @catch Exception
     */
	@SuppressWarnings("unchecked")
	public List<Product> getProductsList(String userId) throws Exception{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Product> productList=null;
		try {
			
			productList=new ArrayList<Product>();
			
			Query query=session.createQuery("select p.productId from ProductEntity p where p.productType='U' and p.sellerId=?");
			
			query.setParameter(0, userId);
			List<Integer> ref=query.list();
		
			for (int i = 0; i < ref.size(); i++) {
				
				Product product=new Product();
				product.setProductId(ref.get(i).intValue());
				productList.add(product);
			}
			session.getTransaction().commit();
			/** return the populated list object **/
			return productList;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getProductsList", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}
		
		
	}
	 /**
     * @description this method returns the product id of all those products
     * which are not available for any open bid
     * 
     * @return List <Integer>
     * @throws DAO.TECHNICAL_ERROR
     * @catch Exception
     */
	@SuppressWarnings("unchecked")
	public List<Integer> getBiddedProductsList() throws Exception{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			Query query=session.createQuery("select p.productId from ProductEntity p where p.productId not in(select b.productId from ProductBidEntity b where b.bidStatus='O')");
			session.getTransaction().commit();
			return query.list();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getBiddedProductsList", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}
		
	}
	
	 
	 
		
	/** methods used for the implementation of purchasing used products via bidding **/
	
	
	 /**
     * @description this method returns the list of ProductDetails for
     * all those products whose bid is open
     *
     * @return List <ProductDetails>
     * @throws DAO.TECHNICAL_ERROR
     * @catch Exception
     */
	@SuppressWarnings("unchecked")
	public List<ProductDetails> viewBiddingProducts()throws Exception{
		Session session = sessionFactory.openSession();
		
		List<ProductDetails> productBidList=new ArrayList<ProductDetails>();
		String[] entityList={"ComputersEntity","BagsEntity","BooksEntity","CameraEntity","ClothingEntity","TelevisionEntity","MobileEntity","FootWearEntity"};
		String queryString=null;
		String entityName=null;
		try {
			session.beginTransaction();
			Query query=session.createQuery("select b from ProductBidEntity b where b.bidStatus='O'");
			List<ProductBidEntity> list1=query.list();
			
			for(ProductBidEntity b:list1){
				/** create a ProductBid object and set the base price to the object property 
				 * and use the product id to find the sub category and product name
				 * **/
				ProductDetails productDetails=new ProductDetails();
				productDetails.setPrice(b.getBasePrice());
				productDetails.setProductId(b.getProductId());
				/** check the product id category wise and retrieve the product details **/
				for (int i = 0; i < entityList.length; i++) {
					entityName =entityList[i];
					queryString="select p from "+entityName+" p where p.productId=?";
					
					/** execute the query and fetch the product name and set to the ProductDetails object**/
					Query query1=session.createQuery(queryString);
					query1.setParameter(0, b.getProductId());
				
					if(entityName.equals("ComputersEntity"))
					{
						List<ComputersEntity> o=query1.list();
						if(o.size()!=0){
						productDetails.setProductName(o.get(0).getProductName());
						/** add the productDetails to the productBidList object **/
						productBidList.add(productDetails);
						break;}
					}
					else if(entityName.equals("BagsEntity"))
					{
						List<BagsEntity> o=query1.list();
						if(o.size()!=0){
						productDetails.setProductName(o.get(0).getProductName());
						/** add the productDetails to the productBidList object **/
						productBidList.add(productDetails);
						break;}
					}
					else if(entityName.equals("BooksEntity"))
					{
						List<BooksEntity> o=query1.list();
						if(o.size()!=0){
						productDetails.setProductName(o.get(0).getProductName());
						/** add the productDetails to the productBidList object **/
						productBidList.add(productDetails);
						break;}
					}
					else if(entityName.equals("ClothingEntity"))
					{
						List<ClothingEntity> o=query1.list();
						if(o.size()!=0){
						productDetails.setProductName(o.get(0).getProductName());
						/** add the productDetails to the productBidList object **/
						productBidList.add(productDetails);
						break;}
					}
					else if(entityName.equals("FootWearEntity"))
					{
						List<FootWearEntity> o=query1.list();
						if(o.size()!=0){
						productDetails.setProductName(o.get(0).getProductName());
						/** add the productDetails to the productBidList object **/
						productBidList.add(productDetails);
						break;}
					}
					else if(entityName.equals("MobileEntity"))
					{
						List<MobileEntity> o=query1.list();
						if(o.size()!=0){
						productDetails.setProductName(o.get(0).getProductName());
						/** add the productDetails to the productBidList object **/
						productBidList.add(productDetails);
						break;}
					}
					else if(entityName.equals("TelevisionEntity"))
					{
						List<TelevisionEntity> o=query1.list();
						if(o.size()!=0){
						productDetails.setProductName(o.get(0).getProductName());
						/** add the productDetails to the productBidList object **/
						productBidList.add(productDetails);
						break;}
					}
					else if(entityName.equals("CameraEntity"))
					{
						List<CameraEntity> o=query1.list();
						if(o.size()!=0){
						productDetails.setProductName(o.get(0).getProductName());
						/** add the productDetails to the productBidList object **/
						productBidList.add(productDetails);
						break;}
					}
					
				}
			}
			/** return the populated productBidList object **/
			session.getTransaction().commit();
			return productBidList;
		}  
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"viewBiddingProducts", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}
		
	}
	/**
     * @description this method invokes getLastBidAmount method a obtains a ProductBid
     * populates a bid entity with all the values and saves it
     * @param ProductDetails
     * @return void
     * @throws DAO.TECHNICAL_ERROR
     * @catch Exception
     */
	public void saveBid(ProductDetails productBidDetails)throws Exception{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		ProductBid bidTO=new ProductBid();
		ProductBidEntity bidEntity=new ProductBidEntity();
		try {
		
			/** get the bidding details of the product **/
			bidTO=getLastBidAmount(productBidDetails.getProductId());
			bidEntity.setProductId(bidTO.getProductId());
			bidEntity.setBidStartDate(bidTO.getBidStartDate());
			bidEntity.setDuration(bidTO.getDuration());
			bidEntity.setIncrementValue(bidTO.getIncrementValue());
			bidEntity.setBidStatus(bidTO.getBidStatus());
			/** set the last but one base price as the previous base price for the product **/
			bidEntity.setLastButOneBasePrice(bidTO.getBasePrice());
			/** set the new bid base price as the amount accepted from the bidder **/
			bidEntity.setBasePrice(productBidDetails.getBidAmount());
			if(bidTO.getLastBidderId()!=null){
			/** set the last but one bidder id to the last bidder id **/
			bidEntity.setLastButOneBidderId(bidTO.getLastBidderId());
			/** set the last bidder id as the id of the new bidder **/
			bidEntity.setLastBidderId(productBidDetails.getBidderId());
			}
			else{
				/** set the new bidder id as the last bidder id**/
				bidEntity.setLastBidderId(productBidDetails.getBidderId());
			}
			/** increment the no of bids value by one **/
			bidEntity.setNumberOfBids(bidTO.getNumberOfBids()+1);
			
			session.persist(bidEntity);
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"saveBid", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}
		
	}
	/**
     * @description this method finds a Product bid for the product id 
     * populates a ProductBid and returns it
     * @param Integer
     * @return ProductBid
     * @throws DAO.TECHNICAL_ERROR
     * @catch Exception
     */
	public ProductBid getLastBidAmount(Integer productId)throws Exception{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		ProductBid bidTO=new ProductBid();
		try {
			
			ProductBidEntity b=(ProductBidEntity)session.get(ProductBidEntity.class, productId);
			bidTO.setBasePrice(b.getBasePrice());
			bidTO.setLastBidderId(b.getLastBidderId());
			bidTO.setProductId(b.getProductId());
			bidTO.setBidStartDate(b.getBidStartDate());
			bidTO.setNumberOfBids(b.getNumberOfBids());
			bidTO.setDuration(b.getDuration());
			bidTO.setIncrementValue(b.getIncrementValue());
			bidTO.setBidStatus(b.getBidStatus());
			session.getTransaction().commit();
			/** return the populated bidTO object with bidding details **/
			return bidTO;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getLastBidAmount", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}
		
	}
	/**
     * @description this method finds all the records from CategoryEntity
     * populates a list of Category and returns it
     * @return List<Category>
     * @throws Exception
     * @catch Exception
     */
	@SuppressWarnings("unchecked")
	public List<Category> getAllCategoryList()throws Exception{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Category> categoryList=new ArrayList<Category>();
		try {
		
			Query query=session.createQuery("select c from CategoryEntity c");
			List<CategoryEntity> list=query.list();
			for(CategoryEntity c:list)
			{
				Category cTO=new Category();
				cTO.setCategoryId(c.getCategoryId());
				cTO.setCategoryName(c.getCategoryName());
				categoryList.add(cTO);
			}
			session.getTransaction().commit();
			/** return the populated categoryList object with category details **/
			return categoryList;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getAllCategoryList", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}
		
	}
	
	/**
     * @description this method persists the used product to ProductEntity
     * by setting the status as 'U' and returns the product id generated
     * @param Product
     * @return Integer
     * @throws DAO.TECHNICAL_ERROR
     * @catch Exception
     */
	public Integer addUsedProductsForBidding(Product productDetails) throws Exception{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			
			ProductEntity product=new ProductEntity();
			product.setCategoryId(productDetails.getCategoryId());
			product.setSubCategory(productDetails.getSubCategory());
			product.setProductType('U');
			product.setSellerId(productDetails.getSellerId());
			session.save(product);
			session.getTransaction().commit();
			/** return the generated product id **/
			return product.getProductId();
		} 
		 catch (Exception exception) {
				JCartLogger.logError(this.getClass().getName(),
						"addUsedProductsForBidding", exception.getMessage());
				throw new Exception("DAO.TECHNICAL_ERROR");
			} finally {
				
				session.close(); 
			}
	}
}
