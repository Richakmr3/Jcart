<%@page import="com.infy.jcart.resources.Factory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="javax.faces.context.FacesContext"%>
<%@page import="javax.faces.context.ExternalContext"%>
<%@page import="java.util.Map"%><html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Logout</title>
<link
	href="${pageContext.request.contextPath}/page-resources/styles/style.css"
	media="screen" rel="stylesheet">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang" />
</head>

<body>

	<f:view>
		<%
			FacesContext context = FacesContext.getCurrentInstance();
				ExternalContext etx = context.getExternalContext();
				Map<String, Object> map = etx.getSessionMap();
				if (session.getAttribute("role") != null) {
					map.put("loginBean", Factory.createLoginBean());

					session.setAttribute("userId", null);
					session.setAttribute("role", null);
					session.setAttribute("status", null);
				}

		%>

		<jsp:include page="header.jsp"></jsp:include>

		<h:form prependId="false">

			<center>
				<div id="container">
					<div id="menu1"><jsp:include page="/pages/VisitorLeftToc.jsp"></jsp:include>
					</div>
					<div id="page">

						<h1>
							<h:outputText value="#{loginBean.message}"
								styleClass="#{loginBean.styleClass}"></h:outputText>
							<br>
							<h:outputText value="#{lable.You_have_Logged_out_successfully }"
								styleClass="successMsg"></h:outputText>
							<br>

						</h1>



						<h:outputText value="#{loginBean.message}"
							styleClass="#{loginBean.styleClass}"></h:outputText>
						<br>

						<h:outputLink value="../../faces/pages/login.jsp">
							<h:outputText value="#{lable.Click_To_Login}"></h:outputText>
						</h:outputLink>
					</div>

				</div>


			</center>
		</h:form>
		<jsp:include page="/pages/footer.jsp"></jsp:include>
	</f:view>
</body>
</html>