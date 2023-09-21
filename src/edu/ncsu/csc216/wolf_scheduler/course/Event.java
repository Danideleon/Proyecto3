/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Event class that extends the activity class
 */
public class Event extends Activity {
	
	/**Event details */
	private String eventDetails;

	/**
	 * Constructor for the Event class
	 * @param title the title of the events
	 * @param meetingDays the meeting days for the event
	 * @param startTime the start time of the event
	 * @param endTime the end time of the event
	 * @param eventDetails the details for the event
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}

	/**
	 * Gets the Event Details
	 * @return the eventDetails the event details to get
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Sets the Event Details  
	 * @param eventDetails the eventDetails to set
	 * @throws IllegalArgumentException if event details is null or empty 
	 */
	public void setEventDetails(String eventDetails) {
		//Throw IAE if event details is null or empty 
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		this.eventDetails = eventDetails;
	}
	
	/**
	 * Sets the Meeting days and times for the event 
	 * @param meetingDays the meeting days for the event
	 * @param startTime the start time for the event
	 * @param endTime the end time for the event
	 * @throws IllegalArgumentException If meeting days is empty, null or doesn't contain valid characters
	 * @throws IllegalArgumentException If start and/or end time is less than 0 or more than 2359
	 * @throws IllegalArgumentException If end time is earlier than start time
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		
		//If meeting days is empty, null or doesn't contain valid characters
		if(meetingDays == null || meetingDays.isEmpty() || !meetingDays.matches("[MTWHFSU]*")) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		//If start/end time is invalid
		if (startTime < 0 || startTime > 2359 || endTime < 0 || endTime > 2359) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		//If end time is earlier than start time
		if (endTime < startTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		//If all checks pass, set the meeting days and times
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/** 
	 * Create a short version of the array of information in the GUI 
	 * @return an array of length 4
	 */
	@Override
	public String[] getShortDisplayArray() {
		//Get a string array of length four
		String [] shortDisplayArray = {"", "", getTitle(), getMeetingString()};
		return shortDisplayArray;
	}

	/** 
	 * Create a long version of the array of information in the GUI 
	 * @return an array of length 7
	 */
	@Override
	public String[] getLongDisplayArray() {
		//Get a string array of length seven
		String [] longDisplayArray = {"", "", getTitle(), "", "", getMeetingString(), eventDetails};
		return longDisplayArray;
	}

	/**
	 * Returns a String representation of the event
	 */
	@Override
	public String toString() {
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + eventDetails; 
	}

	/**
	 * Checks if some other course or event is duplicate 
	 * @param activity the activity to be checked
	 * @return true if the activity is a duplicate
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		//Check if activity is an instance of Event
		if(activity instanceof Event) {
			Event otherEvent = (Event) activity;
			return this.getTitle().equals(otherEvent.getTitle());
		}
		
		return false;
	}

}
