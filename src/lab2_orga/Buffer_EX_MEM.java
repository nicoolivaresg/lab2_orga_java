package lab2_orga;

public class Buffer_EX_MEM {
	public Linea_de_control RegWrite = null;
	public Linea_de_control MemtoReg = null;
	public Linea_de_control Branch = null;
	public Linea_de_control MemRead = null;
	public Linea_de_control MemWrite = null;
	public Integer ResultAdd = null;
	public Integer Zero = null;
	public Integer ALUOut = null;
	public Integer SalidaForwardB = null;
	public Integer SalidaMUX_ALUSrc = null;
	public String SalidaMUX_RegDst =null;
	public Instruction inst=null;
	public Buffer_EX_MEM(){
		
	}
}
