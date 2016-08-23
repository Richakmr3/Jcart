package com.infy.jcart.web.managedbeans;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;


import com.infy.jcart.beans.Category;
import com.infy.jcart.beans.ProductBid;
import com.infy.jcart.beans.ProductDetails;
import com.infy.jcart.beans.Product;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@SessionScoped
@SuppressWarnings("unused")
public class BiddingBean {
	private Integer productId;
	private Double basePrice;
	private Integer incrementValue;
	private Character bidStatus;
	private Integer duration;
	private List<SelectItem> productList = new ArrayList<SelectItem>();
	private List<ProductDetails> usedProductList;
	private List<SelectItem> categoryList = new ArrayList<SelectItem>();
	private List<SelectItem> subCategoryList = new ArrayList<SelectItem>();
	private String errorMsg;
	private String successMsg;
	private String userId;
	private HttpSession session;
	private FacesContext ftx;
	private ExternalContext etx;
	private boolean selectEdit;
	private boolean selectUpdate;
	private HtmlDataTable selectData;
	private HtmlDataTable selectUsedProductData;
	private boolean sellProduct;
	private ProductBid productBid;
	private boolean makeBid;
	private Integer categoryId;
	private Integer subCategoryId;
	private String imageUrl;
	private boolean productAdded;

	public boolean isMakeBid() {
		return makeBid;
	}

	public boolean isProductAdded() {
		return productAdded;
	}

	public void setProductAdded(boolean productAdded) {
		this.productAdded = productAdded;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public List<SelectItem> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<SelectItem> categoryList) {
		this.categoryList = categoryList;
	}

	public List<SelectItem> getSubCategoryList() {
		return subCategoryList;
	}

	public void setSubCategoryList(List<SelectItem> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}

	public void setMakeBid(boolean makeBid) {
		this.makeBid = makeBid;
	}

	public HtmlDataTable getSelectUsedProductData() {
		return selectUsedProductData;
	}

	public void setSelectUsedProductData(HtmlDataTable selectUsedProductData) {
		this.selectUsedProductData = selectUsedProductData;
	}

	public List<ProductDetails> getUsedProductList() {
		return usedProductList;
	}

	public void setUsedProductList(List<ProductDetails> usedProductList) {
		this.usedProductList = usedProductList;
	}

	public ProductBid getProductBid() {
		return productBid;
	}

	public void setProductBid(ProductBid productBid) {
		this.productBid = productBid;
	}

	public boolean isSellProduct() {
		return sellProduct;
	}

	public void setSellProduct(boolean sellProduct) {
		this.sellProduct = sellProduct;
	}

	public HtmlDataTable getSelectData() {
		return selectData;
	}

	public void setSelectData(HtmlDataTable selectData) {
		this.selectData = selectData;
	}

	public boolean isSelectEdit() {
		return selectEdit;
	}

	public void setSelectEdit(boolean selectEdit) {
		this.selectEdit = selectEdit;
	}

	public boolean isSelectUpdate() {
		return selectUpdate;
	}

	public void setSelectUpdate(boolean selectUpdate) {
		this.selectUpdate = selectUpdate;
	}

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getSuccessMsg() {
		return successMsg;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	public List<SelectItem> getProductList() {
		return productList;
	}

	public void setProductList(List<SelectItem> productList) {
		this.productList = productList;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public Integer getIncrementValue() {
		return incrementValue;
	}

	public void setIncrementValue(Integer incrementValue) {
		this.incrementValue = incrementValue;
	}

	public Character getBidStatus() {
		return bidStatus;
	}

	public void setBidStatus(Character bidStatus) {
		this.bidStatus = bidStatus;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/****
	 * @description This method is used to initiate the bean 
	 * class variables with values to
	 * be displayed when the page gets loaded
	 * 
	 * @exception Exception
	 */
	public BiddingBean() 
	{
		try {
			this.productAdded = false;
			this.makeBid = true;
			this.sellProduct = false;
			this.selectEdit = true;
			this.selectUpdate = false;
			this.errorMsg = "";
			
			
			ftx = FacesContext.getCurrentInstance();
			etx = ftx.getExternalContext();
			session = (HttpSession) etx.getSession(true);
			/** set the user id from the session **/
			this.userId = session.getAttribute("userId").toString();
			
			List<Product> list1 = new ArrayList<Product>();
			this.productList.clear();
			list1.clear();
			
			list1=Factory.createBidService().getProductsList(this.userId);
			

			List<Integer> biddedProducts =Factory.createBidService().getBiddedProductsList(); 
					
			/**
			 * populate the product list variable with the retrieved product
			 * id's
			 **/
			for (Product product : list1) {
				this.productList.add(new SelectItem(product.getProductId()));
			}

			/** populate the used product list available for bidding **/
			this.usedProductList = new ArrayList<ProductDetails>();
			this.usedProductList.clear();
			this.usedProductList =  Factory.createBidService().viewBiddingProducts();

			/** populate the category list **/
			List<Category> list2 =  Factory.createBidService().getAllCategoryList();
			this.categoryList.clear();
			for (Category c : list2) {
				this.categoryList.add(new SelectItem(c.getCategoryId(), c
						.getCategoryName()));
			}

		} catch (Exception exception) {
			
			try {
				this.errorMsg="";
				this.usedProductList = new ArrayList<ProductDetails>();
				this.usedProductList.clear();
				this.usedProductList =  Factory.createBidService().viewBiddingProducts();

				/** populate the category list **/
				this.categoryList = new ArrayList<SelectItem>();
				List<Category> list2 =  Factory.createBidService().getAllCategoryList();
				this.categoryList.clear();
				for (Category c : list2) {
					this.categoryList.add(new SelectItem(c.getCategoryId(), c
							.getCategoryName()));
				}
			} catch (Exception exception2) {
				
				JCartLogger.logError(this.getClass().getName(), "BiddingBean", exception2
						.toString());
				this.errorMsg = JCartConfig.getMessageFromProperties(exception2.getMessage());
			}
			JCartLogger.logError(this.getClass().getName(), "BiddingBean", exception
					.toString());
			this.errorMsg = JCartConfig.getMessageFromProperties(exception.getMessage());
		}
	}

	

	

	
	/**
	 * methods used for the implementation of purchasing used products via
	 * bidding
	 **/
	/****
	 * @description This method is used to start bidding for a product
	 * 
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String makeBidForProduct() {
		String path="";
		try {
			this.errorMsg = "";
			this.successMsg = "";
			/** active the make bid status **/
			this.makeBid = false;
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"makeBidForProduct", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
			path="/pages/errorPage";}
		}
		return path;

	}

