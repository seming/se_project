import java.util.Scanner;

public class NoteManager extends DailyManageOutline {

	private String user_id;
	private Note[] noteData;
	private int current_note_size;
	private static final int MAXIMUM_SISE_OF_NOTE = 100;

	
	public NoteManager(String user_id) {
		this.user_id = user_id;
		noteData = new Note[MAXIMUM_SISE_OF_NOTE];
		//Open User's note DB Infomation & Copy into noteData
		
	}
	
	public void add() {
		Scanner sc = new Scanner(System.in);
		String new_note_contents;
		int new_note_id;
		do{
			System.out.print("��Ʈ ������ �Է��ϼ��� : ");
			new_note_contents = sc.nextLine();
		}while(isOutOfRange(new_note_contents));
		new_note_id = current_note_size++;
		noteData[new_note_id] = new_note_contents;
	}

	private boolean isOutOfRange(String string){
		int length = string.length();
		if(length>30 || length<=0)
			return true;
		else
			return false;
	}
	
	public void delete() {
		Scanner sc = new Scanner(System.in);
		System.out.print("������ ��Ʈ�� ID�� �Է��ϼ��� : ");
		int id_to_be_deleted = sc.nextInt();
		
		if(isExistingNote(id_to_be_deleted))
			coverHoleInArrayByDeletion(id_to_be_deleted);
		else
			System.out.println("�ش� ID�� ��Ʈ�� �����ϴ�");
		
		sc.close();
	}

	public void viewAllList() {

	}

	private boolean isExistingNote(int note_id){
		if(note_id<0)
			return false;
		if(noteData[note_id]==null)
			return false;
		return true;
	}
	
	private void coverHoleInArrayByDeletion(int hole_index){
		//Need to be filled!
	}
	
	
}

