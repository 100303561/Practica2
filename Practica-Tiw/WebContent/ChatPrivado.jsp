<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="es.uc3m.tiw.domains.*"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chat Privado</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

</head>
<body background="images/wallpaper.jpg">

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h3>
					<center>
						<img src="images/titulo.jpg"
							style="max-height: 100px; width: 40%;">
				</h3>
				<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-4" background="white">
						<div class="row">
							<div class="col-md-12" style="border: 2px, white;">

								<font size=3><div class="panel panel-danger"
										style="height: auto;">
										<div class="panel-body" style="height: auto;">

											<%
												List<Mensaje> lista = (List<Mensaje>) request.getAttribute("mensajes");
												User u = (User) session.getAttribute("usuario");

												if (request.getAttribute("mensajes") != null) {

													for (int i = 0; i < lista.size(); i++) {

														if (u.getId() == (lista.get(i)).getIdemisor()) {
											%>

											<style>
.enviado{
	color: green;
	text-align: right;
}
</style>
<p class="enviado"><%=(lista.get(i)).getMensaje()%></p1>
											<%
												} else {
											%>

											<style>
.recibido{
	color: blue;
	text-align: left;
}
</style><p class="recibido"><%=(lista.get(i)).getMensaje()%></p2>

											<%
												}
											%>

											<%-- <p><%=(lista.get(i)).getMensaje()%></p> --%>



											<%
													}
												
											%>



										</div>
									</div></font>

							</div>
						</div>
						<center>

							<form action="ControllerServlet" method="post" name="formadmin"
								align="center">
								<font size=4><input type="text" name="mensaje"
									placeholder="Respuesta..." style="width: 350px;"></font> <input
									type="hidden" name="action" value="enviarMensaje"> <input
									type="hidden" name="idDestinatario"
									value="<%=request.getParameter("idUserChat")%>">
									
									
									
									
								<div style="padding: 5px;">
									<input type="submit" class="btn btn-success btn-lg"
										value="Responder" />
								</div>
							</form>

							<%
								}
							%>
						
					</div>
					<div class="col-md-4"></div>
				</div>
			</div>
		</div>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/scripts.js"></script>
</body>
</html>