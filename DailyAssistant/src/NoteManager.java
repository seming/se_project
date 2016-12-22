import java.util.InputMismatchException;
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
		Scanner sc = new Scanner(System.in);
		System.out.println("새로 추가할 노트의 내용을 입력하세요.(※범주 : 1~30자)");
		String newNoteContents = sc.nextLine();
		
		if(isOutOfRange(newNoteContents)){
			System.out.println("입력크기가 범주를 초과하였습니다.");
			if(alarmWhenCancel())
				return;
			else
				add();
		}else{
			Note newNote = new Note();
			newNote.setContents(newNoteContents);
			newNote.setNote_id(noteData.size());
			noteData.add(newNote);
		}
		
		sc.close();
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
		System.out.print("삭제할 노트의 ID를 입력하세요 : ");
		try{
			int id_to_be_deleted = sc.nextInt();
			if(isExistingNote(id_to_be_deleted)){
				if(alarmWhenDelete())
					noteData.remove(id_to_be_deleted);
				setPopUpWindow("노트" + id_to_be_deleted + "가 삭제되었습니다.");
			}else
				System.out.println("해당 ID의 노트가 없습니다");

		}catch(InputMismatchException ime){
			System.out.println("정상적인 ID의 형태가 아닙니다.");
			if(alarmWhenCancel())
				return;
			else
				delete();
		}
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

