<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Add Offer Details</title>
<script language="javascript" src="../../page-resources/scripts/datetimepicker.js">
</script>
<link href="../../page-resources/styles/style.css" media="screen"
	rel="stylesheet">
	<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
	
</head>
<body>
<%
	if(session.getAttribute("userId")==null || session.getAttribute("role").toString().charAt(0)!='R'){
		response.sendRedirect("../../faces/pages/errorPage.jsp");
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
	<h3><h:outputText value="#{lable.Fix_Offer_Details}"></h:outputText> </h3><br>

	<h:panelGrid columns="3">

	<h:outputText value="#{lable.Offer_Discount_percent}:"></h:outputText>
	<h:inputText id="disc" value="#{addOfferBean.offerDiscount}" required="true" requiredMessage="#{lable.Mandatory_Field}">
	<f:validateDoubleRange minimum="1" maximum="100"></f:validateDoubleRange>
	</h:inputText>
	<h:message for="disc"></h:message>
	
	<h:outputText value="#{lable.Start_Date}:"></h:outputText>
	<h:inputText id="sDate" onclick="javascript:NewCal('sDate','ddmmmyyyy')" value="#{addOfferBean.startDate}" required="true" requiredMessage="#{lable.Mandatory_Field}">
	<f:converter converterId="dateConverter"/>
	</h:inputText>
	
	<h:message for="sDate"></h:message>
	
	<h:outputText value="#{lable.End_Date}:"></h:outputText>
	<h:inputText id="eDate" onclick="javascript:NewCal('eDate','ddmmmyyyy')" value="#{addOfferBean.endDate}" required="true" requiredMessage="#{lable.Mandatory_Field}">
	<f:converter converterId="dateConverter"/>
	</h:inputText>
	<h:message for="eDate"></h:message>
	
	<h:outputText value="#{lable.Offer_Details}:"></h:outputText>
	<h:inputTextarea id="det" value="#{addOfferBean.offerDetails}" required="true" requiredMessage="#{lable.Mandatory_Field}"/>
	<h:message for="det"></h:message>
</h:panelGrid>
<h:commandButton value="#{lable.Add_Offer_Details}" styleClass="btn" action="#{addOfferBean.fixOfferDetails}" type="submit" ></h:commandButton>
<br><br>
	<h:outputText value="#{addOfferBean.message}" styleClass="#{addOfferBean.styleClass}"></h:outputText><br>
	</div>
	</div>
</center>
</h:form>
<jsp:include page="../footer.jsp"></jsp:include>
</f:view>
</body>
</html>



