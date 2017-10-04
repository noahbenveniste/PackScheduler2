package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * @author Noah Benveniste
 * @author Kevin Hildner
 * @author Kristina Fialo
 *
 */
public class InvalidTransitionException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	/** Default return message for ConflictException */
	private static final String DEFAULT_MSG = "Invalid FSM Transition.";
	
	/**
	 * Main constructor; uses a custom input exception message
	 * @param customMsg the user-specified exception message
	 */
	public InvalidTransitionException(String customMsg) {
		super(customMsg);
	}
	
	/**
	 * Constructor for use with default message
	 */
	public InvalidTransitionException() {
		this(DEFAULT_MSG);
	}
}
