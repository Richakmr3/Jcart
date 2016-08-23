package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.infy.jcart.beans.Bags;
import com.infy.jcart.beans.Books;
import com.infy.jcart.beans.Camera;
import com.infy.jcart.beans.Clothing;
import com.infy.jcart.beans.Computers;
import com.infy.jcart.beans.FootWear;
import com.infy.jcart.beans.Mobile;
import com.infy.jcart.beans.Television;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@SessionScoped
@SuppressWarnings("unchecked")
public class SearchBean {
	String path = null;
	private String category;
	private String message;
	private String searchString = "Enter the Product to Search";

	private List<Bags> listBags;
	private List<Books> listBooks;
	private List<Camera> listCamera;
	private List<Clothing> listClothing;
	private List<Computers> listComputers;
	private List<FootWear> listFootware;
	private List<Mobile> listMobile;
	private List<Television> listTelevison;

	private List<Bags> sellistBags;
	private List<Books> sellistBooks;
	private List<Camera> sellistCamera;
	private List<Clothing> sellistClothing;
	private List<Computers> sellistComputers;
	private List<FootWear> sellistFootware;
	private List<Mobile> sellistMobile;
	private List<Television> sellistTelevison;

	private Boolean renderedButton = false;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public List<Bags> getListBags() {
		return listBags;
	}

	public void setListBags(List<Bags> listBags) {
		this.listBags = listBags;
	}

	public List<Books> getListBooks() {
		return listBooks;
	}

	public void setListBooks(List<Books> listBooks) {
		this.listBooks = listBooks;
	}

	public List<Camera> getListCamera() {
		return listCamera;
	}

	public void setListCamera(List<Camera> listCamera) {
		this.listCamera = listCamera;
	}

	public List<Clothing> getListClothing() {
		return listClothing;
	}

	public void setListClothing(List<Clothing> listClothing) {
		this.listClothing = listClothing;
	}

	public List<Computers> getListComputers() {
		return listComputers;
	}

	public void setListComputers(List<Computers> listComputers) {
		this.listComputers = listComputers;
	}

	public List<FootWear> getListFootware() {
		return listFootware;
	}

	public void setListFootware(List<FootWear> listFootware) {
		this.listFootware = listFootware;
	}

	public List<Mobile> getListMobile() {
		return listMobile;
	}

	public void setListMobile(List<Mobile> listMobile) {
		this.listMobile = listMobile;
	}

	public List<Television> getListTelevison() {
		return listTelevison;
	}

	public void setListTelevison(List<Television> listTelevison) {
		this.listTelevison = listTelevison;
	}

	public List<Bags> getSellistBags() {
		return sellistBags;
	}

	public void setSellistBags(List<Bags> sellistBags) {
		this.sellistBags = sellistBags;
	}

	public List<Books> getSellistBooks() {
		return sellistBooks;
	}

	public void setSellistBooks(List<Books> sellistBooks) {
		this.sellistBooks = sellistBooks;
	}

	public List<Camera> getSellistCamera() {
		return sellistCamera;
	}

	public void setSellistCamera(List<Camera> sellistCamera) {
		this.sellistCamera = sellistCamera;
	}

	public List<Clothing> getSellistClothing() {
		return sellistClothing;
	}

	public void setSellistClothing(List<Clothing> sellistClothing) {
		this.sellistClothing = sellistClothing;
	}

	public List<Computers> getSellistComputers() {
		return sellistComputers;
	}

	public void setSellistComputers(List<Computers> sellistComputers) {
		this.sellistComputers = sellistComputers;
	}

	public List<FootWear> getSellistFootware() {
		return sellistFootware;
	}

	public void setSellistFootware(List<FootWear> sellistFootware) {
		this.sellistFootware = sellistFootware;
	}

	public List<Mobile> getSellistMobile() {
		return sellistMobile;
	}

	public void setSellistMobile(List<Mobile> sellistMobile) {
		this.sellistMobile = sellistMobile;
	}

	public List<Television> getSellistTelevison() {
		return sellistTelevison;
	}

	public void setSellistTelevison(List<Television> sellistTelevison) {
		this.sellistTelevison = sellistTelevison;
	}

