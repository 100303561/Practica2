package acciones;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.Product;
import clases.Status;
import clases.User;

public class UpdateProduct implements IAction {

	@Override
	public void processAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recuperamos mi sesion para sacar el id del producto a modificar
		HttpSession misession = (HttpSession) request.getSession();
		Product p1 = (Product) misession.getAttribute("productoModificar");

		// Parametros obtenidos en el formulario para modificar

		p1.setProduct_Name(request.getParameter("name"));
		p1.setCategory(request.getParameter("category"));
		//Ñapa de ultima hora
		if (!(String.valueOf(p1.getPrice()).equals(request.getParameter("price")))){
			p1.setPrice(Integer.parseInt(request.getParameter("price")));
		}
		
		p1.setDescription(request.getParameter("desc"));

		// Conexion con la bbdd
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejemplo");
		EntityManager em = emf.createEntityManager();

		// Comienza la transaccion
		em.getTransaction().begin();

		em.merge(p1);
		em.getTransaction().commit();

		em.close();


		// Comprobamos si el que esta modificando el producto es el
		// administrador
		if (misession.getAttribute("admin") != null) {
			response.sendRedirect("AdminUsers.jsp");
		} else {

			response.sendRedirect("Index.jsp");

		}

	}

}
