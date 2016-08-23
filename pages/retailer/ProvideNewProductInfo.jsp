<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Product Information (New Product)</title>
<link rel="stylesheet" type="text/css" href="../../page-resources/styles/style.css">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body>
<f:view>

	<h3><h:outputText value="#{lable.Provide_New_Product_Information}"></h:outputText> </h3><br>

	<h:panelGrid columns="3">

	<h:outputText value="#{lable.Price} :"></h:outputText>
	<h:inputText id="price" value="#{addProductBean.newProduct.price}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="price"></h:message>
	
	<h:outputText value="#{lable.Discount_percent} :"></h:outputText>
	<h:inputText id="discount" value="#{addProductBean.newProduct.discount}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="discount"></h:message>
	
	<h:outputText value="#{lable.Offer_Code_if_any} :"></h:outputText>
	<h:selectOneMenu id="offer" value="#{addProductBean.newProduct.offerCode}">
			<f:selectItem itemLabel="--#{lable.Select}--"/>
			<f:selectItems value="#{addProductBean.offerCodeList}"/>
	</h:selectOneMenu>
	<h:message for="offer"></h:message>
		
</h:panelGrid>

</f:view>
</body>
</html>