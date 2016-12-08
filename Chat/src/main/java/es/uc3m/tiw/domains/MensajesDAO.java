package es.uc3m.tiw.domains;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MensajesDAO extends CrudRepository <Mensaje, Integer> {

//	public List<Mensaje> findById (int iddestinatario);
	public List<Mensaje> findAll();
	
}
