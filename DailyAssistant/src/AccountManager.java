import java.util.Scanner;
import java.util.Vector;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class AccountManager implements Serializable{

	private String id;
	private String password;
	private int isFileEmpty = -1;
	Vector<String> idData = new Vector<String>();
	Vector<String> passwordData = new Vector<String>();
	boolean isSignUp = false;
	
	public AccountManager() {
		getSavedIdData();
		getSavedPasswordData();
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setId(String newId) {
		id = newId;
		while(true) {
			for(int i=0; i<idData.size(); i++) {
				if(id.equals(idData.get(i))){
					idData.setElementAt(id, i);
					saveIdData();
					break;
				}
			}
		}
	}
	
	public void setPassword(String newPassword) {
		password = newPassword;
		while(true) {
			for(int i=0; i<passwordData.size(); i++) {
				if(id.equals(passwordData.get(i))){
					idData.setElementAt(password, i);
					savePasswordData();
					break;
				}
			}
		}
	}
	
	private void getSavedIdData() {
		String inputFilePath = "database\\idDB.txt";
		createNewFileIfNoFile(inputFilePath);
		/*try {
			FileInputStream fileInputStream = new FileInputStream(inputFilePath);
			isFileEmpty = fileInputStream.read();
			if(isFileEmpty != -1) {
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				idData = (Vector<String>)objectInputStream.readObject();
				objectInputStream.close();
			}
			fileInputStream.close();
		}
		catch(Exception exc) {
			System.out.println("오류가 발생하였습니다"+exc);
		}*/
		try {
			FileInputStream fileInputStream = new FileInputStream(inputFilePath);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			idData = (Vector<String>) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
		}
		catch (Exception exc) {}
	}
	
	private void getSavedPasswordData() {
		/*String inputFilePath = "database\\passwordDB.txt";
		createNewFileIfNoFile(inputFilePath);
		try {
			FileInputStream fileInputStream = new FileInputStream(inputFilePath);
			if(isFileEmpty != -1) {
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				passwordData = (Vector<String>)objectInputStream.readObject();
				objectInputStream.close();
			}
			fileInputStream.close();
		}
		catch(Exception exc) {
			System.out.println("오류가 발생하였습니다"+exc);
		}*/
		String inputFilePath = "database\\passwordDB.txt";
		createNewFileIfNoFile(inputFilePath);
		try {
			FileInputStream fileInputStream = new FileInputStream(inputFilePath);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			passwordData = (Vector<String>) objectInputStream.readObject();
			objectInputStream.close();
			fileInputStream.close();
		}
		catch (Exception exc) {}
	}
	
	private void createNewFileIfNoFile(String inputfilepath) {
		File inputfile = new File(inputfilepath);
		if(!inputfile.isFile()){
			try {
				inputfile.createNewFile();
			}
			catch (IOException e) {}
		}
	}
	
	public boolean logIn() {
		System.out.println("로그인");
		askUserNextAction();
		if(isFileEmpty == -1 && isSignUp == false) {
			isSignUp = viewSignUpMenu();	
			if(isSignUp == true) {
				signUp();
				return false;
			}
			else {
				return false;
			}
		}
		getAccountInformation();
		int i;
		for(i=0; i<idData.size(); i++) {
			if(id.equals(idData.get(i))){
				if(password.equals(passwordData.get(i))){
					System.out.println("로그인 성공");
					saveIdData();
					savePasswordData();
					return true;
				}
				else {
					System.out.println("비밀번호가 다릅니다.");
					System.out.println("로그인 실패");
					return false;
				}
			}
		}
		System.out.println("아이디가 존재하지 않습니다.");
		return false;
	}
	
	public void askUserNextAction() {
		Scanner scan = new Scanner(System.in);
		do {
			printMenu();
			int menuNumber = scan.nextInt();
			switch(menuNumber) {
			case 1 :
				signUp();
				break;
			case 2 :
				return;
			default :
				System.out.println("잘못 입력하였습니다");
				break;	
			}
		} while(true);
	}
	
	public void printMenu() {
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.print("입력 : ");
	}
	
	private boolean viewSignUpMenu() {
		Scanner scan = new Scanner(System.in);
		int isSignUp;
		
		System.out.println("저장된 아이디가 없습니다.");
		System.out.println("가입하시겠습니까?");
		System.out.println("1. 가입");
		System.out.println("2. 돌아가기");
		isSignUp = scan.nextInt();
		if(isSignUp == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void signUp() {
		boolean accountLengthChecker = false;
		
		System.out.println("회원가입");
		do{
			System.out.println("아이디와 비밀번호는 15자이내로 입력해주세요.");
			getAccountInformation();
			accountLengthChecker = checkAccountLength(id, password);
			if(!accountLengthChecker) {
				System.out.println("아이디나 비밀번호가 15자를 넘어갑니다.");
				System.out.println("다시 입력해주세요.");
			}
		}while(!accountLengthChecker);	
		
		if(isFileEmpty != -1) {
			int i;
			while(true) {
				for(i=0; i<idData.size(); i++) {
					if(id.equals(idData.get(i))){
						System.out.println("이미 존재하는 아이디입니다.");
						System.out.println("다시 입력해주세요.");
						getAccountInformation();
						break;
					}
				}
				if(i== idData.size()){
					break;
				}
			}
		}
		idData.addElement(id);
		passwordData.addElement(password);
		System.out.println("회원가입 완료");
		isSignUp = true;
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
	
	public void saveIdData() {
		try {
			String outputFilePath = "database\\idDB.txt";
			FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(idData);
			objectOutputStream.close();
			fileOutputStream.close();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public void savePasswordData() {
		try {
			String outputFilePath = "database\\passwordDB.txt";
			FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(passwordData);
			objectOutputStream.close();
			fileOutputStream.close();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
}
