package acciones;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.Product;

public class Article implements IAction {

	@Override
	public void processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejemplo");
		EntityManager em = emf.createEntityManager();

		Product producto = em.find(Product.class, id);

		em.close();

		// Recuperamos la session
		HttpSession misession = (HttpSession) request.getSession();

		misession.setAttribute("productShow", producto);
		// Redirigir a la pagina para modificar el producto

		response.sendRedirect("Article.jsp");

	}

}
