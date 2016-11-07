package lab2_orga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class Simulador {

	static Integer BRANCH = 0;
	static Integer LIMITE =0;
	static Control_Unit  CU = new Control_Unit();
	static Registro  R = new Registro();
	static Forwarding_Unit FU = new Forwarding_Unit();
	static Memoria M = new Memoria();
	static ALU A = new ALU();
	static ArrayList<Instruction> instruction_memory;
	static Integer PC = 0;
	static Instruction IR = null;
	static Integer FINISH = 0 ;
	static MUX_2 mux_PCSrc = new  MUX_2();
	static MUX_2 mux_ALUSrc = new MUX_2();
	static MUX_2 mux_RegDst = new MUX_2();
	static MUX_2 mux_MemtoReg = new MUX_2();
	static MUX_3 mux_ForwardA = new MUX_3();
	static MUX_3 mux_ForwardB = new MUX_3();
	static Buffer_IF_ID IF_ID = new Buffer_IF_ID();
	static Buffer_ID_EX ID_EX = new Buffer_ID_EX();
	static Buffer_EX_MEM EX_MEM = new Buffer_EX_MEM();
	static Buffer_MEM_WB MEM_WB = new Buffer_MEM_WB();
	
	private static void update_IF_ID(){
		IF_ID.inst = IR;
		IF_ID.PC_4 = PC + 1;
	}
	public static void update_ID_EX(){
		ID_EX.PC_4 = IF_ID.PC_4;
		ID_EX.Branch = CU.Branch;
		ID_EX.ALUSrc = CU.ALUSrc;
		ID_EX.MemRead = CU.MemRead;
		ID_EX.MemWrite = CU.MemWrite;
		ID_EX.MemtoReg = CU.MemtoReg;
		ID_EX.RegDst = CU.RegDst;
		ID_EX.RegWrite = CU.RegWrite;
		ID_EX.inst = IF_ID.inst;
		ID_EX.ReadData1 = R.ReadData1;
		ID_EX.ReadData2 = R.ReadData2;
		ID_EX.Rs = IF_ID.inst.getRs();
		ID_EX.Rt = IF_ID.inst.getRt();
		ID_EX.Rd = IF_ID.inst.getRd();
		if(IF_ID.inst.Mnemonic.compareTo("la") == 0){
			R.put(IF_ID.inst.getRt(), IF_ID.inst.getDir());
		}else{
			ID_EX.SignExtend = IF_ID.inst.getImm();			
		}
	}
	private static void update_EX_MEM(){
		EX_MEM.inst = ID_EX.inst;
		EX_MEM.Branch = ID_EX.Branch;
		EX_MEM.MemRead = ID_EX.MemRead;
		EX_MEM.MemtoReg = ID_EX.MemtoReg;
		EX_MEM.MemWrite = ID_EX.MemWrite;
		EX_MEM.RegWrite = ID_EX.RegWrite;
		if (ID_EX.inst.Mnemonic.compareTo("j")==0 ||ID_EX.inst.Mnemonic.compareTo("beq")==0 ||ID_EX.inst.Mnemonic.compareTo("blt")==0 ||ID_EX.inst.Mnemonic.compareTo("bgt")==0){
			EX_MEM.ResultAdd = ID_EX.PC_4 + ID_EX.SignExtend;
		}else{
			EX_MEM.ResultAdd = null;
		}
		EX_MEM.Zero = A.Zero;
		EX_MEM.ALUOut = A.ALUout;
		EX_MEM.SalidaForwardB = mux_ForwardB.evaluar();
		mux_RegDst.EntradaReg0 = ID_EX.Rt;
		mux_RegDst.EntradaReg1 = ID_EX.Rd;
		if(ID_EX.RegDst.estado()){
			mux_RegDst.Estado = 1;
		}else{
			mux_RegDst.Estado = 0;
		}
		EX_MEM.SalidaMUX_RegDst = mux_RegDst.evaluarStr();
	}
	private static void update_MEM_WB(){
		MEM_WB.inst = EX_MEM.inst;
		MEM_WB.MemtoReg = EX_MEM.MemtoReg;
		MEM_WB.RegWrite = EX_MEM.RegWrite;
		R.RegWrite = MEM_WB.RegWrite;
		if(EX_MEM.inst.Mnemonic.compareTo("lw")==0){
			MEM_WB.ReadDataMemory = M.ReadData;
		}else{
			MEM_WB.ReadDataMemory = null;	
		}
		MEM_WB.ALUOut = EX_MEM.ALUOut;
		MEM_WB.SalidaMUX_RegDst = EX_MEM.SalidaMUX_RegDst;
	}
	private static void updateControlUnit() {
		if(IR!=null){
			if(IR.Mnemonic.compareTo("mflo")==0 || IR.Mnemonic.compareTo("mfhi")==0){
				CU.RegDst.activar();
				CU.MemRead.desactivar();
				CU.MemWrite.desactivar();
				CU.MemtoReg.desactivar();
				CU.ALUSrc.desactivar();
				CU.RegWrite.activar();
				CU.Branch.desactivar();
			}else
				if(IR.Mnemonic.compareTo("add")==0){
				CU.RegDst.activar();
				CU.MemRead.desactivar();
				CU.MemWrite.desactivar();
				CU.MemtoReg.desactivar();
				CU.ALUSrc.desactivar();
				CU.RegWrite.activar();
				CU.Branch.desactivar();
			}else 
				if(IR.Mnemonic.compareTo("addi")==0){
				CU.RegDst.desactivar();
				CU.MemRead.desactivar();
				CU.MemWrite.desactivar();
				CU.MemtoReg.desactivar();
				CU.ALUSrc.activar();
				CU.RegWrite.activar();
				CU.Branch.desactivar();
			}else
				if(IR.Mnemonic.compareTo("div")==0){
					CU.RegDst.activar();
					CU.MemRead.desactivar();
					CU.MemWrite.desactivar();
					CU.MemtoReg.desactivar();
					CU.ALUSrc.desactivar();
					CU.RegWrite.desactivar();
					CU.Branch.desactivar();
			}else
				if(IR.Mnemonic.compareTo("mul")==0){
					CU.RegDst.activar();
					CU.MemRead.desactivar();
					CU.MemWrite.desactivar();
					CU.MemtoReg.desactivar();
					CU.ALUSrc.desactivar();
					CU.RegWrite.desactivar();
					CU.Branch.desactivar();
			}else
				if(IR.Mnemonic.compareTo("la")==0){
					CU.RegDst.desactivar();
					CU.MemRead.desactivar();
					CU.MemWrite.desactivar();
					CU.MemtoReg.desactivar();
					CU.ALUSrc.desactivar();
					CU.RegWrite.desactivar();
					CU.Branch.desactivar();
			}else
				if(IR.Mnemonic.compareTo("beq")==0 || IR.Mnemonic.compareTo("blt")==0 || IR.Mnemonic.compareTo("bgt")==0){
					CU.RegDst.desactivar();
					CU.MemRead.desactivar();
					CU.MemWrite.desactivar();
					CU.MemtoReg.desactivar();
					CU.ALUSrc.desactivar();
					CU.RegWrite.desactivar();
					CU.Branch.activar();
			}else
				if(IR.Mnemonic.compareTo("j")==0){
					CU.RegDst.activar();
					CU.MemRead.desactivar();
					CU.MemWrite.desactivar();
					CU.MemtoReg.desactivar();
					CU.ALUSrc.desactivar();
					CU.RegWrite.desactivar();
					CU.Branch.desactivar();
			}else
				if(IR.Mnemonic.compareTo("lw")==0){
					CU.RegDst.desactivar();
					CU.MemRead.activar();
					CU.MemWrite.desactivar();
					CU.MemtoReg.activar();
					CU.ALUSrc.activar();
					CU.RegWrite.activar();
					CU.Branch.desactivar();
			}else
				if(IR.Mnemonic.compareTo("sw")==0){
					CU.RegDst.desactivar();
					CU.MemRead.desactivar();
					CU.MemWrite.activar();
					CU.MemtoReg.desactivar();
					CU.ALUSrc.activar();
					CU.RegWrite.desactivar();
					CU.Branch.desactivar();
			}
			
		}
	}
	private static void fetchRegister() {
		R.ReadReg1 = IF_ID.inst.getRs();
		R.ReadReg2 = IF_ID.inst.getRt();
		if(IF_ID.inst.Mnemonic.compareTo("la")==0){
			R.ReadData2 = R.registers.get(R.ReadReg2);			
		}else{
			R.ReadData1 = R.registers.get(R.ReadReg1);
			R.ReadData2 = R.registers.get(R.ReadReg2);
		}
	}
	private static void updateFU() {
		FU.ID_EX_Rs = ID_EX.Rs;
		FU.ID_EX_Rt = ID_EX.Rt;
		FU.MEM_WB_RegistroResultado = MEM_WB.SalidaMUX_RegDst;
		FU.EX_MEM_RegistroResultado = EX_MEM.SalidaMUX_RegDst;
		FU.EX_MEM_RegWrite = EX_MEM.RegWrite;
		FU.MEM_WB_RegWrite = MEM_WB.RegWrite;
	}
	private static void updateMUX_Forward() {
		mux_ForwardA.Entrada0 = ID_EX.ReadData1;
		mux_ForwardA.Entrada1 = mux_MemtoReg.evaluar();
		mux_ForwardA.Entrada2 = EX_MEM.ALUOut;
		mux_ForwardB.Entrada0 = ID_EX.ReadData2;
		mux_ForwardB.Entrada1 = mux_MemtoReg.evaluar();
		mux_ForwardB.Entrada2 = EX_MEM.ALUOut;
	}
	private static void updateMUX_ALUSrc() {
		mux_ALUSrc.Entrada0 = mux_ForwardB.evaluar();
		mux_ALUSrc.Entrada1 = ID_EX.SignExtend;
	}
	private static void updateALU() {
		A.operadorA = mux_ForwardA.evaluar();
		A.operadorB = mux_ALUSrc.evaluar();
	}
	private static void checkBranch() {
		if(EX_MEM.Branch.estado() && EX_MEM.Zero == 1){
			BRANCH = 1;
		}
	}
	private static void writeRegister() {
		R.WriteData = mux_MemtoReg.evaluar();
		if(MEM_WB.SalidaMUX_RegDst.compareTo("$zero") == 0){
			R.registers.put(MEM_WB.SalidaMUX_RegDst, R.WriteData);
		}
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
				for (int k = 0; k < nueva.length; k++) {
					System.out.print(nueva[k]+" ");
					if(k == nueva.length -1){
						System.out.print("\n");
					}
				}
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
				}else if (nueva[0].compareTo("lw" ) == 0|| nueva[0].compareTo("sw") == 0){
						String[] descomp = nueva[2].replace('(', ' ').replace(')', ' ').split(" ");
						i = new I_Instruction(nueva[0],descomp[1],nueva[1],Integer.valueOf(descomp[0]));
						instructions.add(i);
						i=null;
						pc+=1;
				}else if(nueva[0].compareTo("la") == 0) {
						//QUE HACER CON EL ARREGLO
					if(nueva[2].compareTo("ARREGLO") == 0){
						i= new I_Instruction(nueva[0], nueva[1], null, nueva[2]);
						instructions.add(i);
						i=null;
						pc+=1;
					}
					
				}else if(nueva[0].compareTo("add") == 0){
					r = new R_Instruction(nueva[0], nueva[1], nueva[2], nueva[3]);
					instructions.add(r);
					r=null;
					pc+=1;
				}else if(nueva[0].compareTo("addi")==0){
					i = new I_Instruction(nueva[0], nueva[1], nueva[2], Integer.valueOf(nueva[3]));
					instructions.add(i);
					i=null;
					pc+=1;
				}else if(nueva[0].compareTo("beq")==0 || nueva[0].compareTo("bgt")==0 || nueva[0].compareTo("blt")==0){
					Integer etiqueta = labels.get(nueva[3]);
					i = new I_Instruction(nueva[0], nueva[1], nueva[2], etiqueta);
					instructions.add(i);
					i=null;
					pc+=1;
				}else if (nueva[0].compareTo("div")==0 ){
					if(nueva.length == 3){
						r = new R_Instruction(nueva[0], nueva[1], nueva[2], null);
						instructions.add(r);
						r=null;
						pc+=1;
					}else if(nueva.length == 4){
						r = new R_Instruction(nueva[0], nueva[1], nueva[2], nueva[3]);
						instructions.add(r);
						r=null;
						pc+=1;
					}
				}else if (nueva[0].compareTo("mul")==0 ){
					if(nueva.length == 3){
						r = new R_Instruction(nueva[0], nueva[1], nueva[2], null);
						instructions.add(r);
						r=null;
						pc+=1;
					}else if(nueva.length == 4){
						r = new R_Instruction(nueva[0], nueva[1], nueva[2], nueva[3]);
						instructions.add(r);
						r=null;
						pc+=1;
					}
				}else if(nueva[0].compareTo("nop") == 0){
					r = new R_Instruction(nueva[0], null, null, null);
					instructions.add(r);
					r=null;
					pc+=1;
				}
			}	
			linea = bufferedReader.readLine();
		}
		LIMITE = pc;
		fileReader.close();
		bufferedReader.close();
		bufferedReader= null;
		fileReader = null;	
		return instructions;
	}
	
	public static void IF() throws Exception{
		if (PC == instruction_memory.size()){
			FINISH = 1;
		}else{
			IR = instruction_memory.get(PC); 
			update_IF_ID();
			mux_PCSrc.Entrada0 = PC+1;
			if(EX_MEM.Branch==null || EX_MEM.Zero == null|| !EX_MEM.Branch.estado() || EX_MEM.Zero == 0 ){
				mux_PCSrc.Entrada1 = PC+1; 
			}else{
				if(EX_MEM.Branch.estado() && EX_MEM.Zero == 1){
					mux_PCSrc.Entrada1 = EX_MEM.ResultAdd;
				}else{
					mux_PCSrc.Entrada1 = PC+1;
				}
			}
			if(PC==0){
				PC = PC+1;
			}else{
				PC = mux_PCSrc.evaluar();				
			}
		}
	}
	public static void ID() throws Exception{
		fetchRegister();
		updateControlUnit();
		update_ID_EX();
		
		
	}
	public static void EX() throws Exception{
		if(ID_EX.PC_4 == 1){
			mux_ForwardA.Entrada0 = ID_EX.ReadData1;
			mux_ForwardB.Entrada0 = ID_EX.ReadData2;
			updateMUX_ALUSrc();
			A.operadorA = mux_ForwardA.evaluar();
			if(ID_EX.ALUSrc.estado()){
				mux_ALUSrc.Estado = 1;
			}else{
				mux_ALUSrc.Estado = 0;
			}
			A.operadorB = mux_ALUSrc.evaluar();
			
		}else{
			updateFU();
			FU.evaluar();
			updateMUX_Forward();
			updateMUX_ALUSrc();
			updateALU();
		}
		A.operar(ID_EX.inst);
		update_EX_MEM();
	}
	public static void MEM(){
		checkBranch();
		if(BRANCH == 0){
			if(EX_MEM.inst.Mnemonic.compareTo("lw")==0){
				M.Address = EX_MEM.ALUOut;
				M.ReadData = M.load();
			}else
				if(EX_MEM.inst.Mnemonic.compareTo("sw")==0){
					M.Address = EX_MEM.ALUOut;
					M.WriteData = EX_MEM.SalidaForwardB;
					M.save();
			}
			update_MEM_WB();
		}else{
			BRANCH = 0;
		}
	}
	public static void WB(){
		if(MEM_WB.MemtoReg.estado()){
			mux_MemtoReg.Estado = 1;
		}else{
			mux_MemtoReg.Estado = 0;
		}
		mux_MemtoReg.Entrada1 = MEM_WB.ReadDataMemory;
		mux_MemtoReg.Entrada0 = MEM_WB.ALUOut;
		R.WriteData = mux_MemtoReg.evaluar();
		R.WriteReg = MEM_WB.SalidaMUX_RegDst;
		if(MEM_WB.inst.Mnemonic.compareTo("la")!=0){
			R.registers.put(R.WriteReg, R.WriteData);			
		}
		if(PC == instruction_memory.size()){
			FINISH = 1;
		}
	}
	
	public static void execute() throws Exception{
		while (PC != instruction_memory.size()) {
			if(FINISH != 1){
				if (PC == 0){
					IF();					
				}else
					if (PC == 1){
						ID();
						IF();
				}else
					if (PC == 2){
						EX();
						ID();
						IF();					
				}else
					if (PC == 3){
						MEM();
						EX();
						ID();
						IF();
				}else
					if(PC == 4){
						WB();
						MEM();
						EX();
						ID();
						IF();
						//
						WB();
						MEM();
						EX();
						ID();
						//
						WB();
						MEM();
						EX();
						//
						WB();
						MEM();
						//
						WB();
				}else{
					execute();
				}
					
			}
		}
	}
	
	public static void main(String[] args) {
		
		if (args.length == 1){
			try{
				instruction_memory= leer(args[0]);
				execute();
				
				
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}

}
