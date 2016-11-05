package lab2_orga;
import java.util.ArrayList;

public class Memoria {
	public Integer direccion;
	public ArrayList<Integer> M;
	private static Memoria instance = null;
	protected Memoria() {
		// Exists only to defeat instantiation.
		for (int i = 0; i < 5000; i++) {
			M.add(i, 0);
		}
	}
	public static Memoria getInstance() {
		if(instance == null) {
			instance = new Memoria();
		}
		return instance;
	}
}