	public Boolean getRenderedButton() {
		return renderedButton;
	}

	public void setRenderedButton(Boolean renderedButton) {
		this.renderedButton = renderedButton;
	}
	/***
	 * @description This method is used to search product items by category
	 * 
	 * @return String
	 * @exception NoSearchDetailsfoundException
	 *                , Exception
	 */
	@SuppressWarnings("rawtypes")
	public String getSearchelements() {

		this.message = null;
		listBags = new ArrayList<Bags>();
		listBooks = new ArrayList<Books>();
		listCamera = new ArrayList<Camera>();
		listClothing = new ArrayList<Clothing>();
		listComputers = new ArrayList<Computers>();
		listFootware = new ArrayList<FootWear>();
		listMobile = new ArrayList<Mobile>();
		listTelevison = new ArrayList<Television>();

		try {
			if (this.category.equals("O")) {
				List<List> listOfLists = Factory.createSearchService()
						.getSearchElementsByAllCategory(searchString);
				renderedButton = false;

				for (int i = 0; i < listOfLists.size(); i++) {
					List testList = listOfLists.get(i);

					Object o = testList.get(0);

					if (o instanceof Bags) {
						this.listBags = testList;
					}

					if (o instanceof Books) {
						this.listBooks = testList;
					}

					if (o instanceof Camera) {
						this.listCamera = testList;
					}

					if (o instanceof Clothing) {
						this.listClothing = testList;
					}

					if (o instanceof Computers) {
						this.listComputers = testList;
					}

					if (o instanceof FootWear) {
						this.listFootware = testList;
					}

					if (o instanceof Mobile) {
						this.listMobile = testList;
					}

					if (o instanceof Television) {
						this.listTelevison = testList;
					}

				}
			} else {

				renderedButton = true;
				List list = Factory.createSearchService()
						.getSearchElementsByCategory(searchString, category);
				Object o = list.get(0);

				if (o instanceof Bags) {
					this.listBags = list;
				}

				if (o instanceof Books) {
					this.listBooks = list;
				}

				if (o instanceof Camera) {
					this.listCamera = list;
				}

				if (o instanceof Clothing) {
					this.listClothing = list;
				}

				if (o instanceof Computers) {
					this.listComputers = list;
				}

				if (o instanceof FootWear) {
					this.listFootware = list;
				}

				if (o instanceof Mobile) {
					this.listMobile = list;
				}

				if (o instanceof Television) {
					this.listTelevison = list;
				}
			}
			return "";
		} catch (Exception exception) {
			path = "";
			JCartLogger.logError(this.getClass().getName(),
					"getSearchelements", exception.toString());
			if (exception.getMessage().contains("TECHNICAL_ERROR")) {
				path = "/pages/errorPage";
			}
			this.message = JCartConfig.getMessageFromProperties(exception.getMessage());
			return path;
		}

	}

