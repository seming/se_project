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
	private Vector<Schedule> oneUserScheduleData = null;
	
	public ScheduleManager(String userID) {
		this.user_id = userID;
		getSavedScheduleData();
		if(DataIsEmpty()){
			System.out.println("����� ������ �����ϴ�");
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
				System.out.println("�߸� �Է��Ͽ����ϴ�");
				break;	
			}
		} while(true);
	}
	
	public void printMenu() {
		System.out.println("1. �߰�");
		System.out.println("2. ����");
		System.out.println("3. ���ư���");
		System.out.print("�Է� : ");
	}
	
	public boolean DataIsEmpty(){
		for(int i = 0; i < scheduleData.size(); i++) {
			if (scheduleData.get(i).getUserID().equals(user_id)){
				return false;
			}
			
		}
		return true;
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
			System.out.println("������ �߻��Ͽ����ϴ� "+exc);
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
			System.out.println("������ �߻��Ͽ����ϴ� "+exc);
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
		
		System.out.print("�߰��� ������ ����(1990~2020��)�� �Է��Ͻʽÿ� (�ǳʶٷ��� '-1'�� �Է�): ");
		do{
			newYear = scan.nextInt();
			if (newYear == -1){
				newYear = 1990;
				break;
			}				
		}while(newYear < 1990 || newYear > 2020);

		System.out.print("�߰��� ������ ���� �Է��Ͻʽÿ� (�ǳʶٷ��� '-1'�� �Է�): ");
		do{
			newMonth = scan.nextInt();
			if (newMonth == -1){
				newMonth = 1;
				break;
			}				
		}while(newMonth < 1 || newMonth > 12);
		
		System.out.print("�߰��� ������ ���� �Է��Ͻʽÿ� (�ǳʶٷ��� '-1'�� �Է�): ");
		do{
			newDay = scan.nextInt();
			if (newDay == -1){
				newDay = 1;
				break;
			}				
		}while(newDay < 1 || newDay > 31);

		if (DayIsOutOfRange(newYear, newMonth, newDay)){
			setPopUpWindow("�������� �ʴ� ��¥�Դϴ�. ���������� �����մϴ�.");
			newDay = lastDayOfMonth(newYear, newMonth, newDay);
		}
		
		System.out.print("�߰��� ������ ������ �Է��Ͻʽÿ� (�ۼ��� ����Ϸ��� '-1'�� �Է�): ");
		do{
			newContents = scan.nextLine();
			if (newContents.equals("-1")){
				if(alarmWhenCancel()){
					return;
				}
				else
					;
			}
			
		}while(newContents.isEmpty());{
			setPopUpWindow("������ �Է����ּ���");
		}

		newContents = checkLengthOFContentsAndModifyIfOutOfRange(newContents);
		Date scheduleDate = new Date(newYear, newMonth, newDay);
		Schedule newSchedule = new Schedule(user_id, scheduleDate, newContents);
		scheduleData.addElement(newSchedule);
		setPopUpWindow("����Ǿ����ϴ�");
	}

	public String checkLengthOFContentsAndModifyIfOutOfRange(String newContents) {
		if(newContents.length() > 50)
			return newContents.substring(0, 50);
		else
			return newContents;
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
		System.out.print("������ ������ ���� ��ȣ�� �Է��ϼ���");
		Scanner scan = new Scanner(System.in);
		int index = scan.nextInt();
		Schedule scheduleToBeDeleted = oneUserScheduleData.get(index);
		index = scheduleData.indexOf(scheduleToBeDeleted);
		scheduleData.remove(index);
		
	}

	@Override
	public void viewAllList() {
		// TODO Auto-generated method stub
		for (int i = 0; i < scheduleData.size(); i++){
			if (scheduleData.get(i).equals(user_id)){
				oneUserScheduleData.addElement(scheduleData.get(i));
			}
		}
		
		System.out.println("==============����� ����===============");
		for(int i = 0; i < oneUserScheduleData.size(); i++) {
			System.out.print(i+". ");
			System.out.print(oneUserScheduleData.get(i).getYear()+"��");
			System.out.print(oneUserScheduleData.get(i).getMonth()+"��");
			System.out.println(oneUserScheduleData.get(i).getDay()+"��");
			System.out.println("���� : " + scheduleData.get(i).getContents());
		}
		System.out.println("====================================");
	}
	
}
