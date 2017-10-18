package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import edu.ncsu.csc216.collections.list.SortedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * @author Noah Benveniste
 * @author Sarah Heckman
 * @author Daniel Mills 
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     *   Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     *   a File NotFoundException is thrown. The Courses are returned as a SortedList, with the sorting
     *   of the Course's defined by Course.compareTo.
     * @param fileName file to read Course records from
     * @return a list of valid Courses, sorted in lexicographic order
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));
	    
	    //Generate the array list to contain the course objects
	    SortedList<Course> courses = new SortedList<Course>();
	    
	    //Continue reading through the file while there are lines to read
	    while (fileReader.hasNextLine()) {
	        //Try to read the course
	    	try {
	    		//Call the helper class to parse the line and get the course information
	            Course course = readCourse(fileReader.nextLine());
	            //If the course can be read, check for duplicates in the arraylist
	            boolean duplicate = false;
	            for (int i = 0; i < courses.size(); i++) {
	                Course c = courses.get(i);
	                if (course.getName().equals(c.getName()) &&
	                        course.getSection().equals(c.getSection())) {
	                    //it's a duplicate
	                    duplicate = true;
	                }
	            }
	            //If the current course object isn't a duplicate, add it to the arraylist
	            if (!duplicate) {
	                courses.add(course);
	            }
	        //If the course is invalid, skip the line
	        } catch (IllegalArgumentException e) {
	            //skip the line
	        }
	    }
	    fileReader.close();
	    return courses;
	}
    
    /**
     * Helper method that parses individual courses that are read in as lines from the input file
     * and creates a course object
     * @param nextLine
     * @return a course object containing the course data that was given to the method
     * @throws IllegalArgumentException if the string cannot be read or the string contains
     * invalid data and the object cannot be created
     */
    private static Course readCourse(String nextLine) {
    	//Create the scanner object
    	Scanner lineReader = new Scanner(nextLine);
    	//Specify the token delimiter
    	lineReader.useDelimiter(",");
    	
    	//Initialize variables to store tokens
    	String courseName = "";
    	String courseTitle =  "";
    	String sectionNum = "";
    	int credits = 0;
    	String id = "";
    	String meetingDays = "";
    	/**
    	String startTimeString = "";
    	String endTimeString = "";
    	*/
    	int startTime = 0;
    	int endTime = 0;
    	Course c = null;
    	
    	//First, try to read in the tokens from the string
    	try {
    		//The first element should be a STRING for the COURSE NAME
    		courseName = lineReader.next();
    		//The next element should be a STRING for the COURSE TITLE
    		courseTitle = lineReader.next();
    		//The next element should be a STRING for the SECTION NUMBER
    		sectionNum = lineReader.next();
    		//The next element should be an INTEGER for the NUMBER OF CREDIT HOURS
    		credits = lineReader.nextInt();
    		//The next element should be a STRING for the instructor's ID
    		id = lineReader.next();
    		//The next element should be a STRING for the MEETING DAYS
    		meetingDays = lineReader.next();
    		//Check first that the line contains more tokens
    		if (lineReader.hasNext() ) {
    			//The next element should be a INTEGER for the START TIME (assuming the time isn't arranged)	
    			startTime = lineReader.nextInt();
    			
    			//The next element should be a INTEGER for the END TIME (assuming the time isn't arranged)
    			endTime = lineReader.nextInt();
    		}	
    	} catch (NoSuchElementException e) {
    		//If an exception is thrown while parsing the string,
    		//close the scanner and then throw an IllegalArgumentException for 
    		//readCourseRecords().
    		lineReader.close();
    		throw new IllegalArgumentException();
    	}
    	
    	//Once all tokens have been read in, try to create the object
    	try {
    		//If the meeting days is A, call the constructor for an arranged course
    		if (meetingDays.equals("A") && startTime == 0 && endTime == 0) {
    			c = new Course(courseName, courseTitle, sectionNum, credits, id, meetingDays);
    		} else if (meetingDays.equals("A") && (startTime != 0 || endTime != 0)) {
    			lineReader.close();
    			throw new IllegalArgumentException();
    		} else {
    			c = new Course(courseName, courseTitle, sectionNum, credits, id, meetingDays, startTime, endTime);
    		}
    		
    	} catch (IllegalArgumentException e) {
    		lineReader.close();
    		throw new IllegalArgumentException();
    	}
    	lineReader.close();
    	return c;
	}

	/**
     * Writes the given list of Courses to an output file
     * @param fileName the name of the file to be written to
     * @param courses an array list of courses to be written to the file
     * @throws IOException if the file cannot be saved
     */
    public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
    	//Initialize print stream object to write to file
    	PrintStream fileWriter = new PrintStream(new File(fileName));

    	//Index through the array list, printing one course per line
    	for (int i = 0; i < courses.size(); i++) {
    	    fileWriter.println(courses.get(i).toString());
    	}

    	//Once all courses have been printed into the file, close the print stream
    	fileWriter.close();
    }
}