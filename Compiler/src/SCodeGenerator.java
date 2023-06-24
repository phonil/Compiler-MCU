import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class SCodeGenerator {
	private SParser parser;
	private Scanner scanner;
	private Vector<String[]> instuctions;

	public SCodeGenerator(SParser parser) {
		this.parser = parser;
		instuctions = new Vector<String[]>();
	}

	public void generate(String symbolTableFile, String statementFile) {
		try {
			scanner = new Scanner(new File(statementFile));

			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				line = line.trim();
				String[] instruction = line.split("[ \t]+");
				String operator = instruction[0];

				switch (operator) {
				case "halt":
					instruction[0] = "0x00";
					break;

				case "loada":
					instruction[0] = "0x01";
					break;

				case "loadc":
					instruction[0] = "0x02";
					break;

				case "storea":
					instruction[0] = "0x03";
					break;

				case "adda":
					instruction[0] = "0x04";
					break;

				case "addc":
					instruction[0] = "0x05";
					break;

				case "divc":
					instruction[0] = "0x06";
					break;

				case "shr":
					instruction[0] = "0x07";
					break;

				case "jump":
					instruction[0] = "0x08";
					break;

				case "push":
					instruction[0] = "0x09";
					break;

				case "pop":
					instruction[0] = "0x0a";
					break;

				case "loads":
					instruction[0] = "0x0b";
					break;

				case "stores":
					instruction[0] = "0x0c";
					break;

				case "jumps":
					instruction[0] = "0x0d";
					break;

				}
				if (instruction.length == 2) {
					if (instruction[1].startsWith("#")) {
						instruction[1] = instruction[1].replace("#", "");
						if (instruction[1].startsWith("6")) {
//							instruction[1] = "3C";
						} else if (instruction[1].startsWith("7")) {
//							instruction[1] = "46";
						}
					} else if (instruction[1].startsWith("[")) {
						instruction[1] = instruction[1].replace("[", "");
						instruction[1] = instruction[1].replace("]", "");
					} else if (instruction[1].contains("()sum")) {
						instruction[1] = "29";
					} else if (instruction[1].contains("()avg")) {
						instruction[1] = "39";
					} else if (instruction[1].startsWith("@")) {
						instruction[1] = instruction[1].replace("@stuNum", "0");
						instruction[1] = instruction[1].replace("@sum", "8");
						instruction[1] = instruction[1].replace("@avg", "12");

					}

				}

				// object code file »ý¼º
				File file = new File("executable/Object Code00");
				try {
					FileWriter fw = new FileWriter(file, true);
					for (int i = 0; i < instruction.length; i++) {
						fw.write(instruction[i] + " ");

					}
					fw.write("\n");
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
