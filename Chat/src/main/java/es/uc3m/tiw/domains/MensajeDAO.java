package es.uc3m.tiw.domains;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface MensajeDAO extends CrudRepository <Mensaje, Integer> {
	
//	public List<Mensaje> findById (int iddestinatario);
	public  List<Mensaje> findAll();
//	public List<Mensaje> findMensajes(int idemisor, int iddestinatario);
	
	
	//Filtrar mensajes que son para mi de un destinatario
	@Query("select m from Mensaje m where (m.idemisor = :idAjeno and m.iddestinatario = :idPropio)")
	public List<Mensaje> mensajesRecibidos(@Param ("idPropio")int idPropio,@Param ("idAjeno") int idAjeno);
	
	//Filtrar mensajes que envio a un destinatario
	@Query("select m from Mensaje m where (m.idemisor = :idPropio and m.iddestinatario = :idAjeno)")
	public List<Mensaje> mensajesEnviados(@Param ("idPropio") int idPropio, @Param ("idAjeno") int idAjeno);
	
	
	
	//DistinctRecibo
	@Query("select m,idemisor from Mensaje m where m.iddestinatario = :idPropio group by m.idemisor")
	public List<Mensaje> findCustomRecibido(@Param ("idPropio") int idPropio);

	//DistinctEnvio
	@Query("select m,idemisor from Mensaje m where m.idemisor = :idPropio group by m.iddestinatario")
	public List<Mensaje> findCustomEnviado(@Param ("idPropio") int idPropio);

/*
@Query("select distinct idemisor from Mensaje mensajes where mensajes.iddestinatario=:iddestinatiario order by idmessage desc")
public List<Integer> customVerConversaciones(@Param("iddestinatario") int iddestinatario);
//}	findDistinctUserByMensajeReceptorOrMensajeEmisor*/
	
}
