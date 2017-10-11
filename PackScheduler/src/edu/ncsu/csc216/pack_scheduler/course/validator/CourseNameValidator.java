package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Used to simulate an FSM for validating a course name. Uses four different
 * states (InitialState, LetterState, DigitState and SuffixState) and controls
 * the transitions between these states
 * 
 * @author Noah Benveniste
 * @author Kevin Hildner
 * @author Kristina Fialo
 *
 */
public class CourseNameValidator {

	/** Tells if the string is able to reach a valid end state */
	private boolean validEndState;
	/** Keeps track of the number of letters in the course name */
	private int letterCount;
	/** Keeps track of the number of digits in the course name */
	private int digitCount;

	private final State initialState = new InitialState();
	private final State letterState = new LetterState();
	private final State numberState = new NumberState();
	private final State suffixState = new SuffixState();

	/** Keeps track of the current state in the FSM */
	private State state;

	/**
	 * Constructor for a CourseNameValidator object
	 */
	public CourseNameValidator() {
		this.validEndState = false;
		this.letterCount = 0;
		this.digitCount = 0;
		this.state = initialState;
	}

	/**
	 * Checks that a course name contains 1-4 letters followed by 3 numbers and an
	 * optional one letter suffix
	 * 
	 * @param courseName
	 *            the course name to check for validity
	 * @return true if the course name is valid, false otherwise
	 * @throws InvalidTransitionException
	 *             if the input contains a character that is not a letter or digit
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		// Reset instance variables in case this object is being reused for a different
		// input
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
	 * Object representing a state in an FSM. This is an abstract class that will be
	 * inherited in the implementation of the four states
	 * 
	 * @author Noah Benveniste
	 * @author Kevin Hildner
	 * @author Kristina Fialo
	 *
	 */
	private abstract class State {

		/** Error message for a state object with invalid characters */
		private static final String OTHER_MSG = "Course name can only contain letters and digits.";

		/**
		 * Tells what to do from a state if the next character is a letter
		 * 
		 * @throws InvalidTransitionException
		 *             If the state is not supposed to be followed by a letter
		 */
		public abstract void onLetter() throws InvalidTransitionException;

		/**
		 * Tells what to do from a state if the next character is a digit
		 * 
		 * @throws InvalidTransitionException
		 *             If the state is not supposed to be followed by a letter
		 */
		public abstract void onDigit() throws InvalidTransitionException;

		/**
		 * Tells what to do from a state if the next character is neither a digit or a
		 * letter
		 * 
		 * @throws InvalidTransitionException
		 *             Is always thrown, as there should only be letters and numbers in
		 *             a course title
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException(OTHER_MSG);
		}

	}

	/**
	 * This is the default state in the FSM, and is the state that is initiated in
	 * the constructor
	 * 
	 * @author Noah Benveniste
	 * @author Kevin Hildner
	 * @author Kristina Fialo
	 */
	private class InitialState extends State {

		/**
		 * Transitions the state to letterState and increments the letterCount. This is
		 * used if the next character is a letter.
		 */
		@Override
		public void onLetter() {
			CourseNameValidator.this.state = letterState;
			CourseNameValidator.this.letterCount++;
		}

		/**
		 * Throws an exception, as InitialState is not to be followed by a digit.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}

	/**
	 * This state represents being on a letter
	 * 
	 * @author Noah Benveniste
	 * @author Kevin Hildner
	 * @author Kristina Fialo
	 */
	private class LetterState extends State {

		/**
		 * Keeps the state set to LetterState and increments the letterCount. This is
		 * used if the next character is another letter.
		 * 
		 * @throws InvalidTransitionException
		 *             if letterCount is greater than 3
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
		 * Changes the state to numberState and increments the digitCount. This is used
		 * if the next character is a digit.
		 */
		@Override
		public void onDigit() {
			CourseNameValidator.this.state = numberState;
			CourseNameValidator.this.digitCount++;
		}
	}

	/**
	 * This state represents being on a number. This can be a valid end state.
	 * 
	 * @author Noah Benveniste
	 * @author Kevin Hildner
	 * @author Kristina Fialo
	 */
	private class NumberState extends State {

		/**
		 * Transitions the state to suffixState and increments the letterCount. This is
		 * used if the next character is a letter.
		 * 
		 * @throws InvalidTransitionException
		 *             if digitCount is not equal to 3
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
		 * Keeps the state set to numberState and increments the digitCount. This is
		 * used if the next character is a digit.
		 * 
		 * @throws InvalidTransitionException
		 *             if the digitCount is equal to 3
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
	 * This state represents being on the suffix. This is the letter that can
	 * optionally follow the 3 numbers, and is a valid end state.
	 * 
	 * @author Noah Benveniste
	 * @author Kevin Hildner
	 * @author Kristina Fialo
	 */
	private class SuffixState extends State {

		/**
		 * Always throws an exception as SuffixState should not be followed by anything.
		 * 
		 * @throws InvalidTransitionException always
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}

		/**
		 * Always throws an exception as SuffixState should not be followed by anything.
		 * 
		 * @throws InvalidTransitionException always
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
	}
}
