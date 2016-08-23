package com.infy.jcart.web.convertors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.infy.jcart.resources.JCartLogger;

@FacesConverter("calendarConv")
public class CalendarTOStringConverter implements Converter {

	/**
	 * @description this method parses a String object to Calendar Object and
	 *              returns null in case String has no value
	 * @param FacesContext
	 *            , UIComponent, String
	 * @return Object
	 * @throws ConverterException
	 */

	public Object getAsObject(FacesContext context, UIComponent component,
			String str) {
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MMM-yyyy");
		Date dateReference;
		try {
			if (str == "") {
				return null;
			}
			dateReference = simpleDate.parse(str);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateReference);
			return calendar;
		} catch (ParseException e) {
			JCartLogger.logError(this.getClass().getName(), "getAsObject",
					e.toString());
			FacesMessage message = new FacesMessage();
			message.setSummary("Enter date in dd-mmm-yyyy format");
			message.setDetail("Enter date in dd-mmm-yyyy format");
			throw new ConverterException(message);
		}

	}

	/**
	 * @description this method formats a Calendar object to String Object and
	 *              returns null in case Object is null
	 * @param FacesContext
	 *            , UIComponent, Object
	 * @return String
	 */
	public String getAsString(FacesContext context, UIComponent component,
			Object obj) {
		String textDate = null;
		try {
			if (obj == null) {
				return null;
			}
			Calendar c = (Calendar) obj;
			Date d = c.getTime();
			SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MMM-yyyy");
			textDate = simpleDate.format(d);

		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "getAsString",
					e.getMessage());
		}
		return textDate;
	}

}
