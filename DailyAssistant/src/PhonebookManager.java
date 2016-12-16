import java.util.Scanner;
import java.util.Vector;

public class PhonebookManager extends DailyManageOutline {
	private String user_id;
	private Vector<Phonebook> phonebookData;
	
	public PhonebookManager(String user_id) {
		phonebookData = new Vector<Phonebook>();	//���� ����°� �ƴ� ���Ͽ��� ������ �� �ֵ��� ��������
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
				System.out.println("�߸� �Է��Ͽ����ϴ�.");
				break;	
			}
		} while(true);
	}
	
	public void printMenu() {
		System.out.println("1. �߰�");
		System.out.println("2. �� ����");
		System.out.println("3. ���ư���");
		System.out.print("�Է� : ");
	}

	public void add() {
		Scanner scan = new Scanner(System.in);
		System.out.print("�߰��� ����ó�� �̸��� �Է��Ͻʽÿ� : ");
		String newName = scan.nextLine();
		System.out.print("�߰��� ����ó�� ��ȭ��ȣ�� �Է��Ͻʽÿ�(- �����Ͽ� �Է�) : ");
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
			System.out.print("�̸� : " + phonebookData.get(i).getName());
			System.out.println(", ��ȭ��ȣ : " + phonebookData.get(i).getNumber());
		}
		System.out.print("�̸� : " + phonebookData.get(i).getName());
		System.out.println(", ��ȭ��ȣ : " + phonebookData.get(i).getNumber());
	}
}