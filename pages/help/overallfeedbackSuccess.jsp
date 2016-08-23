<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
	<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<title>JCart - Over All Feedback Success</title>
<link href="../../page-resources/styles/style.css" media="screen"
	rel="stylesheet">

<style type="text/css">
</style>
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body><f:view>
	<div id="header">
			<div id="logo">
				<img src="../../page-resources/images/jclogo.png" alt="not included">
			</div>
			<div id="hlinks">
						<a href="../../help/aboutus.jsp"><h:outputText value="#{lable.About_Us}"></h:outputText></a>&nbsp;|&nbsp;
			<a href="../help/contactus.jsp"><h:outputText value="#{lable.Contact_Us}"></h:outputText></a>&nbsp;|&nbsp;
			<a href="../searchPage.jsp"><h:outputText value="#{lable.Home}"></h:outputText></a>&nbsp;|&nbsp;
			<c:if test="${role ne null}">
			<a href=""><h:outputText value="#{lable.Account_Details}"></h:outputText></a>&nbsp;|&nbsp;
			<a href="../logout.jsp"><h:outputText value="#{lable.Logout}"></h:outputText></a></c:if>
			<c:if test="${role eq null}">
			<a href="../login.jsp"><h:outputText value="#{lable.Login}"></h:outputText></a>&nbsp;|&nbsp;
			<a href="../SignUp.jsp"><h:outputText value="#{lable.Sign_Up}"></h:outputText></a></c:if>
			&nbsp;|&nbsp;<a href="../help/overallfeedback.jsp"><h:outputText value="#{lable.Feedback}"></h:outputText></a>
			</div>
			<center>
			</center>
		</div>
	<h:form>
	<center>
	<div id="container">
	<div id="menu1">
		<c:if test="${userId==null}">
			<ul>
					<li><a href="#" id="current"><h:outputText value="#{lable.Product_Options}"></h:outputText></a>  </li>
					<li><a href="../searchPage.jsp"><h:outputText value="#{lable.Search_Page}"></h:outputText></a> </li>
					<li><a href="../ViewPopularProducts.jsp"><h:outputText value="#{lable.View_Popular_Products}"></h:outputText></a></li>
					<li><a href="../ViewProductsByRetailer.jsp"><h:outputText value="#{lable.View_Products_By_Retailer}"></h:outputText></a></li>
					<li><a href="../ViewRecentlyAddedProducts.jsp"><h:outputText value="#{lable.View_Recently_Added_Products}"></h:outputText></a></li>				
				</ul>
		</c:if>
		
		<c:if test="${role eq 'U'}">
			<ul>
					<li><a href="#" id="current"><h:outputText value="#{lable.Product_Options}"></h:outputText></a> </li>
					<li><a href="../searchPage.jsp"><h:outputText value="#{lable.Search_Page}"></h:outputText></a> </li>
					<li><a href="../ViewPopularProducts.jsp"><h:outputText value="#{lable.View_Popular_Products}"></h:outputText></a></li>
					<li><a href="../ViewProductsByRetailer.jsp"><h:outputText value="#{lable.View_Products_By_Retailer}"></h:outputText></a></li>
					<li><a href="../ViewRecentlyAddedProducts.jsp"><h:outputText value="#{lable.View_Recently_Added_Products}"></h:outputText></a></li>
					<li><a href="../user/provideRating.jsp"><h:outputText value="#{lable.Provide_Rating}"></h:outputText></a></li>
					<li><a href="../user/requestProduct.jsp"><h:outputText value="#{lable.Request_Product}"></h:outputText></a></li>
					<li><a href="../user/initiateProductBidding.jsp"><h:outputText value="#{lable.Initiate_Bidding}"></h:outputText></a></li>
					<li><a href="../user/updateProductBidding.jsp"><h:outputText value="#{lable.Update_Bidding}"></h:outputText></a></li>
					<li><a href="../user/purchaseUsedProducts.jsp"><h:outputText value="#{lable.Purchase_Used_Products}"></h:outputText></a></li>						
				</ul>
		</c:if>	
						
						
		</div>
		
		
		 <div id="menu2">
		
		
		<c:if test="${role eq 'U'}">
			<ul>
					<li><a href="#" id="current1"><h:outputText value="#{lable.Account_Settings}"></h:outputText></a> </li>
					<li><a href="../accountSettings/ChangePassword.jsp"><h:outputText value="#{lable.Change_Password}"></h:outputText></a> </li>
					<li><a href="../accountSettings/deactivateAccount.jsp"><h:outputText value="#{lable.DeActivate_Account}"></h:outputText></a></li>
					<li><a href="../accountSettings/EditProfile.jsp"><h:outputText value="#{lable.Edit_Profile}"></h:outputText></a></li>
					<li><a href="../accountSettings/ToggleMessageService.jsp"><h:outputText value="#{lable.Toggle_Message_Settings}"></h:outputText></a></li>
			</ul>						
		</c:if>	
						
						
		</div>
	<div id="page">
	
	<%String s=(String)session.getAttribute("userId"); %>
	<%if(! (s==null||s.length()==0) ){ %>
	<h2>Your feedback is valuable!!! Thanks for giving Feedback :<%=session.getAttribute("userId")%></h2>
	<%} else{%>
	<h2>Your feedback is valuable!!! Thanks for giving Feedback</h2>
	<%} %>
	
	</div>
	</div>
	</center>
	</h:form></f:view>
	<div id="footer">
<center>
&copy;&nbsp; 2015 JCart Shopping 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; STAY IN TOUCH : 
<a href="../help/blocked.html" ><img src="../page-resources/images/fb.png"></a>
<a href="../help/blocked.html"><img src="../page-resources/images/twit.png"></a>
<a href="../help/blocked.html"><img src="../page-resources/images/gplus.png"></a>&nbsp;
</center>
</div>
</body>
</html>