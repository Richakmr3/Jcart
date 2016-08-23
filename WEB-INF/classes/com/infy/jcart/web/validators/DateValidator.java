package com.infy.jcart.web.validators;

import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("dateValidator")
public class DateValidator implements Validator {
	/***
	 * @description this method checks whether the date entred by the user is
	 *              after today's date or not, if not then throws Validator
	 *              Exception
	 * @param FacesContext
	 *            , UIComponent, Object
	 * @return void
	 * @throws ValidatorException
	 */
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {

		Calendar givenDate = (Calendar) arg2;
		if (givenDate.before(Calendar.getInstance())) {
			FacesMessage message = new FacesMessage();
			message.setDetail("Date should not be before the Current Date");
			message.setSummary("Date should not be before the Current Date");
			throw new ValidatorException(message);
		}

	}
}
