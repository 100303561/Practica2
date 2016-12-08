package es.uc3m.tiw.domains;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface MensajesDAO extends CrudRepository <Mensaje, Integer> {
	
//	public List<Mensaje> findById (int iddestinatario);
	public  List<Mensaje> findAll();
//	public List<Mensaje> findMensajes(int idemisor, int iddestinatario);
}
