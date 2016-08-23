package com.infy.jcart.web.convertors;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter("requestStatusConvertor")
public class RequestStatusConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return null;
	}
	  /**
     * @description this method converts the request status in accordance with the
     * business logic
     * @param FacesContext, UIComponent, Object
     * @return String

     */
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String s = arg2.toString();
		if (s.equalsIgnoreCase("S")) {
			return "Submitted";
		} else {
			return "Acknowledged";
		}
	}

}
