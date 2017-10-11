package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * @author Noah Benveniste
 * @author Kevin Hildner
 */
public class Schedule {
	/** */
	private String title;
	
	/**
	 * 
	 */
	public Schedule() {
	
	}
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public boolean addCourseToSchedule(Course c) {
		return false;
	}
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public boolean removeCourseFromSchedule(Course c) {
		return false;
	}
	
	/**
	 * 
	 */
	public void resetSchedule() {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String[][] getScheduledCourses() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		
	}
}
