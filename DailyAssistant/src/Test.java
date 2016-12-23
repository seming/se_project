import static org.junit.Assert.*;
public class Test {
	
	@org.junit.Test
	public void testCheckNumberAndModifyIfOutOfRange() {
		PhonebookManager phonebookManager = new PhonebookManager("0000");
		assertEquals("01234567891", phonebookManager.checkNumberAndModifyIfOutOfRange("012345678912345"));
		assertEquals("0212345678", phonebookManager.checkNumberAndModifyIfOutOfRange("0212345678"));
		assertEquals("12341234123", phonebookManager.checkNumberAndModifyIfOutOfRange("1234123412341234"));
	}
	
	@org.junit.Test
	public void testIsEmptyNameOrEmptyNumber() {
		PhonebookManager phonebookManager = new PhonebookManager("0000");
		assertFalse(phonebookManager.isEmptyNameOrEmptyNumber("asdf", "02123456780"));
		assertTrue(phonebookManager.isEmptyNameOrEmptyNumber("", "0212345678"));
		assertTrue(phonebookManager.isEmptyNameOrEmptyNumber("John", ""));
	}
	
	@org.junit.Test
	public void testDayIsOutOfRange() {
		ScheduleManager scheduleManager = new ScheduleManager("0000");
		assertTrue(scheduleManager.DayIsOutOfRange(2010, 9, 31));
		assertFalse(scheduleManager.DayIsOutOfRange(2010, 9, 30));
		assertFalse(scheduleManager.DayIsOutOfRange(2010, 7, 31));
	}
	
	@org.junit.Test	
	public void testIsSkipped() {
		ScheduleManager scheduleManager = new ScheduleManager("0000");
		assertTrue(scheduleManager.isSkipped(-1));
		assertFalse(scheduleManager.isSkipped(0));		
	}
}
