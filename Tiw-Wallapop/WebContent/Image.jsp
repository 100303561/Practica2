<%@ page import = "java.io.*" %>
<%@ page import = "javax.persistence.*" %>
<%@ page import="es.uc3m.tiw.domains.*"%>
<%@ page import = "clases.Product" %>
<jsp:useBean id="photo" class="clases.Product" scope="session" />
<%
 
	int idProduct;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejemplo");
	EntityManager em = emf.createEntityManager();
	
	if (request.getParameter("imgID") != null )
	{	 
		idProduct = Integer.parseInt(request.getParameter("imgID")) ;   
		
		try
		{
		    // get the image from the database
		    Product pr = em.find(Product.class, idProduct);
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
		finally
		{
		    em.close();
		}
	}

%>