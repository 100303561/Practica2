<%@ page import = "java.io.*" %>
<%@ page import = "javax.persistence.*" %>
<%@ page import="es.uc3m.tiw.domains.*"%>
<%@ page import ="javax.ws.rs.client.*"%>;
<%-- <jsp:useBean id="photo" class="clases.Product" scope="session" /> --%>
<%
 
	int idProduct;
	Client client = ClientBuilder.newClient();
	WebTarget webResource;
	
	
	if (request.getParameter("imgID") != null )
	{	 
		idProduct = Integer.parseInt(request.getParameter("imgID")) ;
		String operacion = "products";
		
		try
		{
		    // get the image from the database
		    webResource = client.target("http://localhost:8020").path(operacion).path(String.valueOf(idProduct));
		    Product pr = webResource.request().accept("application/json").get(Product.class);
		    byte[] imgData = pr.getImagen();
		    // display the image
		    response.setContentType("image/jpeg");
		    OutputStream o = response.getOutputStream();
		    o.write(imgData);
		    o.flush(); 
	    	o.close();
		}
		catch (Exception e)
		{
		  e.printStackTrace();
		  throw e;
		}
		
	}

%>