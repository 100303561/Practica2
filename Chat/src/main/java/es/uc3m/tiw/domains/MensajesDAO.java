package es.uc3m.tiw.domains;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface MensajesDAO extends CrudRepository <Mensaje, Integer> {
	
//	public List<Mensaje> findById (int iddestinatario);
	public  List<Mensaje> findAll();
//	public List<Mensaje> findMensajes(int idemisor, int iddestinatario);
	
	
	//Filtrar mensajes que son para mi de un destinatario
	@Query("select m from Mensaje m where (m.idemisor = :idAjeno and m.iddestinatario = :idPropio)")
	public List<Mensaje> mensajesRecibidos(@Param ("idPropio")int idPropio,@Param ("idAjeno") int idAjeno);
	
	//Filtrar mensajes que envio a un destinatario
	@Query("select m from Mensaje m where (m.idemisor = :idPropio and m.iddestinatario = :idAjeno)")
	public List<Mensaje> mensajesEnviados(@Param ("idPropio") int idPropio, @Param ("idAjeno") int idAjeno);
	
	
	
	//Distinct
	@Query("select m,idemisor from Mensaje m where m.iddestinatario = :iddestinatario group by m.idemisor")
	public List<Mensaje> findCustom(@Param ("iddestinatario") int iddestinatario);


/*
@Query("select distinct idemisor from Mensaje mensajes where mensajes.iddestinatario=:iddestinatiario order by idmessage desc")
public List<Integer> customVerConversaciones(@Param("iddestinatario") int iddestinatario);
//}	findDistinctUserByMensajeReceptorOrMensajeEmisor*/
	
}
