<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page import="es.uc3m.tiw.domains.*"%>
<!DOCTYPE html>
<html lang="en" class="wide wow-animation">
<head>
<!-- Site Title-->
<title>PRODUCTO</title>
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
	<%!Product p = new Product();%>
	<%
		p = (Product) request.getAttribute("productShow");
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
							<div class="slogan">
								<p>Lo mejor en ventas online</p>
							</div>
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
								<a href="#" class="brand-name">Compra<br>
								<span>Vende</span></a>
							</div>
						</div>

					</div>
				</nav>
			</div>
		</header>
		<!-- Page Content-->
		<main class="page-content text-left"> <!-- Section Title Breadcrumbs-->
		<section class="section-full">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						<input type="hidden" name="ProductID" value=<%=p.getId()%>>
						<h1><%=p.getProduct_name()%></h1>
						<p></p>
						<ol class="breadcrumb">
							<li><form name="do3" method="post" action="ControllerServlet">
						 <input type="hidden" name="action" value="reload"> <a
							href="javascript:document.do3.submit()">Index</a>
					</form></li>
							
							
							
							<li>Ver articulo</li>
							<li class="active"><%=p.getProduct_name()%></li>
						</ol>
					</div>
				</div>
			</div>
		</section>
		<!-- Section Catalog Single Left-->
		<section class="section-sm section-sm-mod-2">
			<div class="container">
				<h2><%=p.getProduct_name()%></h2>
				<hr>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-8">
						<div data-lightbox="gallery" class="product-carousel">
							<!-- Slick Carousel-->
							<div class="carousel-slider slider">
								<div class="item" style="width: 450px; height: 450px;">
									<img src="Image.jsp?imgID=<%=p.getId()%>"
										style="width: 720px; height: 450px;">
								</div>

							</div>
							<br> <br>
						</div>
						<div class="col-xs-12">
							<h4>Descripcion del producto</h4>
							<p><%=p.getDescription()%></p>
						</div>
						<div class="row visible-xs-block visible-sm-block">
							<div class="col-xs-12 col-sm-6">
							
							</div>
							<div class="sidebar-module col-xs-12 col-sm-6">
								<div class="price">
									<p class="small">Precio</p>
									<p>
										<span class="h4"><%=p.getPrice()%> €</span><span
											class="h6 text-darker"> </span>
									</p>
									
								</div>
							</div>
						</div>

					</div>
					<div class="col-xs-12 col-sm-12 col-md-4">
						<div class="catalog-sidebar range">
							<div class="sidebar-module cell-sm-12 text-left col-md-push-0">


							</div>
							<div class="sidebar-module cell-sm-6 cell-md-12 cell-md-push-2">
								<ul class="describe-1 list-unstyled">

									<li><span class="fa fa-check-circle"></span>Estado <%=p.getStatus()%></li>
								</ul>
								<ul class="describe-2 list-unstyled preffix-2">
									<li><span class="fa fa-reorder"></span>Categoria <%=p.getCategory()%></li>
									<li><span class="fa fa-money"></span>Precio <%=p.getPrice()%></li>
								</ul>
							</div>
							<div class="sidebar-module cell-sm-6 cell-md-12 cell-md-push-4">
								<ul class="list-unstyled">
									<li><span class="icon icon-darker icon-xxs fa-star-o"></span><a
										href="#" class="preffix-1 text-primary">Añadir a
											favoritos</a></li>
									<li class="offset-6">
										<ul class="list-inline">
											<li><span
												class="icon icon-xxs-mod-1 icon-darker fa-share-square-o"></span><span
												class="preffix-1">Compartir</span></li>
											<li><a href="#" class="fa-facebook"></a></li>
											<li><a href="#" class="fa-twitter"></a></li>
											<li><a href="#" class="fa-pinterest-p"></a></li>
											<li><a href="#" class="fa-google"></a></li>
										</ul>
									</li>
								</ul>
							</div>
							<div class="sidebar-module cell-sm-6 cell-md-12 cell-md-push-5">
								<!-- RD Google Map-->

							</div>
							<div class="sidebar-module cell-sm-6 cell-md-12 cell-md-push-3">
								<div class="price">
									<p class="small">Precio</p>
									<p>
										<span class="h4"> <%=p.getPrice()%> Euros
										</span><span class="h6 text-darker"> </span>
									</p>
									<div class="btn-group-isotope">
										<a href="Chat.jsp"
											class="btn btn-primary btn-primary-transparent btn-md btn-min-width-lg">Contactar</a><a
											href="#"
											class="btn btn-primary btn-primary-transparent btn-md btn-min-width-lg">Comprar</a>
									</div>
								</div>
							</div>


						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- Section Catalog Single Right--> </main>
		<!-- Page Footer-->
		<footer class="page-foot text-center text-md-left bg-gray">
			<div class="container-fluid">
				<div class="footer-wrap">
					<div class="rd-navbar-brand">
						<a href="Index.jsp" class="brand-name">Compra<br>
						<span>Venta</span></a>
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

</body>
</html>