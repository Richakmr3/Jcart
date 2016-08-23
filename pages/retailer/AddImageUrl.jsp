<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>

<title>JCart - Add Product Variant</title>
</head>
<body>
<%
	if(session.getAttribute("userId")==null || session.getAttribute("role").toString().charAt(0)!='R'){
		response.sendRedirect("../errorPage.jsp");
	}
 %>
<f:view>
<jsp:include page="../header.jsp"></jsp:include>
		<center>
		<div id="container">
		
			<!-- Left navigation pane links -->
		
		<!-- Main body page for displaying details -->
		<div id="page">
		
		 <h1><h:outputText value="#{lable.Add_Product_Variation_Information}"></h:outputText> </h1>
    <hr/>
 
    <fieldset>
        <legend><h:outputText value="#{lable.Provide_variant_Info}"></h:outputText> </legend>
        <form action="../FileUploadServlet" method="post" enctype="multipart/form-data" name="form1">
            
            <table>
				<tr>
				
					<td><label for="filename_1"><h:outputText value="#{lable.Upload_Image}"></h:outputText> : </label></td>
					<td> <input id="filename_1" type="file" name="filename_1"/></td>
					
				</tr>
			</table>
            
            <input type="submit" class="btn" value="#{lable.Add_Image}"/>&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="reset" class="btn" value="#{lable.Reset}">
        </form>
    </fieldset>

	
	<a href="Home.jsp"><h:outputText value="#{lable.Home}"></h:outputText> </a>
	</div>
	</div>
</center>

<jsp:include page="../footer.jsp"></jsp:include>
</f:view>
</body>
</html>