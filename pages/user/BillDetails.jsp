<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Bill Details</title>
<link href="../../pages-resources/styles/style.css" media="screen"
	rel="stylesheet">




<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body>
<%
	if(session.getAttribute("userId")==null || session.getAttribute("role").toString().charAt(0)!='U'){
		response.sendRedirect("../errorPage.jsp");
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
	<h:form>
	<div id="page">
			

		<c:if test="${empty billingBean.billDetails}">
		<br><br><br><br><br>
		<h3><h:outputText value="#{lable.Select_Items_From_Cart}" styleClass="errorMsg"></h:outputText></h3>
		<br><br>
		<h:commandLink value="Back to Cart" action="viewCart.jsp"></h:commandLink>
		</c:if>
			
	<c:if test="${not empty billingBean.billDetails}">
		<h:dataTable border="1" value="#{billingBean.billDetails}" var="bill">
			<h:column id="column1">
				<f:facet name="header">
					<h:outputText value="#{lable.productId}"></h:outputText>
				</f:facet>
				<h:outputText value="#{bill.productId}"></h:outputText>
			</h:column>
			<h:column id="column2">
				<f:facet name="header">
					<h:outputText value="#{lable.product_name}"></h:outputText>
				</f:facet>
				<h:outputText value="#{bill.productName}"></h:outputText>
			</h:column>
			<h:column id="column3">
				<f:facet name="header">
					<h:outputText value="#{lable.price}"></h:outputText>
				</f:facet>
				<h:outputText value="#{bill.price}"></h:outputText>
			</h:column>
			<h:column id="column4">
				<f:facet name="header">
					<h:outputText value="#{lable.Discounted_Price}"></h:outputText>
				</f:facet>
				<h:outputText value="#{bill.discountedPrice}"></h:outputText>
			</h:column>		
		</h:dataTable>
		<br><br>
		
	<p style="color: green;font-size: 20px">	Total Amount&nbsp;&nbsp;<h:outputText value="#{billingBean.discountedBill}"></h:outputText></p>
		<br><br><br>
	<h:commandButton value="#{lable.Continue}" styleClass="btn" action="RedeemPoints.jsp"></h:commandButton>	
	</c:if>
	

</div>
	</h:form></div>
	</center>

	<!-- Footer -->

	<jsp:include page="../footer.jsp"></jsp:include>
</f:view>
</body>
</html> 	