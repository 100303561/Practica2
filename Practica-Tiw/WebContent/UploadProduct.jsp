<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page import="es.uc3m.tiw.domains.*"%>
<!DOCTYPE html>
<html lang="en" class="wide wow-animation">
<head>
<!-- Site Title-->
<title>Perfil personal</title>
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
	<!-- Recogo el usuario guardado en la session -->
	<%!User u = new User();%>
	<%
		u = (User) session.getAttribute("usuario");
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
						<h1>Perfil personal</h1>
						<p></p>
						<ol class="breadcrumb">
							<li><a href="Index.jsp">Index</a></li>
							<li class="active">Perfil personal</li>
						</ol>
					</div>
				</div>
			</div>
		</section>
		<!--Section Contact Us 1-->
		<section class="section-sm">
			<div class="container">
				<div class="row" style="width: auto;">
					<div class="col-md-8">
						<h2>Añade tu nuevo producto</h2>
						<hr>
						

						<div class="col-xs-12 offset-2">
							


						<form class="inset-6" action="ControllerServlet"
					method="post" enctype="multipart/form-data">

					<div class="col-sm-6">
						<!-- RD form-->

						<input type="hidden" name="action" value="uploadProduct">
						<div class="form-group">
							<label for="title" class="small">¿Que vendes?</label> <input
								id="title" type="text" name="title"
								placeholder="Indica brevemente lo que aparecerÃ¡ en el anuncio"
								class="form-control"required>
						</div>
						<div class="form-group">
							<label for="message" class="small">Descri­belo</label>
							<textarea id="message" name="message"
								placeholder="Introduce una descripciÃ³n de tu producto." required></textarea>
						</div>
						<div class="row"></div>
						<div class="col-sm-6">
							<div class="form-group">
								<label for="select-2" class="small">Localizacion</label> <select
									id="select-2" name="city"
									class="rd-mailform-select form-control" required>
									<option value="">Elige ciudad</option>
									<option value="Madrid">Madrid</option>
									<option value="Valencia">Valencia</option>
									<option value="Barcelona">Barcelona</option>
									<option value="Extremadura">Extremadura</option>
									<option value="Galicia">Galicia</option>
									<option value="Sevilla">Sevilla</option>

								</select>
							</div>


						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label for="select-2" class="small">Categoria</label> <select
									id="select-2" name="category"
									class="rd-mailform-select form-control" required>
									<option value="">Elige categoria</option>
									<option value="Hogar">Hogar</option>
									<option value="Tecnologia">Tecnologia</option>
									<option value="Ropa">Ropa</option>
									<option value="Vehiculos">Vehiculos</option>
								</select>
							</div>


						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label for="price" class="small">Precio</label> <input
									id="price" type="number" min="0" step="any" name="price" placeholder="Precio"
									class="form-control" required>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-6">
						<div class="google-search">
							<div class="form-group">
								<label for="address" class="small">Subir imagen</label> <input
									id="address" type="file" name="fileToUpload"
									placeholder="No title" src="" required>


								<div class="col-xs-12 offset-8">
									<button type="submit"
										class="btn btn-sm btn-primary btn-min-width-md">Subir
										Producto</button>
								</div>
							</div>
						</div>
					</div>
				</form>
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
							href="#">Terms of Use and Privacy Policy</a>
					</p>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>