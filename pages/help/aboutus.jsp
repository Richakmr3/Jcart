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

<title>JCart - About us</title>
<link href="../../page-resources/styles/style.css" media="screen"
	rel="stylesheet">

<style type="text/css">
</style>
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang" />
</head>
<body
	style="background-image: url('../../page-resources/images/jclogo_blur.png');">


	<f:view>
		<jsp:include page="../header.jsp"></jsp:include>
		<h:form prependId="false">
			<center>
				<div id="container">
					<div id="menu1">
						<c:if test="${userId==null}">
							<ul>
								<li><a href="#" id="current"><h:outputText
											value="#{lable.Product_Options}"></h:outputText></a></li>
								<li><a href="../searchPage.jsp"><h:outputText
											value="#{lable.Search_Page}"></h:outputText></a></li>
								<li><a href="../ViewPopularProducts.jsp"><h:outputText
											value="#{lable.View_Popular_Products}"></h:outputText></a></li>
								<li><a href="../ViewProductsByRetailer.jsp"><h:outputText
											value="#{lable.View_Products_By_Retailer}"></h:outputText></a></li>
								<li><a href="../ViewRecentlyAddedProducts.jsp"><h:outputText
											value="#{lable.View_Recently_Added_Products}"></h:outputText></a></li>
							</ul>
						</c:if>

						<c:if test="${role eq 'U'}">
							<ul>
								<li><a href="#" id="current"></a></li>
								<li><a href="../searchPage.jsp"><h:outputText
											value="#{lable.Search_Page}"></h:outputText></a></li>
								<li><a href="../ViewPopularProducts.jsp"><h:outputText
											value="#{lable.View_Popular_Products}"></h:outputText></a></li>
								<li><a href="../ViewProductsByRetailer.jsp"><h:outputText
											value="#{lable.View_Products_By_Retailer}"></h:outputText></a></li>
								<li><a href="../ViewRecentlyAddedProducts.jsp"><h:outputText
											value="#{lable.View_Recently_Added_Products}"></h:outputText></a></li>
								<li><a href="../user/provideRating.jsp"><h:outputText
											value="#{lable.Provide_Rating}"></h:outputText></a></li>
								<li><a href="../user/requestProduct.jsp"><h:outputText
											value="#{lable.Request_Product}"></h:outputText></a></li>
								<li><a href="../user/initiateProductBidding.jsp"><h:outputText
											value="#{lable.Initiate_Bidding}"></h:outputText></a></li>
								<li><a href="../user/updateProductBidding.jsp"><h:outputText
											value="#{lable.Update_Bidding}"></h:outputText></a></li>
								<li><a href="../user/purchaseUsedProducts.jsp"><h:outputText
											value="#{lable.Purchase_Used_Products}"></h:outputText></a></li>
							</ul>
						</c:if>


					</div>


					<div id="menu2">


						<c:if test="${role eq 'U'}">
							<ul>
								<li><a href="#" id="current1"><h:outputText
											value="#{lable.Account_Settings}"></h:outputText></a></li>
								<li><a href="../accountSettings/ChangePassword.jsp"></a>
								<h:outputText value="#{lable.Change_Password}"></h:outputText></li>
								<li><a href="../accountSettings/deactivateAccount.jsp"><h:outputText
											value="#{lable.DeActivate_Account}"></h:outputText></a></li>
								<li><a href="../accountSettings/EditProfile.jsp"></a>
								<h:outputText value="#{lable.Edit_Profile}"></h:outputText></li>
								<li><a href="../accountSettings/ToggleMessageService.jsp"><h:outputText
											value="#{lable.Toggle_Message_Settings}"></h:outputText></a></li>
							</ul>
						</c:if>


					</div>
					<div id="page"
						style="background-image: url('../../page-resources/images/jclogo_blur.png'); background-position: center center; background-repeat: no-repeat;">

						<h2>
							<h:outputText value="#{lable.About_Us}"></h:outputText>
						</h2>
						<hr align="left">
						<h:panelGrid columns="3">
							<h:outputText value="#{lable.Hello_Infoscions}">
							</h:outputText>
							<br>
							<br>
							<br>
							<h:outputText value="#{lable.Infy_msg}"></h:outputText>
							<br>
							<br>
							<h:outputText value="#{lable.Infy_message}"></h:outputText>
							<br>
							<br>
							<h:outputText value="#{lable.Infy_msg1}"></h:outputText>
							<br>
							<br>
							<h:outputText value="#{lable.Infy_msg2}"></h:outputText>"
		</h:panelGrid>
					</div>
				</div>
			</center>

		</h:form>
		<jsp:include page="../footer.jsp"></jsp:include>
	</f:view>
</body>
</html>