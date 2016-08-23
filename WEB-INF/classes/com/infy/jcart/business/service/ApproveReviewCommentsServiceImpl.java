package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.ProductRating;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;




public class ApproveReviewCommentsServiceImpl implements ApproveReviewCommentsService{
	/**
	 * @description this method invokes getUnapprovedRatings() method of
	 * ApproveReviewCommentsDAOImpl class
	 * @return List<ProductRating>
	 * @throws ApproveReviewCommentsService.NO_APPROVE_NEEDE
	 * @catch Exception
	 */
	public List<ProductRating> getUnapprovedRatings()
			throws Exception {
		try {
			List<ProductRating> list = Factory.createApproveReviewCommentsDAO()
					.getUnapprovedRatings();
			if (list.isEmpty()) {
				throw new Exception("ApproveReviewCommentsService.NO_APPROVE_NEEDE");
			}
			return list;
		}  catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getUnapprovedRatings",
					exception.getMessage());
			throw exception;
		}

	}

	/**
	 * @description this method invokes updateReviews() method of
	 *              ApproveReviewCommentsDAOImpl class
	 * @param List
	 *            <ProductRating>, char
	 * @return void
	 * @throws ApproveReviewCommentsService.SELECT_REVIEW
	 * @catch Exception
	 * 
	 */
	public void updateReviews(List<ProductRating> selectedReviews, char c)
			throws  Exception {
		try {
			if (selectedReviews.isEmpty()) {
				throw new Exception("ApproveReviewCommentsService.SELECT_REVIEW");
			}
			for (int i = 0; i < selectedReviews.size(); i++) {
				ProductRating to = selectedReviews.get(i);
				int k = to.getProductId();
				if (k != 0) {
					Factory.createApproveReviewCommentsDAO()
					.updateProductReviews(to, c);

				} else {
					Factory.createApproveReviewCommentsDAO()
					.updateRetailerReviews(to, c);
				}
			}

		}catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "updateReviews",
					exception.getMessage());
			throw exception;
		}
	}
}
