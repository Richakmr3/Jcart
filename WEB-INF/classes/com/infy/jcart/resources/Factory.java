package com.infy.jcart.resources;

import com.infy.jcart.business.service.AccountService;
import com.infy.jcart.business.service.AccountServiceImpl;
import com.infy.jcart.business.service.ApproveRetailerService;
import com.infy.jcart.business.service.ApproveRetailerServiceImpl;
import com.infy.jcart.business.service.ApproveReviewCommentsService;
import com.infy.jcart.business.service.ApproveReviewCommentsServiceImpl;
import com.infy.jcart.business.service.BidService;
import com.infy.jcart.business.service.BidServiceImpl;
import com.infy.jcart.business.service.CartService;
import com.infy.jcart.business.service.CartServiceImpl;
import com.infy.jcart.business.service.CategoryService;
import com.infy.jcart.business.service.CategoryServiceImpl;
import com.infy.jcart.business.service.CustomerExperienceReportService;
import com.infy.jcart.business.service.CustomerExperienceReportServiceImpl;
import com.infy.jcart.business.service.EditProfileService;
import com.infy.jcart.business.service.EditProfileServiceImpl;
import com.infy.jcart.business.service.IssueService;
import com.infy.jcart.business.service.IssueServiceImpl;
import com.infy.jcart.business.service.LoginService;
import com.infy.jcart.business.service.LoginServiceImpl;
import com.infy.jcart.business.service.MessageService;
import com.infy.jcart.business.service.MessageServiceImpl;
import com.infy.jcart.business.service.OfferService;
import com.infy.jcart.business.service.OfferServiceImpl;
import com.infy.jcart.business.service.ProductService;
import com.infy.jcart.business.service.ProductServiceImpl;
import com.infy.jcart.business.service.PurchaseService;
import com.infy.jcart.business.service.PurchaseServiceImpl;
import com.infy.jcart.business.service.RaiseIssueService;
import com.infy.jcart.business.service.RaiseIssueServiceImpl;
import com.infy.jcart.business.service.RatingService;
import com.infy.jcart.business.service.RatingServiceImpl;
import com.infy.jcart.business.service.RegistrationService;
import com.infy.jcart.business.service.RegistrationServiceImpl;
import com.infy.jcart.business.service.ReportService;
import com.infy.jcart.business.service.ReportServiceImpl;
import com.infy.jcart.business.service.RequestProductService;
import com.infy.jcart.business.service.RequestProductServiceImpl;
import com.infy.jcart.business.service.RetailerService;
import com.infy.jcart.business.service.RetailerServiceImpl;
import com.infy.jcart.business.service.SearchService;
import com.infy.jcart.business.service.SearchServiceImpl;
import com.infy.jcart.business.service.ShipmentService;
import com.infy.jcart.business.service.ShipmentServiceImpl;
import com.infy.jcart.business.service.UpdateService;
import com.infy.jcart.business.service.UpdateServiceImpl;
import com.infy.jcart.business.service.VariantService;
import com.infy.jcart.business.service.VariantServiceImpl;
import com.infy.jcart.business.service.ViewAuctionsService;
import com.infy.jcart.business.service.ViewAuctionsServiceImpl;
import com.infy.jcart.business.service.ViewMyOrdersService;
import com.infy.jcart.business.service.ViewMyOrdersServiceImpl;
import com.infy.jcart.business.service.WishListService;
import com.infy.jcart.business.service.WishListServiceImpl;
import com.infy.jcart.dao.AccountDAO;
import com.infy.jcart.dao.AccountDAOImpl;
import com.infy.jcart.dao.ApproveRetailerDAO;
import com.infy.jcart.dao.ApproveRetailerDAOImpl;
import com.infy.jcart.dao.ApproveReviewCommentsDAO;
import com.infy.jcart.dao.ApproveReviewCommentsDAOImpl;
import com.infy.jcart.dao.BidDAO;
import com.infy.jcart.dao.BidDAOImpl;
import com.infy.jcart.dao.CartDAO;
import com.infy.jcart.dao.CartDAOImpl;
import com.infy.jcart.dao.CategoryDAO;
import com.infy.jcart.dao.CategoryDAOImpl;
import com.infy.jcart.dao.CustomerExperienceReportDAO;
import com.infy.jcart.dao.CustomerExperienceReportDAOImpl;
import com.infy.jcart.dao.EditProfileDAO;
import com.infy.jcart.dao.EditProfileDAOImpl;
import com.infy.jcart.dao.FeedBackDAO;
import com.infy.jcart.dao.FeedBackDAOImpl;
import com.infy.jcart.dao.HomeDAO;
import com.infy.jcart.dao.HomeDAOImpl;
import com.infy.jcart.dao.IssueDAO;
import com.infy.jcart.dao.IssueDAOImpl;
import com.infy.jcart.dao.LoginDAO;
import com.infy.jcart.dao.LoginDAOImpl;
import com.infy.jcart.dao.MessageDAO;
import com.infy.jcart.dao.MessageDAOImpl;
import com.infy.jcart.dao.OfferDAO;
import com.infy.jcart.dao.OfferDAOImpl;
import com.infy.jcart.dao.ProductDAO;
import com.infy.jcart.dao.ProductDAOImpl;
import com.infy.jcart.dao.PurchaseDAO;
import com.infy.jcart.dao.PurchaseDAOImpl;
import com.infy.jcart.dao.RaiseIssueDAO;
import com.infy.jcart.dao.RaiseIssueDAOImpl;
import com.infy.jcart.dao.RatingDAO;
import com.infy.jcart.dao.RatingDAOImpl;
import com.infy.jcart.dao.RegistrationDAO;
import com.infy.jcart.dao.RegistrationDAOImpl;
import com.infy.jcart.dao.ReportDAO;
import com.infy.jcart.dao.ReportDAOImpl;
import com.infy.jcart.dao.RequestProductDAO;
import com.infy.jcart.dao.RequestProductDAOImpl;
import com.infy.jcart.dao.RetailerDAO;
import com.infy.jcart.dao.RetailerDAOImpl;
import com.infy.jcart.dao.SearchDAO;
import com.infy.jcart.dao.SearchDAOImpl;
import com.infy.jcart.dao.ShipmentDAO;
import com.infy.jcart.dao.ShipmentDAOImpl;
import com.infy.jcart.dao.UpdateDAO;
import com.infy.jcart.dao.UpdateDAOImpl;
import com.infy.jcart.dao.VariantDAO;
import com.infy.jcart.dao.VariantDAOImpl;
import com.infy.jcart.dao.ViewAuctionsDAO;
import com.infy.jcart.dao.ViewAuctionsDAOImpl;
import com.infy.jcart.dao.ViewIssueDAO;
import com.infy.jcart.dao.ViewIssueDAOImpl;
import com.infy.jcart.dao.ViewMyOrdersDAOImpl;
import com.infy.jcart.dao.ViewMyOrdersDAO;
import com.infy.jcart.dao.WishListDAO;
import com.infy.jcart.dao.WishListDAOImpl;
import com.infy.jcart.web.managedbeans.CartBean;
import com.infy.jcart.web.managedbeans.ChangePasswordBean;
import com.infy.jcart.web.managedbeans.LoginBean;
import com.infy.jcart.web.managedbeans.PopularProductsBean;
import com.infy.jcart.web.managedbeans.PurchaseBagBean;
import com.infy.jcart.web.managedbeans.PurchaseBookBean;
import com.infy.jcart.web.managedbeans.RegistrationBean;
import com.infy.jcart.web.managedbeans.SearchBean;


