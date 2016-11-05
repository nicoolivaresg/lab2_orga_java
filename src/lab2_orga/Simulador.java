package lab2_orga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class Simulador {

	public void update_ID_EX(){
		
	}
	public void update_IF_ID(){
		
	}
	public void update_EX_MEM(){
		
	}
	public void update_MEM_WB(){
		
	}
	
	public static ArrayList<Instruction> leer(String ruta) throws IOException{
		ArrayList<Instruction> instructions = new ArrayList<>();
		File file;
		FileReader fileReader;
		BufferedReader bufferedReader;
		file = new File(ruta);
		fileReader = new FileReader(file);
		bufferedReader = new BufferedReader(fileReader);
		String[]  nueva = null;
		Hashtable<String, Integer> labels = new Hashtable<>();
		Integer pos = 0;
		String linea = bufferedReader.readLine();
		while(linea != null){
			if (linea.length()!=0){
				nueva = linea.replaceAll(",", "").replace(' ','@').split("@");
				if(nueva.length ==1){ //LABEL
					labels.put(nueva[0].replace(':', ' ').trim(),pos+1);
				}
				pos+=1;
			}
			linea = bufferedReader.readLine();
		}
		fileReader.close();
		bufferedReader.close();
		bufferedReader= null;
		fileReader = null;
		file = new File(ruta);
		fileReader = new FileReader(file);
		bufferedReader = new BufferedReader(fileReader);
		linea = bufferedReader.readLine();
		nueva = null;
		J_Instruction j = null;
		R_Instruction r = null;
		I_Instruction i = null;
		Integer pc = 0;
		while(linea != null){
			if(linea.length()!=0){
				nueva = linea.replaceAll(",", "").replace(' ','@').split("@");
				if (nueva.length == 2){// # JUMP o mfhi o mflo
					if (nueva[0].compareTo("j")==0){
						Integer valor = labels.get(nueva[1]);
						j= new J_Instruction(nueva[0], valor);
						instructions.add(j);
						pc+=1;
						j= null;
					}
					else if( nueva[0].compareTo("mfhi")==0 || nueva[0].compareTo("mflo") == 0 ){
						r = new R_Instruction(nueva[0], nueva[1], null, null);
						instructions.add(r);
						r=null;
						pc+=1;
					}
				}else if(nueva.length ==3){
					if (nueva[0].compareTo("lw" ) == 0|| nueva[0].compareTo("sw") == 0){
						String[] descomp = nueva[2].replace('(', ' ').replace(')', ' ').split(" ");
						i = new I_Instruction(nueva[0],descomp[1],nueva[1],Integer.valueOf(descomp[0]));
						instructions.add(i);
						i=null;
						pc+=1;
					}else if(nueva[0].compareTo("la") == 0) {
						/*
						if (descomp[0] != 'ARREGLO' == 0):
							nueva.append(labels[descomp[0]])
						else:
							nueva.append('ARREGLO')
							*/
					}
						/*
						
						if len(descomp) == 1: #la
							nueva.append(linea[0])
							nueva.append(linea[1])
							if descomp[0] != 'ARREGLO':
								nueva.append(labels[descomp[0]])
							else:
								nueva.append('ARREGLO')
						else:
							nueva.append(linea[0])
							nueva.append(linea[1])
							nueva.append(descomp[0])
							nueva.append(descomp[1])
					instructions_memory.append(nueva)	
						 */
				}
				
			}
			linea = bufferedReader.readLine();
		}
		fileReader.close();
		bufferedReader.close();
		bufferedReader= null;
		fileReader = null;
		return instructions;
	}
	
	
	public static void main(String[] args) {
		
		Control_Unit  CU = new Control_Unit();
		Registro  R = new Registro();
		ArrayList<Instruction> instrucciones = null;
		if (args.length == 1){
			try{
				instrucciones= leer(args[0]);
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}

}
