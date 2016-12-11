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

public class ReloadCatalogo implements IAction{

	@Override
	public void processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession ses = (HttpSession) request.getSession();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejemplo");
		EntityManager em = emf.createEntityManager();
		
		
		TypedQuery<Product> consultaCatalogo = em.createNamedQuery("Cataloge", Product.class);

		List<Product> catalogo = consultaCatalogo.getResultList();

		ses.setAttribute("catalogo", catalogo);

		em.close();
		
		response.sendRedirect("Index.jsp");
		
	}

}
