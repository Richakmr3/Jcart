<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link href="../../page-resources/styles/style.css" media="screen"
	rel="stylesheet">
	<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
	
</head>
<body>
<%
	if(session.getAttribute("userId")==null || session.getAttribute("role").toString().charAt(0)!='R'){
		response.sendRedirect("../../faces/pages/errorPage.jsp");
	}
 %>

			<ul>
				<li><h:outputLink value="#" id="current"><h:outputText value="#{lable.Retailer_Options}"></h:outputText> </h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/retailer/AddNewProduct.jsp"><h:outputText value="#{lable.Add_New_Product}"></h:outputText></h:outputLink> </li>
					<li><h:outputLink value="../../faces/pages/retailer/AddProductVariant.jsp"><h:outputText value="#{lable.Add_New_Variant}"></h:outputText></h:outputLink></li>
					<li><h:outputLink value="../../faces/pages/retailer/AddOfferDetails.jsp"><h:outputText value="#{lable.Add_Offer_Details}"></h:outputText></h:outputLink></li>
					<li><h:outputLink value="../../faces/pages/retailer/AddSubCategory.jsp"><h:outputText value="#{lable.Add_Sub_Category}"></h:outputText></h:outputLink></li>
					<li><h:commandLink value="#{lable.Update_Offer_Details}" action="#{updateOfferBean.reBuildList}" ></h:commandLink></li>
					<li><h:outputLink value="../../faces/pages/retailer/UpdateStockInfo.jsp"><h:outputText value="#{lable.Update_Stock_Info}"></h:outputText></h:outputLink></li>
					<li><h:outputLink value="../../faces/pages/retailer/RemoveProductVariationInfo.jsp"><h:outputText value="#{lable.Remove_a_Product_Variation}"></h:outputText></h:outputLink></li>
					<li><h:commandLink value="#{lable.Remove_Offer_Details}" action="#{removeOfferBean.reBuildList}"></h:commandLink></li>
					<li><h:outputLink value="../../faces/pages/retailer/ViewRetailerProducts.jsp"><h:outputText value="#{lable.Show_My_Products}"></h:outputText></h:outputLink></li>
			</ul>

</body>
</html>