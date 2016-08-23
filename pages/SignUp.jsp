<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link href="./page-resources/styles/style.css" media="screen"
	rel="stylesheet">
<link rel="stylesheet" href="../page-resources/styles/jquery-ui.css" media="screen"/>
 <script src="../page-resources/scripts/datetimepicker.js"></script>
 <f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
 </head>
<body>
<f:view>
<jsp:include page="header.jsp"></jsp:include>
<h:form prependId="false">
		<center>
		<div id="container">
		
		<!-- Left navigation pane links -->
			<c:if test="${sessionScope.role eq 'U'}">
				<div id="menu1">
					<jsp:include page="user/UserLeftToc.jsp"></jsp:include>
				</div>
			<!-- Right navigation pane links -->
				<div id="menu2">
					<jsp:include page="user/UserRightToc.jsp"></jsp:include>
				</div>
			</c:if>
			<c:if test="${sessionScope.userId==null}">
		 	<div id="menu1">
		 	<jsp:include page="VisitorLeftToc.jsp" ></jsp:include>
			
			</div>
		<!-- Right navigation pane links -->
		</c:if>
		
		<!-- Main body page for displaying details -->
			<div id="page">
			<center>
			<h1><h:outputText value="#{lable.User_Registration}"></h:outputText> </h1>
				<h:panelGrid columns="2">
				<h:outputLabel value="#{lable.You_are_a} : "><span class="redStar">*</span></h:outputLabel>
				<h:selectOneRadio value="#{registrationBean.userType}" required="true" requiredMessage="Please select your user type!">
						<f:selectItem itemLabel="#{lable.Single_User}" itemValue="U"/>
						<f:selectItem itemLabel="#{lable.Retailer}" itemValue="R"/>
				</h:selectOneRadio>
				
				<h:outputLabel value="#{lable.First_Name} : "><span class="redStar">*</span></h:outputLabel>
				<h:inputText value="#{registrationBean.firstName}" required="true" requiredMessage="Please enter your First Name" validatorMessage="First Name can be max 80 chars long">
					<f:validateLength maximum="80"></f:validateLength>
				</h:inputText>
				
				<h:outputLabel value="#{lable.Last_Name}  : "><span class="redStar">*</span></h:outputLabel>
				<h:inputText value="#{registrationBean.lastName}" required="true" requiredMessage="Please enter your Last Name." validatorMessage="Last Name can be max 80 chars long">
					<f:validateLength maximum="80"></f:validateLength>
				</h:inputText>
				
				<h:outputLabel value="#{lable.Date_Of_Birth}  : "><span class="redStar">*</span></h:outputLabel>
				<h:inputText id="dob" value="#{registrationBean.dob}" required="true" requiredMessage="Please enter your Date of Birth" onclick="javascript:NewCal('dob','ddmmmyyyy')">
				<f:converter converterId="calendarConv"/>
			</h:inputText>
				
				<h:outputLabel value="#{lable.Gender} : "><span class="redStar">*</span></h:outputLabel>
				<h:selectOneRadio value="#{registrationBean.gender}" required="true" requiredMessage="Please select a gender!">
						<f:selectItem itemLabel="#{lable.Male}" itemValue="M"/>
						<f:selectItem itemLabel="#{lable.Female}" itemValue="F"/>
				</h:selectOneRadio>
				
				<h:outputLabel value="#{lable.Password}  : "><span class="redStar">*</span></h:outputLabel>
				<h:inputSecret value="#{registrationBean.password}" required="true" requiredMessage="Please enter a password of your choice" >
				</h:inputSecret>
				
				<h:outputLabel value="#{lable.Confirm_Password}  : "><span class="redStar">*</span></h:outputLabel>
				<h:inputSecret value="#{registrationBean.confirmPassword}" required="true" requiredMessage="Please enter confirm password">
				</h:inputSecret>
				
				<h:outputLabel value="#{lable.Permanent_Address}  : "><span class="redStar">*</span></h:outputLabel>
				<h:inputTextarea value="#{registrationBean.permanentAddress}" required="true" requiredMessage="Please enter your address of correspondence.">
				</h:inputTextarea>
				
				<h:outputLabel value="#{lable.Shipment_Address}  : "><span class="redStar">*</span></h:outputLabel>
				<h:inputTextarea value="#{registrationBean.shipmentAddress}" required="true" requiredMessage="Please enter your address of correspondence.">
				</h:inputTextarea>
				
				<h:outputLabel value="#{lable.Phone_Number}  : "><span class="redStar">*</span></h:outputLabel>
				<h:inputText value="#{registrationBean.phoneNumber}" required="true" requiredMessage="Please enter your contact number." validatorMessage="Phone number should contain only 10 digits.">
					<f:validateLength maximum="10" minimum="10"></f:validateLength>
				</h:inputText>
				
				<h:outputLabel value="#{lable.Security_Question} : "><span class="redStar">*</span></h:outputLabel>
				<h:selectOneMenu value="#{registrationBean.securityQuestion}" required="true" requiredMessage="Please select a security question!" title="This helps you to reset your account settings in case you forgot your password.">
						<f:selectItems value="#{registrationBean.questions}"/>
				</h:selectOneMenu>
				
				<h:outputLabel value="#{lable.Security_Answer} : "><span class="redStar">*</span></h:outputLabel>
				<h:inputText value="#{registrationBean.securityAnswer}" required="true" requiredMessage="Please enter your First Name" title="Provide answer to the question selected above"></h:inputText>
				
				<h:outputLabel value="#{lable.Want_to_subscribe_for_our_Messaging_Service} : "><span class="redStar">*</span></h:outputLabel>
				<h:selectOneRadio value="#{registrationBean.messageService}" required="true" requiredMessage="Please select your user type!">
						<f:selectItem itemLabel="#{lable.Yes}" itemValue="Y"/>
						<f:selectItem itemLabel="#{lable.No}" itemValue="N"/>
				</h:selectOneRadio>
				<h:outputLabel></h:outputLabel>
				<h:commandButton value="#{lable.Sign_Up}" styleClass="sub" action="#{registrationBean.registerUser}"></h:commandButton>
				</h:panelGrid>
				<br>
				<h:messages></h:messages>
				<h:outputLabel value="#{registrationBean.message}" styleClass="successMsg"></h:outputLabel>
				<br>
				<br>
				<br>
				</center>
			</div>
		</div>
		</center>
</h:form>
<!-- Footer -->
		<jsp:include page="footer.jsp"></jsp:include>
</f:view>
</body>
</html>