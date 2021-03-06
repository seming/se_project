import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Vector;

public class PhonebookManager extends DailyManageOutline {
	private String user_id;
	private Vector<Phonebook> phonebookData;
	
	public PhonebookManager(String user_id) {
		this.user_id = user_id;
		phonebookData = new Vector<Phonebook>();
		getSavedPhonebookData();
	}
	
	public void getSavedPhonebookData() {
		String inputFilePath = "database\\"+user_id+"_phonebookDB.txt";
		createNewFileIfNoFile(inputFilePath);
		try {
			FileInputStream fileInputStream = new FileInputStream(inputFilePath);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			phonebookData = (Vector<Phonebook>) objectInputStream.readObject();
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
	
	public void askUserNextAction() {
		Scanner scan = new Scanner(System.in);
		do {
			viewAllList();
			printMenu();
			int menuNumber = scan.nextInt();
			switch(menuNumber) {
			case 1 :
				add();
				break;
			case 2 :
				delete();
				break;
			case 3 :
				saveAndExit();
				return;
			default :
				setPopUpWindow("잘못 입력하였습니다");
				break;	
			}
		} while(true);
	}
	
	public void printMenu() {
		System.out.println("1. 추가");
		System.out.println("2. 삭제");
		System.out.println("3. 돌아가기");
		System.out.print("입력 : ");
	}

	public void add() {
		Scanner scan = new Scanner(System.in);
		System.out.print("추가할 연락처의 이름을 입력하십시오 : ");
		String newName = scan.nextLine();
		System.out.print("추가할 연락처의 전화번호를 입력하십시오(- 제외하여 입력) : ");
		String newNumber = scan.nextLine();
		
		if(isEmptyNameOrEmptyNumber(newName, newNumber)) {
			setPopUpWindow("이름 혹은 전화번호가 비어있습니다.");
			if(alarmWhenCancel() == true)
				return;
			else
				add();
		}
		else {
			newNumber = checkNumberAndModifyIfOutOfRange(newNumber);
			Phonebook newPhoneBook = new Phonebook(newName, newNumber);
			phonebookData.addElement(newPhoneBook);
			setPopUpWindow("저장되었습니다");
		}
	}
	
	public boolean isEmptyNameOrEmptyNumber(String name, String phonenumber) {
		if(name.isEmpty())
			return true;
		else
			if(phonenumber.isEmpty())
				return true;
			else
				return false;
	}
	
	public String checkNumberAndModifyIfOutOfRange(String phonenumber) {
		if(phonenumber.length() <= 11)
			return phonenumber;
		else
			return phonenumber.substring(0, 11);
	}

	public void delete() {
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제할 연락처의 이름을 입력하십시오 : ");
		String nameToBeDeleted = scan.nextLine();
		System.out.print("삭제할 연락처의 전화번호를 입력하십시오 : ");
		String phonenumberToBeDeleted = scan.nextLine();
		
		int index = getIndexOfPhonebook(nameToBeDeleted, phonenumberToBeDeleted);
		if(index != -1) 
			if(alarmWhenDelete()) {
				phonebookData.remove(index);
				setPopUpWindow("삭제되었습니다");
			}
		else
			setPopUpWindow("해당 전화번호가 없습니다");
	}
	
	public int getIndexOfPhonebook(String phonename, String phonenumber) {
		for(int i = 0; i < phonebookData.size(); i++) {
			if(phonebookData.get(i).getName().equals(phonename)
					&& phonebookData.get(i).getNumber().equals(phonenumber))
				return i;
		}
		return -1;
	}
	
	public void viewAllList() {
		if(phonebookData.size() == 0)
			return;
		System.out.println("==============저장된 연락처===============");
		for(int i = 0; i < phonebookData.size(); i++) {
			System.out.print("이름 : " + phonebookData.get(i).getName());
			System.out.println(", 전화번호 : " + phonebookData.get(i).getNumber());
		}
		System.out.println("====================================");
	}
	
	public void saveAndExit() {
		try {
			String outputFilePath = "database\\"+user_id+"_phonebookDB.txt";
			FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(phonebookData);
			objectOutputStream.close();
			fileOutputStream.close();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}