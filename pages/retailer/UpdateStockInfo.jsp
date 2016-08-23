<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Update Stock Information</title>
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
	<h3><h:outputText value="#{lable.Update_Stock_Information}"></h:outputText> </h3><br>
<br>

	<h:panelGrid columns="2">
	<h:outputText value="#{lable.Category}"></h:outputText>
	<h:selectOneMenu id="cat" valueChangeListener="#{updateStockBean.getSubCategories}" onchange="submit();" required="true" requiredMessage="Select a valid Category id" >
			<f:selectItem itemLabel="--#{lable.Select}--"/>
			<f:selectItems value="#{updateStockBean.categoryList}"/>
	</h:selectOneMenu>
	<c:if test="${not empty updateStockBean.subCategoryList}">
	<h:outputText value="#{lable.Sub_Category}"></h:outputText>
	<h:selectOneMenu id="subCat" valueChangeListener="#{updateStockBean.getAllProducts}" onchange="submit();" required="true" requiredMessage="Select a valid Sub Category id" >
			<f:selectItem itemLabel="--#{lable.Select}--"/>
			<f:selectItems value="#{updateStockBean.subCategoryList}"/>
	</h:selectOneMenu>
	</c:if>
	<c:if test="${not empty updateStockBean.productList}">
	<h:outputText value="#{lable.Product_Name}"></h:outputText>
	<h:selectOneMenu id="pId" valueChangeListener="#{updateStockBean.getAllVariants}" onchange="submit();" required="true" requiredMessage="Select a valid Product id">
			<f:selectItem itemLabel="--#{lable.Select}--"/>
			<f:selectItems value="#{updateStockBean.productList}"/>
	</h:selectOneMenu>
	</c:if>
	<c:if test="${not empty updateStockBean.variantList}">
	<h:outputText value="#{lable.Variation_Code}"></h:outputText>
	<h:selectOneMenu id="vCode" valueChangeListener="#{updateStockBean.populateExistingStock}" onchange="submit();" required="true" requiredMessage="Select a valid variation code">
			<f:selectItem itemLabel="--#{lable.Select}--"/>
			<f:selectItems value="#{updateStockBean.variantList}"/>
	</h:selectOneMenu>
	</c:if>
	<c:if test="${updateStockBean.variationCode ne null}">
	<h:outputText value="#{lable.Existing_Stock}:"></h:outputText>
	<h:outputText id="Estock" value="#{updateStockBean.existingStock}"/>
	<h:outputText value="#{lable.Set_Stock}:"></h:outputText>
	<h:inputText id="stock" value="#{updateStockBean.stock}" required="true" requiredMessage="#{lable.Stock_is_Mandatory}">
	</h:inputText>
	
	</c:if>
<br>
</h:panelGrid>
<h:commandButton styleClass="btn" value="#{lable.Fix_Stock}" action="#{updateStockBean.updateStock}" type="submit" ></h:commandButton>
	

<br><br>
	<h:outputText value="#{updateStockBean.message}" styleClass="#{updateStockBean.styleClass}"></h:outputText><br>
	<h:messages></h:messages>
	</div>
	</div>
</center>
</h:form>
<jsp:include page="../footer.jsp"></jsp:include>
</f:view>
</body>
</html>

