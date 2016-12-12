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

import es.uc3m.tiw.domains.Admin;
import es.uc3m.tiw.domains.AdminDAO;
import es.uc3m.tiw.domains.Operands;
import es.uc3m.tiw.domains.Result;
import es.uc3m.tiw.domains.User;
import es.uc3m.tiw.domains.UserDAO;

//@Controller
@RestController
@CrossOrigin
public class PageController {

	@Autowired
	UserDAO userDAO;

	@Autowired
	AdminDAO adminDAO;

	// Pagina Inicial (Login)
	@RequestMapping("/")
	public String main(Model model) {
		model.addAttribute(new Operands(4, 0));
		return "Login";
	}

	// Registrar usuario
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void registrar(@RequestBody User user) {
		System.out.println("Usuario: " + user);
		userDAO.save(user);
	}

	// Login
	@RequestMapping(value="login", method=RequestMethod.POST)
    public User login(@RequestBody User u){
        System.out.println("Haciendo Login ");
        return userDAO.findByEmailAndPassword(u.getEmail(), u.getPassword());
    }
	
	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public User login(@RequestBody User user) {
//		System.out.println("El usuario que llega al microservicio: " + user);
//		User u = new User();
//		u = userDAO.findByEmail(user.getEmail());
//		System.out.println("El usuario encontrado: "+u);
//		// Si existe el usuario comprobaremos su contraseña
//		if (u != null) {
//			// Si las contraseñas son iguales permitimos loguearse
//			if (u.getPassword().equals(user.getPassword())) {
//				System.out.println("Bienvenido");
//				return u;
//			}
//		}
//		// Si el ususario no existe o las contraseñas son distintas redirigimos
//		// a Login
//		System.out.println("El ususario no se encuentra en la bbdd");
//		return user;
//	}

	// Modificar Usuario(debe ser update)
	@RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
	public void modifyUser(@RequestBody User user) {
		System.out.println("Usuario: " + user);
		User u = userDAO.findByEmail(user.getEmail());
		System.out.println(u);
		// Si existe el usuario cambiamos sus atributos
		if (u != null) {
			// Asignamos el id al nuevo ususario
			user.setId(u.getId());
			System.out.println("Usuario con nuevo id: " + user);
			// Borramos el usuario anterior
			userDAO.delete(u);
			// Insertamos el nuevo ususario
			userDAO.save(user);
		}
		// Redirigir a Index
		System.out.println("El usuario ha sido moficado");
		
	}

	// Login Admin
	@RequestMapping(value = "/loginAdmin", method = RequestMethod.POST)
	public Admin loginAdmin(@RequestBody Admin user) {
		System.out.println("Administrador: " + user);
		Admin u = adminDAO.findByEmail(user.getEmail());
		// Si existe el usuario comprobaremos su contraseña
		if (u != null) {
			// Si las contraseñas son iguales permitimos loguearse
			if (u.getPassword().equals(user.getPassword())) {
				System.out.println("Bienvenido");
				return u;
			}

		}
		//Inicializar la variable
		u = new Admin();
		return u;
	}
	
	//Lista de usuarios en la bbdd(debe ser por get)
	@RequestMapping(value = "/listaUser", method = RequestMethod.POST)
	public List<User> listaUser(@RequestBody Admin user) {
		//Recorremos lista de ususarios en la bbdd
		List<User> lista = userDAO.findAll();
		System.out.println("Lista: " + lista.toString());
		return lista;
	}
	

	@RequestMapping(value = "users", method = RequestMethod.GET)
	public List<User> usuarios() {
		System.out.println("Buscar a todos los usuarios");
		return userDAO.findAll();
	}

	// Eliminar Usuario(debe ser delete)
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public void deleteUser(@RequestBody User user) {
		System.out.println("id del usuario a eliminar: "+user.getId());
		User u = userDAO.findOne(user.getId());
		System.out.println(u);

		if (u != null) {
			// Borramos el usuario
			userDAO.delete(u);
		}
	}

//	// Several parameters can be joined into a ModelAttribute
//	@RequestMapping("/calculate")
//	public String calculate(Model model, @ModelAttribute Operands operands,
//			@RequestParam("operation") String operation) {
//
//		System.out.println(operands);
//		System.out.println(operation);
//
//		// Example using POST and JSON
//		Result result = restTemplate.postForObject("http://localhost:8081/{calcOperation}", operands, Result.class,
//				operation);
//
//		// You have to uncomment these lines to use following examples (using
//		// parameters and variablePath)
//		// Map<String, String> brackets = new HashMap<String, String>();
//		// brackets.put("operation", ""+ operation);
//		// brackets.put("operand1", ""+ operands.getOperand1());
//		// brackets.put("operand2", ""+ operands.getOperand2());
//
//		// Example using parameters
//		// Result result =
//		// restTemplate.getForObject("http://localhost:8081/{operation}"+
//		// "?operand1={operand1}&operand2={operand2}", Result.class, brackets);
//
//		// Example using variable Path
//		// Result result =
//		// restTemplate.getForObject("http://localhost:8081/{operation}"+
//		// "/{operand1}/{operand2}", Result.class,brackets);
//
//		System.out.println(result);
//		model.addAttribute(result);
//		return "index";
//	}

	@RequestMapping("/saludos/{nombre}")
	public String saludos(Model modelo, @PathVariable String nombre) {
		modelo.addAttribute("name", nombre);
		return "Hola";
	}
}
