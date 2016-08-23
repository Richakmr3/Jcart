<%@page import="com.infy.jcart.web.managedbeans.CartBean"%>
<%@page import="com.infy.jcart.beans.Cart"%>
<%@page import="com.infy.jcart.web.managedbeans.BillingBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%><%@taglib
	uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="javax.faces.context.FacesContext"%>
<%@page import="javax.faces.context.ExternalContext"%>
<%@page import="java.util.Map"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body>
<%
	FacesContext context = FacesContext.getCurrentInstance();
	ExternalContext etx = context.getExternalContext();
	Map<String, Object> map = etx.getSessionMap();
	map.put("billingBean", new BillingBean());


	HttpSession httpSession = (HttpSession) etx.getSession(false);
	String s = (String) httpSession.getAttribute("billPaymentFlag");
	/*clearing the cart if by checking the paymentFlag*/
	if (s != null) {
		httpSession.setAttribute("billPaymentFlag", null);
		List<Cart> cartList = new ArrayList<Cart>();
		httpSession.setAttribute("cartList", cartList);

		CartBean mb = (CartBean) map.get("cartBean");
		mb.setCartList(cartList);
		map.put("cartBean", mb);
	}
%>
</body>
</html>