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
		System.out.println(servidor+"entr�");
		while(buffer.getNumeroClientes()>0) {
			System.out.println(servidor+"quiero responder un mensaje");
			buffer.recibir();
			System.out.println(servidor+"respond� un mensaje");
		}
		System.out.println(servidor+"me apagu�");
	}
	
	
	
	
	
	
	
	
	
	
	

}
