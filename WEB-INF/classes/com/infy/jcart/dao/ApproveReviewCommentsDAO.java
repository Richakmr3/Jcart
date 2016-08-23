package com.infy.jcart.dao;

import java.util.List;

import com.infy.jcart.beans.ProductRating;

public interface ApproveReviewCommentsDAO {
	public List<ProductRating> getUnapprovedRatings() throws Exception;

	public void updateRetailerReviews(ProductRating to, Character c)
			throws Exception;

	public void updateProductReviews(ProductRating to, Character c)
			throws Exception;
}
