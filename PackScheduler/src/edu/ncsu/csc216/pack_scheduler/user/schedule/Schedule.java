package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Class that represents a student's schedule. Stores an ArrayList of Course objects and a
 * title for its state
 * @author Noah Benveniste
 * @author Kevin Hildner
 */
public class Schedule {
	/** Default schedule title */
	private final String defaultTitle = "My Schedule";
	/** Maintains a list of Courses for the Schedule */
	private ArrayList<Course> schedule;
	/** The Schedule's title */
	private String title;
	
	/**
	 * Constructs the Schedule; initializes an empty array list for the schedule field
	 * and the Schedule's title to a default value
	 */
	public Schedule() {
		this.schedule = new ArrayList<Course>();
		this.title = defaultTitle;
	}
	
	/**
	 * Adds a specified course to the end of the schedule
	 * @param c the course to be added
	 * @return true if the course was added successfully
	 * @throws IllegalArgumentException if a duplicate course is passed or 
	 * if the passed course conflicts with any others already in the schedule
	 */
	public boolean addCourseToSchedule(Course c) {
		for (int i = 0; i < this.schedule.size(); i++) {
			try {
				c.checkConflict(this.schedule.get(i)); //Check that the course to be added doesn't conflict with any others in the schedule
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		try {
			this.schedule.add(this.schedule.size(), c); //Try to add the course to the end of the schedule ArrayList
			return true; //If an exception isn't thrown, return true
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("You are already enrolled in " + c.getName());
		}
	}
	
	/**
	 * Removes a specified course from the schedule
	 * @param c the course to be removed
	 * @return true if the course was removed, false if the course does not exist in the schedule
	 */
	public boolean removeCourseFromSchedule(Course c) {
		for (int i = 0; i < this.schedule.size(); i++) {
			if (schedule.get(i).equals(c)) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Creates a new empty schedule ArrayList and resets the title to the default value
	 */
	public void resetSchedule() {
		this.schedule = new ArrayList<Course>();
		this.title = defaultTitle;
	}
	
	/**
	 * Returns a 2D array of course info where each row is a different course and the
	 * columns are name, section, title and meeting string
	 * @return the schedule's course information as a 2D array
	 */
	public String[][] getScheduledCourses() {
		return null;
	}
	
	/**
	 * Gets the Schedule's title
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Sets the Schedule's title
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title is null
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		this.title = title;
	}
}
