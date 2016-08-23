<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link href="../page-resources/styles/style.css" media="screen"
	rel="stylesheet">
<script type="text/javascript">
	function disableBackButton() {
		location.replace("../pages/logout.jsp");
	}
</script>
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body onunload="disableBackButton()">

<f:view>
	<div id="header">
	<div id="logo"><img src="../page-resources/images/jclogo.png"
		alt="not included"></div>
	</div>
	<center>
	
			<div id="container">
				
			
		


				<!-- Main body page for displaying details -->
				<div id="page">
	
				<h2><h:outputText value="#{lable.Access_is_denied}"></h:outputText><br><h:outputText value="#{lable.Please_Login_again }"></h:outputText>..</h2>	
					<h:outputLink value="../../faces/pages/logout.jsp"><h:outputText value="#{lable.Login}"></h:outputText></h:outputLink>
				</div>
			
			
				</div>
	
				
		</center>
		

	<jsp:include page="footer.jsp"></jsp:include>
</f:view>
</body>
</html>