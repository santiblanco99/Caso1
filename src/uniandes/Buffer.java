package uniandes;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

	/**
	 * Lista del contenido que se encuentra en el buffer.
	 */
	private List<Mensaje> contenido;

	/**
	 * Tamaño límite del buffer.
	 */
	private int tamaño;

	/**
	 * Número de clientes que accederán al buffer.
	 */
	private int numeroClientes;


	public Buffer(int tamaño,int numeroClientes) {
		this.contenido = new LinkedList<>();
		this.tamaño = tamaño;
		this.numeroClientes=numeroClientes;
	}


	public synchronized void enviar(Mensaje mensaje) {
		while (contenido.size() == tamaño) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Thread.yield();//Cede el procesador después de cada intento.
		}
		String buffer = "Buffer: ";//Variable para no tener que escribir siempre "Buffer: " en los sysout
		System.out.println(buffer+"hay espacio para mensajes.");
		contenido.add(mensaje);
		if (contenido.size() == 1) {//Si antes no había contenido despierta a los servidores.
			notifyAll();
		}
		while(!mensaje.fueModificado()) {//Mientras no hayan respondido el mensaje el cliente se queda dormido.
			try {
				wait();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void recibir() {
		while (contenido.size() == 0 && numeroClientes>0) {//Mientras no haya mensajes pero no se haya atendido a todos los clientes.
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(numeroClientes==0) {
				return;
			}
			System.out.println("esperando");
			Thread.yield();
		}
		if(numeroClientes==0) {//No va a haber más mensajes pues no hay más clientes.
			return;
		}
		String buffer = "Buffer: ";//Variable para no tener que escribir siempre "Buffer: " en los sysout
		System.out.println(buffer + "hay mensajes pendientes.");
		Mensaje mensaje = contenido.remove(contenido.size() - 1);
		mensaje.setModificado(true);
		System.out.println(buffer + "mensaje modificado " + mensaje.getMensaje());
		notifyAll();//Modificó un mensaje, ahora le envía la notificación a todos los clientes para que revisen sus mensajes pendientes.

	}

	public synchronized int getNumeroClientes() {
		return numeroClientes;
	}

	public synchronized void reducirNumeroClientes() {
		notifyAll();//Notifica a los servidores de que hay un cliente menos, de ese modo no se queda en espera si ya no hay clientes para atender.
		--this.numeroClientes;
	}

	public static void main(String[] args) {
		int[] info = leerInfo();

		
		int numeroClientes = info[0];
		int tamañoBuffer = info[3];
		int numeroServidores = info[1];
		int numeroMensajes = info[2];

		Buffer buffer = new Buffer(tamañoBuffer,numeroClientes);
		Cliente[] clientes = new Cliente[numeroClientes];
		Servidor[] servidores = new Servidor[numeroServidores];
		int i = 0;
		for (Cliente cli : clientes) {
			cli = new Cliente(i, numeroMensajes, buffer);
			clientes[i++] = cli;
		}
		i = 0;
		for (Servidor serv : servidores) {
			serv = new Servidor(buffer, i);
			servidores[i++] = serv;
		}
		int orden = Double.valueOf(Math.random() * 100).intValue() % 2; //Orden define si se inician primero los clientes o los servidores.
		if (orden == 1) {
			for (Cliente cli : clientes) {
				cli.start();
			}
			for (Servidor serv : servidores) {
				serv.start();
			}
		} else {
			for (Servidor serv : servidores) {
				serv.start();
			}
			for (Cliente cli : clientes) {
				cli.start();
			}
		}


	}

	public static int[] leerInfo() {
		int[]resp = new int[4];
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/info.txt"));
			String line = reader.readLine();
			System.out.println(line);

			String [] info =line.split(",");
			for(int i= 0; i< resp.length; i++) {
				resp[i] = Integer.parseInt(info[i]);
			}


			reader.close();
			return resp;
		}
		catch(Exception e) {
			System.out.println("Error leyendo el archivo");

		}

		return resp;
	}



}





