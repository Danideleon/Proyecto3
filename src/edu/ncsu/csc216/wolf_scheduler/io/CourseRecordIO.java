/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads course records from text files. Writes a set of CourseRecords to a file.
 * 
 * @author Daniela Deleon
 */
public class CourseRecordIO {
	
	 /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
	    ArrayList<Course> courses = new ArrayList<Course>(); //Create an empty array of Course objects
	    while (fileReader.hasNextLine()) { //While we have more lines in the file
	        try { //Attempt to do the following
	            //Read the line, process it in readCourse, and get the object
	            //If trying to construct a Course in readCourse() results in an exception, flow of control will transfer to the catch block, below
	            Course course = readCourse(fileReader.nextLine()); 

	            //Create a flag to see if the newly created Course is a duplicate of something already in the list  
	            boolean duplicate = false;
	            //Look at all the courses in our list
	            for (int i = 0; i < courses.size(); i++) {
	                //Get the course at index i
	                Course current = courses.get(i);
	                //Check if the name and section are the same
	                if (course.getName().equals(current.getName()) &&
	                        course.getSection().equals(current.getSection())) {
	                    //It's a duplicate!
	                    duplicate = true;
	                    break; //We can break out of the loop, no need to continue searching
	                }
	            }
	            //If the course is NOT a duplicate
	            if (!duplicate) {
	                courses.add(course); //Add to the ArrayList!
	            } //Otherwise ignore
	        } catch (IllegalArgumentException e) {
	            //The line is invalid b/c we couldn't create a course, skip it!
	        }
	    }
	    //Close the Scanner b/c we're responsible with our file handles
	    fileReader.close();
	    //Return the ArrayList with all the courses we read!
	    return courses;
	}

	/**
	 * Process each Course line by line 
	 * @param nextLine the next line in the file
	 * @return a newly built course object
	 */
	 private static Course readCourse(String nextLine) {
		 
		 //Create a scanner to process the input line
		 Scanner lineReader = new Scanner (nextLine);
		 //Specify the delimiter as ","
		 lineReader.useDelimiter(",");
		 
		 //Initialize variables to hold the token values
		 String name, title, section, instructorId, meetingDays;
		 int credits; 
		 
		 try {
			//read in tokens and store them in local variables
			 name = lineReader.next().trim();
			 title = lineReader.next().trim();
			 section = lineReader.next().trim();
			 credits = lineReader.nextInt();
			 instructorId = lineReader.next().trim(); 
			 meetingDays = lineReader.next().trim();
			 
			 //throw IAE if meeting days is Arranged and there are more tokens after it.
			 if ("A".equals(meetingDays)) {
				 if (lineReader.hasNext()) {
					 throw new IllegalArgumentException();
				 }
				 
				 //return newly constructed course object
					return new Course (name, title, section, credits, instructorId, meetingDays);
			 }
			 
			 else {
				 //read in tokens for start and end time.
				 int startTime = lineReader.nextInt();
				 int endTime = lineReader.nextInt();
				
				 //throw IAE if there are more tokens
				 if (lineReader.hasNext()) {
					 throw new IllegalArgumentException();
				 }
				 
				 //return a newly constructed course object.
				 return new Course (name, title, section, credits, instructorId, meetingDays, startTime, endTime);
				 
			 }	 
		 } catch (Exception e) {//Catch any exceptions and throw and IAE
			 throw new  IllegalArgumentException();
		 } finally {

		 //close the scanner
		 lineReader.close();
	}
}

}
