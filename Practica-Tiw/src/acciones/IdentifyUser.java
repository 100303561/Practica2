package acciones;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.User;

public class IdentifyUser implements IAction {

	@Override
	public void processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejemplo");
		EntityManager em = emf.createEntityManager();

		// Consulamos la bbdd para saber cual es el usuario
		User u1 = em.find(User.class, id);

		em.close();

		// Pasamos el usuario a la session
		HttpSession misession = (HttpSession) request.getSession();
		misession.setAttribute("usuario", u1);

		// Redirigimos a la pagina que muestra mis productos
		response.sendRedirect("ModifyUser.jsp");
	}

}
