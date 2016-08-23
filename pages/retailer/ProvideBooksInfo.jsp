<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Product Information (Book)</title>
<link rel="stylesheet" type="text/css" href="../page-resources/styles/style.css">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body>
<f:view>

	<h3><h:outputText value="#{lable.Provide_Book_Information}"></h:outputText> </h3><br>

	<h:panelGrid columns="3">

	<h:outputText value="#{lable.Publisher} :"></h:outputText>
	<h:inputText id="pb" value="#{addProductBean.objarray[1].publisher}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="pb"></h:message>
	
	<h:outputText value="#{lable.Product_Name} :"></h:outputText>
	<h:inputText id="product" value="#{addProductBean.objarray[1].productName}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="product"></h:message>
	
	<h:outputText value="#{lable.Author} :"></h:outputText>
	<h:inputText id="author" value="#{addProductBean.objarray[1].author}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="author"></h:message>
	
	<h:outputText value="#{lable.Language} :"></h:outputText>
	<h:selectOneMenu id="lang" value="#{addProductBean.objarray[1].language}" required="true" requiredMessage="Please Select one Item">
			<f:selectItem itemLabel="--#{lable.Select}--"/>
			<f:selectItem itemLabel="Telugu" itemValue="Telugu"/>
			<f:selectItem itemLabel="English" itemValue="English"/>
			<f:selectItem itemLabel="Hindi" itemValue="Hindi"/>
			<f:selectItem itemLabel="Tamil" itemValue="Tamil"/>
			<f:selectItem itemLabel="Kannada" itemValue="Kannada"/>
	</h:selectOneMenu>
	<h:message for="lang"></h:message>
	
	<h:outputText value="#{lable.Published_Year} :"></h:outputText>
	<h:inputText id="year" value="#{addProductBean.objarray[1].publishedYear}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="year"></h:message>
	
	<h:outputText value="#{lable.No_of_pages} :"></h:outputText>
	<h:inputText id="no" value="#{addProductBean.objarray[1].numberOfPages}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="no"></h:message>
	
	<h:outputText value="#{lable.Type_Of_Cover} :"></h:outputText>
	<h:selectOneRadio id="type" value="#{addProductBean.objarray[1].typeOfCover}" required="true" requiredMessage="Please Select One Item">
		<f:selectItem itemLabel="#{lable.Hard}" itemValue="H"></f:selectItem>
		<f:selectItem itemLabel="#{lable.Soft}" itemValue="S"></f:selectItem>
	</h:selectOneRadio>
	<h:message for="type"></h:message>
	
	<h:outputText value="#{lable.Dimensions_eg}:"></h:outputText>
	<h:inputText id="dimensions" value="#{addProductBean.objarray[1].dimensions}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="dimensions"></h:message>
	
	<h:outputText value="#{lable.Description} :"></h:outputText>
	<h:inputTextarea id="desc" value="#{addProductBean.objarray[1].description}" required="true" requiredMessage="Mandatory Field"/>
	<h:message for="desc"></h:message>

</h:panelGrid>

</f:view>
</body>
</html>