import java.util.Scanner;
import java.util.Vector;

public class PhonebookManager extends DailyManageOutline {
	private String user_id;
	private Vector<Phonebook> phonebookData;
	
	public PhonebookManager(String user_id) {
		phonebookData = new Vector<Phonebook>();	//새로 만드는게 아닌 파일에서 가져올 수 있도록 수정요함
		Scanner scan = new Scanner(System.in);
		do {
			printMenu();
			int menuNumber = scan.nextInt();
			switch(menuNumber) {
			case 1 :
				add();
				break;
			case 2 :
				viewAllList();
				break;
			case 3 :
				return;
			default :
				System.out.println("잘못 입력하였습니다.");
				break;	
			}
		} while(true);
	}
	
	public void printMenu() {
		System.out.println("1. 추가");
		System.out.println("2. 다 보기");
		System.out.println("3. 돌아가기");
		System.out.print("입력 : ");
	}

	public void add() {
		Scanner scan = new Scanner(System.in);
		System.out.print("추가할 연락처의 이름을 입력하십시오 : ");
		String newName = scan.nextLine();
		System.out.print("추가할 연락처의 전화번호를 입력하십시오(- 제외하여 입력) : ");
		String newNumber = scan.nextLine();
		Phonebook newPhoneBook = new Phonebook(newName, newNumber);
		phonebookData.addElement(newPhoneBook);
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}
	
	public void viewAllList() {
		int i;
		for(i = 0; phonebookData.get(i) != phonebookData.lastElement(); i++) {
			System.out.print("이름 : " + phonebookData.get(i).getName());
			System.out.println(", 전화번호 : " + phonebookData.get(i).getNumber());
		}
		System.out.print("이름 : " + phonebookData.get(i).getName());
		System.out.println(", 전화번호 : " + phonebookData.get(i).getNumber());
	}
}