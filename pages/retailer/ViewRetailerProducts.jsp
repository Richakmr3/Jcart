<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - View Retailer Products</title>
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
	<h3><h:outputText value="#{lable.View_Your_Products}"></h:outputText> </h3><br>
<br>

	<h:panelGrid columns="3">
	<h:outputText value="#{lable.Category}"></h:outputText>
	<h:selectOneMenu id="cat" valueChangeListener="#{viewProductsBean.getSubCategories}" onchange="submit();">
			<f:selectItem itemLabel="--#{lable.Select}--"/>
			<f:selectItems value="#{viewProductsBean.categoryList}"/>
	</h:selectOneMenu>
	<h:message for="cat"></h:message>
	
	<h:outputText value="#{lable.Sub_Category}"></h:outputText>
	<h:selectOneMenu id="subCat" valueChangeListener="#{viewProductsBean.getAllProducts}" onchange="submit();">
			<f:selectItem itemLabel="--#{lable.Select}--"/>
			<f:selectItems value="#{viewProductsBean.subCategoryList}"/>
	</h:selectOneMenu>
	<h:message for="subCat"></h:message>
	</h:panelGrid>
	<h:dataTable border="1" value="#{viewProductsBean.productList}" var="item" rowClasses="row1,row2" rendered="#{not empty viewProductsBean.productList}">
			<h:column id="id1">
				<f:facet name="header">
					<h:outputText value="#{lable.Product_ID}"></h:outputText>
				</f:facet>
				<h:outputText value="#{item.productId}"></h:outputText>
			</h:column>
			<h:column id="id2" rendered="#{item.productType=='N'}">
				<f:facet name="header">
					<h:outputText value="#{lable.Product_Name}"></h:outputText>
				</f:facet>
				<h:outputText value="#{item.productName}"></h:outputText>
			</h:column>
			<h:column id="id4">
				<f:facet name="header">
					<h:outputText value="#{lable.Price}"></h:outputText>
				</f:facet>
				<h:outputText value="#{item.price}">
				</h:outputText>
			</h:column>
</h:dataTable>

<br>
	
<br>


<br>
	<h:outputText value="#{viewProductsBean.message}" styleClass="#{viewProductsBean.styleClass}"></h:outputText><br>
	</div>
	</div>
</center>

</h:form>
<jsp:include page="../footer.jsp"></jsp:include>
</f:view>
</body>
</html>
