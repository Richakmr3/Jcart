<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Product Information (Camera)</title>
<link rel="stylesheet" type="text/css" href="../page-resources/styles/style.css">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body>
<f:view>

	<h3><h:outputText value="#{lable.Provide_Camera_Information}"></h:outputText> </h3><br>

	<h:panelGrid columns="3">

	<h:outputText value="#{lable.Company} :"></h:outputText>
	<h:inputText id="company" value="#{addProductBean.objarray[2].company}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="company"></h:message>
	
	<h:outputText value="#{lable.Product_Name} :"></h:outputText>
	<h:inputText id="product" value="#{addProductBean.objarray[2].productName}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="product"></h:message>
	
	<h:outputText value="#{lable.Optical_Zoom} :"></h:outputText>
	<h:inputText id="op" value="#{addProductBean.objarray[2].opticalZoom}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="op"></h:message>
	
	<h:outputText value="#{lable.Digital_Zoom} :"></h:outputText>
	<h:inputText id="dg" value="#{addProductBean.objarray[2].digitalZoom}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="dg"></h:message>
	
	<h:outputText value="#{lable.Pixels} :"></h:outputText>
	<h:inputText id="px" value="#{addProductBean.objarray[2].pixels}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="px"></h:message>
	
	<h:outputText value="#{lable.Focal_Length} :"></h:outputText>
	<h:inputText id="fl" value="#{addProductBean.objarray[2].focalLength}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="fl"></h:message>
	
	<h:outputText value="#{lable.Memory} :"></h:outputText>
	<h:inputText id="mm" value="#{addProductBean.objarray[2].memory}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="mm"></h:message>
	
	<h:outputText value="#{lable.Battery} :"></h:outputText>
	<h:inputText id="bt" value="#{addProductBean.objarray[2].battery}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="bt"></h:message>
	
	<h:outputText value="#{lable.Shutter_Speed} :"></h:outputText>
	<h:inputText id="stp" value="#{addProductBean.objarray[2].shutterSpeed}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="stp"></h:message>
	
	<h:outputText value="#{lable.Description} :"></h:outputText>
	<h:inputTextarea id="desc" value="#{addProductBean.objarray[2].description}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="desc"></h:message>

</h:panelGrid>

</f:view>
</body>
</html>