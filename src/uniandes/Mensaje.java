package uniandes;

public class Mensaje {
	private int mensaje;
	
	private boolean mod = false;
	
	public boolean isMod() {
		return mod;
	}

	public void setMod(boolean mod) {
		this.mod = mod;
	}

	public Mensaje(int n) {
		this.mensaje = n;
	}

	public int getMensaje() {
		return mensaje;
	}

	public void setMensaje(int mensaje) {
		this.mensaje = mensaje;
	}

}
