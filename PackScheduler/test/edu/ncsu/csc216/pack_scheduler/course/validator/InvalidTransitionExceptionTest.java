package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests the InvalidTransitionException
 * 
 * @author Kevin Hildner
 */
public class InvalidTransitionExceptionTest {
	/**
	 * Tests the default constructor for InvalidTransitionException
	 */
	@Test
	public void testInvalidTransitionException(){
		try {
			throw new InvalidTransitionException();
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Invalid FSM Transition.");
		}
	}
	
	/**
	 * Tests constructor for InvalidTransitionException with custom message
	 */
	@Test
	public void testInvalidTransitionExceptionMessage() {
		try {
			throw new InvalidTransitionException("Quick catch!");
		} catch (InvalidTransitionException e) {
			assertEquals(e.getMessage(), "Quick catch!");
		}
	}
}
