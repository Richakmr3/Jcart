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
<title>JCart - Edit Profile</title>
<link href="../../page-resources/styles/style.css" media="screen"
	rel="stylesheet">

<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang" />



</head>
<body>
	<%
		if (session.getAttribute("userId") == null
				|| (session.getAttribute("role").toString().charAt(0) != 'R' && session
						.getAttribute("role").toString().charAt(0) != 'U')) {
			response.sendRedirect("../../faces/pages/errorPage.jsp");
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

							<h:panelGrid border="1" columns="2">
								<h:outputText value="#{lable.First_Name}"></h:outputText>
								<h:inputText value="#{editProfileBean.firstName}"
									disabled="#{editProfileBean.disable}" id="fnam" required="true"
									requiredMessage="Please enter first name"
									validatorMessage="Exceeded maximum length for first name">
									<f:validateLength maximum="100" />
								</h:inputText>

								<h:outputText value="#{lable.Last_Name}"></h:outputText>
								<h:inputText value="#{editProfileBean.lastName}"
									disabled="#{editProfileBean.disable}" id="lnam" required="true"
									requiredMessage="Please enter last name"
									validatorMessage="Exceeded maximum length for last name">
									<f:validateLength maximum="100" />
								</h:inputText>

								<h:outputText value="#{lable.Phone_no}"></h:outputText>
								<h:inputText value="#{editProfileBean.phoneNo}"
									disabled="#{editProfileBean.disable}" id="phno" required="true"
									requiredMessage="Please enter phone number"
									validatorMessage="Length should be atleast 10 digits">
									<f:validateLength minimum="10"></f:validateLength>
								</h:inputText>

								<h:outputText value="#{lable.DOB}"></h:outputText>
								<h:inputText value="#{editProfileBean.dob}"
									disabled="#{editProfileBean.disable}" id="dob" required="true"
									requiredMessage="Please enter DOB">
									<f:convertDateTime pattern="dd-MMM-yyyy" type="date" />
								</h:inputText>
							</h:panelGrid>

							<br>
							<br>

							<h:commandLink action="#{editProfileBean.changeAddressFlag}"
								value="#{lable.Manage_Addresses}">
								<br>
							</h:commandLink>
							<c:if test="${editProfileBean.flag== 1}">
								<br>
								<h:panelGrid border="1" columns="2">
									<h:outputText value="#{lable.Permanent_Address}"></h:outputText>
									<h:inputTextarea value="#{editProfileBean.permanentAddress}"
										disabled="#{editProfileBean.disable}" id="pa" required="true"
										requiredMessage="Please enter permanent address"></h:inputTextarea>

									<!-- If it ia retailer then don,t render it using JSTL and session -->
									<h:outputText value="#{lable.Shipment_Address}"></h:outputText>
									<h:inputTextarea value="#{editProfileBean.shipmentAddress}"
										disabled="#{editProfileBean.disable}" id="sa" required="true"
										requiredMessage="Please enter shipment address"></h:inputTextarea>
								</h:panelGrid>
							</c:if>

							<br>
							<h:commandButton styleClass="btn" value="#{lable.Update}"
								disabled="#{editProfileBean.disable}"
								action="#{editProfileBean.updateProfile}"></h:commandButton>
							<h:commandButton styleClass="btn" value="#{lable.Edit}"
								action="#{editProfileBean.editProfile}"></h:commandButton>

							<br>
							<h:outputText rendered="#{editProfileBean.message ne null}"
								value="#{editProfileBean.message}" style="color:green"></h:outputText>
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