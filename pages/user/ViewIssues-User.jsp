<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Issues</title>

<link href="../../page-resources/styles/style.css" media="screen"
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
				<H2 align="center"><h:outputText value="#{lable.View_Issues_Raised_By_Me}"></h:outputText></H2>
<h:form>

		</h:form>
		<br>
		
		<h:messages style="color:red"></h:messages>
		</div>
		</div>
		</center>
		<jsp:include page="../footer.jsp"/>
</f:view>
</body>
</html>