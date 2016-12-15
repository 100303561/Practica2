package es.uc3m.tiw.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.domains.*;

@RestController
@CrossOrigin
public class ControllerChat {

	@Autowired
	MensajeDAO MensajesDAO;
	
	@Autowired
	UserDAO userDAO;

	RestTemplate restTemplate;

	//Almacenar mensaje
	@RequestMapping(value = "/messages", method = RequestMethod.POST)
	public Mensaje enviarMensaje(@RequestBody Mensaje mensaje) {
		System.out.println("Almacenar mensaje en la BD");

		return MensajesDAO.save(mensaje);
	}

	//Mostrar conversacion entre dos usuarios
	@RequestMapping(value = "/messages/{idPropio}/{idAjeno}", method = RequestMethod.GET)
	public List<Mensaje> leerMensajes(@PathVariable("idPropio") int idPropio, @PathVariable("idAjeno") int idAjeno) {
		System.out.println("Buscar mensaje en la BD que me envian y ordenarlos");

		List<Mensaje> conversacionR = MensajesDAO.mensajesRecibidos(idPropio, idAjeno);
		List<Mensaje> conversacionE = MensajesDAO.mensajesEnviados(idPropio, idAjeno);

		//MensajesDAO.mensajesRecibidos(idPropio, idAjeno)
		List<Mensaje> conversacionOrdenada = new ArrayList <Mensaje>();
		//conversacionOrdenada.clear();

		while (!conversacionR.isEmpty() || !conversacionE.isEmpty()) {
		

			if (!conversacionR.isEmpty() && !conversacionE.isEmpty()) {
				if ((conversacionR.get(0)).getIdmessage() < (conversacionE.get(0)).getIdmessage()) {
					conversacionOrdenada.add(conversacionR.get(0));
					conversacionR.remove(0);
				}

				if ((conversacionE.get(0)).getIdmessage() < (conversacionR.get(0)).getIdmessage()) {
					conversacionOrdenada.add(conversacionE.get(0));
					conversacionE.remove(0);
				}
			} else {
				if (!conversacionR.isEmpty()) {
					conversacionOrdenada.addAll(conversacionR);
					conversacionR.removeAll(conversacionR);
				}

				if (!conversacionE.isEmpty()) {
					conversacionOrdenada.addAll(conversacionE);
					conversacionE.removeAll(conversacionE);
				}
			}
		}

		System.out.println("Hemos ordenado bien");
		
		while(!conversacionOrdenada.isEmpty()){
		System.out.println((conversacionOrdenada.get(0)).getMensaje());
		conversacionOrdenada.remove(0);}

		return conversacionOrdenada;

	}

	//Mostrar usuarios con los que he conversado.
	@RequestMapping(value = "/messages/{idPropio}", method = RequestMethod.GET)
	public List<User> verConversaciones(@PathVariable("idPropio") int idPropio) {
		System.out.println("Buscar mensajes recibidos de diferentes usuarios en la BD");

		
		
		//Obtener lista donde yo soy emisor
		List<Integer> DistinctEnviados = MensajesDAO.findCustomEnviado(idPropio);
		
		System.out.println(DistinctEnviados.size());
		
		//En una lista de int voy añadiendo todos los id con lo que he hablado
		List<Integer> aux = new ArrayList<Integer>();
		
		while (!DistinctEnviados.isEmpty()){
			aux.add((DistinctEnviados.get(0)));
			DistinctEnviados.remove(0);
		}
		
		
		
		//Obtener lista donde yo soy destinatario
		List<Integer> DistinctRecibidos = MensajesDAO.findCustomRecibido(idPropio);
		boolean b = false;
		
		while (!DistinctRecibidos.isEmpty()){
			for (int i = 0; i < aux.size(); i++) {
				//Si encuentro el valor en mi array auxliar borro el valor de la lista de recibidos
				if(aux.get(i)==(DistinctRecibidos.get(0))){
					DistinctRecibidos.remove(0);
					b = true;
					break;
				}
			}
			if(b==false){
				aux.add((DistinctRecibidos.get(0)));
				DistinctRecibidos.remove(0);
			}
			
			
		}
		//Lista aux con distintos id
		
		List<User> conversacionesActivas = new ArrayList<User> ();
		
		
		while(!aux.isEmpty()){
			//Coger un id, buscar usuario y guardarlo en una lista para devolverlo
			conversacionesActivas.add(userDAO.findOne(aux.get(0)));
			aux.remove(0);
		}

		return conversacionesActivas;
	}
	
	
}