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

public class Login implements IAction {

	@Override
	public void processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		System.out.println("email y contraseña recogidos" + email + password);


		// Consultar bbdd para saber cual es el nombre del email

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejemplo");
		EntityManager em = emf.createEntityManager();
		TypedQuery<User> consultaAlumnos = em.createNamedQuery("UserLogin", User.class);
		consultaAlumnos.setParameter("email", email);
		consultaAlumnos.setParameter("password", password);

		List<User> lista = consultaAlumnos.getResultList();

		User a = null;

		// Comprobamos que exista el usuario
		if (!lista.isEmpty()) {
			a = lista.get(0);

			HttpSession ses = request.getSession(true);

			ses.setAttribute("usuario", a);

			System.out.println("Datos recogidos del usuario en java" + a);
			// Se realiza consulta a la lista de productos de la bbdd
			TypedQuery<Product> consultaCatalogo = em.createNamedQuery("Cataloge", Product.class);

			List<Product> catalogo = consultaCatalogo.getResultList();

			ses.setAttribute("catalogo", catalogo);

			em.close();
			
			response.sendRedirect("Index.jsp");

		} else {

			// Cerramos conexion
			em.close();

			response.sendRedirect("Login.jsp");
		}

	}

}
