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
<title>JCart - Valuable Customer Report</title>
<link href="../resources/styles/style.css" media="screen"
	rel="stylesheet">
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
	<center><h:form>

		<h1><h:outputText value="#{lable.Most_Valuable_Customers}"></h:outputText></h1>
		<h:panelGrid columns="2">
		<h:outputText value="#{lable.Specify_number_of_records}"/>
		<h:selectOneMenu value="#{valuableCustomerReportBean.numberOfRecords}"
		valueChangeListener="#{valuableCustomerReportBean.getReport}"
		onchange="submit()" required="true" requiredMessage="Please select a valid value">
		<f:selectItem itemLabel="select" />
		<f:selectItem itemLabel="3" itemValue="3"/>
		<f:selectItem itemLabel="5" itemValue="5"/>
		<f:selectItem itemLabel="10" itemValue="10"/>
		</h:selectOneMenu>
		</h:panelGrid>
		<br>
		<c:if test="${not empty valuableCustomerReportBean.paginationList}">
			<h:dataTable value="#{valuableCustomerReportBean.paginationList}"
				var="entries" rowClasses="row1,row2"
				binding="#{valuableCustomerReportBean.datatable}">
				<h:column id="column1">
					<f:facet name="header">
						<h:outputText value="#{lable.Customer_Id}"></h:outputText>
					</f:facet>
					<h:commandLink
						action="#{valuableCustomerReportBean.getProductsPurchased}">
						<h:outputText value="#{entries.userId}"></h:outputText>
					</h:commandLink>
				</h:column>
				<h:column id="column2">
					<f:facet name="header">
						<h:outputText value="Amount Of Purchase">
						</h:outputText>
					</f:facet>
					<h:outputText value="#{entries.amount}">
					<f:convertNumber currencySymbol="Rs." type="currency"/>
					</h:outputText>
				</h:column>
				
			</h:dataTable>
			<h:panelGroup>
			<h:commandLink value="#{lable.Next}" 
				action="#{valuableCustomerReportBean.getNext}"
				rendered="#{(valuableCustomerReportBean.totalPages > 1) and 
			(valuableCustomerReportBean.counter/valuableCustomerReportBean.numberOfRecords +1 < valuableCustomerReportBean.totalPages)}"/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<h:commandLink value="#{lable.Previous}" 
		action="#{valuableCustomerReportBean.getPrevious}"
			rendered="#{(valuableCustomerReportBean.totalPages > 1) and 
			(valuableCustomerReportBean.counter/valuableCustomerReportBean.numberOfRecords +1 >1)}">
		</h:commandLink>
		</h:panelGroup>
			<br>
			<c:if test="${not empty valuableCustomerReportBean.selectedTO.list}">
				<h3>Products Purchased By
				<h:outputText value="#{valuableCustomerReportBean.selectedTO.userId}"/> </h3>
				<h:dataTable value="#{valuableCustomerReportBean.selectedTO.list}"
					var="entry" rowClasses="row1,row2">
					<h:column id="column1">
						<f:facet name="header">
							<h:outputText value="#{lable.Product_Name}"></h:outputText>
						</f:facet>
						<h:outputText value="#{entry.productName}"></h:outputText>
					</h:column>
					<h:column id="column2">
						<f:facet name="header">
							<h:outputText value="#{lable.Product_Brand}"></h:outputText>
						</f:facet>
						<h:outputText value="#{entry.company}"></h:outputText>
					</h:column>
					<h:column id="column3">
						<f:facet name="header">
							<h:outputText value="#{lable.SubCategory}"></h:outputText>
						</f:facet>
						<h:outputText value="#{entry.subcategory}"></h:outputText>
					</h:column>
					<h:column id="column4">
						<f:facet name="header">
							<h:outputText value="#{lable.No_of_Products}"></h:outputText>
						</f:facet>
						<h:outputText value="#{entry.numOfProducts}"></h:outputText>
					</h:column>
					<h:column id="column5">
						<f:facet name="header">
							<h:outputText value="#{lable.Purchase_Date}"></h:outputText>
						</f:facet>
						<h:outputText value="#{entry.dateOfPurchase}">
							<f:converter converterId="calendarConv" />
						</h:outputText>
					</h:column>

				</h:dataTable>
			</c:if>
		</c:if><br>
		<h:outputText value="#{iconicBrandReportBean.message}" styleClass="errorMsg"/>
	</h:form>
	<h:messages></h:messages>
	</center>
	</div>
	</div>
	</center>
	<!-- Footer -->
	<jsp:include page="../footer.jsp"></jsp:include>
</f:view>
</body>
</html>