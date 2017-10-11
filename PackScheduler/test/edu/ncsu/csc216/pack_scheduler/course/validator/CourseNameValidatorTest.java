package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Whitebox tests for the class CourseNameValidator.java
 * 
 * @author Noah Benveniste
 * @author Kevin Hildner
 * @author Kristina Fialo
 *
 */
public class CourseNameValidatorTest {

	/** Invalid course name due to non digit */
	public static final String INVALID_COURSE_NAME_1 = "CSC!16";
	/** Invalid course name due to starting with a digit */
	public static final String INVALID_COURSE_NAME_2 = "2CSC16";
	/** Invalid course name due to letter following suffix */
	public static final String INVALID_COURSE_NAME_3 = "CSC216AB";
	/** Invalid course name due to digit following suffix */
	public static final String INVALID_COURSE_NAME_4 = "CSC216A1";
	/** Invalid course name due to only having 1 digit */
	public static final String INVALID_COURSE_NAME_5 = "CSC1";
	/** Invalid course name due to only having 2 digits */
	public static final String INVALID_COURSE_NAME_6 = "CSC12";
	/** Invalid course name due to having 4 digits */
	public static final String INVALID_COURSE_NAME_7 = "CSC1234";
	/** Invalid course name due to having 5 letters */
	public static final String INVALID_COURSE_NAME_8 = "ABCD123";
	/** Invalid course name with invalid character at first letter index */
	public static final String INVALID_COURSE_NAME_9 = "!BC123";
	/** Invalid course name due to character followed by invalid character */
	public static final String INVALID_COURSE_NAME_10 = "A!C123";
	/**
	 * Invalid course name due to two characters followed by an invalid character
	 */
	public static final String INVALID_COURSE_NAME_11 = "AB!123";
	/**
	 * Invalid course name due to three characters followed by an invalid character
	 */
	public static final String INVALID_COURSE_NAME_12 = "ABC!23";
	/** Invalid course name due to an invalid character following the first digit */
	public static final String INVALID_COURSE_NAME_13 = "ABC1!3";
	/**
	 * Invalid course name due to an invalid character following the second digit
	 */
	public static final String INVALID_COURSE_NAME_14 = "ABC12!";
	/** Invalid course name due to an invalid character following the third digit */
	public static final String INVALID_COURSE_NAME_15 = "ABC123!";
	/** Invalid course name due to an invalid character following the suffix */
	public static final String INVALID_COURSE_NAME_16 = "ABC123A!";

	/** Invalid course name due to being an empty string */
	public static final String EMPTY_COURSE_NAME = "";

	/** Valid course name with 1 letter, no suffix */
	public static final String VALID_COURSE_NAME_L1 = "A216";
	/** Valid course name with 2 letters, no suffix */
	public static final String VALID_COURSE_NAME_L2 = "AG456";
	/** Valid course name with 3 letters, no suffix */
	public static final String VALID_COURSE_NAME_L3 = "BUS901";
	/** Valid course name with 4 letters, no suffix */
	public static final String VALID_COURSE_NAME_L4 = "CSCB111";
	/** Valid course name with 4 letters, and a suffix */
	public static final String VALID_COURSE_NAME_L4_S = "CSCB123C";

	/**
	 * Tests the transitions from the initial state
	 */
	@Test
	public void initialStateTransitionsTest() {
		// Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidator fsm = new CourseNameValidator();

		// Test invalid course name starting with a digit
		try {
			fsm.isValid(INVALID_COURSE_NAME_2);
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name must start with a letter.");
		}

		// Test invalid course name starting with an invalid character
		try {
			fsm.isValid(INVALID_COURSE_NAME_9);
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		}

		// Test valid course name starting with one letter, transition to stateL
		try {
			assertTrue(fsm.isValid(VALID_COURSE_NAME_L1));
		} catch (InvalidTransitionException e) {
			fail();
		}

		// Test that an empty string returns false
		try {
			assertFalse(fsm.isValid(EMPTY_COURSE_NAME));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}

	/**
	 * Tests the transitions from the first letter state
	 */
	@Test
	public void stateLTransitionsTest() {
		// Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidator fsm = new CourseNameValidator();

		// Test valid course name with 2 letters, transition to stateLL
		try {
			assertTrue(fsm.isValid(VALID_COURSE_NAME_L2));
		} catch (InvalidTransitionException e) {
			fail();
		}

		// Test valid course name with 1 letter, transition to stateD
		try {
			assertTrue(fsm.isValid(VALID_COURSE_NAME_L1));
		} catch (InvalidTransitionException e) {
			fail();
		}

		// Test that a course name with a letter followed by an invalid character throws
		// an exception

	}

	/**
	 * Tests the transitions from the second letter state
	 */
	@Test
	public void stateLLTransitionsTest() {
		// Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidator fsm = new CourseNameValidator();

		// Test valid course name with 3 letters, transition to stateLLL
		try {
			assertTrue(fsm.isValid(VALID_COURSE_NAME_L3));
		} catch (InvalidTransitionException e) {
			fail();
		}
	}

	/**
	 * Tests the transitions from the third letter state
	 */
	@Test
	public void invalidTransitionsTest() {
		// Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidator fsm = new CourseNameValidator();

		// Test input with an invalid character
		try {
			fsm.isValid(INVALID_COURSE_NAME_1);
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		}
	}
}
