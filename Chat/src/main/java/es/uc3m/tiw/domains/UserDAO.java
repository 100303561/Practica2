package es.uc3m.tiw.domains;

import java.util.List;


public class UserDAO extends CrudRepository <User, Integer> {

	public List<User> findByName(String name);
	public List<User> findAll();
	
}
