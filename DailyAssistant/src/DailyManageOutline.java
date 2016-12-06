import java.util.Scanner;

public abstract class DailyManageOutline {
	public abstract void add();
	public abstract void delete();
	public abstract void viewAllList();
	public boolean alarmWhenDelete(){
		System.out.println("���� �����Ͻðڽ��ϱ�?\n 1. Ȯ�� \n 2. ���\n");
		return confirm();
	}
	public boolean alarmWhenCancel(){
		System.out.println("����Ͻðڽ��ϱ�?\n 1. Ȯ�� \n 2. ���\n");
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
