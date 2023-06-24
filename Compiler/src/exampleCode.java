import java.util.Scanner;

public class exampleCode { // 기말고사 예제 high-level code

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		int stuNum = sc.nextInt();
		Student student = new Student();
		int sum = student.sum(60, 70);
		int avg = student.avg(60, 70);

//		System.out.println()
//		--> 함수에서 출력할지 메인에서 출력할 지 생각

	}

}

class Student {
	private int kor;
	private int eng;

	public int sum(int kor, int eng) {
		int sum;
		this.kor = kor;
		this.eng = eng;
		sum = this.kor + this.eng;

		return sum;
	}

	public int avg(int kor, int eng) {
		int avg;
		this.kor = kor;
		this.eng = eng;
		avg = (this.kor + this.eng) / 2;

		return avg;
	}
}