public class Factory {

	public static LoginService createLoginService()	{
		return new LoginServiceImpl();
	}

	public static LoginDAO createLoginDAOImpl()	{
		return new LoginDAOImpl();
	}

	public static LoginBean createLoginBean()	{
		return new LoginBean();
	}

	public static EditProfileService createEditProfileServiceImpl()	{
		return new EditProfileServiceImpl();
	}

	public static EditProfileDAO createEditProfileDAOImpl()	{
		return new EditProfileDAOImpl();
	}

	public static ChangePasswordBean createChangePasswordBean()	{
		return new ChangePasswordBean();
	}

	public static RegistrationBean registrationBean(){
		return new RegistrationBean();
	}

	public static EditProfileDAO updateProfileDAOImpl()
	{
		return new EditProfileDAOImpl();
	}
	public static IssueDAO getAllIssues()
	{
		return new IssueDAOImpl();
	}

	public static ViewMyOrdersService createViewMyOrdersService()
	{
		return new ViewMyOrdersServiceImpl();
	}

	public static WishListService createWishListService()
	{
		return new WishListServiceImpl();
	}

	public static UpdateService createUpdateService()
	{
		return new UpdateServiceImpl();
	}


	public static VariantService createVariantService()
	{
		return new VariantServiceImpl();
	}

	public static CustomerExperienceReportService createCustomerExperienceReportService() {
	
		return new CustomerExperienceReportServiceImpl();
	}

	public static ShipmentService createShipmentService() {
		
		return new ShipmentServiceImpl();
	}
	 
	public static SearchService createSearchService()
	{
		return new SearchServiceImpl();

	}

	public static SearchBean createSearchBean()
	{
		return new SearchBean();
	}
	public static PopularProductsBean createPopularProductsBean()
	{
		return new PopularProductsBean();
	}

	public static RetailerService createRetailerService()
	{
		return new RetailerServiceImpl();
	}

	public static FeedBackDAO createFeedBackDAO()
	{
		return new FeedBackDAOImpl();
	}

	public static PurchaseBagBean createPurchaseBagBean() {
		return new PurchaseBagBean();
	}

