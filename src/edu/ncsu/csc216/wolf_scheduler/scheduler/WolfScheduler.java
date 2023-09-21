/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The WolfScheduler system provides a way for a student to determine which
 * course schedule may be best for them in an upcoming semester.
 */
public class WolfScheduler {
	
	/**a course catalog */
	private ArrayList<Course> catalog;
	/**a course schedule */
	private ArrayList<Activity> schedule;
	/**a schedule title */
	private String title;
	
	
	/** 
	 * Constructor for the WolfScheduler class
	 * @param fileName The name of the file to read from
	 */
	public WolfScheduler(String fileName) {
		
		//Construct an empty array list to hold the schedule field
		schedule = new ArrayList<>();
		//Construct and empty array list to hold the catalog field
		catalog = new ArrayList<>();
		//set the title to the default
		title = "My Schedule";
		
		//try to add the course objects from the file to the catalog
		try {
			ArrayList<Course> courses = CourseRecordIO.readCourseRecords(fileName);
			catalog.addAll(courses);
			
		} catch(Exception e){ //catch the exception and throw an IAE if file is not found
			throw new IllegalArgumentException("Cannot find file");
		}
		
	}
	
	/**
	 * Method that creates the catalog with all the courses and their 
	 * respective name, section and title.
	 * @return the 2D array with the course catalog
	 */
	public String[][] getCourseCatalog() {
		
		//Return empty 2D array if there are no courses in catalog
		if (catalog == null || catalog.isEmpty()) {
			
			return new String [0][0];
		}
		
		  String [][] catalogArray = new String[catalog.size()][3];
	        for (int i = 0; i < catalog.size(); i++) {
	            Course c = catalog.get(i);
	            catalogArray[i] = c.getShortDisplayArray();
	        }
	        return catalogArray;
		
		}
	
	/**
	 * Creates the schedule with the courses from the catalog with their 
	 * respective name, section an title.
	 * @return a 2D array with the scheduled courses
	 */
	public String[][] getScheduledActivities() {
		
		//Return an empty 2D array if there are no courses  in schedule
		if (schedule == null || schedule.isEmpty()) {
			
			return new String [0][0];
		}
		 String [][] scheduleArray = new String[schedule.size()][4];
	        for (int i = 0; i < schedule.size(); i++) {
	            Activity a = schedule.get(i);
	            scheduleArray[i] = a.getShortDisplayArray();
	        }
	        return scheduleArray;
	}

	/**
	 * Creates the schedule with the courses from the catalog with their respective
	 * name, section, title, credits, instructor ID, and meeting days and times.
	 * @return a 2D array with the courses in the schedule.
	 */
	public String[][] getFullScheduledActivities() {
		
		//Return an empty 2D array if there are no courses in the schedule
		if (schedule == null || schedule.isEmpty()) {
			
			return new String [0][0];
		}
		
		String [][] scheduleArray = new String[schedule.size()][7];
        for (int i = 0; i < schedule.size(); i++) {
            Activity a = schedule.get(i);
            scheduleArray[i] = a.getLongDisplayArray();
        }
        return scheduleArray;
	}

	/**
	 * Gets the course with the given name and section from the catalog. 
	 * @param name the name of the course
	 * @param section the section of the course
	 * @return the course if it exists, null if it does not exist
	 */
	public Course getCourseFromCatalog(String name, String section) {
		
		//Iterate through the courses in the catalog
		for (int i = 0; i < catalog.size(); i++) {
			 
			Course course = catalog.get(i);
			
			//If course has the same name and section return the given course
			if(course.getName().equals(name) && course.getSection().equals(section)) {
				return course;
			} 
		}
		
		//If course is not found return null
		return null;
			
	}
	
