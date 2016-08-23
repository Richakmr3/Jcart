package com.infy.jcart.resources;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class JCartConfig {

	public static String getMessageFromProperties(String exceptionName){
		Properties properties = new Properties();
		try {
			if(ChangeLocale.getLocale()==null){
				new ChangeLocale();	
			}
			if(ChangeLocale.getLocale().equals("en")){
				properties.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(
								"com/infy/jcart/resources/exceptions_en.properties"));
			}else if(ChangeLocale.getLocale().equals("hi")){
				properties.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(
								"com/infy/jcart/resources/exceptions_hi.properties"));
			}
			else if(ChangeLocale.getLocale().equals("bn")){
				properties.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(
								"com/infy/jcart/resources/exceptions_bn.properties"));
			}
			else if(ChangeLocale.getLocale().equals("kn")){
				properties.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(
								"com/infy/jcart/resources/exceptions_kn.properties"));
			}else if(ChangeLocale.getLocale().equals("ml")){
				properties.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(
								"com/infy/jcart/resources/exceptions_ml.properties"));
			}else if(ChangeLocale.getLocale().equals("ta")){
				properties.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(
								"com/infy/jcart/resources/exceptions_ta.properties"));
			}else if(ChangeLocale.getLocale().equals("te")){
				properties.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(
								"com/infy/jcart/resources/exceptions_te.properties"));
			}
			else{
				properties.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(
								"com/infy/jcart/resources/exceptions.properties"));
			}
			
		} catch (IOException e) {
				JCartLogger
				.logError("JCartConfig", "getErrorMessage", e.toString());
			
		}
		String message=properties.getProperty(exceptionName);
		if(message==null){
		message=getErrorMessageEnglish(exceptionName);
		}
		return message;
	
		
	}

	private static String getErrorMessageEnglish(String exceptionName){
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(
							"com/infy/jcart/resources/exceptions.properties"));
		} catch (IOException e) {
			JCartLogger
			.logError("JCartConfig", "getErrorMessage", e.toString());	
			
		}
		return properties.getProperty(exceptionName);
	}
	
	public static String getPath(String path) {
	//	Properties properties = new Properties();
		try {

			if (path.equalsIgnoreCase("resourcePath")) {
				return "../page-resources/images/";
			}
			// constructing path for products in Desktop
			if (path.equalsIgnoreCase("imageUrlPath")) {
				String userHome = System.getProperty("user.home");
				String tillDesktop = userHome + "\\Desktop\\products\\";
				File myFile = new File(tillDesktop);
				if (!myFile.exists()) {
					myFile.mkdir();
				}
				return tillDesktop;
			}

			if (path.equalsIgnoreCase("errorLoggerPath")) {
				String userHome = System.getProperty("user.home");
				String tillDesktop = userHome + "\\Desktop\\logger\\";
				File myFile = new File(tillDesktop);
				if (!myFile.exists()) {
					myFile.mkdir();
				}
				return tillDesktop + "ErrorLogFile.txt";
			}

		} catch (Exception e) {

			JCartLogger.logError("JCartConfig", "getPath", e.toString());
		}
		return "";
	}
}
