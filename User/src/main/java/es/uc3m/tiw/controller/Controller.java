package es.uc3m.tiw.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class Controller {

	@RequestMapping(value="saludo" )
	public String saludo(){
		return "hola";
	}
	
	
}
