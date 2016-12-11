package jms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.Product;
import clases.User;
import jhc.controller.ProductManager;

/**
 * Servlet implementation class ServletChat
 */
@WebServlet("/ServletChat")
public class ServletChat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletChat() {
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

		// Obtenemos la session para sacar los datos
		HttpSession sesion = (HttpSession) request.getSession();

		// Será la operacion que se quiere realizar
		String intOperacion = request.getParameter("ejecutar");

		// comprueba de operacion si es uno u otro, para mandar a una pagina u
		// otra
		switch (intOperacion) {

		case "EnviarMensaje":

			ObjetoMensaje mensaje1 = new ObjetoMensaje();
			// Emisor es la persona que escribe el mensaje que estara en session
			mensaje1.setEmisor(((User) sesion.getAttribute("usuario")).getId());

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejemplo");
			ProductManager pr = new ProductManager(emf);

			// Obtenemos el id del destinatario
			int destinatarioEscritura = pr.getOwnerbyID(((Product) sesion.getAttribute("productShow")).getID());

			// Asigno destinatario al mensaje
			mensaje1.setDestinatario(destinatarioEscritura);
			// Asignamos el texto del mensaje
			mensaje1.setMensaje(request.getParameter("mensaje"));
			// Escribo el mensaje
			OperacionesJMSC.escribirCola(mensaje1);

			response.sendRedirect("Article.jsp");
			break;

		case "Responder":

			ObjetoMensaje mensaje3 = new ObjetoMensaje();
			// Emisor es la persona que escribe el mensaje que estara en session
			mensaje3.setEmisor(((User) sesion.getAttribute("usuario")).getId());


			// Obtenemos el id del destinatario
			int destinatarioEscritura1 = Integer.parseInt(request.getParameter("idDestinatario"));
			
			// Asigno destinatario al mensaje
			mensaje3.setDestinatario(destinatarioEscritura1);
			// Asignamos el texto del mensaje
			mensaje3.setMensaje(request.getParameter("mensaje"));
			// Escribo el mensaje
			OperacionesJMSC.escribirCola(mensaje3);

			response.sendRedirect("Index.jsp");
			break;

		case "LeerChat":
			List<String> resultado;
			// Obtener id del que envia el mensaje
			String emisor = request.getParameter("idUserChat");

			// Id del destinatario del mensaje
			int idDestinatario = (((User) sesion.getAttribute("usuario")).getId());
			String destinatarioLectura = String.valueOf(idDestinatario);
			// Guardo los mensajes en una lista
			resultado = OperacionesJMSC.leerCola(emisor, destinatarioLectura);

			int tamanoResultado = resultado.size();
			System.out.println(resultado.toString());

			request.setAttribute("mensajes", resultado);
			RequestDispatcher miR2 = request.getRequestDispatcher("ChatPrivado.jsp");
			miR2.forward(request, response);
			break;

		case "MostrarConversaciones":

			ArrayList<Integer> conjuntoEmisores = new ArrayList<Integer>();

			// Crear una lista con los usuario que me han escrito
			ArrayList<User> listaEmisores = new ArrayList<User>();

			int miId = ((User) sesion.getAttribute("usuario")).getId();

			// Introducir el id del que solicita mostrar sus chat
			conjuntoEmisores = OperacionesJMSC.mostrarChats(miId);

			User emisores = null;

			if (conjuntoEmisores.size() > 0) {
				EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("Ejemplo");
				EntityManager em = emf1.createEntityManager();

				// Recorremos la lista de id para encontrar el nombre que nos ha
				// hablado
				for (int a = 0; a < conjuntoEmisores.size(); a++) {

					if (conjuntoEmisores.get(a) != -1) {
						emisores = em.find(User.class, conjuntoEmisores.get(a));
						listaEmisores.add(emisores);
					}
				}

				em.close();

				// Pasamos el atributo con la lista de usuario
				request.setAttribute("listaEmisores", listaEmisores);

				RequestDispatcher miR3 = request.getRequestDispatcher("ChatsAbiertos.jsp");

				miR3.forward(request, response);
			}

			break;

		case "EnviarMensajeAdmin":
			ObjetoMensaje mensaje2 = new ObjetoMensaje();
			// id=0 porque es administrador
			int emisorAdmin = 0;
			String mensajeEnviar = request.getParameter("IDConversacionAdmin");

			mensaje2.setEmisor(emisorAdmin);
			// Consulta campo input ChatsAbiertosAdmin
			mensaje2.setDestinatario(Integer.parseInt(mensajeEnviar));
			mensaje2.setMensaje(request.getParameter("MensajeConversacionAdmin"));
			OperacionesJMSC.escribirCola(mensaje2);
			RequestDispatcher miR4 = request.getRequestDispatcher("chat1.jsp");
			miR4.forward(request, response);
			break;

		}
	}

}
