package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Object class that represents a course
 * @author Noah Benveniste
 * @author Sarah Heckman
 */
public class Course extends Activity implements Comparable<Course> {
    
	/** Fields */
	
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Used for checking the validity of course names */
	private CourseNameValidator validator;
	/** Stores the roll of Students enrolled in this course */
	private CourseRoll roll;
	
	
	/** Constructors */
	
	/**
	 * Constructor for Course object with values for all fields.
	 * @param name Name of the Course
	 * @param title Title of the Course
	 * @param section Section number of the Course
	 * @param credits Number of credit hours for the Course
	 * @param instructorId Course instructor's unity id
	 * @param enrollmentCap the number of students that can enroll in the course
	 * @param meetingDays First letter of all days the Course meets
	 * @param startTime Start time of the Course
	 * @param endTime End time of the Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		this.validator = new CourseNameValidator();
		setName(name);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	    this.roll = new CourseRoll(enrollmentCap);
	}
	
	/**
	 * Constructor for Course that has an arranged meeting time.
	 * @param name Name of the Course
	 * @param title Title of the Course
	 * @param section Section number of the Course
	 * @param credits Number of credit hours for the Course
	 * @param instructorId Course instructor's unity id
	 * @param enrollmentCap the number of students that can enroll in the course
	 * @param meetingDays First letter of all days the Course meets
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap ,String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}
    
	/** Getters and Setters */
	
	/**
	 * Gets the course's name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the Course's name.  If the name is null, has a length less than 4 or 
     * greater than 6, an IllegalArgumentException is thrown.
     * @param name the name to set
     * @throws IllegalArgumentException if name is null or length is less than 4 or 
     * greater than 6
	 */
	private void setName(String name) {
		//First, check that the input isn't null
		if (name == null) {
	        throw new IllegalArgumentException("Invalid course name");
	    }
		//Next, check that the input is of the correct length
	    if (name.length() < 4 || name.length() > 6) {
	        throw new IllegalArgumentException("Invalid course name");
	    }
	    //Next, use the CourseNameValidator to check that the course name is valid
	    boolean valid;
	    try {
	    	valid = validator.isValid(name);
	    } catch (InvalidTransitionException e) { //Throw an IAE if the method throws an InvalidTransitionException
	    	throw new IllegalArgumentException("Invalid course name");
	    }
	    if (!valid) { //Throw an IAE if the method returns false
    		throw new IllegalArgumentException("Invalid course name");
    	}
		this.name = name;
	}
	
