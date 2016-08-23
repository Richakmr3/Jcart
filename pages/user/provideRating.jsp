<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%><%@taglib
	uri="http://java.sun.com/jsf/html" prefix="h"%><%@taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Product and Retailer Rating</title>
<link href="../page-resources/styles/style.css"  media="screen" rel="stylesheet">
<script type="text/javascript">
   function setvalue(me){
	document.getElementById('form1:rate').value = me.title;
 	
   }
   function clear(){
   	document.getElementById('form1:comment').value = "";
   }
</script>
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body onload="clear();">
<%
	if(session.getAttribute("userId")==null || session.getAttribute("role").toString().charAt(0)!='U'){
		response.sendRedirect("../errorPage.jsp");
	}
 %>
<f:view>



<!-- header -->
<jsp:include page="../header.jsp"></jsp:include>
<div id="container">
<div id="menu1">
			<jsp:include page="UserLeftToc.jsp"></jsp:include>
		</div>
		<!-- Right navigation pane links -->
		<div id="menu2">
			<jsp:include page="UserRightToc.jsp"></jsp:include>
		</div>
	<div id="page">
		<br>
		<h:form id="form1">
		
		<center>
		<h:outputLabel value="#{lable.Product_and_Retailer_Rating}" styleClass="pageTitle"></h:outputLabel><br><br>
		<h:panelGrid columns="2">
			<h:selectOneRadio valueChangeListener="#{ratingBean.showOptionToRate}" onclick="submit()" styleClass="selectRadio">
			<f:selectItem itemLabel="Product Rating" itemValue="product"/>
			<f:selectItem itemLabel="Retailer Rating" itemValue="retailer"/>
			</h:selectOneRadio>
		</h:panelGrid>
		<hr>
		<c:if test="${ratingBean.ratingType eq 'retailer'}">
		<h:panelGrid columns="2">
				<h:outputText value="#{lable.Retailer1}" styleClass="label"></h:outputText>
				<h:selectOneMenu value="#{ratingBean.retailerId}" onchange="submit()" styleClass="selectMenu">
				<f:selectItem itemLabel="-select-"/>
				<f:selectItems value="#{ratingBean.retailerList}"/>
				</h:selectOneMenu>
		</h:panelGrid>
		</c:if>
		<c:if test="${ratingBean.ratingType eq 'product'}">
		<h:panelGrid columns="2">
				<h:outputText value="#{lable.Product1}" styleClass="label"></h:outputText>
				<h:selectOneMenu value="#{ratingBean.productId}" onchange="submit()" styleClass="selectMenu">
				<f:selectItem itemLabel="-select-"/>
				<f:selectItems value="#{ratingBean.productList}"/>
				</h:selectOneMenu>
		</h:panelGrid>
		</c:if>
		<br>
		<c:if test="${ratingBean.productId ne null or ratingBean.retailerId ne null}">
		<h:panelGrid columns="2" styleClass="grid">
		<h:outputLabel value="#{lable.Rating1}" styleClass="label"/>
		<div>
		<img src="../../page-resources/images/star_off.png" name="img1" title="Very Bad" width="20px" height="25px" onmouseover='img1.src="../../page-resources/images/star_on.png"' onmouseout='img1.src="../../page-resources/images/star_off.png"' onclick="setvalue(this)"/>
		<img src="../../page-resources/images/star_off.png" name="img2" title="Bad" width="20px" height="25px" onmouseover='img2.src="../../page-resources/images/star_on.png"' onmouseout='img2.src="../../page-resources/images/star_off.png"' onclick="setvalue(this)"/>
		<img src="../../page-resources/images/star_off.png" name="img3" title="Average" width="20px" height="25px" onmouseover='img3.src="../../page-resources/images/star_on.png"' onmouseout='img3.src="../../page-resources/images/star_off.png"' onclick="setvalue(this)"/>
		<img src="../../page-resources/images/star_off.png" name="img4" title="Good" width="20px" height="25px" onmouseover='img4.src="../../page-resources/images/star_on.png"' onmouseout='img4.src="../../page-resources/images/star_off.png"' onclick="setvalue(this)"/>
		<img src="../../page-resources/images/star_off.png" name="img5" title="Excellent" width="20px" height="25px" onmouseover='img5.src="../../page-resources/images/star_on.png"' onmouseout='img5.src="../../page-resources/images/star_off.png"' onclick="setvalue(this)"/>
		</div>
	
		<h:outputLabel value="#{lable.Review_Comments1 }" styleClass="label"/>
		<h:inputTextarea id="comment" value="#{ratingBean.reviewComments}" required="true" requiredMessage="Comments Required" styleClass="box"/>
		</h:panelGrid><br><br>
		<h:commandButton styleClass="sub" action="#{ratingBean.saveRating}" value="#{lable.Submit }"></h:commandButton>
		</c:if>
		<br><br>
		<h:inputHidden id="rate" value="#{ratingBean.rating}">
		<f:converter converterId="ratingConvertor"/>
		</h:inputHidden>
		<h:outputLabel value="#{ratingBean.successMsg}" styleClass="successMsg"></h:outputLabel>
		<h:outputLabel value="#{ratingBean.errorMsg}" styleClass="successMsg"></h:outputLabel>
		
		<br>
		<h:messages styleClass="errorMsg"></h:messages>
		</center>
		</h:form>
	</div>
</div>
<!-- footer -->
<jsp:include page="../footer.jsp"></jsp:include>

</f:view>

</body>
</html>