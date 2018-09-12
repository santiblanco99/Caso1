package uniandes;

public class Buffer {

	private Mensaje[] contenido;

	private int tamano;

	private int tamanoOriginal;
	
	private int contador;
	


	public Buffer(int n){
		this.contenido = new Mensaje[n];
		this.tamano = n;
		this.tamanoOriginal= n;
		this.contador=0;

	}


	public synchronized void enviar(Cliente cli){
		System.out.println("entro cliente: "+cli.getId()+"con msg: "+cli.getMensaje());
		tamano--;

		if(tamano>=0){
			Mensaje actual = cli.crearMensaje();
			contenido[contador]= actual;
			if(contador<tamano-2) {
				contador++;
			}
			else {
				contador = 0;
			}
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cli.setMensaje(actual);
			tamano++;
		}
		else{
			Cliente.yield();



		}

	}

	public synchronized void recibir(Servidor serv){

		if(tamanoOriginal>tamano){
			for(int i = 0;i<=contador;i++) {
				Mensaje msg = contenido[i];
				if(msg.isMod()==false) {
					msg.setMensaje(msg.getMensaje()+1);
					msg.setMod(true);
					System.out.println(msg.getMensaje());
					break;
				}
			}
			this.notify();
			Servidor.yield();
		}
		else{

				Servidor.yield();
		}
	}

	public static void main(String[] args){
		Buffer buffer = new Buffer(1);
		Double n =(Math.random()*100);
		int f =n.intValue();
		Cliente[] clientes = new Cliente[2];
		Servidor[] servidores = new Servidor[2];
		for(int i =0; i<2;i++) {
			servidores[i]= new Servidor(buffer, i);
			clientes[i] = new Cliente(i,i+1,buffer);
		}
		for(Cliente cli: clientes) {
			cli.start();
		}
		for(Servidor serv: servidores) {
			serv.start();
		}
	}




}
