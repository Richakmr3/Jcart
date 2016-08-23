<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Update Offer Details</title>
<link href="../page-resources/styles/style.css" media="screen"
	rel="stylesheet">
	<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
	
</head>
<body>
<%
	if(session.getAttribute("userId")==null || session.getAttribute("role").toString().charAt(0)!='R'){
		response.sendRedirect("../errorPage.jsp");
	}
 %>
<f:view>
<jsp:include page="../header.jsp"></jsp:include>
		
<h:form prependId="false">
<center>
		<div id="container">
		
			<!-- Left navigation pane links -->
		 <div id="menu1">
			<jsp:include page="RetailerLeftToc.jsp"></jsp:include>
		</div>
		<!-- Right navigation pane links -->
		<div id="menu2">
			<jsp:include page="RetailerRightToc.jsp"></jsp:include>
		</div>
		<!-- Main body page for displaying details -->
		<div id="page">
	<h3><h:outputText value="#{lable.Update_Offer_Details}"></h:outputText> </h3><br>
	<c:if test="${not empty updateOfferBean.offerList}">
	<h:dataTable border="1" value="#{updateOfferBean.offerList}" var="item" rowClasses="row1,row2">
			<h:column id="choose">
				<f:facet name="header">
					<h:outputText value="#{lable.Select }"></h:outputText>
				</f:facet>
				<h:selectBooleanCheckbox value="#{item.selected}"></h:selectBooleanCheckbox>
			</h:column>
			<h:column id="id1">
				<f:facet name="header">
					<h:outputText value="#{lable.Offer_Code}"></h:outputText>
				</f:facet>
				<h:outputText value="#{item.offerCode}"></h:outputText>
			</h:column>
			<h:column id="id2">
				<f:facet name="header">
					<h:outputText value="#{lable.Offer_Discount_percent}"></h:outputText>
				</f:facet>
				<h:outputText value="#{item.offerDiscount}"></h:outputText>
			</h:column>
			<h:column id="id3">
				<f:facet name="header">
					<h:outputText value="#{lable.Start_Date}"></h:outputText>
				</f:facet>
				<h:outputText value="#{item.startDate}">
				<f:converter converterId="dateConverter"/>
				</h:outputText>
			</h:column>
			<h:column id="id4">
				<f:facet name="header">
					<h:outputText value="#{lable.End_Date}"></h:outputText>
				</f:facet>
				<h:outputText value="#{item.endDate}">
				<f:converter converterId="dateConverter"/>
				</h:outputText>
			</h:column>
			<h:column id="id5">
				<f:facet name="header">
					<h:outputText value="#{lable.Description}"></h:outputText>
				</f:facet>
				<h:outputText value="#{item.offerDetails}"></h:outputText>
			</h:column>
</h:dataTable>
<br>	
<h:commandButton styleClass="btn" value="#{lable.Update_Offers}" action="#{updateOfferBean.selectToUpdate}" type="submit"></h:commandButton>
</c:if>
<br><br>

	<h:outputText value="#{updateOfferBean.message}" styleClass="#{updateOfferBean.styleClass}"></h:outputText><br>
	</div>
	</div>
</center>
</h:form>
<jsp:include page="../footer.jsp"></jsp:include>
</f:view>
</body>
</html>



