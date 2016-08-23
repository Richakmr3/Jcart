<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%><%@taglib
	uri="http://java.sun.com/jsf/html" prefix="h"%><%@taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - My Wish List</title>
<link href="../../page-resources/styles/style.css" media="screen" rel="stylesheet">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body>
<%
	if(session.getAttribute("userId")==null || session.getAttribute("role").toString().charAt(0)!='U'){
		response.sendRedirect("../errorPage.jsp");
	}
 %>

<f:view>
<!-- header -->
<jsp:include page="../header.jsp"></jsp:include>
<h:form prependId="false">

<div id="container">
<div id="menu1">
			<jsp:include page="UserLeftToc.jsp"></jsp:include>
		</div>
		<!-- Right navigation pane links -->
		<div id="menu2">
			<jsp:include page="UserRightToc.jsp"></jsp:include>
		</div>
<div id="page">
<span class="wishListHeading"><h:outputText value="#{lable.My_Wishlist}"></h:outputText></span><br><br><br>
<hr>
<c:if test="${wishListBean.wishList ne null}">
<h:dataTable value="#{wishListBean.wishList}" var="wish" binding="#{wishListBean.productToRemove}">
<h:column id="column1">
<f:facet name="header"></f:facet>
<div>

<span class="rightSidePanel">
<h:outputText value="#{wish.productName}" styleClass="label"></h:outputText><br>
<h:outputText value="#{wish.price}" styleClass="productPrice">
<f:convertNumber type="currency" currencySymbol="Rs."/>
</h:outputText><br><br>

<h:commandLink action="#{wishListBean.removeFromWishList}" rendered="#{not empty wishListBean.wishList}">
<h:outputText value="#{lable.Remove_from_Wishlist}"></h:outputText>
</h:commandLink>

</span>
</div>
<br>
</h:column>
	</h:dataTable>
</c:if>

<center>
<h:outputLabel value="#{wishListBean.errorMessage}" styleClass="errorMsg"></h:outputLabel><br><br>
<h:commandButton styleClass="sub" value="#{lable.Continue_Shopping}" action="/pages/searchPage.jsp"></h:commandButton>
</center>

<hr>
<br><br>
</div>
</div>
</h:form>
<!-- footer -->
<jsp:include page="../footer.jsp"></jsp:include>

</f:view>
</body>
</html>