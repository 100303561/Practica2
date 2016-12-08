package es.uc3m.tiw.domains;

import org.springframework.data.repository.CrudRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AdminDAO extends CrudRepository<Admin, Integer> {

	// Encontrar administrador por email
	public Admin findByEmail(String email);
}
