<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<!DOCTYPE html>
<%!String com = "0";%>

<html lang="en" class="wide wow-animation">
<head>
<!-- Site Title-->
<title>Login</title>
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
		<header class="page-head"> </header>
		<!-- Page Content-->
		<main class="page-content text-center text-sm-left"> <!--Section Login-->
		<section
			class="section-height-mod-1 bg-image bg-image-1 section-middle">
			<div class="container z-index">
				<div class="row offset-2">
					<div
						class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-offset-0 col-lg-4">
						<div class="jumbotron jumbotron-mod-1 text-center text-md-left">
							<h1>
								Login
								<!-- Rd Mailform result field-->
							</h1>
							<div class="rd-mailform-validate"></div>
							<!-- RD Mailform-->
							<form data-form-type="contact" method="post"
								action="/Practica-Tiw/ControllerServlet"
								class="rd-mailform rd-mailform-mod-1">
								<%
									if (request.getAttribute("com") == com) {
								%>
								<p><%=request.getAttribute("error")%></p>

								<%
									}
								%>
								<input type="hidden" name="action" value="login">
								<input type="text" name="email" placeholder="Username or e-mail">
								<input type="password" name="password" placeholder="Password">
								<input type="submit" class="btn btn-primary btn-md"
									value="Login">
							</form>
							<p>
								Si no estás registrado pulsa <a href="Register.jsp"><font
									color="white">aquí</font></a>
							</p>
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
						<a href="#" class="brand-name">Compra<br> <span>Venta</span></a>
					</div>

					<br> <a href="LoginAdmin.jsp" class="fa-user">Administrador</a>




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
	
</body>
</html>
