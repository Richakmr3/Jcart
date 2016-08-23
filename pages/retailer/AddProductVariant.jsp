<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Add Product Variant</title>
<script language="javascript" src="../resources/scripts/datetimepicker.js">
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
	<h3><h:outputText value="#{lable.Add_Product_Variant}"></h:outputText> </h3><br>

	<h:panelGrid columns="2">

	<h:outputText value="#{lable.Product_ID}:"></h:outputText>
	<h:selectOneMenu id="pId" valueChangeListener="#{addProductVariantBean.getProductDetails}" onchange="submit();" required="true" requiredMessage="Please Select a Product ID">
			<f:selectItem itemLabel="-- #{lable.Select} --"/>
			<f:selectItems value="#{addProductVariantBean.productList}"/>
	</h:selectOneMenu>
	
	<c:if test="${addProductVariantBean.productDetails!=null}">
	<h:outputText value="#{lable.Category}:"></h:outputText>
	<h:outputText value="#{addProductVariantBean.productDetails.category}"/>
		
	<h:outputText value="#{lable.Sub_Category}:"></h:outputText>
	<h:outputText value="#{addProductVariantBean.productDetails.subcategory}"/>
	
	<h:outputText value="#{lable.Product_Name}:"></h:outputText>
	<h:outputText value="#{addProductVariantBean.productDetails.productName}"/>
		
	<c:if test="${addProductVariantBean.productDetails.category=='Clothing' or addProductVariantBean.productDetails.category=='Footwear'}">
	<h:outputText value="#{lable.Fit}:"></h:outputText>
	<h:inputText id="fit" value="#{addProductVariantBean.productVariations.fit}"/>
	</c:if>
	
	<c:if test="${addProductVariantBean.productDetails.category!='Books'}">
	<h:outputText value="#{lable.Color}:"></h:outputText>
	<h:inputText id="color" value="#{addProductVariantBean.productVariations.color}" required="true" requiredMessage="#{lable.Color_is_Mandatory}"/>
	</c:if>
	
	<h:outputText value="#{lable.Stock}:"></h:outputText>
	<h:inputText id="stock" value="#{addProductVariantBean.productVariations.stock}" required="true" requiredMessage="#{lable.Stock_is_Mandatory}"/>
	
	</c:if>
</h:panelGrid>
<h:commandButton styleClass="btn" value="#{lable.Add_Variant}" action="#{addProductVariantBean.addVariant}" type="submit" ></h:commandButton>
<br><br>
	<h:outputText value="#{addProductVariantBean.message}" styleClass="#{addProductVariantBean.styleClass}"></h:outputText><br>
	
	<h:messages></h:messages>
	</div>
	</div>
</center>
</h:form>
<jsp:include page="../footer.jsp"></jsp:include>
</f:view>
</body>
</html>



