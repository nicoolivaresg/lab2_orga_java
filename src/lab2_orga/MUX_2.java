package lab2_orga;

public class MUX_2 {
	private Integer enabled;
	public Integer Estado = 0;
	public Integer Entrada0;
	public Integer Entrada1;
	public String EntradaReg0;
	public String EntradaReg1;
	private Integer Salida;
	private String SalidaReg;
	
	public MUX_2(){
		this.enabled = 1;
	}
	
	public String evaluarStr(){
		if (Estado == 1 && enabled == 1){
			SalidaReg = EntradaReg1;
		}else if(Estado == 0 && enabled == 1){
			SalidaReg = EntradaReg0;
		}else{
			return null;
		}
		return SalidaReg;		
	} 
	public Integer evaluar(){
		if (Estado == 1 && enabled == 1){
			Salida = Entrada1;
		}else if(Estado == 0 && enabled == 1){
			Salida = Entrada0;
		}else{
			return null;
		}
		return Salida;		
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
