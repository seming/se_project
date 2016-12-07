import java.util.Scanner;

public class ScheduleManager extends DailyManageOutline {
	public String user_id;
	private Schedule[] scheduleData;
	
	public ScheduleManager(String userID) {
		if(scheduleData==null) {
			System.out.println("일정이 없습니다");
			System.out.println("1. 일정추가 \n2. 일정삭제 \n3. 뒤로가기");
			innerMenu();
		}
		else {
			viewAllList();
			System.out.println("1. 일정추가 \n2. 일정삭제 \n3. 뒤로가기");
			innerMenu();			
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
		for (int i=0; i<scheduleData.length; i++) {
			System.out.println(scheduleData[i].getYear()+"/");
			System.out.println(scheduleData[i].getMonth()+"/");
			System.out.println(scheduleData[i].getDay()+" : ");
			System.out.println(scheduleData[i].getContents()+"\n");
		}
	}
	
	public void innerMenu() {
		Scanner scan = new Scanner(System.in);
		int userAnswer = scan.nextInt();
		while(!isCorrectFormOfAnswer(userAnswer)) {
			System.out.print("잘못 입력했습니다. 다시 입력 해 주십시오\n 1. 일정추가 \n 2. 일정삭제 \n3. 뒤로가기");
			userAnswer = scan.nextInt();
		}
		if(userAnswer == 1)
			add();
		else if(userAnswer == 2)
			delete();
		else 
			Menu.main(null);
	}
	
	public boolean isCorrectFormOfAnswer(int userAnswer) {
		if(userAnswer == 1 || userAnswer == 2 || userAnswer == 3)
			return true;
		else
			return false;
	}
}
