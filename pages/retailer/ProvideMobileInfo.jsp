<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Product Information (Mobile)</title>
<link rel="stylesheet" type="text/css" href="../page-resources/styles/style.css">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body>
<f:view>

	<h3><h:outputText value="#{lable.Provide_Mobile_Information}"></h:outputText> </h3><br>

	<h:panelGrid columns="3">

	<h:outputText value="#{lable.Company} :"></h:outputText>
	<h:inputText id="company" value="#{addProductBean.objarray[6].company}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="company"></h:message>
	
	<h:outputText value="#{lable.Product_Name} :"></h:outputText>
	<h:inputText id="product" value="#{addProductBean.objarray[6].productName}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="product"></h:message>
	
	<h:outputText value="#{lable.SIM_Type} :"></h:outputText>
	<h:selectOneRadio id="sType" value="#{addProductBean.objarray[6].sim}" required="true" requiredMessage="Please Select One Item">
		<f:selectItem itemLabel="#{lable.DUAL}" itemValue="D"></f:selectItem>
		<f:selectItem itemLabel="#{lable.Single}" itemValue="S"></f:selectItem>
	</h:selectOneRadio>
	<h:message for="sType"></h:message>
	
	<h:outputText value="#{lable.Camera} :"></h:outputText>
	<h:inputText id="cam" value="#{addProductBean.objarray[6].camera}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="cam"></h:message>
	
	<h:outputText value="#{lable.Screen_Size} :"></h:outputText>
	<h:inputText id="ssz" value="#{addProductBean.objarray[6].screenSize}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="ssz"></h:message>
	
	<h:outputText value="#{lable.Resolution_eg}:"></h:outputText>
	<h:inputText id="rs" value="#{addProductBean.objarray[6].resolution}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="rs"></h:message>
	
	<h:outputText value="#{lable.Battery_Type} :"></h:outputText>
	<h:inputText id="bt" value="#{addProductBean.objarray[6].batteryType}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="bt"></h:message>
	
	<h:outputText value="#{lable.Generation} :"></h:outputText>
	<h:selectOneMenu id="ideal" value="#{addProductBean.objarray[6].generation}" required="true" requiredMessage="Please Select one Item">
			<f:selectItem itemLabel="--#{lable.Select}--"/>
			<f:selectItem itemLabel="1G" itemValue="1G"/>
			<f:selectItem itemLabel="2G" itemValue="2G"/>
			<f:selectItem itemLabel="3G" itemValue="3G"/>
			<f:selectItem itemLabel="4G" itemValue="4G"/>
	</h:selectOneMenu>
	<h:message for="ideal"></h:message>
	
	<h:outputText value="#{lable.Memory} :"></h:outputText>
	<h:inputText id="mm" value="#{addProductBean.objarray[6].memory}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="mm"></h:message>
	
	<h:outputText value="#{lable.Description} :"></h:outputText>
	<h:inputTextarea id="desc" value="#{addProductBean.objarray[6].description}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="desc"></h:message>

</h:panelGrid>

</f:view>
</body>
</html>