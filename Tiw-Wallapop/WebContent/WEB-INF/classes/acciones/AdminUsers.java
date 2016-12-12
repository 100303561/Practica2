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

import clases.Admin;
import clases.User;

public class AdminUsers implements IAction {

	@Override
	public void processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recogemos los parametros de la request para consultar la bbdd

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejemplo");
		EntityManager em = emf.createEntityManager();

		// Comprobar que el usuario y la contraseña son correctos
		TypedQuery<Admin> consultaAdmin = em.createNamedQuery("AdminLogin", Admin.class);
		consultaAdmin.setParameter("email", email);
		consultaAdmin.setParameter("password", password);

		List<Admin> listaa = consultaAdmin.getResultList();

		Admin a = null;

		// Comprobamos que exista el usuario
		if (!listaa.isEmpty()) {

			a = listaa.get(0);

			HttpSession ses = request.getSession(true);

			// Guardamos el administrador en la session
			ses.setAttribute("admin", a);

			TypedQuery<User> usuarios = em.createNamedQuery("AdminUsers", User.class);

			List<User> lista = usuarios.getResultList();

			em.close();

			ses.setAttribute("lista", lista);

			// Redireccionamos a la pagina de Login
//			RequestDispatcher miR = request.getRequestDispatcher("AdminUsers.jsp");
//
//			miR.forward(request, response);
			
			response.sendRedirect("AdminUsers.jsp");
		} else {
			// Cerramos conexion
			em.close();

			response.sendRedirect("LoginAdmin.jsp");
		}
	}

}
