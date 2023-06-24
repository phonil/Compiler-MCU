import java.util.Vector;

public class SStackSegment implements INode {
	private SSymbolTable labels;

	Vector<SStatement> statements; // Parser Tree

	private SCodeSegment codeSegment;

	public SStackSegment(SSymbolTable symbolTable, SCodeSegment codeSegment) {
		this.statements = new Vector<SStatement>();
		this.labels = symbolTable;
		this.codeSegment = codeSegment;
	}

	@Override
	public String parse(SLex lex) {
		String[] tokens = lex.getTokens();
		String operator = tokens[0];

		while (operator.compareTo(".end") != 0) {
			if (((operator.startsWith("//")) || (operator.length() == 0))) { // 주석 처리

			} else if (operator.contains(":")) {
				// symbol table / label
				SSymbolEntity entity = new SSymbolEntity();

				entity.setVariableName(operator.replace(":", ""));
				entity.setValue(this.codeSegment.statements.size() + this.statements.size());
				entity.setType("function-label");
				this.labels.add(entity);

			} else { // instruction ==> operand 1개짜리 이므로
				// parse tree
				SStatement statement = null;
//				System.out.println(tokens.length);
				switch (tokens.length) {
				case 1:
					statement = new SStatement(tokens[0]);
					break;
				case 2:
					statement = new SStatement(tokens[0], tokens[1]);
					break;
				default:
					break;
				}
				this.statements.add(statement);
			}
			tokens = lex.getTokens();
			operator = tokens[0];
		}
		return operator;
	}

}