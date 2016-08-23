
package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.ProductOffer;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;

public class OfferServiceImpl implements OfferService {

	/***
	 * @description this method invokes fixOfferDetails() method of OfferDAOImpl
	 *              class and returns the offer code obtained
	 * @param ProductOffer
	 * @return Integer
	 * @throws Exception
	 * @catch Exception
	 */
	public Integer fixOfferDetails(ProductOffer offer) throws Exception {
		try {
			return Factory.createOfferDAO().fixOfferDetails(offer);
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "fixOfferDetails",
					exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method invokes updateOfferDetails() method of OfferDAOImpl
	 *              class
	 * @param List
	 *            <ProductOffer>
	 * @return void
	 * @throws Exception
	 * @catch Exception
	 */
	public void updateOfferDetails(List<ProductOffer> offerList)
			throws Exception {

		Factory.createOfferDAO().updateOfferDetails(offerList);
		try {
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"updateOfferDetails", exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method invokes removeOfferDetails() method of OfferDAOImpl
	 *              class
	 * @param List
	 *            <ProductOffer>
	 * @return void
	 * @throws OfferService.INVALID_OFFER_CODE
	 * @catch Exception
	 */
	public void removeOfferDetails(Integer offerCode) throws Exception {
		try {
			if (Factory.createOfferDAO().getProductOffers(offerCode)
					.equals("Not Found")) {
				throw new Exception("OfferService.INVALID_OFFER_CODE");
			} else {
				Factory.createOfferDAO().removeOfferDetails(offerCode);
			}
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"removeOfferDetails", exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method invokes getOfferDetails() method of OfferDAOImpl
	 *              class and returns the ProductOffer obtained
	 * @param Integer
	 * @return ProductOffer
	 * @throws OfferService.INVALID_OFFER_CODE
	 * @catch Exception
	 */
	public ProductOffer getOfferDetails(Integer offerCode) throws Exception {
		try {
			if (Factory.createOfferDAO().getProductOffers(offerCode)
					.equals("Not Found")) {
				throw new Exception("OfferService.INVALID_OFFER_CODE");
			} else {
				return Factory.createOfferDAO().getOfferDetails(offerCode);
			}
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getOfferDetails",
					exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method invokes getAllOfferCodes() method of OfferDAOImpl
	 *              class and returns the list obtained
	 * @return List<Integer>
	 * @throws OfferService.NO_OFFERCODES_AVAILABLE
	 * @catch Exception
	 */
	public List<Integer> getAllOfferCodes() throws Exception {
		try {
			List<Integer> list = Factory.createOfferDAO().getAllOfferCodes();
			if (list.size() == 0)
				throw new Exception("OfferService.NO_OFFERCODES_AVAILABLE");
			else
				return list;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllOfferCodes",
					exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method invokes getAllOffers() method of OfferDAOImpl class
	 *              and returns the list obtained
	 * @throws OfferService.NO_OFFERCODES_AVAILABLE
	 * @return List<ProductOffer>
	 * @catch Exception
	 */
	public List<ProductOffer> getAllOffers() throws Exception {
		try {
			List<ProductOffer> list = Factory.createOfferDAO().getAllOffers();
			if (list.size() == 0)
				throw new Exception("OfferService.NO_OFFERCODES_AVAILABLE");
			else
				return list;
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllOffers",
					exception.getMessage());
			throw exception;
		}
	}

}
