package lab2_orga;

public class I_Instruction extends Instruction{
	public String Rs;
	public String Rt;
	public Integer Immediate;
	public String Dir;
	
	public I_Instruction(String label, String rt, String rs, Integer immediate) {
		super();
		Rs = rs;
		Rt = rt;
		Immediate = immediate;
		Mnemonic = label;
	}

	public I_Instruction(String label, String rt2, String rs2, String string) {
		// TODO Auto-generated constructor stub
		Mnemonic = label;
		Rs = rs2;
		Rt = rt2;
		Dir = string;
	}
	@Override
	public String getDir(){
		return Dir;
	}
	
	@Override
	public String getRs() {
		// TODO Auto-generated method stub
		return Rs;
	}

	@Override
	public String getRt() {
		// TODO Auto-generated method stub
		return Rt;
	}

	@Override
	public String getRd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getImm() {
		// TODO Auto-generated method stub
		return Immediate;
	}

	@Override
	public Integer getAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRs(String rs) {
		// TODO Auto-generated method stub
		Rs = rs;
	}

	@Override
	public void setRt(String rt) {
		// TODO Auto-generated method stub
		Rt = rt;
	}

	@Override
	public void setRd(String rd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setImm(Integer imm) {
		// TODO Auto-generated method stub
		Immediate = imm;
	}

	@Override
	public void setAddress(Integer addr) {
		// TODO Auto-generated method stub
		
	}
	
}