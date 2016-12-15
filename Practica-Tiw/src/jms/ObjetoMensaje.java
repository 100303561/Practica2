package jms;

import java.io.Serializable;


public class ObjetoMensaje implements Serializable {
	
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -3119519964395729811L;
		int emisor;
		int destinatario;
		String mensaje;
		
		public int getEmisor() {
			return emisor;
		}
		public void setEmisor(int emisor){
			this.emisor = emisor;
		}
		public int getDestinatario() {
			return destinatario;
		}
		public void setDestinatario(int destinatario){
			this.destinatario = destinatario;
		}
		public String getMensaje() {
			return mensaje;
		}
		
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
		@Override
		public String toString() {
			return "ObjetoMensaje emisor=" + emisor + "destinatario=" + destinatario + "mensaje=" + mensaje + "<br>";
		}
	
		
		//Falta añadir int nombre por ID que es una consulta para ver directamente el nombre	
}
