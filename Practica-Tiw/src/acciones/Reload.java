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

import clases.User;

public class Reload implements IAction {

	@Override
	public void processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		


		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejemplo");
		EntityManager em = emf.createEntityManager();
		
		
		TypedQuery<User> usuarios = em.createNamedQuery("AdminUsers", User.class);

		List<User> lista = usuarios.getResultList();

		em.close();

		
		//Recuperamos mi session y guardamos la nueva lista con los valores actualizados
		HttpSession misession = (HttpSession) request.getSession();
		misession.setAttribute("lista", lista);


		
		response.sendRedirect("AdminUsers.jsp");
		
	}

}
