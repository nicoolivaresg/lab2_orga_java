package lab2_orga;

public class Forwarding_Unit {
	public String ForwardA =null;
	public String ForwardB=null;
	public String ID_EX_Rt=null;
	public String ID_EX_Rs=null;
	public String EX_MEM_RegistroResultado=null;
	public String MEM_WB_RegistroResultado=null;
	public Linea_de_control MEM_WB_RegWrite=null;
	public Linea_de_control EX_MEM_RegWrite=null;
	
	public Forwarding_Unit(){
		
	}
	
	public boolean evaluar(){
		if(ID_EX_Rs!=null || ID_EX_Rt != null || EX_MEM_RegistroResultado != null || MEM_WB_RegistroResultado != null ||MEM_WB_RegWrite !=null || EX_MEM_RegWrite != null){
			if(EX_MEM_RegWrite.estado() &&
				EX_MEM_RegistroResultado.compareTo("$zero") == 0 &&
				EX_MEM_RegistroResultado.compareTo(ID_EX_Rs) == 0 ){
				ForwardA = "10";
			}else if(EX_MEM_RegWrite.estado() &&
				EX_MEM_RegistroResultado.compareTo("$zero") == 0 &&
				EX_MEM_RegistroResultado.compareTo(ID_EX_Rt) == 0 ){
				ForwardB = "10";
			}else if(MEM_WB_RegWrite.estado() &&
				MEM_WB_RegistroResultado.compareTo("$zero") == 0 &&
				!(EX_MEM_RegWrite.estado() &&
						EX_MEM_RegistroResultado.compareTo("$zero") == 0 &&
						EX_MEM_RegistroResultado.compareTo(ID_EX_Rs) == 0 ) &&
				MEM_WB_RegistroResultado.compareTo(ID_EX_Rs) == 0 ){
				ForwardA = "01";
			}else if(MEM_WB_RegWrite.estado() &&
				MEM_WB_RegistroResultado.compareTo("$zero") == 0 &&
				!(EX_MEM_RegWrite.estado() &&
						EX_MEM_RegistroResultado.compareTo("$zero") == 0 &&
						EX_MEM_RegistroResultado.compareTo(ID_EX_Rt) == 0 )&&
				MEM_WB_RegistroResultado.compareTo(ID_EX_Rt) == 0){
				ForwardB = "01";
			}
			return true;
		}else{
			return false;
		}
	}
}


