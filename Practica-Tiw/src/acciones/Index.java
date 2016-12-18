package acciones;

import java.io.IOException;
import java.util.List;

import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import es.uc3m.tiw.domains.*;

public class Index implements IAction {

	@Override
	public void processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String city = request.getParameter("city");
		String owner = request.getParameter("owner");
		String category = request.getParameter("category");
		String search = request.getParameter("search");
		

		if (city.equals(""))
			city = null;
		if (category.equals(""))
			category = null;
		if (search.equals(""))
			search = null;
		if(owner.equals("")){
			owner =null;
		}

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejemplo");
		EntityManager em = emf.createEntityManager();
		
		List<Product> catalogo;
		
		if (city != null || category != null || search != null|| owner !=null){
			TypedQuery<Product> advancedSearch = em.createNamedQuery("AdvancedSearch", Product.class);

			advancedSearch.setParameter("city", city);
			advancedSearch.setParameter("category", category);
			
			if (owner == null){
				advancedSearch.setParameter("owner", owner);
			}else {
				advancedSearch.setParameter("owner", '%'+owner+'%');
			}
			if (search == null){
				advancedSearch.setParameter("search", search);
			}else {
				advancedSearch.setParameter("search", '%'+search+'%');
			}
			
			//System.out.println("la consulta es" + advancedSearch.toString());
			catalogo = advancedSearch.getResultList();
		}
		else{
			TypedQuery<Product> consultaCatalogo = em.createNamedQuery("Cataloge", Product.class);

			catalogo = consultaCatalogo.getResultList();
		}

		em.close();

		// Guardamos en la request el catalogo con los productos
		HttpSession misession = (HttpSession) request.getSession();
		misession.setAttribute("catalogo", catalogo);
		// Redirigimos a la pagina catalogo

		RequestDispatcher miR = request.getRequestDispatcher("Index.jsp");

		miR.forward(request, response);
		
		


	}

}
