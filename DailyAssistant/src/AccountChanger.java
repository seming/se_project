import java.util.Scanner;

public class AccountChanger {
	
	public String id;
	public String password;
	
	public AccountChanger(/*TODO Auto-generated method stub*/) {
		// TODO Auto-generated method stub
	}
	
	public boolean identifyPassword(String password) {
		String passwordForIdentify;
		
		System.out.println("���� ��й�ȣ�� �Է����ּ���.");
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
			System.out.println("���ο� ���̵� �Է����ּ���." );
			newIdForChange = scan.nextLine();
			System.out.println("Ȯ���� ���� �ٽ� �ѹ� �Է����ּ���.");
			newIdForIdentify = scan.nextLine();
		
			if(newIdForChange.equals(newIdForIdentify)){
				id = ChangeToNewValue(newIdForChange);
				System.out.println("���̵� ���������� ����Ǿ����ϴ�.");
			}
			else{
				System.out.println("�Է��� ���ο� ���̵�� Ȯ�ο� ���̵� �ٸ��ϴ�.");
				System.out.println("�ٽ� �Է����ּ���.");
			}
		}while(!isIdValueSame);
	}
	
	public void changePassword(String newPassword) {
		String newPasswordForChange;
		String newPasswordForIdentify;
		boolean isPasswordValueSame = false;
		
		do{
			Scanner scan = new Scanner (System.in);
			System.out.println("���ο� ��й�ȣ�� �Է����ּ���." );
			newPasswordForChange = scan.nextLine();
			System.out.println("Ȯ���� ���� �ٽ� �ѹ� �Է����ּ���.");
			newPasswordForIdentify = scan.nextLine();
			
			if(newPasswordForChange.equals(newPasswordForIdentify)){
				password = ChangeToNewValue(newPasswordForChange);
				System.out.println("��й�ȣ�� ���������� ����Ǿ����ϴ�.");
			}
			else{
				System.out.println("�Է��� ���ο� ��й�ȣ�� Ȯ�ο� ���̵� �ٸ��ϴ�.");
				System.out.println("�ٽ� �Է����ּ���.");
			}
		}while(!isPasswordValueSame);
	}
	
	private String ChangeToNewValue(String newValue) {
		return newValue;
	}
	
}
