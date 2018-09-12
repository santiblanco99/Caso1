package uniandes;

public class Servidor extends Thread {
	
	private int mensaje;
	
	private int id;
	
	public Buffer buffer;
	
	
	public Servidor(Buffer buffer,int n) {
		this.buffer = buffer;
		this.id=n;
	}
	
	public void run() {
		System.out.println("entra servidor con id: "+id);
		this.buffer.recibir(this);
		System.out.println("sale servidor con id: "+id);
	
	}
	
	
	
	
	
	
	
	
	
	
	

}
