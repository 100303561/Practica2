package es.uc3m.tiw.domains;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserDAO  extends CrudRepository<User, Integer> {

	
	  
	  //Encontrar usuario por email
	  public User findByEmail (String email);
	  
	  
	  // los métodos findOne, save... ya viene por herencia
		
	  public List<User> findByName(String name);
	  
	 // Si hubiera una relación entre usuario y direcciones también es posible 
	  // buscar usuarios con el nombre de la calle de la otra tabla
	  // Address es el nombre de la tabla que está podría estar relacionado con user
	  // Street es el nombre del campo de la tabla de Address
	 // public List<User> findByAddressStreet(String streetname);
	  public List<User> findAll();
	  
	  //Comprobar nombre de ususario y contraseña correctas
	  @Query("select a from User a where (a.email=:email and a.password=:password)")
	  public boolean custom (@Param ("email") String email, @Param("password") String password);
	  
}