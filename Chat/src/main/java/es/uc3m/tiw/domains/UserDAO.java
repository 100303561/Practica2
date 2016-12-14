package es.uc3m.tiw.domains;


import org.springframework.data.repository.CrudRepository;


public interface UserDAO  extends CrudRepository<User, Integer> {

	
	  
}