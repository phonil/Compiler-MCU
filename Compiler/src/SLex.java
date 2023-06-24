import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SLex {
	private Scanner scanner;

	public SLex() {

	}

	public void initialize(String fileName) {
		try {
			scanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void finalize() {
		scanner.close();
	}

	public String getToken() {
		if (scanner.hasNext()) {
			String token = scanner.next();
			System.out.println(token);
			return token;
		}
		return null;
	}

	public String[] getTokens() {
		if (scanner.hasNext()) {
			String line = scanner.nextLine();
			line = line.trim();
			String[] tokens = line.split("[ \t]+");

			for (int i = 0; i < tokens.length; i++) {
				if (tokens[i] != "")
					System.out.print(tokens[i] + "\n");
			}
//			if (tokens[0] != "")
//				System.out.println();

			return tokens;
		}
		return null;
	}

}
