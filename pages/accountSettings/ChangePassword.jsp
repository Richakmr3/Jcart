<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>JCart - Change Password</title>
<link href="../../page-resources/styles/style.css" media="screen"
	rel="stylesheet">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>	
	
	
	
	
</head>
<body>
<%
	if(session.getAttribute("userId")==null || (session.getAttribute("role").toString().charAt(0)!='R' && session.getAttribute("role").toString().charAt(0)!='U'))
	{
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
			
					

<h:panelGrid border="1" columns="2" >
		
		<h:outputText value="#{lable.Old_Password}"></h:outputText>
			<h:inputSecret value="#{changePasswordBean.password}" id="pass" required="true" requiredMessage="Old Password is a Mandatory Field"></h:inputSecret>
			<h:outputText value="#{lable.New_Password}" ></h:outputText>
			<h:inputSecret value="#{changePasswordBean.newPassword}" id="npass" required="true" requiredMessage="New Password is a Mandatory Field"></h:inputSecret>
			<h:outputText value="#{lable.Confirm_New_Password}"></h:outputText>
			<h:inputSecret value="#{changePasswordBean.newpassword2}" id="cpass" required="true" requiredMessage="Confirm Password is a Mandatory Field"></h:inputSecret>
			<h:outputText value="#{lable.Security_Question}"></h:outputText>
			<h:outputText value="#{changePasswordBean.securityQuestion}" ></h:outputText>
			<h:outputText value="#{lable.Answer}"></h:outputText>
			<h:inputText value="#{changePasswordBean.answer}"  id="ans" required="true" requiredMessage="Answer is a Mandatory Field"></h:inputText>
		</h:panelGrid>	
		<h:commandButton styleClass="btn" value="#{lable.Change_Password}" action="#{changePasswordBean.changePassword}"></h:commandButton>
		
		
		<br>
		<h:outputText value="#{changePasswordBean.message}" style="color:#{changePasswordBean.style1};"></h:outputText><br>
		<h:messages style="color:red"></h:messages>






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