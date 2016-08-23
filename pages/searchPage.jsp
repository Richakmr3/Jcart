<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JCart - Search</title>
<link href="../page-resources/styles/style.css" media="screen"
	rel="stylesheet">

<script type="text/javascript">
	function loadSubCategories(val) {
		var categoryId = Number(val);
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}

		xmlhttp.onreadystatechange = function() {

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("myDiv1").innerHTML = xmlhttp.responseText;
			}
		}

		window.name = categoryId;
		xmlhttp.open("GET", "viewSubCategory.jsp?v=" + categoryId + "&f="
				+ (Math.random() * 100), true);
		xmlhttp.send();

	}

	function funReload() {
		//location.reload(); 
		document.forms[0].submit();
	}
</script>

<style>
ul {
	background-color: #686C7A;
	color: #fff;
}

li {
	display: inline;
}

li:hover {
	background-color: #00BEE4;
	color: #fff;
}
</style>



</head>


<body>
	<f:view>
		<jsp:include page="header.jsp"></jsp:include>
		<!-- Search bar to be included in each page -->





		<center>
			<div id="container">

				<h:form prependId="false">
					<div id="search">
						<h:inputText styleClass="text" title="Enter text to search"
							value="#{searchBean.searchString}" required="true"
							requiredMessage="Please Enter the Content to Search"></h:inputText>

						<h:selectOneMenu value="#{searchBean.category}" styleClass="drop">
							<f:selectItem itemLabel="In All Categories" itemValue="O" />
							<f:selectItem itemLabel="Books" itemValue="Books" />
							<f:selectItem itemLabel="Bags" itemValue="Bags" />
							<f:selectItem itemLabel="Cameras" itemValue="Camera" />
							<f:selectItem itemLabel="Clothing" itemValue="Clothing" />
							<f:selectItem itemLabel="Computers" itemValue="Computers" />
							<f:selectItem itemLabel="Footwear" itemValue="FootWear" />
							<f:selectItem itemLabel="Mobiles" itemValue="Mobile" />
							<f:selectItem itemLabel="TVs" itemValue="Television" />
						</h:selectOneMenu>
						<h:commandButton value="  Search" styleClass="search"
							title="Search button" action="#{searchBean.getSearchelements}"></h:commandButton>
						<h:commandButton styleClass="cart" title="Shopping Cart"
							value="  Cart" action="#{cartBean.viewMycart}"
							rendered="#{sessionScope.role!=null && sessionScope.role=='U'}"></h:commandButton>
					</div>

					<!-- Left navigation pane links -->
					<div id="menu1">
						<div id="myDiv1"
							style="overflow: scroll; height: 420px; overflow-x: hidden"></div>

					</div>
					<c:if test="${sessionScope.role eq 'U'}">
						<!-- Right navigation pane links -->
						<div id="menu2">
							<jsp:include page="user/UserLeftToc.jsp"></jsp:include>
						</div>
					</c:if>
					<c:if test="${sessionScope.userId==null}">
						<div id="menu2">
							<jsp:include page="VisitorLeftToc.jsp"></jsp:include>

						</div>
						<!-- Right navigation pane links -->
					</c:if>
					<!-- Main body page for displaying details -->
					<div id="categories">
						<center>
							<h:panelGroup rendered="#{searchBean.message eq null}">
								<label
									style="background: #00BEE4; color: white; display: inline;">Categories</label>
								<div
									style="text-align: left; display: inline; background: #00BEE4;">
									<ul style="display: inline;">

										<li style="margin-left: -30px;"><h:commandLink
												value="Books" style="color:white;text-decoration: none;"
												onmouseover="loadSubCategories(101);"
												action="#{searchBean.getCategoryList}"></h:commandLink></li>
										<li style="padding: 10px; padding-bottom: 0px;"><h:commandLink
												value="Computers" style="color:white;text-decoration: none;"
												onmouseover="loadSubCategories(102);"
												action="#{searchBean.getCategoryList}"></h:commandLink></li>
										<li style="padding: 10px; padding-bottom: 0px;"><h:commandLink
												value="Camera" style="color:white;text-decoration: none;"
												onmouseover="loadSubCategories(103);"
												action="#{searchBean.getCategoryList}"></h:commandLink></li>
										<li style="padding: 10px; padding-bottom: 0px;"><h:commandLink
												value="TV" style="color:white;text-decoration: none;"
												onmouseover="loadSubCategories(104);"
												action="#{searchBean.getCategoryList}"></h:commandLink></li>
										<li style="padding: 10px; padding-bottom: 0px;"><h:commandLink
												value="Mobile" style="color:white;text-decoration: none;"
												onmouseover="loadSubCategories(105);"
												action="#{searchBean.getCategoryList}"></h:commandLink></li>
										<li style="padding: 10px; padding-bottom: 0px;"><h:commandLink
												value="Clothing" style="color:white;text-decoration: none;"
												onmouseover="loadSubCategories(106);"
												action="#{searchBean.getCategoryList}"></h:commandLink></li>
										<li style="padding: 10px; padding-bottom: 0px;"><h:commandLink
												value="Footwear" style="color:white;text-decoration: none;"
												onmouseover="loadSubCategories(107);"
												action="#{searchBean.getCategoryList}"></h:commandLink></li>
										<li style="padding-left: 10px;"><h:commandLink
												value="Bags" style="color:white;text-decoration: none;"
												onmouseover="loadSubCategories(108);"
												action="#{searchBean.getCategoryList}"></h:commandLink></li>

									</ul>
								</div>
								<br>
							</h:panelGroup>
						</center>
					</div>

					<div id="pageSearch">

						<center>
							<h:dataTable value="#{searchBean.listBags}" var="bag"
								styleClass="productSearchTable"
								rendered="#{not empty searchBean.listBags}"
								>
								<h:column>
									<h:graphicImage url="../page-resources/images/bags_default.png"
										styleClass="bsimg"></h:graphicImage>
								</h:column>
								<h:column>
									<h:outputText value="Product Name : #{bag.productName}"></h:outputText>
									<br>
									<h:outputLabel value="Company     : #{bag.company}"></h:outputLabel>
									<br>
									<h:outputLabel value="Type        : #{bag.type}"></h:outputLabel>
									<br>
									<h:outputLabel value="Material    : #{bag.material}"></h:outputLabel>
									<br>
									<h:outputLabel value="Weight      : #{bag.weight}"></h:outputLabel>
									<br>
									<h:outputLabel value="Ideal For   : #{bag.idealFor}"></h:outputLabel>
									<br>
									<h:commandLink value="View Details"
										action="#{cartBean.viewProductDetails}">
										<f:param value="#{bag.productId}" name="param2"></f:param>
									</h:commandLink>
								</h:column>
							</h:dataTable>


							<h:dataTable value="#{searchBean.listBooks}" var="book"
								styleClass="productSearchTable"
								rendered="#{not empty searchBean.listBooks}">
								<h:column>
									<h:graphicImage url="../page-resources/images/book_default.png"
										styleClass="bsimg"></h:graphicImage>
								</h:column>
								<h:column>
									<h:outputText value="Product Name : #{book.productName}"></h:outputText>
									<br>
									<h:outputLabel value="Author      : #{book.author}"></h:outputLabel>
									<br>
									<h:outputLabel value="Language    : #{book.language}"></h:outputLabel>
									<br>
									<h:outputLabel value="Publisher   : #{book.publisher}"></h:outputLabel>
									<br>
									<h:outputLabel value="Year of Publication : #{book.author}"></h:outputLabel>
									<br>
									<h:outputLabel value="Type Of Cover : #{book.typeOfCover}"></h:outputLabel>
									<br>
									<h:commandLink value="View Details"
										action="#{cartBean.viewProductDetails}">
										<f:param value="#{book.productId}" name="param2"></f:param>
									</h:commandLink>
								</h:column>
							</h:dataTable>

							<h:dataTable value="#{searchBean.listCamera}" var="camera"
								styleClass="productSearchTable"
								rendered="#{not empty searchBean.listCamera}">
								<h:column>
									<h:graphicImage
										url="../page-resources/images/camera_default.png"
										styleClass="bsimg"></h:graphicImage>
								</h:column>
								<h:column>
									<h:outputText value="Product Name : #{camera.productName}"></h:outputText>
									<br>
									<h:outputLabel value="Company     : #{camera.company}"></h:outputLabel>
									<br>
									<h:outputLabel value="Battery     : #{camera.battery}"></h:outputLabel>
									<br>
									<h:outputLabel value="Picture Resolution : #{camera.pixels}"></h:outputLabel>
									<br>
									<h:outputLabel value="Memory      : #{camera.memory}"></h:outputLabel>
									<br>
									<h:outputLabel value="Zoom        : #{camera.opticalZoom}"></h:outputLabel>
									<br>
									<h:commandLink value="View Details"
										action="#{cartBean.viewProductDetails}">
										<f:param value="#{camera.productId}" name="param2"></f:param>
									</h:commandLink>
								</h:column>
							</h:dataTable>

							<h:dataTable value="#{searchBean.listClothing}" var="clothing"
								styleClass="productSearchTable"
								rendered="#{not empty searchBean.listClothing}">
								<h:column>
									<h:graphicImage
										url="../page-resources/images/clothing_default.png"
										styleClass="bsimg"></h:graphicImage>
								</h:column>
								<h:column>
									<h:outputText value="Product Name : #{clothing.productName}"></h:outputText>
									<br>
									<h:outputLabel value="Company     : #{clothing.company}"></h:outputLabel>
									<br>
									<h:outputLabel value="Fabric      : #{clothing.fabric}"></h:outputLabel>
									<br>
									<h:outputLabel value="Ideal For   : #{clothing.idealFor}"></h:outputLabel>
									<br>
									<h:commandLink value="View Details"
										action="#{cartBean.viewProductDetails}">
										<f:param value="#{clothing.productId}" name="param2"></f:param>
									</h:commandLink>
								</h:column>
							</h:dataTable>

							<h:dataTable value="#{searchBean.listComputers}" var="computer"
								styleClass="productSearchTable"
								rendered="#{not empty searchBean.listComputers}">

								<h:column>
									<h:graphicImage
										url="../page-resources/images/computer_default.png"
										styleClass="bsimg"></h:graphicImage>
								</h:column>
								<h:column>
									<h:outputText value="Product Name    : #{computer.productName}"></h:outputText>
									<br>
									<h:outputLabel value="Company        : #{computer.company}"></h:outputLabel>
									<br>
									<h:outputLabel
										value="ProcessorName  : #{computer.processorName}"></h:outputLabel>
									<br>
									<h:outputLabel value="Ram            : #{computer.ram} GB"></h:outputLabel>
									<br>
									<h:outputLabel
										value="Hard Disk      : #{computer.hardDiskSize} GB"></h:outputLabel>
									<br>
									<h:outputLabel
										value="Cell Size      : #{computer.batteryCells}"></h:outputLabel>
									<br>
									<h:commandLink value="View Details"
										action="#{cartBean.viewProductDetails}">
										<f:param value="#{computer.productId}" name="param2"></f:param>
									</h:commandLink>
								</h:column>
							</h:dataTable>


							<h:dataTable value="#{searchBean.listFootware}" var="footwear"
								styleClass="productSearchTable"
								rendered="#{not empty searchBean.listFootware}">
								<h:column>
									<h:graphicImage rendered="#{footwear.idealImageCheck == true}"
											url="../page-resources/images/male_footwear_default.png"
											styleClass="bsimg"></h:graphicImage>

									<h:graphicImage rendered="#{footwear.idealImageCheck == false}"
											url="../page-resources/images/female_footwear_default.png"
											styleClass="bsimg"></h:graphicImage>
									
								</h:column>
								<h:column>
									<h:outputText value="Product Name   : #{footwear.productName}"></h:outputText>
									<br>
									<h:outputLabel value="Company       : #{footwear.company}"></h:outputLabel>
									<br>
									<h:outputLabel value="Material      : #{footwear.material}"></h:outputLabel>
									<br>
									<h:outputLabel value="Ideal For     : #{footwear.idealFor}"></h:outputLabel>
									<br>
									<h:outputLabel value="Weight        : #{footwear.weight}"></h:outputLabel>
									<br>
									<h:commandLink value="View Details"
										action="#{cartBean.viewProductDetails}">
										<f:param value="#{footwear.productId}" name="param2"></f:param>
									</h:commandLink>
								</h:column>
							</h:dataTable>

							<h:dataTable value="#{searchBean.listMobile}" var="mobile"
								styleClass="productSearchTable"
								rendered="#{not empty searchBean.listMobile}">
								<h:column>
									<h:graphicImage
										url="../page-resources/images/mobile_default.png"
										styleClass="bsimg"></h:graphicImage>
								</h:column>
								<h:column>
									<h:outputText value="Product Name    : #{mobile.productName}"></h:outputText>
									<br>
									<h:outputLabel value="Company        : #{mobile.company}"></h:outputLabel>
									<br>
									<h:outputLabel value="Generation     : #{mobile.generation}"></h:outputLabel>
									<br>
									<h:outputLabel value="Memory         : #{mobile.memory} GB"></h:outputLabel>
									<br>
									<h:outputLabel value="Number of Sims : #{mobile.sim}"></h:outputLabel>
									<br>
									<h:outputLabel value="Screen Size    : #{mobile.screenSize}"></h:outputLabel>
									<br>
									<h:commandLink value="View Details"
										action="#{cartBean.viewProductDetails}">
										<f:param value="#{mobile.productId}" name="param2"></f:param>
									</h:commandLink>
								</h:column>
							</h:dataTable>

							<h:dataTable value="#{searchBean.listTelevison}" var="tv"
								styleClass="productSearchTable"
								rendered="#{not empty searchBean.listTelevison}">
								<h:column>
									<h:graphicImage
										url="../page-resources/images/television_default.png"
										styleClass="bsimg"></h:graphicImage>
								</h:column>
								<h:column>
									<h:outputText value="Product Name     : #{tv.productName}"></h:outputText>
									<br>
									<h:outputLabel value="Company         : #{tv.company}"></h:outputLabel>
									<br>
									<h:outputLabel value="HdReady         : #{tv.hdReady}"></h:outputLabel>
									<br>
									<h:outputLabel value="Dimensions      : #{tv.dimension}"></h:outputLabel>
									<br>
									<h:outputLabel
										value="PowerConsumption : #{tv.powerConsumption}"></h:outputLabel>
									<br>
									<h:outputLabel value="Screen Size     : #{tv.screenSize}"></h:outputLabel>
									<br>
									<h:commandLink value="View Details"
										action="#{cartBean.viewProductDetails}">
										<f:param value="#{tv.productId}" name="param2"></f:param>
									</h:commandLink>
								</h:column>
							</h:dataTable>

							<br> <br>
							<h:outputText value="#{searchBean.message}"></h:outputText>
							<h:messages></h:messages>
						</center>
						<br> <br>
					</div>


				</h:form>


			</div>
		</center>

		<!-- Footer -->

		<jsp:include page="footer.jsp"></jsp:include>
	</f:view>
</body>
</html>