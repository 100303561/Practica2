<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page import="es.uc3m.tiw.domains.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html lang="en" class="wide wow-animation">
<head>
<!-- Site Title-->
<title>Home</title>
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

	<!-- Recojo el usuario guardado en la session -->
	<%!User u = new User();%>
	<%
		u = (User) session.getAttribute("usuario");
		System.out.println("Usuario que llega a index" + u);
	%>

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

							<span></span><a>info@compraventa.shop</a>
						</div>
						<div class="slogan">
							<p>Compra y Vende lo que no necesites</p>
						</div>
						<ul class="list-inline">
							<li><a href="www.facebok.com" class="fa-facebook"></a></li>
							<li><a href="www.twitter.com" class="fa-twitter"></a></li>
							<li><a href="www.google.com" class="fa-google"></a></li>
							<li><a href="#" class="fa-rss"></a></li>
						</ul>

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
								<a href="Index.jsp" class="brand-name">Compra<br> <span>
										y Venta!</span></a>
							</div>
						</div>
						<div class="btn-group">
							<a href="UploadProduct.jsp" class="btn btn-sm btn-primary">Subir
								Articulo</a>
						</div>
						<div class="rd-navbar-nav-wrap">


							<!-- RD Navbar Nav-->
							<!-- RD Navbar Nav-->
							<ul class="rd-navbar-nav">
								<li><form name="do3" method="post"
										action="ControllerServlet">
										<input type="hidden" name="action"
											value="mostrarConversaciones"> <a
											href="javascript:document.do3.submit()" class="fa-comments ">Chat</a>
									</form></li>

								<li><form name="do2" method="post" action="ModifyUser.jsp">
										<a href="javascript:document.do2.submit()" class="fa-cogs ">Espacio
											Personal</a>
									</form></li>


								<li><form name="do1" method="post"
										action="ControllerServlet">
										<input type="hidden" name="action" value="showProduct">
										<a href="javascript:document.do1.submit()"
											class="fa-newspaper-o ">Mis Anuncios</a>
									</form></li>
								<li>
									<form name="do" method="post" action="ControllerServlet">
										<input type="hidden" name="action" value="logout"> <a
											href="javascript:document.do.submit()" class="fa-sign-in ">LogOut</a>
									</form>
								</li>
							</ul>



						</div>
					</div>
				</nav>
			</div>
		</header>
		<section>





			<!--Swiper-->
			<div data-height="" data-min-height="600px"
				class="swiper-container swiper-slider">
				<div class="swiper-wrapper">
					<div data-slide-bg="images/slide-1.jpg" class="swiper-slide">
						<div class="swiper-slide-caption">
							<div class="container">
								<p data-caption-animate="fadeInDown" class="h1">
									Veces visitados hoy :
									<%=application.getAttribute("contador")%></p>

								<a href="#" data-caption-animate="fadeInUp"
									data-caption-delay="400" class="btn btn-sm btn-transparent">CATALOGO</a>
							</div>
						</div>
					</div>
					<div data-slide-bg="images/slide-2.jpg" class="swiper-slide">
						<div class="swiper-slide-caption">
							<div class="container">
								<p data-caption-animate="fadeInDown" class="h1">Vende lo que
									no usas</p>

								<a href="#" data-caption-animate="fadeInUp"
									data-caption-delay="400" class="btn btn-sm btn-transparent">CATALOGO</a>
							</div>
						</div>
					</div>
					<div data-slide-bg="images/slide-3.jpg" class="swiper-slide">
						<div class="swiper-slide-caption">
							<div class="container">
								<p data-caption-animate="fadeInDown" class="h1">Negocia</p>

								<a href="#" data-caption-animate="fadeInUp"
									data-caption-delay="400" class="btn btn-sm btn-transparent">CATALOGO</a>
							</div>
						</div>
					</div>
				</div>
				<!-- Swiper Pagination-->
				<div class="swiper-pagination"></div>
			</div>
		</section>

		<!-- Page Content-->
		<div class="page-content">
			<!--Section Find your home-->
			<section class="section-sm section-sm-mod-1 bg-dark">
				<div class="container position-margin-top undefined">
					<div class="search-form-wrap bg-white container-shadow">

						<h3>Encuentra lo que necesites aqui</h3>
						<form action="ControllerServlet" method="post" name="search-form"
							class="form-variant-1">
							<input type="hidden" name="action" value="index"> <label
								for="keyword">Introduzca su busqueda aqui</label> <input
								id="search" type="text" size="50" name="search"
								placeholder="Introduzca palabra clave"> <input
								type="submit" class="btn btn-md btn-primary btn-xs"
								value="Buscar">
							<div class="features">
								<a href="" onclick="return false" class="btn-features"><span></span>Busqueda
									avanzada</a>
								<ul class="checkbox list-inline">
									<li><label for="select-7">Categoria</label> <select
										id="select-2" name="category">
											<option value="">Elige categoria</option>
											<option value="Hogar">Hogar</option>
											<option value="Tecnologia">Tecnologia</option>
											<option value="Vehiculos">Vehiculos</option>
											<option value="Ropa">Ropa</option>
									</select></li>
									<li><label for="range-1">Dueño<br></label> <input
										id="owner" type="text" name="owner"
										class="rd-range-input-value rd-range-input-value-2"></li>
									<li><label for="select-7">Comunidad</label> <select
										name="city">
											<option value="">Elija su Comunidad Autonoma</option>
											<option style="color: black" value="Madrid">Madrid</option>
											<option style="color: black" value="Barcelona">Barcelona</option>
											<option style="color: black" value="Sevilla">Valencia</option>
											<option style="color: black" value="Galicia">Galicia</option>
											<option style="color: black" value="Valencia">Sevilla</option>
											<option style="color: black" value="Extremadura">Extremadura</option>

									</select></li>
								</ul>
							</div>
						</form>
					</div>
				</div>
			</section>


			<!-- <img src="Image.jsp?imgID=1" alt="" width="370" height="250" /> -->
			<!--Section Categories-->
			<section
				class="section-sm section-sm-mod-3 bg-dark text-center text-sm-left">
				<div class="shell">
					<h2>Catalogo</h2>
					<hr>
					<div class="row clearleft-custom-3">
						<%
							List<Product> lista = new ArrayList<Product>();
							lista = (List<Product>) request.getAttribute("catalogo");

							if (request.getAttribute("catalogo") != null) {
								lista = (List<Product>) request.getAttribute("catalogo");

								for (int i = 0; i < lista.size(); i++) {
						%>
						<div class="col-xs-12 col-md-4 col-sm-6">
							<div class="category">
								<img src="Image.jsp?imgID=<%=lista.get(i).getId()%>" alt=""
									width="370" height="250" />
								<form action="ControllerServlet" name="form" method="post">
									<input type="hidden" name="action" value="product"> <input
										type="hidden" name="id" value="<%=lista.get(i).getId()%>">
									<div class="category-content">
										<h4><%=lista.get(i).getProduct_name()%></h4>
										<input type="submit" value="ver articulo"
											class="btn btn-sm btn-primary">
									</div>
								</form>
							</div>
						</div>

						<%
							}
							}
						%>

					</div>

				</div>
			</section>
		</div>
		<!-- Page Footer-->
		<footer class="page-foot text-center text-md-left bg-gray">
			<div class="container-fluid">
				<div class="footer-wrap">
					<div class="rd-navbar-brand">
						<a href="Index.jsp" class="brand-name">Compra<br> <span>Venta!</span></a>
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
								href="#">Terms of Use and Privacy Policy</a>
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