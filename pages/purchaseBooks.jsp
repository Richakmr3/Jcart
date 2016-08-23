<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Purchase Books </title>
<link href="../page-resources/styles/style.css" media="screen" rel="stylesheet">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body>

<f:view>
<!-- header -->
<jsp:include page="header.jsp"></jsp:include>
<h:form prependId="false">

<div id="container">
<div id="menu1">
		 <jsp:include page="VisitorLeftToc.jsp" ></jsp:include>
			
		</div>
<div id="page">
	<div class="purchaseLeftPanel">
		<h:outputText value="#{lable.Browse}"></h:outputText>
		<h:panelGrid styleClass="grid">
			<h:commandLink value="> #{lable.Books}" action="#{purchaseBookBean.purchaseBook}">
			<f:param name="subCategory" value="#{param.subCategory}"></f:param>
			</h:commandLink>
		</h:panelGrid>
	</div>


	<div class="purchaseRightPanel">
	
		<c:if test="${purchaseBookBean.productList ne null and param.showlist ne 'no'}">
		<span class="purchaseTitle">
		<h:outputText value="#{lable.Books}		-"></h:outputText>&nbsp;&nbsp;&nbsp;
		<h:outputText value="#{purchaseBookBean.subCategoryName}"></h:outputText>
		</span><br>
		<h:dataTable value="#{purchaseBookBean.productList}" var="item" binding="#{purchaseBookBean.selectedData}">
		<h:column id="column1">
		<f:facet name="header"></f:facet>
		<div class="contentDiv">
		<span class="leftSidePanel">
		<img src="../page-resources/products/oathofvps.png" class="productImage">
		</span>
		<span class="rightSidePanel">
		<h:panelGrid columns="2">
			<h:panelGrid columns="2">
				<h:outputText value="#{lable.Publisher} :" styleClass="label"></h:outputText>
				<h:outputText value="#{item.publisher}"></h:outputText>
				<h:outputText value="#{lable.ProductName } :" styleClass="label"></h:outputText>
				<h:outputText value="#{item.productName}"></h:outputText>
				<h:outputText value="#{lable.Author} :" styleClass="label"></h:outputText>
				<h:outputText value="#{item.author}"></h:outputText>
				<h:outputText value="#{lable.Language} :" styleClass="label"></h:outputText>
				<h:outputText value="#{item.language}"></h:outputText>
				
			</h:panelGrid>
			<h:panelGrid columns="2">
				<h:outputText value=" "></h:outputText>
				<h:outputText value=" "></h:outputText>
				<h:outputText value="#{lable.Dimensions} :" styleClass="label"></h:outputText>
				<h:outputText value="#{item.dimensions}"></h:outputText>
				<h:outputText value="#{lable.Price} :" styleClass="label"></h:outputText>
				<h:outputText value="#{item.price}"><f:convertNumber type="currency" currencySymbol="Rs."/></h:outputText>
				<h:outputText value=" "></h:outputText>
				
				<h:commandLink value="#{lable.View_Details}" action="#{cartBean.viewProductDetails}" >
								<f:param name="param" value="#{item}"></f:param>
								<f:param name="param2" value="#{item.productId}"></f:param>
			    </h:commandLink>
				
				
			</h:panelGrid>
		</h:panelGrid>
		
		
		
		</span>
		
		</div>
		<br>
		</h:column>
		</h:dataTable>
		
		</c:if>
		<c:if test="${empty purchaseBookBean.productList and param.showlist ne 'no'}">
		<center>
		<h:outputLabel value="#{purchaseBookBean.errorMessage}" styleClass="errorMsg"></h:outputLabel><br><br>
		</center>
		</c:if>
	</div>

</div>
</div>
</h:form>
<!-- footer -->
<jsp:include page="footer.jsp"></jsp:include>
</f:view>
</body>
</html>