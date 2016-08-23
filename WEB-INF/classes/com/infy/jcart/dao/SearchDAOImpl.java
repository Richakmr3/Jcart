package com.infy.jcart.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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

public class SearchDAOImpl implements SearchDAO {

	private SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

	/***
	 * @description this method searches the products according to search string
	 *              in the selected category entity ie wherever that search
	 *              string is present for the product record, product is
	 *              searched and populated to the map with column name as key
	 *              and list of products falling under that category as value
	 *              and returns the map
	 * @param String
	 *            , String
	 * @return List
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("rawtypes")
	private Map<String, List> getSearchElementsInCategory(String searchString,
			String selectedCategory) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Map<String, List> map = new LinkedHashMap<String, List>();
		try {
			Query query = null;
			if (!selectedCategory.equals("O")) {

				query = session.createQuery("select k from " + selectedCategory
						+ "Entity k where k.productName like  '%"
						+ searchString + "%' ");
				List list1 = query.list();
				List<String> className = new ArrayList<String>();
				className.add(selectedCategory + "Entity");
				map.put("className", className);
				map.put("productName", list1);

				if (selectedCategory.equals("Books")) {
					query = session.createQuery("select k from "
							+ selectedCategory
							+ "Entity k where k.publisher like '%"
							+ searchString + "%' ");
				} else {
					query = session.createQuery("select k from "
							+ selectedCategory
							+ "Entity k where k.company like '%" + searchString
							+ "%' ");
				}

				List list2 = query.list();
				map.put("company", list2);

				query = session.createQuery("select k from " + selectedCategory
						+ "Entity k , ProductEntity k1 where "
						+ " k1.productId=k.productId and k1.sellerId like  '%"
						+ searchString + "%' ");
				List list3 = query.list();
				map.put("sellerId", list3);

			}
			session.getTransaction().commit();
			
		}
		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getSearchElementsInCategory", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}

		return map;
	}

	/***
	 * @description this method searches the products according to search string
	 *              ie wherever that search string is present for the product
	 *              record, product is searched and populated to the map with
	 *              column name as key and list of products falling under that
	 *              category as value and returns the map
	 * @param String
	 * @return Map<String, List>
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("rawtypes")
	private Map<String, List> getSearchElementsBySearchString(
			String searchString) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Map<String, List> map = new LinkedHashMap<String, List>();
		try {

			Query query = null;

			List list1 = null;
			List list2 = null;
			List list3 = null;

			String nameOfAllEntities[] = { "BagsEntity", "BooksEntity",
					"CameraEntity", "ClothingEntity", "ComputersEntity",
					"FootWearEntity", "MobileEntity", "TelevisionEntity" };

			for (int i = 0; i < nameOfAllEntities.length; i++) {
				query = session.createQuery("select k from "
						+ nameOfAllEntities[i]
						+ " k where k.productName like  '%" + searchString
						+ "%' ");

				list1 = query.list();
				map.put("ProductName:" + nameOfAllEntities[i], list1);
			}

			for (int i = 0; i < nameOfAllEntities.length; i++) {

				if (nameOfAllEntities[i].equals("BooksEntity")) {
					query = session.createQuery("select k from "
							+ nameOfAllEntities[i]
							+ " k where k.publisher like '%" + searchString
							+ "%' ");
				} else {
					query = session.createQuery("select k from "
							+ nameOfAllEntities[i]
							+ " k where k.company like '%" + searchString
							+ "%' ");
				}
				list2 = query.list();
				map.put("CompanyName:" + nameOfAllEntities[i], list2);
			}

			for (int i = 0; i < nameOfAllEntities.length; i++) {
				query = session.createQuery("select k from "
						+ nameOfAllEntities[i] + " k , ProductEntity k1 where "
						+ " k1.productId=k.productId and k1.sellerId like  '%"
						+ searchString + "%' ");
				list3 = query.list();
				map.put("SellerId:" + nameOfAllEntities[i], list3);
			}
			session.getTransaction().commit();

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getSearchElementsBySearchString", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}

		return map;
	}

	/***
	 * @description this method invokes getSearchElementsInCategory() method of
	 *              SearchService , iterates over the returned map, and
	 *              populates a the list to be returned according to the
	 *              selected category
	 * @param String
	 *            , String
	 * @return List
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings("rawtypes")
	public List getSearchElementsByCategory(String searchString,
			String selectedCategory) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Map<String, List> map = getSearchElementsInCategory(searchString,
					selectedCategory);
			if (map.size() == 0) {
				return null;
			}
			Set entrySet = map.entrySet();

			Iterator iterator = entrySet.iterator();
			List<Bags> listBagTOs = new ArrayList<Bags>();
			List<Books> listBookTOs = new ArrayList<Books>();
			List<Camera> listCameraTOs = new ArrayList<Camera>();
			List<Clothing> listClothingTOs = new ArrayList<Clothing>();
			List<Computers> listComputerTOs = new ArrayList<Computers>();
			List<FootWear> listFootwareTOs = new ArrayList<FootWear>();
			List<Mobile> listMobileTOs = new ArrayList<Mobile>();
			List<Television> listTelevisonTOs = new ArrayList<Television>();
			while (iterator.hasNext()) {
				Entry entry = (Entry) iterator.next();
				List list = (List) entry.getValue();
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

				if (o instanceof BooksEntity) {

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

				if (o instanceof CameraEntity) {

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

				if (o instanceof ClothingEntity) {
					for (int i = 0; i < list.size(); i++) {
						ClothingEntity clothingEntity = (ClothingEntity) list
								.get(i);
						Clothing clothingTO = new Clothing();
						clothingTO.setCompany(clothingEntity.getCompany());
						clothingTO.setDescription(clothingEntity
								.getDescription());
						clothingTO.setFabric(clothingEntity.getFabric());
						clothingTO.setIdealFor(clothingEntity.getIdealFor());
						clothingTO.setProductId(clothingEntity.getProductId());
						clothingTO.setProductName(clothingEntity
								.getProductName());
						listClothingTOs.add(clothingTO);
					}
				}

				if (o instanceof ComputersEntity) {

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
						computersTO.setDescription(computersEntity
								.getDescription());
						computersTO.setGraphicCard(computersEntity
								.getGraphicCard());
						computersTO.setHardDiskSize(computersEntity
								.getHardDiskSize());
						computersTO.setProcessorName(computersEntity
								.getProcessorName());
						computersTO.setProcessorSpeed(computersEntity
								.getProcessorSpeed());
						computersTO
								.setProductId(computersEntity.getProductId());
						computersTO.setProductName(computersEntity
								.getProductName());
						computersTO.setRam(computersEntity.getRam());
						computersTO.setScreenSize(computersEntity
								.getScreenSize());

						listComputerTOs.add(computersTO);

					}
				}
				if (o instanceof FootWearEntity) {
					for (int i = 0; i < list.size(); i++) {
						FootWearEntity footWearEntity = (FootWearEntity) list
								.get(i);
						FootWear footWearTO = new FootWear();
						footWearTO.setCompany(footWearEntity.getCompany());
						footWearTO.setDescription(footWearEntity
								.getDescription());
						footWearTO.setIdealFor(footWearEntity.getIdealFor());
						footWearTO.setMaterial(footWearEntity.getMaterial());
						footWearTO.setProductId(footWearEntity.getProductId());
						footWearTO.setProductName(footWearEntity
								.getProductName());
						footWearTO.setWeight(footWearEntity.getWeight());
						listFootwareTOs.add(footWearTO);
					}
				}
				if (o instanceof MobileEntity) {

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
				}
				if (o instanceof TelevisionEntity) {

					for (int i = 0; i < list.size(); i++) {
						TelevisionEntity televisionEntity = (TelevisionEntity) list
								.get(i);
						Television televisionTO = new Television();
						televisionTO.setCompany(televisionEntity.getCompany());
						televisionTO.setDescription(televisionEntity
								.getDescription());
						televisionTO.setDimension(televisionEntity
								.getDimension());
						televisionTO.setHdReady(televisionEntity.getHdReady());
						televisionTO.setPixelResolution(televisionEntity
								.getPixelResolution());
						televisionTO.setPowerConsumption(televisionEntity
								.getPowerConsumption());
						televisionTO.setProductName(televisionEntity
								.getProductName());
						televisionTO.setScreenSize(televisionEntity
								.getScreenSize());
						televisionTO.setSpeakerOutput(televisionEntity
								.getSpeakerOutput());
						televisionTO.setSpeakerType(televisionEntity
								.getSpeakerType());
						televisionTO.setProductId(televisionEntity
								.getProductId());
						listTelevisonTOs.add(televisionTO);
					}

				}

			}

			if (listBagTOs.size() > 0) {
				return listBagTOs;
			}

			if (listBookTOs.size() > 0) {
				return listBookTOs;
			}

			if (listCameraTOs.size() > 0) {
				return listCameraTOs;
			}

			if (listClothingTOs.size() > 0) {
				return listClothingTOs;
			}

			if (listTelevisonTOs.size() > 0) {
				return listTelevisonTOs;
			}

			if (listMobileTOs.size() > 0) {
				return listMobileTOs;
			}

			if (listFootwareTOs.size() > 0) {
				return listFootwareTOs;
			}

			session.getTransaction().commit();
			return listComputerTOs;

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getSearchElementsByCategory", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
		
			session.close(); 
		}
	}

	/***
	 * @description this method invokes getSearchElementsBySearchString() ,
	 *              iterates over the map obtained, for each category entity it
	 *              populates a list and add this list to the final list to be
	 *              returned and returns the list
	 * @param String
	 * @return List
	 * @throws DAO.TECHNICAL_ERROR
	 * @catch Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getSearchElementsInAllCategories(String searchString)
			throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Map<String, List> map = getSearchElementsBySearchString(searchString);
			if (map.size() == 0) {
				return null;
			}
			Set entrySet = map.entrySet();

			Iterator iterator = entrySet.iterator();

			List<Bags> listBagTOs = new ArrayList<Bags>();
			List<Books> listBookTOs = new ArrayList<Books>();
			List<Camera> listCameraTOs = new ArrayList<Camera>();
			List<Clothing> listClothingTOs = new ArrayList<Clothing>();
			List<Computers> listComputerTOs = new ArrayList<Computers>();
			List<FootWear> listFootwareTOs = new ArrayList<FootWear>();
			List<Mobile> listMobileTOs = new ArrayList<Mobile>();
			List<Television> listTelevisonTOs = new ArrayList<Television>();

			List returnedList = new ArrayList();

			while (iterator.hasNext()) {
				Entry entry = (Entry) iterator.next();
				List list = (List) entry.getValue();
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

				if (o instanceof BooksEntity) {

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

				if (o instanceof CameraEntity) {

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

				if (o instanceof ClothingEntity) {
					for (int i = 0; i < list.size(); i++) {
						ClothingEntity clothingEntity = (ClothingEntity) list
								.get(i);
						Clothing clothingTO = new Clothing();
						clothingTO.setCompany(clothingEntity.getCompany());
						clothingTO.setDescription(clothingEntity
								.getDescription());
						clothingTO.setFabric(clothingEntity.getFabric());
						clothingTO.setIdealFor(clothingEntity.getIdealFor());
						clothingTO.setProductId(clothingEntity.getProductId());
						clothingTO.setProductName(clothingEntity
								.getProductName());
						listClothingTOs.add(clothingTO);
					}
				}

				if (o instanceof ComputersEntity) {

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
						computersTO.setDescription(computersEntity
								.getDescription());
						computersTO.setGraphicCard(computersEntity
								.getGraphicCard());
						computersTO.setHardDiskSize(computersEntity
								.getHardDiskSize());
						computersTO.setProcessorName(computersEntity
								.getProcessorName());
						computersTO.setProcessorSpeed(computersEntity
								.getProcessorSpeed());
						computersTO
								.setProductId(computersEntity.getProductId());
						computersTO.setProductName(computersEntity
								.getProductName());
						computersTO.setRam(computersEntity.getRam());
						computersTO.setScreenSize(computersEntity
								.getScreenSize());

						listComputerTOs.add(computersTO);

					}
				}
				if (o instanceof FootWearEntity) {
					for (int i = 0; i < list.size(); i++) {
						FootWearEntity footWearEntity = (FootWearEntity) list
								.get(i);
						FootWear footWearTO = new FootWear();
						footWearTO.setCompany(footWearEntity.getCompany());
						footWearTO.setDescription(footWearEntity
								.getDescription());
						footWearTO.setIdealFor(footWearEntity.getIdealFor());
						footWearTO.setMaterial(footWearEntity.getMaterial());
						footWearTO.setProductId(footWearEntity.getProductId());
						footWearTO.setProductName(footWearEntity
								.getProductName());
						footWearTO.setWeight(footWearEntity.getWeight());
						listFootwareTOs.add(footWearTO);
					}
				}
				if (o instanceof MobileEntity) {

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
				}
				if (o instanceof TelevisionEntity) {

					for (int i = 0; i < list.size(); i++) {
						TelevisionEntity televisionEntity = (TelevisionEntity) list
								.get(i);
						Television televisionTO = new Television();
						televisionTO.setCompany(televisionEntity.getCompany());
						televisionTO.setDescription(televisionEntity
								.getDescription());
						televisionTO.setDimension(televisionEntity
								.getDimension());
						televisionTO.setHdReady(televisionEntity.getHdReady());
						televisionTO.setPixelResolution(televisionEntity
								.getPixelResolution());
						televisionTO.setPowerConsumption(televisionEntity
								.getPowerConsumption());
						televisionTO.setProductName(televisionEntity
								.getProductName());
						televisionTO.setScreenSize(televisionEntity
								.getScreenSize());
						televisionTO.setSpeakerOutput(televisionEntity
								.getSpeakerOutput());
						televisionTO.setSpeakerType(televisionEntity
								.getSpeakerType());
						televisionTO.setProductId(televisionEntity
								.getProductId());
						listTelevisonTOs.add(televisionTO);
					}

				}

			}

			if (listBagTOs.size() > 0) {
				returnedList.add(listBagTOs);
			}

			if (listBookTOs.size() > 0) {
				returnedList.add(listBookTOs);
			}

			if (listCameraTOs.size() > 0) {
				returnedList.add(listCameraTOs);
			}

			if (listClothingTOs.size() > 0) {
				returnedList.add(listClothingTOs);
			}

			if (listTelevisonTOs.size() > 0) {
				returnedList.add(listTelevisonTOs);
			}

			if (listMobileTOs.size() > 0) {
				returnedList.add(listMobileTOs);
			}

			if (listFootwareTOs.size() > 0) {
				returnedList.add(listFootwareTOs);
			}

			if (listComputerTOs.size() > 0) {
				returnedList.add(listComputerTOs);

			}
			session.getTransaction().commit();
			return returnedList;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "submitFeedback",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			
			session.close(); 
		}
	}

}
