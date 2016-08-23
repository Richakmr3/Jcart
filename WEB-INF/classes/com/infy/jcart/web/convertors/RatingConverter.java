package com.infy.jcart.web.convertors;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/****
 * 
 * @author Dinesh_r05
 * 
 */

@FacesConverter("ratingConvertor")
public class RatingConverter implements Converter {

	  /**
     * @description this method converts the rating received 
     * to the corresponding Integer
     * @param FacesContext, UIComponent, String
     * @return Object

     */
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Object rating = null;
		if (arg2.equals("Very Bad")) {
			rating = 1;
		} else if (arg2.equals("Bad")) {
			rating = 2;
		} else if (arg2.equals("Average")) {
			rating = 3;
		} else if (arg2.equals("Good")) {
			rating = 4;
		} else if (arg2.equals("Excellent")) {
			rating = 5;
		}
		return rating;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return null;
	}

}
