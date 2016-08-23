<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%><%@taglib
	uri="http://java.sun.com/jsf/html" prefix="h"%><%@taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Purchase Used Products</title>
<link href="../../page-resources/styles/style.css" media="screen" rel="stylesheet">
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
		<h:outputLabel value="#{lable.Purchase_Used_Products}" styleClass="pageTitle"></h:outputLabel>
		<br><hr>
		<c:if test="${param.showlist ne 'no'}">
		<h:outputLabel value="#{biddingBean.errorMsg}" styleClass="errorMsg"></h:outputLabel>
		<h:outputLabel value="#{biddingBean.successMsg}" styleClass="successMsg"></h:outputLabel>
		</c:if>
		<br><br>
		<h:dataTable border="1" value="#{biddingBean.usedProductList}" var="row" binding="#{biddingBean.selectUsedProductData}" rowClasses="row1,row2">
			
			<h:column id="column2">
				<f:facet name="header">
					<h:outputText value="#{lable.Product_Name}"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.productName}"></h:outputText>
			</h:column>
			<h:column id="column3">
				<f:facet name="header">
					<h:outputText value="#{lable.Price}"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.price}"><f:convertNumber type="currency" currencySymbol="Rs."/></h:outputText>
			</h:column>
			<c:if test="${biddingBean.makeBid eq false}">
			<h:column id="column4">
				<f:facet name="header">
					<h:outputText value="Bid Amount"></h:outputText>
				</f:facet>
				<h:inputText value="#{row.bidAmount}" size="8"></h:inputText>&nbsp;&nbsp;&nbsp;&nbsp;
				<h:commandLink value="save" action="#{biddingBean.saveBid}" styleClass="actionBtns"></h:commandLink>
			</h:column>
			</c:if>
			<c:if test="${biddingBean.makeBid eq true}">
			<h:column id="column5">
				<f:facet name="header">
					<h:outputText value="Action"></h:outputText>
				</f:facet>
				<h:commandLink value="make a bid" action="#{biddingBean.makeBidForProduct}" styleClass="actionBtns"></h:commandLink>
			</h:column>
			</c:if>
		</h:dataTable>
		
		
		
		</center>
		
	</div>
</div>
</h:form>
<!-- footer -->
<jsp:include page="../footer.jsp"></jsp:include>
</f:view>
</body>
</html>