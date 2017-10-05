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

	/** Invalid course name due to being an empty string */
	public static final String EMPTY_COURSE_NAME = "";

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
	public static final String INVALID_COURSE_NAME_8 = "ABCDE123";
	/** Invalid course name with invalid character at first letter index */
	public static final String INVALID_COURSE_NAME_9 = "!BC123";
	/** Invalid course name with one letter followed by an invalid character */
	public static final String INVALID_COURSE_NAME_10 = "A!C123";
	/** Invalid course name with two letters followed by an invalid character */
	public static final String INVALID_COURSE_NAME_11 = "AB!123";
	/** Invalid course name with three letters followed by an invalid character */
	public static final String INVALID_COURSE_NAME_12 = "ABC!23";
	/** Invalid course name with an invalid character following the first digit */
	public static final String INVALID_COURSE_NAME_13 = "ABC1!3";
	/** Invalid course name with an invalid character following the second digit */
	public static final String INVALID_COURSE_NAME_14 = "ABC12!";
	/** Invalid course name with an invalid character following the third digit */
	public static final String INVALID_COURSE_NAME_15 = "ABC123!";
	/** Invalid course name with an invalid character following the suffix */
	public static final String INVALID_COURSE_NAME_16 = "ABC123A!";
	/** Invalid course name with four letters followed by an invalid character */
	public static final String INVALID_COURSE_NAME_17 = "ABCD!123";
	/** Invalid course name with a digit followed by a letter */
	public static final String INVALID_COURSE_NAME_18 = "ABC1A2";
	/** Invalid course name with two digits followed by a letter */
	public static final String INVALID_COURSE_NAME_19 = "ABC12A3";

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
		try {
			assertTrue(fsm.isValid(VALID_COURSE_NAME_L2));
		} catch (InvalidTransitionException e) {
			fail();
		}

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
		try {
			assertTrue(fsm.isValid(VALID_COURSE_NAME_L4));
		} catch (InvalidTransitionException e) {
			fail();
		}

		// Test valid course name with 3 letters, transition to stateD
		try {
			assertTrue(fsm.isValid(VALID_COURSE_NAME_L3));
		} catch (InvalidTransitionException e) {
			fail();
		}

		// Test that a course name with three letters followed by an invalid character
		// throws an exception
		try {
			fsm.isValid(INVALID_COURSE_NAME_12);
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		}

	}

	/**
	 * Tests the transitions from stateLLLL to stateD
	 */
	@Test
	public void TestStateLLLLTransitions() {
		// Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();

		// Test valid course name with 4 letters, transition to stateD
		try {
			assertTrue(fsm.isValid(VALID_COURSE_NAME_L4));
		} catch (InvalidTransitionException e) {
			fail();
		}

		// Test invalid course name with 5 letters
		try {
			fsm.isValid(INVALID_COURSE_NAME_8);
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name cannot start with more than 4 letters.");
		}

		// Test that a course name with four letters followed by an invalid character
		// throws an exception
		try {
			fsm.isValid(INVALID_COURSE_NAME_17);
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		}

	}

	/**
	 * Tests the transitions from stateD to stateDD
	 */
	@Test
	public void TestStateDTransitions() {
		// Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();

		// Test a valid course name, transition to stateDD
		try {
			assertTrue(fsm.isValid(VALID_COURSE_NAME_L1));
		} catch (InvalidTransitionException e) {
			fail();
		}

		// Test an invalid course name with a single digit not followed by another
		// number
		try {
			assertFalse(fsm.isValid(INVALID_COURSE_NAME_5));
		} catch (InvalidTransitionException e) {
			fail();
		}

		// Test an invalid course name with a single digit followed by a valid character
		try {
			fsm.isValid(INVALID_COURSE_NAME_18);
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name must have 3 digits.");
		}
		// Test an invalid course name with a single digit followed by an invalid
		// character
		try {
			fsm.isValid(INVALID_COURSE_NAME_13);
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		}
	}

	/**
	 * Tests the transitions from stateDD to stateDDD
	 */
	@Test
	public void TestStateDDTransitions() {
		// Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();

		// Test a valid course name, transition to stateDDD
		try {
			assertTrue(fsm.isValid(VALID_COURSE_NAME_L1));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		// Test an invalid course name with two digits not followed by anything
		try {
			assertFalse(fsm.isValid(INVALID_COURSE_NAME_6));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
		// Test an invalid course name with two digits followed by a valid character
		try {
			fsm.isValid(INVALID_COURSE_NAME_19);
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name must have 3 digits.");
		}
		
		// Test an invalid course name with two digits followed by an invalid character
		try {
			fsm.isValid(INVALID_COURSE_NAME_14);
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		}
	}

	/**
	 * Tests the transitions from stateDDD to stateSuffix
	 */
	@Test
	public void TestStateDDDTransitions() {
		// Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();

		// Test a valid course name with three digits, isValid will return true
		try {
			assertTrue(fsm.isValid(VALID_COURSE_NAME_L3));
		} catch(InvalidTransitionException e) {
			fail();
		}
		
		// Test a valid course name that ends with a suffix, transition to stateSuffix
		try {
			assertTrue(fsm.isValid(VALID_COURSE_NAME_L4_S));
		} catch(InvalidTransitionException e) {
			fail();
		}
		
		// Test an invalid course name with 4 digits
		try {
			fsm.isValid(INVALID_COURSE_NAME_7);	
			fail();
		} catch(InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only have 3 digits.");
		}
		
		// Test an invalid course name with 3 digits followed by an invalid character
		try {
			fsm.isValid(INVALID_COURSE_NAME_15);	
			fail();
		} catch(InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		}
	}

	/**
	 * Tests the transitions from stateSuffix
	 */
	@Test
	public void TestStateSuffixTransitions() {
		// Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();

		// Test a valid course name with a suffix
		try {
			assertTrue(fsm.isValid(VALID_COURSE_NAME_L4_S));
		} catch(InvalidTransitionException e) {
			fail();
		}
		
		// Test an invalid course name with a suffix followed by another letter
		try {
			fsm.isValid(INVALID_COURSE_NAME_3);
			fail();
		} catch(InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only have a 1 letter suffix.");
		}
		
		// Test an invalid course name with a suffix followed by another number
		try {
			fsm.isValid(INVALID_COURSE_NAME_4);
			fail();
		} catch(InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name cannot contain digits after the suffix.");
		}
	}
}
