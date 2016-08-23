<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>JCart - View Product Auctions</title>
<link href="../../resources/styles/style.css" media="screen" rel="stylesheet">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body>
<%
	if(session.getAttribute("userId")==null || session.getAttribute("role").toString().charAt(0)!='A'){
		response.sendRedirect("../faces/errorPage.jsp");
	}
 %>
	<f:view>
		<jsp:include page="../header.jsp"></jsp:include>
		<!-- Search bar to be included in each page -->
		<center>
		<div id="container">
		
			<!-- Left navigation pane links -->
		 <div id="menu1">
			<jsp:include page="AdminLeftToc.jsp"></jsp:include>
		</div>
		<!-- Right navigation pane links -->
		<div id="menu2">
			<jsp:include page="AdminRightToc.jsp"></jsp:include>
		</div>
		<!-- Main body page for displaying details -->
			<div id="page">
			<center>
			
				<h1><h:outputText value="#{lable.View_Product_Auctions}"></h:outputText></h1>
				<h:form>
				<h:panelGrid border="1" columns="2">
					<h:selectOneRadio onclick="submit();" 
					valueChangeListener="#{viewAuctionsBean.getAuctions}">
						<f:selectItem itemLabel="Open Auctions" itemValue="1"/>
						<f:selectItem itemLabel="Recently Settled Auctions" itemValue="2"/>
					</h:selectOneRadio>
				</h:panelGrid>
				<c:if test="${viewAuctionsBean.choice==2}">
				<h:panelGrid border="1" columns="2">
					<h:outputText value="#{lable.From_Date}"></h:outputText>
					<h:inputText value="#{viewAuctionsBean.fromDate}" required="true" requiredMessage="Please Enter From date">
						<f:converter converterId="calendarConv"/>
					</h:inputText>
					<h:outputText value="#{lable.To_Date }"></h:outputText>
					<h:inputText value="#{viewAuctionsBean.toDate}" required="true" requiredMessage="Please Enter To date">
						<f:converter converterId="calendarConv"/>
					</h:inputText>
				</h:panelGrid><br>
				<h:commandButton styleClass="btn" value="Get Auctions" 
				action="#{viewAuctionsBean.getAuctionsForDateRange}"></h:commandButton>  
				
				</c:if>
				<br><br>
				<c:if test="${not empty viewAuctionsBean.auctionList}">
				<h:commandLink action="#{viewAuctionsBean.sortByPopularity}" 
				value="Sort By Popularity">
				</h:commandLink>
				<br><br>
				<h:dataTable value="#{viewAuctionsBean.auctionList}" var="entry"
					rowClasses="row1,row2">
					<h:column id="column1">
						<f:facet name="header">
							<h:outputText value="ProductId"></h:outputText>
						</f:facet>
						<h:outputText value="#{entry.productId}"></h:outputText>
						</h:column>
					<h:column id="column2">
						<f:facet name="header">
							<h:outputText value="Seller Id"></h:outputText>
						</f:facet>
							<h:outputText value="#{entry.sellerId}"></h:outputText>
					</h:column>
					<h:column id="column3">
						<f:facet name="header">
							<h:outputText value="Category (SubCategory)"></h:outputText>
						</f:facet>
							<h:outputText value="#{entry.categoryName}"></h:outputText>
							
							<h:outputText value="("></h:outputText>
							<h:outputText value="#{entry.subCategoryName}"></h:outputText>
							<h:outputText value=")" id="t"></h:outputText>
							</h:column>
					
					<h:column id="column5">
						<f:facet name="header">
							<h:outputText value="Auction Period"></h:outputText>
						</f:facet>
						<h:outputText value="#{entry.bidStartDate}">
						<f:converter converterId="calendarConv"/>
						</h:outputText>
						<h:outputText value=" - "/>
						<h:outputText value="#{entry.bidCloseDate}">
						<f:converter converterId="calendarConv"/>
						</h:outputText>
					</h:column>
					<h:column id="column6">
						<f:facet name="header">
							<h:outputText value="Highest Bid"></h:outputText>
						</f:facet>
						
						<h:outputText value="#{entry.basePrice}">
						<f:convertNumber type="currency" currencySymbol="Rs."/>
						</h:outputText>
						<br>
						<h:outputText value="By: "></h:outputText>
						<h:outputText value="#{entry.lastBidderId}"/>
						
						
					</h:column>
					<h:column id="column8">
						<f:facet name="header">
							<h:outputText value="No.Of Bids"></h:outputText>
						</f:facet>
							<h:outputText value="#{entry.numberOfBids}"></h:outputText>
					</h:column>
					
				</h:dataTable>
			</c:if><br></h:form>
				
				<h:messages></h:messages>	
				<h:outputText value="#{viewAuctionsBean.message}" styleClass="errorMsg"/>
		
			</center>
			</div>
		</div>
		</center>
		<!-- Footer -->
		<jsp:include page="../footer.jsp"></jsp:include>
	</f:view>
</body>
</html>