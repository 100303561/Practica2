package es.uc3m.tiw.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.domains.User;
import es.uc3m.tiw.domains.UserDAO;

@RestController
@CrossOrigin
public class PageController {

	@Autowired
	UserDAO userDAO;

	// Login
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public User login(@RequestBody User u) {
		System.out.println("Haciendo Login ");
		return userDAO.findByEmailAndPassword(u.getEmail(), u.getPassword());
	}

	// Registrar usuario
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public void registrar(@RequestBody User user) {
		System.out.println("Usuario: " + user);
		userDAO.save(user);
	}

	// Recoger todos los usuarios de la bbdd
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> usuarios() {
		List<User> lista = userDAO.findAll();
		System.out.println("Lista: " + lista.toString());
		return lista;
	}

	// Modificar Usuario
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public void modifyUser(@PathVariable int id, @RequestBody User user) {
		System.out.println("Usuario: " + user);
		//Buscamos usuario a modificar
		User u = userDAO.findOne(id);
		System.out.println(u);

		// Borramos el usuario
		userDAO.delete(u);

		// Insertamos el nuevo ususario
		userDAO.save(user);
		// Redirigir a Index
		System.out.println("El usuario ha sido moficado");

	}

	// Eliminar Usuario
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable int id) {
		System.out.println("id del usuario a eliminar: " + id);
		// Encontramos el ususario en la bbdd
		User u = userDAO.findOne(id);
		System.out.println(u);
	
		// Borramos el usuario
		userDAO.delete(u);
	
	}
}
