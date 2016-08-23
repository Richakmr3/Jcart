package com.infy.jcart.web.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.infy.jcart.beans.ProductRating;
import com.infy.jcart.resources.ErrorRedirect;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

@ManagedBean
@SessionScoped
public class ApproveReviewCommentsBean {
	private List<ProductRating> ratingList;
	private List<String> userChoice;
	private String message;
	private String styleClass;

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	/****
	 * @description This method is used to populate the unapproved 
	 * product rating details
	 * 
	 * @exception Exception
	 */
	public ApproveReviewCommentsBean(){
		try {
			ratingList =Factory.createApproveReviewCommentsService()
					.getUnapprovedRatings();
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"ApproveReviewCommentsBean", exception.toString());
			if(exception.getMessage().contains("DAO.TECHNICAL_ERROR")){
				ErrorRedirect.reDirect();
				
			}
			else{
				styleClass="errorMsg";
				this.message=JCartConfig.getMessageFromProperties(exception.getMessage());
			}
		}
	}

	/***
	 * @description This method is used to approve selected reviews for a product and
	 * populate the unapproved product rating details
	 * 
	 * @return String
	 * @exception Exception
	 * 
	 */
	public String approveReviews() {
		String path="";
		try {
			List<ProductRating> selectedReviews = new ArrayList<ProductRating>();
			for (int i = 0; i < ratingList.size(); i++) {
				ProductRating to = ratingList.get(i);
				if (to.getSelected() == true) {
					selectedReviews.add(to);
				}
			}

			Factory.createApproveReviewCommentsService().updateReviews(selectedReviews,
					'A');
			ratingList = Factory.createApproveReviewCommentsService()
					.getUnapprovedRatings();
			styleClass="successMsg";
			this.message= JCartConfig.getMessageFromProperties("ApproveReviewCommentsBean.REVIEW_SUCCESS");
			
		
		} catch (Exception e) 
		{
			JCartLogger.logError(this.getClass().getName(), "approveReviews", e
					.getMessage());
			styleClass="errorMsg";
			this.message =JCartConfig.getMessageFromProperties(e.getMessage());
			if(e.getMessage().contains("DAO.TECHNICAL_ERROR"))
			{
			path = "/pages/errorPage";
			}
		}
		return path;
	}

	public List<ProductRating> getRatingList() {
		return ratingList;
	}

	public void setRatingList(List<ProductRating> ratingList) {
		this.ratingList = ratingList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setUserChoice(List<String> userChoice) {
		this.userChoice = userChoice;
	}

	public List<String> getUserChoice() {
		return userChoice;
	}

}
