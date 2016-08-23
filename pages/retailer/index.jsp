<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 
<!-- A particular character set can be specified by assigning "charset" -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
 
<title>JCart - Websense Message</title>
<link rel="stylesheet" href="/en/Default/blockStyle.css" type="text/css">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>

</head>
 
<!-- Default colors are BLACK text and WHITE background -->
<body text="#000000" bgcolor="#99CCFF">
 
 
<!-- The entire page is structured using embedded tables -->
<table width="600" cellpadding="0" cellspacing="0" align = "center">
  <tr> 
    <td rowspan="2"><hr></td>    
    <td align="center"> 
   <!-- This is the Websense logo.  The image is located 	-->
   <!-- inside the "Websense Enterprise" directory 	-->
   <!-- This text is displayed for every blocked website -->
   <br>
        <h2 id="AccessRestricted"><h:outputText value="#{lable.Internet_Access_policy}"></h:outputText> </h2>
    </td>
  </tr>
  <tr> 
    <td> 
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr> 
          <td align="right" width="100"><hr></td>
          <td width="100%"><hr></td>
        </tr>
        <tr> 
          <!-- This displays the reason why the user was blocked -->
          <td align="right" valign="top"> 
            <p class="bold" id="ReasonLabel"><h:outputText value="#{lable.Reason}"></h:outputText>:&nbsp;</p>
          </td>
          <td> 
            <p id="ReasonText"><h:outputText value='#{lable.The_category_is_filtered}'></h:outputText></p>
          </td>
        </tr>
        <tr> 
          <td align="right">&nbsp;</td>
          <td> 
            <hr noshade size="1">
          </td>
        </tr>
        <tr> 
          <!-- This displays the URL the user attempted to surf -->
          <td align="right" valign="top"> 
            <p class="bold" id="UrlLabel"></p>
          </td>
          <td> 
 
<br>
        <h:outputText value="#{lable.The_information_recorded}"></h:outputText>
 
	  <!-- <h2 id="AccessRestricted">The below information has been recorded.</h2> -->
	  
    </td>
  </tr>
<tr>
<td> <br> </td>
</tr>
 
 
                <tr> 
 
 
          
	         	 <td align="center" valign="top"> 
	           		 <p class="bold" id="UrlText"><b><h:outputText value="#{lable.URL}"></h:outputText>:</b>&nbsp;</p>
	            </td>
	          	  <td>
	          		  <h:outputText value="#{lable.Running_the_Project}"></h:outputText> 
	          		 <!--   <a href="../JCart/faces/login.jsp">Redirect To login</a>-->
	          		 <a href="../pages/login.jsp"><h:outputText value="#{lable.Redirect_To_login}"></h:outputText> </a>
	         	 </td>
	          	</tr>
 
	<tr> 
          <td align="center" valign="top"> 
            </td>
            <td align = "left">
	    <br>
      
          </td>
          
        </tr>
      </table>
    </td>
  </tr>
 
</table>
 
</body>
</html>