package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import acciones.IAction;
import clases.Product;
import acciones.*;
import es.uc3m.tiw.domains.*;
import jhc.controller.ProductManager;
import jms.ObjetoMensaje;

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
		String operacion;
		System.out.println(action);

		// Solo para probar funcionalidad

		// Variables globales

		User u = new User();
		User a = new User();
		User u1 = new User();
		Mensaje mensajeSend = new Mensaje();
		
		HttpSession misession = (HttpSession) request.getSession();
		RequestDispatcher miR;

		Client client =  ClientBuilder.newClient();
		WebTarget webResource;

		if (action != null) {
			switch (action) {
			
			
			case "login":

				u.setEmail(request.getParameter("email"));
				u.setPassword(request.getParameter("password"));

				System.out.println("email y contraseña recogidos" + u.getEmail() + u.getPassword());

				// REST Client using POST Verb and JSON

				webResource = client.target("http://localhost:8010").path(action);

				a = webResource.request("application/json").accept("application/json")
						.post(Entity.entity(u, MediaType.APPLICATION_JSON), User.class);

				// Comprobamos que exista el usuario
				if (a.getName() != null) {

					// Guardamos el usuario en la session
					HttpSession ses = request.getSession(true);
					ses.setAttribute("usuario", a);

					// Guardar el catalogo en session(LLamar a microservicio
					// catalogo)
					// ses.setAttribute("catalogo", catalogo);

					miR = request.getRequestDispatcher("Prueba.jsp");
					miR.forward(request, response);

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

				// REST Client using POST Verb and JSON
				webResource = client.target("http://localhost:8010").path(action);
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
				webResource = client.target("http://localhost:8010").path(action);
				webResource.request("application/json").accept("application/json")
						.post(Entity.entity(u1, MediaType.APPLICATION_JSON), User.class);

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
				webResource = client.target("http://localhost:8010").path(action);
				webResource.request("application/json").accept("application/json")
						.post(Entity.entity(u1, MediaType.APPLICATION_JSON), User.class);

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
				misession = (HttpSession) request.getSession();

				if (misession.getAttribute("admin") != null) {
					// Invalidamos sesion y redirigir a login admin
					request.getSession().invalidate();
					miR = request.getRequestDispatcher("LoginAdmin.jsp");
					miR.forward(request, response);

				} else {
					// Invalidamos sesion y redirigir a login
					miR = request.getRequestDispatcher("Login.jsp");
					miR.forward(request, response);
				}
				break;
			case "uploadProduct":
				act = new UploadProduct();
				break;
			case "adminUsers":
				act = new AdminUsers();
				break;
			case "index":
				act = new Index();
				break;
			case "showProduct":
				act = new ShowProduct();
				break;

			case "modifyProduct":
				act = new ModifyProduct();
				break;

			case "updateProduct":
				act = new UpdateProduct();
				break;
			case "deleteProduct":
				act = new DeleteProduct();
				break;
			case "loginAdmin":
				// Recogemos los parametros de la request para consultar la bbdd

				u.setEmail(request.getParameter("email"));
				u.setPassword(request.getParameter("password"));

				// REST Client using POST Verb and JSON

				webResource = client.target("http://localhost:8010").path(action);

				Admin ad = webResource.request("application/json").accept("application/json")
						.post(Entity.entity(u, MediaType.APPLICATION_JSON), Admin.class);

				// Comprobamos que exista el usuario
				if (ad.getEmail() != null) {

					// Guardamos el usuario en la session
					misession = request.getSession(true);
					misession.setAttribute("admin", a);

					// Guardar lista de ususarios(LLamar a microservicio
					// lista de ususarios)
					webResource = client.target("http://localhost:8010").path("listaUser");
					List<User> lista = null;
//					lista = (List<User>) webResource.request("application/json").accept("application/json")
//							.post(Entity.entity(u, MediaType.APPLICATION_JSON), List.class);
					misession.setAttribute("lista", lista);

					miR = request.getRequestDispatcher("AdminUsers.jsp");
					miR.forward(request, response);

				} else {

					miR = request.getRequestDispatcher("LoginAdmin.jsp");
					miR.forward(request, response);
				}
				break;
			case "showProductAdmin":
				act = new ShowProductAdmin();
				break;
			case "identifyUser":
				act = new IdentifyUser();
				break;
			// añadir demas controladores product
			case "reload":
				act = new Reload();
				break;
			case "product":
				act = new Article();
				break;
			case "reloadCatalogo":
				act = new ReloadCatalogo();
				break;
				
				
			case "enviarMensaje":
				
				operacion= "messages";
				if(request.getParameter("destinoAdmin")!= null){
					String mensaje	=  request.getParameter("mensaje");				
					int idemisor= (((User) misession.getAttribute("usuario")).getId());
					int iddestinatario = Integer.parseInt(request.getParameter("idDestinatario"));
					
					
					mensajeSend.setIddestinatario(iddestinatario);
					mensajeSend.setIdemisor(idemisor);
					mensajeSend.setMensaje(mensaje);
					
					
					
					// REST Client using POST Verb and JSON
					webResource = client.target("http://localhost:8030").path(operacion);
					webResource.request("application/json").accept("application/json")
					.post(Entity.entity(mensajeSend, MediaType.APPLICATION_JSON),Mensaje.class);
							
							
					System.out.println("Se ha enviado el mensaje al usuario: " + iddestinatario);

					// Redireccionamos a la pagina de chat para seguir hablando
					miR = request.getRequestDispatcher("Chat.jsp");

					miR.forward(request, response);
					break;
					
				}
				
				
				
				case "LeerChat":
					
					operacion="messages";
					
					List<Mensaje> resultado;
					//comprobacion de admin o ususario normal
					User usuario = (User) misession.getAttribute("usuario");
					int idPropio= usuario.getId();	
					int idAjeno=Integer.parseInt(request.getParameter("idUserChat"));
					
						// REST Client using GET Verb and Path Variable
						client = ClientBuilder.newClient();
						webResource = client.target("http://localhost:8030").path(operacion)
								.path("idPropio")
								.path("idAjeno");
						
						Mensaje[] lista= webResource.request().accept("application/json").get(Mensaje[].class);
							
						resultado= Arrays.asList(lista);
						
						request.setAttribute("mensajes", resultado);
								
						System.out.println("Se muestran los mensajes recibidos para el usuario: " + usuario.getName());

						// Redireccionamos a la pagina de Login
						miR = request.getRequestDispatcher("ChatPrivado.jsp");

						miR.forward(request, response);
						break;
						
					
					
					
				}
				
				
				
				
			}

		}

		act.processAction(request, response);
	}

}
