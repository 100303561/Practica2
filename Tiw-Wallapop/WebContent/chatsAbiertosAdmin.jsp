<%@ page import="java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="es.uc3m.tiw.domains.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
<form name="nombre" method="post" action="/Practica-Tiw/ServletChat">

<ul id="chatsActivos">
  <div id="chatsActivos" class="nav__notification">
    <li id="12345" class="nav__notification__icon">Esto es un chat de prueba</li>
    <li class="nav__notification__num">Aqui se muestran los chats</li>
  </div>
</ul>


<% while (request.getParameter("listaEmisores")!=null) { %>
<h1> <%=request.getParameter("listaEmisores").toString()%> </h1>
<form action="/Practica-Tiw/ServletChat"name="nombre" method="post" >
<input type="hidden" name="ejecutar" value="LeerChat">
<input type="hidden" name="id" value=<%=request.getParameter("listaEmisores")%>> 
<input type="submit" value="Entrar al chat" > 
<% } %>
</form>

<form action="/Practica-Tiw/ServletChat"name="nombre" method="post" >
<input type="submit" value="Leer Mensajes"> 
<input type="hidden" name="ejecutar" value="LeerChat">   
<input type="hidden" name="id" value=<%=request.getAttribute("listaEmisores")%>></input>
</form>

<!--  
<form action="/Practica-Tiw/ServletChat" method="post" nombre="nombre">
<input type="submit" value="Actualizar"> <----- pulsa para Actualizar chats</input>
<input type="hidden" value="MostrarConversaciones" name="ejecutar">
</form>-->

<form action="/Practica-Tiw/ServletChat" method="post" nombre="EnviarMensajeAdmin">
<input type="input" name="IDConversacionAdmin" placeholder="ID...">
<input type="input" name="MensajeConversacionAdmin" placeholder="Mensaje...">
<input type="hidden" value="EnviarMensajeAdmin" name="ejecutar">
<input type="submit" value="EnviarMensaje">
</form>


<form action="/Practica-Tiw/ServletChat" name="nombre" method="post" >
<input type="hidden" name="ejecutar" value="Pagina Inicio">
<input type="submit" value="VOLVER A INICIO" > 
</form>

</body>
<script>

</script>

</html>