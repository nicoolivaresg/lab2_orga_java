package lab2_orga;

public class R_Instruction extends Instruction{
	public String Rs;
	public String Rt;
	public String Rd;
	
	public R_Instruction(String label,String rd, String rs, String rt) {
		super();
		Rs = rs;
		Rt = rt;
		Rd = rd;
		Mnemonic = label;
	}
	
}
