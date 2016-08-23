<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title></title>
<link href="../../page-resources/styles/style.css" media="screen"
	rel="stylesheet">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>

	
</head>
<body>

<%
	if(session.getAttribute("userId")==null || !session.getAttribute("role").equals('A')){
		response.sendRedirect("../../faces/pages/errorPage.jsp");
	}
 %>

			<ul>
				<li><h:outputLink value="#" id="current"><h:outputText value="#{lable.Reports}"></h:outputText></h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/admin/CategorySalesGraph.jsp"><h:outputText value="#{lable.Category_Sales}"></h:outputText></h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/admin/CustomerExperienceReport.jsp"><h:outputText value="#{lable.Customer_Experience}"></h:outputText></h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/admin/IconicBrandReport.jsp"><h:outputText value="#{lable.Iconic_Brands}"></h:outputText></h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/admin/TrendingReport.jsp"><h:outputText value="#{lable.Trendings}"></h:outputText></h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/admin/ValuableCustomerReport.jsp"><h:outputText value="#{lable.Valuable_Customers}"></h:outputText></h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/admin/viewOverallFeedback.jsp">View Application Feedback</h:outputLink> </li>
					
			</ul>

</body>
</html>