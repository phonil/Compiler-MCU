package hw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Memory {
	private Vector<String[]> memory;

	// code / stack Segment ºÐ¸®
	public Vector<String[]> codeSegment;
	public Vector<Integer> stackSegment;
	public Vector<String[]> dataSegment;

	public Vector<String[]> function;

	private Register MAR;
	private Register MBR;
	private Scanner scanner;

	public Memory() {
		this.memory = new Vector<String[]>();
		this.codeSegment = new Vector<String[]>();
		this.stackSegment = new Vector<Integer>();
		this.function = new Vector<String[]>();
		this.dataSegment = new Vector<String[]>();

		String objectCodeFile = "executable/Object Code00";

		File file = new File(objectCodeFile);

		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] lineArr = line.split(" ");
			this.memory.add(lineArr);
		}
		scanner.close();

		for (int i = 0; i < 29; i++) { // code segment
			this.codeSegment.add(this.memory.get(i));
		}
		for (int i = 29; i < this.memory.size(); i++) { // stack segment
			this.function.add(this.memory.get(i));
		}

		String symbolTableFile = "executable/SymbolTable00";

		file = new File(symbolTableFile);

		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int i = 0;
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] lineArr = line.split(" ");
			this.dataSegment.add(lineArr);
			this.dataSegment.get(i)[2] = "0";
			i++;
		}

		scanner.close();

		for (int j = 0; j < 7; j++) {
			this.stackSegment.add(0);
		}

	}

	public void associate(Register MAR, Register MBR) {
		this.MAR = MAR;
		this.MBR = MBR;
	}

	public void load() {
		int address = MAR.getAddress();
		MBR.setValue1(this.memory.get(address)[0]);
		if (this.memory.get(address).length == 2)
			MBR.setValue2(Integer.parseInt(this.memory.get(address)[1]));
	}

}
