package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
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
	 * 
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
	 * 
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException("Invalid enrollment capacity.");
		} else if (enrollmentCap < this.roll.size()){
			throw new IllegalArgumentException("New enrollment capacity cannot be less than the number of currently enrolled students.");
		} else {
			this.enrollmentCap = enrollmentCap;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getEnrollmentCap() {
		return this.enrollmentCap;
	}
}
