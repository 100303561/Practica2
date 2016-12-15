package es.uc3m.tiw.domains;


import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Mensaje implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue
	@Column(name="idmessage")
	private int idmessage;
	private int idemisor;
	private int iddestinatario;
	private String mensaje;
	
	public int getIdmessage() {
		return idmessage;
	}


	public void setIdmessage(int idmessage) {
		this.idmessage = idmessage;
	}


	public int getIdemisor() {
		return idemisor;
	}


	public void setIdemisor(int idemisor) {
		this.idemisor = idemisor;
	}


	public int getIddestinatario() {
		return iddestinatario;
	}


	public void setIddestinatario(int iddestinatario) {
		this.iddestinatario = iddestinatario;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public Mensaje(int idmessage, int idemisor, int iddestinatario, String mensaje) {
		super();
		this.idmessage = idmessage;
		this.idemisor = idemisor;
		this.iddestinatario = iddestinatario;
		this.mensaje = mensaje;
	}


	public Mensaje() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}