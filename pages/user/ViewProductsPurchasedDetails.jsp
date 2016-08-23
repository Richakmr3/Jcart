<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - My Purchase</title>

<link href="../pages-resources/styles/style.css" media="screen"
	rel="stylesheet">
	<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body>
<%
	if(session.getAttribute("userId")==null || session.getAttribute("role").toString().charAt(0)!='U'){
		response.sendRedirect("../../faces/pages/errorPage.jsp");
	}
 %>
	<f:view>
	<jsp:include page="../header.jsp"></jsp:include>
	<!-- Search bar to be included in each page -->

	
		<center>
		<div id="container">
		<div id="menu1">
			<jsp:include page="UserLeftToc.jsp"></jsp:include>
		</div>
		<!-- Right navigation pane links -->
		<div id="menu2">
			<jsp:include page="UserRightToc.jsp"></jsp:include>
		</div>
		<div id="page">
				<H2 align="center"><h:outputText value="#{lable.View_My_Orders}"></h:outputText></H2>
<h:form>

		
	
		
	<c:if test="${ not empty viewMyOrdersBean.productList  }">
			<h3><h:outputText value="#{lable.Products_purchased}"></h:outputText>
		</h3>
			<h:dataTable value="#{viewMyOrdersBean.productList}" var="i1"
					rowClasses="row1,row2">
					<h:column id="column1">
						<f:facet name="header">
							<h:outputText value="#{lable.Product_Id}"></h:outputText>
						</f:facet>
						<h:commandLink value="#{i1.productId}" action="#{viewMyOrdersBean.getDetailsOfProduct}">
						<f:param value="#{i1.productId}" name="pid3"></f:param>
						</h:commandLink>
					</h:column>
					<h:column id="column2">
						<f:facet name="header">
							<h:outputText value="Variation Code"></h:outputText>
						</f:facet>
						<h:outputText value="#{i1.variationCode}"></h:outputText>
					</h:column>
					</h:dataTable>
		</c:if>
	
	<c:if test="${ viewMyOrdersBean.productDetails[4] !=null && not empty viewMyOrdersBean.productList }">
			<h3>
		<h:outputText value="#{lable.Products_Details}"></h:outputText></h3>
			<h:panelGrid columns="2">
			<h:outputText value="#{lable.productName}"></h:outputText>
			<h:outputText value="#{viewMyOrdersBean.productDetails[4]}"></h:outputText>
			<h:outputText value="#{lable.CompanyName }"></h:outputText>
			<h:outputText value="#{viewMyOrdersBean.productDetails[3]}"></h:outputText>
			<h:outputText value="#{lable.Category}"></h:outputText>
			<h:outputText value="#{viewMyOrdersBean.productDetails[1]}"></h:outputText>
			<h:outputText value="#{lable.SubCategory}"></h:outputText>
			<h:outputText value="#{viewMyOrdersBean.productDetails[2]}"></h:outputText>
			<h:outputText value="#{lable.SellerId}"></h:outputText>
			<h:outputText value="#{viewMyOrdersBean.productDetails[0]}"></h:outputText>
			</h:panelGrid>
		</c:if>
	
	
		</h:form>
		
		<br>
		<h:outputText value="#{viewMyOrdersBean.message}" style="color:green"></h:outputText>
		<h:messages style="color:red"></h:messages>
		<br>
		<h:outputLink value="ViewMyOrders.jsp">Back</h:outputLink>
		</div>
		</div>
		</center>
		<jsp:include page="../footer.jsp"/>
</f:view>
</body>
</html>