package com.infy.jcart.web.servlets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.infy.jcart.resources.JCartConfig;
import com.infy.jcart.resources.JCartLogger;

public class ShowPictureServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShowPictureServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException, IOException {
		try

		{

			String paramImageUrl = request.getParameter("paramImgUrl");
			InputStream inputStream = null;

			paramImageUrl = paramImageUrl.replace("\\", "/");
			if (paramImageUrl.contains("/products")) {
				inputStream = new FileInputStream(paramImageUrl);
			} else {

				String s = getServletContext().getInitParameter(
						"defaultImagePath");
				paramImageUrl = s + paramImageUrl.substring(2);
				inputStream = new FileInputStream(paramImageUrl);
			}

			response.setContentType("image/png");
			ServletOutputStream outputStream = response.getOutputStream();
			byte buf[] = new byte[10000];
			while (inputStream.available() > 0) {
				inputStream.read(buf);
				outputStream.write(buf);
			}

			inputStream.close();
			outputStream.close();
		} catch (FileNotFoundException e) {
			try {
				throw new Exception(JCartConfig
						.getMessageFromProperties("IMAGE_NOT_FOUND_EXCEPTION"));
			} catch (Exception e1) {
				JCartLogger.logError(this.getClass().getName(), "doGet",
						e1.toString());
				
			}

		} catch (Exception e) {
			if (null == e.getMessage()
					|| e.getMessage().equalsIgnoreCase("null")) {
			} else {
				JCartLogger.logError(this.getClass().getName(), "doGet",
						e.toString());
				e.getMessage();
			}
		}
	}
}
