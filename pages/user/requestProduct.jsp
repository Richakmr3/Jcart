<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%><%@taglib
	uri="http://java.sun.com/jsf/html" prefix="h"%><%@taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Request New Product</title>
<link href="../page-resources/styles/style.css" media="screen" rel="stylesheet">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body>
<%
	if(session.getAttribute("userId")==null || session.getAttribute("role").toString().charAt(0)!='U'){
		response.sendRedirect("../errorPage.jsp");
	}
 %>

<f:view>
<!-- header -->
<jsp:include page="../header.jsp"></jsp:include>
<h:form prependId="false">

<div id="container">
	<div id="menu1">
			<jsp:include page="UserLeftToc.jsp"></jsp:include>
		</div>
		<!-- Right navigation pane links -->
		<div id="menu2">
			<jsp:include page="UserRightToc.jsp"></jsp:include>
		</div>
	<div id="page">
		<br>
		
		<center>
		<h:outputLabel value="#{lable.Request_New_Product}" styleClass="pageTitle"></h:outputLabel><br><br><br>
		<h:panelGrid columns="2" styleClass="menuPanel">
			<h:panelGrid columns="2">
				<h:outputText value="#{lable.Category1}" styleClass="label"></h:outputText>
				<h:selectOneMenu valueChangeListener="#{requestProductBean.populateSubCategory}" onchange="submit()" styleClass="selectMenu" required="true" requiredMessage="Category field is mandatory">
				<f:selectItem itemLabel="-select-"/>
				<f:selectItems value="#{requestProductBean.categoryList}"/>
				</h:selectOneMenu>
			</h:panelGrid>

			<c:if test="${not empty requestProductBean.subCategoryList}">
				<h:panelGrid columns="2">
					<h:outputText value="#{lable.SubCategory2}" styleClass="label"></h:outputText>
					<h:selectOneMenu valueChangeListener="#{requestProductBean.getSubCategory}" onchange="submit()" styleClass="selectMenu" required="true" requiredMessage="Sub-Category field is mandatory">
					<f:selectItem itemLabel="-select-"/>
					<f:selectItems value="#{requestProductBean.subCategoryList}"/>
					</h:selectOneMenu>
				</h:panelGrid>
			</c:if>
		</h:panelGrid>
		<br><br>
		<c:if test="${requestProductBean.subCategoryId ne null}">
		<h:panelGrid columns="2">
			<h:outputText value="#{lable.Description1}" styleClass="label"></h:outputText>
			<h:inputTextarea value="#{requestProductBean.description}" styleClass="box" required="true" requiredMessage="Description Required" validatorMessage="Description should have atleast 50 characters at max 200">
			<f:validateLength maximum="200" minimum="10"></f:validateLength>
			</h:inputTextarea>				
		</h:panelGrid><br>
		<h:commandButton value="#{lable.Request}" action="#{requestProductBean.requestProduct}" styleClass="sub"></h:commandButton>
		</c:if>	
		<br><br>
		
		<h:outputLabel value="#{requestProductBean.succesMessage}" styleClass="successMsg"></h:outputLabel>
		<h:outputLabel value="#{requestProductBean.errorMessage}" styleClass="errorMsg"></h:outputLabel>
		<br>
		<h:messages styleClass="errorMsg"></h:messages>
		</center>
		<br>
		<hr>

	</div>
</div>
</h:form>
<!-- footer -->
<jsp:include page="../footer.jsp"></jsp:include>

</f:view>
</body>
</html>