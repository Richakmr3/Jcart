<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%><%@taglib
	uri="http://java.sun.com/jsf/core" prefix="f"%><%@taglib
	uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="../page-resources/styles/style.css" media="screen"
	rel="stylesheet">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Purchase History</title>
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
</head>
<body>
<%
	if(session.getAttribute("userId")==null || session.getAttribute("role").toString().charAt(0)!='U'){
		response.sendRedirect("../errorPage.jsp");
	}
 %>
<f:view>
<jsp:include page="../header.jsp"></jsp:include>
		<!-- Search bar to be included in each page -->
		
		
		
		
		
		<center>
		<div id="container">
		
		<h:form>
		<div id="search">
			<h:inputText styleClass="text" title="Enter text to search" value="#{searchBean.searchString}"></h:inputText>
			
			 <h:selectOneMenu  value="#{searchBean.category}"    styleClass="drop" >	
				<f:selectItem itemLabel="In All Categories"     itemValue="O"/>
				<f:selectItem itemLabel="Books"         		itemValue="Books"/>
				<f:selectItem itemLabel="Bags"          		itemValue="Bags"/>
				<f:selectItem itemLabel="Cameras"       		itemValue="Camera"/>
				<f:selectItem itemLabel="Clothing"      		itemValue="Clothing"/>
				<f:selectItem itemLabel="Computers"     		itemValue="Computers"/>
				<f:selectItem itemLabel="Footwear"      		itemValue="FootWear"/>
				<f:selectItem itemLabel="Mobiles"       		itemValue="Mobile"/>
				<f:selectItem itemLabel="TVs"           		itemValue="Television" />
			</h:selectOneMenu>
            <h:commandButton styleClass="search" title="Search button" action="#{searchBean.getSearchelements}"></h:commandButton>
			<h:commandButton styleClass="cart" title="Shopping Cart" value="Cart (0)" action="#{cartBean.viewMycart}"></h:commandButton>
			</div>
		
			<!-- Left navigation pane links -->
		<div id="menu1">
			<ul>
			
				<li><a href="#" id="101" onmouseover="loadXMLDoc(this);" onclick="submit();"><h:outputText value="#{lable.Books}"></h:outputText></a></li>
				<li><a href="#" id="102" onmouseover="loadXMLDoc(this);" onclick="submit();"><h:outputText value="#{lable.Computer}"></h:outputText></a></li>
				<li><a href="#" id="103" onmouseover="loadXMLDoc(this);" onclick="submit();"><h:outputText value="#{lable.Camera}"></h:outputText></a></li>
				<li><a href="#" id="104" onmouseover="loadXMLDoc(this);" onclick="submit();"><h:outputText value="#{lable.TV}"></h:outputText></a></li>
				<li><a href="#" id="105" onmouseover="loadXMLDoc(this);" onclick="submit();"><h:outputText value="#{lable.Mobile}"></h:outputText></a></li>
				<li><a href="#" id="106" onmouseover="loadXMLDoc(this);" onclick="submit();"><h:outputText value="#{lable.Clothing}"></h:outputText></a></li>
				<li><a href="#" id="107" onmouseover="loadXMLDoc(this);" onclick="submit();"><h:outputText value="#{lable.Footwear}"></h:outputText></a></li>
				<li><a href="#" id="108" onmouseover="loadXMLDoc(this);" onclick="submit();"><h:outputText value="#{lable.Bags}"></h:outputText></a></li>
			</ul>
			<h4><h:outputText value="#{lable.subCategories}"></h:outputText></h4>
			<div id="myDiv1"></div>
			
		</div>
		<!-- Right navigation pane links -->
		<div id="menu2">
			<ul>
				<li><a href="#" id="101" onmouseover="loadXMLDoc(this);" onclick="submit();"><h:outputText value="#{lable.Books}"></h:outputText></a></li>
				<li><a href="#" id="102" onmouseover="loadXMLDoc(this);" onclick="submit();"><h:outputText value="#{lable.Computer}"></h:outputText></a></li>
				<li><a href="#" id="103" onmouseover="loadXMLDoc(this);" onclick="submit();"><h:outputText value="#{lable.Camera}"></h:outputText></a></li>
				<li><a href="#" id="104" onmouseover="loadXMLDoc(this);" onclick="submit();"><h:outputText value="#{lable.TV}"></h:outputText></a></li>
				<li><a href="#" id="105" onmouseover="loadXMLDoc(this);" onclick="submit();"><h:outputText value="#{lable.Mobile}"></h:outputText></a></li>
				<li><a href="#" id="106" onmouseover="loadXMLDoc(this);" onclick="submit();" ><h:outputText value="#{lable.Clothing}"></h:outputText></a></li>
				<li><a href="#" id="107" onmouseover="loadXMLDoc(this);" onclick="submit();"><h:outputText value="#{lable.Footwear}"></h:outputText></a></li>
				<li><a href="#" id="108" onmouseover="loadXMLDoc(this);" onclick="submit();"><h:outputText value="#{lable.Bags}"></h:outputText></a></li>
			</ul>
			<h4><h:outputText value="#{lable.subCategories}"></h:outputText></h4>
			<div id="myDiv2"></div>
		</div>
		<!-- Main body page for displaying details -->
	<h:form>


<center>
<br><br><br><br>
<h3> <h:outputText value="#{lable.Transaction_Details}"></h:outputText></h3><br><br>
		<c:if test="${not empty purchaseHistoryBean.displayList}">

		<h:dataTable border="1" value="#{purchaseHistoryBean.displayList}" var="item">
			<h:column id="column1">
				<f:facet name="header">
					<h:outputText value="#{lable.Payment_Id}"></h:outputText>
				</f:facet>
				<h:outputText value="#{item.paymentId}"></h:outputText>
			</h:column>
			<h:column id="column2">
				<f:facet name="header">
					<h:outputText value="#{lable.Product_List}"></h:outputText>
				</f:facet>
				<h:dataTable value="#{item.productName}" var="name">
				<h:column id="c1">
				<h:outputText value="#{name}"></h:outputText>
				<h:outputText value="   "></h:outputText>
				</h:column>
				
				</h:dataTable>
				
				
			</h:column>
			<h:column id="column3">
				<f:facet name="header">
					<h:outputText value="#{lable.Net_bill }"></h:outputText>
				</f:facet>
				<h:outputText value="#{item.netBill}"></h:outputText>
			</h:column>
			<h:column id="column4">
				<f:facet name="header">
					<h:outputText value="#{lable.Date_Of_Purchase}"></h:outputText>
				</f:facet>
				<h:outputText value="#{item.dateOfPurchase}">

					<f:convertDateTime dateStyle="medium"/>
				</h:outputText>
			</h:column>
			
			
		</h:dataTable>
		</c:if>
		<c:if test="${empty purchaseHistoryBean.displayList}">
		<h3> <h:outputText value="#{lable.No_Details_Found}"></h:outputText></h3>
		</c:if>
	<h:outputText value="#{purchaseHistoryBean.message}"> </h:outputText>
	</center>	
	</h:form></h:form></div></center>
	<jsp:include page="../footer.jsp"></jsp:include>
</f:view></body>
</html>