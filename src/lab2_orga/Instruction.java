package lab2_orga;

public abstract class Instruction {
	public String Mnemonic;
	
	public abstract String getDir();
	public abstract String getRs();
	public abstract String getRt();
	public abstract String getRd();
	public abstract Integer getImm();
	public abstract Integer getAddress();
	public abstract void setRs(String rs);
	public abstract void setRt(String rt);
	public abstract void setRd(String rd);
	public abstract void setImm(Integer imm);
	public abstract void setAddress(Integer addr);
}
