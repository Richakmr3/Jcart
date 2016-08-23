package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.ProductRating;

public interface ApproveReviewCommentsService {
	public List<ProductRating> getUnapprovedRatings()
			throws Exception;
	public void updateReviews(List<ProductRating> selectedReviews, char c)
			throws  Exception;
	
}
