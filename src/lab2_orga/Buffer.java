package lab2_orga;

import java.util.ArrayList;
import java.util.Hashtable;

public class Buffer {
	public String nombre;
	private Hashtable<String, Integer> control;
	private Hashtable<String, Integer> datos;
	
	public Buffer(ArrayList<String> nombres_lineas_control, ArrayList<String> nombres_lineas_dato){
		for (String string : nombres_lineas_control) {
			control.put(string, 0);			
		}
		for (String string : nombres_lineas_dato) {
			datos.put(string, 0);
		}
	}
	
	public void setDato(String nombre, Integer dato){
		this.datos.put(nombre,dato);
	}
	
	public void setControl(String nombre, Integer dato){
		this.control.put(nombre, dato);
	}
	
	public Integer getDato(String nombre){
		return this.datos.get(nombre);
	}
	
	public Integer getControl(String nombre){
		return this.control.get(nombre);
	}
	
}
