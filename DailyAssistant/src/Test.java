import static org.junit.Assert.*;

import java.util.Scanner;

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
	
	@org.junit.Test
	public void testCheckIdInformationSame() {
		AccountChanger accountChanger = new AccountChanger("0000", "0000");
		assertTrue(accountChanger.checkIdInformationSame("asdf", "asdf"));
		assertFalse(accountChanger.checkIdInformationSame("qwer", "asdf"));
	}
	
	@org.junit.Test
	public void testCheckLength() {
		AccountManager accountManager = new AccountManager();
		assertTrue(accountManager.checkLength("asdf"));
		assertFalse(accountManager.checkLength("qwertyuiopasdfghjkl"));
	
	@org.junit.Test
	public void testIsOutOfRange() {
		NoteManager notemanager = new NoteManager("1111");
		assertTrue(notemanager.isOutOfRange(""));
		assertTrue(notemanager.isOutOfRange("123456789012345678901234567890"));
		assertFalse(notemanager.isOutOfRange("12345678901234567890123456789"));
	}
	
	@org.junit.Test
	public void testIsCorrectFormOfAnswer() {
		NoteManager notemanager = new NoteManager("1111");
		assertTrue(notemanager.isCorrectFormOfAnswer(1));
		assertTrue(notemanager.isCorrectFormOfAnswer(2));
		assertFalse(notemanager.isCorrectFormOfAnswer(4));
	}
}
