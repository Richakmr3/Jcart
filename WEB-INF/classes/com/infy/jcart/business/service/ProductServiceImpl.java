package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.NewProducts;
import com.infy.jcart.beans.Product;
import com.infy.jcart.beans.ProductDetails;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;

public class ProductServiceImpl implements ProductService {

	/***
	 * @description this method invokes addProduct() method of ProductDAOImpl class
	 *              and returns the productId obtained
	 * @param Product
	 *            , Object[], NewProducts
	 * @return Integer
	 * @throws Exception
	 * @catch Exception
	 */
	public Integer addProduct(Product product, Object[] tos,
			NewProducts newProducts) throws Exception {
		try {
			return Factory.createProductDAO().addProduct(product,tos,newProducts);
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "addProduct",
					exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method invokes getAllProducts() method of ProductDAOImpl
	 *              class and returns the list obtained
	 * @param Integer
	 * @return List<Product>
	 * @throws ProductService.NO_PRODUCTS_AVAILABLE
	 * @catch Exception
	 */
	public List<Product> getAllProducts(Integer subCategory) throws Exception {
		try {
			List<Product> list=Factory.createProductDAO().getAllProducts(subCategory);
			if(list.size()==0)
				throw new Exception("ProductService.NO_PRODUCTS_AVAILABLE");
			else
				return list;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllProducts",
					exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method invokes getAllProductsByMe() method of
	 *              ProductDAOImpl class and returns the list obtained
	 * @param Integer
	 * @return List<Product>
	 * @throws ProductService.NO_PRODUCTS_AVAILABLE
	 * @catch Exception
	 */
	public List<Product> getAllProductsByMe(Integer subCategory)
			throws Exception {
		try {
			List<Product> list=Factory.createProductDAO().getAllProductsByMe(subCategory);
			if(list.size()==0)
				throw new Exception("ProductService.NO_PRODUCTS_AVAILABLE");
			else
				return list;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllProductsByMe",
					exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method invokes getRetailerProducts() method of
	 *              ProductDAOImpl class and returns the list obtained
	 * @param Integer
	 * @return List<ProductDetails>
	 * @throws ProductService.NO_PRODUCTS_AVAILABLE
	 * @catch Exception
	 */
	public List<ProductDetails> getRetailerProducts(Integer subCategory,
			String retailerId) throws Exception {
		try {
			List<ProductDetails> list=Factory.createProductDAO().getRetailerProducts(subCategory,retailerId);
			if(list.size()==0)
				throw new Exception("ProductService.NO_PRODUCTS_AVAILABLE");
			else
				return list;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getRetailerProducts",
					exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method invokes getRecentlyAddedProducts() method of
	 *              ProductDAOImpl class and returns the list obtained
	 * @return List<ProductDetails>
	 * @throws ProductService.NO_PRODUCTS_AVAILABLE
	 * @catch Exception
	 */
	public List<ProductDetails> getRecentlyAddedProducts() throws Exception {
		try {
			List<ProductDetails> list=Factory.createProductDAO().getRecentlyAddedProducts();
			if(list.size()==0)
				throw new Exception("ProductService.NO_PRODUCTS_AVAILABLE");
			else
				return list;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getRecentlyAddedProducts",
					exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method invokes getMostPopularProducts() method of
	 *              ProductDAOImpl class and returns the list obtained
	 * @return List<ProductDetails>
	 * @throws ProductService.NO_PRODUCTS_AVAILABLE
	 * @catch Exception
	 */
	public List<ProductDetails> getMostPopularProducts() throws Exception {
		try {
			List<ProductDetails> list=Factory.createProductDAO().getMostPopularProducts();
			if(list.size()==0)
				throw new Exception("ProductService.NO_PRODUCTS_AVAILABLE");
			else
				return list;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getMostPopularProducts",
					exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method invokes getProductsByRetailer() method of
	 *              ProductDAOImpl class and returns the list obtained
	 * @param String
	 *            , Integer
	 * @return List<ProductDetails>
	 * @throws ProductService.NO_PRODUCTS_AVAILABLE
	 * @catch Exception
	 */
	public List<ProductDetails> getProductsByRetailer(String retailerId,
			Integer categoryId) throws Exception {
		try {

			List<ProductDetails> list=Factory.createProductDAO().getProductsByRetailer(retailerId,categoryId);
			if(list.size()==0)
				throw new Exception("ProductService.NO_PRODUCTS_AVAILABLE");
			else
				return list;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getProductsByRetailer",
					exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method invokes getRetailerProducts() method of
	 *              ProductDAOImpl class and returns the list obtained
	 * @param String
	 * @return List<ProductDetails>
	 * @throws ProductService.NO_PRODUCTS_AVAILABLE
	 * @catch Exception
	 */
	public List<ProductDetails> getRetailerProducts(String retailerId)
			throws Exception {
		try {
			
			List<ProductDetails> list=Factory.createProductDAO().getRetailerProducts(retailerId);
			if(list.size()==0)
				throw new Exception("ProductService.NO_PRODUCTS_AVAILABLE");
			else
				return list;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getRetailerProducts",
					exception.getMessage());
			throw exception;
		}
	}

}
