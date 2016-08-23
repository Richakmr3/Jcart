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
<title>JCart - Payment Success</title>
<link href="../pages-resources/styles/style.css" media="screen"
	rel="stylesheet">

<script type="text/javascript">
	function destroyBean() {
	
		var xmlhttp;
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }

		xmlhttp.open("GET","../user/paymentMBDestroyer.jsp?f="+(Math.random()*100),true);
		xmlhttp.send();
		alert("Thanks for Shopping");
		
	}
</script>


</head>
<body onunload="destroyBean()">
<%
	if (session.getAttribute("userId") == null
			|| session.getAttribute("role").toString().charAt(0) != 'U') {
		response.sendRedirect("../faces/errorPage.jsp");
	}
%><f:view>
	<jsp:include page="../header.jsp"></jsp:include>
	<!-- Search bar to be included in each page -->





	<center>
	<div id="container"><h:form prependId="false">
		<div id="menu1"><jsp:include page="UserLeftToc.jsp"></jsp:include>
		</div>
		<!-- Right navigation pane links -->
		<div id="menu2"><jsp:include page="UserRightToc.jsp"></jsp:include>
		</div>

		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>



		<center>
		<h5><h:outputText value="#{billingBean.message}" escape="false"></h:outputText></h5>
		</center>



	</h:form></div>
	</center>

	<!-- Footer -->

	<jsp:include page="../footer.jsp"></jsp:include>
</f:view>
</body>
</html>