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
<title>JCart - Customer Experience Report</title>
<link href="../../page-resources/styles/style.css" media="screen" rel="stylesheet">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body>
<%
	if(session.getAttribute("userId")==null || session.getAttribute("role").toString().charAt(0)!='A'){
		response.sendRedirect("../../faces/pages/errorPage.jsp");
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
			<h:form>
				<h1><h:outputText value="#{lable.Customer_Experience_Report}"></h:outputText></h1>
				<h:panelGrid border="0" columns="2">
				<h:outputText value="#{lable.Customer_Experience_For }"/>
					<h:selectOneRadio onclick="submit();" 
					value="#{customerExperienceReportBean.choice}"
					valueChangeListener="#{customerExperienceReportBean.getReportData}">
						<f:selectItem itemLabel="Products" itemValue="1"/>
						<f:selectItem itemLabel="Retailers" itemValue="2"/>
					</h:selectOneRadio>
				</h:panelGrid>
				
				<h:panelGrid columns="2" rendered="#{customerExperienceReportBean.choice eq 2}">
				
				<h:outputText value="#{lable.Select_Retailer_Id }" ></h:outputText>
					<h:selectOneMenu  valueChangeListener="#{customerExperienceReportBean.getRetailerReportData}" 	
					onchange="submit();">
						<f:selectItem itemLabel="--Select--" itemValue="0"/>
						<f:selectItems value="#{customerExperienceReportBean.retailerIdList}"/>
					</h:selectOneMenu>
				</h:panelGrid>
				</h:form>
				
				<c:if test="${not empty customerExperienceReportBean.entries}">
				<h:dataTable value="#{customerExperienceReportBean.entries}" var="entry"
					rowClasses="row1,row2">
					<h:column id="column1">
						<f:facet name="header">
							<h:outputText value="#{lable.Experience}"></h:outputText>
						</f:facet>
						<h:outputText value="#{entry.key}"></h:outputText>
					</h:column>
					<h:column id="column2">
						<f:facet name="header">
							<h:outputText value="#{lable.Percentage }"></h:outputText>
						</f:facet>
						
							<h:inputText size="#{entry.value}" readonly="true" maxlength="1" value="#{entry.value}" styleClass="graph" rendered="#{entry.value ge 75}">
						
							</h:inputText>
							<h:inputText size="#{entry.value}" readonly="true" maxlength="1" value="#{entry.value}" styleClass="graph1" rendered="#{entry.value ge 50 and entry.value lt 75}">
							
							</h:inputText>
							<h:inputText size="#{entry.value}" readonly="true" maxlength="1" value="#{entry.value}" styleClass="graph2"  rendered="#{entry.value ge 25 and entry.value lt 50}">
							
							</h:inputText>
							<h:inputText size="#{entry.value}" readonly="true" maxlength="1" value="#{entry.value}" styleClass="graph3"  rendered="#{entry.value ge 1 and entry.value lt 25}">
						
							</h:inputText>
							<h:inputText size="1" readonly="true" maxlength="1" value="#{entry.value}" styleClass="graph3"  rendered="#{entry.value lt 1}">
							
							</h:inputText>
							</h:column>
				</h:dataTable>
				</c:if><br>
				<h:outputText value="#{customerExperienceReportBean.message}" styleClass="errorMsg"/>
				<br>
				<br>
		
			</center>
			</div>
		</div>
		</center>
		<!-- Footer -->
		<jsp:include page="../footer.jsp"></jsp:include>
	</f:view>
</body>
</html>