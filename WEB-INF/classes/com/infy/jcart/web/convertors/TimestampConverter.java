package com.infy.jcart.web.convertors;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.infy.jcart.resources.JCartLogger;



@FacesConverter("timestampConvertor")
public class TimestampConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return null;
	}
	  /**
     * @description this method converts the Calendar object to a String Object
     * containing date in "dd-MMM-yyyy hh:mm:ss" format and returns "--"
     * if Object is null
     * @param FacesContext, UIComponent, String
     * @return Object

     */
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		String textDate = null;
		try {
			if (obj == null) {
				return "--";
			}
			Calendar c = (Calendar) obj;
			Date d = c.getTime();
			SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MMM-yyyy");
			textDate = simpleDate.format(d);
			textDate = textDate + " " + c.get(Calendar.HOUR_OF_DAY) + ":"
					+ c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);

		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "getAsString", e
					.getMessage());

		}
		return textDate;
	}

}
