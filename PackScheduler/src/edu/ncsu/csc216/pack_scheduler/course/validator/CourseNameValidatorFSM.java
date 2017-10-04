package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * @author Noah Benveniste
 * @author Kevin Hildner
 * @author Kristina Fialo
 *
 */
public class CourseNameValidatorFSM {

	/** */
	private boolean validEndState;
	/** */
	private int letterCount;
	/** */
	private int digitCount;
	
	private final State initialState = new InitialState();
	private final State letterState = new LetterState();
	private final State numberState = new NumberState();
	private final State suffixState = new SuffixState();
	
	private final String OTHER_MSG = "Course name can only contain letters and digits.";
	
	/** */
	private State state;
	
	/** */
	private String courseName;
	
	/**
	 * 
	 */
	public CourseNameValidatorFSM() {
		this.validEndState = false;
		this.letterCount = 0;
		this.digitCount = 0;
		this.state = initialState;
	}
	
	/**
	 * 
	 * @param courseName
	 * @return
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		for (int i = 0; i < courseName.length(); i++) {
			if (Character.isLetter(courseName.charAt(i))) {
				state.onLetter();
			} else if (Character.isDigit(courseName.charAt(i))) {
				state.onDigit();
			} else {
				state.onOther();
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @author Noah
	 *
	 */
	private class InitialState extends State {
	
		/**
		 * 
		 */
		@Override
		public void onLetter() {
			CourseNameValidatorFSM.this.state = letterState;
		}
	
		/**
		 * 
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException();
		}
	
		/**
		 * 
		 */
		@Override
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException(OTHER_MSG);	
		}
	}

	/**
	 * 
	 * @author Noah
	 *
	 */
	private class LetterState extends State {

		/**
		 * 
		 */
		@Override
		public void onLetter() {
			CourseNameValidatorFSM.this.state = letterState;
		}

		/**
		 * 
		 */
		@Override
		public void onDigit() {
			CourseNameValidatorFSM.this.state = numberState;
		}

		/**
		 * 
		 */
		@Override
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException(OTHER_MSG);	
		}
		
	}
	
	/**
	 * 
	 * @author Noah
	 *
	 */
	private class NumberState extends State {
	
		/**
		 * 
		 */
		@Override
		public void onLetter() {
			CourseNameValidatorFSM.this.state = suffixState;
		}
	
		/**
		 * 
		 */
		@Override
		public void onDigit() {
			CourseNameValidatorFSM.this.state = numberState;
		}
	
		/**
		 * 
		 */
		@Override
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException(OTHER_MSG);	
		}
	}

	/**
	 * 
	 * @author Noah
	 *
	 */
	private class SuffixState extends State {

		/**
		 * 
		 */
		@Override
		public void onLetter() throws InvalidTransitionException{
			throw new InvalidTransitionException();
		}

		/**
		 * 
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException();
		}

		/**
		 * 
		 */
		@Override
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException(OTHER_MSG);	
			
		}
	}
}
