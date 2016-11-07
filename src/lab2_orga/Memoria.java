package lab2_orga;
import java.util.ArrayList;

public class Memoria {
	public String Direccion;
	public Integer Address;
	public Integer ReadData;
	public Integer WriteData;
	public ArrayList<Integer> M;
	private static Memoria instance = null;
	protected Memoria() {
		// Exists only to defeat instantiation.
	}
	public static Memoria getInstance() {
		if(instance == null) {
			instance = new Memoria();
		}
		return instance;
	}
	
	public Integer load(){
		return M.get(Address);
	}
	public void save(){
		M.add(Address,WriteData);
	}
}
