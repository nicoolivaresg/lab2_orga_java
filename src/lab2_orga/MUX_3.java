package lab2_orga;

public class MUX_3 {
	private Integer enabled;
	private String Estado;
	public Integer Entrada0;
	public Integer Entrada1;
	public Integer Entrada2;
	private Integer Salida;
	
	public MUX_3(){
		this.enabled = 1;
		this.Estado = "00";
		this.Entrada0 =0;
		this.Entrada1 =0;
	}
	
	public Integer evaluar(){
		if(enabled ==1){
			if (Estado.compareTo("00")==0){
				Salida = Entrada0;
			}else if(Estado.compareTo("01")==0){
				Salida = Entrada1;
			}else if(Estado.compareTo("10")==0){
				Salida = Entrada2;
			}
			return Salida;
		}else{
			return null;
		}
	}
	
	public boolean isEnabled(){
		if(enabled == 1){
			return true;
		}else{
			return false;
		}
	}
	
	public void setEnable(){
		enabled = 1;
	}
	
	public void setDisable(){
		enabled = 0;
	}
}
