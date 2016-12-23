import static org.junit.Assert.*;
import org.junit.Test;

public class PhonebookManagerTest {
	
	@Test
	public void testCheckNumberAndModifyIfOutOfRange() {
		PhonebookManager phonebookManager = new PhonebookManager("0000");
		assertEquals("01234567891", phonebookManager.checkNumberAndModifyIfOutOfRange("012345678912345"));
		assertEquals("0212345678", phonebookManager.checkNumberAndModifyIfOutOfRange("0212345678"));
		assertEquals("12341234123", phonebookManager.checkNumberAndModifyIfOutOfRange("1234123412341234"));
	}
	
	@Test
	public void testIsEmptyNameOrEmptyNumber() {
		PhonebookManager phonebookManager = new PhonebookManager("0000");
		assertFalse(phonebookManager.isEmptyNameOrEmptyNumber("asdf", "02123456780"));
		assertTrue(phonebookManager.isEmptyNameOrEmptyNumber("", "0212345678"));
		assertTrue(phonebookManager.isEmptyNameOrEmptyNumber("John", ""));
	}
}
