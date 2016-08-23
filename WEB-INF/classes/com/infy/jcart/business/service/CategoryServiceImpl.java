package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.Category;
import com.infy.jcart.beans.SubCategory;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;

public class CategoryServiceImpl implements CategoryService{
	
	
	/**
	 * @description this method invokes getAllCategories()
	 * method of CategoryDAOImpl class and returns the list obtained
	 * @return List<CategoryTO> 
	 * @throws CategoryService.NO_CATEGORIES_AVAILABLE
	 * @catch Exception
	 */

	public List<Category> getAllCategories() throws Exception {
		try {
			List<Category> list=Factory.createCategoryDAO().getAllCategories();
			if(list.isEmpty()){
				throw new Exception("CategoryService.NO_CATEGORIES_AVAILABLE");
			}else{
				return list;
			}
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllCategories",
					exception.getMessage());
			throw exception;
		}
	}
	/**
	 * @description this method invokes getSubCategories()
	 * method of CategoryDAOImpl class and returns the list obtained
	 * @param Integer
	 * @return List<SubCategoryTO> 
	 * @throws CategoryService.NO_SUB_CATEGORIES_AVAILABLE
	 * @catch Exception
	 */
	public List<SubCategory> getSubCategories(Integer categoryId) throws Exception {
		try {
			List<SubCategory> list=Factory.createCategoryDAO().getSubCategories(categoryId);
			if(list.size()==0)
				throw new Exception("CategoryService.NO_SUB_CATEGORIES_AVAILABLE");
			else
				return list;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getSubCategories",
					exception.getMessage());
			throw exception;
		}
	}
	/**
	 * @description this method invokes addSubCategory()
	 * method of CategoryDAOImpl class and returns the subCategoryId obtained
	 * @param Integer, String
	 * @return Integer
	 * @throws  Exception
	 * @catch Exception
	 */
	public Integer addSubCategory(Integer categoryId, String subCategoryName) throws Exception {
		try {
			return Factory.createCategoryDAO().addSubCategory(categoryId,subCategoryName);
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "addSubCategory",
					exception.getMessage());
			throw exception;
		}
	}
	/**
	 * @description this method invokes removeSubCategory()
	 * method of CategoryDAOImpl class 
	 * @param Integer
	 * @return void
	 * @throws  Exception
	 * @catch Exception
	 */
	public void removeSubCategory(Integer subCategoryId) throws Exception {
		try {
			Factory.createCategoryDAO().removeSubCategory(subCategoryId);
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "removeSubCategory",
					exception.getMessage());
			throw exception;
		}
	}
}
