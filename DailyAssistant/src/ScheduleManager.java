import java.util.Scanner;

public class ScheduleManager extends DailyManageOutline {
	public String user_id;
	private Schedule[] scheduleData;
	
	public ScheduleManager(String userID) {
		if(scheduleData==null) {
			System.out.println("������ �����ϴ�");
			System.out.println("1. �����߰� \n2. ��������");
			Scanner scan = new Scanner(System.in);
			int userAnswer = scan.nextInt();
			if(userAnswer == 1){
				//add(userID);
			}
		}
		else {
			//viewAllList(userID);
			System.out.println("1. �����߰� \n2. ��������");
		}		
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewAllList() {
		// TODO Auto-generated method stub
		
	}
}
