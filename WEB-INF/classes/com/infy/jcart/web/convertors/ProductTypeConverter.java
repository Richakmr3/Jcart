package com.infy.jcart.web.convertors;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;


@FacesConverter("productTypeConvertor")
public class ProductTypeConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return null;
	}
	 /**
	   * @description this method converts the product status in accordance to 
	   * the business logic
	   * @param FacesContext, UIComponent, Object
	   * @return String
	   * @throws ConverterException
	   */
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Character arg = (Character) arg2;
		if (arg == 'N')
			return "New";
		else if (arg == 'U')
			return "Used";
		else {
			throw new ConverterException("Invalid Product Type");
		}

	}

}
