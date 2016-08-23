package com.infy.jcart.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.infy.jcart.beans.Bags;
import com.infy.jcart.beans.Books;
import com.infy.jcart.beans.Camera;
import com.infy.jcart.beans.Clothing;
import com.infy.jcart.beans.Computers;
import com.infy.jcart.beans.FootWear;
import com.infy.jcart.beans.Mobile;
import com.infy.jcart.beans.PurchaseHistory;
import com.infy.jcart.beans.Television;
import com.infy.jcart.entites.BagsEntity;
import com.infy.jcart.entites.BooksEntity;
import com.infy.jcart.entites.CameraEntity;
import com.infy.jcart.entites.ClothingEntity;
import com.infy.jcart.entites.ComputersEntity;
import com.infy.jcart.entites.FootWearEntity;
import com.infy.jcart.entites.MobileEntity;
import com.infy.jcart.entites.NewProductsEntity;
import com.infy.jcart.entites.PaymentEntity;
import com.infy.jcart.entites.TelevisionEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;

public class PurchaseDAOImpl implements PurchaseDAO {

	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

	@SuppressWarnings("unchecked")
	public List<Bags> getAllBagProducts(String subCategoryName,
			Character idealFor) throws Exception {
		Session session = sessionFactory.openSession();
		List<Bags> bagList = null;
		try {
			bagList = new ArrayList<Bags>();
			session.beginTransaction();
			Query query = session
					.createQuery("select b from BagsEntity b where b.productId in (select p.productId from ProductEntity p where p.productType='N' and p.subCategory=(select c.subCategoryId from SubCategoryEntity c where c.subCategoryName=?))and b.idealFor=?");
			query.setParameter(0, subCategoryName);
			query.setParameter(1, idealFor);
			List<BagsEntity> entityList = query.list();
			for (BagsEntity bag : entityList) {
				Bags bags = new Bags();
				bags.setCompany(bag.getCompany());
				bags.setDescription(bag.getDescription());
				bags.setMaterial(bag.getMaterial());
				bags.setType(bag.getType());
				bags.setProductName(bag.getProductName());
				bags.setProductId(bag.getProductId());
				bags.setWeight(bag.getWeight());
				/** get the price for the product **/
				NewProductsEntity newProduct = (NewProductsEntity) session.get(
						NewProductsEntity.class, bags.getProductId());
				if (newProduct != null) {
					bags.setPrice(newProduct.getPrice());
				} else {
					bags.setPrice(0.0);
				}
				bagList.add(bags);
			}
			session.getTransaction().commit();
			
		}
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getAllBagProducts", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}

		return bagList;
	}

