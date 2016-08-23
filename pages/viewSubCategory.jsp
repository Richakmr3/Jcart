<%@page import="com.infy.jcart.web.managedbeans.SearchBean"%>
<%@page import="com.infy.jcart.resources.Factory"%>
<%-- <%@page import="com.infy.jcart.web.managedbeans"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="java.util.ArrayList"  %>
<%@ page import="java.util.List"  %>
<%-- <%@ page import="com.infy.jcart.dao"  %> --%><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">

</script>

<title>JCart - Sub Category</title>
</head>

<body>
<%
	int i=0;
List<String> subList=new ArrayList<String>();
String categoryName=null;
String categoryId=request.getParameter("v");
if(categoryId.equals("101"))
{
	categoryName="Books";
}
if(categoryId.equals("102"))
{
	categoryName="Computers";
}
if(categoryId.equals("103"))
{
	categoryName="Camera";
}
if(categoryId.equals("104"))
{
	categoryName="Television";
}
if(categoryId.equals("105"))
{
	categoryName="Mobile";
}
if(categoryId.equals("106"))
{
	categoryName="Clothing";
}
if(categoryId.equals("107"))
{
	categoryName="FootWear";
}
if(categoryId.equals("108"))
{
	categoryName="Bags";
}
session.setAttribute("category", categoryName);
//String str=Factory.createSearchBean().getCategoryList();
subList=Factory.createHomeDAO().getSubCategoryNameList(Integer.parseInt(request.getParameter("v")));
SearchBean bean=(SearchBean)session.getAttribute("searchBean");
bean.setCategory(categoryName);
session.setAttribute("searchBean",bean);
%>
<br>
<ul>
<li><a href="#" id="current">Sub-Categories</a></li>
<%for(i=0;i<subList.size();i++)
{
 %>

			
			<li><a href="../pages/purchase<%=categoryName%>.jsp?subCategory=<%=subList.get(i)%>&showlist=no">
			<%
			out.println(subList.get(i));
			 %>
			</a></li>
			
		
 <%
 }
  %>
 </ul>


</body>
</html>