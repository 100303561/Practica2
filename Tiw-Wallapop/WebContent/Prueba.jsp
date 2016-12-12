<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page session="true"%>
    <%@ page import="es.uc3m.tiw.domains.*"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- Recogo el usuario guardado en la session -->
	<%!User u = new User();%>
	<%
		u = (User) session.getAttribute("usuario");
	%>
	
	<h1>Bienvenido <%=u.getName()%></h1>


</body>
</html>