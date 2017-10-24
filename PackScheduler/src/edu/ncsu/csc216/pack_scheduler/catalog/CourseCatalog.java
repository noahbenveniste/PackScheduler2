package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;


/**
 * A CourseCatalog represents a collection of the Courses available for a
 *   Student to register in, or for a Faculty to teach. 
 * @author Kevin Hildner
 * @author Noah Benveniste
 *
 */
public class CourseCatalog {

	/** The course catalog */
	private SortedList<Course> catalog;
	
	/**
	 * Constructs a blank CourseCatalog
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}
	
	
	/**
	 * Creates a new catalog
	 */
	public void newCourseCatalog() {
		this.catalog = new SortedList<Course>();
	}
	
	/**
	 * Provided a file of Course records in valid format, reads in the 
	 *   Courses as a SortedList.
	 * @param fileName path to a file containing Course records
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adds a course to the catalog. Allows any IllegalArgumentExceptions that are thrown from
	 * constructing a course to be thrown to the user. Returns true if the course is valid and 
	 * can be added to the catalog, returns false if the course already exists in the catalog
	 * @param name Name of the Course
	 * @param title Title of the Course
	 * @param section Section number of the Course
	 * @param credits Number of credit hours for the Course
	 * @param instructorID Course instructor's unity id
	 * @param meetingDays First letter of all days the Course meets
	 * @param startTime the time the course starts
	 * @param endTime the time the course ends
	 * @return true if the course is valid and can be added to the catalog, returns false if the 
	 * course already exists in the catalog
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorID, int enrollmentCap,
			String meetingDays, int startTime, int endTime) {
		// Create the course
		Course c = null;
		if (meetingDays.equals("A")) {
			c = new Course(name, title, section, credits, instructorID, enrollmentCap, meetingDays);
		} else {
			c = new Course(name, title, section, credits, instructorID, enrollmentCap, meetingDays, startTime, endTime);
		}
		// Check for duplicate
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).isDuplicate(c)) {
				return false;
			}
		}

		// Add the class to their schedule
		catalog.add(c);
		return true;

	}
	
	/**
	 * Removes a course from the catalog, specified by name and section number.
	 * @param name the name of the course
	 * @param section the section number of the course
	 * @return true if the course was able to be removed (i.e. it existed in the catalog),
	 * or false if the course did not exist in the catalog and couldn't be removed.
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if ((c.getName().equals(name)) && (c.getSection().equals(section))) {
				catalog.remove(i);
				return true;
			}
		}
		//Return false if the course is not in the catalog
		return false;
	}
	
	/**
	 * Gets a course from the catalog, specified by name and section number.
	 * @param name the name of the course
	 * @param section the section number of the course
	 * @return the course specified by name and section or null if the specified course
	 * does not exist.
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if ((c.getName().equals(name)) && (c.getSection().equals(section))) {
				return c;
			}
		}
		//Return null if the course is not in the catalog
		return null;
	}
	
	/**
	 * Gets a string array representation of the courseCatalog with course name, section
	 * title, and meeting string in each column respectively and a course in each row.
	 * @return courseCatalog as a 2D string array
	 */
	public String[][] getCourseCatalog() {
		//Initialize the string array
		String[][] courseCatalog = new String[catalog.size()][4];
		//Loop through the course catalog getting the required fields and adding them
		//to the relevant cells in the string array
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			courseCatalog[i][0] = c.getName();
			courseCatalog[i][1] = c.getSection();
			courseCatalog[i][2] = c.getTitle();
			courseCatalog[i][3] = c.getMeetingString();
		}
		return courseCatalog;
	}
	
	/**
	 * Saves the current course catalog
	 * @param fileName The desired name of the file being saved to
	 * @throws IllegalArgumentException if the file is unable to be written to
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file ");
		}
	}
}