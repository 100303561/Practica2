<%@ page import="java.util.*, javax.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>

<%@ page import="es.uc3m.tiw.domains.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">



<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

</head>
<font color="white"><body background="images/wallpaper.jpg">

		<div class="container-fluid-md-12">
			<div class="row">
				<div class="col-md-12">
					<h3 class="text-center">Chats Abiertos con usuarios</h3>
					<div class="row">
						<center>
							<div class="col-md-12">
								<table class="table">

									<thead>
										<tr>

											<th>
												<form name="nombre" method="post"
													action="ControllerServlet">


													<ul id="chatsActivos">
														<div id="chatsActivos" class="nav__notification">
															<%
																List<User> lista = new ArrayList<User>();

																if (request.getAttribute("listaEmisores") != null) {
																	lista = (List<User>) request.getAttribute("listaEmisores");
																	for (int i = 0; i < lista.size(); i++) {
															%>
															<font color="white"><li
																class="nav__notification__num">Has recibido
																	mensajes de:</li></font>
														</div>
													</ul>

												</form>
											</th>

										</tr>
									</thead>
									<tbody>
										<tr>
											<td value=""><font color="white"> <%=lista.get(i).getName()%>
											</font></td>



											<td>


												<form action="ControllerServlet" name="nombre"
													method="post">
													<input type="submit" class="btn btn-info "
														value="Iniciar chat con usuario"> <input
														type="hidden" name="idUserChat"
														value="<%=lista.get(i).getId()%>"> <input
														type="hidden" name="ejecutar" value="LeerChat">

													</button>
											</td>
											</form>
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
		</div>

		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/scripts.js"></script>
	</body>
</html>