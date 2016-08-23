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

<title>JCart - Facts</title>
<link href="../../page-resources/styles/style.css" media="screen"
	rel="stylesheet">

<style type="text/css">
</style>
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body style="background-image: url('../page-resources/images/jclogo_blur.png');">


<f:view>
	<jsp:include page="../header.jsp"></jsp:include>
	<h:form prependId="false">
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
					<li><a href="../user/purchaseUsedProducts.jsp?&showlist=no"><h:outputText value="#{lable.Purchase_Used_Products}"></h:outputText></a></li>						
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
		<div id="page"
			style="background-image: url('../page-resources/images/jclogo_blur.png'); background-position: center center; background-repeat: no-repeat;">

			<h2><h:outputText value="#{lable.Facts_and_Questions}"></h:outputText></h2>
		<hr align="left">
		<h:panelGrid columns="3">
		<h:panelGroup>
	
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg1}"></h:outputText></b></u><h:outputText value="#{lable.Msg2}"></h:outputText><br>" escape="false"/>
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg3}"></h:outputText></b></u><h:outputText value="#{lable.Msg4}"></h:outputText><br><br>" escape="false"></h:outputText>
		
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg5}"></h:outputText><br><br></b></u><h:outputText value="#{lable.Msg6}"></h:outputText><br><br><br>" escape="false"/>
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg3}"></h:outputText></b></u><h:outputText value="#{lable.Msg7}"></h:outputText><br><br><br><br>" escape="false"></h:outputText>
		
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg8}"></h:outputText><br><br></b></u><h:outputText value="#{lable.Msg9}"></h:outputText><br><br><br>" escape="false"/>
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg3}"></h:outputText></b></u><h:outputText value="#{lable.Msg10}"></h:outputText><br><br><br><br>" escape="false"></h:outputText>
		
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg11}"></h:outputText><br><br></b></u<h:outputText value="#{lable.Msg12}"></h:outputText><br><br> <br>" escape="false"/>
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg3}"></h:outputText></b></u><h:outputText value="#{lable.Msg13}"></h:outputText><br><br><br><br>" escape="false"></h:outputText>
		
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg14}"></h:outputText><br><br></b></u><h:outputText value="#{lable.Msg15}"></h:outputText><br><br><br>" escape="false"/>
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg3}"></h:outputText><br><br></b></u><h:outputText value="#{lable.Msg16}"></h:outputText><br><br><ul><li><h:outputText value="#{lable.Msg17}"></h:outputText><br><br></li><li><h:outputText value="#{lable.Msg18}"></h:outputText><br><br></li><li><h:outputText value="#{lable.Msg19}"></h:outputText><br><br></li></ul><br>" escape="false"/>
		
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg20}"></h:outputText><br><br></b></u><h:outputText value="#{lable.Msg21}"></h:outputText><br><br><br>" escape="false"/>
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg3}"></h:outputText></b></u><h:outputText value="#{lable.Msg22}"></h:outputText><br><br></b> <h:outputText value="#{lable.Msg23}"></h:outputText><br><br><br><br>" escape="false"></h:outputText>
		
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg24}"></h:outputText><br><br></b></u><h:outputText value="#{lable.Msg25}"></h:outputText><br><br><br>" escape="false"/>
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg3}"></h:outputText></b></u><h:outputText value="#{lable.Msg26}"></h:outputText><br><br><b><h:outputText value="#{lable.Msg27}"></h:outputText><br><br></b><br><br>" escape="false"></h:outputText>
		
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg28}"></h:outputText><br><br></b></u><h:outputText value="#{lable.Msg29}"></h:outputText><br><br><br>" escape="false"/>
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg3}"></h:outputText></b></u><h:outputText value="#{lable.Msg30}"></h:outputText><br><br><br><br>" escape="false"></h:outputText>
		
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg31}"></h:outputText><br><br></b></u><h:outputText value="#{lable.Msg32}"></h:outputText><br><br><br>" escape="false"/>
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg3}"></h:outputText></b></u><h:outputText value="#{lable.Msg33}"></h:outputText><br><br><br><br>" escape="false"></h:outputText>
		
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg34}"></h:outputText><br><br></b></u><h:outputText value="#{lable.Msg35}"></h:outputText><br><br><br>" escape="false"/>
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg3}"></h:outputText></b></u><h:outputText value="#{lable.Msg36}"></h:outputText><br><br><b><h:outputText value="#{lable.Msg37}"></h:outputText><br><br></b><h:outputText value="#{lable.Msg38}" escape="false"></h:outputText><br><br>"/>
		
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg39}"></h:outputText><br><br></b></u><h:outputText value="#{lable.Msg40}"></h:outputText><br><br><br>" escape="false"/>
		<h:outputText value="<u><b><h:outputText value="#{lable.Msg3}"></h:outputText></b></u><h:outputText value="#{lable.Msg41}"></h:outputText><br><br><br><br>" escape="false"></h:outputText>
		
		
		</h:panelGroup>	
		</h:panelGrid>
		</div>
		</div>
		</center>

	</h:form>
	<jsp:include page="../footer.jsp"></jsp:include>
</f:view>
</body>
</html>