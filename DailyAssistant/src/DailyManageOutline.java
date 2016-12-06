import java.util.Scanner;

public abstract class DailyManageOutline {
	public abstract void add();
	public abstract void delete();
	public abstract void viewAllList();
	public boolean alarmWhenDelete(){
		System.out.println("���� �����Ͻðڽ��ϱ�?\n 1. Ȯ�� \n 2. ���");
		return confirm();
	}
	
	public boolean alarmWhenCancel(){
		System.out.println("����Ͻðڽ��ϱ�?\n 1. Ȯ�� \n 2. ���");
		return confirm();
	}
	
	public void setPopUpWindow(String confirmMessage){
		System.out.println(confirmMessage);
	}
	
	public boolean confirm(){
		Scanner scan = new Scanner(System.in);
		int userAnswer = scan.nextInt();
		while(!isCorrectFormOfAnswer(userAnswer)) {
			System.out.print("�߸� �Է��߽��ϴ�. �ٽ� �Է� �� �ֽʽÿ�\n 1. Ȯ�� \n 2. ���");
			userAnswer = scan.nextInt();
		}
		if(userAnswer == 1)
			return true;
		else
			return false;
	}
	
	public boolean isCorrectFormOfAnswer(int userAnswer) {
		if(userAnswer == 1 || userAnswer == 2)
			return true;
		else
			return false;
	}
}
