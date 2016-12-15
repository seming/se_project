import java.util.Scanner;
import java.util.Vector;

public class NoteManager extends DailyManageOutline {
	
	private String user_id;
	private Vector<Note> noteData;
	private static final int MAXIMUM_SISE_OF_NOTE = 100;

	public NoteManager(String user_id) {
		this.user_id = user_id;
		noteData = new Vector<Note>(MAXIMUM_SISE_OF_NOTE);
		//Open User's note DB Infomation & Copy into noteData
	}
	
	public void add() {
		//NEED TO BE MODIFIED
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
			noteData.remove(id_to_be_deleted);
		else
			System.out.println("�ش� ID�� ��Ʈ�� �����ϴ�");
		
		sc.close();
	}

	public void viewAllList() {
		for(int i=0;i<noteData.capacity();i++){
			Note noteForView = noteData.elementAt(i);
			System.out.println("note id : "+i+"/t"+noteForView.getContents());
		}
	}

	private boolean isExistingNote(int note_id){
		if(note_id < 0)
			return false;
		if(note_id > noteData.capacity())
			return false;
		return true;
	}
	
}

