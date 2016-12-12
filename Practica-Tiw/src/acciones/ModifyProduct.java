package acciones;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.Product;
import clases.User;

public class ModifyProduct implements IAction {

	@Override
	public void processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recoger id de la request

		int id = Integer.parseInt(request.getParameter("id"));

		// Recuperamos la session
		HttpSession misession = (HttpSession) request.getSession();

		// Conectarme con la base de datos para escoger el producto

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejemplo");
		EntityManager em = emf.createEntityManager();

		Product producto = em.find(Product.class, id);

		em.close();

		// Guardar producto en la session

		misession.setAttribute("productoModificar", producto);
		// Redirigir a la pagina para modificar el producto

		response.sendRedirect("ModifyProduct.jsp");
	}

}
