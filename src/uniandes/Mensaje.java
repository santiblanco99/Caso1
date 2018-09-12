package uniandes;

public class Mensaje {

	private String contenido;

	private boolean modificado;

	public boolean fueModificado() {
		return modificado;
	}

	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	public Mensaje(String contenido) {
		this.contenido = contenido;
	}

	public String getMensaje() {
		return contenido;
	}

	public void setMensaje(String contenido) {
		this.contenido = contenido;
	}

}
