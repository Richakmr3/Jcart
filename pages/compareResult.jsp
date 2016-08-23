<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<f:loadBundle var="lable" basename="com.infy.jcart.resources.Lang"/>
<title>JCart - Compare Result</title>
<link href="../page-resources/styles/style.css" media="screen"
	rel="stylesheet">

</head>
<body>
	<f:view>
		<jsp:include page="header.jsp"></jsp:include>
	
		<center>
		<div id="container">
		
		 <div id="menu1">
		 <jsp:include page="VisitorLeftToc.jsp" ></jsp:include>
		</div>
		
		<h:form>
		
			<div id="page">
			<center>
			
			<c:forEach items="#{searchBean.sellistBags}" var="item"> 
					<h:panelGroup style="display:inline;border:2px">
						<h:panelGrid columns="1" style="display:inline;border:2px solid">
							
							<h:outputLabel value="#{lable.ProductName} :#{item.productName}" styleClass="price"></h:outputLabel>
							<h:outputLabel value="#{lable.Company} :#{item.company}" styleClass="price"></h:outputLabel>
							<h:outputLabel value="#{lable.Type} :#{item.type}" styleClass="price"></h:outputLabel>
							<h:outputLabel value="#{lable.Material} :#{item.material}" styleClass="price"></h:outputLabel>
							<h:outputLabel value="#{lable.Weight} :#{item.weight}" styleClass="price"></h:outputLabel>
							<h:outputLabel value="#{lable.Ideal_For} :#{item.idealFor}" styleClass="price"></h:outputLabel>
						</h:panelGrid>
					</h:panelGroup>
				</c:forEach>
				
				
				<h:panelGroup rendered="#{not empty searchBean.sellistBooks}">
				<hr>
				</h:panelGroup>
				
			
				
				<c:forEach items="#{searchBean.sellistBooks}" var="item" > 
					<h:panelGroup style="display:inline;border:2px">
						<h:panelGrid columns="1" style="display:inline;border:2px solid">
							
							<h:outputLabel value="#{lable.ProductName } :#{item.productName}" styleClass="price" ></h:outputLabel>	
							<h:outputLabel value="#{lable.Author }      :#{item.author}" styleClass="price"></h:outputLabel>
							<h:outputLabel value="#{lable.Language }    :#{item.language}" styleClass="price"></h:outputLabel>
							<h:outputLabel value="#{lable.Publisher }   :#{item.publisher}" styleClass="price"></h:outputLabel>
							<h:outputLabel value="#{lable.Year_of_Publication } :#{item.author}" styleClass="price"></h:outputLabel>
							<h:outputLabel value="#{lable.Type_Of_Cover } :#{item.typeOfCover}" styleClass="price"></h:outputLabel>
						</h:panelGrid>
					</h:panelGroup>
				</c:forEach>
				
				<h:panelGroup rendered="#{not empty searchBean.sellistCamera}">
				<hr>
				</h:panelGroup>
				
					<c:forEach items="#{searchBean.sellistCamera}" var="item"> 
						<h:panelGroup style="display:inline;border:2px">
							<h:panelGrid columns="1" style="display:inline;border:2px solid">
								
								<h:outputLabel value="#{lable.ProductName } :#{item.productName}" styleClass="price"></h:outputLabel>	
								<h:outputLabel value="#{lable.Company }     :#{item.company}" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.Battery }     :#{item.battery}" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.Picture_Resolution }   :#{item.pixels}" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.Memory }      :#{item.memory}" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.Zoom }        :#{item.opticalZoom}" styleClass="price"></h:outputLabel>
							</h:panelGrid>
						</h:panelGroup>
					</c:forEach>
				
				<h:panelGroup rendered="#{not empty searchBean.sellistClothing}">
				<hr>
				</h:panelGroup>
					
					
					<c:forEach items="#{searchBean.sellistClothing}" var="item"> 
						<h:panelGroup style="display:inline;border:2px">
							<h:panelGrid columns="1" style="display:inline;border:2px solid">
								
								<h:outputLabel value="#{lable.ProductName} :#{item.productName}" styleClass="price"></h:outputLabel>	
								<h:outputLabel value="#{lable.Company }     :#{item.company}" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.Fabric }      :#{item.fabric}" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.Ideal_For }   :#{item.idealFor}" styleClass="price"></h:outputLabel>
							</h:panelGrid>
						</h:panelGroup>
					</c:forEach>
					
					
				<h:panelGroup rendered="#{not empty searchBean.sellistComputers}">
					<hr>
				</h:panelGroup>
				
				
					<c:forEach items="#{searchBean.sellistComputers}" var="item"> 
						<h:panelGroup style="display:inline;border:2px">
							<h:panelGrid columns="1" style="display:inline;border:2px solid">
								
								<h:outputLabel value="#{lable.ProductName }    :#{item.productName}" styleClass="price"></h:outputLabel>	
								<h:outputLabel value="#{lable.Company }        :#{item.company}" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.ProcessorName }  :#{item.processorName}" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.RAM }            :#{item.ram} GB" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.Hard_Disk }      :#{item.hardDiskSize} GB" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.Cell_Size }      :#{item.batteryCells}" styleClass="price"></h:outputLabel>
							</h:panelGrid>
						</h:panelGroup>
					</c:forEach>
					
					
				<h:panelGroup rendered="#{not empty searchBean.sellistFootware}">
				<hr>
				</h:panelGroup>
				
				
						<c:forEach items="#{searchBean.sellistFootware}" var="item"> 
						<h:panelGroup style="display:inline;border:2px">
							<h:panelGrid columns="1" style="display:inline;border:2px solid">
								
								<h:outputLabel value="#{lable.ProductName }    :#{item.productName}" styleClass="price"></h:outputLabel>	
								<h:outputLabel value="#{lable.Company }        :#{item.company}" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.Material}       :#{item.material}" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.Ideal_For }      :#{item.idealFor}" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.Weight }         :#{item.weight}" styleClass="price"></h:outputLabel>
							</h:panelGrid>
						</h:panelGroup>
						
					</c:forEach>
					
					
				<h:panelGroup rendered="#{not empty searchBean.sellistMobile}">
				    <hr>
				</h:panelGroup>
				
				
					<c:forEach items="#{searchBean.sellistMobile}" var="item"> 
						<h:panelGroup style="display:inline;border:2px">
							<h:panelGrid columns="1" style="display:inline;border:2px solid">
								
								<h:outputLabel value="#{lable.ProductName }     :#{item.productName}" styleClass="price"></h:outputLabel>	
								<h:outputLabel value="#{lable.Company }         :#{item.company}" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.Generation }      :#{item.generation}" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.Memory }          :#{item.memory} MB" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.Number_of_Sims}  :#{item.sim}" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.Screen_Size }     :#{item.screenSize}" styleClass="price"></h:outputLabel>
							</h:panelGrid>
						</h:panelGroup>
                     </c:forEach>    
					
					
					<h:panelGroup rendered="#{not empty searchBean.sellistTelevison}">
						<hr>
					</h:panelGroup>
					
					<c:forEach items="#{searchBean.sellistTelevison}" var="item"> 
						<h:panelGroup style="display:inline;border:2px">
							<h:panelGrid columns="1" style="display:inline;border:2px solid">
								
								<h:outputLabel value="#{lable.ProductName }      :#{item.productName}" styleClass="price"></h:outputLabel>	
								<h:outputLabel value="#{lable.Company }          :#{item.company}" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.HdReady }          :#{item.hdReady}" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.Dimensions }       :#{item.dimension}" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.PowerConsumption } :#{item.powerConsumption}" styleClass="price"></h:outputLabel>
								<h:outputLabel value="#{lable.Screen_Size }      :#{item.screenSize}" styleClass="price"></h:outputLabel>
							</h:panelGrid>
						</h:panelGroup>
                     </c:forEach>   
					
					
				<br>
				<h:outputText value="#{searchBean.message}"></h:outputText>
				<h:outputLink value="searchPage.jsp">Back</h:outputLink>
			</center>		
			</div>
			</h:form>
		</div>
		</center>
		
		<!-- Footer -->
		<jsp:include page="footer.jsp"></jsp:include>
	</f:view>
</body>
</html>