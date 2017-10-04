package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * @author Noah Benveniste
 * @author Keving Hildner
 * @author Kristina Fialo
 *
 */
public abstract class State {
	
	/** */
	private final String OTHER_MSG = "Course name can only contain letters and digits.";
	
	/**
	 * 
	 * @throws InvalidTransitionException
	 */
	public abstract void onLetter() throws InvalidTransitionException;
	
	/**
	 * 
	 * @throws InvalidTransitionException
	 */
	public abstract void onDigit() throws InvalidTransitionException;
	
	/**
	 * 
	 * @throws InvalidTransitionException
	 */
	public void onOther() throws InvalidTransitionException {
		throw new InvalidTransitionException(OTHER_MSG);	
	}

}
