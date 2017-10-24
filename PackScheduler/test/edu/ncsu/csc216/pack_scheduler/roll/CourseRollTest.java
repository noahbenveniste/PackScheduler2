/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests the CourseRoll class
 * 
 * @author Kevin Hildner
 *
 */
public class CourseRollTest {

	/**
	 * Tests the constructor
	 */
	@Test
	public void testCourseRoll() {
		// Test creating a valid course roll
		CourseRoll c = new CourseRoll(15);
		assertEquals(15, c.getEnrollmentCap());
		// Test creating a course roll with enrollment cap < min enrollment
		try {
			new CourseRoll(9);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid enrollment capacity.", e.getMessage());
		}
		// Test creating a course roll with enrollment cap > max enrollment
		try {
			new CourseRoll(251);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid enrollment capacity.", e.getMessage());
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll#setEnrollmentCap(int)}.
	 */
	@Test
	public void testSetEnrollmentCap() {
		// Create a new course Roll
		CourseRoll c = new CourseRoll(15);

		// Add students to the roll
		for (int i = 0; i < 15; i++) {
			c.enroll(new Student("first", "last", "ID" + i, "email@a.com", "pw"));
		}

		// Test setting the enrollment cap below the enrolled number of students
		try {
			c.setEnrollmentCap(10);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("New enrollment capacity cannot be less than the number of currently enrolled students.",
					e.getMessage());
		}
		assertEquals(15, c.getEnrollmentCap());

		// Test setting the enrollment cap to its current value
		c.setEnrollmentCap(15);
		assertEquals(15, c.getEnrollmentCap());

		// Test setting the enrollment cap to a higher value
		c.setEnrollmentCap(50);
		assertEquals(50, c.getEnrollmentCap());

		// Test setting the enrollment cap to a lower value that is still above the
		// number of students enrolled
		c.setEnrollmentCap(25);
		assertEquals(25, c.getEnrollmentCap());

	}

	/**
	 * Test the enroll method
	 */
	@Test
	public void testEnroll() {
		// Create a new course roll
		CourseRoll c = new CourseRoll(15);
		assertEquals(15, c.getOpenSeats());

		// Test adding students to the roll
		for (int i = 0; i < 14; i++) {
			c.enroll(new Student("first", "last", "ID" + i, "email@a.com", "pw"));
		}
		assertEquals(1, c.getOpenSeats());

		// Test adding a null student
		try {
			c.enroll(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot add null elements.", e.getMessage());
		}
		// Test adding a duplicate student
		try {
			c.enroll(new Student("first", "last", "ID1", "email@a.com", "pw"));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(
					"The student could not be added because either because the course roll is full or because the student is already enrolled.",
					e.getMessage());
		}
		// Test adding when the list is full
		c.enroll(new Student("first", "last", "ID15", "email@a.com", "pw"));
		try {
			c.enroll(new Student("first", "last", "ID16", "email@a.com", "pw"));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(
					"The student could not be added because either because the course roll is full or because the student is already enrolled.",
					e.getMessage());
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll#drop(edu.ncsu.csc216.pack_scheduler.user.Student)}.
	 */
	@Test
	public void testDrop() {
		// Create a new course roll
		CourseRoll c = new CourseRoll(15);
		assertEquals(15, c.getOpenSeats());

		// Add students to the roll
		for (int i = 0; i < 14; i++) {
			c.enroll(new Student("first", "last", "ID" + i, "email@a.com", "pw"));
		}
		assertEquals(1, c.getOpenSeats());
		// Try dropping a student from the beginning of the list
		c.drop(new Student("first", "last", "ID1", "email@a.com", "pw"));
		assertEquals(2, c.getOpenSeats());
		// Try dropping a student from the end of the list
		c.drop(new Student("first", "last", "ID13", "email@a.com", "pw"));
		assertEquals(3, c.getOpenSeats());
		// Try dropping a student from the middle of the list
		c.drop(new Student("first", "last", "ID5", "email@a.com", "pw"));
		assertEquals(4, c.getOpenSeats());
		// Try dropping a student that isn't on the roll
		c.drop(new Student("first", "last", "ID25", "email@a.com", "pw"));
		assertEquals(4, c.getOpenSeats());
		// Try calling drop on a null student
		try {
			c.drop(null);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Student to remove cannot be null.", e.getMessage());
		}
		
	}

}
