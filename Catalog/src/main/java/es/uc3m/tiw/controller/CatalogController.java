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
	ProductDAO ProductDAO;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void createProduct(@RequestBody Product product)
	{

		System.out.println(product);
		ProductDAO.save(product);
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.POST)
	public void updateProduct(@PathVariable int id, @RequestBody Product product)
	{
		Product p = ProductDAO.findOne(id);
		
		System.out.println(p);
		ProductDAO.delete(p);

		System.out.println(product);
		ProductDAO.save(product);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable int id)
	{
		Product p = ProductDAO.findOne(id);
		System.out.println(p);
		ProductDAO.delete(p);		
	}
	@RequestMapping(value = "/products{search}/{category}", method = RequestMethod.GET)
	public List<Product> usuarios(@PathVariable("search") String search, @PathVariable("category") String category) {
		List<Product> list = ProductDAO.advandcedSearch(search, category);
		System.out.println("Lista: " + list.toString());
		return list;
	}
}
