import java.util.Scanner;
import java.util.Vector;

public class PhonebookManager extends DailyManageOutline {
	private String user_id;
	private Vector<Phonebook> phonebookData;
	
	public PhonebookManager(String user_id) {
		phonebookData = new Vector<Phonebook>();	//새로 만드는게 아닌 파일에서 가져올 수 있도록 수정요함
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
				return;
			default :
				System.out.println("잘못 입력하였습니다");
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
		
		if(newName.isEmpty() || newNumber.isEmpty()) {
			System.out.println("이름 혹은 전화번호가 비어있습니다.");
			if(alarmWhenCancel() == true)
				return;
			else
				add();
		}
		else {
			newNumber = checkNumberAndModifyIfOutOfRange(newNumber);
			Phonebook newPhoneBook = new Phonebook(newName, newNumber);
			phonebookData.addElement(newPhoneBook);
			System.out.println("저장되었습니다");
		}
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
				System.out.println("삭제되었습니다");
			}
		else
			System.out.println("해당 전화번호가 없습니다");
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
		System.out.println("==============저장된 연락처===============");
		for(int i = 0; i < phonebookData.size(); i++) {
			System.out.print("이름 : " + phonebookData.get(i).getName());
			System.out.println(", 전화번호 : " + phonebookData.get(i).getNumber());
		}
		System.out.println("====================================");
	}
}