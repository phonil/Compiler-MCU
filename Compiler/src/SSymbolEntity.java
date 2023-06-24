public class SSymbolEntity {
	private String name;
	private int value;

	private String type;

	public String getName() {
		return name;
	}

	public void setVariableName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}