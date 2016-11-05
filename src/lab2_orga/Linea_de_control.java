package lab2_orga;

public class Linea_de_control {
	private Integer activa;
		
	public Linea_de_control(){
		this.activa = 0;
	}
	
	public boolean estado(){
		if (this.activa == 1){
			return true;
		}else{
			return false;
		}
	}
	
	public Integer getActivo(){
		return this.activa;
	}
	
	public void activar(){
		this.activa = 1;
	}
	
	public void desactivar(){
		this.activa = 0;
	}
}
