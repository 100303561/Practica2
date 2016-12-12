package jms;

import java.util.*;

import javax.jms.*;
import javax.jms.Queue;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class OperacionesJMSC{

	private static javax.jms.ConnectionFactory factory = null;
	private static javax.naming.InitialContext contextoInicial = null;
	private static javax.jms.Destination cola = null;
	private static javax.jms.Connection Qcon = null;
	private static javax.jms.Session QSes = null;
	private static javax.jms.MessageProducer Mpro = null;
	private static javax.jms.MessageConsumer Mcon = null;
	

	
	
	 
			
	
	public static void escribirCola(ObjetoMensaje mensaje){

		try {

			//atribuyes al mensaje que ira a la cola, el objeto mensaje		
			//Aqui atribuimos al mensaje el correlationID que sera el destinatario.

			String correlationIDmensaje= (Integer.toString(mensaje.getDestinatario())+"¿$?"+(Integer.toString(mensaje.getEmisor())));
			contextoInicial = new javax.naming.InitialContext();





			// siempre por JNDI
			//recupera el recurso del server en JNDI

			factory = (javax.jms.ConnectionFactory) contextoInicial
					.lookup("jms/cf1.1");
			cola = (javax.jms.Destination) contextoInicial
					.lookup("jms/queueAsincrona");



			// Establece conexion con factory que tomaste anteriormente del servidor
			Qcon = factory.createConnection();
			// creo sesion con conexion de antes (parametrsod e sesion por documento)
			QSes = Qcon
					.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
			// creando escritor en cola
			Mpro = QSes.createProducer(cola);

			Qcon.start();





			ObjectMessage mensajeSend = QSes.createObjectMessage(mensaje);
			mensajeSend.setJMSCorrelationID(correlationIDmensaje);
			//Metemos el String con IDdestinatario como correlationID
			//mensajeSend.setJMSCorrelationID(correlationIDmensaje);

			Mpro.send(mensajeSend);

			Mpro.close();
			QSes.close();
			Qcon.close();


		} 
		//FIN try
		catch (javax.jms.JMSException e) {
			System.out
			.println(".....JHC *************************************** Error de JMS: "
					+ e.getLinkedException().getMessage());
			System.out
			.println(".....JHC *************************************** Error de JMS: "
					+ e.getLinkedException().toString());
		} catch (Exception e) {
			System.out
			.println("JHC *************************************** Error Exception: "
					+ e.getMessage());
		}
	}



	public static ArrayList<String> leerCola(String emisor, String destinatario){
		//lectura en JMS
		//debes cogerlo del id recibido en el JSP


		//----------------debemos coger nuestro id de la sesion-----------------------------------------------------------------//



		ArrayList<String> colaMensajes = new ArrayList<String>();

		try {
			contextoInicial = new javax.naming.InitialContext();


			factory = (javax.jms.ConnectionFactory) contextoInicial
					.lookup("jms/cf1.1");
			cola = (javax.jms.Destination) contextoInicial
					.lookup("jms/queueAsincrona");



			Qcon = factory.createConnection();

			QSes = Qcon
					.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);

			//Creas el consumidor
			Mcon = QSes.createConsumer(cola);
			//Inicio
			Qcon.start();


			//---------------------------------Problema de recibir mensaje------------------------------------------

			ObjectMessage mensaje = null;

			mensaje=(ObjectMessage) Mcon.receive(100);
			while(mensaje!=null){

				ObjetoMensaje mensajito= (ObjetoMensaje) mensaje.getObject();
				String correlationIDmensaje= (Integer.toString(mensajito.getDestinatario())+"¿$?"+(Integer.toString(mensajito.getEmisor())));

				if(mensaje.getJMSCorrelationID().equals(correlationIDmensaje)){

					//colaMensajes.add(" "+"<br>"+"El usuario:"+" "+mensajito.getEmisor()+" "+"dice:"+" "+mensajito.getMensaje());
					colaMensajes.add(mensajito.getMensaje());

					/*String textoRecibido = mensajito.getMensaje()+ "<br>";
		int emisorMensajeRecibido = mensajito.getEmisor();
		int destinatarioMensajeRecibido = mensajito.getDestinatario();*/



					mensaje=(ObjectMessage) Mcon.receive(100);
				}
			}

			Mcon.close();
			QSes.close();
			Qcon.close();

		} catch (javax.jms.JMSException e) {
			System.out
			.println(".....JHC *************************************** Error de JMS: "
					+ e.getLinkedException().getMessage());
			System.out
			.println(".....JHC *************************************** Error de JMS: "
					+ e.getLinkedException().toString());
		} catch (Exception e) {
			System.out
			.println("JHC *************************************** Error Exception: "
					+ e.getMessage());
		}



		return colaMensajes;
	}





	public static ArrayList<Integer> mostrarChats(int IDpropio){
		try {

			contextoInicial = new javax.naming.InitialContext();
			factory = (javax.jms.ConnectionFactory) contextoInicial
					.lookup("jms/cf1.1");
			cola = (javax.jms.Destination) contextoInicial
					.lookup("jms/queueAsincrona");


		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Connection _connection = null;


		ArrayList <ObjetoMensaje> conjuntoMensajes = new ArrayList<ObjetoMensaje>();
		ArrayList <Integer> conjuntoEmisores = new ArrayList<Integer>();
		int emisorChat;
		boolean encontrado=false;


		try {


			_connection = factory.createConnection();

			Session session = _connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			QueueBrowser browser = session.createBrowser((Queue)cola);


			//QueueBrowser buscadorMensajes= session.createBrowser((Queue)cola,Integer.toString(IDpropio) );
			@SuppressWarnings("rawtypes")
			Enumeration msgs = browser.getEnumeration();

			ObjetoMensaje mensajito;

			while(msgs.hasMoreElements()==true)
			{
				ObjectMessage mensaje = (ObjectMessage)msgs.nextElement();


				mensajito= (ObjetoMensaje) mensaje.getObject();


				if(mensajito.getDestinatario()==IDpropio){
					conjuntoMensajes.add(mensajito);
				}

			} 

			//-------------------------------------------------PROBLEMAS CON EL CASTING--------------------			

			//Creamos el mensaje que vamos a coger de la cola
			while(msgs.hasMoreElements()==true)
					{
						System.out.println("comprobamos los elementos de la cola, AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");

						 ObjectMessage mensaje = (ObjectMessage)msgs.nextElement();
						 mensajito= (ObjetoMensaje) mensaje.getObject();


						 if(mensajito.getDestinatario()==IDpropio){
							 conjuntoMensajes.add(mensajito);
						 }

					} 
			 
			if(msgs.hasMoreElements()!= true){
						ObjetoMensaje mensajeFin = new ObjetoMensaje();
						mensajeFin.setDestinatario(IDpropio);
						mensajeFin.setEmisor(-1);
						mensajeFin.setMensaje("NO HAY MAS MENSAJES QUE RECIBIR");
						conjuntoMensajes.add(mensajeFin);
					}	 

			for(int i=0; i<conjuntoMensajes.size();i++){
				emisorChat=conjuntoMensajes.get(i).getEmisor();
				encontrado=false;
				for(int j=0; j<conjuntoEmisores.size() && !encontrado;j++){

					if(emisorChat==conjuntoEmisores.get(j)){
						encontrado=true;
					}else{
						encontrado=false;
					}

				}
				if(encontrado==false){
					conjuntoEmisores.add(emisorChat);
				}
			}

			//Excepeciones en caso de error
		} catch (JMSException e) {
			System.out
			.println(".....JHC *************************************** Error de JMS: "
					+ e.getLinkedException().getMessage());
			System.out
			.println(".....JHC *************************************** Error de JMS: "
					+ e.getLinkedException().toString());
		} finally {
			if (_connection != null) {
				try {
					_connection.close();
				} catch (JMSException e) {
					System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().getMessage());
					System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().toString());
				}
			}
		}

		return conjuntoEmisores;

	}


}





