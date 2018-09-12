package uniandes;

import java.util.List;

public class Cliente extends Thread{
	
	/**
	 * Identificación del cliente.
	 */
	private int id;
	
	/**
	 * Lista de mensajes del cliente.
	 */
	private Mensaje[] mensajes;
	
	/**
	 * Instancia del Buffer.
	 */
	private Buffer buffer;
	
	/**
	 * Constructor de la clase Cliente
	 * @param id Identificador del cliente.
	 * @param numeroMensaje Número de consultas realizadas por el cliente.
	 * @param buffer Instancia del Buffer.
	 */
	public Cliente(int id, int numeroMensajes, Buffer buffer) {
		this.id = id;
		this.mensajes = new Mensaje[numeroMensajes];
		this.buffer = buffer;
	}
	
	public void run() {
		String cliente = "Cliente #"+id+": ";//Variable para no tener que escribir siempre "Cliente #id: " en los sysout
		System.out.println(cliente+"entré.");
		for(Mensaje mensaje:mensajes) {//Por cada mensaje quiere agregarlo al buffer.
			int n = Double.valueOf(Math.random()*100).intValue();//Valor aleatorio del mensaje.
			String m = "Mensaje #"+n;
			mensaje = new Mensaje(m);
			System.out.println(cliente+"quiero subir un mensaje#"+n);
			buffer.enviar(mensaje);
			System.out.println(cliente+"me respondieron mensaje#"+n);
		}
		buffer.reducirNumeroClientes();//Le avisa al buffer que se fue.
		System.out.println(cliente+"salí.");
	}
	
	
	
	
	
	
	
	
	
	
	

}
