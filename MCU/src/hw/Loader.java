package hw;
import java.util.HashMap;
import java.util.Scanner;

public class Loader {

	public enum ESymbolType {
		eVariable, eLabel, eRegister
	}

	class SymbolEntity {
		public ESymbolType eSymbolType;
		public int value;

		public SymbolEntity(ESymbolType eSymbolType, int value) {
			this.eSymbolType = eSymbolType;
			this.value = value;
		}
	}

	HashMap<String, SymbolEntity> symbolTable;

	public Loader() { // variable���� ������ ����� / �ڿ��� count�� �����ž� �׷��� �������� �ٲ���� /
		// variable�� count, sum, i �� �ִµ� �̰� 0, 4, 8�� �ٲ����
		symbolTable = new HashMap<String, SymbolEntity>();
		// ���� add ��ų�ž�.. parse Header�� else����..?
	}

	public void load() {
		Scanner scanner = new Scanner("code/exe2");// code �� / ?
		parseHeader(scanner); // ÷�� ��� �о����
		parseCode(scanner); // �� ���� �ڵ� �κ� �о����
		scanner.close();
	}

	private void parseHeader(Scanner scanner) {
		int sizeDS, sizeHS, sizeSS;

		String line = scanner.nextLine();
		String[] tokens = getTokens(line);
		if (tokens[0].charAt(0) == '$') { // �޷��� �ƴϸ�? --> variable
			if (tokens[0].charAt(1) == 'D') {
				sizeDS = Integer.parseInt(tokens[1]);
			} else if (tokens[0].charAt(1) == 'S') {
				sizeHS = Integer.parseInt(tokens[1]);
			} else if (tokens[0].charAt(1) == 'H') {
				sizeSS = Integer.parseInt(tokens[1]);
			}

		} else {
			this.symbolTable.put(tokens[0], new SymbolEntity(ESymbolType.eVariable, Integer.parseInt(tokens[1]))); // variable
																													// name
																													// /
		}

	}

	private String[] getTokens(String line) {
		String[] tokens = line.split("[ \t]*"); // space, tab, carrige return(\r), line feed(\n) / * : �� ���� ������..!
		return tokens;
	}

	private void parseCode(Scanner scanner) {

	}
}
