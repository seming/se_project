import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class AccountChanger extends AccountManager{
	private static AccountManager accountmanager;
	String id;
	String password;
	
	public AccountChanger(String userID, String userPassword) {
		accountmanager = new AccountManager();
		id = userID;
		password = userPassword;
		System.out.println(password);
		identifyPassword(password);
		System.out.println(password);
		askUserNextAction();
	}
	
	public void identifyPassword(String password) {
		String passwordForIdentify;
		boolean isPasswordForIdentifySame = false;
		
		do{
			System.out.println("���� ��й�ȣ�� �Է����ּ���.");
			Scanner scan = new Scanner (System.in);
			passwordForIdentify = scan.nextLine();
			isPasswordForIdentifySame = checkPasswordForIdentifySame(passwordForIdentify);
			if(!isPasswordForIdentifySame) {
				System.out.print("��й�ȣ�� �ٸ��ϴ�. �ٽ� �Է����ּ���.");
			}
		}while(!isPasswordForIdentifySame);
	}
	
	public void askUserNextAction() {
		Scanner scan = new Scanner(System.in);
		do {
			printMenu();
			int menuNumber = scan.nextInt();
			switch(menuNumber) {
			case 1 :
				changeId();
				break;
			case 2 :
				changePassword();
				break;
			case 3 :
				//saveAndExit();
				return;
			default :
				System.out.println("�߸� �Է��Ͽ����ϴ�");
				break;	
			}
		} while(true);
	}
	
	public void printMenu() {
		System.out.println("1. ���̵� ����");
		System.out.println("2. ��й�ȣ ����");
		System.out.println("3. ���ư���");
		System.out.print("�Է� : ");
	}
	
	public void changeId() {
		String newIdForChange = null;
		String newIdForIdentify = null;
		boolean isIdInformationSame = false;
		
		do{
			System.out.println("���ο� ���̵� �Է��ϰ� ������ ���̵� �Ʒ��� �� �� �� �Է����ּ���.");
			getInformation(newIdForChange, newIdForIdentify);
			isIdInformationSame = checkIdInformationSame(newIdForChange, newIdForIdentify);
			if(isIdInformationSame) {
				id = changeToNewValue(newIdForChange);
				accountmanager.setId(id);
				System.out.println("���̵� ���������� ����Ǿ����ϴ�.");
			}
			else{
				System.out.println("�Է��� ���ο� ���̵�� Ȯ�ο� ���̵� �ٸ��ϴ�.");
				System.out.println("�ٽ� �Է����ּ���.");
			}
		}while(!isIdInformationSame);
	}
	
	public void changePassword() {
		String newPasswordForChange = null;
		String newPasswordForIdentify = null;
		boolean isPasswordInformationSame = false;
		
		do{
			System.out.println("���ο� ��й�ȣ�� �Է��ϰ� ������ ��й�ȣ�� �Ʒ��� �� �� �� �Է����ּ���.");
			getInformation(newPasswordForChange, newPasswordForIdentify);
			isPasswordInformationSame = checkPasswordInformationSame(newPasswordForChange, newPasswordForIdentify);
			if(isPasswordInformationSame) {
				password = changeToNewValue(newPasswordForChange);
				accountmanager.setPassword(password);
				System.out.println("��й�ȣ�� ���������� ����Ǿ����ϴ�.");
			}
			else{
				System.out.println("�Է��� ���ο� ��й�ȣ�� Ȯ�ο� ���̵� �ٸ��ϴ�.");
				System.out.println("�ٽ� �Է����ּ���.");
			}
		}while(!isPasswordInformationSame);
	}
	
	private String changeToNewValue(String newValue) {
		return newValue;
	}
	
	private void getInformation(String newInformation, String newInformationForIdentify) {
		Scanner scan = new Scanner (System.in);
		System.out.printf("���泻���Է� : " );
		newInformation = scan.nextLine();
		System.out.printf("Ȯ�� : ");
		newInformationForIdentify = scan.nextLine();
	}
	
	private boolean checkPasswordForIdentifySame(String passwordForIdentify) {
		if (passwordForIdentify.equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}
	private boolean checkIdInformationSame(String newIdForChange, String newIdForIdentify) {
		if(newIdForChange.equals(newIdForIdentify)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean checkPasswordInformationSame(String newPasswordForChange, String newPasswordForIdentify) {
		if(newPasswordForChange.equals(newPasswordForIdentify)) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
