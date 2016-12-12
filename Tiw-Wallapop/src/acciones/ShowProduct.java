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

public class ShowProduct implements IAction {

	@Override
	public void processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recuperamos el usuario de la sesion para ver los atributos que han
		// sido modificados

		HttpSession misession = (HttpSession) request.getSession();
		User u1 = (User) misession.getAttribute("usuario");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejemplo");
		EntityManager em = emf.createEntityManager();
		
		//Realizamos la consulta para saber cuales son mis productos por el ID
		TypedQuery<Product> q = em.createNamedQuery("ShowMyProduct", Product.class);
		q.setParameter("idUser", u1);
		
		//Guardamos los resultados en una lista
		List<Product> lista = q.getResultList();

		em.close();
		

		//Pasamos la lista de mis productos a la session
		misession.setAttribute("misproductos", lista);
		
		//Redirigimos a la pagina que muestra mis productos
		response.sendRedirect("ShowProduct.jsp");
		
		
		
		

	}

}
