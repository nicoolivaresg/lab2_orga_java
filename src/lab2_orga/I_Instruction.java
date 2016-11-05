package lab2_orga;

public class I_Instruction extends Instruction{
	public String Rs;
	public String Rt;
	public Integer Immediate;
	
	public I_Instruction(String label, String rt, String rs, Integer immediate) {
		super();
		Rs = rs;
		Rt = rt;
		Immediate = immediate;
		Mnemonic = label;
	}
}