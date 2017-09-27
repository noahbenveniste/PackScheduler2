package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Used to test the Student class
 * @author Noah Benveniste
 * @author Daniel Mills
 * @author Kevin Hildner
 */
public class StudentTest {

	/** Valid first name */
	private static final String FIRST_NAME = "first";
	/** Valid last name */
	private static final String LAST_NAME = "last";
	/** Valid id */
	private static final String ID = "id";
	/** Valid email */
	private static final String EMAIL = "email@ncsu.edu";
	/** Valid password */
	private static final String HASHED_PASSWORD = "hashedpassword";
	/** Valid credits */
	private static final int CREDITS = 10;
	/** Max number of credits, used by constructor without int param as default value */
	private static final int MAX_CREDITS = 18;
	/** Min number of credits */
	private static final int MIN_CREDITS = 3;
	
	/**
	 * Testing constructor that has credits as an additional parameter
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		//Test that student object is not created when the first name is null
		Student s = null; //Initialize a student reference to null
		try {
		    s = new Student(null, LAST_NAME, ID, EMAIL, HASHED_PASSWORD, CREDITS);
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		//Test that student object is not created when the first name is an empty string
		s = null; //Initialize a student reference to null
		try {
		    s = new Student("", LAST_NAME, ID, EMAIL, HASHED_PASSWORD, CREDITS);
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		//Test invalid construction using ID parameter
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, null, EMAIL, HASHED_PASSWORD, CREDITS);
			fail();
		} catch (IllegalArgumentException e){
			assertNull(s);
		}
		
		s = null;
		try {
			s = new Student(FIRST_NAME, LAST_NAME, "", EMAIL, HASHED_PASSWORD, CREDITS);
			fail();
		} catch (IllegalArgumentException e){
			assertNull(s);
		}
		
		//Test for valid student object construction
		s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, HASHED_PASSWORD, CREDITS);
		try {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(HASHED_PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		} catch (IllegalArgumentException e){
			fail();
		}
	}

	/**
	 * Testing constructor that does not have credits as a parameter
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		//Test for valid student object construction
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, HASHED_PASSWORD);
		try {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(HASHED_PASSWORD, s.getPassword());
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		} catch (IllegalArgumentException e){
			fail();
		}
	}

	/**
	 * Testing setter for email field
	 */
	@Test
	public void testSetEmail() {
		//Initialize a valid student 
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, HASHED_PASSWORD);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(HASHED_PASSWORD, s.getPassword());
		assertEquals(MAX_CREDITS, s.getMaxCredits());
		
