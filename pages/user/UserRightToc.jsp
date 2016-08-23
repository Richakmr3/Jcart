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
				<li><h:outputLink value="#" id="current1"><h:outputText value="#{lable.Account_Settings}"></h:outputText></h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/accountSettings/ChangePassword.jsp"><h:outputText value="#{lable.Change_Password}"></h:outputText></h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/accountSettings/deactivateAccount.jsp"><h:outputText value="#{lable.Deactivate_Account}"></h:outputText></h:outputLink></li>
					<li><h:outputLink value="../../faces/pages/accountSettings/EditProfile.jsp"><h:outputText value="#{lable.Edit_Profile}"></h:outputText></h:outputLink></li>
					<li><h:outputLink value="../../faces/pages/accountSettings/ToggleMessageService.jsp"><h:outputText value="#{lable.Toggle_Message_Settings}"></h:outputText></h:outputLink></li>
					
					
					<li><h:outputLink value="../../faces/pages/user/RaiseAnIssue.jsp"><h:outputText value="#{lable.Raise_an_issue}"></h:outputText></h:outputLink></li>
					<li><h:outputLink value="../../faces/pages/user/ViewMyOrders.jsp"><h:outputText value="#{lable.View_My_Order}"></h:outputText></h:outputLink></li>
					<li><h:outputLink value="../../faces/pages/user/ViewIssues-User.jsp"><h:outputText value="#{lable.View_My_Issues}"></h:outputText></h:outputLink></li>
					
			</ul>

</body>
</html>