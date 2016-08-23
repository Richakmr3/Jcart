<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Product Information (Computer)</title>
<link rel="stylesheet" type="text/css" href="../page-resources/styles/style.css">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body>
<f:view>

	<h3><h:outputText value="#{lable.Provide_Computer_Information}"></h:outputText> </h3><br>

	<h:panelGrid columns="3">

	<h:outputText value="#{lable.Company} :"></h:outputText>
	<h:inputText id="company" value="#{addProductBean.objarray[4].company}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="company"></h:message>
	
	<h:outputText value="#{lable.ProductName} :"></h:outputText>
	<h:inputText id="product" value="#{addProductBean.objarray[4].productName}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="product"></h:message>
	
	<h:outputText value="#{lable.Processor_Name} :"></h:outputText>
	<h:inputText id="procName" value="#{addProductBean.objarray[4].processorName}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="procName"></h:message>
	
	<h:outputText value="#{lable.Processor_Speed_Ghz}:"></h:outputText>
	<h:inputText id="procSpeed" value="#{addProductBean.objarray[4].processorSpeed}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="procSpeed"></h:message>
	
	<h:outputText value="#{lable.RAM_GB} :"></h:outputText>
	<h:inputText id="ram" value="#{addProductBean.objarray[4].ram}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="ram"></h:message>
	
	<h:outputText value="#{lable.Hard_Disk_Size_GB} :"></h:outputText>
	<h:inputText id="hd" value="#{addProductBean.objarray[4].hardDiskSize}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="hd"></h:message>
	
	<h:outputText value="#{lable.Graphic_Card} :"></h:outputText>
	<h:inputText id="gc" value="#{addProductBean.objarray[4].graphicCard}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="gc"></h:message>
	
	<h:outputText value="#{lable.Screen_Size} :"></h:outputText>
	<h:inputText id="sz" value="#{addProductBean.objarray[4].screenSize}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="sz"></h:message>
	
	<h:outputText value="#{lable.Battery_BackUp} :"></h:outputText>
	<h:inputText id="bbu" value="#{addProductBean.objarray[4].batteryBackUp}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="bbu"></h:message>
	
	<h:outputText value="#{lable.Battery_Cells} :"></h:outputText>
	<h:selectOneMenu id="btc" value="#{addProductBean.objarray[4].batteryCells}" required="true" requiredMessage="Please Select one Item">
			<f:selectItem itemLabel="-- #{lable.Select} --"/>
			<f:selectItem itemLabel="6" itemValue="6"/>
			<f:selectItem itemLabel="8" itemValue="8"/>
			<f:selectItem itemLabel="12" itemValue="12"/>
	</h:selectOneMenu>
	<h:message for="btc"></h:message>
	
	<h:outputText value="#{lable.Description} :"></h:outputText>
	<h:inputTextarea id="desc" value="#{addProductBean.objarray[4].description}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="desc"></h:message>
	
</h:panelGrid>

</f:view>
</body>
</html>