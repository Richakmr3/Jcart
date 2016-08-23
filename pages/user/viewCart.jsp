<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.List"%>
<html>
<head><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - View Cart</title>
<link href="../pages-resources/styles/style.css" media="screen"
	rel="stylesheet">
</head>
<body>
<%
	if(session.getAttribute("userId")==null || session.getAttribute("role").toString().charAt(0)!='U'){
		response.sendRedirect("../faces/errorPage.jsp");
	}
 %>
 

 
<f:view>
<jsp:include page="../header.jsp"></jsp:include>
		<!-- Search bar to be included in each page -->
		<center>
		<div id="container">
		<div id="menu1">
			<jsp:include page="UserLeftToc.jsp"></jsp:include>
		</div>
		<!-- Right navigation pane links -->
		<div id="menu2">
			<jsp:include page="UserRightToc.jsp"></jsp:include>
		</div>
		<h:form>
		
			<!-- Left navigation pane links -->
	
		<!-- Main body page for displaying details -->
			<div id="page">
			<center>
		<c:if test="${not empty cartBean.cartList}">
		<h:dataTable value="#{cartBean.cartList}" var="cart">
		<h:column>
		<f:facet name="header">
		<h:outputLabel value="Product ID"></h:outputLabel>
		</f:facet>
		<h:outputText value="#{cart.productId}"></h:outputText>
		</h:column>
		<h:column>
		<f:facet name="header">
		<h:outputLabel value="Variation Code"></h:outputLabel>
		</f:facet>
		<h:outputText value="#{cart.variations.variationCode}" rendered="#{cart.variations ne null }"></h:outputText>
		<h:outputText value="NA" rendered="#{cart.variations eq null }"></h:outputText>
		</h:column>
		<h:column>
		<f:facet name="header">
		<h:outputLabel value="Color"></h:outputLabel>
		</f:facet>
		<h:outputText value="#{cart.variations.color}" rendered="#{cart.variations ne null }"></h:outputText>
		<h:outputText value="NA" rendered="#{cart.variations eq null }"></h:outputText>
		</h:column>
		<h:column>
		<f:facet name="header">
		<h:outputLabel value="Fit"></h:outputLabel>
		</f:facet>
		<h:outputText value="#{cart.variations.fit}" rendered="#{cart.variations ne null }"></h:outputText>
		<h:outputText value="NA" rendered="#{cart.variations eq null }"></h:outputText>
		</h:column>
		<h:column>
		<f:facet name="header">
		<h:outputLabel value="Stock"></h:outputLabel>
		</f:facet>
		<h:outputText value="#{cart.variations.stock}" rendered="#{cart.variations ne null }"></h:outputText>
		<h:outputText value="NA" rendered="#{cart.variations eq null }"></h:outputText>
		</h:column>
		<h:column>
		<f:facet name="header">
		<h:outputLabel value="SellerId"></h:outputLabel>
		</f:facet>
		<h:outputText value="#{cart.sellerId}"></h:outputText>
		</h:column>
		<h:column>
		<f:facet name="header">
		<h:outputLabel value="Sub Category"></h:outputLabel>
		</f:facet>
		<h:outputText value="#{cart.subCategoryName}"></h:outputText>
		</h:column>
		<h:column>
		<f:facet name="header">
		<h:outputLabel value="Category"></h:outputLabel>
		</f:facet>
		<h:outputText value="#{cart.categoryName}"></h:outputText>
		</h:column>
		<h:column>
		<f:facet name="header">
		<h:outputLabel value="Company Name"></h:outputLabel>
		</f:facet>
		<h:outputText value="#{cart.companyName}"></h:outputText>
		</h:column>
		<h:column>
		<f:facet name="header">
		<h:outputLabel value="Product Name"></h:outputLabel>
		</f:facet>
		<h:outputText value="#{cart.productName}"></h:outputText>
		</h:column>
		<h:column>
		<f:facet name="header">
		<h:outputLabel value="Image"></h:outputLabel>
		</f:facet>
		<h:graphicImage value="#{cart.variations.imageUrl}"></h:graphicImage>
		</h:column>
		<h:column>
		<f:facet name="header">
		<h:outputLabel value="Select"></h:outputLabel>
		</f:facet>
		<h:selectBooleanCheckbox value="#{cart.selected}"></h:selectBooleanCheckbox>
		</h:column>
		
		</h:dataTable><bR><BR>
			<h:commandButton value="Remove Items" action="#{cartBean.removeItems}" styleClass="btn"></h:commandButton>&nbsp;&nbsp;
			<h:commandButton value="Purchase Item" action="#{billingBean.generateBill}" styleClass="btn"></h:commandButton>
			</c:if>
			<c:if test="${empty cartBean.cartList}">
			<br><br>
			<h:outputText value="Cart is empty!!" style="color:red;font-weight:bolder;"></h:outputText>
			</c:if>
			</center>
			</div>
			</h:form>
		</div>
		</center>
		
		<!-- Footer -->
		<jsp:include page="../footer.jsp"></jsp:include>
	
</f:view>
</body>
</html>