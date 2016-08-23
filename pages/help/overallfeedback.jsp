<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>JCart - Over All Feedback</title>
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
			<h:outputLink value= "../help/aboutus.jsp" ><h:outputText value="#{lable.About_Us}" /></h:outputLink>&nbsp;|&nbsp;
			<h:outputLink value="../help/contactus.jsp"><h:outputText value="#{lable.Contact_Us}" /></h:outputLink>&nbsp;|&nbsp;
			<h:outputLink value="../searchPage.jsp"><h:outputText value="#{lable.Home}" /></h:outputLink>&nbsp;|&nbsp;
			<c:if test="${role ne null}">
			<h:outputLink value=""><h:outputText value="#{lable.Account_Details}" /></h:outputLink>&nbsp;|&nbsp;
			<h:outputLink value="../logout.jsp"><h:outputText value="#{lable.Logout}" /></h:outputLink></c:if>
			<c:if test="${role eq null}">
			<h:outputLink value= "../login.jsp"><h:outputText value="#{lable.Login}" /></h:outputLink>&nbsp;|&nbsp;
			<h:outputLink value= "../SignUp.jsp"><h:outputText value="#{lable.Sign_Up}" /></h:outputLink></c:if>
			&nbsp;|&nbsp;<h:outputLink value= "../help/overallfeedback.jsp"><h:outputText value="#{lable.Feedback}" /></h:outputLink>
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
			
					<li><h:outputLink value= "#" id="current"><h:outputText value="#{lable.Product_Options}"/></h:outputLink>  </li>
					<li><h:outputLink value="../searchPage.jsp"><h:outputText value="#{lable.Search_Page}"/></h:outputLink> </li>
					<li><h:outputLink value="../ViewPopularProducts.jsp"><h:outputText value="#{lable.View_Popular_Products}"/></h:outputLink></li>
					<li><h:outputLink value="../ViewProductsByRetailer.jsp"><h:outputText value="#{lable.View_Products_By_Retailer}"/></h:outputLink></li>
					<li><h:outputLink value="../ViewRecentlyAddedProducts.jsp"><h:outputText value="#{lable.View_Recently_Added_Products}"/></h:outputLink></li>				
				</ul>
		</c:if>
		
		<c:if test="${role eq 'U'}">
			<ul>
					<li><h:outputText value="#{lable.Product_Options}" id="current"></h:outputText> </li>
					<li><h:commandLink value="#{lable.Search_Page}" action="../searchPage.jsp"></h:commandLink> </li>
					<li><h:commandLink value="#{lable.View_Products_By_Retailer}" action="../ViewProductsByRetailer.jsp"></h:commandLink></li>
					<li><h:commandLink value="#{lable.View_Recently_Added_Products}" action="../ViewRecentlyAddedProducts.jsp"></h:commandLink></li>
					<li><h:commandLink value="#{lable.Provide_Rating}" action="../user/provideRating.jsp"></h:commandLink></li>
					<li><h:commandLink value="#{lable.Request_Product}" action="../user/requestProduct.jsp"></h:commandLink></li>
					<li><h:commandLink value="#{lable.Initiate_Bidding}" action="../user/initiateProductBidding.jsp"></h:commandLink></li>
					<li><h:commandLink value="#{lable.Update_Bidding}" action="../user/updateProductBidding.jsp"></h:commandLink></li>
					<li><h:commandLink value="#{lable.Purchase_Used_Products}" action="../user/purchaseUsedProducts.jsp"></h:commandLink></li>						
				</ul>
		</c:if>	
						
						
		</div>
		
		
		 <div id="menu2">
		
		
		<c:if test="${role eq 'U'}">
			<ul>
					<li><h:outputText value="#{lable.Account_Settings}" id="current1"></h:outputText> </li>
					<li><h:commandLink value="#{lable.Change_Password}" action="../accountSettings/ChangePassword.jsp"></h:commandLink> </li>
					<li><h:commandLink value="#{lable.DeActivate_Account}" action="../accountSettings/deactivateAccount.jsp"></h:commandLink></li>
					<li><h:commandLink value="#{lable.Edit_Profile}" action="../accountSettings/EditProfile.jsp"></h:commandLink></li>
					<li><h:commandLink value="#{lable.Toggle_Message_Settings}" action="../accountSettings/ToggleMessageService.jsp"></h:commandLink></li>
			</ul>						
		</c:if>	
						
						
		</div>
		
		
		
	<div id="page">
	
	<h2><h:outputText value="#{lable.Overall_Feedback_for_the_Application}"></h:outputText></h2>
	<table  style="border:2px solid">
	
	<tr bgcolor="#AEDDE1">
			<td><h:outputText value="#{lable.UserId}"></h:outputText></td>
			<td><input type="text" name="userId"/></td>
		</tr>
		<tr >
			<td><h:outputText value="#{lable.Feedback_Comment}"></h:outputText></td>
			<td><textarea rows="8" cols="60" name="feedback"></textarea></td>
		</tr>

		<tr  bgcolor="#AEDDE1">
			<td><h:outputText value="#{lable.Rating}"></h:outputText></td>
			<td><select name="rating">
				<option value="5">Excellent</option>
				<option value="4">Good</option>
				<option value="3">Average</option>
				<option value="2">Poor</option>
				<option value="1">V.Poor</option>
				<option selected="selected">--Select--</option>
			</select>
			</td>
		</tr>
	</table>
	<h:commandButton value="#{lable.submit}" action ="overallfeedbackSuccess.jsp" styleClass="btn"/>
	</div>
	</div>
	</center>
	</h:form>
	<div id="footer">
<center>
&copy;&nbsp; 2015 JCart Shopping 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;STAY IN TOUCH : 
<a href="../help/blocked.html" ><img src="../page-resources/images/fb.png"></a>
<a href="../help/blocked.html"><img src="../page-resources/images/twit.png"></a>
<a href="../help/blocked.html"><img src="../page-resources/images/gplus.png"></a>&nbsp;
</center>
</div></f:view>
</body>
</html>