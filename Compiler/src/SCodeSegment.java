import java.util.Vector;

public class SCodeSegment implements INode {
	private SSymbolTable labels;

	public Vector<SStatement> statements; // Parser Tree

	public SCodeSegment(SSymbolTable symbolTable) {
		this.statements = new Vector<SStatement>();
		this.labels = symbolTable;
	}

	@Override
	public String parse(SLex lex) {
		String[] tokens = lex.getTokens();
		String operator = tokens[0];

		while (operator.compareTo(".stack") != 0) {
			if (((operator.startsWith("//")) || (operator.length() == 0))) { // 주석 처리

			} else if (operator.contains(":")) {
				// symbol table / label
				SSymbolEntity entity = new SSymbolEntity();

				entity.setVariableName(operator.replace(":", ""));
				entity.setValue(this.statements.size());
				entity.setType("label");
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