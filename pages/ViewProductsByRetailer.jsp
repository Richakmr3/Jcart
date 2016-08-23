<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - My Products</title>
<link href="../page-resources/styles/style.css" media="screen"
	rel="stylesheet">
	<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
	
</head>
<body>
<f:view>
<jsp:include page="header.jsp"></jsp:include>
		
<h:form prependId="false">
<center>
		<div id="container">
		
			<!-- Left navigation pane links -->
			<c:if test="${sessionScope.role eq 'U'}">
				<div id="menu1">
					<jsp:include page="user/UserLeftToc.jsp"></jsp:include>
				</div>
			<!-- Right navigation pane links -->
				<div id="menu2">
					<jsp:include page="user/UserRightToc.jsp"></jsp:include>
				</div>
			</c:if>
			<c:if test="${sessionScope.userId==null}">
		 	<div id="menu1">
		 	<jsp:include page="VisitorLeftToc.jsp" ></jsp:include>
			
			</div>
		<!-- Right navigation pane links -->
		</c:if>
		<!-- Main body page for displaying details -->
		<div id="page">
	<h3><h:outputText value="#{lable.View_Retailer_Products}"></h:outputText> </h3><br>
<br>

	<h:panelGrid columns="3">
	<h:outputText value="#{lable.Retailer_ID}:"></h:outputText>
	<h:selectOneMenu id="ret" value="#{viewRetailerProductsBean.retailerId}" required="true" requiredMessage="Mandatory Field">
			<f:selectItem itemLabel="--#{lable.Select}--"/>
			<f:selectItems value="#{viewRetailerProductsBean.retailerList}"/>
	</h:selectOneMenu>
	<h:message for="ret" styleClass="errorMsg"></h:message>
	
	<h:outputText value="#{lable.Category} :"></h:outputText>
	<h:selectOneMenu id="cat" value="#{viewRetailerProductsBean.categoryId}" required="true" requiredMessage="Mandatory Field">
			<f:selectItem itemLabel="--#{lable.Select}--"/>
			<f:selectItems value="#{viewRetailerProductsBean.categoryList}"/>
	</h:selectOneMenu>
	<h:message for="cat" styleClass="errorMsg"></h:message>
	</h:panelGrid>
	<h:commandButton styleClass="btn" value="#{lable.View_Products}" action="#{viewRetailerProductsBean.getProductsByRetailer}" type="submit" ></h:commandButton>
	<c:if test="${not empty viewRetailerProductsBean.productList}">
	<h:dataTable border="1" value="#{viewRetailerProductsBean.productList}" var="item" rowClasses="row1,row2">
			<h:column id="id1">
				<f:facet name="header">
					<h:outputText value="#{lable.Product_ID}"></h:outputText>
				</f:facet>
				<h:outputText value="#{item.productId}"></h:outputText>
			</h:column>
			<h:column id="id2">
				<f:facet name="header">
					<h:outputText value="#{lable.Sub_Category}"></h:outputText>
				</f:facet>
				<h:outputText value="#{item.subcategory}"></h:outputText>
			</h:column>
			<h:column id="idcompany">
				<f:facet name="header">
					<h:outputText value="Comapany" rendered="#{item.category!='Books'}"></h:outputText>
					<h:outputText value="Author" rendered="#{item.category=='Books'}"></h:outputText>
				</f:facet>
				<h:outputText value="#{item.company}"></h:outputText>
				
			</h:column>
			<h:column id="id4">
				<f:facet name="header">
					<h:outputText value="#{lable.Price}"></h:outputText>
				</f:facet>
				<h:outputText value="#{item.price}">
				</h:outputText>
			</h:column>
			<h:column id="id6">
				<f:facet name="header">
					<h:outputText value="#{lable.Item}"></h:outputText>
				</f:facet>
				<h:graphicImage url="#{item.url}"
								styleClass="bsimg"></h:graphicImage>
				
			</h:column>
</h:dataTable>
</c:if>
<br>
	

<br><br>
	<h:outputText value="#{viewRetailerProductsBean.message}" styleClass="errorMsg"></h:outputText><br>
	</div>
	</div>
</center>
</h:form>
<jsp:include page="footer.jsp"></jsp:include>
</f:view>
</body>
</html>
