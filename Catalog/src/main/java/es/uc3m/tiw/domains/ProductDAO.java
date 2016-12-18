package es.uc3m.tiw.domains;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductDAO extends CrudRepository<Product, Integer>{
	
	@Query("select p from Product p WHERE ((p.product_name LIKE CONCAT('%', :search,'%') OR p.description LIKE CONCAT('%', :search, '%') OR :search IS NULL) AND (p.category = :category or :category IS NULL)AND (p.city = :city or :city IS NULL) AND (p.userName LIKE CONCAT('%', :owner, '%') OR :owner IS NULL))")
	public List<Product> advandcedSearch (@Param ("search") String search, @Param("category") String category, @Param("city") String city, @Param("owner") String owner);

	//Busqueda de todos los productos de un usuario
	public List<Product> findByUser(int userID);
	
	//Lista de todos los productos de la bbdd
	public List<Product> findAll();

}
