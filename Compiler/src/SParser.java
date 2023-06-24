public class SParser {
	private SLex lex;
	public SProgram program;

	public void parse(SLex lex) {
		program = new SProgram();
		program.parse(lex);

	}

}
