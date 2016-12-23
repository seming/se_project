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
				System.out.println("�߸� �Է��Ͽ����ϴ�");
				break;	
			}
		} while(true);
	}
	
	public void printMenu() {
		System.out.println("1. �߰�");
		System.out.println("2. ����");
		System.out.println("3. ���ư���");
		System.out.print("�Է� : ");
	}

	public void add() {
		Scanner scan = new Scanner(System.in);
		System.out.print("�߰��� ����ó�� �̸��� �Է��Ͻʽÿ� : ");
		String newName = scan.nextLine();
		System.out.print("�߰��� ����ó�� ��ȭ��ȣ�� �Է��Ͻʽÿ�(- �����Ͽ� �Է�) : ");
		String newNumber = scan.nextLine();
		
		if(isEmptyNameOrEmptyNumber(newName, newNumber)) {
			System.out.println("�̸� Ȥ�� ��ȭ��ȣ�� ����ֽ��ϴ�.");
			if(alarmWhenCancel() == true)
				return;
			else
				add();
		}
		else {
			newNumber = checkNumberAndModifyIfOutOfRange(newNumber);
			Phonebook newPhoneBook = new Phonebook(newName, newNumber);
			phonebookData.addElement(newPhoneBook);
			System.out.println("����Ǿ����ϴ�");
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
		System.out.print("������ ����ó�� �̸��� �Է��Ͻʽÿ� : ");
		String nameToBeDeleted = scan.nextLine();
		System.out.print("������ ����ó�� ��ȭ��ȣ�� �Է��Ͻʽÿ� : ");
		String phonenumberToBeDeleted = scan.nextLine();
		
		int index = getIndexOfPhonebook(nameToBeDeleted, phonenumberToBeDeleted);
		if(index != -1) 
			if(alarmWhenDelete()) {
				phonebookData.remove(index);
				System.out.println("�����Ǿ����ϴ�");
			}
		else
			System.out.println("�ش� ��ȭ��ȣ�� �����ϴ�");
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
		System.out.println("==============����� ����ó===============");
		for(int i = 0; i < phonebookData.size(); i++) {
			System.out.print("�̸� : " + phonebookData.get(i).getName());
			System.out.println(", ��ȭ��ȣ : " + phonebookData.get(i).getNumber());
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