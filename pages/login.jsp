<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>JCart - Login</title>
<link href="../page-resources/styles/style.css" media="screen"
	rel="stylesheet">
	<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
	
</head>
<body>

<f:view>
<jsp:include page="header.jsp"></jsp:include>
		<center>
		<div id="container">
		 <div id="menu1">
		 <jsp:include page="VisitorLeftToc.jsp" ></jsp:include>
		</div>
		<div id="hlinkslang">
			<h:form>
				<h:commandLink value="#{lable.English}"	action="#{changeLocale.changeLangEng}"></h:commandLink>&nbsp;
                <h:commandLink value="#{lable.Bengali}"	action="#{changeLocale.changeLangBengali}"></h:commandLink>&nbsp;
                <h:commandLink value="#{lable.Hindi}"	action="#{changeLocale.changeLangHindi}"></h:commandLink>&nbsp;
                <h:commandLink value="#{lable.Kannada}"	action="#{changeLocale.changeLangKannada}"></h:commandLink>&nbsp;
                <h:commandLink value="#{lable.Malayalam}" action="#{changeLocale.changeLangMalayalam}"></h:commandLink>&nbsp;
                <h:commandLink value="#{lable.Tamil}" action="#{changeLocale.changeLangTamil}"></h:commandLink>&nbsp;
                <h:commandLink value="#{lable.Telugu}" action="#{changeLocale.changeLangTelugu}"></h:commandLink>
			</h:form>
		</div>
		<div id="page">
<h:form>
		
	            <h1><h:outputText value="#{lable.Login}"></h:outputText> </h1>
				<h:panelGrid columns="2">
				<h:outputLabel value="#{lable.User_ID} : "><span class="redStar">*</span></h:outputLabel>
				<h:inputText value="#{loginBean.userId}" required="true" requiredMessage="Please enter your User ID">
					<f:validateLength maximum="100"></f:validateLength>
				</h:inputText>
				<h:outputLabel value="#{lable.Password }  : "><span class="redStar">*</span></h:outputLabel>
				<h:inputSecret value="#{loginBean.password}" required="true" requiredMessage="Please enter the correct password."></h:inputSecret>
				<h:outputLabel></h:outputLabel>
				<h:commandButton value="#{lable.Login}" styleClass="sub" action="#{loginBean.doLogin}"></h:commandButton>
				</h:panelGrid>
				<br>
</h:form>
	<h:messages></h:messages>
	<h:outputText value="#{loginBean.message}" styleClass="#{loginBean.styleClass}"></h:outputText><br>
	
	</div>
	</div>
</center>

<jsp:include page="footer.jsp"></jsp:include>
</f:view>
</body>
</html>