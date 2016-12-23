import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	private static String userID;
	private static AccountManager accountmanager;
	private static PhonebookManager phonebookmanager;
	private static ScheduleManager schedulemanager;
	private static NoteManager notemanager;

	public static void main(String[] args) {		
		accountmanager = new AccountManager();
		
		accountmanager.logIn();
		
		do{
			printMenu();
			int userChoice = getInputAndHandleException();
			switch(userChoice){
			case 1:
				schedulemanager = new ScheduleManager(userID);
				schedulemanager.askUserNextAction();
				break;
			case 2:
				phonebookmanager = new PhonebookManager(userID);
				phonebookmanager.askUserNextAction();
				break;
			case 3:
				notemanager = new NoteManager(userID);
				notemanager.askUserNextAction();
				break;
			case 4:
				System.out.println("Daily Assistant를 종료합니다.");
				return;
			default:
				System.out.println("잘못된 입력값입니다. 다시 입력해주십시오.");
			}
		} while(true);
	}

	private static void printMenu() {
		System.out.println("1. 스케줄 관리");
		System.out.println("2. 전화번호부 관리");
		System.out.println("3. 노트 관리");
		System.out.println("4. 종료");
		System.out.print("입력하세요 : ");
	}

	private static int getInputAndHandleException() {
		Scanner sc = new Scanner(System.in);
		int input;
		try{
			input = sc.nextInt();
		}catch(InputMismatchException ime){
			System.out.println("잘못된 입력값입니다. 다시 입력해주십시오.");
			return getInputAndHandleException();
		}
		return input;
	}
}
