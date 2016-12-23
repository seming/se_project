import java.util.Scanner;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class AccountManager {

	private String id;
	private String password;
	Vector idData = new Vector();
	Vector passwordData = new Vector();
	
	public AccountManager() {
		
	}
	
	private void getSavedIdData() {
		try {
			FileInputStream fileInputStream = new FileInputStream("idDB.txt");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			idData = (Vector)objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
		}
		catch(Exception exc) {
			System.out.println("������ �߻��Ͽ����ϴ�"+exc);
		}
	}
	
	private void getSavedPasswordData() {
		try {
			FileInputStream fileInputStream = new FileInputStream("passwordDB.txt");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			passwordData = (Vector)objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
		}
		catch(Exception exc) {
			System.out.println("������ �߻��Ͽ����ϴ�"+exc);
		}
	}
	
	public boolean logIn() {
		
			getAccountInformation();
			
		return false;
	}
	
	public void signUp() {
		boolean accountLengthChecker = false;
		
		do{
			System.out.println("���̵�� ��й�ȣ�� 15���̳��� �Է����ּ���.");
			getAccountInformation();
			accountLengthChecker = checkAccountLength(id, password);
			if(!accountLengthChecker) {
				System.out.println("���̵� ��й�ȣ�� 15�ڸ� �Ѿ�ϴ�.");
				System.out.println("�ٽ� �Է����ּ���.");
			}
			
		}while(!accountLengthChecker);	
	}
	
	private void getAccountInformation() {
		Scanner scan = new Scanner (System.in);
		
		System.out.println("���̵� �Է����ּ���");
		id = scan.nextLine();
		System.out.println("��й�ȣ�� �Է����ּ���");
		password = scan.nextLine();
	}
	
	private boolean checkAccountLength(String id, String password) {
		boolean idLengthChecker = checkLength(id);
		boolean passwordLengthChecker = checkLength(password);
		
		if(idLengthChecker == true && passwordLengthChecker == true) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	private boolean checkLength(String stringForCheck) {
		int lengthChecker = stringForCheck.length();
		
		if(lengthChecker <= 15) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