	/***
	 * @description This method is used to check comparision between the
	 *              selected product details
	 * @return String
	 * @exception Exception
	 *
	 */
	public String compareto() {

		path = "/pages/compareResult";
		this.message = null;
		sellistBags = new ArrayList<Bags>();
		sellistBooks = new ArrayList<Books>();
		sellistCamera = new ArrayList<Camera>();
		sellistClothing = new ArrayList<Clothing>();
		sellistComputers = new ArrayList<Computers>();
		sellistFootware = new ArrayList<FootWear>();
		sellistMobile = new ArrayList<Mobile>();
		sellistTelevison = new ArrayList<Television>();

		try {

			if (category.equals("Books")) {
				int flag = 0;
				List<Books> list = new ArrayList<Books>();
				for (int i = 0; i < this.listBooks.size(); i++) {
					if (listBooks.get(i).getSelected() == true) {
						listBooks.get(i).setSelected(false);
						list.add(listBooks.get(i));
						flag++;
						// logic to un check the checked check box
						Books t = listBooks.get(i);
						t.setSelected(null);
						listBooks.remove(i);
						listBooks.add(i, t);
					}

				}
				this.sellistBooks = list;
				if (flag == 0) {

					this.message = JCartConfig
							.getMessageFromProperties("SearchBean.SELECT_BOOK");
					return "";
				}

			}

			if (category.equals("Bags")) {
				int flag = 0;
				List<Bags> list = new ArrayList<Bags>();
				for (int i = 0; i < this.listBags.size(); i++) {

					if (listBags.get(i).getSelected() == true) {
						listBags.get(i).setSelected(false);
						list.add(listBags.get(i));
						flag++;
						// logic to un check the checked check box
						Bags t = listBags.get(i);
						t.setSelected(null);
						listBags.remove(i);
						listBags.add(i, t);
					}

				}
				this.sellistBags = list;
				if (flag == 0) {

					this.message = JCartConfig
							.getMessageFromProperties("SearchBean.SELECT_BAG");

					path = "";
				}
			}

			if (category.equals("Camera")) {
				int flag = 0;
				List<Camera> list = new ArrayList<Camera>();
				for (int i = 0; i < this.listCamera.size(); i++) {
					if (listCamera.get(i).getSelected() == true) {
						listCamera.get(i).setSelected(false);
						list.add(listCamera.get(i));
						flag++;
						// logic to un check the checked check box
						Camera t = listCamera.get(i);
						t.setSelected(null);
						listCamera.remove(i);
						listCamera.add(i, t);
					}

				}
				this.sellistCamera = list;
				if (flag == 0) {

					this.message = JCartConfig
							.getMessageFromProperties("SearchBean.SELECT_CAMERA");

					path = "";
				}
			}

			if (category.equals("Clothing")) {
				int flag = 0;
				List<Clothing> list = new ArrayList<Clothing>();
				for (int i = 0; i < this.listClothing.size(); i++) {
					if (listClothing.get(i).getSelected() == true) {
						listClothing.get(i).setSelected(false);
						list.add(listClothing.get(i));
						flag++;
						// logic to un check the checked check box
						Clothing t = listClothing.get(i);
						t.setSelected(null);
						listClothing.remove(i);
						listClothing.add(i, t);
					}

				}
				this.sellistClothing = list;
				if (flag == 0) {

					this.message = JCartConfig
							.getMessageFromProperties("SearchBean.SELECT_CLOTHING");

					path = "";
				}
			}

			if (category.equals("Computers")) {
				int flag = 0;
				List<Computers> list = new ArrayList<Computers>();
				for (int i = 0; i < this.listComputers.size(); i++) {
					if (listComputers.get(i).getSelected() == true) {
						listComputers.get(i).setSelected(false);
						list.add(listComputers.get(i));
						flag++;
						// logic to un check the checked check box
						Computers t = listComputers.get(i);
						t.setSelected(null);
						listComputers.remove(i);
						listComputers.add(i, t);
					}

				}
				this.sellistComputers = list;
				if (flag == 0) {

					this.message = JCartConfig
							.getMessageFromProperties("SearchBean.SELECT_COMPUTER");

					path = "";
				}
			}

			if (category.equals("FootWear")) {
				int flag = 0;
				List<FootWear> list = new ArrayList<FootWear>();
				for (int i = 0; i < this.listFootware.size(); i++) {
					if (listFootware.get(i).getSelected() == true) {
						listFootware.get(i).setSelected(false);
						list.add(listFootware.get(i));
						flag++;
						// logic to un check the checked check box
						FootWear t = listFootware.get(i);
						t.setSelected(null);
						listFootware.remove(i);
						listFootware.add(i, t);
					}

				}
				this.sellistFootware = list;
				if (flag == 0) {

					this.message = JCartConfig
							.getMessageFromProperties("SearchBean.SELECT_FOOTWEAR");

					path = "";
				}
			}

			if (category.equals("Mobile")) {
				int flag = 0;
				List<Mobile> list = new ArrayList<Mobile>();
				for (int i = 0; i < this.listMobile.size(); i++) {
					if (listMobile.get(i).getSelected() == true) {
						listMobile.get(i).setSelected(false);
						list.add(listMobile.get(i));
						flag++;
						// logic to un check the checked check box
						Mobile t = listMobile.get(i);
						t.setSelected(null);
						listMobile.remove(i);
						listMobile.add(i, t);
					}

				}
				this.sellistMobile = list;
				if (flag == 0) {

					this.message = JCartConfig
							.getMessageFromProperties("SearchBean.SELECT_MOBILE");
					path = "";
				}
			}

			if (category.equals("Television")) {
				int flag = 0;
				List<Television> list = new ArrayList<Television>();
				for (int i = 0; i < this.listTelevison.size(); i++) {
					if (listTelevison.get(i).getSelected() == true) {
						listTelevison.get(i).setSelected(false);
						list.add(listTelevison.get(i));
						flag++;
						// logic to un check the checked check box
						Television t = listTelevison.get(i);
						t.setSelected(null);
						listTelevison.remove(i);
						listTelevison.add(i, t);
					}

				}
				this.sellistTelevison = list;
				if (flag == 0) {

					this.message = JCartConfig
							.getMessageFromProperties("SearchBean.SELECT_TELEVISION");
					path = "";
				}
			}

			return path;
		}

		catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "compareto",
					exception.toString());
			path = "/pages/errorPage";
			return path;
		}
	}

	/****
	 * @description This method is used to populate the details of products
	 *              under the given category
	 * 
	 * @return String
	 * @exception Exception
	 */
	@SuppressWarnings("rawtypes")
	public String getCategoryList() {
		path = "/pages/SearchPage";
		try {

			
			this.message = null;
			listBags = new ArrayList<Bags>();
			listBooks = new ArrayList<Books>();
			listCamera = new ArrayList<Camera>();
			listClothing = new ArrayList<Clothing>();
			listComputers = new ArrayList<Computers>();
			listFootware = new ArrayList<FootWear>();
			listMobile = new ArrayList<Mobile>();
			listTelevison = new ArrayList<Television>();

			this.getSessionCategory();
			
		 this.resetListInBeanSession();
			
			//this.category="Books";
			
			List categoryList = Factory.createHomeDAO().getElementsByCategory(this.category);

			if (this.category.equals("Computers")) {

				this.listComputers = (List<Computers>) categoryList;				
				
			}
			if (this.category.equals("Camera")) {

				this.listCamera = (List<Camera>) categoryList;
				

			}
			if (this.category.equals("Television")) {

				this.listTelevison = (List<Television>) categoryList;
				

			}
			if (this.category.equals("Mobile")) {

				this.listMobile = (List<Mobile>) categoryList;
				

			}
			if (this.category.equals("Clothing")) {

				this.listClothing = (List<Clothing>) categoryList;
				

			}
			if (this.category.equals("FootWear")) {

				this.listFootware = (List<FootWear>) categoryList;
			

			}
			if (this.category.equals("Books")) {					
				
				
				this.setListBooks((List<Books>)categoryList);			
				

			}
			if (this.category.equals("Bags")) {

				this.listBags = (List<Bags>) categoryList;
				

			}

			return path;
		} catch (Exception exception) {
			
			JCartLogger.logError(this.getClass().getName(), "compareto",
					exception.toString());
			path = "/pages/errorPage";
			return path;
		}

	}

	/***
	 * @description This method is used to retrieve the given category as search
	 *              item
	 * 
	 * @return void
	 */
	public void getSessionCategory() {

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		this.category = (String) session.getAttribute("category");
		// as of now by default showing Computers products in mid pane when
		// category not selected.has to be modified to show best products etc
		// when Category==null
		if (category == null) {

			this.category = "Computers";
		}

	}

	/***
	 * This method is used to refresh the list containing populated details
	 * 
	 * @return SearchBean
	 */
	public SearchBean resetListInBeanSession() {
		SearchBean bean = (SearchBean) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("searchBean");
		bean.setListBags(new ArrayList<Bags>());
		bean.setListBooks(new ArrayList<Books>());
		bean.setListCamera(new ArrayList<Camera>());
		bean.setListClothing(new ArrayList<Clothing>());
		bean.setListComputers(new ArrayList<Computers>());
		bean.setListFootware(new ArrayList<FootWear>());
		bean.setListMobile(new ArrayList<Mobile>());
		bean.setListTelevison(new ArrayList<Television>());
		return bean;
	}

	

}
