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
			System.out.println("오류가 발생하였습니다"+exc);
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
			System.out.println("오류가 발생하였습니다"+exc);
		}
	}
	
	public boolean logIn() {
		
			getAccountInformation();
			
		return false;
	}
	
	public void signUp() {
		boolean accountLengthChecker = false;
		
		do{
			System.out.println("아이디와 비밀번호는 15자이내로 입력해주세요.");
			getAccountInformation();
			accountLengthChecker = checkAccountLength(id, password);
			if(!accountLengthChecker) {
				System.out.println("아이디나 비밀번호가 15자를 넘어갑니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		}while(!accountLengthChecker);	
	}
	
	private void getAccountInformation() {
		Scanner scan = new Scanner (System.in);
		
		System.out.println("아이디를 입력해주세요");
		id = scan.nextLine();
		System.out.println("비밀번호를 입력해주세요");
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
