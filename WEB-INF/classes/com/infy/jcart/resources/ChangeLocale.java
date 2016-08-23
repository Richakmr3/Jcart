package com.infy.jcart.resources;

import java.util.Locale;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ChangeLocale {
	private static String locale;

	public ChangeLocale() {
		locale="en";
	}
	public static String getLocale() {
		return locale;
	}


	public static void setLocale(String locale) {
		ChangeLocale.locale = locale;
	}
	
	public String changeLangBengali(){
		return this.changeLang("bn");
	}
	public String changeLangEng(){
		return this.changeLang("en");
	}
	
	public String changeLangHindi(){
		return this.changeLang("hi");
	}
	
	public String changeLangKannada(){
		return this.changeLang("kn");
	}
	
	public String changeLangMalayalam(){
		return this.changeLang("ml");
	}
	public String changeLangTamil(){
		return this.changeLang("ta");
	}
	public String changeLangTelugu(){
		return this.changeLang("te");
	}
	public String changeLang(String loca){
		locale=loca;
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(locale));
		return "";
	}
}
