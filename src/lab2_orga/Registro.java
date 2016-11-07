package lab2_orga;

import java.util.Hashtable;

public class Registro {
	public Hashtable<String, Integer> registers = new Hashtable<>();
	public String ReadReg1=null;
	public String ReadReg2=null;
	public Integer ReadData1=null;
	public Integer ReadData2=null;
	public Integer WriteData=null;
	public String WriteReg =null;
	public boolean guardadoARREGLO =false;
	public String registroARREGLO;
	public String valorARREGLO;
	public Linea_de_control RegWrite= null;
	private static Registro instance = null;
	protected Registro() {
		// Exists only to defeat instantiation.
		registers.put("$zero", 0);
		registers.put("$v0", 0);
		registers.put("$v1", 0);
		registers.put("$a0", 0);
		registers.put("$a1", 0);
		registers.put("$a2", 0);
		registers.put("$a3", 0);
		registers.put("$t0", 0);
		registers.put("$t1", 0);
		registers.put("$t2", 0);
		registers.put("$t3", 0);
		registers.put("$t4", 0);
		registers.put("$t5", 0);
		registers.put("$t6", 0);
		registers.put("$t7", 0);
		registers.put("$t8", 0);
		registers.put("$t9", 0);
		registers.put("lo", 0);
		registers.put("hi", 0);
	}
	public static Registro getInstance() {
		if(instance == null) {
			instance = new Registro();
		}
		return instance;
	}
	
	public void put(String key, String value){
		if(!guardadoARREGLO){
			registroARREGLO = key;
			valorARREGLO = value;
			guardadoARREGLO = true;
		}		
	}
		
}
