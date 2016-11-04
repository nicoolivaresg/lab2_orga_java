package lab2_orga;

public class Linea_de_control {
	private int activo;
	private static Linea_de_control instance = null;
	protected Linea_de_control() {
		// Exists only to defeat instantiation.
		this.activo = 0;
	}
	public static Linea_de_control getInstance() {
		if(instance == null) {
			instance = new Linea_de_control();
		}
		return instance;
	}
	
	public int getActivo(){
		return this.activo;
	}
	
	public void activar(){
		this.activo = 1;
	}
	
	public void desactivar(){
		this.activo = 0;
	}
}