	/**
	 * Gets the course's section.
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * Sets the course's section.
	 * @param section the section to set
	 * @throws IllegalArgumentException if the input is null, not of length 3, or not all digits
	 */
	public void setSection(String section) {
		//Check that the input isn't null
		if (section == null) {
		    throw new IllegalArgumentException("Invalid section number");	
		}
		//Check that the input is 3 characters long
		if (section.length() != 3 ) {
			throw new IllegalArgumentException("Invalid section number");
		}
		//Check that all characters are digits
		for (int i = 0; i < section.length(); i++) {
			//Loop through the length of the string. If any char
			//is not a digit, an exception is thrown
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section number");
			}
		}
		this.section = section;
	}
	
	/**
	 * Gets the course's number of credits.
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	/**
	 * Sets the course's number of credits.
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if the number of credits is not between
	 * 1 and 5 inclusive
	 */
	public void setCredits(int credits) {
		//Check that the input is not less than 1 or greater
		//than 5
		if (credits < 1 || credits > 5) {
			throw new IllegalArgumentException("");
		}
		this.credits = credits;
	}
	
	/**
	 * Gets the instructor's unity id.
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	/**
	 * Sets the instructor's unity id.
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if the input is null or empty
	 */
	public void setInstructorId(String instructorId) {
		//Check that the input isn't null or an empty string
		if (instructorId == null || instructorId.equals("")) {
			throw new IllegalArgumentException("Invalid instructor unity id");
		}
		this.instructorId = instructorId;
	}
	
	/**
	 * Sets the meeting days for a Course
	 * @param meetingDays the meetingDays to set
	 * @throws IllegalArgumentException if the input is null or an empty string, if the 
	 * input has characters other than m,t,w,h,f,a, if an input contains the character "A" 
	 * with any other characters
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		//Check that the input isn't null or an empty string
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		
		//Check for invalid characters
		for (int i = 0; i < meetingDays.length(); i++) {
			if (meetingDays.charAt(i) != 'M' && meetingDays.charAt(i) != 'T' && 
					meetingDays.charAt(i) != 'W' && meetingDays.charAt(i) != 'H' && 
					meetingDays.charAt(i) != 'F' && meetingDays.charAt(i) != 'A') {
				throw new IllegalArgumentException("Invalid meeting days");
			}
			//Check that if the string is greater than 1 char, it doesn't
			//contain 'A'
			if (meetingDays.charAt(i) == 'A' && meetingDays.length() > 1) {
				throw new IllegalArgumentException("Invalid meeting days");
			}
		}
		
		super.setMeetingDays(meetingDays);
	}
	
	/**
	 * Checks if two courses are duplicates. A course is a duplicate of another if
	 * it has the same name.
	 * @param activity The activity to be compared
	 * @return true if the input activity is a course and has the same name as the 
	 * course being compared, false if the input is not a course or it is a course 
	 * and does not have the same name as the course being compared
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		//Check that the input is of type Course
		if (activity instanceof Course) {
			//Cast the input to a Course
			Course course = (Course)activity;
			//Check that the courses being compared do not have the same name
			return this.getName().equals(course.getName()) && this.getSection().equals(course.getSection());
		} else {
			//Return false if the Activity is not a Course
			return false;
		}
	}
	
	/**
	 * Creates and returns a string array containing the course name, section, title and meetingString
	 * @return the short string array
	 */
	@Override
	public String[] getShortDisplayArray() {
		return new String[] {this.getName(), this.getSection(), this.getTitle(), this.getMeetingString()};
	}

	/**
	 * Creates and returns a string array containing the course name, section, title, credits, instructorId,
	 * meetingString and an empty string for a field that event has that course does not.
	 * @return the long string array
	 */
	@Override
	public String[] getLongDisplayArray() {
		return new String[] {this.getName(), this.getSection(), this.getTitle(), 
				"" + this.getCredits(), this.getInstructorId(), this.getMeetingString(), ""};
	}
	
	/**
	 * Getter for the course's course roll
	 * @return the course roll for this course
	 */
	public CourseRoll getCourseRoll() {
		return this.roll;
	}
	
	/**
	 * Used to compare two Course objects. Done by comparing course name and then section 
	 * lexicographically i.e. checks if a string field would come
	 * before or after another string field in a dictionary.
	 * @param o The Course to compare to
	 * @return a negative int if this is lexicographically greater than o, a positive
	 * int if this is lexicographically less than o, and 0 if this and o are equivalent
	 */
	@Override
	public int compareTo(Course o) {
		if (!this.getName().equals(o.getName())) {
			return this.getName().compareTo(o.getName());
		} else if (!this.getSection().equals(o.getSection())) {
			return this.getSection().compareTo(o.getSection());
		}
		//If all fields are equal, return 0
		return 0;
	}
	
	
	/** Overridden hashCode(), equals() and toString() */
	
	
	/**
	 * Generates a hashCode for Course using all fields.
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality over all fields.
	 * @param obj The object to compare
	 * @return true if the objects are the same over all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
	    if (this.getMeetingDays().equals("A")) {
	        return name + "," + this.getTitle() + "," + section + "," + credits + "," + instructorId + "," + this.getMeetingDays();
	    }
	    return name + "," + this.getTitle() + "," + section + "," + credits + "," + instructorId + "," + this.getMeetingDays() + "," + this.getStartTime() + "," + this.getEndTime(); 
	}
}
