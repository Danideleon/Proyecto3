package edu.ncsu.csc216.wolf_scheduler.course;

/**
 *  Incorporates the checked conflict behavior as an interface
 */
public interface Conflict {

	/**
	 * Checks whether two events or courses in the schedule have a time conflict
	 * @param possibleConflictingActivity the event or schedule that causes a conflict
	 * @throws ConflictException if the events or courses have a time conflict
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
