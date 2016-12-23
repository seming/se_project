import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		getSavedNoteData();
	}
	
	private void getSavedNoteData() {
		String inputFilePath = "database\\"+user_id+"_noteDB.txt";
		createNewFileIfNoFile(inputFilePath);
		try {
			FileInputStream fileinputstream = new FileInputStream(inputFilePath);
			ObjectInputStream objectinputstream = new ObjectInputStream(fileinputstream);
			noteData = (Vector<Note>) objectinputstream.readObject();
			objectinputstream.close();
			fileinputstream.close();
		} catch (Exception e) {}
	}

	private void createNewFileIfNoFile(String inputfilepath) {
		File inputfile = new File(inputfilepath);
		if(!inputfile.isFile()){
			try {
				inputfile.createNewFile();
			} catch (IOException e) {}
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
  				System.out.println("잘못 입력하였습니다");
 			}
 		} while(true);
	}
	
	private void printMenu() {
 		System.out.println("1. 추가");
 		System.out.println("2. 삭제");
 		System.out.println("3. 돌아가기");
 		System.out.print("입력 : ");
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
	}

	private boolean isOutOfRange(String string) {
		int length = string.length();
		if(length>30 || length<=0)
			return true;
		else
			return false;
	}
	
	public void delete() {
		//Need refactoring
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
	}
	
	public void viewAllList() {
		if(noteData.size() == 0){
			return;
		}
		System.out.println("==============저장된 노트===============");
		System.out.println("id\t contents");
		for(int i=0;i<noteData.size();i++){
			Note noteForView = noteData.elementAt(i);
			System.out.println(i+"\t "+noteForView.getContents());
		}
		System.out.println("====================================");	
	}

	private boolean isExistingNote(int note_id) {
		if(note_id < 0)
			return false;
		if(note_id > noteData.size())
			return false;
		return true;
	}
	
	public void saveAndExit() {
		String outputFilePath = "database\\"+user_id+"_noteDB.txt";
		try {
			FileOutputStream fileoutputstream = new FileOutputStream(outputFilePath);
			ObjectOutputStream objectoutputstream = new ObjectOutputStream(fileoutputstream);
			objectoutputstream.writeObject(noteData);
			objectoutputstream.close();
			fileoutputstream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

