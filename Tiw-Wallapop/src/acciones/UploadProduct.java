package acciones;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import clases.Product;
import clases.Status;
import clases.User;

public class UploadProduct implements IAction {

	@Override
	public void processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Usuario para guardar datos modificados
		Product p = new Product();

		// Parametros obtenidos en el formulario para inertrar productos

		p.setCategory(request.getParameter("category"));
		p.setDescription(request.getParameter("message"));
		p.setProduct_Name(request.getParameter("title"));
		p.setPrice(Double.parseDouble(request.getParameter("price")));
		
		
		
		
		// Es posible usar librerias de terceros para pasar un InputStream a byte[]
		// http://www.baeldung.com/convert-input-stream-to-array-of-bytes
		
		/*Alberto intenta insertar las imagenes, creemos que es asi pero nos salta 
		 * un error que no sabemos resovler, esta hecho de la forma que lo 
		 * hace el profesor si consigues hacerlo de otra forma que le peten y fuera*/
		
		Part filePart = request.getPart("fileToUpload");

	    byte[] data = new byte[(int) filePart.getSize()];
	    
	    filePart.getInputStream().read(data, 0, data.length);
	    
		p.setImagen(data);

		
		
		

		// Recuperamos el usuario de la sesion para ver los atributos que han
		// sido modificados
		HttpSession misession = (HttpSession) request.getSession();
		User u1 = (User) misession.getAttribute("usuario");

		// Poner el id_user de la sesion
		p.setUser(u1);

		// Guardamos en el nuevo usuario la id que se encuentra en sesion
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejemplo");
		EntityManager em = emf.createEntityManager();

		//System.out.println(u1.toString());

		// Si los nombres son distintos los modifico
		
		Status st = em.find(Status.class, 1);
		p.setStatus(st);
		
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();

			
		
		
		em.close();

	

		// Redirigir a la pagina de Index

		response.sendRedirect("Index.jsp");

		

	}

}
