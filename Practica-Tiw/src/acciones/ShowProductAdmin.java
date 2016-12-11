package acciones;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.Product;
import clases.User;

public class ShowProductAdmin implements IAction {

	@Override
	public void processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Recogemos el id del usuario que queremos mostrar sus productos.
		int id = Integer.parseInt(request.getParameter("id"));
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejemplo");
		EntityManager em = emf.createEntityManager();
		
		//Consulamos la bbdd para saber cual es el usuario
		User u1 = em.find(User.class, id);
		
		//Realizamos la consulta para saber cuales son los productos del usuario
		TypedQuery<Product> q = em.createNamedQuery("ShowMyProduct", Product.class);
		q.setParameter("idUser", u1);
		
		//Guardamos los resultados en una lista
		List<Product> lista = q.getResultList();

		em.close();
		

		//Pasamos la lista de mis productos a la session
		HttpSession misession = (HttpSession) request.getSession();
		misession.setAttribute("productosUsuario", lista);
		
		//Redirigimos a la pagina que muestra mis productos
		response.sendRedirect("ShowProductAdmin.jsp");
		
	}

}
