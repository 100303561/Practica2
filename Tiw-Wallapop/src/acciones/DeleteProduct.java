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

import clases.Product;
import clases.User;

public class DeleteProduct implements IAction {

	@Override
	public void processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recogemos el producto de la sesion

		HttpSession misession = (HttpSession) request.getSession();
		Product p = (Product) misession.getAttribute("productoModificar");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejemplo");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Query q = em.createNamedQuery("DeleteProduct", Product.class);
		q.setParameter("id", p.getID());
		q.executeUpdate();

		em.getTransaction().commit();

		em.close();

		// Borramos el objeto de la session
		misession.removeAttribute("productoModificar");

		// Redirigir a la pagina de Index

		// Comprobamos si el que esta modificando el producto es el
		// administrador
		if (misession.getAttribute("admin") != null) {
			response.sendRedirect("AdminUsers.jsp");
		} else {

			RequestDispatcher miR = request.getRequestDispatcher("Index.jsp");

			miR.forward(request, response);
		}

	}

}
