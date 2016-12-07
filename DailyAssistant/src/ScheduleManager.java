import java.util.Scanner;

public class ScheduleManager extends DailyManageOutline {
	public String user_id;
	private Schedule[] scheduleData;
	
	public ScheduleManager(String userID) {
		if(scheduleData==null) {
			System.out.println("일정이 없습니다");
			System.out.println("1. 일정추가 \n2. 일정삭제");
			Scanner scan = new Scanner(System.in);
			int userAnswer = scan.nextInt();
			if(userAnswer == 1){
				//add(userID);
			}
		}
		else {
			//viewAllList(userID);
			System.out.println("1. 일정추가 \n2. 일정삭제");
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
