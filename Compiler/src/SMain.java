import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SMain {
	private SLex lex;
	private SParser parser;
	private SCodeGenerator codeGenerator;

	public SMain() {

	}

	public void initialize() {
		lex = new SLex();
		lex.initialize("source/기말 예제 AssemCode2");
		parser = new SParser();
		codeGenerator = new SCodeGenerator(parser);
	}

	public void finalize() {
		lex.finalize();
	}

	public void run() {
		parser.parse(this.lex);

		String symbolTableFile = "executable/SymbolTable00";
		String statementFile = "executable/Statements00";
		File file = new File(symbolTableFile);
		try {
			FileWriter fw = new FileWriter(file);
			for (int i = 0; i < parser.program.symbolTable.size(); i++) {
				fw.write(parser.program.symbolTable.get(i).getName() + " ");
				fw.write(parser.program.symbolTable.get(i).getValue() + " ");
				fw.write(parser.program.symbolTable.get(i).getType() + "\n");

			}
			fw.close();

			file = new File(statementFile);
			fw = new FileWriter(file);

			for (int i = 0; i < parser.program.codeSegment.statements.size(); i++) {
				if (parser.program.codeSegment.statements.get(i).getOperand1() != null) {
					if (parser.program.codeSegment.statements.get(i).getOperand2() != null) {
						// operator, operand1, 2
						fw.write(parser.program.codeSegment.statements.get(i).getOperator() + " ");
						fw.write(parser.program.codeSegment.statements.get(i).getOperand1() + " ");
						fw.write(parser.program.codeSegment.statements.get(i).getOperand2() + "\n");

					} else {
						// operator, operand1
						fw.write(parser.program.codeSegment.statements.get(i).getOperator() + " ");
						fw.write(parser.program.codeSegment.statements.get(i).getOperand1() + "\n");

					}
				} else {
					// operator
					fw.write(parser.program.codeSegment.statements.get(i).getOperator() + "\n");
				}
			}

			for (int i = 0; i < parser.program.stackSegment.statements.size(); i++) {
				if (parser.program.stackSegment.statements.get(i).getOperand1() != null) {
					fw.write(parser.program.stackSegment.statements.get(i).getOperator() + " ");
					fw.write(parser.program.stackSegment.statements.get(i).getOperand1() + "\n");
				} else {
					fw.write(parser.program.stackSegment.statements.get(i).getOperator() + "\n");
				}
			}

			fw.close();

			codeGenerator.generate(symbolTableFile, statementFile);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		SMain main = new SMain(); // 만들고
		main.initialize(); // 초기화
		main.run(); // 실행
		main.finalize(); // 정리
	}

}
