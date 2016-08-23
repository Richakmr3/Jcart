package com.infy.jcart.web.convertors;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("paymentTypeConvertor")
public class PaymentTypeConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return null;
	}
	 /**
	   * @description this method converts the Payment type in accordance to 
	   * the business logic
	   * @param FacesContext, UIComponent, Object
	   * @return String
	   */
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String s = arg2.toString();
		if (s.equalsIgnoreCase("D")) {
			return "Cash on delivery";
		} else if (s.equalsIgnoreCase("C")) {
			return "By Card";
		} else {
			return "EMI";
		}
	}

}
