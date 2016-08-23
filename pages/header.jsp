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
<link href="../page-resources/styles/style.css" media="screen"
	rel="stylesheet">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang" />

</head>
<body>


	<div id="header">
		<h:form>
			<div id="logo">
				<img
					src="${pageContext.request.contextPath}/page-resources/images/jclogo.png"
					alt="Not Present">
			</div>
		</h:form>
		<div id="hlinks">
			<h:form prependId="false">
				<h:outputLink value="../../faces/pages/help/aboutus.jsp">
					<h:outputText value="#{lable.About_Us}"></h:outputText>
				</h:outputLink>&nbsp;|&nbsp;
			<h:outputLink value="../../faces/pages/help/contactus.jsp">
					<h:outputText value="#{lable.Contact_Us}"></h:outputText>
				</h:outputLink>&nbsp;|&nbsp;
			
			
			<c:set var="user" value="${sessionScope.userId}"></c:set>
				<c:set var="userRole" value="${sessionScope.role}"></c:set>

				<c:choose>
					<c:when test="${userRole eq 'U'}">
						<h:outputLink value="../../faces/pages/user/Home.jsp">
							<h:outputText value="#{lable.Home}"></h:outputText>
						</h:outputLink>&nbsp;|&nbsp;
			</c:when>
					<c:when test="${userRole eq 'R'}">
						<h:outputLink value="../../faces/pages/retailer/Home.jsp">
							<h:outputText value="#{lable.Home}"></h:outputText>
						</h:outputLink>&nbsp;|&nbsp;
			</c:when>
					<c:when test="${userRole eq 'A'}">
						<h:outputLink value="../../faces/pages/admin/Home.jsp">
							<h:outputText value="#{lable.Home}"></h:outputText>
						</h:outputLink>&nbsp;|&nbsp;
			</c:when>
					<c:otherwise>
						<h:outputLink value="../../faces/pages/searchPage.jsp">
							<h:outputText value="#{lable.Home}"></h:outputText>
						</h:outputLink>&nbsp;|&nbsp;
			</c:otherwise>
				</c:choose>
				<c:if test="${userRole!=null and userRole ne 'A'}">
					<h:outputLink
						value="../../faces/pages/accountSettings/EditProfile.jsp">
						<h:outputText value="#{lable.Account_Details }"></h:outputText>
					</h:outputLink>&nbsp;|&nbsp;
			</c:if>

				<c:if test="${userRole!=null}">
					<h:commandLink action="#{loginBean.doLogout}"
						value="#{lable.Logout}"></h:commandLink>&nbsp;|&nbsp;
			</c:if>

				<c:if test="${userRole==null}">
					<h:outputLink value="../../faces/pages/login.jsp">
						<h:outputText value="#{lable.Login}"></h:outputText>
					</h:outputLink>&nbsp;|&nbsp;
			<h:outputLink value="../../faces/pages/SignUp.jsp">
						<h:outputText value="#{lable.Sign_Up}"></h:outputText>
					</h:outputLink>&nbsp;|&nbsp;
			</c:if>
				<c:if test="${userRole eq 'U'}">
					<h:outputLink value="../../faces/pages/user/viewWishList.jsp">
						<h:outputText value="#{lable.Wish_List }"></h:outputText>
					</h:outputLink>&nbsp;|&nbsp;
			</c:if>
				<c:if test="${user==null}">
					<h:outputLink value="../faces/pages/help/overallfeedback.jsp">
						<h:outputText value="#{lable.Feedback}"></h:outputText>
					</h:outputLink>
				</c:if>
				<c:if test="${userRole eq 'U'}">
					<h:outputLink value="../../faces/pages/help/overallfeedback.jsp">
						<h:outputText value="#{lable.Feedback}"></h:outputText>
					</h:outputLink>





				</c:if>
			</h:form>

		</div>
		
		
	</div>


</body>
</html>