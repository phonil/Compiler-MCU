package hw;

import hw.CPU.EOperator;

public class IR extends Register {
	private EOperator operator;
	private int operand;

	public EOperator getOperator() {
		return this.operator;
	}

	public void setOperator(EOperator operator) {
		this.operator = operator;
	}

	public int getOperand() {
		return operand;
	}

	public void setOperand(int operand) {
		this.operand = operand;
	}

}