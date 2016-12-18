package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import acciones.IAction;
import acciones.*;
import es.uc3m.tiw.domains.*;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
@MultipartConfig
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IAction act = null;
		String action = request.getParameter("action");
		System.out.println(action);
		String operacion = "";

		// Solo para probar funcionalidad

		// Variables globales

		User u = new User();
		User a = new User();
		User u1 = new User();
		User usuario = new User();
		Product p = new Product();
		Product[] listaProduct = null;
		List<Product> catalogo = null;
		int idPropio;
		int idAjeno;
		int idemisor;
		Mensaje mensajeSend = new Mensaje();
		Mensaje[] listaMensaje = null;
		User[] listaUser = null;
		List<Mensaje> resultado;
		int id;

		HttpSession misession = (HttpSession) request.getSession();
		RequestDispatcher miR;

		Client client = ClientBuilder.newClient();
		WebTarget webResource;

		if (action != null) {
			switch (action) {
			case "login":

				operacion = "login";

				u.setEmail(request.getParameter("email"));
				u.setPassword(request.getParameter("password"));

				System.out.println("email y contraseña recogidos" + u.getEmail() + u.getPassword());

				// REST Client using POST Verb and JSON

				webResource = client.target("http://localhost:8010").path(operacion);

				a = webResource.request("application/json").accept("application/json")
						.post(Entity.entity(u, MediaType.APPLICATION_JSON), User.class);

				// Comprobamos que exista el usuario
				if (a != null) {

					if (a.getAdmin() == 0) {
						// Guardamos el usuario en la session
						HttpSession ses = request.getSession(true);
						ses.setAttribute("usuario", a);

						// Obtenemos los productos disponibles
						operacion = "products";
						webResource = client.target("http://localhost:8020").path(operacion);
						listaProduct = webResource.request().accept("application/json").get(Product[].class);

						// Convertimos el array de productos en una lista
						catalogo = Arrays.asList(listaProduct);

						// Guardar el catalogo en session
						request.setAttribute("catalogo", catalogo);

						miR = request.getRequestDispatcher("Index.jsp");
						miR.forward(request, response);
					} else {
						// Guardamos el usuario en la session
						misession = request.getSession(true);
						misession.setAttribute("admin", a);

						// Guardar lista de ususarios(LLamar a microservicio
						// lista de ususarios)

						operacion = "users";
						client = ClientBuilder.newClient();
						webResource = client.target("http://localhost:8010").path(operacion);
						listaUser = webResource.request().accept("application/json").get(User[].class);
						List<User> lista2 = Arrays.asList(listaUser);
						// Pasar atributo por request
						request.setAttribute("lista", lista2);

						miR = request.getRequestDispatcher("AdminUsers.jsp");
						miR.forward(request, response);
					}

				} else {

					miR = request.getRequestDispatcher("Login.jsp");
					miR.forward(request, response);
				}
				break;
			case "register":

				u.setName(request.getParameter("name"));
				u.setSurname(request.getParameter("surname"));
				u.setCity(request.getParameter("city"));
				u.setEmail(request.getParameter("email"));
				u.setPassword(request.getParameter("clave1"));

				operacion = "users";

				// REST Client using POST Verb and JSON
				webResource = client.target("http://localhost:8010").path(operacion);
				webResource.request("application/json").accept("application/json")
						.post(Entity.entity(u, MediaType.APPLICATION_JSON), User.class);

				System.out.println("Se ha registrado el usuario con email: " + u.getEmail());

				// Redireccionamos a la pagina de Login
				miR = request.getRequestDispatcher("Login.jsp");

				miR.forward(request, response);
				break;
			case "modifyUser":

				u1 = (User) misession.getAttribute("usuario");

				// Recoger atributos de la request
				u1.setName(request.getParameter("name"));
				u1.setSurname(request.getParameter("surname"));
				u1.setCity(request.getParameter("city"));
				u1.setPassword(request.getParameter("clave1"));

				// Llamada a microservicio
				operacion = "users";

				client = ClientBuilder.newClient();
				webResource = client.target("http://localhost:8010").path(operacion).path(String.valueOf(u1.getId()));
				webResource.request("application/json").accept("application/json")
						.put(Entity.entity(u1, MediaType.APPLICATION_JSON), User.class);

				System.out.println("El usuario modificado es: " + u1);

				// Si eres admninistrador redirigir a AdminUsers
				if (misession.getAttribute("admin") != null) {
					miR = request.getRequestDispatcher("AdminUsers.jsp");
					miR.forward(request, response);
				} else {

					// Guardar en session el ususario con atributos modicados
					misession.setAttribute("usuario", u1);
					// Redirigir a Index
					miR = request.getRequestDispatcher("Index.jsp");
					miR.forward(request, response);

				}
				break;
			case "deleteUser":
				// Recuperamos el usuario de la sesion

				u1 = (User) misession.getAttribute("usuario");

				// Llamar al microservicio que borra el usuario
				// Llamada a microservicio
				operacion = "users";

				client = ClientBuilder.newClient();
				webResource = client.target("http://localhost:8010").path(operacion).path(String.valueOf(u1.getId()));
				webResource.request().accept("application/json").delete();

				System.out.println("El usuario ha sido eliminado");

				if (misession.getAttribute("admin") != null) {
					miR = request.getRequestDispatcher("AdminUsers.jsp");
					miR.forward(request, response);
				} else {

					// invalidamos sesion
					misession.invalidate();

					// Redirigir a la pagina de Index

					miR = request.getRequestDispatcher("Login.jsp");
					miR.forward(request, response);
				}
				break;
			case "logout":

				// Invalidamos sesion y redirigir a login admin
				request.getSession().invalidate();
				miR = request.getRequestDispatcher("Login.jsp");
				miR.forward(request, response);

				break;
			case "uploadProduct":

				p.setProduct_name(request.getParameter("title"));
				p.setDescription(request.getParameter("message"));
				p.setCategory(request.getParameter("category"));
				p.setPrice(Double.parseDouble(request.getParameter("price")));
				p.setCity(request.getParameter("city"));
				p.setStatus("Disponible");

				// Obtener imagen
				Part filePart = request.getPart("fileToUpload");

				byte[] data = new byte[(int) filePart.getSize()];

				filePart.getInputStream().read(data, 0, data.length);

				p.setImagen(data);

				System.out.println(data.toString());

				u1 = (User) misession.getAttribute("usuario");

				p.setUser(u1.getId());
				p.setUserName(u1.getName());

				operacion = "products";

				// REST Client using POST Verb and JSON
				webResource = client.target("http://localhost:8020").path(operacion);
				webResource.request("application/json").accept("application/json")
						.post(Entity.entity(p, MediaType.APPLICATION_JSON), Product.class);

				System.out.println("Se ha registrado el producto: " + p.getProduct_name());

				// Obtenemos los productos disponibles
				operacion = "products";
				webResource = client.target("http://localhost:8020").path(operacion);
				listaProduct = webResource.request().accept("application/json").get(Product[].class);

				// Convertimos el array de productos en una lista
				catalogo = Arrays.asList(listaProduct);

				// Guardar el catalogo en session
				request.setAttribute("catalogo", catalogo);

				miR = request.getRequestDispatcher("Index.jsp");
				miR.forward(request, response);

				break;
			case "adminUsers":
				act = new AdminUsers();
				break;
			case "index":
				String city = request.getParameter("city");
				String owner = request.getParameter("owner");
				String category = request.getParameter("category");
				String search = request.getParameter("search");
				operacion = "products";

				// Comprobamos los valores introducidos por el usuario
				if (city.equals(""))
					city = null;
				if (category.equals(""))
					category = null;
				if (search.equals(""))
					search = null;
				if (owner.equals("")) {
					owner = null;
				}

				// Si se ha introducido algun campo de busqueda la realizamos
				// con esos criterios, sino mostramos productos de la forma
				// normal
				client = ClientBuilder.newClient();
				if (city != null || category != null || search != null || owner != null) {
					if (owner != null)
						owner = "%" + owner + "%";

					if (search != null)
						search = "%" + search + "%";

					// REST Client using POST Verb and JSON
					webResource = client.target("http://localhost:8020").path(operacion).path(search).path(category)
							.path(city).path(owner);
					listaProduct = webResource.request().accept("application/json").get(Product[].class);

				} else {

					webResource = client.target("http://localhost:8020").path(operacion);
					listaProduct = webResource.request().accept("application/json").get(Product[].class);
				}

				misession = (HttpSession) request.getSession();
				misession.setAttribute("catalogo", listaProduct);
				// Redirigimos a la pagina catalogo

				miR = request.getRequestDispatcher("Index.jsp");
				miR.forward(request, response);

				break;
			case "showProduct":
				// Recogemos el id del usuario que queremos mostrar sus
				// productos.
				u1 = (User) misession.getAttribute("usuario");
				id = u1.getId();

				// Realizamos la consulta para saber cuales son los productos
				// del usuario
				operacion = "userProducts";
				client = ClientBuilder.newClient();
				webResource = client.target("http://localhost:8020").path(operacion).path(String.valueOf(id));
				listaProduct = webResource.request().accept("application/json").get(Product[].class);

				// Guardamos los resultados en una lista
				// List<Product> lista = q.getResultList();

				// Pasamos la lista de mis productos a la session
				misession = (HttpSession) request.getSession();
				catalogo = Arrays.asList(listaProduct);
				misession.setAttribute("misproductos", catalogo);

				// Redirigimos a la pagina que muestra productos de un usuario
				miR = request.getRequestDispatcher("ShowProduct.jsp");
				miR.forward(request, response);
				break;

			case "modifyProduct":
				
				
				operacion = "products";
				client = ClientBuilder.newClient();
				webResource = client.target("http://localhost:8020").path(operacion).path(request.getParameter("id"));
				p= webResource.request().accept("application/json").get(Product.class);

				misession = (HttpSession) request.getSession();
				misession.setAttribute("productoModificar", p);
				
				miR = request.getRequestDispatcher("ModifyProduct.jsp");
				miR.forward(request, response);
				
				break;

			case "updateProduct":
				misession = (HttpSession) request.getSession();
				
				p =(Product) misession.getAttribute("productoModificar"); 
				p.setProduct_name(request.getParameter("name"));
				p.setDescription(request.getParameter("desc"));
				p.setCategory(request.getParameter("category"));
				p.setPrice(Double.parseDouble(request.getParameter("price")));
				p.setStatus(request.getParameter("status"));
			
				operacion = "products";
				client = ClientBuilder.newClient();
				webResource = client.target("http://localhost:8020").path(operacion).path(String.valueOf(p.getId()));
				webResource.request("application/json").accept("application/json")
					.put(Entity.entity(p, MediaType.APPLICATION_JSON), Product.class);
			
				
				webResource = client.target("http://localhost:8020").path(operacion);
				listaProduct = webResource.request().accept("application/json").get(Product[].class);
	
				misession = (HttpSession) request.getSession();
				misession.setAttribute("catalogo", listaProduct);
				// Redirigimos a la pagina catalogo
	
				miR = request.getRequestDispatcher("Index.jsp");
				miR.forward(request, response);
				break;
			case "deleteProduct":
				p =(Product) misession.getAttribute("productoModificar"); 
				
			
				operacion = "products";
				client = ClientBuilder.newClient();
				webResource = client.target("http://localhost:8020").path(operacion).path(String.valueOf(p.getId()));
				
			
				
				webResource = client.target("http://localhost:8020").path(operacion).path(String.valueOf(p.getId()));
				webResource.request().accept("application/json").delete();
				
				misession = (HttpSession) request.getSession();
				misession.setAttribute("catalogo", listaProduct);
				// Redirigimos a la pagina catalogo
	
				miR = request.getRequestDispatcher("Index.jsp");
				miR.forward(request, response);
				break;
			case "showProductAdmin":
				// Recogemos el id del usuario que queremos mostrar sus
				// productos.
				id = Integer.parseInt(request.getParameter("id"));

				operacion = "userProducts";
				client = ClientBuilder.newClient();
				webResource = client.target("http://localhost:8020").path(operacion).path(String.valueOf(id));
				listaProduct = webResource.request().accept("application/json").get(Product[].class);

				// Guardamos los resultados en una lista
				// List<Product> lista = q.getResultList();

				// Pasamos la lista de mis productos a la session
				misession = (HttpSession) request.getSession();
				catalogo = Arrays.asList(listaProduct);
				misession.setAttribute("misproductos", catalogo);

				// Redirigimos a la pagina que muestra productos de un usuario
				miR = request.getRequestDispatcher("ShowProductAdmin.jsp");
				miR.forward(request, response);

				break;
			case "identifyUser":

				id = Integer.parseInt(request.getParameter("id"));

				// Llamada a microservicio para encontrar un usuario
				operacion = "users";

				client = ClientBuilder.newClient();
				webResource = client.target("http://localhost:8010").path(operacion).path(String.valueOf(id));
				u1 = webResource.request("application/json").accept("application/json").get(User.class);

				// Pasamos el usuario a la session
				misession = (HttpSession) request.getSession();
				misession.setAttribute("usuario", u1);

				// Redirigimos a la pagina que muestra mis productos
				miR = request.getRequestDispatcher("ModifyUser.jsp");
				miR.forward(request, response);
				break;
			// añadir demas controladores product
			case "reload":
				// Si soy administrador devuelvo a la pagina adminuser.jsp
				if (misession.getAttribute("admin") != null) {
					// Guardar lista de ususarios
					operacion = "users";
					client = ClientBuilder.newClient();
					webResource = client.target("http://localhost:8010").path(operacion);
					listaUser = webResource.request().accept("application/json").get(User[].class);
					List<User> lista2 = Arrays.asList(listaUser);
					// Pasar atributo por request
					request.setAttribute("lista", lista2);

					miR = request.getRequestDispatcher("AdminUsers.jsp");
					miR.forward(request, response);
				} else {

					// Obtenemos los productos disponibles
					operacion = "products";
					webResource = client.target("http://localhost:8020").path(operacion);
					listaProduct = webResource.request().accept("application/json").get(Product[].class);

					// Convertimos el array de productos en una lista
					catalogo = Arrays.asList(listaProduct);

					// Guardar el catalogo en session
					request.setAttribute("catalogo", catalogo);

					miR = request.getRequestDispatcher("Index.jsp");
					miR.forward(request, response);
				}

				break;
			case "product":
				id = Integer.parseInt(request.getParameter("id"));


				operacion = "products";
				
				webResource = client.target("http://localhost:8020").path(operacion).path(String.valueOf(id));
			    Product pr = webResource.request().accept("application/json").get(Product.class);

				request.setAttribute("productShow", pr);
				// Redirigir a la pagina para modificar el producto

				miR = request.getRequestDispatcher("Article.jsp");
				miR.forward(request, response);
				break;
			case "reloadCatalogo":
				act = new ReloadCatalogo();
				break;

			case "enviarMensaje":

				operacion = "messages";

				String mensaje = request.getParameter("mensaje");

				if (misession.getAttribute("admin") != null) {
					idemisor = (((User) misession.getAttribute("admin")).getId());
				} else {
					idemisor = (((User) misession.getAttribute("usuario")).getId());
				}
				int iddestinatario = Integer.parseInt(request.getParameter("idDestinatario"));

				mensajeSend.setIddestinatario(iddestinatario);
				mensajeSend.setIdemisor(idemisor);
				mensajeSend.setMensaje(mensaje);

				// REST Client using POST Verb and JSON
				webResource = client.target("http://localhost:8030").path(operacion);
				webResource.request("application/json").accept("application/json")
						.post(Entity.entity(mensajeSend, MediaType.APPLICATION_JSON), Mensaje.class);

				// Consulto de nuevo la conversacion
				client = ClientBuilder.newClient();
				webResource = client.target("http://localhost:8030").path(operacion).path(String.valueOf(idemisor))
						.path(String.valueOf(iddestinatario));

				listaMensaje = webResource.request().accept("application/json").get(Mensaje[].class);

				resultado = Arrays.asList(listaMensaje);

				request.setAttribute("mensajes", resultado);
				request.setAttribute("idUserChat", iddestinatario);

				System.out.println("Se ha enviado el mensaje al usuario: " + iddestinatario);

				// Redireccionamos a la pagina de chat para seguir hablando
				miR = request.getRequestDispatcher("ChatPrivado.jsp");

				miR.forward(request, response);
				break;

			case "LeerChat":

				operacion = "messages";
				if (misession.getAttribute("admin") != null) {
					usuario = (User) misession.getAttribute("admin");

				} else {
					usuario = (User) misession.getAttribute("usuario");
				}

				idPropio = usuario.getId();
				idAjeno = Integer.parseInt(request.getParameter("idUserChat"));

				// REST Client using GET Verb and Path Variable
				client = ClientBuilder.newClient();
				webResource = client.target("http://localhost:8030").path(operacion).path(String.valueOf(idPropio))
						.path(String.valueOf(idAjeno));

				listaMensaje = webResource.request().accept("application/json").get(Mensaje[].class);

				resultado = Arrays.asList(listaMensaje);

				request.setAttribute("mensajes", resultado);

				System.out.println("Se muestran los mensajes recibidos para el usuario: " + usuario.getName());

				// Redireccionamos a la pagina de Login
				miR = request.getRequestDispatcher("ChatPrivado.jsp");

				miR.forward(request, response);
				break;

			case "mostrarConversaciones":

				List<User> listaConversaciones = new ArrayList<User>();

				// Si es admin, ejecuto find all() de usuarios.
				if (misession.getAttribute("admin") != null) {
					// REST Client using GET Verb and Path Variable

					operacion = "users";
					client = ClientBuilder.newClient();
					webResource = client.target("http://localhost:8010").path(operacion);

					User[] arrayConversaciones = webResource.request().accept("application/json").get(User[].class);

					listaConversaciones = Arrays.asList(arrayConversaciones);

					request.setAttribute("listaEmisores", listaConversaciones);

					System.out.println("Se muestran todas las conversaciones: ");

					// Redireccionamos a la pagina de Login
					miR = request.getRequestDispatcher("ChatsAbiertos.jsp");

					miR.forward(request, response);

				} else {
					usuario = (User) misession.getAttribute("usuario");
					operacion = "messages";
					idPropio = usuario.getId();

					System.out.println(idPropio);

					client = ClientBuilder.newClient();

					webResource = client.target("http://localhost:8030").path(operacion).path(String.valueOf(idPropio));
					User[] arrayConversaciones = webResource.request().accept("application/json").get(User[].class);

					listaConversaciones = Arrays.asList(arrayConversaciones);

					request.setAttribute("listaEmisores", listaConversaciones);

					System.out.println("Se muestran todas las conversaciones de USUARIO: ");

					// Redireccionamos a la pagina de Login
					miR = request.getRequestDispatcher("ChatsAbiertos.jsp");

					miR.forward(request, response);

				}
				break;

			}

		}

		act.processAction(request, response);

	}
}
