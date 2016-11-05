package lab2_orga;

public class ALU {
	private Registro R;
	private int operadorA;
	private int operadorB;
	private int enable;
	private int ALUout;
	private int Zero;
	private static ALU instance = null;
	protected ALU(Buffer ID_EX,Buffer EX_MEM) {
		// Exists only to defeat instantiation.
		this.enable = 1;
	}
	public static ALU getInstance(Buffer ID_EX,Buffer EX_MEM) {
		if(instance == null) {
			instance = new ALU(ID_EX,EX_MEM);
		}
		return instance;
	}
	
	public int getOperadorA() {
		return operadorA;
	}
	public void setOperadorA(int operadorA) {
		this.operadorA = operadorA;
	}
	public int getOperadorB() {
		return operadorB;
	}
	public void setOperadorB(int operadorB) {
		this.operadorB = operadorB;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public int getALUout() {
		return ALUout;
	}
	public int getZero() {
		return Zero;
	}
	
	public void operar(String instruccion) throws Exception{
		if(instruccion == "add"){
			this.ALUout = this.operadorA + this.operadorB;
			this.Zero = 0;
		}else if(instruccion == "addi"){
			this.ALUout = this.operadorA + this.operadorB;
			this.Zero = 0;
		}else if(instruccion == "mul"){
			this.ALUout = this.operadorA*this.operadorB;
			this.Zero = 0;
		}else if(instruccion == "j"){
			this.ALUout = 0;
			this.Zero = 0;
		}else if(instruccion == "beq"){
			if(this.operadorA == this.operadorB){
				this.Zero = 1;
				this.ALUout = 0;
			}else{
				this.Zero = 0;
				this.ALUout = 0; 
			}
		}else if(instruccion == "blt"){
			if(this.operadorA < this.operadorB){
				this.Zero = 1;
				this.ALUout = 0;
			}else{
				this.Zero = 0;
				this.ALUout = 0; 
			}
		}else if(instruccion == "bgt"){
			if(this.operadorA > this.operadorB){
				this.Zero = 1;
				this.ALUout = 0;
			}else{
				this.Zero = 0;
				this.ALUout = 0; 
			}
		}else if(instruccion == "nop"){
			
		}else if(instruccion == "div"){
			try{
				this.ALUout = this.operadorA/this.operadorB;
				int remainder = this.operadorA % this.operadorB;
				R.registers.put("hi", this.ALUout);
				R.registers.put("lo", remainder);
				this.Zero = 0;
			}catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}else if(instruccion == "mflo"){
			this.ALUout = R.registers.get("lo");
		}else if(instruccion == "mfhi"){
			this.ALUout = R.registers.get("hi");
		}else if(instruccion == "lw"){
			this.ALUout = this.operadorB/4;
		}else if(instruccion == "sw"){
			this.ALUout = this.operadorB/4;
		}else if(instruccion == "la"){
			this.ALUout = this.operadorB;
		}
		
	}
	
	
	
}
