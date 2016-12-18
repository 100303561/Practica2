package es.uc3m.tiw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.uc3m.tiw.domains.Product;
import es.uc3m.tiw.domains.ProductDAO;

@RestController
@CrossOrigin
public class CatalogController {

	@Autowired
	ProductDAO productDAO;

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public void createProduct(@RequestBody Product product) {

		System.out.println(product);
		productDAO.save(product);
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public Product getSingle(@PathVariable int id) {
		return productDAO.findOne(id);
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public List<Product> getAll() {
		return productDAO.findAll();
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public void updateProduct(@PathVariable int id, @RequestBody Product product) {
		Product p = productDAO.findOne(id);
		
		System.out.println(p);
		productDAO.delete(p);

		System.out.println(product);
		productDAO.save(product);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable int id) {
		Product p = productDAO.findOne(id);
		System.out.println(p);
		productDAO.delete(p);
	}

	@RequestMapping(value = "/products/{search}/{category}/{city}/{owner}", method = RequestMethod.GET)
	public List<Product> getProducts(@PathVariable("search") String search, @PathVariable("category") String category, @PathVariable("city") String city, @PathVariable("owner") String owner) {
		System.out.println(city);
		//Si hemos tenido que cambiar algun valor antes lo volvemos a dejar como null
		//para usarlo en la consulta
		if (city.equals("-1"))
			city = null;
		if (category.equals("-1"))
			category = null;
		if (search.equals("-1"))
			search = null;
		if (owner.equals("-1"))
			owner = null;
		
		List<Product> list = productDAO.advandcedSearch(search, category, city, owner);
		System.out.println("Lista: " + list.toString());
		return list;
	}

//	Devuelve todos los productos de un usuario
	@RequestMapping(value = "/userProducts/{userID}", method = RequestMethod.GET)
	public List<Product> getProductcByUser(@PathVariable("userID") int userID) {
		List<Product> list = productDAO.findByUser(userID);
		System.out.println("Lista: " + list.toString());
		return list;
	}
}
