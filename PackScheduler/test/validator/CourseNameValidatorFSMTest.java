package validator;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidatorFSM;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Tests CourseNameValidatorFSM's ability to transition out from each of its states, and to 
 * validate a proper course name
 * 
 * @author Kevin Hildner
 */
public class CourseNameValidatorFSMTest {
	/** Invalid course name due to non digit */
	public static final String INVALID_COURSE_NAME_1 = "CSC!16";
	/** Invalid course name due to starting with a digit */
	public static final String INVALID_COURSE_NAME_2 = "2CSC16";
	
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
	 * 
	 */
	@Test
	public void TestInitialStateTransitions() {
		//Test 
	}
	
	/**
	 * 
	 */
	@Test
	public void TestInvalidTransitions() {
		//Construct a CourseNameValidatorFSM to be used throughout the tests
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
		
		//Test input with an invalid character
		try {
			fsm.isValid(INVALID_COURSE_NAME_1);
			fail();
		} catch(InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		}
		
	}
}
