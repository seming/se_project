import java.util.Scanner;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Account {

	private String id;
	private String password;
	Vector idData = new Vector();
	Vector passwordData = new Vector();
	
	public Account() {
		// TODO Auto-generated method stub
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
	
	public boolean logIn(String id, String password) {
		
		getAccountInformation();
		
		
		
		return false;
	}
	
	public void signUp() {
		
		System.out.println("���̵�� ��й�ȣ�� 15���̳��� �Է����ּ���.");
		getAccountInformation();
		
		
			
	}
	
	private void getAccountInformation() {
		Scanner scan = new Scanner (System.in);
		
		System.out.println("���̵� �Է����ּ���");
		id = scan.nextLine();
		System.out.println("��й�ȣ�� �Է����ּ���");
		password = scan.nextLine();
	}
	
	
}
