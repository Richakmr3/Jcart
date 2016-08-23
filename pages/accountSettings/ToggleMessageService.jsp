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
<title>JCart - Toggle Message Service</title>
<link href="../../page-resources/styles/style.css" media="screen"
	rel="stylesheet">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang" />
</head>
<body>
	<%
		if (session.getAttribute("userId") == null
				|| (session.getAttribute("role").toString().charAt(0) != 'R' && session
						.getAttribute("role").toString().charAt(0) != 'U')) {
			response.sendRedirect("../errorPage.jsp");
		}
	%>
	<f:view>
		<jsp:include page="../header.jsp"></jsp:include>
		<!-- Search bar to be included in each page -->
		<center>
			<div id="container">
				<c:if test="${sessionScope.role eq 'R'}">
					<div id="menu1">
						<h:form prependId="false">
							<jsp:include page="../retailer/RetailerLeftToc.jsp"></jsp:include>
						</h:form>
					</div>
					<!-- Right navigation pane links -->
					<div id="menu2">
						<jsp:include page="../retailer/RetailerRightToc.jsp"></jsp:include>
					</div>

				</c:if>
				<c:if test="${sessionScope.role eq 'U'}">
					<div id="menu1">
						<jsp:include page="../user/UserLeftToc.jsp"></jsp:include>
					</div>
					<!-- Right navigation pane links -->
					<div id="menu2">
						<jsp:include page="../user/UserRightToc.jsp"></jsp:include>
					</div>
				</c:if>

				<h:form prependId="false">


					<div id="page">
						<center>
							<c:if
								test="${editProfileBean.messageService eq editProfileBean.yes}">
								<h:outputText value="#{lable.msg}"></h:outputText>
								<br>
								<br>
							</c:if>
							<c:if
								test="${editProfileBean.messageService eq editProfileBean.no}">
								<h:outputText value="#{lable.new_msg}"></h:outputText>
								<br>
								<br>
							</c:if>
							<br>
							<br>
							<h:commandButton styleClass="btn"
								value="#{lable.Toggle_Message_Settings}"
								action="#{editProfileBean.toggleMessageSettings }">
							</h:commandButton>
							<br> <br>
							<h:outputText value="#{editProfileBean.msMesssage }"
								styleClass="successMessage"></h:outputText>
							<br><br>
							<h:outputText value="#{editProfileBean.decMessage }"
								styleClass="successMessage"></h:outputText>
							<br> <br>








						</center>
					</div>


				</h:form>
			</div>
		</center>

		<!-- Footer -->

		<jsp:include page="../footer.jsp"></jsp:include>
	</f:view>
</body>
</html>