<%@ page import="java.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="css/style.css">
<script>
 function mostrarEnviados()
 	{
            
            var mensajeEnviado=document.getElementById("mensajesEnviados");
 			var mensajeEscrito = document.getElementById("mensaje").value;

            	
            	mensajeEnviado.innerHTML+=(mensajeEscrito);
            	mensajeEnviado.innerHTML+=("<br>");
 	}
            </script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
<body background="images/wallpaper.jpg">
<body>
	<div class="contenedorChat">
		<div class="div3">
			<h3>
					<center>
						<img src="images/titulo.jpg"
							style="max-height: 100px; width: 40%;">
				</h3>
		</div>
		<div id="fondochat" class="div1">
			<div class="backgroundChatRecibo">

				<center>

					<form action="ControllerServlet" method="post"
						name="nombre" align="center">
						<font size=4><input type="text" name="mensaje"
							placeholder="Escribe tu mensaje..." style="width: 350px;"></font> <input
							type="hidden" name="action" value="enviarMensaje"> <input
							type="hidden" name="idDestinatario"
							value="<%=request.getParameter("idUserChat")%>">
						<div style="padding: 5px;">
							<input type="submit" class="btn btn-success btn-lg"
								value="Enviar Mensaje" />
						</div>
					</form>
					
					
					
					
					
			</div>
		</div>
	</div>
	<script>
function myFunction() {

	
   	var node = document.createElement("LI");
    var textnode = document.createTextNode("<%=request.getAttribute("mensajeEscrito")%>
		");
			node.appendChild(textnode);
			document.getElementById("enviados").appendChild(node);
		}
	</script>
</body>
</html>