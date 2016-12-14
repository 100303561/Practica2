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

import es.uc3m.tiw.domains.Mensaje;
import es.uc3m.tiw.domains.MensajeDAO;

@RestController
@CrossOrigin
public class ControllerChat {

	@Autowired
	MensajeDAO MensajesDAO;

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

		List<Mensaje> conversacionOrdenada = MensajesDAO.mensajesRecibidos(idPropio, idAjeno);
		conversacionOrdenada.clear();

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

		System.out.println("hemos ordenado bien");
		System.out.println(conversacionOrdenada);

		return conversacionOrdenada;

	}

	//Mostrar usuarios con los que he conversado.
	@RequestMapping(value = "/messages/{idPropio}", method = RequestMethod.GET)
	public List<Mensaje> verConversaciones(@PathVariable("idPropio") int idPropio) {
		System.out.println("Buscar mensajes recibidos de diferentes usuarios en la BD");

		List<Mensaje> DistinctEnviados = MensajesDAO.findCustomEnviado(idPropio);
		List<Mensaje> DistinctRecibidos = MensajesDAO.findCustomRecibido(idPropio);
		List<Mensaje> conversacionesActivas = DistinctEnviados;
		DistinctEnviados.addAll(DistinctRecibidos);

		return conversacionesActivas;
	}
}