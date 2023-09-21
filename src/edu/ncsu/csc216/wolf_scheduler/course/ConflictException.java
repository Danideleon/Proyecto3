/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Throws an exception if two events or courses have a time conflict
 */
public class ConflictException extends Exception {

	/**
	 * ID used for serialization 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Parameterized constructor with a custom message
	 * @param message the message to be thrown when exception is called
	 */
	public ConflictException (String message) {
		super (message);
	}
	
	/**
	 * Parameterless constructor with default message 
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}

}
