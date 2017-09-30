package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

public class RegistrationManagerTest {
	
	private RegistrationManager manager;
	
	/**
	 * Sets up the CourseManager and clears the data.
	 * @throws Exception if error
	 */
	@Before
	public void setUp() throws Exception {
		manager = RegistrationManager.getInstance();
		manager.clearData();
	}

	/**
	 * Tests getting courseCatalog from the RegistrationManager
	 */
	@Test
	public void testGetCourseCatalog() {
		//Test that the course catalog starts out empty
		assertEquals(0, manager.getCourseCatalog().getCourseCatalog().length);
		
		//Load in the course catalog from a file
		manager.getCourseCatalog().loadCoursesFromFile("test-files/course_records.txt");
		
		//Check that the course catalog now contains courses
		assertEquals(8, manager.getCourseCatalog().getCourseCatalog().length);
		
	}

	/**
	 * Tests getting studentRecords from the RegistrationManager
	 */
	@Test
	public void testGetStudentDirectory() {
		//Test that the student directory starts out empty
		assertEquals(0, manager.getStudentDirectory().getStudentDirectory().length);
		
		//Load in the student directory from a file
		manager.getStudentDirectory().loadStudentsFromFile("test-files/student_records.txt");
		
		//Check that the student records now contains students
		assertEquals(10, manager.getStudentDirectory().getStudentDirectory().length);
	}

	/**
	 * Tests the login() method by getting information from a generic registrar.properties file
	 */
	@Test
	public void testLogin() {
		//Load in the student directory from a file
		manager.getStudentDirectory().loadStudentsFromFile("test-files/student_records.txt");
		
		//Set up the properties object for parsing registrar.properties
		Properties prop = new Properties();
		String registrarPW;
		String registrarID;
		
		//Get the password and id from registrar.properties
		try (InputStream input = new FileInputStream("registrar.properties")) {
			prop.load(input);

			registrarPW = prop.getProperty("pw");
			registrarID = prop.getProperty("id");

		} catch (IOException e) {
			throw new IllegalArgumentException("Could not load registrar.properties");
		}
		
		//Test valid login for registrar
		assertTrue(manager.login(registrarID, registrarPW));
		
		//Test invalid login for registrar
		assertFalse(manager.login(registrarID, "wrongpassword"));
		
		Student studentUser = manager.getStudentDirectory().getStudentById("daustin");
		assertTrue(manager.login(studentUser.getId(), studentUser.getPassword()));
		
	}

	/**
	 * Tests the logout() method with a generic registar.properties file
	 */
	@Test
	public void testLogout() {
		Properties prop = new Properties();
		String registrarID;
		
		//Get the password and id from registrar.properties
		try (InputStream input = new FileInputStream("registrar.properties")) {
			prop.load(input);

			registrarID = prop.getProperty("id");

		} catch (IOException e) {
			throw new IllegalArgumentException("Could not load registrar.properties");
		}
		//Test that logging out sets the current user to the registrar
		manager.logout();
		assertEquals(registrarID, manager.getCurrentUser().getId());
	}

	@Test
	public void testGetCurrentUser() {
		fail("Not yet implemented");
	}

}