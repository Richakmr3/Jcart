<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Product Information (Bag)</title>
<link rel="stylesheet" type="text/css" href="../page-resources/styles/style.css">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body>
<f:view>

	<h3><h:outputText value="#{lable.Provide_Bags_Information}"></h:outputText> </h3><br>

	<h:panelGrid columns="3">

	<h:outputText value="#{lable.Company} :"></h:outputText>
	<h:inputText id="company" value="#{addProductBean.objarray[0].company}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="company"></h:message>
	
	<h:outputText value="#{lable.Product_Name} :"></h:outputText>
	<h:inputText id="product" value="#{addProductBean.objarray[0].productName}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="product"></h:message>
	
	<h:outputText value="#{lable.Type} :"></h:outputText>
	<h:inputText id="type" value="#{addProductBean.objarray[0].type}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="type"></h:message>
	
	<h:outputText value="#{lable.Ideal_For} :"></h:outputText>
	<h:selectOneMenu id="ideal" value="#{addProductBean.objarray[0].idealFor}" required="true" requiredMessage="Please Select one Item">
			<f:selectItem itemLabel="--#{lable.Select}--"/>
			<f:selectItem itemLabel="Male" itemValue="M"/>
			<f:selectItem itemLabel="Female" itemValue="F"/>
	</h:selectOneMenu>
	<h:message for="ideal"></h:message>
	
	<h:outputText value="#{lable.Material} :"></h:outputText>
	<h:inputText id="material" value="#{addProductBean.objarray[0].material}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="material"></h:message>
	
	<h:outputText value="#{lable.Weight} :"></h:outputText>
	<h:inputText id="weight" value="#{addProductBean.objarray[0].weight}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="weight"></h:message>
	
	<h:outputText value="#{lable.Description} :"></h:outputText>
	<h:inputTextarea id="desc" value="#{addProductBean.objarray[0].description}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="desc"></h:message>

</h:panelGrid>

</f:view>
</body>
</html>