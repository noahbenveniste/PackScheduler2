package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.ncsu.csc216.collections.list.SortedList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests the static helper class, StudentRecordIO.
 * @author demills
 */
public class StudentRecordIOTest {
	// Input files.
	/** Relative path to file containing multiple valid Student records */
	public static final String STUDENT_RECORDS = "test-files/student_records.txt";
	/** Relative path to file containing multiple valid Student records, with a blank line inserted between two non-blank lines */
	public static final String STUDENT_RECORDS_WITH_BLANK = "test-files/student_records_with_blank_line.txt";
	/** Relative path to file containing several invalid Student records */
	public static final String INVALID_STUDENT_RECORDS = "test-files/invalid_student_records.txt";
	/** Relative path to file containing valid Student records with several duplicates mixed in */
	public static final String DUPLICATE_STUDENT_RECORDS = "test-files/duplicate_student_records.txt";
	
	// Expected output files.
	/** Relative path to file containing Student records identical to STUDENT_RECORDS file */
	public static final String EXPECTED_FULL_STUDENT_RECORDS = "test-files/expected_full_student_records.txt";
	/** Relative path to file containing a single Student record for Zahir King */
	public static final String EXPECTED_STUDENT_RECORDS = "test-files/expected_student_records.txt";
	
	/** String representing the valid student Zahir King */
	private String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	/** String representing the valid student Cassandra Schwartz */
	private String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	/** String representing the valid student Shannon Hansen */
	private String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	/** String representing the valid student Demetrius Austin */
	private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	/** String representing the valid student Raymond Brennan */
	private String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	/** String representing the valid student Emerald Frost */
	private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	/** String representing the valid student Lane Berg */
	private String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	/** String representing the valid student Griffith Stone */
	private String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	/** String representing the valid student Althea Hicks */
	private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	/** String representing the valid student Dylan Nolan */
	private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";

	/** A String array of valid Students for use in tests */
	private String [] validStudents = {validStudent0, validStudent1, validStudent2, validStudent3, validStudent4, validStudent5,
	        validStudent6, validStudent7, validStudent8, validStudent9};

	/** A String representing a hashed password. It will always be a SHA-256 hash of "pw" */
	private String hashPW;
	/** A string identifying the SHA-256 hashing algorithm for use in creating a MessageDigest */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Hashes the passwords of all representations of Students in the validStudents array of Strings.
	 */
	@Before
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = new String(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}

