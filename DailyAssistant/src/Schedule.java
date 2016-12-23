import java.io.Serializable;
import java.util.Date;

public class Schedule implements Serializable{
	private String userID;
	private int year;
	private int month;
	private int day;
	private String contents;
	

	public Schedule(String userID, Date date, String contents) {
		this.userID = userID;
		this.year = date.getYear();
		this.month = date.getMonth();
		this.day = date.getDay();
		this.contents = contents;
	}
	
	public String getUserID(){
		return userID;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
		
}
