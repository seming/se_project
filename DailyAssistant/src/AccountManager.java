import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;

public class AccountManager {

	public String id;
	public String password;
	
	/*public Account() {
		// TODO Auto-generated method stub
	}*/
	
	public void getAccountInformationForLogin() {
		Scanner scan = new Scanner (System.in);
		
		System.out.println("아이디를 입력해주세요");
		id = scan.nextLine();
		System.out.println("비밀번호를 입력해주세요");
		password = scan.nextLine();
	}
	
	public void getAccountInformation() {
		
	}
	
	public boolean logIn(String id, String password) {
		
		return false;
	}
	
	public void signUp() {
		
	}
}
