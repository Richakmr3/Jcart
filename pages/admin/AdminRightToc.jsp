<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title></title>
<link href="../../page-resources/styles/style.css" media="screen"
	rel="stylesheet">
	<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body>
<%
	if(session.getAttribute("userId")==null || session.getAttribute("role").toString().charAt(0)!='A'){
		response.sendRedirect("../../faces/pages/errorPage.jsp");
	}
 %>
			<ul>
				<li><h:outputLink value="#" id="current1"><h:outputText value="#{lable.Admin_Options}"></h:outputText></h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/admin/ApproveRetailer.jsp"><h:outputText value="#{lable.Approve_Retailer}"></h:outputText></h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/admin/ApproveReviewComments.jsp"><h:outputText value="#{lable.Approve_Review_Comments}"></h:outputText></h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/admin/SendMessage.jsp"><h:outputText value="#{lable.Send_Message}"></h:outputText></h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/admin/UnblockUserAccounts.jsp"><h:outputText value="#{lable.Unblock_User_Accounts}"></h:outputText></h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/admin/UnlockUserAccounts.jsp"><h:outputText value="#{lable.Unlock_User_Accounts}"></h:outputText></h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/admin/ViewIssues.jsp"><h:outputText value="#{lable.View_Issues}"></h:outputText></h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/admin/ViewProductAuctions.jsp"><h:outputText value="#{lable.View_Product_Auctions}"></h:outputText></h:outputLink> </li>
					
			</ul>

</body>
</html>