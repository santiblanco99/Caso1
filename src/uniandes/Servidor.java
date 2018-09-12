package uniandes;

public class Servidor extends Thread {
	
	private int id;
	
	public Buffer buffer;
	
	
	public Servidor(Buffer buffer,int id) {
		this.buffer = buffer;
		this.id=id;
	}
	
	public void run() {
		String servidor = "Servidor #"+id+": ";
		System.out.println(servidor+"entré");
		while(buffer.getNumeroClientes()>0) {
			System.out.println(servidor+"quiero responder un mensaje");
			buffer.recibir();
			System.out.println(servidor+"respondí un mensaje");
		}
		System.out.println(servidor+"me apagué");
	}
	
	
	
	
	
	
	
	
	
	
	

}
