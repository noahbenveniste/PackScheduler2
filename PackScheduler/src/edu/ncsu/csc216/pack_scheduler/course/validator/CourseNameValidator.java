package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * @author Noah Benveniste
 * @author Kevin Hildner
 * @author Kristina Fialo
 *
 */
public class CourseNameValidator {

	/** Keeps track of whether */
	private boolean validEndState;
	/** */
	private int letterCount;
	/** */
	private int digitCount;
	
	private final State initialState = new InitialState();
	private final State letterState = new LetterState();
	private final State numberState = new NumberState();
	private final State suffixState = new SuffixState();
	
	/** */
	private State state;
	
	/**
	 * 
	 */
	public CourseNameValidator() {
		this.validEndState = false;
		this.letterCount = 0;
		this.digitCount = 0;
		this.state = initialState;
	}
	
	/**
	 * Checks that a course name contains 1-4 letters followed by 3 numbers and an optional
	 * one letter suffix
	 * @param courseName the course name to check for validity
	 * @return true if the course name is valid, false otherwise
	 * @throws InvalidTransitionException if the input contains a character that is not a letter or digit
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		//Reset instance variables in case this object is being reused for a different input
		this.validEndState = false;
		this.letterCount = 0;
		this.digitCount = 0;
		this.state = initialState;
		
		for (int i = 0; i < courseName.length(); i++) {
			if (Character.isLetter(courseName.charAt(i))) {
				state.onLetter();
			} else if (Character.isDigit(courseName.charAt(i))) {
				state.onDigit();
			} else {
				state.onOther();
			}
		}
		if (state.equals(numberState)) {
			this.validEndState = (digitCount == 3 && letterCount >= 1 && letterCount <= 4);
		} else if (state.equals(suffixState)) {
			this.validEndState = (digitCount == 3 && letterCount >= 2 && letterCount <= 5);
		}
		return this.validEndState;
	}
	
	/**
	 * @author Noah Benveniste
	 * @author Keving Hildner
	 * @author Kristina Fialo
	 *
	 */
	private abstract class State {
		
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
	
	/**
	 * @author Noah Benveniste
	 * @author Kevin Hildner
	 * @author Kristina Fialo
	 */
	private class InitialState extends State {
	
		/**
		 * 
		 */
		@Override
		public void onLetter() {
			CourseNameValidator.this.state = letterState;
			CourseNameValidator.this.letterCount++;
		}
	
		/**
		 * 
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}

	/**
	 * @author Noah Benveniste
	 * @author Kevin Hildner
	 * @author Kristina Fialo
	 */
	private class LetterState extends State {

		/**
		 * 
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (CourseNameValidator.this.letterCount >= 4) {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			} else {
				CourseNameValidator.this.state = letterState;
				CourseNameValidator.this.letterCount++;
			}
		}

		/**
		 * 
		 */
		@Override
		public void onDigit() {
			CourseNameValidator.this.state = numberState;
			CourseNameValidator.this.digitCount++;
		}
	}
	
	/**
	 * @author Noah Benveniste
	 * @author Kevin Hildner
	 * @author Kristina Fialo
	 */
	private class NumberState extends State {
	
		/**
		 * 
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (CourseNameValidator.this.digitCount == 3) {
				CourseNameValidator.this.state = suffixState;
				CourseNameValidator.this.letterCount++;
			} else {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}
	
		/**
		 * 
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			if (CourseNameValidator.this.digitCount == 3) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			} else {
				CourseNameValidator.this.state = numberState;
				CourseNameValidator.this.digitCount++;
			}
		}
	}

	/**
	 * @author Noah Benveniste
	 * @author Kevin Hildner
	 * @author Kristina Fialo
	 */
	private class SuffixState extends State {

		/**
		 * 
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}

		/**
		 * 
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
	}
}
