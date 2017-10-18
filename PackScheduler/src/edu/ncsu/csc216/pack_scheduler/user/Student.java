package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Class for a student object
 * @author Sarah Heckman
 * @author demills
 * @author nnbenven
 * @author kwhildne
 */
public class Student extends User implements Comparable<Student> {
	
	/** Fields */
	
	/** The maximum number of credits that the student can enroll in */
	private int maxCredits;
	/** The student's schedule */
	private Schedule schedule;
	
	/** Class Constants */
	
	/** The minimum number of credits to enroll in */
	public static final int MIN_CREDITS = 3;
	/** The maximum number of credits to enroll in */
	public static final int MAX_CREDITS = 18;

	/**
	 * Constructor for student object
	 * @param firstName The student's first name
	 * @param lastName The student's last name
	 * @param id The student's id
	 * @param email The student's email
	 * @param hashPW The student's password
	 * @param maxCredits The max number of credits that the student can take
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		//Set the fields by calling setter methods to enforce class invariants
		super(firstName, lastName, id, email, hashPW);
		this.setMaxCredits(maxCredits);
		this.schedule = new Schedule();
	}
	
	/**
	 * Constructor with default max credit value
	 * @param firstName The student's first name
	 * @param lastName The student's last name
	 * @param id The student's id
	 * @param email The student's email
	 * @param hashPW The student's password
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, MAX_CREDITS);
	}
	
	/**
	 * Gets the max number of credits the student can take
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the max number of credits the student can take
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException for inputs less than 3 or greater than 18
	 */
	public void setMaxCredits(int maxCredits) {
		//Check that the number of credits is between 3 and 18
		if (maxCredits < MIN_CREDITS || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		//Set the field if all conditions are met
		this.maxCredits = maxCredits;
	}
	
	/**
	 * Getter method for the schedule field
	 * @return the student's schedule
	 */
	public Schedule getSchedule() {
		return this.schedule;
	}
	
	/**
	 * Used to compare two student object. Done by comparing last name, then first
	 * name, then unity id lexicographically i.e. checks if a string field would come
	 * before or after another string field in a dictionary.
	 * @param o The student to compare to
	 * @return a negative int if this is lexicographically greater than o, a positive
	 * int if this is lexicographically less than o, and 0 if this and o are equivalent
	 */
	@Override
	public int compareTo(Student o) {
		if (!this.getLastName().equals(o.getLastName())) { //Compare last names
			return this.getLastName().compareTo(o.getLastName());
		} else if (!this.getFirstName().equals(o.getFirstName())) { //Compare first names
			return this.getFirstName().compareTo(o.getFirstName());
		} else if (!this.getId().equals(o.getId())) { //Compare unity ids
			return this.getId().compareTo(o.getId());
			
		}
		//If all fields are equal, return 0
		return 0;
	}

	/**
	 * Creates a string representation of the student object's data
	 * @return the student info represented as a string
	 */
	@Override
	public String toString() {
		return this.getFirstName() + "," + this.getLastName() + "," + this.getId() + "," 
			+ this.getEmail() + "," + this.getPassword() + "," + this.getMaxCredits();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}

}
