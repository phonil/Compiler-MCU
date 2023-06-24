package hw;

import java.util.Scanner;

public class CPU {
	public enum EState {
		eStopped, eRunning
	}

	public enum EOperator {
		eHalt, eLoada, eLoadc, eStorea, eAdda, eAddc, eDivc, eShr, eJump, ePush, ePop, eLoads, eStores, eJumps
	}

	private Memory memory;

	public void associate(Memory memory) {
		this.memory = memory;
	}

	private EState eState;

	// registers

	public IR ir;
	public Register mar, mbr;
	public Register cs, ss, pc; // Code Segment Register, program counter
	public Register ac;

	public CPU() {
		ir = new IR();
		mar = new Register();
		mbr = new Register();
		cs = new Register();
		ss = new Register();
		ss.setAddress(29);
		pc = new Register();
		ac = new Register();

	}

	private void fetch() {
		System.out.println("fetch");
		mar.setAddress(pc.getAddress());

		memory.load();

		ir.setValue1(mbr.getValue1());
		if (mbr.getValue2() != -1)
			ir.setValue2(mbr.getValue2());
		else
			ir.setValue2(0);

	}

	private void decode() {
		System.out.println("decode");

		// Opertor
		switch (ir.getValue1()) {
		case "0x00":
			ir.setOperator(EOperator.eHalt);
			break;
		case "0x01":
			ir.setOperator(EOperator.eLoada);
			break;
		case "0x02":
			ir.setOperator(EOperator.eLoadc);
			break;
		case "0x03":
			ir.setOperator(EOperator.eStorea);
			break;
		case "0x04":
			ir.setOperator(EOperator.eAdda);
			break;
		case "0x05":
			ir.setOperator(EOperator.eAddc);
			break;
		case "0x06":
			ir.setOperator(EOperator.eDivc);
			break;
		case "0x07":
			ir.setOperator(EOperator.eShr);
			break;
		case "0x08":
			ir.setOperator(EOperator.eJump);
			break;
		case "0x09":
			ir.setOperator(EOperator.ePush);
			break;
		case "0x0a":
			ir.setOperator(EOperator.ePop);
			break;
		case "0x0b":
			ir.setOperator(EOperator.eLoads);
			break;
		case "0x0c":
			ir.setOperator(EOperator.eStores);
			break;
		case "0x0d":
			ir.setOperator(EOperator.eJumps);
			break;
		default:
			break;
		}

		if (ir.getValue2() != -1) {
			// Operand
			ir.setOperand(ir.getValue2());
		}

	}

	private void execute() {
		pc.setAddress(pc.getAddress() + 1);
		System.out.println("excute");

		switch (ir.getOperator()) {
		case eHalt:
			this.halt();
			break;
		case eLoada:
			this.loada(ir.getValue2());
			break;
		case eLoadc:
			this.loadc(ir.getValue2());
			break;
		case eStorea:
			this.storea(ir.getValue2());
			break;
		case eAdda:
			this.adda();
			break;
		case eAddc:
			this.addc(ir.getValue2());
			break;
		case eDivc:
			this.divc(ir.getValue2());
			break;
		case eShr:
			this.shr();
			break;
		case eJump:
			this.jump(ir.getValue2());
			break;
		case ePush:
			this.push();
			break;
		case ePop:
			this.pop();
			break;
		case eLoads:
			this.loads(ir.getValue2());
			break;
		case eStores:
			this.stores(ir.getValue2());
			break;
		case eJumps:
			this.jumps(ir.getValue2());
			break;
		default:
			break;
		}

	}

	// operator method

	public void halt() {
		this.eState = EState.eStopped;
	}

	public void loada(int address) {
		this.ac.setValue(address);
	}

	public void loadc(int operand) {
		this.ac.setValue(operand);
	}

	public void storea(int operand) {
		for (int i = 0; i < this.memory.dataSegment.size(); i++) {
			if (this.memory.dataSegment.get(i)[1].equals(Integer.toString(operand))) {
				this.memory.dataSegment.get(i)[2] = Integer.toString(ac.getValue());
				break;
			}
		}
	}

	public void adda() { //

	}

	public void addc(int value) {
		this.ac.setValue(this.ac.getValue() + value);
	}

	public void divc(int divNum) {
		this.ac.setValue(this.ac.getValue() / divNum);
	}

	public void shr() { //

	}

	public void jump(int line) {
		this.pc.setAddress(line);
	}

	public void push() { //

	}

	public void pop() { //

	}

	public void loads(int sp) {
		ac.setValue(this.memory.stackSegment.get(sp / 4));
	}

	public void stores(int sp) {
		this.memory.stackSegment.set(sp / 4, ac.getValue());
	}

	public void jumps(int sp) {
		this.pc.setAddress(this.memory.stackSegment.get(sp / 4));
	}

	public void start() {
		this.eState = EState.eRunning;
		this.run();
	}

	public void stop() {
		this.eState = EState.eStopped;
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		while (this.eState == EState.eRunning) {
			System.out.println("-----------------\npc:" + pc.getAddress());
			this.fetch();
			this.decode();
			this.execute();
			System.out.println("mar:" + mar.getAddress());
			System.out.println("mbr:" + mbr.getValue2());
			System.out.println("ac:" + ac.getValue());
			System.out.println("ir:" + ir.getValue2());
		}

		System.out.println("-----------------");
		System.out.println("sum: " + this.memory.dataSegment.get(2)[2]);
		System.out.println("avg: " + this.memory.dataSegment.get(3)[2]);

	}

}
