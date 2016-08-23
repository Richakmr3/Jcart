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
<title>JCart - Message Sender</title>
<link href="../../page-resources/styles/style.css" media="screen"
	rel="stylesheet">
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
	<div id="container"><h:form prependId="false">

<div id="menu1">
			<jsp:include page="AdminLeftToc.jsp"></jsp:include>
		</div>
		<!-- Right navigation pane links -->
		<div id="menu2">
			<jsp:include page="AdminRightToc.jsp"></jsp:include>
		</div>
		<div id="page">
		<center>
		
		<h:panelGrid columns="2">
		<h:outputText value="#{lable.Enter_the_userId}"></h:outputText>
		<h:inputText value="#{viewOverAllFeedbackBean.userId}" required="true" requiredMessage="User Id is Mandatory"></h:inputText>
		</h:panelGrid>
		
		<h:commandButton action="#{viewOverAllFeedbackBean.getFeedbackComments}" value="#{lable.Get_Comments }" styleClass="btn"></h:commandButton>
	
		<br><br>
		<h:dataTable value="#{viewOverAllFeedbackBean.list}" var="item" styleClass="row2,row1" rendered="#{not empty viewOverAllFeedbackBean.list}">
			<h:column>
				<f:facet name="header">
				<h:outputText value="#{lable.FeedBackId}"></h:outputText>
				</f:facet>	
				<h:outputText value="#{item.feedbackId}"></h:outputText>
			</h:column>
			
			<h:column>
				<f:facet name="header">
				<h:outputText value="#{lable.Feedback_Comment}"></h:outputText>
				</f:facet>	
				<h:outputText value="#{item.feedbackComment}"></h:outputText>
			</h:column>
		
			<h:column>
				<f:facet name="header">
				<h:outputText value="#{lable.Rating}"></h:outputText>
				</f:facet>	
				<h:outputText value="#{item.rating}"></h:outputText>
			</h:column>
		
		</h:dataTable>
		
		<h:outputText value="#{viewOverAllFeedbackBean.message}" styleClass="successMessage"></h:outputText>
		<h:messages></h:messages>
		
		</center>
		</div>
		
	</h:form></div>
	</center>

	<!-- Footer -->

	<jsp:include page="../footer.jsp"></jsp:include>
</f:view>
</body>
</html>