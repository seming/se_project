import java.util.Scanner;

public abstract class DailyManageOutline {
	public abstract void add();
	public abstract void delete();
	public abstract void viewAllList();
	public boolean alarmWhenDelete(){
		System.out.println("정말 삭제하시겠습니까?\n 1. 확인 \n 2. 취소");
		return confirm();
	}
	
	public boolean alarmWhenCancel(){
		System.out.println("취소하시겠습니까?\n 1. 확인 \n 2. 취소");
		return confirm();
	}
	
	public void setPopUpWindow(String confirmMessage){
		System.out.println(confirmMessage);
	}
	
	public boolean confirm(){
		Scanner scan = new Scanner(System.in);
		int userAnswer = scan.nextInt();
		while(!isCorrectFormOfAnswer(userAnswer)) {
			System.out.print("잘못 입력했습니다. 다시 입력 해 주십시오\n 1. 확인 \n 2. 취소");
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
