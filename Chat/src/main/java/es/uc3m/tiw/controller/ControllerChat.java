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
import es.uc3m.tiw.domains.MensajesDAO;

@RestController
@CrossOrigin
public class ControllerChat {
	
	@Autowired
	MensajesDAO MensajesDAO;
	
	
	RestTemplate restTemplate;
	
	@RequestMapping (value="/mensajes", method = RequestMethod.GET)
	public List<Mensaje> mensajes(){
		System.out.println("Buscar todos los IDs usuarios que han mandado un mensaje a mi persona");
		return MensajesDAO.findAll() ;
	}
	
	@RequestMapping (value="/mensajes/{idemisor}/{iddestinatario}/{mensaje}", method = RequestMethod.GET)
	public Mensaje EnvioMensaje(@PathVariable("idemisor") int idemisor, 
			@PathVariable("iddestinatario") int iddestinatario,
			@PathVariable("mensaje") String mensaje){
		System.out.println("Enviar el siguiente mensaje "+ mensaje);
		
		Mensaje mensajeSend= new Mensaje();
		mensajeSend.setIdemisor(idemisor);
		mensajeSend.setIddestinatario(iddestinatario);
		mensajeSend.setMensaje(mensaje);
		return MensajesDAO.save(mensajeSend) ;
	}
	
	
	
	@RequestMapping (value="/messages", method = RequestMethod.POST)
	public Mensaje enviar(@RequestBody Mensaje mensaje){
		System.out.println("Almacenar un mensaje en la BD");
		return MensajesDAO.save(mensaje);
	}
}
	
	/*
	@RequestMapping (value="messages/{idemisor}/{iddestintario}", method = RequestMethod.GET)
	public MensajesDAO findMensajes(@PathVariable("idemisor" Integer idemisor) @PathVariable("iddestinatario" Integer iddestinatario)){
		System.out.println("Buscar mensajes de un usuario concreto, mediante su ID");
		return MensajesDAO.findMensajes(idemisor,iddestinatario);
	}
	*/
	
	/*
	@RequestMapping (value="/usersCount", method = RequestMethod.GET)
	public Count cantidad(){
		System.out.println("Cantidad de elementos");
		return new Count(MensajesDAO.count());
	}
	
	*/

// ASI COGEMOS POR POST LOS ELEMENTOS QUE TENEMOS EN EL JSP O EN LOS HTML. 
//SE IMPLEMENTA EN EL CONTROLLER PRINCIPAL DEL PROYECTO WEB DINAMICO
/*case "calculate":
				
				String operation = request.getParameter("operation");
				// There may be exceptions...
				Operands operands = new Operands();
				operands.setOperand1(Integer.parseInt(request.getParameter("operand1")));
				operands.setOperand2(Integer.parseInt(request.getParameter("operand2")));
				
				/*
				// REST Client using GET Verb and Parameters
				Client client = ClientBuilder.newClient();
				WebTarget webResource = client.target("http://localhost:8081").path(operation)
						.queryParam("operand1", operands.getOperand1())
						.queryParam("operand2", operands.getOperand2());
				Result result=	webResource.request().accept("application/json").get(Result.class);
				
				
				// REST Client using POST Verb and JSON
				Client client = ClientBuilder.newClient();
				WebTarget webResource = client.target("http://localhost:8081").path(operation);
				Result result=	webResource.request("application/json").accept("application/json").post(Entity.entity(operands,MediaType.APPLICATION_JSON),Result.class);
				
				
				// REST Client using GET Verb and Path Variable
				/*Client client = ClientBuilder.newClient();
				WebTarget webResource = client.target("http://localhost:8081").path(operation)
						.path(request.getParameter("operand1"))
						.path(request.getParameter("operand2"));
				Result result=	webResource.request().accept("application/json").get(Result.class);
				
				
				
				request.setAttribute("result", result);
				
				
				*/
				
				
				
		/*	javax.ws.rs.client.Client client = ClientBuilder.newClient();
				WebTarget target = client.target("http://localhost:8081").path("sum");*/
				
				
				//target.matrixParam(name, values)
				
				//JerseyClient jerseyClient = JerseyClientBuilder.createClient();
			//	JerseyWebTarget jerseyWebTarget =  jerseyClient.target("http://localhost:8081").path("sum");
		//ClientResponse responsea = jerseyWebTarget.res;
			/* String inputData = "{\"firstName\":\"Alice\",\"lastName\":\"Brown\",\"school\":\"Bright Stars\",\"standard\":\"Three\",\"rollNumber\":1212}";
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class,inputData);
			 if(response.getStatus()!=201){
				throw new RuntimeException("HTTP Error: "+ response.getStatus());
				 }
				*/
				
				
				
				
			/*	Client client = ClientBuilder.newClient();
				   Response res = client.target("http://example.org/hello").request("text/plain").get();
				
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
						
				//System.out.println(result);
			
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
				break;
			default:
				RequestDispatcher home = request.getRequestDispatcher("index.jsp");
				home.forward(request, response);
				break;
		}
		
		
		
		
		
	}

}
*/