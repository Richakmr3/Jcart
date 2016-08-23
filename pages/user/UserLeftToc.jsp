<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link href="../../page-resources/styles/style.css" media="screen"
	rel="stylesheet">
	<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body>
<%
	if(session.getAttribute("userId")==null || session.getAttribute("role").toString().charAt(0)!='U'){
		response.sendRedirect("../../faces/pages/errorPage.jsp");
	}
 %>
			<ul>
					<li><h:outputLink value="#" id="current"><h:outputText value="#{lable.Product_Options}"></h:outputText></h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/searchPage.jsp"><h:outputText value="#{lable.Search_Page}"></h:outputText></h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/ViewPopularProducts.jsp"><h:outputText value="#{lable.View_Popular_Products}"></h:outputText></h:outputLink></li>
					<li><h:outputLink value="../../faces/pages/ViewProductsByRetailer.jsp"><h:outputText value="#{lable.View_Products_By_Retailer}"></h:outputText></h:outputLink></li>
					<li><h:outputLink value="../../faces/pages/ViewRecentlyAddedProducts.jsp"><h:outputText value="#{lable.View_Recently_Added_Products}"></h:outputText></h:outputLink></li>
					<li><h:outputLink value="../../faces/pages/user/provideRating.jsp"><h:outputText value="#{lable.Provide_Rating}"></h:outputText></h:outputLink></li>
					<li><h:outputLink value="../../faces/pages/user/requestProduct.jsp"><h:outputText value="#{lable.Request_Product}"></h:outputText></h:outputLink></li>
					<li><h:outputLink value="../../faces/pages/user/initiateProductBidding.jsp"><h:outputText value="#{lable.Initiate_Bidding}"></h:outputText></h:outputLink></li>
					<li><h:outputLink value="../../faces/pages/user/updateProductBidding.jsp"><h:outputText value="#{lable.Update_Bidding}"></h:outputText></h:outputLink></li>
					<li><h:outputLink value="../../faces/pages/user/purchaseUsedProducts.jsp?&showlist=no"><h:outputText value="#{lable.Purchase_Used_Products}"></h:outputText></h:outputLink></li>				
				</ul>

</body>
</html>