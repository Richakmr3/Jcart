<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Remove Offer Details</title>
<link href="../../page-resources/styles/style.css" media="screen"
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
	<h3><h:outputText value="#{lable.Remove_Offer_Details}"></h:outputText> </h3><br>
	
	<h:panelGrid columns="3">

	<h:outputText value="#{lable.Offer_Code}:"></h:outputText>
	<h:selectOneMenu id="oCode" valueChangeListener="#{removeOfferBean.getOfferDetails}" onchange="submit();" required="true" requiredMessage="Please Select one Item">
			<f:selectItem itemLabel="-- #{lable.Select} --"/>
			<f:selectItems value="#{removeOfferBean.offerCodeList}"/>
	</h:selectOneMenu>
	<h:message for="oCode"></h:message>
	
	<c:if test="${removeOfferBean.offerCode ne null}">
	<h:outputText value="#{lable.Offer_Discount_percent}:"></h:outputText>
	<h:outputText id="disc" value="#{removeOfferBean.productOffer.offerDiscount}"/>
	<h:message for="disc"></h:message>
	
	<h:outputText value="#{lable.Start_Date}:"></h:outputText>
	<h:outputText id="sDate" value="#{removeOfferBean.productOffer.startDate}">
	<f:converter converterId="dateConverter"/>
	</h:outputText>
	<h:message for="sDate"></h:message>
	
	<h:outputText value="#{lable.End_Date}:"></h:outputText>
	<h:outputText id="eDate" value="#{removeOfferBean.productOffer.endDate}">
	<f:converter converterId="dateConverter"/>
	</h:outputText>
	<h:message for="eDate"></h:message>
	
	<h:outputText value="#{lable.Offer_Details}:"></h:outputText>
	<h:outputText id="det" value="#{removeOfferBean.productOffer.offerDetails}"/>
	<h:message for="det"></h:message>
	</c:if>
</h:panelGrid>
<c:if test="${removeOfferBean.offerCode ne null}">
<h:commandButton styleClass="btn" value="#{lable.Remove_Offer_Details}" action="#{removeOfferBean.removeOfferDetails}" type="submit" ></h:commandButton>
</c:if>
<br><br>

	<h:outputText value="#{removeOfferBean.message}" styleClass="#{removeOfferBean.styleClass}"></h:outputText><br>
	<h:messages></h:messages>
	</div>
	</div>
</center>
</h:form>
<jsp:include page="../footer.jsp"></jsp:include>
</f:view>
</body>
</html>



