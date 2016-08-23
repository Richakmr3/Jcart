<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Product Information (TV)</title>
<link rel="stylesheet" type="text/css" href="../page-resources/styles/style.css">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>

</head>
<body>
<f:view>

	<h3><h:outputText value="#{lable.Provide_Television_Information}"></h:outputText> </h3><br>

	<h:panelGrid columns="3">

	<h:outputText value="#{lable.Company} :"></h:outputText>
	<h:inputText id="company" value="#{addProductBean.objarray[7].company}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="company"></h:message>
	
	<h:outputText value="#{lable.Product_Name} :"></h:outputText>
	<h:inputText id="product" value="#{addProductBean.objarray[7].productName}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="product"></h:message>
	
	<h:outputText value="#{lable.Pixel_Resolution} :"></h:outputText>
	<h:inputText id="pxr" value="#{addProductBean.objarray[7].pixelResolution}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="pxr"></h:message>
	
	<h:outputText value="#{lable.Screen_Size} :"></h:outputText>
	<h:inputText id="ssz" value="#{addProductBean.objarray[7].screenSize}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="ssz"></h:message>
	
	<h:outputText value="#{lable.HD_Ready} :"></h:outputText>
	<h:selectOneRadio id="hd" value="#{addProductBean.objarray[7].hdReady}" required="true" requiredMessage="Please Select One Item">
		<f:selectItem itemLabel="#{lable.Yes}" itemValue="Y"></f:selectItem>
		<f:selectItem itemLabel="#{lable.No}" itemValue="N"></f:selectItem>
	</h:selectOneRadio>
	<h:message for="hd"></h:message>
	
	<h:outputText value="#{lable.Dimensions_eg}:"></h:outputText>
	<h:inputText id="dm" value="#{addProductBean.objarray[7].dimension}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="dm"></h:message>
	
	<h:outputText value="#{lable.Power_Consumption} :"></h:outputText>
	<h:inputText id="pc" value="#{addProductBean.objarray[7].powerConsumption}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="pc"></h:message>
	
	<h:outputText value="#{lable.Speaker_Output} :"></h:outputText>
	<h:inputText id="so" value="#{addProductBean.objarray[7].speakerOutput}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="so"></h:message>
	
	<h:outputText value="#{lable.Speaker_Type} :"></h:outputText>
	<h:inputText id="st" value="#{addProductBean.objarray[7].speakerType}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="st"></h:message>
	
	<h:outputText value="#{lable.Description} :"></h:outputText>
	<h:inputTextarea id="desc" value="#{addProductBean.objarray[7].description}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="desc"></h:message>
	
</h:panelGrid>

</f:view>
</body>
</html>