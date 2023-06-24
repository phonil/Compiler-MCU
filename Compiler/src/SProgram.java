public class SProgram implements INode {
	public SSymbolTable symbolTable;

	private SHeader header;
	public SStackSegment stackSegment;
	public SCodeSegment codeSegment;

	public SProgram() {
		this.symbolTable = new SSymbolTable();
		this.header = new SHeader(symbolTable);
		this.codeSegment = new SCodeSegment(symbolTable);
		this.stackSegment = new SStackSegment(symbolTable, codeSegment);
	}

	@Override
	public String parse(SLex lex) {
		String token = lex.getToken();

		if (token.compareTo(".header") == 0) {
			token = this.header.parse(lex);
		}
		if (token.compareTo(".code") == 0) {
			token = this.codeSegment.parse(lex);
		}
		if (token.compareTo(".stack") == 0) {
			token = this.stackSegment.parse(lex);
		}

		return null;
	}
}