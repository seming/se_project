import java.util.Scanner;

public class AccountChanger {
	
	public String id;
	public String password;
	
	public AccountChanger(/*TODO Auto-generated method stub*/) {
		// TODO Auto-generated method stub
	}
	
	public boolean identifyPassword(String password) {
		String passwordForIdentify;
		boolean isPasswordForIdentifySame = false;
		
		System.out.println("현재 비밀번호를 입력해주세요.");
		Scanner scan = new Scanner (System.in);
		passwordForIdentify = scan.nextLine();
		isPasswordForIdentifySame = checkPasswordForIdentifySame(passwordForIdentify);
		return isPasswordForIdentifySame;
	}
	
	public void changeId() {
		String newIdForChange = null;
		String newIdForIdentify = null;
		boolean isIdInformationSame = false;
		
		do{
			System.out.println("새로운 아이디를 입력하고 동일한 아이디를 아래에 한 번 더 입력해주세요.");
			getInformation(newIdForChange, newIdForIdentify);
			isIdInformationSame = checkIdInformationSame(newIdForChange, newIdForIdentify);
			if(isIdInformationSame) {
				id = changeToNewValue(newIdForChange);
				System.out.println("아이디가 성공적으로 변경되었습니다.");
			}
			else{
				System.out.println("입력한 새로운 아이디와 확인용 아이디가 다릅니다.");
				System.out.println("다시 입력해주세요.");
			}
		}while(!isIdInformationSame);
	}
	
	public void changePassword(String newPassword) {
		String newPasswordForChange = null;
		String newPasswordForIdentify = null;
		boolean isPasswordInformationSame = false;
		
		do{
			System.out.println("새로운 비밀번호를 입력하고 동일한 비밀번호를 아래에 한 번 더 입력해주세요.");
			getInformation(newPasswordForChange, newPasswordForIdentify);
			isPasswordInformationSame = checkPasswordInformationSame(newPasswordForChange, newPasswordForIdentify);
			if(isPasswordInformationSame) {
				password = changeToNewValue(newPasswordForChange);
				System.out.println("비밀번호가 성공적으로 변경되었습니다.");
			}
			else{
				System.out.println("입력한 새로운 비밀번호와 확인용 아이디가 다릅니다.");
				System.out.println("다시 입력해주세요.");
			}
		}while(!isPasswordInformationSame);
	}
	
	private String changeToNewValue(String newValue) {
		return newValue;
	}
	
	private void getInformation(String newInformation, String newInformationForIdentify) {
		Scanner scan = new Scanner (System.in);
		System.out.printf("변경내용입력 : " );
		newInformation = scan.nextLine();
		System.out.printf("확인 : ");
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
