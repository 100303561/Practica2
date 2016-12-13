<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
										style="max-height: 200px; height: 200px;">
										<div class="panel-body"
											style="max-height: 200px; height: 200px;">

											<%
												List<String> lista = (List<String>) request.getAttribute("mensajes");

												ArrayList<String> colaMensajes = new ArrayList<String>();

												if (request.getAttribute("mensajes") != null) {
													lista = (List<String>) request.getAttribute("mensajes");
													for (int i = 0; i < lista.size(); i++) {
											%>



											<p><%=lista.get(i)%></p>


											<%
												}
											%>



										</div>
									</div></font>

							</div>
						</div>
						<center>

							<form action="ControllerServlet" method="post"
								name="formadmin" align="center">
								<font size=4><input type="text" name="mensaje" placeholder="Respuesta..." style="width: 350px;"></font>
								<input type="hidden" name="ejecutar" value="Responder">
								<input type="hidden" name="idDestinatario"
									value="<%=request.getParameter("idUserChat")%>"> 
									<div style="padding: 5px;"><input
									type="submit" class="btn btn-success btn-lg" value="Responder" /></div>
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