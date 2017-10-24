package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Unit tests for Schedule
 * 
 * @author Noah Benveniste
 * @author Kevin Hildner
 */
public class ScheduleTest {

	/**
	 * Tests the constructor for a schedule object
	 */
	@Test
	public void testSchedule() {
		// Create a new schedule
		Schedule s = new Schedule();
		assertEquals("My Schedule", s.getTitle());
		assertEquals(0, s.getScheduledCourses().length);
	}

	/**
	 * Tests the addCourse method
	 */
	@Test
	public void testAddCourseToSchedule() {
		// Create a new schedule
		Schedule s = new Schedule();

		// Create new courses to add to the schedule
		Course c1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 1330, 1445);
		Course c2 = new Course("BW216", "Basket Weaving Concepts", "001", 3, "kwhildne", 10, "TH", 1200, 1300);
		Course c3 = new Course("CSC316", "Data structures", "002", 3, "jdoe", 10, "MW", 1200, 1300);
		Course c4 = new Course("ECE331", "Principles of Electrical Engineering", "003", 3, "JJdoe", 10, "TH", 1100,
				1230);
		Course c5 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 1330, 1445);

		// Test adding valid courses
		assertTrue(s.addCourseToSchedule(c1));
		assertTrue(s.addCourseToSchedule(c2));
		assertTrue(s.addCourseToSchedule(c3));

		// Test trying to add a conflicting course
		try {
			s.addCourseToSchedule(c4);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The course cannot be added due to a conflict.", e.getMessage());
		}

		// Test trying to add a duplicate course
		try {
			s.addCourseToSchedule(c5);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("You are already enrolled in CSC216", e.getMessage());
		}
	}

	/**
	 * Tests the removeCourseFromSchedule method
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		// Create a new schedule
		Schedule s = new Schedule();

		// Create new courses to add to the schedule
		Course c1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 1330, 1445);
		Course c2 = new Course("BW216", "Basket Weaving Concepts", "001", 3, "kwhildne", 10, "TH", 1200, 1300);
		Course c3 = new Course("CSC316", "Data structures", "002", 3, "jdoe", 10, "MW", 1200, 1300);
		Course c4 = new Course("ECE331", "Principles of Electrical Engineering", "003", 3, "JJdoe", 10, "TH", 1100,
				1230);

		// Add the courses to the schedule
		s.addCourseToSchedule(c1);
		s.addCourseToSchedule(c2);
		s.addCourseToSchedule(c3);

		String[][] courses = s.getScheduledCourses();

		assertEquals(3, courses.length);
		assertEquals("CSC216", courses[0][0]);
		assertEquals("001", courses[0][1]);
		assertEquals("Programming Concepts - Java", courses[0][2]);
		assertEquals("MW 1:30PM-2:45PM", courses[0][3]);
		assertEquals("BW216", courses[1][0]);
		assertEquals("001", courses[1][1]);
		assertEquals("Basket Weaving Concepts", courses[1][2]);
		assertEquals("TH 12:00PM-1:00PM", courses[1][3]);
		assertEquals("CSC316", courses[2][0]);
		assertEquals("002", courses[2][1]);
		assertEquals("Data structures", courses[2][2]);
		assertEquals("MW 12:00PM-1:00PM", courses[2][3]);

		// Try removing a course that is not on the list
		assertFalse(s.removeCourseFromSchedule(c4));

		courses = s.getScheduledCourses();
		assertEquals(3, courses.length);
		assertEquals("CSC216", courses[0][0]);
		assertEquals("001", courses[0][1]);
		assertEquals("Programming Concepts - Java", courses[0][2]);
		assertEquals("MW 1:30PM-2:45PM", courses[0][3]);
		assertEquals("BW216", courses[1][0]);
		assertEquals("001", courses[1][1]);
		assertEquals("Basket Weaving Concepts", courses[1][2]);
		assertEquals("TH 12:00PM-1:00PM", courses[1][3]);
		assertEquals("CSC316", courses[2][0]);
		assertEquals("002", courses[2][1]);
		assertEquals("Data structures", courses[2][2]);
		assertEquals("MW 12:00PM-1:00PM", courses[2][3]);

		// Try removing a course from the middle of the list
		assertTrue(s.removeCourseFromSchedule(c2));

		courses = s.getScheduledCourses();
		assertEquals(2, courses.length);
		assertEquals("CSC216", courses[0][0]);
		assertEquals("001", courses[0][1]);
		assertEquals("Programming Concepts - Java", courses[0][2]);
		assertEquals("MW 1:30PM-2:45PM", courses[0][3]);
		assertEquals("CSC316", courses[1][0]);
		assertEquals("002", courses[1][1]);
		assertEquals("Data structures", courses[1][2]);
		assertEquals("MW 12:00PM-1:00PM", courses[1][3]);

		s.addCourseToSchedule(c2);

		// Try removing a course from the front of the list
		assertTrue(s.removeCourseFromSchedule(c1));

		courses = s.getScheduledCourses();
		assertEquals(2, courses.length);
		assertEquals("CSC316", courses[0][0]);
		assertEquals("002", courses[0][1]);
		assertEquals("Data structures", courses[0][2]);
		assertEquals("MW 12:00PM-1:00PM", courses[0][3]);
		assertEquals("BW216", courses[1][0]);
		assertEquals("001", courses[1][1]);
		assertEquals("Basket Weaving Concepts", courses[1][2]);
		assertEquals("TH 12:00PM-1:00PM", courses[1][3]);

		// Try removing a course form the end of the list
		assertTrue(s.removeCourseFromSchedule(c2));

		courses = s.getScheduledCourses();
		assertEquals(1, courses.length);
		assertEquals("CSC316", courses[0][0]);
		assertEquals("002", courses[0][1]);
		assertEquals("Data structures", courses[0][2]);
		assertEquals("MW 12:00PM-1:00PM", courses[0][3]);
	}

	/**
	 * Tests the ResetSchedule method
	 */
	@Test
	public void testResetSchedule() {
		// Create a new schedule
		Schedule s = new Schedule();

		// Create new courses to add to the schedule
		Course c1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 1330, 1445);
		Course c2 = new Course("BW216", "Basket Weaving Concepts", "001", 3, "kwhildne", 10, "TH", 1200, 1300);
		Course c3 = new Course("CSC316", "Data structures", "002", 3, "jdoe", 10, "MW", 1200, 1300);

		// Add the courses to the schedule
		s.addCourseToSchedule(c1);
		s.addCourseToSchedule(c2);
		s.addCourseToSchedule(c3);

		String[][] courses = s.getScheduledCourses();

		assertEquals(3, courses.length);

		// Try resetting the schedule
		s.resetSchedule();

		courses = s.getScheduledCourses();

		assertEquals(0, courses.length);
	}

	/**
	 * Test the getScheduledCourses method
	 */
	@Test
	public void testGetScheduledCourses() {
		// Create a new schedule
		Schedule s = new Schedule();

		// Create new courses to add to the schedule
		Course c1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 1330, 1445);
		Course c2 = new Course("BW216", "Basket Weaving Concepts", "001", 3, "kwhildne", 10, "TH", 1200, 1300);
		Course c3 = new Course("CSC316", "Data structures", "002", 3, "jdoe", 10, "MW", 1200, 1300);

		// Add the courses to the schedule
		s.addCourseToSchedule(c1);
		s.addCourseToSchedule(c2);
		s.addCourseToSchedule(c3);

		// Get the string array of scheduled courses
		String[][] courses = s.getScheduledCourses();

		assertEquals(3, courses.length);
		assertEquals("CSC216", courses[0][0]);
		assertEquals("001", courses[0][1]);
		assertEquals("Programming Concepts - Java", courses[0][2]);
		assertEquals("MW 1:30PM-2:45PM", courses[0][3]);
		assertEquals("BW216", courses[1][0]);
		assertEquals("001", courses[1][1]);
		assertEquals("Basket Weaving Concepts", courses[1][2]);
		assertEquals("TH 12:00PM-1:00PM", courses[1][3]);
		assertEquals("CSC316", courses[2][0]);
		assertEquals("002", courses[2][1]);
		assertEquals("Data structures", courses[2][2]);
		assertEquals("MW 12:00PM-1:00PM", courses[2][3]);
	}

	/**
	 * Tests the setTitle method
	 */
	@Test
	public void testSetTitle() {
		// Create a new schedule
		Schedule s = new Schedule();

		assertEquals("My Schedule", s.getTitle());

		// Try setting the title
		s.setTitle("mY sCheDulE");

		assertEquals("mY sCheDulE", s.getTitle());

		// Try setting a null title
		s.resetSchedule();
		String invalidString = null;
		try {
			s.setTitle(invalidString);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Title cannot be null", e.getMessage());
			assertEquals("My Schedule", s.getTitle());
		}
	}

	/**
	 * Tests the canAdd method
	 */
	@Test
	public void testCanAdd() {
		// Create a new schedule
		Schedule s = new Schedule();

		// Create new courses to add to the schedule
		Course c1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 1330, 1445);
		Course c2 = new Course("BW216", "Basket Weaving Concepts", "001", 3, "kwhildne", 10, "TH", 1200, 1300);
		Course c3 = new Course("CSC316", "Data structures", "002", 3, "jdoe", 10, "MW", 1200, 1300);
		Course c4 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 1330, 1445);
		Course c5 = new Course("CSC316", "Data structures", "002", 3, "jdoe", 10, "T", 1200, 1300);

		// Test if a course can be added to an empty schedule
		assertTrue(s.canAdd(c1));
		
		// Add courses to the schedule
		s.addCourseToSchedule(c1);
		s.addCourseToSchedule(c2);
		s.addCourseToSchedule(c3);
		
		// Test if a duplicate can be added
		assertFalse(s.canAdd(c4));
		
		// Test if a null course can be added
		assertFalse(s.canAdd(null));
		
		// Test if a conflicting course can be added
		assertFalse(s.canAdd(c5));

	}

}