	@SuppressWarnings("unchecked")
	public List<Books> getAllBookProducts(String subCategoryName)
			throws Exception {
		Session session = sessionFactory.openSession();
		List<Books> bookList = null;
		try {
			bookList = new ArrayList<Books>();
			session.beginTransaction();
			Query query = session
					.createQuery("select b from BooksEntity b where b.productId in (select p.productId from ProductEntity p where p.productType='N' and p.subCategory=(select c.subCategoryId from SubCategoryEntity c where c.subCategoryName=?))");
			query.setParameter(0, subCategoryName);
			List<BooksEntity> entityList = query.list();
			for (BooksEntity book : entityList) {
				Books books = new Books();
				books.setAuthor(book.getAuthor());
				books.setDescription(book.getDescription());
				books.setDimensions(book.getDimensions());
				books.setLanguage(book.getLanguage());
				books.setProductName(book.getProductName());
				books.setPublisher(book.getPublisher());
				books.setProductId(book.getProductId());
				/** get the price for the product **/
				NewProductsEntity newProduct = (NewProductsEntity) session.get(
						NewProductsEntity.class, books.getProductId());
				if (newProduct != null) {
					books.setPrice(newProduct.getPrice());
				} else {
					books.setPrice(0.0);
				}
				bookList.add(books);
			}
			session.getTransaction().commit();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getAllBookProducts", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}

		return bookList;
	}

	@SuppressWarnings("unchecked")
	public List<Camera> getAllCameraProducts(String subCategoryName)
			throws Exception {
		Session session = sessionFactory.openSession();
		List<Camera> camList = null;
		try {
			camList = new ArrayList<Camera>();
			session.beginTransaction();
			Query query = session
					.createQuery("select b from CameraEntity b where b.productId in (select p.productId from ProductEntity p where p.productType='N' and p.subCategory=(select c.subCategoryId from SubCategoryEntity c where c.subCategoryName=?))");
			query.setParameter(0, subCategoryName);
			List<CameraEntity> entityList = query.list();
			for (CameraEntity cam : entityList) {
				Camera camera = new Camera();
				camera.setCompany(cam.getCompany());
				camera.setDigitalZoom(cam.getDigitalZoom());
				camera.setMemory(cam.getMemory());
				camera.setOpticalZoom(cam.getOpticalZoom());
				camera.setProductName(cam.getProductName());
				camera.setPixels(cam.getPixels());
				camera.setFocalLength(cam.getFocalLength());
				camera.setPixels(cam.getBattery());
				camera.setProductId(cam.getProductId());
				/** get the price for the product **/
				NewProductsEntity newProduct = (NewProductsEntity) session.get(
						NewProductsEntity.class, camera.getProductId());
				if (newProduct != null) {
					camera.setPrice(newProduct.getPrice());
				} else {
					camera.setPrice(0.0);
				}
				camList.add(camera);
			}
			session.getTransaction().commit();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getAllCameraProducts", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}

		return camList;
	}

	@SuppressWarnings("unchecked")
	public List<Clothing> getAllClothingProducts(String subCategoryName,
			Character idealFor) throws Exception {
		Session session = sessionFactory.openSession();
		List<Clothing> clothList = null;
		try {
			clothList = new ArrayList<Clothing>();
			session.beginTransaction();
			Query query = session
					.createQuery("select b from ClothingEntity b where b.productId in (select p.productId from ProductEntity p where p.productType='N' and p.subCategory=(select c.subCategoryId from SubCategoryEntity c where c.subCategoryName=?))and b.idealFor=?");
			query.setParameter(0, subCategoryName);
			query.setParameter(1, idealFor);
			List<ClothingEntity> entityList = query.list();
			for (ClothingEntity cloth : entityList) {
				Clothing clothing = new Clothing();
				clothing.setCompany(cloth.getCompany());
				clothing.setFabric(cloth.getFabric());
				clothing.setProductId(cloth.getProductId());
				clothing.setProductName(cloth.getProductName());
				/** get the price for the product **/
				NewProductsEntity newProduct = (NewProductsEntity) session.get(
						NewProductsEntity.class, clothing.getProductId());
				if (newProduct != null) {
					clothing.setPrice(newProduct.getPrice());
				} else {
					clothing.setPrice(0.0);
				}
				clothList.add(clothing);
			}
			session.getTransaction().commit();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getAllClothingProducts", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}

		return clothList;
	}

	@SuppressWarnings("unchecked")
	public List<Computers> getAllComputerProducts(String subCategoryName)
			throws Exception {
		Session session = sessionFactory.openSession();
		List<Computers> compList = null;
		try {
			compList = new ArrayList<Computers>();
			session.beginTransaction();
			Query query = session
					.createQuery("select b from ComputersEntity b where b.productId in (select p.productId from ProductEntity p where p.productType='N' and p.subCategory=(select c.subCategoryId from SubCategoryEntity c where c.subCategoryName=?))");
			query.setParameter(0, subCategoryName);
			List<ComputersEntity> entityList = query.list();
			for (ComputersEntity computer : entityList) {
				Computers comp = new Computers();
				comp.setCompany(computer.getCompany());
				comp.setDescription(computer.getDescription());
				comp.setProductId(computer.getProductId());
				comp.setProductName(computer.getProductName());
				comp.setScreenSize(computer.getScreenSize());
				comp.setProcessorName(computer.getProcessorName());
				comp.setProcessorSpeed(computer.getProcessorSpeed());
				/** get the price for the product **/
				NewProductsEntity newProduct = (NewProductsEntity) session.get(
						NewProductsEntity.class, comp.getProductId());
				if (newProduct != null) {
					comp.setPrice(newProduct.getPrice());
				} else {
					comp.setPrice(0.0);
				}
				compList.add(comp);
			}
			session.getTransaction().commit();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getAllComputerProducts", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}

		return compList;
	}

	@SuppressWarnings("unchecked")
	public List<FootWear> getAllFootwearProducts(String subCategoryName,
			Character idealFor) throws Exception {
		Session session = sessionFactory.openSession();
		List<FootWear> footwearList = null;
		try {
			footwearList = new ArrayList<FootWear>();
			session.beginTransaction();
			Query query = session
					.createQuery("select b from FootWearEntity b where b.productId in (select p.productId from ProductEntity p where p.productType='N' and p.subCategory=(select c.subCategoryId from SubCategoryEntity c where c.subCategoryName=?))and b.idealFor=?");
			query.setParameter(0, subCategoryName);
			query.setParameter(1, idealFor);
			List<FootWearEntity> entityList = query.list();
			for (FootWearEntity footWear : entityList) {
				FootWear foot = new FootWear();
				foot.setCompany(footWear.getCompany());
				foot.setDescription(footWear.getDescription());
				foot.setIdealFor(footWear.getIdealFor());
				foot.setMaterial(footWear.getMaterial());
				foot.setProductId(footWear.getProductId());
				foot.setProductName(footWear.getProductName());
				foot.setWeight(footWear.getWeight());
				/** get the price for the product **/
				NewProductsEntity newProduct = (NewProductsEntity) session.get(
						NewProductsEntity.class, foot.getProductId());
				if (newProduct != null) {
					foot.setPrice(newProduct.getPrice());
				} else {
					foot.setPrice(0.0);
				}
				footwearList.add(foot);
			}
			session.getTransaction().commit();
			
		}
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getAllFootwearProducts", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}

		return footwearList;
	}

	@SuppressWarnings("rawtypes")
	private List<Integer> getProducts(int paymentId) throws Exception {
		Session session = sessionFactory.openSession();
		List<Integer> productIdList = new ArrayList<Integer>();
		try {
			session.beginTransaction();
			Query q = session
					.createSQLQuery("select productid from JCART_PAYMENTDETAILS where paymentid=?");
			q.setParameter(0, paymentId);
			List list= q.list();
			
			for (Object object : list) 
			{
				productIdList.add(Integer.parseInt(object.toString()));
			}
			session.getTransaction().commit();
			
		} 
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getProducts",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally {
		
			session.close(); 
		}
		return productIdList;
	}

	@SuppressWarnings("unchecked")
	public List<PurchaseHistory> getPaymentDetails(String userId)
			throws Exception {
		Session session = sessionFactory.openSession();
		List<PurchaseHistory> list = new ArrayList<PurchaseHistory>();
		try {
			session.beginTransaction();
			Query q = session
					.createQuery("from PaymentEntity k where k.buyerId=?");
			q.setParameter(0, userId);
			List<PaymentEntity> l = q.list();
			if (l.size() > 0 || l != null) {
				Iterator<PaymentEntity> it = l.iterator();
				while (it.hasNext()) {
					PaymentEntity p = it.next();
					PurchaseHistory history = new PurchaseHistory();
					history.setPaymentId(p.getPaymentId());
					List<Integer> ilist = getProducts(history.getPaymentId());
					history.setProductId(ilist);
					history.setDateOfPurchase(p.getPurchaseDate().getTime());
					history.setNetBill(p.getNetBill());
					list.add(history);
				}

			}
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getPaymentDetails",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally {
		
			session.close(); 
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Mobile> getAllMobileProducts(String subCategoryName)
			throws Exception {
		Session session = sessionFactory.openSession();
		List<Mobile> mobileList = null;
		try {
			mobileList = new ArrayList<Mobile>();
			session.beginTransaction();
			Query query = session
					.createQuery("select b from MobileEntity b where b.productId in (select p.productId from ProductEntity p where p.productType='N' and p.subCategory=(select c.subCategoryId from SubCategoryEntity c where c.subCategoryName=?))");
			query.setParameter(0, subCategoryName);
			List<MobileEntity> entityList = query.list();
			for (MobileEntity mobile : entityList) {
				Mobile mob = new Mobile();
				mob.setCompany(mobile.getCompany());
				mob.setProductName(mobile.getProductName());
				mob.setResolution(mobile.getResolution());
				mob.setScreenSize(mobile.getScreenSize());
				mob.setCamera(mobile.getCamera());
				mob.setBatteryType(mobile.getBatteryType());
				mob.setProductId(mobile.getProductId());
				/** get the price for the product **/
				NewProductsEntity newProduct = (NewProductsEntity) session.get(
						NewProductsEntity.class, mob.getProductId());
				if (newProduct != null) {
					mob.setPrice(newProduct.getPrice());
				} else {
					mob.setPrice(0.0);
				}
				mobileList.add(mob);
			}
			session.getTransaction().commit();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getAllMobileProducts", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}

		return mobileList;
	}

	@SuppressWarnings("unchecked")
	public List<Television> getAllTVProducts(String subCategoryName)
			throws Exception {
		Session session = sessionFactory.openSession();
		List<Television> tvList = null;
		try {
			tvList = new ArrayList<Television>();
			session.beginTransaction();
			Query query = session
					.createQuery("select b from TelevisionEntity b where b.productId in (select p.productId from ProductEntity p where p.productType='N' and p.subCategory=(select c.subCategoryId from SubCategoryEntity c where c.subCategoryName=?))");
			query.setParameter(0, subCategoryName);
			List<TelevisionEntity> entityList = query.list();
			for (TelevisionEntity tv : entityList) {
				Television television = new Television();
				television.setCompany(tv.getCompany());
				television.setDimension(tv.getDimension());
				television.setPixelResolution(tv.getPixelResolution());
				television.setProductId(tv.getProductId());
				television.setProductName(tv.getProductName());
				television.setScreenSize(tv.getScreenSize());
				television.setSpeakerType(tv.getSpeakerType());
				/** get the price for the product **/
				NewProductsEntity newProduct = (NewProductsEntity) session.get(
						NewProductsEntity.class, television.getProductId());
				if (newProduct != null) {
					television.setPrice(newProduct.getPrice());
				} else {
					television.setPrice(0.0);
				}
				tvList.add(television);
			}
			session.getTransaction().commit();
		}
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllTVProducts",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}

		return tvList;
	}

}
