package acciones;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.User;

public class ModifyUser implements IAction {

	@Override
	public void processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession misession = (HttpSession) request.getSession();
		User u1 = (User) misession.getAttribute("usuario");


		u1.setName(request.getParameter("name"));
		u1.setSurname(request.getParameter("surname"));
		u1.setCity(request.getParameter("city"));
		u1.setPassword(request.getParameter("clave1"));

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejemplo");
		EntityManager em = emf.createEntityManager();


		// Comienza la transaccion
		em.getTransaction().begin();

		em.merge(u1);
		em.getTransaction().commit();

		em.close();

		// Redirigir a la pagina de Index

		System.out.println("El usuario modificado es: " + u1);

		if (misession.getAttribute("admin") != null) {

			response.sendRedirect("AdminUsers.jsp");
		} else {

			RequestDispatcher miR = request.getRequestDispatcher("Index.jsp");

			miR.forward(request, response);

		}

	}

}
