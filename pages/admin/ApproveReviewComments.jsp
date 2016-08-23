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
<title>JCart - Approve Review Comments</title>
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
				<h1><h:outputText value="#{lable.Approve_Review_Comments}"></h:outputText></h1>
				<c:if test="${not empty approveReviewCommentsBean.ratingList}">
				<h:panelGrid border="1" columns="3">
				<h:outputLabel value="#{lable.Select_the_columns}"></h:outputLabel>
				<h:selectManyCheckbox value="#{approveReviewCommentsBean.userChoice}"
				 onclick="submit()">
					<f:selectItem itemValue="userId" itemLabel="UserId"/>
					<f:selectItem itemValue="productId" itemLabel="ProductId"/>
					<f:selectItem itemValue="retailerId" itemLabel="RetailerId"/>
				</h:selectManyCheckbox>
			</h:panelGrid>
				<h:dataTable value="#{approveReviewCommentsBean.ratingList}" var="entry"
					rowClasses="row1,row2">
					<h:column id="column1">
						<f:facet name="header">
							<h:outputText value="#{lable.Rating}"></h:outputText>
						</f:facet>
						<h:outputText value="#{entry.rating}"></h:outputText>
					</h:column>
					<h:column id="column2">
						<f:facet name="header">
							<h:outputText value="#{lable.Review_Comments}"></h:outputText>
						</f:facet>
							<h:outputText value="#{entry.reviewComments}"></h:outputText>
					</h:column>
					<h:column id="column3">
						<f:facet name="header">
							<h:outputText value="#{lable.Date_Of_Submission}"></h:outputText>
						</f:facet>
							<h:outputText value="#{entry.dos}">
							<f:converter converterId="calendarConv"/>
							</h:outputText>
					</h:column>
					<c:forEach items="${approveReviewCommentsBean.userChoice}" var="choice">						
						<c:if test="${choice=='userId'}">	
					<h:column id="column5">
						<f:facet name="header">
							<h:outputText value="UserId"></h:outputText>
						</f:facet>
						<h:outputText value="#{entry.userId}"></h:outputText>
					</h:column>
					</c:if>
					<c:if test="${choice=='productId'}">	
					<h:column id="column6">
						<f:facet name="header">
							<h:outputText value="ProductId"></h:outputText>
						</f:facet>
						<h:outputText value="#{entry.productId}" rendered="#{entry.productId!=0}"></h:outputText>
					</h:column>
					</c:if>
					<c:if test="${choice=='retailerId'}">	
					<h:column id="column4">
						<f:facet name="header">
							<h:outputText value="RetailerId"></h:outputText>
						</f:facet>
						<h:outputText value="#{entry.retailerId}"></h:outputText>
					</h:column>
					</c:if>
					</c:forEach>
					<h:column id="column7">
						<f:facet name="header">
							<h:outputText value="#{lable.Select}"></h:outputText>
						</f:facet>
						<h:selectBooleanCheckbox value="#{entry.selected}">
						</h:selectBooleanCheckbox>
					</h:column>
				</h:dataTable><br>
				<h:commandButton styleClass="btn" value="#{lable.Approve}" action="#{approveReviewCommentsBean.approveReviews}" ></h:commandButton>  
				<h:outputText value="  "></h:outputText>
				</c:if><br>
				<h:outputText value="#{approveReviewCommentsBean.message}" styleClass="#{approveReviewCommentsBean.styleClass}"/>
				<br><br>
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