package edu.ncsu.csc216.wolf_scheduler.course;

/** 
 * Super class of the Course class
 */
public abstract class Activity implements Conflict {

	/** Hours in a day*/
	private static final int UPPER_HOUR = 24;
	/** Minutes in an hour */
	private static final int UPPER_MINUTE = 60;
	/** zero */
	private static final int ZERO = 0;
	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/** 
	 * Constructor for the Activity class
	 * @param title the title of the course
	 * @param meetingDays the meeting days for the course
	 * @param startTime the start time of the course
	 * @param endTime the end time of the course
	 */
	  public Activity(String title, String meetingDays, int startTime, int endTime) {
	        super();
	        setTitle(title);
	        setMeetingDaysAndTime(meetingDays, startTime, endTime);
	    }

	/**
	 * Returns the Course's title. 
	 * @return the title of the course
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title. 
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is null or empty
	 */
	public void setTitle(String title) {
		
		//Throw an exception if title is null or an empty string
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException ("Invalid title.");
		}
		
		this.title = title;
		
	}

	/**
	 * Returns the Course's meeting days.
	 * @return the meetingDays of the course
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Course's start time.
	 * @return the startTime of the course
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's end time.
	 * @return the endTime of the course
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the days and time in which the course meets. 
	 * @param meetingDays the meeting days to set
	 * @param startTime the start time of the course to set
	 * @param endTime the end time of the course to set
	 * @throws IllegalArgumentException if meetingDays is null
	 * @throws IllegalArgumentException if meeting days is A and start and end time is not zero
	 * @throws IllegalArgumentException if any letters differ from weekdays 
	 * @throws IllegalArgumentException if any weekday counter is more than one
	 * @throws IllegalArgumentException if start time not between 0 and 23 for hours or 0 and 59 for minutes, inclusive
	 * @throws IllegalArgumentException if end time not between 0 and 23 for hours or 0 and 59 for minutes, inclusive
	 * @throws IllegalArgumentException if ending time is less than starting time
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		
		//Throw exception if meetingDays is null 
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		//Throw exception if meeting days is A and start and end time is not zero
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			
			//set the parameters
			this.meetingDays = meetingDays;
			this.startTime = 0;
			this.endTime = 0;
		}
		else {
			
			//counters for each weekday
			int monCount = 0;
			int tuesCount = 0;
			int wedCount = 0;
			int thurCount = 0;
			int friCount = 0;
			int satCount = 0;
			int sunCount = 0;
		
			//iterate through each character in meeting days and increase weekday counter
			// throw exception if any letters differ from weekdays 
			for (int i = 0; i < meetingDays.length(); i++) {
				char day = meetingDays.charAt(i);
				
				switch(day) {
				
				case 'M':
					monCount++;
					break;
				case 'T':
					tuesCount++;
					break;
				case 'W':
					wedCount++;
					break;
				case 'H':
					thurCount++;
					break;
				case 'F':
					friCount++;
					break;
				case 'S':
					satCount++;
					break;
				case 'U' :
					sunCount++;
					break;
				default:
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			
			// Throw exception if any weekday counter is more than one
			if (monCount > 1 || tuesCount > 1 || wedCount > 1 || thurCount > 1 || friCount > 1 || satCount > 1 || sunCount > 1) {
				throw new IllegalArgumentException ("Invalid meeting days and times.");
			}
			
			// Convert military to standard time
			int startHour = startTime / 100;
			int startMin = startTime % 100;
			int endHour = endTime / 100;
			int endMin = endTime % 100;
			
			//not between 0 and 23 for hours or 0 and 59 for minutes, inclusive
			if (startHour <= ZERO || startHour >= UPPER_HOUR || startMin < ZERO || startMin >= UPPER_MINUTE) {
				throw new IllegalArgumentException ("Invalid meeting days and times.");
			}
			
			//not between 0 and 23 for hours or 0 and 59 for minutes, inclusive
			if (endHour <= ZERO || endHour >= UPPER_HOUR || endMin < ZERO || endMin >= UPPER_MINUTE) {
				throw new IllegalArgumentException ("Invalid meeting days and times.");
			}
			
			//Throw exception if ending time is less than starting time
			if (endHour <= startHour) {
				throw new IllegalArgumentException ("Invalid meeting days and times.");
			}
			
		}
		
		
		//Set up the parameters
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
		
	}

	/**
	 * Helper method to convert military time into standard time
	 * @param time the time in military format
	 * @return the time in standard format
	 */
	private String getTimeString(int time) {
		
		//Convert military time to standard time
		int hours = time / 100;
		int minutes = time % 100;
		
		//Initialize string to hold AM or PM
		String ampm;
		
		//if hours are more than or equal to 12 it is PM
		if ( hours >= 12) {
		ampm = "PM";
		} 
		
		//Otherwise its AM
		else {
			ampm = "AM";
		}
		
		//0 is military time is equal to 12AM in standard time
		if (hours == 0) {
			hours = 12; 
		}
		
		//Otherwise subtract from 12
		else if (hours > 12){
			hours -= 12;
			
		}
		
		//format hours and minutes
		String hourStr = String.format("%d", hours);
		String minuteStr = String.format("%02d", minutes);
		
		//return hours followed by minutes and AM or PM
		return hourStr + ":" + minuteStr + ampm;
		
	}

	/**
	 * Checks if there exists any conflict in overlapping times between this activity 
	 * and the possibly conflicting activity
	 * @param possibleConflictingActivity the activity that might cause a timing conflict
	 * @throws ConflictException if end time of this event is equal to start time of the possibly conflicting activity and vice versa
	 * @throws ConflictException if at least one meeting day is the same and times are the same throw an exception
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		
			// throw exception if end time of this event is equal to start time of the possibly conflicting activity and vice versa
			if (endTime == possibleConflictingActivity.startTime || startTime == possibleConflictingActivity.endTime) {
				throw new ConflictException ();
			}
			
			String[] thisMeetingDays = meetingDays.split("");
			
			//Iterate through the meeting days of this activity 
			for (String day : thisMeetingDays) {
				day = day.trim();
					//if at least one meeting day is the same and times are the same throw an exception
				        if (possibleConflictingActivity.meetingDays.contains(day) 
				        		&& endTime == possibleConflictingActivity.endTime 
				        		&& startTime == possibleConflictingActivity.startTime) {
				                throw new ConflictException();
				            }
				        }
				    }

	/**
	 * Returns a string with the meeting days and times 
	 * @return the meeting days and times of the course
	 */
	public String getMeetingString() {
		
		//Initialize variable to hold start and end time
		String starting = getTimeString(startTime);
		String ending = getTimeString(endTime);
		String meeting;
		
		// if meeting days is A meeting string is "Arranged"
		if ("A".equals(meetingDays)) {
			meeting = "Arranged";
		
			
		}
		
		//Otherwise return meeting string with days and start and end time.
		else {
			meeting = meetingDays + " " +  starting + "-" + ending;
		}
		
		return meeting; 
	}
	
	/** 
	 * Provide a short version of the array of information in the GUI 
	 * @return an array of length 4
	 */
	public abstract String[] getShortDisplayArray();
	
	/** 
	 * Provide a long version of the array of information in the GUI 
	 * @return an array of length 7
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * Checks if some other course or event is duplicate 
	 * @param activity the activity to be checked
	 * @return true if the activity is a duplicate
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Returns a hash code value for the activity
	 * @return result the result of the hash code for activity
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Indicates whether some other object is equal to the activity
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	


}