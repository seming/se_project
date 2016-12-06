import java.util.Scanner;

public abstract class DailyManageOutline {
	public abstract void add();
	public abstract void delete();
	public abstract void viewAllList();
	public boolean alarmWhenDelete(){
		System.out.println("정말 삭제하시겠습니까?\n 1. 확인 \n 2. 취소\n");
		return confirm();
	}
	public boolean alarmWhenCancel(){
		System.out.println("취소하시겠습니까?\n 1. 확인 \n 2. 취소\n");
		return confirm();
	}
	public void setPopUpWindow(String confirmMessage){
		System.out.println(confirmMessage);
	}
	
	public boolean confirm(){
		Scanner scan = new Scanner(System.in);
		if(scan.nextInt()==1){
			return true;
		}
		else{
			return false;
		}
	}
}
