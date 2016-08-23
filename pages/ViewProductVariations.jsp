<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Product Variations</title>
<link href="../page-resources/styles/style.css" media="screen"
	rel="stylesheet">
</head>
<body>


	

	<f:view>
		<jsp:include page="header.jsp"></jsp:include>
		<!-- Search bar to be included in each page -->
		
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
			<center>
			<h3>Products Details</h3>
			<!-- Display variations here -->
			<h:panelGroup id="variationsList" rendered="#{not empty cartBean.variations}">
			 <h:dataTable value="#{cartBean.variations}" var="item1" styleClass="productSearchTable">
             <h:column>
             <h:graphicImage url="#{item1.imageUrl}" styleClass="bsimgnew"></h:graphicImage>
             </h:column>
             <h:column>
             <h:outputLabel value="Product id   : #{item1.productId}" ></h:outputLabel><br>	
			 <h:outputLabel value="Color        :#{item1.color}"  rendered="#{item1.color != 'NA'}"></h:outputLabel><br>
			 <h:outputLabel value="Fit          :#{item1.fit}"  rendered="#{item1.fit != 'NA'}"></h:outputLabel><br>
			 <h:outputLabel value="Stock       :#{item1.stock}" ></h:outputLabel><br>
			 <h:selectBooleanCheckbox value="#{item1.selected}" rendered="#{sessionScope.role!=null && sessionScope.role=='U'}"></h:selectBooleanCheckbox>
			 <h:outputLabel value="Select" rendered="#{sessionScope.role!=null && sessionScope.role=='U'}"></h:outputLabel>   			         
             </h:column>
             </h:dataTable>   
			 </h:panelGroup>
			<!-- Displaying product details -->
			<c:if test="${not empty cartBean.variations}">
			<c:set value="productDetails1" var="productDetails" scope="request"></c:set>
			</c:if>
			<c:if test="${empty cartBean.variations}">
			<c:set value="productDetails2" var="productDetails" scope="request"></c:set>
			</c:if>
			<h:panelGroup styleClass="#{requestScope.productDetails}">
			<c:if test="${ cartBean.productDetails[4] !=null}">
			
			<center>
			<h:panelGrid columns="2">
			<h:outputText value="Product Name"></h:outputText>
			<h:outputText value="#{cartBean.productDetails[4]}"></h:outputText>
			<h:outputText value="Company Name"></h:outputText>
			<h:outputText value="#{cartBean.productDetails[3]}"></h:outputText>
			<h:outputText value="Category"></h:outputText>
			<h:outputText value="#{cartBean.productDetails[1]}"></h:outputText>
			<h:outputText value="Sub Category"></h:outputText>
			<h:outputText value="#{cartBean.productDetails[2]}"></h:outputText>
			<h:outputText value="Seller Id"></h:outputText>
			<h:outputText value="#{cartBean.productDetails[0]}"></h:outputText>
			</h:panelGrid>
			</center>
		</c:if>		
		
		<h:commandButton value="Add to Cart" action="#{cartBean.addToCart}" styleClass="btn" rendered="#{sessionScope.role!=null && sessionScope.role=='U'}"></h:commandButton>
				</h:panelGroup>
				</center>				
			</div>
		</div>
		</center>
		</h:form>
		<!-- Footer -->
		<jsp:include page="footer.jsp"></jsp:include>
	</f:view>
</body>
</html>