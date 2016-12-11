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

import clases.User;

public class DeleteUser implements IAction {

	@Override
	public void processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recuperamos el usuario de la sesion para ver los atributos que han
		// sido modificados
		HttpSession misession = (HttpSession) request.getSession();
		User u1 = (User) misession.getAttribute("usuario");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejemplo");
		EntityManager em = emf.createEntityManager();

		System.out.println(u1.toString());

		// Borramos datos de tabla

		em.getTransaction().begin();

		Query q = em.createNamedQuery("DeleteUser", User.class);
		q.setParameter("id", u1.getId());
		q.executeUpdate();

		em.getTransaction().commit();

		em.close();

		if (misession.getAttribute("admin") != null) {
			
			response.sendRedirect("AdminUsers.jsp");
		} else {
		
		
		// invalidamos sesion
		misession.invalidate();

		// Redirigir a la pagina de Index

		RequestDispatcher miR = request.getRequestDispatcher("Login.jsp");

		miR.forward(request, response);
		}
	}

}
