package com.infy.jcart.business.service;

import java.util.List;

import com.infy.jcart.beans.ProductVariations;
import com.infy.jcart.resources.Factory;
import com.infy.jcart.resources.JCartLogger;

public class VariantServiceImpl implements VariantService {

	/***
	 * @description this method invokes addVariant() method of VariantDAO and
	 *              returns the String obtained
	 * @param ProductVariations
	 * @return String
	 * @catch Exception
	 */
	public String addVariant(ProductVariations productVariations)
			throws Exception {
		try {

			return Factory.createVariantDAO().addVariant(productVariations);

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "addVariant",
					exception.getMessage());
			throw exception;
		}
	}


	/***
	 * @description this method invokes getAllVariants() method of VariantDAO
	 *              and returns the list obtained
	 * @param Integer
	 * @return List<ProductVariationsTO>
	 * @throws VariantService.NO_VARIANTS_AVAILABLE
	 * @catch Exception
	 */
	public List<ProductVariations> getAllVariants(Integer productId)
			throws Exception {
		try {

			List<ProductVariations> list = Factory.createVariantDAO()
					.getAllVariants(productId);
			if (list.size() == 0)
				throw new Exception("VariantService.NO_VARIANTS_AVAILABLE");
			else
				return list;

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAllVariants",
					exception.getMessage());
			throw exception;
		}
	}
	/***
	 * @description this method invokes getVariantDetails() method of VariantDAO
	 *              and returns the ProductVariations obtained
	 * @param Integer
	 *            , String
	 * @return ProductVariations
	 * @throws Exception
	 * @catch Exception
	 */
	public ProductVariations getVariantDetails(Integer productId,
			String variationCode) throws Exception {
		try {

			return Factory.createVariantDAO().getVariantDetails(productId,
					variationCode);

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(),
					"getVariantDetails", exception.getMessage());
			throw exception;
		}
	}

	/***
	 * @description this method invokes updateStock() method of VariantDAO
	 * @param ProductVariations
	 * @return void
	 * @throws Exception
	 * @catch Exception
	 */

	public void updateStock(ProductVariations to) throws Exception {

		try {

			Factory.createVariantDAO().updateStock(to);

		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "updateStock",
					exception.getMessage());
			throw exception;
		}

	}

}
