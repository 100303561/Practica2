<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page import="es.uc3m.tiw.domains.*"%>

<!DOCTYPE html>
<html lang="en" class="wide wow-animation">
<head>
<!-- Site Title-->
<title>Modificar producto</title>
<meta name="format-detection" content="telephone=no">
<meta name="viewport"
	content="width=device-width, height=device-height, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta charset="utf-8">
<link rel="icon" href="images/favicon.ico" type="image/x-icon">
<!-- Stylesheets-->
<link rel="stylesheet" type="text/css"
	href="//fonts.googleapis.com/css?family=Ubuntu:400,300,300italic,700%7CMontserrat:400,700">
<link rel="stylesheet" href="css/style.css">
<!--[if lt IE 10]>
    <div style="background: #212121; padding: 10px 0; box-shadow: 3px 3px 5px 0 rgba(0,0,0,.3); clear: both; text-align:center; position: relative; z-index:1;"><a href="http://windows.microsoft.com/en-US/internet-explorer/"><img src="images/ie8-panel/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today."></a></div>
    <script src="js/html5shiv.min.js"></script>
		<![endif]-->
<script type="text/javascript">
	function ConfirmDelete() {
		var confirmar = confirm("Are you Sure?");
		if (!confirmar) {
			return false;
		} else {
			alert("Sus datos serán eliminados")
			return true;
		}
	}
</script>

</head>
<body>
	<!-- Recoger usuario por el id desde lista de productos -->
	<%!Product p = new Product();%>
	<%
		p = (Product) session.getAttribute("productoModificar");
	%>
	<div class="page">
		<!-- Page Header-->
		<header class="page-head"> </header>
		<!-- Page Content-->
		<main class="page-content text-left"> <!-- Section Title Breadcrumbs-->
		<section class="section-full">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						<h1><%=p.getProduct_name()%></h1>
						<p></p>
						<ol class="breadcrumb">
							<li><form name="do3" method="post" action="ControllerServlet">
						 <input type="hidden" name="action" value="reload"> <a
							href="javascript:document.do3.submit()">Index</a>
					</form></li>
							<li class="active">Producto</li>
						</ol>
					</div>
				</div>
			</div>
		</section>
		<!--Section Contact Us 1-->
		<section class="section-sm">
			<div class="container">
				<div class="row">
					<div class="col-md-8">
						<h2>Producto</h2>
						<hr>
						<p>Aquí puede ver y modificar sus datos de producto.</p>
						<p>No se olvide de comprobar que los datos son correctos</p>

						<div class="col-xs-12 offset-2">
							<h4>Datos de producto</h4>
							<!-- Rd Mailform result field-->
							<div class="rd-mailform-validate"></div>
							<!-- RD Mailform-->
							<form data-form-type="contact" method="post"
								action="ControllerServlet" class="rd-mailform">
								<div class="row">
									<div class="col-sm-6">
										<input type="hidden" name="action" value="updateProduct">
										<input type="text" name="name"
											Value="<%=p.getProduct_name()%>">
									</div>
									<div class="col-sm-6 input-mod-1">
										<input type="number" name="price" value="<%=p.getPrice()%>">
									</div>
								</div>
								<input type="textbox" name="desc"
									value="<%=p.getDescription()%>"> 
									<select	id="select-2" name="category">
										<option value="<%=p.getCategory()%>"><%=p.getCategory()%></option>
										<option value="Hogar">Hogar</option>
										<option value="Tecnologia">Tecnologia</option>
										<option value="Vehiculos">Vehiculos</option>
										<option value="Ropa">Ropa</option>
								</select>
									
									
									<select	name="status" class="rd-mailform-select" required>
									<option value="<%=p.getStatus()%>"><%=p.getStatus()%></option>
									<option style="color: black" value="Disponible">Disponible</option>
									<option style="color: black" value="Vendido">Vendido</option>
									<option style="color: black" value="Reservado">Reservado</option>

								</select> <input type="submit" class="btn btn-primary btn-md"
									value="Modificar producto">
							</form>
						</div>
						<form method="post" action="ControllerServlet"
							name="borrar" onsubmit="return ConfirmDelete()">
							<input type="hidden" name="action" value="deleteProduct">

							<input type="submit" value="Eliminar Producto" />
						</form>
					 <div class="sidebar-module cell-sm-6 cell-md-12 cell-md-push-2">
						
						<div class="item" style="width: 350px; height: 350px;">
							<img src="Image.jsp?imgID=<%=p.getId()%>"
								style="width: 720px; height: 450px;">
						</div>
						</div>

					</div>
				</div>

			</div>
		</section>
		</main>
		<!-- Page Footer-->
		<footer class="page-foot text-center text-md-left bg-gray">
			<div class="container-fluid">
				<div class="footer-wrap">
					<div class="rd-navbar-brand">
						<a href="#" class="brand-name">Compra<br> <span>Venta</span></a>
					</div>
					<ul class="list-inline">
						<li><a href="#" class="fa-facebook"></a></li>
						<li><a href="#" class="fa-twitter"></a></li>
						<li><a href="#" class="fa-pinterest-p"></a></li>
						<li><a href="#" class="fa-vimeo"></a></li>
						<li><a href="#" class="fa-google"></a></li>
						<li><a href="#" class="fa-rss"></a></li>
					</ul>
					<div class="copyright">
						<p>
							&#169;<span id="copyright-year"></span> All Rights Reserved <a
								href="terms.html">Terms of Use and Privacy Policy</a>
						</p>
					</div>
				</div>
			</div>
		</footer>
	</div>

</body>
</html>