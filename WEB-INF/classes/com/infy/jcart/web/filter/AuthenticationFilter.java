package com.infy.jcart.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infy.jcart.resources.JCartLogger;

public class AuthenticationFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) {
		try {
			HttpServletRequest request = (HttpServletRequest) arg0;
			HttpServletResponse response = (HttpServletResponse) arg1;
			String url = request.getRequestURL().toString();

			String user = (String) request.getSession().getAttribute("userId");
			String role = null;
			if ((user != null) &&(request.getSession()
					.getAttribute("role")!=null)) 
			{
				role = request.getSession()
				.getAttribute("role")
				.toString();
			}
			
			 if 
			(		url.contains("javax.faces.resource")
					||url.contains("page-resources")
					||url.contains("/searchPage.jsp") 
					|| url.contains("/login.jsp")
					|| url.contains("/logout.jsp")
					|| url.contains("/SignUp.jsp")
					|| url.contains("/accessDenied.jsp")
					|| url.contains("/errorPage.jsp")
					||url.contains("/ViewPopularProducts.jsp")
					|| url.contains("/ViewProductsByRetailer.jsp")
					|| url.contains("/ViewRecentlyAddedProducts.jsp")
					|| url.contains("/ViewProductVariations.jsp")
					|| url.contains("/viewSubCategory.jsp")
					||url.contains("/help")
					
					||url.contains("/purchaseBags.jsp")
					||url.contains("/purchaseBooks.jsp")
					||url.contains("/purchaseCamera.jsp")
					
					||url.contains("/purchaseClothing.jsp")
					||url.contains("/purchaseComputers.jsp")
					||url.contains("/purchaseFootWear.jsp")
	
					||url.contains("/purchaseMobile.jsp")
					||url.contains("/purchaseTelevision.jsp")
					) 
			{
				
				arg2.doFilter(request, response);

			} else if (user == null) 
			{
				
				response.sendRedirect("../../faces/pages/accessDenied.jsp");
			} else if (role.equals("A")) {
				if (!(url.contains("/admin"))) {
					
					response.sendRedirect("../../faces/pages/accessDenied.jsp");
				} else {
					
					arg2.doFilter(request, response);
				}
			} else if (role.equals("R")) {
				if (!(url.contains("/retailer")
					||url.contains("/accountSettings")
					||url.contains("/help")
					||url.contains("/FileUploadServlet"))) 
				{
					
					response.sendRedirect("../../faces/pages/accessDenied.jsp");
				} 
				else 
				{
					
					arg2.doFilter(request, response);
				}
			} else if (role.equals("U")) {
				if (!(url.contains("/user")
					||url.contains("/accountSettings")
					||url.contains("/help"))) 
				{
					
					response.sendRedirect("../../faces/pages/accessDenied.jsp");
				} 
				else 
				{
					
					arg2.doFilter(request, response);
				}

			} 
			

		} catch (Exception e) {
			JCartLogger.logError(this.getClass().getName(), "doFilter", e
					.getMessage());
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
