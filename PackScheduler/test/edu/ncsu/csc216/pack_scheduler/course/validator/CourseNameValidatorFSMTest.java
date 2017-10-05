package edu.ncsu.csc216.pack_scheduler.course.validator;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidatorFSM;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Tests CourseNameValidatorFSM's ability to transition out from each of its
 * states, and to validate a proper course name
 * 
 * @author Kevin Hildner
 */
public class CourseNameValidatorFSMTest {
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
	public static final String INVALID_COURSE_NAME_10 = "A!C123";
	public static final String INVALID_COURSE_NAME_11 = "AB!123";
	public static final String INVALID_COURSE_NAME_12 = "ABC!23";
	public static final String INVALID_COURSE_NAME_13 = "ABC1!3";
	public static final String INVALID_COURSE_NAME_14 = "ABC12!";
	public static final String INVALID_COURSE_NAME_15 = "ABC123!";
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
	 * Tests the transitions from initialState to stateL and stateD
	 */
	@Test
	public void TestInitialStateTransitions() {
		// Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();

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
	 * Tests the transitions from stateL to stateLL and stateD
	 */
	@Test
	public void TestStateLTransitions() {
		// Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();

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
		try {
			fsm.isValid(INVALID_COURSE_NAME_10);
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		}

	}

	/**
	 * Tests the transitions from stateLL to stateLLL and stateD
	 */
	@Test
	public void TestStateLLTransitions() {
		// Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();

		// Test valid course name with 3 letters, transition to stateLLL
		try {
			assertTrue(fsm.isValid(VALID_COURSE_NAME_L3));
		} catch (InvalidTransitionException e) {
			fail();
		}

		// Test valid course name with 2 letters, transition to stateD

		// Test that a course name with two letters followed by an invalid character
		// throws an exception
		try {
			fsm.isValid(INVALID_COURSE_NAME_11);
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		}
	}

	/**
	 * Tests the transitions from stateLLL to stateLLLL and stateD
	 */
	@Test
	public void TestStateLLLTransitions() {
		// Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();

		// Test valid course name with 4 letters, transition to stateLLLL

		// Test that a course name with three letters followed by an invalid character
		// throws an exception

	}

	/**
	 * Tests the transitions from stateLLLL to stateD
	 */
	@Test
	public void TestStateLLLLTransitions() {
		// Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();

		// Test valid course name with 4 letters, transition to stateD

		// Test invalid course name with 5 letters

		// Test that a course name with four letters followed by an invalid character
		// throws an exception

	}

	/**
	 * Tests the transitions from stateD to stateDD
	 */
	@Test
	public void TestStateDTransitions() {
		// Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();

		// Test a valid course name, transition to stateDD

		// Test an invalid course name with a single digit followed by a valid character

		// Test an invalid course name with a single digit followed by an invalid
		// character

	}

	/**
	 * Tests the transitions from stateDD to stateDDD
	 */
	@Test
	public void TestStateDDTransitions() {
		// Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();

		// Test a valid course name, transition to stateDDD

		// Test an invalid course name with two digits followed by a valid character

		// Test an invalid course name with two digits followed by an invalid character
	}

	/**
	 * Tests the transitions from stateDDD to stateSuffix
	 */
	@Test
	public void TestStateDDDTransitions() {
		// Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();

		// Test a valid course name, isValid will return true

		// Test a valid course name that ends with a suffix, isValid will return true

		// Test an invalid course name with 4 digits

		// Test an invalid course name with 3 digits followed by an invalid character

		// Test an invalid course name with 3 digits followed by a space
	}

	/**
	 * Tests the transitions from stateSuffix
	 */
	@Test
	public void TestStateSuffixTransitions() {
		// Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
		
		// Test a valid course name with a suffix
		
		// Test an invalid course name with a suffix followed by another letter
		
		// Test an invalid course name with a suffix followed by another number
		
	}
}
