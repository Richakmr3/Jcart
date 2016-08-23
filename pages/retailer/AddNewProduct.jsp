<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Add New Product</title>
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
		
	 <div id="menu1">
			<jsp:include page="RetailerLeftToc.jsp"></jsp:include>
		</div>
		<!-- Right navigation pane links -->
		<div id="menu2">
			<jsp:include page="RetailerRightToc.jsp"></jsp:include>
		</div>
		<!-- Main body page for displaying details -->
		<div id="page">
	<h3><h:outputText value="#{lable.Add_New_Product_Information}"></h:outputText> </h3><br>
	
	<h:panelGrid columns="3">

	
		
	<h:outputText value="#{lable.Category}"></h:outputText>
	<h:selectOneMenu id="cat" valueChangeListener="#{addProductBean.getSubCategories}" onchange="submit();" required="true" requiredMessage="Please Select one Item">
			<f:selectItem itemLabel="--#{lable.Select}--"/>
			<f:selectItems value="#{addProductBean.categoryList}"/>
	</h:selectOneMenu>
	<h:message for="cat"></h:message>
	
	<h:outputText value="#{lable.Sub_Category}"></h:outputText>
	<h:selectOneMenu id="subCat" valueChangeListener="#{addProductBean.selectSubCategory}" onchange="submit();" required="true" requiredMessage="#{lable.Please_Select_one_Item }">
			<f:selectItem itemLabel="--#{lable.Select}--"/>
			<f:selectItems value="#{addProductBean.subCategoryList}"/>
	</h:selectOneMenu>
	<h:message for="subCat"></h:message>

</h:panelGrid>

<c:choose>
<c:when test="${addProductBean.categoryId eq 108 and addProductBean.subCategory ne null}">
	<jsp:include page="ProvideBagsInfo.jsp"></jsp:include>
</c:when>
<c:when test="${addProductBean.categoryId eq 101 and addProductBean.subCategory ne null}">
	<jsp:include page="ProvideBooksInfo.jsp"></jsp:include>
</c:when>
<c:when test="${addProductBean.categoryId eq 103 and addProductBean.subCategory ne null}">
	<jsp:include page="ProvideCameraInfo.jsp"></jsp:include>
</c:when>
<c:when test="${addProductBean.categoryId eq 106 and addProductBean.subCategory ne null}">
	<jsp:include page="ProvideClothingInfo.jsp"></jsp:include>
</c:when>
<c:when test="${addProductBean.categoryId eq 102 and addProductBean.subCategory ne null}">
	<jsp:include page="ProvideComputerInfo.jsp"></jsp:include>
</c:when>
<c:when test="${addProductBean.categoryId eq 107 and addProductBean.subCategory ne null}">
	<jsp:include page="ProvideFootWearInfo.jsp"></jsp:include>
</c:when>
<c:when test="${addProductBean.categoryId eq 105 and addProductBean.subCategory ne null}">
	<jsp:include page="ProvideMobileInfo.jsp"></jsp:include>
</c:when>
<c:when test="${addProductBean.categoryId eq 104 and addProductBean.subCategory ne null}">
	<jsp:include page="ProvideTelevisionInfo.jsp"></jsp:include>
</c:when>
</c:choose>

	<jsp:include page="ProvideNewProductInfo.jsp"></jsp:include>


<h:commandButton value="#{lable.Add_Information}" styleClass="btn" type="submit" action="#{addProductBean.addProduct}"></h:commandButton>
<br><br>

	<h:outputText rendered="#{addProductBean.message ne null} " value="#{addProductBean.message}" styleClass="#{addProductBean.styleClass}"></h:outputText><br>
	</div>
	</div>
</center>
</h:form>
<jsp:include page="../footer.jsp"></jsp:include>
</f:view>
</body>
</html>


