<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="clases.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page session="true"%>
<%@ page import="es.uc3m.tiw.domains.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="wide wow-animation">

<head>
<!-- Site Title-->
<title>Administracion</title>
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
</head>
<body>


	<!-- Page-->
	<div class="page">
		<!-- Page Header-->
		<header class="page-head">
			<!-- RD Navbar-->
			<div class="rd-navbar-wrap header_corporate">
				<nav class="rd-navbar" data-layout="rd-navbar-fixed"
					data-sm-layout="rd-navbar-fullwidth"
					data-md-layout="rd-navbar-fullwidth">
					<!--RD Navbar Panel-->
					<div class="rd-navbar-top-panel">
						<div class="rd-navbar-top-panel-wrap">
							
							

							<ul class="list-inline">
								<li><a href="#" class="fa-facebook"></a></li>
								<li><a href="#" class="fa-twitter"></a></li>
								<li><a href="#" class="fa-pinterest-p"></a></li>
								<li><a href="#" class="fa-vimeo"></a></li>
								<li><a href="#" class="fa-google"></a></li>
								<li><a href="#" class="fa-rss"></a></li>
							</ul>

						</div>
					</div>
					<div class="rd-navbar-inner inner-wrap">
						<div class="rd-navbar-panel">
							<!-- RD Navbar Toggle-->
							<button data-rd-navbar-toggle=".rd-navbar-nav-wrap"
								class="rd-navbar-toggle">
								<span></span>
							</button>
							<!-- RD Navbar Brand-->
							<div class="rd-navbar-brand">
								<a href="index.jsp" class="brand-name">Compra<br> <span>Venta</span></a>
							</div>
						</div>

					</div>
				</nav>
			</div>
		</header>
		<!-- Page Content-->
		<main class="page-content text-center text-lg-left"> <!-- Section Title Breadcrumbs-->
		<section class="section-full">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						<h1>Administracion de productos</h1>
						<p></p>
						<ol class="breadcrumb">
							<li><a href="AdminUsers.jsp">Admin</a></li>
							<li><a href="AdminUsers.jsp">Administracion usuarios</a></li>
							<li class="active">Administracion productos</li>
						</ol>
					</div>
				</div>
			</div>
		</section>
		<!--Section Tables Main Color Header-->
		<section class="section-md">
			<div class="container">
				<h2>Productos en linea</h2>

				<hr>
				<div class="row">
					<div class="col-lg-10">
						<div class="table-responsive">
							<table class="table table-primary">
								<colgroup>
									<col class="col-xs-2">
									<col class="col-xs-3">
									<col class="col-xs-4">
									<col class="col-xs-4">
									<col class="col-xs-3">
									<col class="col-xs-3">
								</colgroup>

								<thead>
									<tr class="bg-primary">
										<th>ID</th>
										<th>Titulo</th>
										<th>Precio</th>
										<th>Categoria</th>
										<th>Estado</th>

									</tr>
								</thead>
								<tbody>
									<%
										List<Product> lista = new ArrayList<Product>();
										if (session.getAttribute("productosUsuario") != null) {
											lista = (List<Product>) session.getAttribute("productosUsuario");
										}
										for (int i = 0; i < lista.size(); i++) {
									%>
									<tr>
										<td><%=lista.get(i).getID()%></td>
										<td><%=lista.get(i).getProduct_Name()%></td>
										<td><%=lista.get(i).getPrice()%></td>
										<td><%=lista.get(i).getCategory()%></td>
										<td><%=lista.get(i).getStatus().getStatus()%></td>
									</tr>


									<%
										}
									%>

									

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<form action="/Practica-Tiw/ControllerServlet" method="post" name="formadmin" align="center">
				<h5>
					Insertar id para modificar producto</h5> 
					<input type="hidden" name="action" value="modifyProduct">
					
					<input type="number" name="id"
						size="2" placeholder="Id product"> <input type="submit"
						value="Modificar producto">
			</form>

		</section>
		<!--Section Tables Dark Header--> </main>
		<!-- Page Footer-->
		<footer class="page-foot text-center text-md-left bg-gray">
			<div class="container-fluid">
				<div class="footer-wrap">
					<div class="rd-navbar-brand">
						<a href="index.html" class="brand-name">Compra<br> <span>Venta!</span></a>
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
	<!-- Java script-->
	<script src="js/core.min.js"></script>
	<script src="js/script.js"></script>
</body>
</html>