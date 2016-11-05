package lab2_orga;

public class Control_Unit {
	static Linea_de_control RegDst = new Linea_de_control();
	static Linea_de_control RegWrite = new Linea_de_control();
	static Linea_de_control Branch = new Linea_de_control();
	static Linea_de_control MemRead = new Linea_de_control();
	static Linea_de_control MemWrite = new Linea_de_control();
	static Linea_de_control MemtoReg = new Linea_de_control();
	static Linea_de_control ALUSrc = new Linea_de_control();
	
	private static Control_Unit instance = null;
	protected Control_Unit() {
		// Exists only to defeat instantiation.
	}
	public static Control_Unit getInstance() {
		if(instance == null) {
			instance = new Control_Unit();
		}
		return instance;
	}
}
