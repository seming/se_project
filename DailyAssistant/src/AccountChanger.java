import java.util.Scanner;

public class AccountChanger {
	
	public String id;
	public String password;
	
	public AccountChanger(/*TODO Auto-generated method stub*/) {
		// TODO Auto-generated method stub
	}
	
	public boolean identifyPassword(String password) {
		String passwordForIdentify;
		
		System.out.println("현재 비밀번호를 입력해주세요.");
		Scanner scan = new Scanner (System.in);
		passwordForIdentify = scan.nextLine();
		
		if (passwordForIdentify.equals(password)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void changeId() {
		String newIdForChange;
		String newIdForIdentify;
		boolean isIdValueSame = false;
		
		do{
			Scanner scan = new Scanner (System.in);
			System.out.println("새로운 아이디를 입력해주세요." );
			newIdForChange = scan.nextLine();
			System.out.println("확인을 위해 다시 한번 입력해주세요.");
			newIdForIdentify = scan.nextLine();
		
			if(newIdForChange.equals(newIdForIdentify)){
				id = ChangeToNewValue(newIdForChange);
				System.out.println("아이디가 성공적으로 변경되었습니다.");
			}
			else{
				System.out.println("입력한 새로운 아이디와 확인용 아이디가 다릅니다.");
				System.out.println("다시 입력해주세요.");
			}
		}while(!isIdValueSame);
	}
	
	public void changePassword(String newPassword) {
		String newPasswordForChange;
		String newPasswordForIdentify;
		boolean isPasswordValueSame = false;
		
		do{
			Scanner scan = new Scanner (System.in);
			System.out.println("새로운 비밀번호를 입력해주세요." );
			newPasswordForChange = scan.nextLine();
			System.out.println("확인을 위해 다시 한번 입력해주세요.");
			newPasswordForIdentify = scan.nextLine();
			
			if(newPasswordForChange.equals(newPasswordForIdentify)){
				password = ChangeToNewValue(newPasswordForChange);
				System.out.println("비밀번호가 성공적으로 변경되었습니다.");
			}
			else{
				System.out.println("입력한 새로운 비밀번호와 확인용 아이디가 다릅니다.");
				System.out.println("다시 입력해주세요.");
			}
		}while(!isPasswordValueSame);
	}
	
	private String ChangeToNewValue(String newValue) {
		return newValue;
	}
	
}
