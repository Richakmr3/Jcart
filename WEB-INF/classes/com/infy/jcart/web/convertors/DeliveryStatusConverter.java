package com.infy.jcart.web.convertors;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.infy.jcart.resources.JCartLogger;

@FacesConverter("deliveryStatusConverter")
public class DeliveryStatusConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return null;
	}

	/**
	 * @description this method converts the delivery status in accordance to
	 *              the business logic
	 * @param FacesContext
	 *            , UIComponent, Object
	 * @return String
	 */
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		try {
			String s = arg2.toString();
			if (s.equalsIgnoreCase("D")) {
				return "Delivered";
			}

			else if (s.equalsIgnoreCase("P")) {
				return "Pending";
			}

			else if (s.equalsIgnoreCase("S")) {
				return "Shipped";
			}
		} catch (Exception exception) {
			JCartLogger.logError(this.getClass().getName(), "getAsString",
					exception.getMessage());
		}
		return "Returned";
	}
}
