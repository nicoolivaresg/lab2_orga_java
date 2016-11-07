package lab2_orga;

public class J_Instruction extends Instruction{
	public Integer Address;

	public J_Instruction(String mnemonic,Integer address) {
		super();
		Address = address;
		Mnemonic = mnemonic;
	}

	@Override
	public String getRs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getImm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAddress() {
		// TODO Auto-generated method stub
		return Address;
	}

	@Override
	public void setRs(String rs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRt(String rt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRd(String rd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setImm(Integer imm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAddress(Integer addr) {
		// TODO Auto-generated method stub
		Address = addr;
	}

	@Override
	public String getDir() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
