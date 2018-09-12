package uniandes;

public class Cliente extends Thread{
	
	private int id;
	
	private int msg;
	
	private Buffer buffer;
	
	private Mensaje mensaje;
	
	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

	public Cliente(int n, int msg, Buffer buffer) {
		this.id = n;
		this.msg = msg;
		this.buffer = buffer;
	}
	
	public Mensaje crearMensaje() {
		Mensaje mensaje = new Mensaje(msg);
		return mensaje;
	}
	
	public void run() {
		
		System.out.println("entra cliente con id: "+id);
		System.out.println("mensaje de cliente "+id + " = "+msg);
		this.buffer.enviar(this);
		System.out.println("Mensaje nuevo del cliente: " +id+" = "
		+mensaje.getMensaje());
		System.out.println("sale cliente con id: "+id);
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
