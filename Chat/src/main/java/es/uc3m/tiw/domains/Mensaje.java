package es.uc3m.tiw.domains;

import java.io.Serializable;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Mensaje implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@NumeroMensaje
	@GeneratedValue
	@Colum(name="numeroMensaje")
	
	private int NumeroMensaje;
	private int IDRemitente;
	private int IDDEstinatario;
	private String Mensaje;
	
	
	public int getIDRemitente() {
		return IDRemitente;
	}


	public void setIDRemitente(int iDRemitente) {
		IDRemitente = iDRemitente;
	}


	public int getIDDEstinatario() {
		return IDDEstinatario;
	}


	public void setIDDEstinatario(int iDDEstinatario) {
		IDDEstinatario = iDDEstinatario;
	}


	public String getMensaje() {
		return Mensaje;
	}


	public void setMensaje(String mensaje) {
		Mensaje = mensaje;
	}


	public int getNumeroMensaje() {
		return numeroMensaje;
	}


	public void setNumeroMensaje(int numeroMensaje) {
		this.numeroMensaje = numeroMensaje;
	}


	public Mensaje() {
		super();
		
	}


	public Mensaje(int numeroMensaje, int iDRemitente, int iDDEstinatario, String mensaje) {
		super();
		this.numeroMensaje = numeroMensaje;
		IDRemitente = iDRemitente;
		IDDEstinatario = iDDEstinatario;
		Mensaje = mensaje;
	}


	
}
