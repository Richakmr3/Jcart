package com.infy.jcart.dao;

import java.util.ArrayList;
import java.util.List;





import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.Category;
import com.infy.jcart.beans.SubCategory;
import com.infy.jcart.entites.CategoryEntity;
import com.infy.jcart.entites.SubCategoryEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;

public class CategoryDAOImpl implements CategoryDAO{


	
	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

	

	/**
	 * @description this method finds all the records in CategoryEntity
	 * populates the list of Category and returns it
	 * @return List<Category> 
	 * @throws  DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Category> getAllCategories() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List<Category> toLIst=new ArrayList<Category>();
		try {



			Query query=session.createQuery("select c from CategoryEntity c");

			List<CategoryEntity> list=query.list();

			for(CategoryEntity obj:list){
				Category to=new Category();
				to.setCategoryId(obj.getCategoryId());
				to.setCategoryName(obj.getCategoryName());
				toLIst.add(to);
			}
			session.getTransaction().commit();
			return toLIst;
		}

		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getAllCategories", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}
	}
	/**
	 * @description this method finds all the records in SubCategoryEntity
	 * populates the list of SubCategory and returns it
	 * @param Integer
	 * @return List<SubCategory> 
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SubCategory> getSubCategories(Integer categoryId) throws Exception {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List<SubCategory> toList=new ArrayList<SubCategory>();
		try {


			Query query=session.createQuery("select s from SubCategoryEntity s where s.categoryId=?");
			query.setParameter(0, categoryId);
			List<SubCategoryEntity> list=query.list();

			for(SubCategoryEntity entity:list){
				SubCategory to=new SubCategory();
				to.setSubCategoryId(entity.getSubCategoryId());
				to.setSubCategoryName(entity.getSubCategoryName());
				toList.add(to);
			}
			session.getTransaction().commit();
			return toList;
		}

		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getSubCategories", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}
	}

	/**
	 * @description this method persists the new subcategory 
	 * and returns the subCategoryId geerated
	 * @param Integer, String
	 * @return Integer
	 * @throws  DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public Integer addSubCategory(Integer categoryId, String subCategoryName) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try {


			SubCategoryEntity entity=new SubCategoryEntity();
			entity.setCategoryId(categoryId);
			entity.setSubCategoryName(subCategoryName);

			session.save(entity);
			session.getTransaction().commit();
			return entity.getSubCategoryId();
		}

		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"addSubCategory", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}
	}
	/**
	 * @description this method finds the subcategory and removes
	 * it from database
	 * @param Integer
	 * @return void
	 * @throws  DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public void removeSubCategory(Integer subCategoryId) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try {


			SubCategoryEntity entity=(SubCategoryEntity)session.get(SubCategoryEntity.class, subCategoryId);

			if(entity!=null)
			{
				session.delete(entity);
			}
			session.getTransaction().commit();
		}

		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"removeSubCategory", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally 
		{	
			session.close(); 
		}
	}
}
