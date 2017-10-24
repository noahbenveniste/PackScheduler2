package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * Keeps track of and limits the roll of students in a course.
 * 
 * @author Noah Benveniste
 * @author Kevin Hildner
 */
public class CourseRoll {
	/** The list of students */
	private LinkedAbstractList<Student> roll;
	/** The number of students that this CourseRoll can enroll */
	private int enrollmentCap;
	/** The minimum number of students that a CourseRoll can enroll */
	private static final int MIN_ENROLLMENT = 10;
	/** The maximum number of students that a CourseRoll can enroll */
	private static final int MAX_ENROLLMENT = 250;

	/**
	 * Creates a new course roll.
	 * 
	 * @param enrollmentCap
	 *            the maximum number of students that will be allowed to enroll in
	 *            the course. Can be from 10-250 inclusive.
	 * @throws IllegalArgumentException
	 *             if the enrollment cap input is below ten, above 250, blank or
	 *             null
	 */
	public CourseRoll(int enrollmentCap) {
		try {
			this.setEnrollmentCap(enrollmentCap);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		roll = new LinkedAbstractList<Student>(this.getEnrollmentCap());
	}

	/**
	 * Sets the maximum number of students that are allowed to enroll in a course
	 * 
	 * @param enrollmentCap
	 *            the new maximum number of students that are allowed to enroll in
	 *            the course
	 * @throws IllegalArgumentException
	 *             if enrollementCap is less than 10, greater than 250, blank, null,
	 *             or less than the number of students who are already enrolled in
	 *             the course.
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException("Invalid enrollment capacity.");
		}
		if (roll != null && (enrollmentCap < this.roll.size())) {
			throw new IllegalArgumentException(
					"New enrollment capacity cannot be less than the number of currently enrolled students.");
		}
		this.enrollmentCap = enrollmentCap;
	}

	/**
	 * Getter for the current enrollment capacity
	 * 
	 * @return the enrollment capacity
	 */
	public int getEnrollmentCap() {
		return this.enrollmentCap;
	}

	/**
	 * Getter for the number of open seats left in the course roll
	 * 
	 * @return the number of open seats
	 */
	public int getOpenSeats() {
		return this.enrollmentCap - this.roll.size();
	}

	/**
	 * Checks if a student is able to be added to a course roll
	 * 
	 * @param s
	 *            the student who's ability to be enrolled is being checked.
	 * @return true if they are, false if there is no space left or they are already
	 *         enrolled
	 */
	public boolean canEnroll(Student s) {
		// Check that the list isn't full
		if (this.roll.size() == this.getEnrollmentCap()) {
			return false;
		}
		// Check for duplicates
		for (int i = 0; i < this.roll.size(); i++) {
			if (this.roll.get(i).equals(s)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Adds a student to the course roll
	 * 
	 * @param s
	 *            the student to be added
	 * @throws IllegalArgumentException
	 *             if the student is null, there is no more room in the course roll,
	 *             the student is already enrolled, or attempting to add the student
	 *             to the list otherwise throws an exception
	 */
	public void enroll(Student s) {
		if (this.canEnroll(s)) {
			try {
				this.roll.add(this.roll.size(), s);
			} catch (NullPointerException e) {
				throw new IllegalArgumentException(e.getMessage());
			} catch (IndexOutOfBoundsException e) {
				throw new IllegalArgumentException(e.getMessage());
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		} else {
			throw new IllegalArgumentException(
					"The student could not be added because either because the course roll is full"
							+ " or because the student is already enrolled.");
		}
	}

	/**
	 * Drops the specified student from the course roll
	 * 
	 * @param s
	 *            the student who is dropping from the course
	 * @throws IllegalArgumentException
	 *             if the student is null or removing the student from the list
	 *             otherwise generates an exception
	 */
	public void drop(Student s) {
		if (s == null) {
			throw new IllegalArgumentException("Student to remove cannot be null.");
		}
		for (int i = 0; i < this.roll.size(); i++) {
			if (s.equals(this.roll.get(i))) {
				try {
					this.roll.remove(i);
				} catch (IndexOutOfBoundsException e) {
					throw new IllegalArgumentException(e.getMessage());
				}
			}
		}
	}
}
