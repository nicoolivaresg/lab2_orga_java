package lab2_orga;

import java.util.Hashtable;

public class Registro {
	public Hashtable<String, Integer> registers;
	private String RegRead1;
	private String RegRead2;
	private Integer RegData1;
	private Integer RegData2;
	private Integer WriteData;
	private String WriteReg;
	private static Registro instance = null;
	protected Registro() {
		// Exists only to defeat instantiation.
		this.RegRead1 = null;
		this.RegRead2 = null;
		this.RegData1 = 0;
		this.RegData2 = 0;
		this.WriteData = 0;
		this.WriteReg = null;
	}
	public static Registro getInstance() {
		if(instance == null) {
			instance = new Registro();
		}
		return instance;
	}
	
	public String getRegRead1() {
		return RegRead1;
	}
	public void setRegRead1(String regRead1) {
		RegRead1 = regRead1;
	}
	public String getRegRead2() {
		return RegRead2;
	}
	public void setRegRead2(String regRead2) {
		RegRead2 = regRead2;
	}
	public Integer getRegData1() {
		return RegData1;
	}
	public void setRegData1(Integer regData1) {
		RegData1 = regData1;
	}
	public Integer getRegData2() {
		return RegData2;
	}
	public void setRegData2(Integer regData2) {
		RegData2 = regData2;
	}
	public Integer getWriteData() {
		return WriteData;
	}
	public void setWriteData(Integer writeData) {
		WriteData = writeData;
	}
	public String getWriteReg() {
		return WriteReg;
	}
	public void setWriteReg(String writeReg) {
		WriteReg = writeReg;
	}
	
	public Integer getRegister(String nombre_registro){
		return registers.get(nombre_registro);
	}
	
	public void setRegister(String nombre_registro, Integer nuevo_valor){
		registers.put(nombre_registro, nuevo_valor);
	}
	
	
}
