package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * A class providing static IO methods for reading and writing Student records
 *   from and to a file, respectively.
 *   
 * @author kwhildne
 * @author nnbenven 
 * @author demills
 * @author Sarah Heckman
 */
public class StudentRecordIO {

	/**
	 * Given a filename of Student records, reads the file and returns the valid
	 *   Student records in an ArrayList. Individual records in the file are handled
	 *   by the processStudent() helper method. If a Student object cannot be constructed
	 *   from a line in the file, no Student is added to the ArrayList and the method
	 *   moves on to the next line. If a Student object can be constructed, but the Student
	 *   is a duplicate of another Student (i.e. has matching ID), the Student is not added
	 *   to the ArrayList and the method moves on to the next line.
	 * If a filename is null, an empty string, or pointing to a non-existent 
	 *   or unopenable file, a FileNotFoundException is thrown.
	 * @param filename the file to read records from
	 * @return an ArrayList of Student records read from file
	 * @throws FileNotFoundException if the given file cannot be found or accessed
	 */
	public static SortedList<Student> readStudentRecords(String filename) throws FileNotFoundException {
		Scanner fileReader;
		try {
			fileReader = new Scanner(new FileInputStream(filename));
		} catch(NullPointerException e) {
			throw new FileNotFoundException();
		}
		SortedList<Student> students = new SortedList<Student>();
		// While the reader has more lines, delegates to the processStudent
		// helper method to read in Students.
		while (fileReader.hasNextLine()) {
			try {
				Student student = processStudent(fileReader.nextLine());
				boolean duplicate = false;
				// If the processed record matches a Student who is already read in,
				//   duplicate is set to true, and the duplicate will not be added to
				//   the ArrayList of students.
				for (int i = 0; i < students.size(); i++) {
					User s = students.get(i);
					// A Student is a duplicate if its ID matches one in the students ArrayList.
					if (student.getId().equals(s.getId())) {
						//it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					students.add(student);
				}
			} catch (IllegalArgumentException e) {
				// An IllegalArgumentException will be thrown from ProcessStudent if 
				//   the line cannot be processed into a valid Student record.
				// Nothing should be done if this happens, just continues to next line.
			}
		}
		fileReader.close();
		return students;
	}
	
	/**
	 * Given a filename and an ArrayList of Students, writes the Student records 
	 *   to the file as a comma-separated sequence of Strings in the format:
	 *   "firstName,lastName,id,email,password,maxCredits"
	 *   
	 * @param filename the file to write Student records to 
	 * @param studentDirectory an ArrayList of Students to write to file
	 * @throws IOException if the file cannot be created
	 */
	public static void writeStudentRecords(String filename, SortedList<Student> studentDirectory) throws IOException {
		// IOException is thrown from the File constructor if file cannot be created.
		// If the filename is null, a NullPointerException is caught and an IOException thrown in its place.
		PrintStream fileWriter ;
		try {
			fileWriter = new PrintStream(new File(filename));
		} catch(NullPointerException e) {
			throw new IOException();
		} 

		for (int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();
	}
	
	/**
	 * Private helper method for readCourseRecords() that processes a single line 
	 *   in a file of Student records. Given a line of comma-separated values, reads 
	 *   in the values, creates a Student object and returns it.
	 * If the values cannot be read from the String, or the values read in are illegal for 
	 *   creating Student objects, an IllegalArgumentException is thrown.
	 * 
	 * @param line a sequence of comma-separated values representing a Student
	 * @return Student read in from the given line
	 * @throws IllegalArgumentException if a Student cannot be read from the line
	 */
	private static Student processStudent(String line) {
		// Impossible for a null line to be passed here, so no 
		//   check is made.
		if (line.equals("")) {
			throw new IllegalArgumentException();
		}

		Scanner readLine = new Scanner(line);
		readLine.useDelimiter(",");

		String firstName;
		String lastName; 
		String id;
		String email;
		String password;
		int maxCredits;

		// Attempts to read in a line, expecting the tokens to be there. If the tokens
		//   aren't there, a NoSuchElementException will be thrown and then caught in 
		//   the catch block.
		try {
			firstName = readLine.next();
			lastName = readLine.next();
			id = readLine.next();
			email = readLine.next();
			password = readLine.next();
			maxCredits = readLine.nextInt();
			readLine.close();
			Student s = new Student(firstName, lastName, id, email, password, maxCredits);
			return s;
		} catch (NoSuchElementException e) {
			readLine.close();
			throw new IllegalArgumentException();
		}
	}
}