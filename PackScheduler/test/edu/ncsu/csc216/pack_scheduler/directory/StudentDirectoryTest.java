package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

/**
 * Tests StudentDirectory.
 * @author Sarah Heckman
 */
public class StudentDirectoryTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_CREDITS = 15;
	/** Invalid course records */ 
	private final String invalidTestFile = "test-files/invalid_student_records.txt";
	/** Non matching password */
	private static final String PASSWORD1 = "pw1";
	
    /*  Removed the provided setUp() method, because it overwrote an
     *    input file "student_records.txt" (unsorted records) with the contents of
     *    "expected_full_student_records.txt" (sorted records). Since Lab 3 is testing
     *    the implementation of SortedList as the underlying data structure of
     *    StudentDirectory, it makes more sense to take an unsorted "student_records.txt"
     *    as input, produce a sorted "actual_student_records.txt" and compare
     *    that to "expected_full_student_records.txt". Otherwise, the
     *    StudentDirectoryTest tests might pass even if StudentDirectory hadn't
     *    been replaced its ArrayList with a SortedList. Also, it seems like good practice
     *    to not overwrite input files, since they are committed to GitHub. If we were to
     *    use a sorted file, we should just change the file and push it, rather than
     *    overwrite it before every test.
     */
	
	/**
	 * Tests StudentDirectory().
	 */
	@Test
	public void testStudentDirectory() {
		//Test that the StudentDirectory is initialized to an empty list
		StudentDirectory sd = new StudentDirectory();
		assertFalse(sd.removeStudent("sesmith5"));
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.testNewStudentDirectory().
	 */
	@Test
	public void testNewStudentDirectory() {
		//Test that if there are students in the directory, they 
		//are removed after calling newStudentDirectory().
		StudentDirectory sd = new StudentDirectory();
		
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		
		sd.newStudentDirectory();
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.loadStudentsFromFile().
	 */
	@Test
	public void testLoadStudentsFromFile() {
		StudentDirectory sd = new StudentDirectory();
		StudentDirectory sd2 = new StudentDirectory();
				
		//Test valid file
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		
		//Test that invalid files are ignored
		sd.loadStudentsFromFile(invalidTestFile);
		assertEquals(0, sd2.getStudentDirectory().length);
		
		//Test file that doesn't exist
		try {
			sd.loadStudentsFromFile("Nonexistant File");
			fail("Loaded file that doesn't exist");
		} catch(IllegalArgumentException e) {
			assertEquals(0, sd2.getStudentDirectory().length);
		}
		
		
	}

	/**
	 * Tests StudentDirectory.addStudent().
	 */
	@Test
	public void testAddStudent() {
		StudentDirectory sd = new StudentDirectory();
		
		//Test valid Student
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
		
		//Test null password
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_CREDITS);
			fail("Student added w/ null password");
		} catch(IllegalArgumentException e) {
			studentDirectory = sd.getStudentDirectory();
			assertEquals(1, studentDirectory.length);
			assertEquals(FIRST_NAME, studentDirectory[0][0]);
			assertEquals(LAST_NAME, studentDirectory[0][1]);
			assertEquals(ID, studentDirectory[0][2]);		
		}
		
		//Test blank password
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_CREDITS);
			fail("Student added w/ blank password");
		} catch(IllegalArgumentException e) {
			studentDirectory = sd.getStudentDirectory();
			assertEquals(1, studentDirectory.length);
			assertEquals(FIRST_NAME, studentDirectory[0][0]);
			assertEquals(LAST_NAME, studentDirectory[0][1]);
			assertEquals(ID, studentDirectory[0][2]);		
		}
		
		//Test null repeated password
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_CREDITS);
			fail("Student added w/ null repeated password");
		} catch(IllegalArgumentException e) {
			studentDirectory = sd.getStudentDirectory();
			assertEquals(1, studentDirectory.length);
			assertEquals(FIRST_NAME, studentDirectory[0][0]);
			assertEquals(LAST_NAME, studentDirectory[0][1]);
			assertEquals(ID, studentDirectory[0][2]);		
		}
		
		//Test blank repeated password
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_CREDITS);
			fail("Student added w/ blank repeated password");
		} catch(IllegalArgumentException e) {
			studentDirectory = sd.getStudentDirectory();
			assertEquals(1, studentDirectory.length);
			assertEquals(FIRST_NAME, studentDirectory[0][0]);
			assertEquals(LAST_NAME, studentDirectory[0][1]);
			assertEquals(ID, studentDirectory[0][2]);		
		}
		
		//Test passwords that don't match
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD1, MAX_CREDITS);
			fail("Student added w/ non matching passwords");
		} catch(IllegalArgumentException e) {
			studentDirectory = sd.getStudentDirectory();
			assertEquals(1, studentDirectory.length);
			assertEquals(FIRST_NAME, studentDirectory[0][0]);
			assertEquals(LAST_NAME, studentDirectory[0][1]);
			assertEquals(ID, studentDirectory[0][2]);		
		}
		
		//Test adding a student. 
		sd.addStudent(LAST_NAME, FIRST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
		
		// Test adding a duplicate Student, determined by ID, who shouldn't be added to the StudentDirectory.
		assertEquals(1, sd.getStudentDirectory().length);
		sd.addStudent("Steven", "Seagal", ID, "sseagal@ncsu.edu", PASSWORD, PASSWORD, MAX_CREDITS);
		assertEquals(1, sd.getStudentDirectory().length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		
		// Tests adding a second Student, with a different ID than the first.
		sd.addStudent("Bat", "Man", "bman", "batman@ncsu.edu", PASSWORD, PASSWORD, MAX_CREDITS);
		studentDirectory = sd.getStudentDirectory();
		assertEquals(2, studentDirectory.length);
		// Since studentDirectory is implemented with a SortedList, and Student's are sorted in
		//   lexicographic order by last name, then first, then ID, Bat Man should be the second
		//   entry in the directory.
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
		assertEquals("Bat", studentDirectory[1][0]);
		assertEquals("Man", studentDirectory[1][1]);
		assertEquals("bman", studentDirectory[1][2]);	// Tests adding another student
	}

	/**
	 * Tests StudentDirectory.removeStudent().
	 */
	@Test
	public void testRemoveStudent() {
		StudentDirectory sd = new StudentDirectory();

		//Add students and remove
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		assertTrue(sd.removeStudent("efrost"));
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(9, studentDirectory.length);
		assertEquals("Zahir", studentDirectory[5][0]);
		assertEquals("King", studentDirectory[5][1]);
		assertEquals("zking", studentDirectory[5][2]);
	}

	/**
	 * Tests StudentDirectory.saveStudentDirectory().
	 */
	@Test
	public void testSaveStudentDirectory() {
		StudentDirectory sd = new StudentDirectory();
		
		//Add a student
		sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		assertEquals(1, sd.getStudentDirectory().length);
		sd.saveStudentDirectory("test-files/actual_student_records.txt");
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
		
		// Test if saveStudentDirectory will catch a FileIOException
		try{ 
			sd.saveStudentDirectory("/abc/def/these_directories_dont_exist.jpg");
			fail();
		} catch(IllegalArgumentException e){
			assertEquals("Unable to write to file /abc/def/these_directories_dont_exist.jpg", e.getMessage());
		}
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}