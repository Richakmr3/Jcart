package com.infy.jcart.dao;

import java.util.ArrayList;
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
import com.infy.jcart.beans.Television;
import com.infy.jcart.entites.BagsEntity;
import com.infy.jcart.entites.BooksEntity;
import com.infy.jcart.entites.CameraEntity;
import com.infy.jcart.entites.ClothingEntity;
import com.infy.jcart.entites.ComputersEntity;
import com.infy.jcart.entites.FootWearEntity;
import com.infy.jcart.entites.MobileEntity;
import com.infy.jcart.entites.TelevisionEntity;
import com.infy.jcart.resources.HibernateUtility;
import com.infy.jcart.resources.JCartLogger;

public class HomeDAOImpl implements HomeDAO {

	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

	/***
	 * @description this method returns the list of subcategory names for a
	 *              particular category id
	 * @param Integer
	 * @return List<String>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("unchecked")
	public List<String> getSubCategoryNameList(Integer categoryId)
			throws Exception 
			{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<String> subCategoryNameList = new ArrayList<String>();
		try 
		{
			Query q = session
					.createQuery("SELECT sc.subCategoryName FROM SubCategoryEntity sc WHERE sc.categoryId=?");
			q.setParameter(0, categoryId);
			subCategoryNameList = q.list();
			session.getTransaction().commit();
		} catch (Exception exception) 
		{
			JCartLogger.logError(this.getClass().getName(),
					"getSubCategoryNameList", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 
		finally 
		{
			session.close();
		}
		return subCategoryNameList;
	}

	/***
	 * @description this method returns all the products for a particular
	 *              category
	 * @param String
	 * @return List
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("rawtypes")
	private List getAllElementsOfCategory(String categoryName) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Query q = session.createQuery("Select k from " + categoryName
					+ "Entity k");
			session.getTransaction().commit();
			return q.list();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getSubCategoryNameList", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close();
			 		}
	}

	/**
	 * @description this method checks the type of category and accordingly
	 * 				populates the list of products to be returned
	 * @param String
	 * @return List
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("rawtypes")
	public List getElementsByCategory(String categoryName) throws Exception 
	{

		try {
			List<Bags> listBagTOs = new ArrayList<Bags>();
			List<Books> listBookTOs = new ArrayList<Books>();
			List<Camera> listCameraTOs = new ArrayList<Camera>();
			List<Clothing> listClothingTOs = new ArrayList<Clothing>();
			List<Computers> listComputerTOs = new ArrayList<Computers>();
			List<FootWear> listFootwareTOs = new ArrayList<FootWear>();
			List<Mobile> listMobileTOs = new ArrayList<Mobile>();
			List<Television> listTelevisonTOs = new ArrayList<Television>();

			List list = getAllElementsOfCategory(categoryName);

			Object o = null;
			if (list.size() > 0) {
				o = list.get(0);
			}
			if (o instanceof BagsEntity) {

				for (int i = 0; i < list.size(); i++) {
					BagsEntity bagsEntity = (BagsEntity) list.get(i);
					Bags bagsTO = new Bags();
					bagsTO.setCompany(bagsEntity.getCompany());
					bagsTO.setDescription(bagsEntity.getDescription());
					bagsTO.setIdealFor(bagsEntity.getIdealFor());
					bagsTO.setMaterial(bagsEntity.getMaterial());
					bagsTO.setProductId(bagsEntity.getProductId());
					bagsTO.setProductName(bagsEntity.getProductName());
					bagsTO.setType(bagsEntity.getType());
					bagsTO.setWeight(bagsEntity.getWeight());
					listBagTOs.add(bagsTO);

				}

			}

			else if (o instanceof BooksEntity) {

				for (int i = 0; i < list.size(); i++) {
					BooksEntity booksEntity = (BooksEntity) list.get(i);
					Books booksTO = new Books();
					booksTO.setAuthor(booksEntity.getAuthor());
					booksTO.setDescription(booksEntity.getDescription());
					booksTO.setDimensions(booksEntity.getDimensions());
					booksTO.setLanguage(booksEntity.getLanguage());
					booksTO.setNumberOfPages(booksEntity.getNumberOfPages());
					booksTO.setProductId(booksEntity.getProductId());
					booksTO.setProductName(booksEntity.getProductName());
					booksTO.setPublishedYear(booksEntity.getPublishedYear());
					booksTO.setPublisher(booksEntity.getPublisher());
					booksTO.setTypeOfCover(booksEntity.getTypeOfCover());
					listBookTOs.add(booksTO);
				}
			}

			else if (o instanceof CameraEntity) {

				for (int i = 0; i < list.size(); i++) {
					CameraEntity cameraEntity = (CameraEntity) list.get(i);
					Camera cameraTO = new Camera();
					cameraTO.setBattery(cameraEntity.getBattery());
					cameraTO.setCompany(cameraEntity.getCompany());
					cameraTO.setDescription(cameraEntity.getDescription());
					cameraTO.setDigitalZoom(cameraEntity.getDigitalZoom());
					cameraTO.setFocalLength(cameraEntity.getFocalLength());
					cameraTO.setMemory(cameraEntity.getMemory());
					cameraTO.setOpticalZoom(cameraEntity.getOpticalZoom());
					cameraTO.setPixels(cameraEntity.getPixels());
					cameraTO.setProductId(cameraEntity.getProductId());
					cameraTO.setProductName(cameraEntity.getProductName());
					cameraTO.setShutterSpeed(cameraEntity.getShutterSpeed());
					listCameraTOs.add(cameraTO);
				}

			}

			else if (o instanceof ClothingEntity) {
				for (int i = 0; i < list.size(); i++) {
					ClothingEntity clothingEntity = (ClothingEntity) list
							.get(i);
					Clothing clothingTO = new Clothing();
					clothingTO.setCompany(clothingEntity.getCompany());
					clothingTO.setDescription(clothingEntity.getDescription());
					clothingTO.setFabric(clothingEntity.getFabric());
					clothingTO.setIdealFor(clothingEntity.getIdealFor());
					clothingTO.setProductId(clothingEntity.getProductId());
					clothingTO.setProductName(clothingEntity.getProductName());
					listClothingTOs.add(clothingTO);
				}
			}

			else if (o instanceof ComputersEntity) {

				for (int i = 0; i < list.size(); i++) {
					ComputersEntity computersEntity = (ComputersEntity) list
							.get(i);
					Computers computersTO = new Computers();
					computersTO.setBatteryBackUp(computersEntity
							.getBatteryBackUp());
					computersTO.setBatteryCells(computersEntity
							.getBatteryCells());
					computersTO.setCompany(computersEntity.getCompany());
					computersTO.setBatteryBackUp(computersEntity
							.getBatteryBackUp());
					computersTO
							.setDescription(computersEntity.getDescription());
					computersTO
							.setGraphicCard(computersEntity.getGraphicCard());
					computersTO.setHardDiskSize(computersEntity
							.getHardDiskSize());
					computersTO.setProcessorName(computersEntity
							.getProcessorName());
					computersTO.setProcessorSpeed(computersEntity
							.getProcessorSpeed());
					computersTO.setProductId(computersEntity.getProductId());
					computersTO
							.setProductName(computersEntity.getProductName());
					computersTO.setRam(computersEntity.getRam());
					computersTO.setScreenSize(computersEntity.getScreenSize());

					listComputerTOs.add(computersTO);

				}
			} else if (o instanceof FootWearEntity) {
				for (int i = 0; i < list.size(); i++) {
					FootWearEntity footWearEntity = (FootWearEntity) list
							.get(i);
					FootWear footWearTO = new FootWear();
					footWearTO.setCompany(footWearEntity.getCompany());
					footWearTO.setDescription(footWearEntity.getDescription());
					footWearTO.setIdealFor(footWearEntity.getIdealFor());
					footWearTO.setMaterial(footWearEntity.getMaterial());
					footWearTO.setProductId(footWearEntity.getProductId());
					footWearTO.setProductName(footWearEntity.getProductName());
					footWearTO.setWeight(footWearEntity.getWeight());
					
					if(footWearEntity.getIdealFor()=='M'){
						footWearTO.setIdealImageCheck(true);
					}
					else if (footWearEntity.getIdealFor()=='F') {
						footWearTO.setIdealImageCheck(false);
					}
					
					listFootwareTOs.add(footWearTO);
				}
			} else if (o instanceof MobileEntity) {

				for (int i = 0; i < list.size(); i++) {
					MobileEntity mobileEntity = (MobileEntity) list.get(i);
					Mobile mobileTO = new Mobile();
					mobileTO.setBatteryType(mobileEntity.getBatteryType());
					mobileTO.setCamera(mobileEntity.getCamera());
					mobileTO.setCompany(mobileEntity.getCompany());
					mobileTO.setDescription(mobileEntity.getDescription());
					mobileTO.setGeneration(mobileEntity.getGeneration());
					mobileTO.setMemory(mobileEntity.getMemory());
					mobileTO.setProductId(mobileEntity.getProductId());
					mobileTO.setProductName(mobileEntity.getProductName());
					mobileTO.setResolution(mobileEntity.getResolution());
					mobileTO.setScreenSize(mobileEntity.getScreenSize());
					mobileTO.setSim(mobileEntity.getSim());
					listMobileTOs.add(mobileTO);
				}
			} else if (o instanceof TelevisionEntity) {

				for (int i = 0; i < list.size(); i++) {
					TelevisionEntity televisionEntity = (TelevisionEntity) list
							.get(i);
					Television televisionTO = new Television();
					televisionTO.setCompany(televisionEntity.getCompany());
					televisionTO.setDescription(televisionEntity
							.getDescription());
					televisionTO.setDimension(televisionEntity.getDimension());
					televisionTO.setHdReady(televisionEntity.getHdReady());
					televisionTO.setPixelResolution(televisionEntity
							.getPixelResolution());
					televisionTO.setPowerConsumption(televisionEntity
							.getPowerConsumption());
					televisionTO.setProductName(televisionEntity
							.getProductName());
					televisionTO
							.setScreenSize(televisionEntity.getScreenSize());
					televisionTO.setSpeakerOutput(televisionEntity
							.getSpeakerOutput());
					televisionTO.setSpeakerType(televisionEntity
							.getSpeakerType());
					televisionTO.setProductId(televisionEntity.getProductId());
					listTelevisonTOs.add(televisionTO);
				}

			}

			if (listBagTOs.size() > 0) {
				return listBagTOs;
			}

			else if (listBookTOs.size() > 0) {
				return listBookTOs;
			}

			else if (listCameraTOs.size() > 0) {
				return listCameraTOs;
			}

			else if (listClothingTOs.size() > 0) {
				return listClothingTOs;
			}

			else if (listTelevisonTOs.size() > 0) {
				return listTelevisonTOs;
			}

			else if (listMobileTOs.size() > 0) {
				return listMobileTOs;
			}

			else if (listFootwareTOs.size() > 0) 
			{
				return listFootwareTOs;
			} 
			else 
			{
				return listComputerTOs;
			}
		} 
		catch (Exception exception) 
		{
			JCartLogger.logError(this.getClass().getName(),
					"getElementsByCategory", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
	}
	
	
}
