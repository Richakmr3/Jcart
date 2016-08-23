<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Remove Sub-Category</title>
<link href="../page-resources/styles/style.css" media="screen"
	rel="stylesheet">
	<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
	
</head>
<body>
<%
	if(session.getAttribute("userId")==null || session.getAttribute("role").toString().charAt(0)!='R'){
		response.sendRedirect("../errorPage.jsp");
	}
 %>
<f:view>
<jsp:include page="../header.jsp"></jsp:include>
		
<h:form prependId="false">
<center>
		<div id="container">
		
		 <div id="menu1">
			<jsp:include page="RetailerLeftToc.jsp"></jsp:include>
		</div>
		<!-- Right navigation pane links -->
		<div id="menu2">
			<jsp:include page="RetailerRightToc.jsp"></jsp:include>
		</div>
		<!-- Main body page for displaying details -->
		<div id="page">
	<h3><h:outputText value="#{lable.Remove_Sub_Category}"></h:outputText> </h3><br>

	<h:panelGrid columns="3">

	<h:outputText value="#{lable.Category}"></h:outputText>
	<h:selectOneMenu id="cat" valueChangeListener="#{subCategoryBean.getSubCategories}" onchange="submit();" required="true" requiredMessage="#{lable.Please_Select_one_Item}">
			<f:selectItem itemLabel="--#{lable.Select}--"/>
			<f:selectItems value="#{subCategoryBean.categoryList}"/>
	</h:selectOneMenu>
	<h:message for="cat"></h:message>
	
	<h:outputText value="#{lable.Sub_Category}"></h:outputText>
	<h:selectOneMenu id="subCat" value="#{subCategoryBean.subCategoryId}" onchange="submit();" required="true" requiredMessage="#{lable.Please_Select_one_Item}">
			<f:selectItem itemLabel="--#{lable.Select}--"/>
			<f:selectItems value="#{subCategoryBean.subCategoryList}"/>
	</h:selectOneMenu>
	<h:message for="subCat"></h:message>

</h:panelGrid>
<h:commandButton styleClass="btn" value="#{lable.Remove_Sub_Category}" action="#{subCategoryBean.removeSubCategory}" type="submit" ></h:commandButton>
<br><br>

	<h:outputText value="#{subCategoryBean.message}" styleClass="#{subCategoryBean.styleClass}"></h:outputText><br>
	</div>
	</div>
</center>
</h:form>
<jsp:include page="../footer.jsp"></jsp:include>
</f:view>
</body>
</html>



