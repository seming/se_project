import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

public class ScheduleManager extends DailyManageOutline {
	private String user_id;
	private Vector<Schedule> scheduleData;
	
	public ScheduleManager(String userID) {
		this.user_id = userID;
		scheduleData = new Vector<Schedule>();
		getSavedScheduleData();
	}

	public void getSavedScheduleData() {
		String inputFilePath = "database\\"+user_id+"_scheduleDB.txt";
		createNewFileIfNoFile(inputFilePath);
		try {
			FileInputStream fileInputStream = new FileInputStream(inputFilePath);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			scheduleData = (Vector<Schedule>) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
		}
		catch (Exception exc) {}
	}
	
	private void createNewFileIfNoFile(String inputfilepath) {
		File inputfile = new File(inputfilepath);
		if(!inputfile.isFile()){
			try {
				inputfile.createNewFile();
			}
			catch (IOException e) {}
		}
	}
	
	public void askUserNextAction() {
		Scanner scan = new Scanner(System.in);
		do {
			if(!scheduleData.isEmpty()){
				viewAllList();
			}
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
	
	public void add() {	
		Scanner scan = new Scanner(System.in);
		int newYear = 1990;
		int newMonth = 1;
		int newDay = 1;
		String newContents;
		
		do {
			System.out.print("�߰��� ������ ����(1990~2020��)�� �Է��Ͻʽÿ� (�ǳʶٷ��� '-1'�� �Է�): ");
			newYear = scan.nextInt();
			if (isSkipped(newYear)){
				newYear = 1990;
				break;
			}				
		} while(newYear < 1990 || newYear > 2020);

		do {
			System.out.print("�߰��� ������ ���� �Է��Ͻʽÿ� (�ǳʶٷ��� '-1'�� �Է�): ");
			newMonth = scan.nextInt();
			if (isSkipped(newMonth)){
				newMonth = 1;
				break;
			}				
		} while(newMonth < 1 || newMonth > 12);

		do {
			System.out.print("�߰��� ������ ���� �Է��Ͻʽÿ� (�ǳʶٷ��� '-1'�� �Է�): ");
			newDay = scan.nextInt();
			if (isSkipped(newDay)){
				newDay = 1;
				break;
			}				
		} while(newDay < 1 || newDay > 31);

		if (DayIsOutOfRange(newYear, newMonth, newDay)){
			setPopUpWindow("�������� �ʴ� ��¥�Դϴ�. ���������� �����մϴ�.");
			newDay = 30;
		}
		scan.nextLine();
		do {
			System.out.print("�߰��� ������ ������ �Է��Ͻʽÿ� (�ۼ��� ����Ϸ��� '-1'�� �Է�):");
			newContents = scan.nextLine();
			if(newContents.equals("-1")){
				if(alarmWhenCancel()){
					newContents = null;
					break;
				}
				else
					;
			}			
		} while(newContents.isEmpty());

		if(newContents == null){
			return;
		}
		
		newContents = checkLengthOFContentsAndModifyIfOutOfRange(newContents);
		Schedule newSchedule = new Schedule(newYear, newMonth, newDay , newContents);
		scheduleData.addElement(newSchedule);
		setPopUpWindow("����Ǿ����ϴ�");
	}
	
	public boolean isSkipped(int signal) {
		if (signal == -1)
			return true;
		return false;
	}

	public String checkLengthOFContentsAndModifyIfOutOfRange(String newContents) {
		if(newContents.length() > 50)
			return newContents.substring(0, 50);
		else
			return newContents;
	}
	
	public boolean DayIsOutOfRange(int newYear, int newMonth, int newDay) {
		if ((newMonth == 2 || newMonth == 4 || newMonth == 6 || newMonth == 9 || newMonth == 11) 
				&& newDay>30)
			return true;
		else 
			return false;
	}

	public void delete() {
		viewAllList();
		System.out.print("������ ������ ���� ��ȣ�� �Է��ϼ���:");
		Scanner scan = new Scanner(System.in);
		int toBeDeleted;
		do {
			toBeDeleted = scan.nextInt(); 
		} while(scheduleData.size() < toBeDeleted  || toBeDeleted < 0);
		if(alarmWhenDelete()){
			scheduleData.remove(toBeDeleted);
			System.out.println("�����Ǿ����ϴ�");
		}
		else
			;
	}

	public void viewAllList() {
		System.out.println("==============����� ����===============");
		for(int i = 0; i < scheduleData.size(); i++) {
			System.out.print(i+". ");
			System.out.print(scheduleData.get(i).getYear()+"��");
			System.out.print(scheduleData.get(i).getMonth()+"��");
			System.out.print(scheduleData.get(i).getDay()+"��");
			System.out.println(". ���� : " + scheduleData.get(i).getContents());
		}
		System.out.println("====================================");
	}
	
	public void saveAndExit() {
		String outputFilePath = "database\\"+user_id+"_scheduleDB.txt";
		try {
			FileOutputStream fileoutputstream = new FileOutputStream(outputFilePath);
			ObjectOutputStream objectoutputstream = new ObjectOutputStream(fileoutputstream);
			objectoutputstream.writeObject(scheduleData);
			fileoutputstream.close();
			objectoutputstream.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
