package com.infy.jcart.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;








import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
import com.infy.jcart.beans.NewProducts;
import com.infy.jcart.beans.Product;
import com.infy.jcart.beans.ProductDetails;
import com.infy.jcart.beans.Television;
import com.infy.jcart.entites.BagsEntity;
import com.infy.jcart.entites.BooksEntity;
import com.infy.jcart.entites.CameraEntity;
import com.infy.jcart.entites.CategoryEntity;
import com.infy.jcart.entites.ClothingEntity;
import com.infy.jcart.entites.ComputersEntity;
import com.infy.jcart.entites.FootWearEntity;
import com.infy.jcart.entites.MobileEntity;
import com.infy.jcart.entites.NewProductsEntity;
import com.infy.jcart.entites.ProductEntity;
import com.infy.jcart.entites.SubCategoryEntity;
import com.infy.jcart.entites.TelevisionEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;

public class ProductDAOImpl implements ProductDAO {

	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

	/***
	 * @description this method persists the Product and according to the
	 *              category makes an entry to the corresponding table and
	 *              returns the auto generated product id
	 * @param Product
	 *            , Object[], NewProducts
	 * @return Integer
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public Integer addProduct(Product product, Object[] ob,
			NewProducts newProducts) throws Exception {
		Session session = sessionFactory.openSession();
		ProductEntity productEntity = new ProductEntity();
		try {
			// Persist Product

			productEntity.setCategoryId(product.getCategoryId());
			productEntity.setProductType(product.getProductType());
			productEntity.setSellerId(product.getSellerId());
			productEntity.setSubCategory(product.getSubCategory());
			session.beginTransaction();
			session.save(productEntity);
			session.getTransaction().commit();

			// Persist Sub Category
			switch (product.getCategoryId()) {
			case 108:
				addBag(productEntity.getProductId(), (Bags) ob[0]);
				break;
			case 101:
				addBook(productEntity.getProductId(), (Books) ob[1]);
				break;
			case 103:
				addCamera(productEntity.getProductId(), (Camera) ob[2]);
				break;
			case 106:
				addClothing(productEntity.getProductId(), (Clothing) ob[3]);
				break;
			case 102:
				addComputer(productEntity.getProductId(), (Computers) ob[4]);
				break;
			case 107:
				addFootWear(productEntity.getProductId(), (FootWear) ob[5]);
				break;
			case 105:
				addMobile(productEntity.getProductId(), (Mobile) ob[6]);
				break;
			case 104:
				addTelevision(productEntity.getProductId(), (Television) ob[7]);
				break;
			default:
				removeProductsOnException(productEntity.getProductId());
				break;
			}
			// Persist NewProduct or ProductBid
			addNewProduct(productEntity.getProductId(), newProducts);

		} catch (Exception exception) {
			
			JCartLogger.logError(this.getClass().getName(), "addProduct",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}

		return productEntity.getProductId();
	}

	/***
	 * @description this method populates a BagsEntity and persists it
	 * @param Integer
	 *            , Bags
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	private void addBag(Integer productId, Bags bags) throws Exception {
		Session session = sessionFactory.openSession();
		
		try {
			BagsEntity entity = new BagsEntity();
			entity.setCompany(bags.getCompany());
			entity.setDescription(bags.getDescription());
			entity.setIdealFor(bags.getIdealFor());
			entity.setMaterial(bags.getMaterial());
			entity.setProductId(productId);
			entity.setProductName(bags.getProductName());
			entity.setType(bags.getType());
			entity.setWeight(bags.getWeight());
			entity.setUrl("../page-resources/images/bags_default.png");
			session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "addBag",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}

	}

	/***
	 * @description this method populates a BooksEntity and persists it
	 * @param Integer
	 *            , Books
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	private void addBook(Integer productId, Books books) throws Exception {
		Session session = sessionFactory.openSession();
	
		try {
			BooksEntity entity = new BooksEntity();
			entity.setAuthor(books.getAuthor());
			entity.setDescription(books.getDescription());
			entity.setDimensions(books.getDimensions());
			entity.setLanguage(books.getLanguage());
			entity.setNumberOfPages(books.getNumberOfPages());
			entity.setProductId(productId);
			entity.setProductName(books.getProductName());
			entity.setPublishedYear(books.getPublishedYear());
			entity.setPublisher(books.getPublisher());
			entity.setTypeOfCover(books.getTypeOfCover());
			entity.setUrl("../page-resources/images/book_default.png");
			session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "addBook",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}

	}

	/***
	 * @description this method populates a CameraEntity and persists it
	 * @param Integer
	 *            , Camera
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	private void addCamera(Integer productId, Camera camera) throws Exception {
		Session session = sessionFactory.openSession();
		//session.beginTransaction();
		try {
			CameraEntity entity = new CameraEntity();
			entity.setBattery(camera.getBattery());
			entity.setCompany(camera.getCompany());
			entity.setDescription(camera.getDescription());
			entity.setDigitalZoom(camera.getDigitalZoom());
			entity.setFocalLength(camera.getFocalLength());
			entity.setMemory(camera.getMemory());
			entity.setOpticalZoom(camera.getOpticalZoom());
			entity.setPixels(camera.getPixels());
			entity.setProductId(productId);
			entity.setProductName(camera.getProductName());
			entity.setShutterSpeed(camera.getShutterSpeed());
			entity.setUrl("../page-resources/images/camera_default.png");
			session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "addCamera",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}

	}

	/***
	 * @description this method populates a ClothingEntity and persists it
	 * @param Integer
	 *            , Clothing
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	private void addClothing(Integer productId, Clothing clothing)
			throws Exception {
		Session session = sessionFactory.openSession();
		
		try {
			ClothingEntity entity = new ClothingEntity();
			entity.setCompany(clothing.getCompany());
			entity.setDescription(clothing.getDescription());
			entity.setFabric(clothing.getFabric());
			entity.setIdealFor(clothing.getIdealFor());
			entity.setProductId(productId);
			entity.setProductName(clothing.getProductName());
			entity.setUrl("../page-resources/images/clothing_default.png");
			session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "addClothing",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}

	}

	/***
	 * @description this method populates a ComputersEntity and persists it
	 * @param Integer
	 *            , Computers
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	private void addComputer(Integer productId, Computers computers)
			throws Exception {
		Session session = sessionFactory.openSession();
		
		try {

			ComputersEntity entity = new ComputersEntity();
			entity.setBatteryBackUp(computers.getBatteryBackUp());
			entity.setBatteryCells(computers.getBatteryCells());
			entity.setCompany(computers.getCompany());
			entity.setDescription(computers.getDescription());
			entity.setGraphicCard(computers.getGraphicCard());
			entity.setHardDiskSize(computers.getHardDiskSize());
			entity.setProcessorName(computers.getProcessorName());
			entity.setProcessorSpeed(computers.getProcessorSpeed());
			entity.setProductId(productId);
			entity.setProductName(computers.getProductName());
			entity.setRam(computers.getRam());
			entity.setScreenSize(computers.getScreenSize());
			entity.setUrl("../page-resources/images/computer_default.png");
			session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "addComputer",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}

	}

	/***
	 * @description this method populates a FootWearEntity and persists it
	 * @param Integer
	 *            , FootWear
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	private void addFootWear(Integer productId, FootWear wear) throws Exception {
		Session session = sessionFactory.openSession();
		
		try {
			FootWearEntity foot = new FootWearEntity();
			foot.setCompany(wear.getCompany());
			foot.setDescription(wear.getDescription());
			foot.setIdealFor(wear.getIdealFor());
			foot.setMaterial(wear.getMaterial());
			foot.setProductId(productId);
			foot.setProductName(wear.getProductName());
			foot.setWeight(wear.getWeight());
			foot.setUrl("../page-resources/images/footwear_default.png");
			session.beginTransaction();
			session.save(foot);
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "addFootWear",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}

	}

	/***
	 * @description this method populates a MobileEntity and persists it
	 * @param Integer
	 *            , Mobile
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	private void addMobile(Integer productId, Mobile mobile) throws Exception {
		Session session = sessionFactory.openSession();
		
		try {
			MobileEntity entity = new MobileEntity();
			entity.setBatteryType(mobile.getBatteryType());
			entity.setCamera(mobile.getCamera());
			entity.setCompany(mobile.getCompany());
			entity.setDescription(mobile.getDescription());
			entity.setGeneration(mobile.getGeneration());
			entity.setMemory(mobile.getMemory());
			entity.setProductId(productId);
			entity.setProductName(mobile.getProductName());
			entity.setResolution(mobile.getResolution());
			entity.setScreenSize(mobile.getScreenSize());
			entity.setSim(mobile.getSim());
			entity.setUrl("../page-resources/images/mobile_default.png");
			session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "addMobile",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}

	}

	/***
	 * @description this method populates a TelevisionEntity and persists it
	 * @param Integer
	 *            , Television
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	private void addTelevision(Integer productId, Television television)
			throws Exception {
		Session session = sessionFactory.openSession();
	
		try {
			TelevisionEntity entity = new TelevisionEntity();
			entity.setCompany(television.getCompany());
			entity.setDescription(television.getDescription());
			entity.setProductId(productId);
			entity.setProductName(television.getProductName());
			entity.setScreenSize(television.getScreenSize());
			entity.setDimension(television.getDimension());
			entity.setHdReady(television.getHdReady());
			entity.setPixelResolution(television.getPixelResolution());
			entity.setPowerConsumption(television.getPowerConsumption());
			entity.setSpeakerOutput(television.getSpeakerOutput());
			entity.setSpeakerType(television.getSpeakerType());
			entity.setUrl("../page-resources/images/television_default.png");
			session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "addTelevision",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}

	}

	/***
	 * @description this method populates a NewProductsEntity and persists it
	 * @param Integer
	 *            , NewProducts
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	private void addNewProduct(Integer productId, NewProducts products)
			throws Exception {
		Session session = sessionFactory.openSession();
		
		try {
			NewProductsEntity entity = new NewProductsEntity();
			entity.setDiscount(products.getDiscount());
			entity.setOfferCode(products.getOfferCode());
			entity.setPrice(products.getPrice());
			entity.setProductId(productId);
			entity.setProductAddDate(Calendar.getInstance());
			session.beginTransaction();
			session.save(entity);
			session.getTransaction().commit();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "addNewProduct",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}

	}

	/***
	 * @description this method finds all the new products for a particular sub
	 *              category
	 * @param Integer
	 * @return List<Product>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts(Integer subCategory) throws Exception {
		Session session = sessionFactory.openSession();
		List<Product> productList = new ArrayList<Product>();
		session.beginTransaction();
		try {
			Query query = session
					.createQuery("select p from ProductEntity p where p.subCategory=? and p.productType=?");
			query.setParameter(0, subCategory);
			query.setParameter(1, 'N');
			List<ProductEntity> list = query.list();
			for (ProductEntity obj : list) {
				Product to = new Product();
				to.setProductId(obj.getProductId());

				BagsEntity entity1 = (BagsEntity) session.get(BagsEntity.class,
						obj.getProductId());
				if (entity1 != null)
					to.setProductName("Bag - " + entity1.getProductName());

				BooksEntity entity2 = (BooksEntity) session.get(
						BooksEntity.class, obj.getProductId());
				if (entity2 != null)
					to.setProductName("Book - " + entity2.getProductName());

				CameraEntity entity3 = (CameraEntity) session.get(
						CameraEntity.class, obj.getProductId());
				if (entity3 != null)
					to.setProductName("Camera - " + entity3.getProductName());

				ClothingEntity entity4 = (ClothingEntity) session.get(
						ClothingEntity.class, obj.getProductId());
				if (entity4 != null)
					to.setProductName("Clothing - " + entity4.getProductName());

				ComputersEntity entity5 = (ComputersEntity) session.get(
						ComputersEntity.class, obj.getProductId());
				if (entity5 != null)
					to.setProductName("Computers - " + entity5.getProductName());

				FootWearEntity entity6 = (FootWearEntity) session.get(
						FootWearEntity.class, obj.getProductId());
				if (entity6 != null)
					to.setProductName("FootWear - " + entity6.getProductName());

				MobileEntity entity7 = (MobileEntity) session.get(
						MobileEntity.class, obj.getProductId());
				if (entity7 != null)
					to.setProductName("Mobile - " + entity7.getProductName());

				TelevisionEntity entity8 = (TelevisionEntity) session.get(
						TelevisionEntity.class, obj.getProductId());
				if (entity8 != null)
					to.setProductName("TeleVision - "
							+ entity8.getProductName());

				// Populate price
				NewProductsEntity newProduct = (NewProductsEntity) session.get(
						NewProductsEntity.class, obj.getProductId());
				if (newProduct != null) {
					to.setPrice(newProduct.getPrice());

				}
				productList.add(to);
			}
			session.getTransaction().commit();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllProducts",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {

			session.close(); 
		}
		return productList;
	}

	/***
	 * @description this method finds all the new products for a particular sub
	 *              category and for the logged in user
	 * @param Integer
	 * @return List<Product>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Product> getAllProductsByMe(Integer subCategory)
			throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Product> productList = new ArrayList<Product>();
		FacesContext ftx = FacesContext.getCurrentInstance();
		ExternalContext etx = ftx.getExternalContext();
		HttpSession httpSession = (HttpSession) etx.getSession(true);

		String userId = (String) httpSession.getAttribute("userId");
		try {
			Query query = session
					.createQuery("select p from ProductEntity p where p.subCategory=? and p.productType=? and p.sellerId=?");
			query.setParameter(0, subCategory);
			query.setParameter(1, 'N');
			query.setParameter(2, userId);
			List<ProductEntity> list = query.list();
			for (ProductEntity obj : list) {
				Product to = new Product();
				to.setProductId(obj.getProductId());

				BagsEntity entity1 = (BagsEntity) session.get(BagsEntity.class,
						obj.getProductId());
				if (entity1 != null)
					to.setProductName("Bag - " + entity1.getProductName());

				BooksEntity entity2 = (BooksEntity) session.get(
						BooksEntity.class, obj.getProductId());
				if (entity2 != null)
					to.setProductName("Book - " + entity2.getProductName());

				CameraEntity entity3 = (CameraEntity) session.get(
						CameraEntity.class, obj.getProductId());
				if (entity3 != null)
					to.setProductName("Camera - " + entity3.getProductName());

				ClothingEntity entity4 = (ClothingEntity) session.get(
						ClothingEntity.class, obj.getProductId());
				if (entity4 != null)
					to.setProductName("Clothing - " + entity4.getProductName());

				ComputersEntity entity5 = (ComputersEntity) session.get(
						ComputersEntity.class, obj.getProductId());
				if (entity5 != null)
					to.setProductName("Computers - " + entity5.getProductName());

				FootWearEntity entity6 = (FootWearEntity) session.get(
						FootWearEntity.class, obj.getProductId());
				if (entity6 != null)
					to.setProductName("FootWear - " + entity6.getProductName());

				MobileEntity entity7 = (MobileEntity) session.get(
						MobileEntity.class, obj.getProductId());
				if (entity7 != null)
					to.setProductName("Mobile - " + entity7.getProductName());

				TelevisionEntity entity8 = (TelevisionEntity) session.get(
						TelevisionEntity.class, obj.getProductId());
				if (entity8 != null)
					to.setProductName("TeleVision - "
							+ entity8.getProductName());

				// Populate price
				NewProductsEntity newProduct = (NewProductsEntity) session.get(
						NewProductsEntity.class, obj.getProductId());
				if (newProduct != null) {
					to.setPrice(newProduct.getPrice());

				}
				productList.add(to);

			}
			session.getTransaction().commit();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getAllProductsByMe", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}
		return productList;
	}

	/***
	 * @description this method finds the product according to the category and
	 *              removes it
	 * @param Integer
	 * @return void
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	public void removeProductsOnException(Integer productId) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			BagsEntity entity1 = (BagsEntity) session.get(BagsEntity.class,
					productId);
			if (entity1 != null)
				session.delete(entity1);

			BooksEntity entity2 = (BooksEntity) session.get(BooksEntity.class,
					productId);
			if (entity2 != null)
				session.delete(entity2);
			CameraEntity entity3 = (CameraEntity) session.get(
					CameraEntity.class, productId);
			if (entity3 != null)
				session.delete(entity3);
			ClothingEntity entity4 = (ClothingEntity) session.get(
					ClothingEntity.class, productId);
			if (entity4 != null)
				session.delete(entity4);
			ComputersEntity entity5 = (ComputersEntity) session.get(
					ComputersEntity.class, productId);
			if (entity5 != null)
				session.delete(entity5);
			FootWearEntity entity6 = (FootWearEntity) session.get(
					FootWearEntity.class, productId);
			if (entity6 != null)
				session.delete(entity6);
			MobileEntity entity7 = (MobileEntity) session.get(
					MobileEntity.class, productId);
			if (entity7 != null)
				session.delete(entity7);
			TelevisionEntity entity8 = (TelevisionEntity) session.get(
					TelevisionEntity.class, productId);
			if (entity8 != null)
				session.delete(entity8);
			
			session.getTransaction().commit();
			
		}
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"removeProductsOnException", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
	
			session.close(); 
		}

	}

	/***
	 * @description this method finds the new products for the particular sub category 
	 * 				and retailer, populates a list of ProductDetails and returns it
	 * @param Integer
	 *            , String
	 * @return List<ProductDetails>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<ProductDetails> getRetailerProducts(Integer subCategory,
			String retailerId) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<ProductDetails> toList = new ArrayList<ProductDetails>();
		try {
			Query query = session
					.createQuery("select p.productId,p.productType,p.sellerId,c.categoryName,s.subCategoryName from"
							+ " ProductEntity p,CategoryEntity c,SubCategoryEntity s where"
							+ " p.sellerId=? and p.subCategory=? and p.categoryId=c.categoryId and p.subCategory=s.subCategoryId and p.productType=?");
			query.setParameter(0, retailerId);
			query.setParameter(1, subCategory);
			query.setParameter(2, 'N');
			List list = query.list();
			for (Object obj : list) {
				Object[] obj1 = (Object[]) obj;
				ProductDetails to = new ProductDetails();
				to.setProductId((Integer) obj1[0]);
				to.setCategory((String) obj1[3]);
				to.setSubcategory((String) obj1[4]);

				// Populate New Product Details

				BagsEntity entity1 = (BagsEntity) session.get(BagsEntity.class,
						(Integer) obj1[0]);
				if (entity1 != null)
					to.setProductName(entity1.getProductName());

				BooksEntity entity2 = (BooksEntity) session.get(BooksEntity.class,
						(Integer) obj1[0]);
				if (entity2 != null)
					to.setProductName(entity2.getProductName());

				CameraEntity entity3 = (CameraEntity) session.get(CameraEntity.class,
						(Integer) obj1[0]);
				if (entity3 != null)
					to.setProductName(entity3.getProductName());

				ClothingEntity entity4 = (ClothingEntity) session.get(ClothingEntity.class,
						(Integer) obj1[0]);
				if (entity4 != null)
					to.setProductName(entity4.getProductName());

				ComputersEntity entity5 = (ComputersEntity) session.get(ComputersEntity.class,
						(Integer) obj1[0]);
				if (entity5 != null)
					to.setProductName(entity5.getProductName());

				FootWearEntity entity6 =(FootWearEntity) session.get(FootWearEntity.class,
						(Integer) obj1[0]);
				if (entity6 != null)
					to.setProductName(entity6.getProductName());

				MobileEntity entity7 = (MobileEntity) session.get(MobileEntity.class,
						(Integer) obj1[0]);
				if (entity7 != null)
					to.setProductName(entity7.getProductName());

				TelevisionEntity entity8 = (TelevisionEntity) session.get(TelevisionEntity.class,
						(Integer) obj1[0]);
				if (entity8 != null)
					to.setProductName(entity8.getProductName());

				NewProductsEntity newProduct = (NewProductsEntity) session.get(NewProductsEntity.class,
						(Integer) obj1[0]);
				if (newProduct != null) {
					to.setPrice(newProduct.getPrice());
				}

				toList.add(to);
			}
			session.getTransaction().commit();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getRetailerProducts",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}
		return toList;
	}

	/***
	 * @description this method finds the first 9 newly added products
	 * 				in the descending order of date of addition of products ,
	 * 				populates a list of ProductDetails and returns it
	 * 				class and returns the list obtained
	 * @return List<ProductDetails>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ProductDetails> getRecentlyAddedProducts() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<ProductDetails> toList = new ArrayList<ProductDetails>();
		try {
			Query query = session
					.createQuery("select s from NewProductsEntity s order by s.productAddDate desc");
			query.setFirstResult(0);
			query.setMaxResults(9);
			List<NewProductsEntity> list = query.list();
			for (NewProductsEntity obj : list) {
				ProductEntity product = (ProductEntity) session.get(ProductEntity.class, obj
						.getProductId());
				if (product != null) {

					ProductDetails to = new ProductDetails();

					BagsEntity entity1 = (BagsEntity) session.get(BagsEntity.class, obj
							.getProductId());
					if (entity1 != null) {
						to.setProductName(entity1.getProductName());
						to.setSubcategory("Bag");
						to.setCompany(entity1.getCompany());
						to.setUrl(entity1.getUrl());
					}
					BooksEntity entity2 = (BooksEntity) session.get(BooksEntity.class, obj
							.getProductId());
					if (entity2 != null) {
						to.setProductName(entity2.getProductName());
						to.setSubcategory("Book");
						to.setCompany(entity2.getAuthor());
						to.setUrl(entity2.getUrl());
					}
					CameraEntity entity3 = (CameraEntity) session.get(CameraEntity.class, obj
							.getProductId());
					if (entity3 != null) {
						to.setProductName(entity3.getProductName());
						to.setSubcategory("Camera");
						to.setCompany(entity3.getCompany());
						to.setUrl(entity3.getUrl());
					}
					ClothingEntity entity4 = (ClothingEntity) session.get(ClothingEntity.class, obj
							.getProductId());
					if (entity4 != null) {
						to.setProductName(entity4.getProductName());
						to.setSubcategory("Clothing");
						to.setCompany(entity4.getCompany());
						to.setUrl(entity4.getUrl());
					}
					ComputersEntity entity5 = (ComputersEntity) session.get(ComputersEntity.class,
							obj.getProductId());
					if (entity5 != null) {
						to.setProductName(entity5.getProductName());
						to.setSubcategory("Computer");
						to.setCompany(entity5.getCompany());
						to.setUrl(entity5.getUrl());
					}
					FootWearEntity entity6 = (FootWearEntity) session.get(FootWearEntity.class, obj
							.getProductId());
					if (entity6 != null) {
						to.setProductName(entity6.getProductName());
						to.setSubcategory("FootWear");
						to.setCompany(entity6.getCompany());
						to.setUrl(entity6.getUrl());
					}
					MobileEntity entity7 = (MobileEntity) session.get(MobileEntity.class, obj
							.getProductId());
					if (entity7 != null) {
						to.setProductName(entity7.getProductName());
						to.setSubcategory("Mobile");
						to.setCompany(entity7.getCompany());
						to.setUrl(entity7.getUrl());
					}
					TelevisionEntity entity8 = (TelevisionEntity) session.get(TelevisionEntity.class,
							obj.getProductId());
					if (entity8 != null) {
						to.setProductName(entity8.getProductName());
						to.setSubcategory("Television");
						to.setCompany(entity8.getCompany());
						to.setUrl(entity8.getUrl());
					}
					// Populate price
					to.setPrice(obj.getPrice());

					toList.add(to);

				}
			}
			session.getTransaction().commit();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getRecentlyAddedProducts",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}
		return toList;
	}

	/***
	 * @description this method finds the top 9 products that have been ordered
	 * 				maximum times, populates a list of  ProductDetails and returns it
	 * @return List<ProductDetails>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<ProductDetails> getMostPopularProducts() throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<ProductDetails> toList = new ArrayList<ProductDetails>();
		try {
			Query query = session
					.createSQLQuery("select q.productid from jcart_orderlogtable p,JCART_PAYMENTDETAILS q group by "
							+ "q.productid order by sum(p.NUMOFPRODUCTS) desc");

			query.setFirstResult(0);
			query.setMaxResults(9);
			List list = query.list();
			for (Object object : list) {
				
				BigDecimal bd = (BigDecimal) (object);
				Integer id = bd.intValue();
				ProductEntity product =(ProductEntity) session.get(ProductEntity.class, id);
				if (product != null) {

					ProductDetails to = new ProductDetails();
					
					BagsEntity entity1 = (BagsEntity) session.get(BagsEntity.class, id);
					if (entity1 != null) {
						to.setProductName(entity1.getProductName());
						to.setSubcategory("Bag");
						to.setCompany(entity1.getCompany());
						to.setUrl(entity1.getUrl());
					}
					BooksEntity entity2 = (BooksEntity) session.get(BooksEntity.class, id);
					if (entity2 != null) {
						to.setProductName(entity2.getProductName());
						to.setSubcategory("Book");
						to.setCompany(entity2.getAuthor());
						to.setUrl(entity2.getUrl());
					}
					CameraEntity entity3 = (CameraEntity) session.get(CameraEntity.class, id);
					if (entity3 != null) {
						to.setProductName(entity3.getProductName());
						to.setSubcategory("Camera");
						to.setCompany(entity3.getCompany());
						to.setUrl(entity3.getUrl());
					}
					ClothingEntity entity4 = (ClothingEntity) session.get(ClothingEntity.class, id);
					if (entity4 != null) {
						to.setProductName(entity4.getProductName());
						to.setSubcategory("Clothing");
						to.setCompany(entity4.getCompany());
						to.setUrl(entity4.getUrl());
					}
					ComputersEntity entity5 = (ComputersEntity) session.get(ComputersEntity.class, id);
					if (entity5 != null) {
						to.setProductName(entity5.getProductName());
						to.setSubcategory("Computer");
						to.setCompany(entity5.getCompany());
						to.setUrl(entity5.getUrl());
					}
					FootWearEntity entity6 = (FootWearEntity) session.get(FootWearEntity.class, id);
					if (entity6 != null) {
						to.setProductName(entity6.getProductName());
						to.setSubcategory("FootWear");
						to.setCompany(entity6.getCompany());
						to.setUrl(entity6.getUrl());
					}
					MobileEntity entity7 = (MobileEntity) session.get(MobileEntity.class, id);
					if (entity7 != null) {
						to.setProductName(entity7.getProductName());
						to.setSubcategory("Mobile");
						to.setCompany(entity7.getCompany());
						to.setUrl(entity7.getUrl());
					}
					TelevisionEntity entity8 = (TelevisionEntity) session.get(TelevisionEntity.class,
							id);
					if (entity8 != null) {
						to.setProductName(entity8.getProductName());
						to.setSubcategory("Television");
						to.setCompany(entity8.getCompany());
						to.setUrl(entity8.getUrl());
					}
					// Populate price
					NewProductsEntity newProduct = (NewProductsEntity) session.get(
							NewProductsEntity.class, id);
					if (newProduct != null) {
						to.setPrice(newProduct.getPrice());
						
					}

					toList.add(to);

				}
			}
			session.getTransaction().commit();
			
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getMostPopularProducts",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}
		return toList;
	}

	/***
	 * @description this method finds the new product details for a particular seller
	 * 				and category, populates a list of ProductDetails and returns it
	 * @param String, Integer
	 * @return List<ProductDetails>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ProductDetails> getProductsByRetailer(String retailerId,
			Integer categoryId) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<ProductDetails> toList = new ArrayList<ProductDetails>();
		try {
			Query query = session
					.createQuery("select p from ProductEntity p where p.sellerId=? and p.categoryId=? and p.productType=?");
			query.setParameter(0, retailerId);
			query.setParameter(1, categoryId);
			query.setParameter(2, 'N');
			List<ProductEntity> list = query.list();
			for (ProductEntity obj : list) {
				ProductDetails to = new ProductDetails();
				to.setProductId(obj.getProductId());

				SubCategoryEntity subCatergory = (SubCategoryEntity) session.get(
						SubCategoryEntity.class, obj.getSubCategory());
				if (subCatergory != null)
					to.setSubcategory(subCatergory.getSubCategoryName());

				CategoryEntity catergory = (CategoryEntity) session.get(CategoryEntity.class, obj
						.getCategoryId());
				if (catergory != null) {
					to.setCategory(catergory.getCategoryName());
				}

				BagsEntity entity1 = (BagsEntity) session.get(BagsEntity.class, obj
						.getProductId());
				if (entity1 != null) {
					to.setCompany(entity1.getCompany());
					to.setProductName(entity1.getProductName());
					to.setUrl(entity1.getUrl());
				}
				BooksEntity entity2 = (BooksEntity) session.get(BooksEntity.class, obj
						.getProductId());
				if (entity2 != null) {
					to.setCompany(entity2.getAuthor());
					to.setProductName(entity2.getProductName());
					to.setUrl(entity2.getUrl());
				}
				CameraEntity entity3 = (CameraEntity) session.get(CameraEntity.class, obj
						.getProductId());
				if (entity3 != null) {
					to.setCompany(entity3.getCompany());
					to.setProductName(entity3.getProductName());
					to.setUrl(entity3.getUrl());
				}
				ClothingEntity entity4 = (ClothingEntity) session.get(ClothingEntity.class, obj
						.getProductId());
				if (entity4 != null) {
					to.setCompany(entity4.getCompany());
					to.setProductName(entity4.getProductName());
					to.setUrl(entity4.getUrl());
				}
				ComputersEntity entity5 = (ComputersEntity) session.get(ComputersEntity.class, obj
						.getProductId());
				if (entity5 != null) {
					to.setCompany(entity5.getCompany());
					to.setProductName(entity5.getProductName());
					to.setUrl(entity5.getUrl());
				}
				FootWearEntity entity6 = (FootWearEntity) session.get(FootWearEntity.class, obj
						.getProductId());
				if (entity6 != null) {
					to.setCompany(entity6.getCompany());
					to.setProductName(entity6.getProductName());
					to.setUrl(entity6.getUrl());
				}
				MobileEntity entity7 = (MobileEntity) session.get(MobileEntity.class, obj
						.getProductId());
				if (entity7 != null) {
					to.setCompany(entity7.getCompany());
					to.setProductName(entity7.getProductName());
					to.setUrl(entity7.getUrl());
				}
				TelevisionEntity entity8 = (TelevisionEntity) session.get(TelevisionEntity.class, obj
						.getProductId());
				if (entity8 != null) {
					to.setCompany(entity8.getCompany());
					to.setProductName(entity8.getProductName());
					to.setUrl(entity8.getUrl());
				}

				// Populate price
				NewProductsEntity newProduct = (NewProductsEntity) session.get(NewProductsEntity.class,
						obj.getProductId());
				if (newProduct != null) {
					to.setPrice(newProduct.getPrice());
				}
				toList.add(to);
			}
			session.getTransaction().commit();
			
		}
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "submitFeedback",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}
		return toList;
	}

	/***
	 * @description this method finds the new product details for a particular seller
	 * 				populates a list of ProductDetails and returns it
	 * @param String
	 * @return List<ProductDetails>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<ProductDetails> getRetailerProducts(String retailerId)
			throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<ProductDetails> toList = new ArrayList<ProductDetails>();
		try {
			Query query = session
					.createQuery("select p.productId,p.productType,p.sellerId,c.categoryName,s.subCategoryName from"
							+ " ProductEntity p,CategoryEntity c,SubCategoryEntity s where"
							+ " p.sellerId=? and p.categoryId=c.categoryId and p.subCategory=s.subCategoryId and p.productType=?");
			query.setParameter(0, retailerId);
			query.setParameter(1, 'N');
			List list = query.list();
			for (Object obj : list) {
				Object[] obj1 = (Object[]) obj;
				ProductDetails to = new ProductDetails();
				to.setProductId((Integer) obj1[0]);
				to.setCategory((String) obj1[3]);
				to.setSubcategory((String) obj1[4]);

				// Populate New Product Details

				BagsEntity entity1 = (BagsEntity) session.get(BagsEntity.class,
						(Integer) obj1[0]);
				if (entity1 != null)
					to.setProductName(entity1.getProductName());

				BooksEntity entity2 = (BooksEntity) session.get(BooksEntity.class,
						(Integer) obj1[0]);
				if (entity2 != null)
					to.setProductName(entity2.getProductName());

				CameraEntity entity3 = (CameraEntity) session.get(CameraEntity.class,
						(Integer) obj1[0]);
				if (entity3 != null)
					to.setProductName(entity3.getProductName());

				ClothingEntity entity4 = (ClothingEntity) session.get(ClothingEntity.class,
						(Integer) obj1[0]);
				if (entity4 != null)
					to.setProductName(entity4.getProductName());

				ComputersEntity entity5 = (ComputersEntity) session.get(ComputersEntity.class,
						(Integer) obj1[0]);
				if (entity5 != null)
					to.setProductName(entity5.getProductName());

				FootWearEntity entity6 = (FootWearEntity) session.get(FootWearEntity.class,
						(Integer) obj1[0]);
				if (entity6 != null)
					to.setProductName(entity6.getProductName());

				MobileEntity entity7 = (MobileEntity) session.get(MobileEntity.class,
						(Integer) obj1[0]);
				if (entity7 != null)
					to.setProductName(entity7.getProductName());

				TelevisionEntity entity8 = (TelevisionEntity) session.get(TelevisionEntity.class,
						(Integer) obj1[0]);
				if (entity8 != null)
					to.setProductName(entity8.getProductName());

				NewProductsEntity newProduct = (NewProductsEntity) session.get(NewProductsEntity.class,
						(Integer) obj1[0]);
				if (newProduct != null) {
					to.setPrice(newProduct.getPrice());
				}
				toList.add(to);
			}
			session.getTransaction().commit();
			
		}
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getRetailerProducts",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}
		return toList;
	}

}
