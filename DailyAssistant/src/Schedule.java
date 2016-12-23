import java.io.Serializable;
import java.util.Date;

public class Schedule implements Serializable{
	private int year;
	private int month;
	private int day;
	private String contents;
	
	public Schedule(int year, int month, int day , String contents) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.contents = contents;
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