	/****
	 * @description This method is used to save the bidding details like bid amount entered
	 * by the bidder
	 * 
	 * @exception Exception
	 * @return String
	 */
	public String saveBid() {
		String path="";
		
		try {
			/** change the status of the makeBid variable **/
			this.makeBid = true;
			this.errorMsg = "";
			ProductDetails productDetails = (ProductDetails) this.selectUsedProductData
					.getRowData();
			productDetails.setBidderId(this.userId);
			/**
			 * invoke the BidService class method named saveBid by passing the
			 * ProductDetails object
			 **/
			Factory.createBidService().saveBid(productDetails);
			this.successMsg = JCartConfig.getMessageFromProperties("BiddingBean.SUCCESS_MESSAGE_SAVE_BID");	
			
			/** refresh used product list available for bidding with new values **/
			this.usedProductList.clear();
			this.usedProductList =Factory.createBidService().viewBiddingProducts();
		} catch (Exception exception) {
			
					JCartLogger.logError(this.getClass().getName(), "saveBid",
							exception.toString());
					if (exception.getMessage().contains("TECHNICAL_ERROR")) {
						path = "/pages/errorPage";
					}
					this.errorMsg = JCartConfig.getMessageFromProperties(exception.getMessage());
			
		}
		return path;
		}
		

	


	/****
	 *@description This method is used to add used products for bidding
	 * 
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String addUsedProductsForBidding() {
		
	String path="";
		try {
			this.productAdded = true;
			Product product = new Product();
			product.setCategoryId(this.categoryId);
			product.setSellerId(this.userId);
			product.setSubCategory(this.subCategoryId);
			/**
			 * invoke the manager class method to save the details and save the
			 * image with the new product id
			 **/
			this.uploadProductImage(Factory.createBidService().addUsedProductsForBidding(product));
			List<Product> list1 = new ArrayList<Product>();
			this.productList.clear();
			list1.clear();
			list1 = Factory.createBidService().getProductsList(this.userId);

			List<Integer> biddedProducts = Factory.createBidService().getBiddedProductsList();
			/**
			 * populate the product list variable with the retrieved product
			 * id's
			 **/
			for (Product product1 : list1) {

				this.productList.add(new SelectItem(product1.getProductId()));
			}
	
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(),
					"addUsedProductsForBidding", e.toString());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR")){
			path="/pages/errorPage";}
			
		}
		return path;
	}

	/****
	 * 
	 * @description This method is used to upload product image.
	 * 
	 * @param productId
	 * @return void
	 * @exception Exception
	 */

	public void uploadProductImage(Integer productId) {
		File file1 = null;
		File file2 = null;
		String path="";
		try {
			String s = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap().get("productImage");
			file1 = new File(s);
			int mid = s.lastIndexOf(".");
			String ext = s.substring(mid + 1, s.length());
			file2 = new File(JCartConfig.getPath("imageUrlPath") + productId
					+ "." + ext);
			InputStream in = new FileInputStream(file1);
			OutputStream out = new FileOutputStream(file2);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"uploadProductImage", exception.toString());
			if (exception.getMessage().contains("TECHNICAL_ERROR")) {
				path = "/pages/errorPage";
			
			
		} 
		}
	}
}

