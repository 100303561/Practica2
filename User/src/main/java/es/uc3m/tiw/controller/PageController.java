package es.uc3m.tiw.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import es.uc3m.tiw.domains.Operands;
import es.uc3m.tiw.domains.Result;
import es.uc3m.tiw.domains.User;
import es.uc3m.tiw.domains.UserDAO;

//@RestController
@Controller
public class PageController {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/")
	public String main(Model model){
		
		model.addAttribute(new Operands(4,0));
		return "Register";
	}
	

	//Registrar usuario
	@RequestMapping(value="/registrar", method=RequestMethod.POST)
	public String registrar(Model model, @ModelAttribute User user){
		System.out.println("Usuario: "+user);
		userDAO.save(user);
		return "Login";
	}
	
	
	
	@RequestMapping(value="users", method=RequestMethod.GET)
	public List<User> usuarios(){
		System.out.println("Buscar a todos los usuarios");
		return userDAO.findAll();
	}
	
	// Several parameters can be joined into a ModelAttribute
	@RequestMapping("/calculate")
	public String calculate(Model model,
			@ModelAttribute Operands operands,
			@RequestParam("operation") String operation){
		
		System.out.println(operands);
		System.out.println(operation);
		
		// Example using POST and JSON 
		Result result = restTemplate.postForObject("http://localhost:8081/{calcOperation}",
				operands, Result.class,operation);
		
		// You have to uncomment these lines to use following examples (using parameters and variablePath)
		//Map<String, String> brackets = new HashMap<String, String>();
		//brackets.put("operation", ""+ operation);
		//brackets.put("operand1", ""+ operands.getOperand1());
		//brackets.put("operand2", ""+ operands.getOperand2());
		
		// Example using parameters
		//Result result = restTemplate.getForObject("http://localhost:8081/{operation}"+
		//		"?operand1={operand1}&operand2={operand2}", Result.class, brackets);
		
		// Example using variable Path
		//Result result = restTemplate.getForObject("http://localhost:8081/{operation}"+
		//		"/{operand1}/{operand2}", Result.class,brackets);
				
		System.out.println(result);
		model.addAttribute(result);
		return "index";
	}
	
	@RequestMapping("/saludos/{nombre}")
    public String saludos(Model modelo, @PathVariable String nombre){
        modelo.addAttribute("name", nombre);
        return "Hola";
    }
}