package com.infy.jcart.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;






import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.Category;
import com.infy.jcart.beans.SubCategory;
import com.infy.jcart.beans.UserProductRequest;
import com.infy.jcart.entites.CategoryEntity;
import com.infy.jcart.entites.SubCategoryEntity;
import com.infy.jcart.entites.UserProductRequestEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;



public class RequestProductDAOImpl implements RequestProductDAO{

	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	/***
	 * @description this method populates a  UserProductRequestEntity object,
	 * persists it and returns the request id generated
	 * and returns the request id obtained
	 * @param UserProductRequest
	 * @return Integer

	 * @throws  Exception
	 */
	public Integer requestProduct(UserProductRequest productDetails) throws Exception{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		UserProductRequestEntity product=null;
		try {
			
			product=new UserProductRequestEntity();
			product.setDescription(productDetails.getDescription());
			product.setRequestDate(Calendar.getInstance());
			product.setStatus('P');
			product.setSubCategory(productDetails.getSubCategory());
			product.setUserId(productDetails.getUserId());
			
			session.persist(product);
			session.getTransaction().commit();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"requestProduct", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}finally{
			
			session.close(); 
		}
		return product.getRequestId();
	}
	/***
	 * @description this method retrieves all the categories, populates a list of
	 * Category and returns it
	 * and returns the list obtained
	 * @return List<Category>
	 * @throws  Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Category> getAllCategory() throws Exception{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Category> categoryList=new ArrayList<Category>();
		List<CategoryEntity> list=new ArrayList<CategoryEntity>();
		try {
			
			Query getCategory=session.createQuery("select c from CategoryEntity c");
			list=getCategory.list();
			for(CategoryEntity category:list){
				Category c=new Category();
				c.setCategoryId(category.getCategoryId());
				c.setCategoryName(category.getCategoryName());
				categoryList.add(c);
			}
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getAllCategory", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}finally{
			
			session.close(); 
		}
		return categoryList;
	}
	/***
	 * @description this method finds all the subcategories for a particular 
	 * category, populates a list of SubCategory and returns it
	 * @param Integer
	 * @return List<SubCategory>
	 * @throws  Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SubCategory> getAllSubCategory(Integer categoryId) throws Exception{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<SubCategory> subCategoryList=new ArrayList<SubCategory>();
		List<SubCategoryEntity> list=new ArrayList<SubCategoryEntity>();
		try {
		
			Query getSubCategory=session.createQuery("select s from SubCategoryEntity s where s.categoryId=?");
			getSubCategory.setParameter(0, categoryId);
			list=getSubCategory.list();
			
			for(SubCategoryEntity subCategory:list){
				SubCategory s=new SubCategory();
				s.setSubCategoryId(subCategory.getSubCategoryId());
				s.setSubCategoryName(subCategory.getSubCategoryName());
				subCategoryList.add(s);
			}
			session.getTransaction().commit();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getAllCategory", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}finally{
			
			session.close(); 
		}
		return subCategoryList;
	}
}
