package com.infy.jcart.web.convertors;

import java.util.Calendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.infy.jcart.resources.CalendarUtility;
import com.infy.jcart.resources.JCartLogger;



@FacesConverter("dateConverter")
public class DateConverter implements Converter {

	  /**
     * @description this method converts a String Object to Calendar Object
     * @param FacesContext, UIComponent, String
     * @return Object

     */

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Calendar c = null;
		try {
			c = CalendarUtility.getCalendarFromString(arg2,"dd-MMM-yyyy");
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "getAsObject", e
					.toString());		
		}
		return c;
	}
	 /**
     * @description this method converts a Calendar Object to String Object
     * @param FacesContext, UIComponent, Object
     * @return String

     */
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String s = null;
		try {
			s = CalendarUtility.getStringFromCalendar((Calendar)arg2,"dd-MMM-yyyy");
		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "getAsString", e
					.toString());
		}
		return s;
	}
}