		//Testing that setting the email field to null throws an exception and doesn't change anything
		try {
			s.setEmail(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(HASHED_PASSWORD, s.getPassword());
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		}
		
		//Testing that setting the email field to an empty string throws an exception and doesn't change anything
		try {
			s.setEmail("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(HASHED_PASSWORD, s.getPassword());
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		}
		
		//Testing that setting an email without an '@' character throws an exception and doesn't change anything
		try {
			s.setEmail("invalidEmail.ncsu.edu");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(HASHED_PASSWORD, s.getPassword());
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		}
		
		//Testing that setting an email without an '.' character throws an exception and doesn't change anything
		try {
			s.setEmail("invalidEmail@ncsuedu");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(HASHED_PASSWORD, s.getPassword());
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		}
		
		//Testing that setting an email where the last '.' comes before the '@' character throws an exception and doesn't change anything
		try {
			s.setEmail("invalid.Email@ncsuedu");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(HASHED_PASSWORD, s.getPassword());
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		}
		
		//Valid email set containing only one '.' characters
		s.setEmail("validEmail@ncsu.edu");
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals("validEmail@ncsu.edu", s.getEmail());
		assertEquals(HASHED_PASSWORD, s.getPassword());
		assertEquals(MAX_CREDITS, s.getMaxCredits());
		
		//Valid email set containing multiple '.' characters
		s.setEmail("valid.Email@ncsu.edu");
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals("valid.Email@ncsu.edu", s.getEmail());
		assertEquals(HASHED_PASSWORD, s.getPassword());
		assertEquals(MAX_CREDITS, s.getMaxCredits());
	}

	/**
	 * Testing setter for password field
	 */
	@Test
	public void testSetPassword() {
		//Initialize a valid student 
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, HASHED_PASSWORD);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(HASHED_PASSWORD, s.getPassword());
		assertEquals(MAX_CREDITS, s.getMaxCredits());
		
		//Test that setting the password to null throws an exception and doesn't change anything
		try {
			s.setPassword(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(HASHED_PASSWORD, s.getPassword());
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		}
		
		//Test that setting the password to an empty string throws an exception and doesn't change anything
		try {
			s.setPassword("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(HASHED_PASSWORD, s.getPassword());
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		}
		
		//Valid password set
		s.setPassword("password");
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals("password", s.getPassword());
		assertEquals(MAX_CREDITS, s.getMaxCredits());
	}

	/**
	 * Testing setter for credits field
	 */
	@Test
	public void testSetMaxCredits() {
		//Initialize a valid student
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, HASHED_PASSWORD, CREDITS);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(HASHED_PASSWORD, s.getPassword());
		assertEquals(CREDITS, s.getMaxCredits());
		
		//Test that setting credits below 3 throws an exception and doesn't change anything
		try {
			s.setMaxCredits(2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(HASHED_PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		}

		
		//Test that setting credits above 18 throws an exception and doesn't change anything
		try {
			s.setMaxCredits(19);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(HASHED_PASSWORD, s.getPassword());
			assertEquals(CREDITS, s.getMaxCredits());
		}
		
		//Test valid credits set at lower boundary
		s.setMaxCredits(3);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(HASHED_PASSWORD, s.getPassword());
		assertEquals(MIN_CREDITS, s.getMaxCredits());
		
		//Test valid credits set at middle value
		s.setMaxCredits(7);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(HASHED_PASSWORD, s.getPassword());
		assertEquals(7, s.getMaxCredits());
		
		//Test valid credits set at upper boundary
		s.setMaxCredits(MAX_CREDITS);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(HASHED_PASSWORD, s.getPassword());
		assertEquals(MAX_CREDITS, s.getMaxCredits());
	}

	/**
	 * Testing setter for firstName field
	 */
	@Test
	public void testSetFirstName() {
		//Initialize a valid student 
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, HASHED_PASSWORD);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(HASHED_PASSWORD, s.getPassword());
		assertEquals(MAX_CREDITS, s.getMaxCredits());
		
		//Test that setting the first name to null throws an exception and doesn't change anything
		try {
			s.setFirstName(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(HASHED_PASSWORD, s.getPassword());
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		}
		
		//Test that setting the first name to an empty string throws an exception and doesn't change anything
		try {
			s.setFirstName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(HASHED_PASSWORD, s.getPassword());
			assertEquals(MAX_CREDITS, s.getMaxCredits()); 
		}
		
		//Valid first name set
		s.setFirstName("foo");
		assertEquals("foo", s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(HASHED_PASSWORD, s.getPassword());
		assertEquals(MAX_CREDITS, s.getMaxCredits());
	}

	/**
	 * Testing setter for lastName field
	 */
	@Test
	public void testSetLastName() {
		//Initialize a valid student 
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, HASHED_PASSWORD);
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals(LAST_NAME, s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(HASHED_PASSWORD, s.getPassword());
		assertEquals(MAX_CREDITS, s.getMaxCredits());
		
		//Test that setting the first name to null throws an exception and doesn't change anything
		try {
			s.setLastName(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(HASHED_PASSWORD, s.getPassword());
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		}
		
		//Test that setting the first name to an empty string throws an exception and doesn't change anything
		try {
			s.setLastName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
			assertEquals(LAST_NAME, s.getLastName());
			assertEquals(ID, s.getId());
			assertEquals(EMAIL, s.getEmail());
			assertEquals(HASHED_PASSWORD, s.getPassword());
			assertEquals(MAX_CREDITS, s.getMaxCredits());
		}
		
		//Valid first name set
		s.setLastName("bar");
		assertEquals(FIRST_NAME, s.getFirstName());
		assertEquals("bar", s.getLastName());
		assertEquals(ID, s.getId());
		assertEquals(EMAIL, s.getEmail());
		assertEquals(HASHED_PASSWORD, s.getPassword());
		assertEquals(MAX_CREDITS, s.getMaxCredits());
	}
	
	/**
	 * Testing compareTo method
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student("abc", "abc", "abc123", "email@email.com", "password");
		Student s2 = new Student("def", "def", "def123", "email@email.com", "password");
		Student s3 = new Student("efg", "efg", "efg123, ", "email@email.com", "password");
		Student s4 = new Student("def", "abc", "abc123", "email@email.com", "password");
		Student s5 = new Student("abc", "abc", "abc456", "email@email.com", "password");
		Student s6 = new Student("abc", "abc", "abc123", "email@email.com", "password");
		
		assertTrue(s1.compareTo(s2) < 0);
		assertTrue(s2.compareTo(s1) > 0);
		assertTrue(s2.compareTo(s3) < 0);
		assertTrue(s3.compareTo(s2) > 0);
		assertTrue(s1.compareTo(s3) < 0);
		assertTrue(s3.compareTo(s1) > 0);
		
		assertTrue(s1.compareTo(s4) < 0);
		assertTrue(s1.compareTo(s5) < 0);
		assertTrue(s1.compareTo(s6) == 0);
	}
	
	/**
	 * Testing equals method
	 */
	@SuppressWarnings("unlikely-arg-type") //For testing line 259 in Student class under equals() method
	@Test
	public void testEqualsObject() {
		//Create objects for testing
		String str = "Student";
		Student s0 = null;
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, HASHED_PASSWORD, CREDITS);
		Student s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, HASHED_PASSWORD, CREDITS);
		Student s3 = new Student("Foo", LAST_NAME, ID, EMAIL, HASHED_PASSWORD, CREDITS);
		Student s4 = new Student(FIRST_NAME, "Bar", ID, EMAIL, HASHED_PASSWORD, CREDITS);
		Student s5 = new Student(FIRST_NAME, LAST_NAME, "foobar", EMAIL, HASHED_PASSWORD, CREDITS);
		Student s6 = new Student(FIRST_NAME, LAST_NAME, ID, "foobar@ncsu.edu", HASHED_PASSWORD, CREDITS);
		Student s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "drowssap", CREDITS);
		Student s8 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, HASHED_PASSWORD, 5);
		Student s9 = new Student("Foo", "Bar", "foobar", "foobar@ncsu.edu", "drowssap");
		
		//Test that comparing to a null student returns false
		assertFalse(s1.equals(s0));
		
		//Test that comparing an object of a different class returns false
		assertFalse(s1.equals(str));
		
		//Test for inequality across individual fields
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
		
		//Test that two objects with different state across all fields are unequal
		assertFalse(s1.equals(s9));
		
		//Test that comparing a student to itself returns true
		assertTrue(s1.equals(s1));
		
		//Test that two objects with the same state are equal
		assertTrue(s1.equals(s2));
	}
	
	/**
	 * Testing hashCode method
	 */
	@Test
	public void testHashCode() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, HASHED_PASSWORD);
		Student s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, HASHED_PASSWORD);
		Student s3 = new Student("Foo", "Bar", "foobar", "foobar@ncsu.edu", "drowssap");
		
		//Test that two unequal objects have different hash code
		assertNotEquals(s2.hashCode(), s3.hashCode());
		
		//Test that two equal objects have the same hash code
		assertEquals(s1.hashCode(), s2.hashCode());
	}
	
	/**
	 * Testing toString method
	 */
	@Test
	public void testToString() {
		//Test toString for object constructed with credits param
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, HASHED_PASSWORD, CREDITS);
		String str1 = "first,last,id,email@ncsu.edu,hashedpassword,10";
		assertEquals(s1.toString(), str1);
		
		//Test toString for object constructed without credits param
		Student s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, HASHED_PASSWORD);
		String str2 = "first,last,id,email@ncsu.edu,hashedpassword,18";
		assertEquals(s2.toString(), str2);
	}
}
