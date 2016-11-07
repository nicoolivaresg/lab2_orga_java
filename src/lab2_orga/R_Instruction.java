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
		return Rd;
	}

	@Override
	public Integer getImm() {
		// TODO Auto-generated method stub
		return null;
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
		Rd = rd;
	}

	@Override
	public void setImm(Integer imm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAddress(Integer addr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDir() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
