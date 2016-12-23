import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

public class ScheduleManager extends DailyManageOutline {
	public String user_id;
	private Vector<Schedule> scheduleData;
	
	public ScheduleManager(String userID) {
		this.user_id = userID;
		getSavedScheduleData();//userID 확인?
		if(scheduleData.isEmpty()){
			DataIsEmpty();
		}
		else
			viewAllList();
		
		askUserNextAction();
	}

	public void askUserNextAction() {
		Scanner scan = new Scanner(System.in);
		do {
			viewAllList();
			printMenu();
			int menuNumber = scan.nextInt();
			switch(menuNumber) {
			case 1 :
				add();
				break;
			case 2 :
				delete();
				break;
			case 3 :
				saveAndExit();
				return;
			default :
				System.out.println("잘못 입력하였습니다");
				break;	
			}
		} while(true);
	}
	
	public void printMenu() {
		System.out.println("1. 추가");
		System.out.println("2. 삭제");
		System.out.println("3. 돌아가기");
		System.out.print("입력 : ");
	}
	
	public void DataIsEmpty(){
		System.out.println("저장된 내용이 없습니다");
	}
	
	public void getSavedScheduleData() {
		try {
			FileInputStream fin = new FileInputStream("scheduleDB.txt");
			ObjectInputStream ois = new ObjectInputStream(fin);
			scheduleData = (Vector<Schedule>) ois.readObject();
			ois.close();
			fin.close();
		}
		catch (Exception exc) {
			System.out.println("오류가 발생하였습니다 "+exc);
		}
	}
	
	public void saveAndExit() {
		try {
			FileOutputStream fout = new FileOutputStream("scheduleDB.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(scheduleData);
			oos.close();
			fout.close();
		}
		catch (Exception exc) {
			System.out.println("오류가 발생하였습니다 "+exc);
		}
	}
	
	@Override
	public void add() {		
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int newYear = 1990;
		int newMonth = 1;
		int newDay = 1;
		String newContents;
		
		System.out.print("추가할 일정의 연도(1990~2020년)를 입력하십시오 (건너뛰려면 '-1'을 입력): ");
		do{
			newYear = scan.nextInt();
			if (newYear == -1){
				newYear = 1990;
				break;
			}				
		}while(newYear < 1990 || newYear > 2020);

		System.out.print("추가할 일정의 월을 입력하십시오 (건너뛰려면 '-1'을 입력): ");
		do{
			newMonth = scan.nextInt();
			if (newMonth == -1){
				newMonth = 1;
				break;
			}				
		}while(newMonth < 1 || newMonth > 12);
		
		System.out.print("추가할 일정의 일을 입력하십시오 (건너뛰려면 '-1'을 입력): ");
		do{
			newDay = scan.nextInt();
			if (newDay == -1){
				newDay = 1;
				break;
			}				
		}while(newDay < 1 || newDay > 31);

		if (DayIsOutOfRange(newYear, newMonth, newDay)){
			setPopUpWindow("존재하지 않는 날짜입니다. 마지막날로 변경합니다.");
			newDay = lastDayOfMonth(newYear, newMonth, newDay);
		}
		
		System.out.print("추가할 일정의 내용을 입력하십시오 (작성을 취소하려면 '-1'을 입력): ");
		do{
			newContents = scan.nextLine();
			if (newContents.equals("-1")){
				break;
			}
			
		}while(newContents.isEmpty());{
			setPopUpWindow("내용을 입력해주세요");
		}
		
		Date scheduleDate = new Date(newYear, newMonth, newDay);
		Schedule newSchedule = new Schedule(scheduleDate, newContents);
		scheduleData.addElement(newSchedule);
		setPopUpWindow("저장되었습니다");
	}


	public boolean DayIsOutOfRange(int newYear, int newMonth, int newDay) {
		Calendar calendar = Calendar.getInstance( );
		calendar.set(newYear, newMonth-1, newDay );
		if (calendar.getActualMaximum(Calendar.DATE) < newDay)
			return true;
		else 
			return false;
	}
	
	public int lastDayOfMonth (int newYear, int newMonth, int newDay) {
		Calendar calendar = Calendar.getInstance( );
		calendar.set(newYear, newMonth-1, newDay );
		return calendar.getActualMaximum(Calendar.DATE );	
	}
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewAllList() {
		// TODO Auto-generated method stub
		System.out.println("==============저장된 일정===============");
		for(int i = 0; i < scheduleData.size(); i++) {
			System.out.print(scheduleData.get(i).getYear()+"연");
			System.out.print(scheduleData.get(i).getMonth()+"월");
			System.out.println(scheduleData.get(i).getDay()+"일");
			System.out.println("일정 : " + scheduleData.get(i).getContents());
		}
		System.out.println("====================================");
	}
	
}
