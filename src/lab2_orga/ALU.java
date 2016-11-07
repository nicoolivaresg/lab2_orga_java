package lab2_orga;

public class ALU {
	public Registro R;
	public Integer operadorA;
	public Integer operadorB;
	public Integer enable;
	public Integer ALUout;
	public Integer Zero;
	
	
	
	public void operar(Instruction inst) throws Exception{
		if(inst.Mnemonic.compareTo("add")==0){
			ALUout = operadorA + operadorB;
			Zero = 0;
		}else if(inst.Mnemonic.compareTo("addi")==0){
			ALUout = operadorA + operadorB;
			Zero = 0;
		}else if(inst.Mnemonic.compareTo("mul")==0){
			ALUout = operadorA*operadorB;
			Zero = 0;
		}else if(inst.Mnemonic.compareTo("j")==0){
			ALUout = 0;
			Zero = 0;
		}else if(inst.Mnemonic.compareTo("beq")==0){
			if(operadorA == operadorB){
				Zero = 1;
				ALUout = 0;
			}else{
				Zero = 0;
				ALUout = 0; 
			}
		}else if(inst.Mnemonic.compareTo("blt")==0){
			if(operadorA < operadorB){
				Zero = 1;
				ALUout = 0;
			}else{
				Zero = 0;
				ALUout = 0; 
			}
		}else if(inst.Mnemonic.compareTo("bgt")==0){
			if(operadorA > operadorB){
				Zero = 1;
				ALUout = 0;
			}else{
				Zero = 0;
				ALUout = 0; 
			}
		}else if(inst.Mnemonic.compareTo("nop")==0){
			
		}else if(inst.Mnemonic.compareTo("div")==0){
			try{
				ALUout = operadorA/operadorB;
				int remainder = operadorA % operadorB;
				R.registers.put("hi", ALUout);
				R.registers.put("lo", remainder);
				Zero = 0;
			}catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}else if(inst.Mnemonic.compareTo("mflo")==0){
			ALUout = R.registers.get("lo");
		}else if(inst.Mnemonic.compareTo("mfhi")==0){
			ALUout = R.registers.get("hi");
		}else if(inst.Mnemonic.compareTo("lw")==0){
			ALUout = operadorB/4;
		}else if(inst.Mnemonic.compareTo("sw")==0){
			ALUout = operadorB/4;
		}else if(inst.Mnemonic.compareTo("la")==0){
			ALUout = operadorB;
		}
		
	}
	
	
	
}