	public static CartBean createCartBean() {
		return new CartBean();
	}


	
	public static PurchaseBookBean createPurchaseBookBean() {
		return new PurchaseBookBean();
	}

	public static ReportService createReportService() {
		return new ReportServiceImpl();
	}
	
	public static ReportDAO createReportDAO() {
		return new ReportDAOImpl();
	}
	
	public static ViewAuctionsService createViewAuctionsService() {
		return new ViewAuctionsServiceImpl();
	}

	public static AccountDAO createAccountDAO(){
		return new AccountDAOImpl();
	}

	public static ApproveRetailerDAO createApproveRetailerDAO()
	{
		return new ApproveRetailerDAOImpl();
	}

	public static ApproveReviewCommentsDAO createApproveReviewCommentsDAO()
	{
		return new ApproveReviewCommentsDAOImpl();
	}

	public static BidDAO createBidDAO()
	{
		return new BidDAOImpl();
	}

	public static CartDAO createCartDAO(){
		return new CartDAOImpl();
	}

	public static CategoryDAO createCategoryDAO()
	{
		return new CategoryDAOImpl();
	}

	public static CustomerExperienceReportDAO createCustomerExperienceReportDAO(){
		return new CustomerExperienceReportDAOImpl();
	}

	public static EditProfileDAO createEditProfileDAO(){
		return new EditProfileDAOImpl();
	}

	public static HomeDAO createHomeDAO(){
		return new HomeDAOImpl();
	}

	public static IssueDAO createIssueDAO(){
		return new IssueDAOImpl();
	}

	public static LoginDAO createLoginDAO(){
		return new LoginDAOImpl();
	}

	public static MessageDAO createMessageDAO(){
		return new MessageDAOImpl();
	}

	public static OfferDAO createOfferDAO(){
		return new OfferDAOImpl();
	}

	public static ProductDAO createProductDAO(){
		return new ProductDAOImpl();
	}

	public static PurchaseDAO createPurchaseDAO(){
		return new PurchaseDAOImpl();
	}

	public static RaiseIssueDAO createRaiseIssueDAO()
	{
		return new RaiseIssueDAOImpl();
	}

	public static RatingDAO createRatingDAO()
	{
		return new RatingDAOImpl();
	}


	public static RegistrationDAO createRegistrationDAO()
	{
		return new RegistrationDAOImpl();
	}


	public static RequestProductDAO createRequestProductDAO()
	{
		return new RequestProductDAOImpl();
	}


	public static RetailerDAO createRetailerDAO(){
		return new RetailerDAOImpl();
	}

	public static SearchDAO createSearchDAO(){
		return new SearchDAOImpl();
	}


	public static ShipmentDAO createShipmentDAO(){
		return new ShipmentDAOImpl();
	}


	public static UpdateDAO createUpdateDAO(){
		return new UpdateDAOImpl();
	}


	public static VariantDAO createVariantDAO(){
		return new VariantDAOImpl();
	}

	public static ViewAuctionsDAO createViewAuctionsDAO(){
		return new ViewAuctionsDAOImpl();
	}

	public static ViewIssueDAO createViewIssueDAO(){
		return new ViewIssueDAOImpl();
	}

	public static ViewMyOrdersDAO createViewMyOrdersDAO(){
		return new ViewMyOrdersDAOImpl();
	}


	public static WishListDAO createWishListDAO(){
		return new WishListDAOImpl();
	}


	public static AccountService createAccountService(){
		return new AccountServiceImpl();
	}

	public static CustomerExperienceReportService createExperienceReportService(){
		return new CustomerExperienceReportServiceImpl();
	}

	public static IssueService createIssueService(){
		return new IssueServiceImpl();
	}

	public static MessageService createMessageService(){
		return new MessageServiceImpl();
	}

	public static OfferService createOfferService(){
		return new OfferServiceImpl();
	}

	public static ProductService createProductService(){
		return new ProductServiceImpl();
	}



	public static BidService createBidService()
	{
		return new BidServiceImpl();
	}
	public static ApproveReviewCommentsService createApproveReviewCommentsService()
	{
		return new ApproveReviewCommentsServiceImpl();
	}

	public static ApproveRetailerService createApproveRetailerService()
	{
		return new ApproveRetailerServiceImpl();
	}

	public static RatingService createRatingService()
	{
		return new RatingServiceImpl();
	}





	public static RaiseIssueService createRaiseIssueService()
	{
		return new RaiseIssueServiceImpl();
	}



	public static RequestProductService createRequestProductService()
	{
		return new RequestProductServiceImpl();
	}


	public static RegistrationService createRegistrationService()
	{
		return new RegistrationServiceImpl();
	}



	public static CategoryService createCategoryService()
	{
		return new CategoryServiceImpl();
	}



	public static PurchaseService createPurchaseService(){
		return new PurchaseServiceImpl();
	}



	public static CartService createCartService(){
		return new CartServiceImpl();
	}
















}
