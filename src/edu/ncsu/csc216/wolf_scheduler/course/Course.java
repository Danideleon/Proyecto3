/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * System provides a way for a student to determine which course schedule may be best 
 * for them in an upcoming semester.
 * 
 * @author Daniela Deleon
 */
public class Course extends Activity {
	/** Minimum length of name*/
	private static final int MIN_NAME_LENGTH = 5; 
	/** Maximum length of name*/
	private static final int MAX_NAME_LENGTH = 8; 
	/** Minimum letter count*/
	private static final int MIN_LETTER_COUNT = 1; 
	/** Maximum letter count*/
	private static final int MAX_LETTER_COUNT = 4; 
	/** Digit Count*/
	private static final int DIGIT_COUNT = 3; 
	/** Length of Section*/
	private static final int SECTION_LENGTH = 3; 
	/** Maximum amount of credits */
	private static final int MAX_CREDITS = 5; 
	/** Minimum amount of credits */
	private static final int MIN_CREDITS = 1; 
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for 
	 * courses that are arranged.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as a series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}


	/**
	 * Constructs a Course object wit values for all fields.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as a series of chars
	 * @param startTime start time for the Course.
	 * @param endTime end time for the Course.
	 */
	 public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
	            int startTime, int endTime) {
	        super(title, meetingDays, startTime, endTime);
	        setName(name);
	        setSection(section);
	        setCredits(credits);
	        setInstructorId(instructorId);
	    }

	/**
	 * Return the Course's name.
	 * @return the name of the course
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 5 or more than 8,
	 * does not contain a space between letter characters and number characters, has less than 1
	 * or more than 4 letter characters, and not exactly three trailing digit characters, an
	 * IllegalArgumentException is thrown.
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is null
	 * @throws IllegalArgumentException if the name is an empty string 
	 * @throws IllegalArgumentException if the name contains less than 5 characters or greater than 8 characters
	 * @throws IllegalArgumentException if the number of letters is incorrect
	 * @throws IllegalArgumentException if the number of digits is incorrect
	 */
	private void setName(String name) {
		//Throw exception if the name is null
		if(name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		//Throw exception if the name is an empty string 
		//Throw exception if the name contains less than 5 characters or greater than 8 characters
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		//Check for pattern of L[LLL] NNN
		int letterCount = 0;
		int digitCount = 0;
		boolean findSpace = false;
		
		for (int i = 0; i < name.length(); i++) {
			char character = name.charAt(i);
			
			if (!findSpace) {
				if (Character.isLetter(character)) {
					letterCount++;
				}
				else if (character == ' ') {
					findSpace = true;
				}
				else {
					throw new IllegalArgumentException ("Invalid course name.");
				}
				
			}
			else {
			
			if (Character.isDigit(character)) {
				digitCount++;
			}
			else {
				throw new IllegalArgumentException ("Invalid course name.");
				}
			}
		}
		
		//Check that number of letter is correct
		if (letterCount < MIN_LETTER_COUNT || letterCount > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException ("Invalid course name.");
		}
		
		//Check that the number of digits is correct
		if (digitCount != DIGIT_COUNT) {
			throw new IllegalArgumentException ("Invalid course name.");
		}
		
		this.name = name;
	}

	/**
	 * Returns the Course's section.
	 * @return the section of the course
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section.
	 * @param section the section to set
	 * @throws IllegalArgumentException if section is null
	 * @throws IllegalArgumentException if section is not three characters
	 * @throws IllegalArgumentException if any of the 3 characters are not numbers
	 */
	public void setSection(String section) { 
		
		//Throw an exception if section is null
		if (section == null) {
			throw new IllegalArgumentException ("Invalid section.");
		}
		//Throw an exception if section is not three characters
		if (section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException ("Invalid section.");
		}
		//Throw an exception if any of the 3 characters are not numbers
		for (int i = 0; i < section.length(); i++) {
			char character = section.charAt(i);
			
			if (Character.isLetter(character)) {
				throw new IllegalArgumentException ("Invalid section.");
			}
		}
		
		this.section = section;
	}

	/**
	 * Returns the Course's credit hours.
	 * @return the credits of the course
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credit hours. 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credits is less than 1 or greater than 5
	 */
	public void setCredits(int credits) {
		// Throw exception if credits is less than 1 or greater than 5
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}

	/**
	 * Returns the Instructor's unity ID
	 * @return the instructorId who teaches the course
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Instructor's unity ID
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if the Instuctor's ID is null or empty
	 */
	public void setInstructorId(String instructorId) {
		//Throw exception if the Instuctor's ID is null or empty
		if (instructorId == null || "".equals(instructorId)) {
			throw new IllegalArgumentException ("Invalid instructor id.");
		}
		
		this.instructorId = instructorId;
	}

	/**
	 * Sets the Meeting days and times for the course 
	 * @param meetingDays the meeting days for the course
	 * @param startTime the start time for the course
	 * @param endTime the end time for the course
	 * @throws IllegalArgumentException If meeting days consists of any character other than M,T,W,H,F or A or has duplicate characters
	 * @throws IllegalArgumentException If A is the meeting days list, and is not the only character
	 * @throws IllegalArgumentException If the start time is not between 0000 and 2359
	 * @throws IllegalArgumentException If the end time is not between 0000 and 2359
	 * @throws IllegalArgumentException If the end time is less than the start time
	 * @throws IllegalArgumentException If a start and/or end time is listed when meeting days is A
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		
		//meeting consists of any char other than M,T,W,H,F or A
		if (!meetingDays.matches("[MTWHFA]*") || hasDuplicate(meetingDays)) {
			throw new IllegalArgumentException ("Invalid meeting days and times.");	
		} 
		
		//If A is the meeting days list, it must be the only character
		if (meetingDays.contains("A") && meetingDays.length() > 1) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		//The start time is not between 0000 and 2359
		if (startTime < 0 || startTime > 2359) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		//the end time is not between 0000 and 2359
		if (endTime < 0 || endTime > 2359) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		//The end time is less than the start time
		if (endTime < startTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		//A start/end time is listed when meeting days is A
		if ( "A".equals(meetingDays) && (startTime != 0 || endTime != 0)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		//If all conditions are met, set the meeting days and times
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Helper method to check for duplicate characters in a string
	 * @param str string to be iterated through
	 * @return true if there is a duplicate
	 */
	private boolean hasDuplicate(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(str.lastIndexOf(c) != i) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
	    if ("A".equals(getMeetingDays())) {
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 
	}

	/**
	 * Returns a hash code value for the course
	 * @return result the result of the hash code for course
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
	 * Indicates whether some other object is equal to the Course
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
	 * Method that is executed when the program is run.
	 * 
	 * @param args command line arguments 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/** 
	 * Provide a short version of the array of information in the GUI 
	 * @return an array of length 4
	 */
	@Override
	public String[] getShortDisplayArray() {
		String [] shortDisplayArray = {name, section, getTitle(), getMeetingString()}; 
		return shortDisplayArray;
	}
	
	
	/** 
	 * Provide a long version of the array of information in the GUI 
	 * @return an array of length 7
	 */
	@Override
	public String[] getLongDisplayArray() {
		String [] longArrayDisplay = {name, section, getTitle(), String.valueOf(credits), instructorId, getMeetingString(), "" };
		return longArrayDisplay;
	}

	/**
	 * Checks if some other course or event is duplicate 
	 * @param activity the activity to be checked
	 * @return true if the activity is a duplicate
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		//Check if activity is an instance of Course
		if(activity instanceof Course) {
			Course otherCourse = (Course) activity;
			return this.getTitle().equals(otherCourse.getTitle());
		}
		
		return false;
	}



}
