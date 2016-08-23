package com.infy.jcart.web.convertors;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.infy.jcart.resources.JCartLogger;


/***
 * 
 * @author swati_kansal
 *
 */
@FacesConverter("roleConvertor")
public class RoleConveter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		try{
			return arg2;
		}
		catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "getAsObject", e.getMessage());
			return null;
		}
	}
	  /**
     * @description this method converts the role of user in accordance
     * to the business logic
     * @param FacesContext, UIComponent, Object
     * @return String

     */
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		try{
			Character c=(Character) arg2;
			if(c=='A'){
				return "Admin";
			}
			else if(c=='R'){
				return "Retailer";
			}
			else if(c=='U'){
				return "User";
			}
			else{
				return null;
			}
		}
		
		catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "getAsString", e.getMessage());
			return null;
		}
	
	}

}
