<!DOCTYPE html>
<html lang="en" class="wide wow-animation">
<%@ page import="es.uc3m.tiw.domains.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

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
		</header>
		<!-- Page Content-->
		<main class="page-content text-center text-lg-left"> <!-- Section Title Breadcrumbs-->
		<section class="section-full">
			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						<h1>Administracion de usuarios</h1>
						<p></p>
						<ol class="breadcrumb">
							<li><form name="do3" method="post"
									action="ControllerServlet">
									<input type="hidden" name="action" value="reload"> <a
										href="javascript:document.do3.submit()">Admin</a>
								</form></li>

							<li class="active">Administracion Usuarios</li>
						</ol>
					</div>
				</div>
			</div>
		</section>
		<ul class="rd-navbar-nav">
			<li><form name="do3" method="post" action="ControllerServlet">
					<input type="hidden" name="action" value="mostrarConversaciones">
					<input type="submit" value="Chat">
				</form></li>
		</ul>
		<!--Section Tables Main Color Header-->
		<section class="section-md">
			<div class="container">
				<h2>Usuarios registrados</h2>

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
										<th>Nombre</th>
										<th>Apellidos</th>
										<th>Email</th>
										<th>Contraseña</th>
										<th>Ciudad</th>
										<th></th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<%
										List<User> lista = new ArrayList<User>();
										if (request.getAttribute("lista") != null) {
											lista = (List<User>) request.getAttribute("lista");
										}
										for (int i = 0; i < lista.size(); i++) {
											if (lista.get(i).getAdmin() == 0) {
									%>
									<tr>
										<td><%=lista.get(i).getId()%></td>
										<td><%=lista.get(i).getName()%></td>
										<td><%=lista.get(i).getSurname()%></td>
										<td><%=lista.get(i).getEmail()%></td>
										<td><%=lista.get(i).getPassword()%></td>
										<td><%=lista.get(i).getCity()%></td>
										<td><form action="ControllerServlet" method="post"
												name="formadmin" align="center">
												<input type="hidden" name="id"
													value="<%=lista.get(i).getId()%>"> <input
													type="hidden" name="action" value="identifyUser"><input
													type="submit" value="Modificar Usuario" />
											</form></td>
										<td><form action="ControllerServlet" method="post"
												name="formadmin" align="center">
												<input type="hidden" name="id"
													value="<%=lista.get(i).getId()%>"> <input
													type="hidden" name="action" value="showProductAdmin"><input
													type="submit" value="Ver Productos" />
											</form></td>
									</tr>


									<%
										}
										}
									%>

									</tr>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>


		</section>
		<!--Section Tables Dark Header--> </main>
		<!-- Page Footer-->
		<footer class="page-foot text-center text-md-left bg-gray">
			<div class="container-fluid">
				<div class="footer-wrap">
					<div class="rd-navbar-brand">
						<a href="#" class="brand-name">Compra<br> <span>Venta!</span></a>
					</div>
					<form name="do" method="post" action="ControllerServlet">
						<input type="hidden" name="action" value="logout"> <a
							href="javascript:document.do.submit()" class="fa-sign-in ">LogOut</a>
					</form>
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