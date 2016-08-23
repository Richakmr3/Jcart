<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>JCart - Unblock User Account</title>
<link href="../../page-resources/styles/style.css" media="screen" rel="stylesheet">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body>
<%
	if(session.getAttribute("userId")==null || session.getAttribute("role").toString().charAt(0)!='A'){
		response.sendRedirect("../../faces/pages/errorPage.jsp");
	}
 %>
	<f:view>
		<jsp:include page="../header.jsp"></jsp:include>
		<!-- Search bar to be included in each page -->
		<center>
		<div id="container">
		
			<!-- Left navigation pane links -->
		 <div id="menu1">
			<jsp:include page="AdminLeftToc.jsp"></jsp:include>
		</div>
		<!-- Right navigation pane links -->
		<div id="menu2">
			<jsp:include page="AdminRightToc.jsp"></jsp:include>
		</div>
		<!-- Main body page for displaying details -->
			<div id="page">
			<center>
			<h:form>
				<h1><h:outputText value="#{lable.Unblock_Accounts}"></h:outputText></h1>
			
			
				<c:if test="${not empty unblockAccountBean.blockedAccountList}">
				<h:dataTable value="#{unblockAccountBean.blockedAccountList}" var="entry"
					rowClasses="row1,row2" rendered="#{unblockAccountBean.blockedAccountList ne null}">
					<h:column id="column1">
						<f:facet name="header">
							<h:outputText value="#{lable.UserId}"></h:outputText>
						</f:facet>
						<h:outputText value="#{entry.userId}"></h:outputText>
					</h:column>
					<h:column id="column2">
						<f:facet name="header">
							<h:outputText value="#{lable.Role}"></h:outputText>
						</f:facet>
						
							<h:outputText value="#{entry.role}">
							<f:converter converterId="roleConvertor"/>
							</h:outputText>
					</h:column>
					<h:column id="column3">
						<f:facet name="header">
							<h:outputText value="#{lable.Last_Seen_On}"></h:outputText>
						</f:facet>
						
							<h:outputText value="#{entry.logoutTime}">
							<f:converter converterId="timestampConvertor"/>
							</h:outputText>
							</h:column>
					<h:column id="column4">
						<f:facet name="header">
							<h:outputText value="#{lable.Select}"></h:outputText>
						</f:facet>
							<h:selectBooleanCheckbox value="#{entry.selected}">
				</h:selectBooleanCheckbox>
							</h:column>
				</h:dataTable>
				
				<h:commandButton styleClass="btn" value="#{lable.Unblock_Selected_Accounts}" 
				action="#{unblockAccountBean.unblockAccounts}" ></h:commandButton>  
	
				</c:if><br><br>
				<h:outputText value="#{unblockAccountBean.message}" styleClass="#{unblockAccountBean.styleClass}"/>
					</h:form>
			</center>
		</div>
		</div>
		</center>
		<!-- Footer -->
		<jsp:include page="../footer.jsp"></jsp:include>
	</f:view>
</body>
</html>