	/**
	 * Adds the course with the given name and section to the schedule.
	 * @param name the name of the course
	 * @param section the section of the course
	 * @throws IllegalArgumentException if the course is already in the schedule
	 * @return true if the course exists and can be added to the schedule, false if the course is not in the catalog.
	 * @throws IllegalArgumentException if the courseToAdd is a duplicate in the schedule 
	 */
	public boolean addCourseToSchedule(String name, String section){
				
		//Get the course to add from the catalog
		Course courseToAdd = getCourseFromCatalog(name, section);
		
		//If course is not found return false
		if (courseToAdd == null) {
		return false;
		}

		//Check if the courseToAdd is a duplicate in the schedule 
		for (Activity activity : schedule) {
			if (activity.isDuplicate(courseToAdd)){
				throw new IllegalArgumentException("You are already enrolled in " + name);
				}
			}
		try {
			for (Activity existingCourse : schedule) {
				if (courseToAdd.getEndTime() == existingCourse.getEndTime() || courseToAdd.getStartTime() == existingCourse.getStartTime()) {
					throw new ConflictException();
				}
				
			}
	
		} catch (ConflictException e) {
			throw new IllegalArgumentException("The course cannot be added due to a conflict.");
		}

		//If its not a duplicate , add the course to the schedule
		schedule.add(courseToAdd);
		return true; 

		}
	/**
	 * Adds the event with the given parameters to the schedule
	 * @param eventTitle the title  of the event
	 * @param eventMeetingDays the meeting days for the event
	 * @param eventStartTime the start time for the event
	 * @param eventEndTime the end time for the events
	 * @param eventDetails the details for the event
	 * @throws IllegalArgumentException if the new event is a duplicate of an existing event
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails) {
		
		//Check if the new event is a duplicate of an existing event
		for (Activity existingEvent : schedule) {
			if (existingEvent.getTitle().equals(eventTitle)) {
				throw new IllegalArgumentException("You have already created an event called " + eventTitle);
			}
		}
		
		try {
			for (Activity existingEvent : schedule) {
				if (eventEndTime == existingEvent.getEndTime() || eventStartTime == existingEvent.getStartTime()) {
					throw new ConflictException();
				}
				
				String[] thisMeetingDays = eventMeetingDays.split("");
				
				//Iterate through the meeting days of this activity 
				for (String day : thisMeetingDays) {
					day = day.trim();
						//if at least one meeting day is the same and times are the same throw an exception
					        if (.meetingDays.contains(day) 
					        		&& endTime == possibleConflictingActivity.endTime 
					        		&& startTime == possibleConflictingActivity.startTime) {
					                throw new ConflictException();
					            }
					        }
				
			}
		} catch (ConflictException e) {
			throw new IllegalArgumentException("The event cannot be added due to a conflict.");
		}
		//If its not a duplicate, create a new event and add it to schedule\
		Event newEvent = new Event (eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
		schedule.add(newEvent);
	}
	
	/**
	 * Removes an activity from the schedule 
	 * @param idx the index of the Activity you want to remove from the schedule
	 * @return true if the given course can be removed, false if the course is not on the schedule.
	 */
	public boolean removeActivityFromSchedule(int idx) {
		
		try {
			//Attempt to remove the activity at the specified index
			schedule.remove(idx);
			return true;
		} catch(IndexOutOfBoundsException e) {
			//catch the exception and return false of index is out of bounds
			return false;
		}
	}
	
	/**
	 * Creates an empty Array list to hold the schedule
	 */
	public void resetSchedule() {
	
	//Create an empty array list for the schedule
	 schedule = new ArrayList<>();
		
	}
	
	/**
	 * Gets the schedule title
	 * @return the title for the schedule
	 */
	public String getScheduleTitle() {
		
		//Return the schedule title 
		return title;
	}

	/**
	 * Sets the title for the schedule
	 * @param newTitle the new title for the schedule
	 * @throws IllegalArgumentException If new title is null 
	 */
	public void setScheduleTitle(String newTitle) {
		
		//If new title is null throw an IAE
		if (newTitle == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		
		//Default title is now equal to new title
		title = newTitle;
		
	}

	/**
	 * Exports the newly created schedule with the courses to a file.
	 * @param fileName the name of the file
	 * @throws IllegalArgumentException if the file cannot be saved
	 */
	public void exportSchedule(String fileName) {
	
		//Try using writeCourseRecordsIO to export the file
		try {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
			
		} catch (IOException e) { //Catch if an exception is thrown and throw a new IAE
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}

}
