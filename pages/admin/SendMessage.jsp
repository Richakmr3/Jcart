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
		<center><h:panelGrid border="1" columns="2">
			<h:outputText value="#{lable.Reciever_ID }"></h:outputText>
			<h:selectOneMenu value="#{sendMessageBean.recieverId}" required="true"
				requiredMessage="Please select a reciever Id">
				<f:selectItem itemLabel="--Select--" />
				<f:selectItems value="#{sendMessageBean.empIds}" />
			</h:selectOneMenu>
			<h:outputText value="#{lable.Message_Text }"></h:outputText>
			<h:inputTextarea value="#{sendMessageBean.message}" required="true"
				requiredMessage="Please enter some text as message"></h:inputTextarea>
		</h:panelGrid> <br>
		<br>
		<h:commandButton styleClass="btn" value="#{lable.Send_Message }"
			action="#{sendMessageBean.sendMessage}"></h:commandButton>
			<br><br>
			<h:outputText value="#{sendMessageBean.errMessage}" styleClass="successMessage"></h:outputText>
			<h:messages styleClass="errorMsg"></h:messages>
			</center>
		</div>
		

	</h:form></div>
	</center>

	<!-- Footer -->

	<jsp:include page="../footer.jsp"></jsp:include>
</f:view>
</body>
</html>