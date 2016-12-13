package es.uc3m.tiw.domains;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface MensajesDAO extends CrudRepository <Mensaje, Integer> {
	
//	public List<Mensaje> findById (int iddestinatario);
	public  List<Mensaje> findAll();
//	public List<Mensaje> findMensajes(int idemisor, int iddestinatario);
	
	
	
	//@Query("select m from Mensaje m where m.idemisor = :iddestinatario group by idemisor")
	public List<Mensaje> findMensajesByiddestinatario(int iddestinatario);
	
	@Query("select idemisor from Mensaje m where m.iddestinatario = :iddestinatario group by m.idemisor")
	public List<Mensaje> findCustom(@Param ("iddestinatario") int iddestinatario);


/*
@Query("select distinct idemisor from Mensaje mensajes where mensajes.iddestinatario=:iddestinatiario order by idmessage desc")
public List<Integer> customVerConversaciones(@Param("iddestinatario") int iddestinatario);
//}	findDistinctUserByMensajeReceptorOrMensajeEmisor*/
	
}
