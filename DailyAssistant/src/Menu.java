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
				System.out.println("Daily Assistant�� �����մϴ�.");
				return;
			default:
				System.out.println("�߸��� �Է°��Դϴ�. �ٽ� �Է����ֽʽÿ�.");
			}
		} while(true);
	}

	private static void printMenu() {
		System.out.println("1. ������ ����");
		System.out.println("2. ��ȭ��ȣ�� ����");
		System.out.println("3. ��Ʈ ����");
		System.out.println("4. ����");
		System.out.print("�Է��ϼ��� : ");
	}

	private static int getInputAndHandleException() {
		Scanner sc = new Scanner(System.in);
		int input;
		try{
			input = sc.nextInt();
		}catch(InputMismatchException ime){
			System.out.println("�߸��� �Է°��Դϴ�. �ٽ� �Է����ֽʽÿ�.");
			return getInputAndHandleException();
		}
		return input;
	}
}
