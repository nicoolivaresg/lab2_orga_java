package lab2_orga;

public class MUX_3 {
	public Integer enable;
	public String Estado;
	public Integer Entrada0;
	public Integer Entrada1;
	public Integer Entrada2;
	public Integer Salida;
	
	public MUX_3(){
		this.enable = 1;
		this.Estado = "11";
		this.Entrada0 =0;
		this.Entrada1 =0;
	}
	
	public void evaluar(){
		if(enable ==1){
			if (Estado == "00"){
				Salida = Entrada0;
			}else if(Estado == "01"){
				Salida = Entrada1;
			}else if(Estado == "10"){
				Salida = Entrada2;
			}
		}
	}
}
