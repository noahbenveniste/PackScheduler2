package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * @author Noah Benveniste
 * @author Keving Hildner
 * @author Kristina Fialo
 *
 */
public abstract class State {
	
	public abstract void onLetter() throws InvalidTransitionException;
	
	public abstract void onDigit() throws InvalidTransitionException;
	
	public abstract void onOther() throws InvalidTransitionException;

}
