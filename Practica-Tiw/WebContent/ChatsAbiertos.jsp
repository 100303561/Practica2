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
					<h3 class="text-center">
						<font color="white" size=7>Chats abiertos con usuarios</font>
					</h3>
					<div class="col-md-4" value="Volver a conversaciones"
					style="margin-bottom: 25px; border-radius: 15px;">

					<form name="do3" method="post" action="ControllerServlet">
						<input type="submit" class="btn btn-warning"
							style="border-radius: 10px; border: 1px solid black; max-width: auto; max-height: auto;"
							value="Volver a Index"> <input type="hidden"
							name="action" value="reload"> <a
							href="javascript:document.do3.submit()"></a>
					</form>

				</div>
					<div class="row">
						<center>
							<div class="col-md-12">
								<table class="table">

									<thead>
										<tr>

											<th>
												<form name="nombre" method="post" action="ControllerServlet">


													<ul id="chatsActivos">
														<font color="white" size=5><li
															class="nav__notification__num">Contactar con:</li></font>
														<div id="chatsActivos" class="nav__notification"></div>
													</ul>

												</form>
											</th>

										</tr>
									</thead>
									<tbody>
										<tr>
											<%
												List<User> lista = new ArrayList<User>();

												if (request.getAttribute("listaEmisores") != null) {
													lista = (List<User>) request.getAttribute("listaEmisores");
													for (int i = 0; i < lista.size(); i++) {
														if (session.getAttribute("admin") != null) {
															if (lista.get(i).getAdmin() == 0) {
											%>
											<td value=""><font color="white"> <%=lista.get(i).getName()%>
											</font></td>



											<td>


												<form action="ControllerServlet" name="nombre" method="post">
													<input type="submit" class="btn btn-info "
														value="Iniciar chat con usuario"> <input
														type="hidden" name="idUserChat"
														value="<%=lista.get(i).getId()%>"> <input
														type="hidden" name="action" value="LeerChat">

													</button>
											</td>
											</form>
											<%
												}
														} else {
											%>
							
											
											<td value=""><font color="white"> <%=lista.get(i).getName()%>
											</font></td>



											<td>


												<form action="ControllerServlet" name="nombre" method="post">
													<input type="submit" class="btn btn-info "
														value="Iniciar chat con usuario"> <input
														type="hidden" name="idUserChat"
														value="<%=lista.get(i).getId()%>"> <input
														type="hidden" name="action" value="LeerChat">

													</button>
											</td>
											</form>



											<%
												}
													}%>
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
		</div>

		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/scripts.js"></script>
	</body>
</html>