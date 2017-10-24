package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * RegistrationManager represents a manager that keeps track of its own instance and
 * has the responsibilities related to getting the single instance,
 * the courseCatalog, studentDirectory, and user authentication. 
 * Also contains the Registrar inner class.
 * @author Noah Benveniste
 * @author Kevin Hildner
 * @author Kristina Fialo
 */
public class RegistrationManager {

	private static RegistrationManager instance;
	private CourseCatalog courseCatalog;
	private StudentDirectory studentDirectory;
	private User registrar;
	private User currentUser;
	private boolean loggedIn;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * Constructor for RegistrationManager.
	 */
	private RegistrationManager() {
		createRegistrar();
		this.courseCatalog = new CourseCatalog();
		this.studentDirectory = new StudentDirectory();
		this.loggedIn = false;
		this.currentUser = null;
	}

	/**
	 * Reads from the Properties file to get the value associated with password, 
	 * then hashes the password. Then gets the rest of the properties as parameters for 
	 * the Registrar constructor.
	 * @throws IllegalArgumentException if there is no local copy of the specified file.
	 */
	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/**
	 * Hashes the User's password.
	 * @param pw password of the User
	 * @throws IllegalArgumentException if the password cannot be hashed.
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Creates the single instance of RegistrationManager if one doesn't already exist.
	 * @return the RegistrationManager instance if it exists
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Gets the course catalog.
	 * @return the courseCatalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Gets the student directory.
	 * @return the studentDirectory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Logs in a user with their id and password. The user can log in if
	 * their id and hashed password matches the user's stored id and password.
	 * @param id of the User logging in
	 * @param password of the User logging in
	 * @return true if the user can log in, false otherwise 
	 * (i.e. the input password is wrong or someone else is logged in)
	 * @throws IllegalArgumentException if the user doesn't exist
	 */
	public boolean login(String id, String password) {
		if (!loggedIn) {
			Student s = studentDirectory.getStudentById(id);
			if (s != null) {
				MessageDigest digest;
				try {
					digest = MessageDigest.getInstance(HASH_ALGORITHM);
					digest.update(password.getBytes());
					String localHashPW = new String(digest.digest());
					if (s.getPassword().equals(localHashPW)) {
						currentUser = s;
						this.loggedIn = true;
						return true;
					} else {
						return false;
					}
				} catch (NoSuchAlgorithmException e) {
					throw new IllegalArgumentException();
				}
			} else if (registrar.getId().equals(id)) {
				MessageDigest digest;
				try {
					digest = MessageDigest.getInstance(HASH_ALGORITHM);
					digest.update(password.getBytes());
					String localHashPW = new String(digest.digest());
					if (registrar.getPassword().equals(localHashPW)) {
						currentUser = registrar;
						this.loggedIn = true;
						return true;
					} else {
						return false;
					}
				} catch (NoSuchAlgorithmException e) {
					throw new IllegalArgumentException();
				}
			}
			throw new IllegalArgumentException("User doesn't exist.");
		} else {
			return false;
		}
	}

	/**
	 * Logs a User out of the system.
	 */
	public void logout() {
		currentUser = null;
		this.loggedIn = false;
	}

	/**
	 * Getter for currentUser
	 * @return the current User
	 */
	public User getCurrentUser() {
		return this.currentUser;
	}

	/**
	 * Clears data in the courseCatalog and the studentDirectory.
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
	}
	
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}

	/**
	 * Inner class of RegistrationManager that represents a generic Registrar User.
	 * The Registrar works with the studentDirectory and courseCatalog.
	 * @author Noah Benveniste
	 * @author Kevin Hildner
	 * @author Kristina Fialo
	 */
	private static class Registrar extends User {
		/**
		 * Create a Registrar user with the user id and password in the
		 * registrar.properties file.
		 * @param firstName of the Registrar user
		 * @param lastName of the Registrar user
		 * @param id of the Registrar user
		 * @param email of the Registrar user
		 * @param hashPW hashed password of the Registrar user
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
}