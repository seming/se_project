import java.util.Scanner;

public class ScheduleManager extends DailyManageOutline {
	public String user_id;
	private Schedule[] scheduleData;
	
	public ScheduleManager(String userID) {
		if(scheduleData==null) {
			System.out.println("������ �����ϴ�");
			System.out.println("1. �����߰� \n2. �������� \n3. �ڷΰ���");
			innerMenu();
		}
		else {
			viewAllList();
			System.out.println("1. �����߰� \n2. �������� \n3. �ڷΰ���");
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
			System.out.print("�߸� �Է��߽��ϴ�. �ٽ� �Է� �� �ֽʽÿ�\n 1. �����߰� \n 2. �������� \n3. �ڷΰ���");
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
