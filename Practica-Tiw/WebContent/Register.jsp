<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<%@ page session="true"%>
<!DOCTYPE html>
<%!String com = "0";%>
<html lang="en" class="wide wow-animation">
<head>
<!-- Site Title-->
<title>Register</title>
<script type="text/javascript">
	function comprobar() {
		var mirarLargo = "no";
		var mirarIgual = "no";
		var contra1 = document.formulario.clave1
		var contra2 = document.formulario.clave2

		if (contra1.value.length >= 6) {
			mirarLargo = "si"
		} else {
			alert("La contraseña debe tener mas de 6 caracteres");

		}
		if (contra1.value == contra2.value) {
			mirarIgual = "si"
		} else {
			alert("La contraseñas deben deben ser iguales")
		}
		if (mirarLargo == "no" || mirarIgual == "no") {
			return false
		}
	}
</script>
<meta name="format-detection" content="telephone=no">
<meta name="viewport"
	content="width=device-width, height=device-height, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta charset="utf-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


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
							<div class="login">
								<a href="register.html"><span class="fa-user"></span>
									Login/Registration</a>
							</div>
							<div class="contact-info">
								<a href="callto:#"><span class="material-icons-local_phone"></span>
									1-800-1234-567</a><a href="mailto:#"><span
									class="material-icons-drafts"></span> info@demolink.org</a>
							</div>
							<div class="slogan">
								<p>Professional realtor works for you</p>
							</div>
							<ul class="list-inline">
								<li><a href="#" class="fa-facebook"></a></li>
								<li><a href="#" class="fa-twitter"></a></li>
								<li><a href="#" class="fa-pinterest-p"></a></li>
								<li><a href="#" class="fa-vimeo"></a></li>
								<li><a href="#" class="fa-google"></a></li>
								<li><a href="#" class="fa-rss"></a></li>
							</ul>
							<div class="btn-group">
								<a href="submit-property.html" class="btn btn-sm btn-primary">Submit
									Property</a>
							</div>
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
								<a href="#" class="brand-name">Compra<br> <span>Venta</span></a>
							</div>
						</div>
						<div class="btn-group">
							<a href="submit-property.html" class="btn btn-sm btn-primary">Submit
								Property</a>
						</div>
						<div class="rd-navbar-nav-wrap"></div>
					</div>
				</nav>
			</div>
		</header>
		<!-- Page Content-->
		<main class="page-content text-center text-sm-left"> <!--Section Login-->
		<center>
			<section
				class="section-height-mod-1 bg-image bg-image-1 section-middle">
				<div class="container text-white text-center text-md-left z-index">
					<h1>Crear una cuenta</h1>
					<div class="row offset-2">
						<div
							class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0 col-lg-4">
							<div class="jumbotron jumbotron-mod-1 text-center text-md-left">
								<!-- Rd Mailform result field-->
								<div class="rd-mailform-validate"></div>
								<!-- RD Mailform-->
								<form data-form-type="contact" name="formulario"
									action="/Practica-Tiw/ControllerServlet" method="post"
									class="rd-mailform rd-mailform-mod-1"
									onsubmit="return comprobar()">
									<input type="text" name="name" placeholder="Nombre" required>
									<input type="text" name="surname" placeholder="Apellido"
										required>
									<%
										if (session.getAttribute("com") == com) {
									%>
									<p><%=session.getAttribute("error")%></p>

									<%
										}
									%>
									<input type="hidden" name="action" value="register">
									<input type="text" name="email" placeholder="Your e-mail"
										required> <input type="password" name="clave1"
										placeholder="Contraseña" required> <input
										type="password" name="clave2"
										placeholder="Confirme contraseña" required> <select
										name="city" class="rd-mailform-select" required>
										<option value="">Elija su Comunidad Autónoma</option>
										<option style="color: black" value="Madrid">Madrid</option>
										<option style="color: black" value="Barcelona">Barcelona</option>
										<option style="color: black" value="Sevilla">Valencia</option>
										<option style="color: black" value="Galicia">Galicia</option>
										<option style="color: black" value="Valencia">Sevilla</option>
										<option style="color: black" value="Extremadura">Extremadura</option>

									</select> <input type="submit" value="Register"
										class="btn btn-primary btn-md">
								</form>

								<p>O registrate con</p>
								<div class="btn-group-mod-2">
									<a href="#" class="btn btn-sm btn-info"><span
										class="fa-facebook"></span></a><a href="#"
										class="btn btn-sm btn-info-2"><span class="fa-twitter"></span></a><a
										href="#" class="btn btn-sm btn-danger"><span
										class="fa-google-plus"></span></a>
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
						<a href="index.html" class="brand-name">Grand<br> <span>Estate</span></a>
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