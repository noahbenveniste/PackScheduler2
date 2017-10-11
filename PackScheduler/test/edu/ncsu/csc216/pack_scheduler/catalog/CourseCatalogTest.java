/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the CourseCatalog class. 
 * 
 * @author Noah Benveniste
 * @author Daniel Mills
 * @author Kevin Hildner
 */
public class CourseCatalogTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course section */
	private static final String SECTION = "001";
	
	/**
	 * Tests the construction of a course catalog 
	 */
	@Test
	public void testCourseCatalog() {
		//Test that the StudentDirectory is initialized to an empty list
		CourseCatalog catalog = new CourseCatalog();
		assertFalse(catalog.removeCourseFromCatalog(NAME, SECTION));
		assertEquals(0, catalog.getCourseCatalog().length);
	}
	
	/**
	 * Tests loading in a course catalog from a list of courses in a text file.
	 */
	@Test
	public void testLoadCourseCatalog() {
		//Test that invalid test files are ignored
		CourseCatalog badCatalog = new CourseCatalog();
		badCatalog.loadCoursesFromFile(invalidTestFile);
		assertEquals(0, badCatalog.getCourseCatalog().length);
		
		//Load in a course catalog from a valid test file
		CourseCatalog catalog = new CourseCatalog();
		catalog.loadCoursesFromFile(validTestFile);
		assertEquals(8, catalog.getCourseCatalog().length);
		
		//Test file that doesn't exist
		try {
			badCatalog.loadCoursesFromFile("Nonexistant File");
			fail("Loaded file that doesn't exist");
		} catch(IllegalArgumentException e) {
			assertEquals(0, badCatalog.getCourseCatalog().length);
		}
	}
	
	/**
	 * Tests that calling newCourseCatalog() on a populated CourseCatalog resets it to empty
	 */
	@Test
	public void testNewCourseCatalog() {
		//Load in a course catalog from a valid test file
		CourseCatalog catalog = new CourseCatalog();
		catalog.loadCoursesFromFile(validTestFile);
		assertEquals(8, catalog.getCourseCatalog().length);
		
		//Check that calling the method resets the catalog
		catalog.newCourseCatalog();
		assertEquals(0, catalog.getCourseCatalog().length);
	}
	
	/**
	 * Tests adding a course to a catalog
	 */
	@Test
	public void testAddCourseToCatalog() {
		// Add a blank courseCatalog
		CourseCatalog catalog = new CourseCatalog();

		// Test adding a valid course to the catalog
		assertTrue(catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", "MW", 910,
				1100));
		assertEquals(1, catalog.getCourseCatalog().length);

		// Test adding a duplicate course to the catalog
		assertFalse(catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", "MW", 910,
				1100));
		assertEquals(1, catalog.getCourseCatalog().length);
		
		// Test adding non-duplicate course with same name, but different section.
		assertTrue(catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "002", 3, "jdyoung2", "MW", 910,
				1100));
		assertEquals(2, catalog.getCourseCatalog().length);
		// Test adding non-duplicate course with same section, but different name.
		assertTrue(catalog.addCourseToCatalog("MA305", "Linear Algebra", "001", 3, "jdyoung2", "MW", 910,
				1100));
		assertEquals(3, catalog.getCourseCatalog().length);
	}

	/**
	 * Tests removing a course from a catalog
	 * @author kwhildne
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		// Load in a course catalog from a valid test file
		CourseCatalog catalog = new CourseCatalog();
		catalog.loadCoursesFromFile(validTestFile);

		// Test removing a course that exists in the file
		assertTrue(catalog.removeCourseFromCatalog("CSC116", "001"));
		assertEquals(7, catalog.getCourseCatalog().length);

		// Test removing a course that does not exist in the file
		assertFalse(catalog.removeCourseFromCatalog("CSC116", "001"));
		assertEquals(7, catalog.getCourseCatalog().length);

	}

	/**
	 * Tests getting a course from a catalog
	 * @author kwhildne
	 */
	@Test
	public void testGetCourseFromCatalog() {
		// Load in a course catalog from a valid test file
		CourseCatalog catalog = new CourseCatalog();
		catalog.loadCoursesFromFile(validTestFile);
		
		// Test getting a course from the catalog
		Course c = catalog.getCourseFromCatalog("CSC116", "001");
		Course t = new Course("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", "MW", 910,
		1100);
		assertTrue(c.equals(t));
		
		// Test tring to get a course that doesn't exist in the catalog
		Course d = catalog.getCourseFromCatalog("CSC900", "001");
		assertNull(d);
	}

	/**
	 * Tests returning part of a CourseCatalog's contents as a String array
	 *   of the Courses with each row in the form {name,section,title,meetingString}.
	 * @author demills
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();

		// Tests empty catalog.
		assertEquals(0, catalog.getCourseCatalog().length);

		// Tests a catalog with several course records.
		catalog.loadCoursesFromFile(validTestFile);

		String[][] actual = catalog.getCourseCatalog();
		assertEquals(8, catalog.getCourseCatalog().length);
		assertEquals("CSC116", actual[0][0]);
		assertEquals("001", actual[0][1]);
		assertEquals("Intro to Programming - Java", actual[0][2]);
		assertEquals("MW 9:10AM-11:00AM", actual[0][3]);

		assertEquals("CSC216", actual[3][0]);
		assertEquals("601", actual[5][1]);
		assertEquals("Programming Concepts - Java", actual[5][2]);
		assertEquals("Arranged", actual[5][3]);

		assertEquals("CSC226", actual[6][0]);
		assertEquals("001", actual[6][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", actual[6][2]);
		assertEquals("MWF 9:35AM-10:25AM", actual[6][3]);

		assertEquals("CSC230", actual[7][0]);
		assertEquals("001", actual[7][1]);
		assertEquals("C and Software Tools", actual[7][2]);
		assertEquals("MW 11:45AM-1:00PM", actual[7][3]);
	}
	
	/**
	 * Tests saving a course catalog to file. 
	 * @author demills
	 */
	@Test
	public void testSaveCourseCatalog() {
		// Tests that an IllegalArgumentException is thrown when passed a null filename.
		// IllegalArguments can be thrown by other bad inputs, but the CourseRecordIO 
		//   class is already tested on those paths. We only need to follow at least 
		//   one of these paths to ensure CourseCatalog catches IOExceptions from
		//   CourseRecordIO appropriately.
		CourseCatalog actual = new CourseCatalog();
		actual.loadCoursesFromFile(validTestFile);
		try {
			actual.saveCourseCatalog("");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Unable to write to file ", e.getMessage());
		}
		actual.loadCoursesFromFile(validTestFile);
	}
}