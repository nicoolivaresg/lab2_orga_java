package lab2_orga;

public class MUX_2 {
	public Integer enable;
	public Integer Estado;
	public Integer Entrada0;
	public Integer Entrada1;
	public Integer Salida;
	
	public MUX_2(){
		this.enable = 1;
	}
	
	public Integer evaluar(){
		if (Estado == 1){
			Salida = Entrada1;
		}else if(Estado == 0){
			Salida = Entrada0;
		}
		return Estado;	
	}
	
	
}
