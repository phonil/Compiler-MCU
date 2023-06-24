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

	public Loader() { // variable인지 뭔지를 적어놔 / 뒤에서 count가 있을거야 그러면 번지수로 바꿔야지 /
		// variable이 count, sum, i 가 있는데 이걸 0, 4, 8로 바꿔야해
		symbolTable = new HashMap<String, SymbolEntity>();
		// 이제 add 시킬거야.. parse Header의 else에서..?
	}

	public void load() {
		Scanner scanner = new Scanner("code/exe2");// code 앞 / ?
		parseHeader(scanner); // 첨엔 헤더 읽어야지
		parseCode(scanner); // 그 다음 코드 부분 읽어야지
		scanner.close();
	}

	private void parseHeader(Scanner scanner) {
		int sizeDS, sizeHS, sizeSS;

		String line = scanner.nextLine();
		String[] tokens = getTokens(line);
		if (tokens[0].charAt(0) == '$') { // 달러가 아니면? --> variable
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
		String[] tokens = line.split("[ \t]*"); // space, tab, carrige return(\r), line feed(\n) / * : 몇 개기 나오든..!
		return tokens;
	}

	private void parseCode(Scanner scanner) {

	}
}