	/**
	 * Provided method for comparing the contents of two files, line by line.
	 * 
	 * @param expFile a file of the expected results for a test
	 * @param actFile a file of the actual results for a test
	 */
	private void checkFiles(String expFile, String actFile) {
	    try {
	        Scanner expScanner = new Scanner(new FileInputStream(expFile));
	        Scanner actScanner = new Scanner(new FileInputStream(actFile));
	        
	        while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
	            String exp = expScanner.nextLine();
	            String act = actScanner.nextLine();
	            assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
	        }
	        if (expScanner.hasNextLine()) {
	            fail("The expected results expect another line " + expScanner.nextLine());
	        }
	        if (actScanner.hasNextLine()) {
	            fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
	        }
	        
	        expScanner.close();
	        actScanner.close();
	    } catch (IOException e) {
	        fail("Error reading files.");
	    }
	}

	/**
	 * Tests readStudentRecords() method of StudentRecordIO.
	 * See StudentRecordIO.readStudentRecords()'s Javadoc for description
	 *   of functionality being tested and in-line comments of testReadStudentRecords()
	 *   for specific details of testing.
	 */
	@Test
	public void testReadStudentRecords() {
		// A SortedList of Students that holds the output of 
		//   the readStudentRecords() methods. 
		SortedList<Student> actualStudents = new SortedList<Student>();
		actualStudents = null;

		// Tests passing an empty string filename.
		try {
			actualStudents = StudentRecordIO.readStudentRecords("");
			fail();
		} catch (FileNotFoundException e) {
			assertNull(actualStudents);
		}

		// Tests passing a null filename.
		try {
			actualStudents = StudentRecordIO.readStudentRecords(null);
			fail();
		} catch (FileNotFoundException e) {
			assertNull(actualStudents);
		}

		// Tests reading a non-existent file.
		try {
			actualStudents = StudentRecordIO.readStudentRecords("non_existent_file.txt");
			fail();
		} catch (FileNotFoundException e) {
			assertNull(actualStudents);
		}

		// Tests reading an empty line inserted between non-empty lines.
		// No exceptions should be thrown.
		try {
			SortedList<Student> expected = StudentRecordIO.readStudentRecords(EXPECTED_FULL_STUDENT_RECORDS);
			actualStudents = StudentRecordIO.readStudentRecords(STUDENT_RECORDS_WITH_BLANK);

			assertEquals(expected.size(), actualStudents.size());
			// Compares each Student in the two SortedLists, expected and actualStudents. 
			// Since VALID_STUDENTS_WITH_BLANK has the same records as VALID_STUDENTS, 
			// except for a single blank line inserted, the two should produce identical 
			// lists of Students.
			for (int i = 0; i < expected.size(); i++) {
				// assertEquals will use the overridden equals() method of 
				//   Student, not of Object.
				assertEquals(expected.get(i), actualStudents.get(i));
			}
		} catch (Exception e) {
			fail();
		}
		
		// Tests reading a file with invalid entries, either duplicates or invalid 
		//   Student entries. A record is invalid if StudentRecordIO.processStudent()
		//   cannot read in valid tokens or a Student object cannot be created from 
		//   validly read in tokens. The invalid entries should be omitted with
		//   no exceptions thrown.
		actualStudents = null;
		try {
			actualStudents = StudentRecordIO.readStudentRecords(INVALID_STUDENT_RECORDS);
			assertNotNull(actualStudents);
			assertEquals(0, actualStudents.size()); 
		} catch (Exception e) {
			fail();
		}
		
		// Tests reading a file with duplicate Student records, which are 
		//   identified by equal, e
		actualStudents = null;
		try {
			actualStudents = StudentRecordIO.readStudentRecords(DUPLICATE_STUDENT_RECORDS);
			assertNotNull(actualStudents);
			assertEquals(2, actualStudents.size());
		} catch (Exception e) {
			fail();
		}
		
		// Tests reading a normal, valid file of Student records.
		actualStudents = null;
		try {
			actualStudents = StudentRecordIO.readStudentRecords(DUPLICATE_STUDENT_RECORDS);
			assertNotNull(actualStudents);
			assertEquals(2, actualStudents.size());
		} catch (Exception e) {
			fail();
		}
		
		// Creates a StudentRecordIO instance and does nothing with it.
		// Without this addition, JaCoCo reports that StudentRecordIO doesn't
		//   have 100% condition and statement coverage, listing the only 
		//   uncovered code as the class header (public class StudentRecordIO { )
		// Although it makes no sense to instantiate a class containing only static
		//   methods, the code below is to account a flaw in the JaCoCo tool, so our
		//   group can receive full extra credit points for attaining 100% coverage.
		StudentRecordIO s = new StudentRecordIO();
		assertTrue(s instanceof StudentRecordIO);
	}

	/**
	 * Tests writeStudentRecords() method of StudentRecordIO.
   	 * See StudentRecordIO.writeStudentRecords()'s Javadoc for description
	 *   of functionality being tested and in-line comments of testWriteStudentRecords()
	 *   for specific details of testing.
	 */
	@Test
	public void testWriteStudentRecords() {
		// Tests passing a null filename. File class should throw an IOException.
		SortedList<Student> studentDirectory = new SortedList<Student>();
		try {
			StudentRecordIO.writeStudentRecords(null, studentDirectory);
			fail();
		} catch(IOException e) {
			// Do nothing
		}
			
		// Tests passing an empty string filename. IOException should be thrown.
		try {
			StudentRecordIO.writeStudentRecords("", studentDirectory);
			fail();
		} catch(IOException e) {
			// Do nothing. 
		}
		
		// Tests passing a filename the Student shouldn't have access to.
		try {
			StudentRecordIO.writeStudentRecords(File.separator + File.separator + "test_file.txt", studentDirectory);
			fail();
		} catch (IOException e) {
			// Do nothing
		}

		// Tests passing an empty SortedList of Students. File should be created, but containing no records.
		try {
			StudentRecordIO.writeStudentRecords("test-files/blank_file.txt", studentDirectory);
		} catch (Exception e) {
			// Checks for existence of output file.
			File f = new File("test-files/blank_file.txt");
			assertTrue(f.exists());

			// Checks that the file has 0 lines.
			// NOTE: I referenced a StackOverflow response to find out how to count lines in a file.
			//    link: https://stackoverflow.com/questions/1277880/how-can-i-get-the-count-of-line-in-a-file-in-an-efficient-way
			//    username for response: Augustin
			Path p = Paths.get("test-files/blank_file.txt");
			try {
				assertEquals(0, Files.lines(p).count());
			} catch(Exception e_0) {
				fail();
			}
		}
		
		// Tests writing out valid records.
		try {
		studentDirectory = StudentRecordIO.readStudentRecords(STUDENT_RECORDS);
			StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", studentDirectory);
		} catch (Exception e) {
			fail();
		}

		// Checks that output file was created.
		File outputFile = new File("test-files/actual_student_records.txt");
		assertTrue(outputFile.exists());

		// Checks that the output file matches the expected.
		checkFiles(EXPECTED_FULL_STUDENT_RECORDS, "test-files/actual_student_records.txt");
	}
	
	/**
	 * Tests writing to a directory for which the user running the program doesn't have access to.
	 * The expected behavior is that an error message is returned, although we aren't able to
	 *   check that the file actually wasn't created. If the test is run on Jenkins, determined
	 *   by the return value of System.getProperty("user.name"), the IOException thrown will
	 *   return a message of "...(Permission denied)" rather than "...(No such file or directory)".
	 *   Therefore, two different assertions are provided to account for both possibilities.
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
	    SortedList<Student> students = new SortedList<Student>();
	    students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
	    //Assumption that you are using a hash of "pw" stored in hashPW

	    try {
	        StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt".replaceAll("/",  File.separator + File.separator), students);
	        //StudentRecordIO.writeStudentRecords("asdf.txt", students);
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IOException e) {

	    	// I modified the provided test so that it runs correctly both on Jenkins and locally.
	    	// If the test is run on Jenkins, a "Permission denied" IOException should be thrown.
	    	if (System.getProperty("user.name").equals("jenkins")) {
	    		assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", e.getMessage());
	    	} else {
	    		if (System.getProperties().getProperty("os.name").matches("Windows.*")) {
	    			assertEquals("\\home\\sesmith5\\actual_student_records.txt (The system cannot find the path specified)", e.getMessage());
	    		} else if (System.getProperty("os.name").matches("Linux.*")) {
	    			assertEquals("/home/sesmith5/actual_student_records.txt (No such file or directory)", e.getMessage());
	    		}
	    		// If the test is run locally, i.e. by any user other than Jenkins, 
	    		//   a "No such file" exception should be thrown.
	    	}
	    } 
	}
}